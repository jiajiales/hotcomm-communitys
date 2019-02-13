package com.hotcomm.community.accesscontrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.accesscontrol.mapper.SynchroDoorRecordMapper;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.en.person.PersonLableEN;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DoorRecordPA;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.doorcontrol.SynchroDoorRecordData;
import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

@Service
public class SynchroDoorRecordDataImpl extends BaseService implements SynchroDoorRecordData {
	@Autowired
	SynchroDoorRecordMapper synchroDoorRecordMapper;

	@Autowired
	CommunityService communityService;
	@Autowired
	PersonService personService;
	@Autowired
	PersonRecordService personRecordService;

	@Override
	public void SynchroDoorRecord() throws HKException {
		/*CommunityListAllUM communityListAllUM=new CommunityListAllUM();//测试代码
		communityListAllUM.setCid(31);
		communityListAllUM.setDoorduDepId(6062);*/
		
		
		List<CommunityListAllUM> queryListCommunityAll = communityService.queryListCommunityAll();
		if (queryListCommunityAll.size() != 0) {
			for (CommunityListAllUM communityListAllUM : queryListCommunityAll) {
				List<HotHttpEntity> params = new ArrayList<>();
				params.add(new HotHttpEntity("token", EntityEnum.TEXT, getToken()));
				params.add(new HotHttpEntity("depId", EntityEnum.TEXT, communityListAllUM.getDoorduDepId()));
				params.add(new HotHttpEntity("beginTime", EntityEnum.TEXT, DateUtils.getBeforeNdayTime2(1)));
				params.add(new HotHttpEntity("endTime", EntityEnum.TEXT, DateUtils.getAfterNdayTime(1)));
				params.add(new HotHttpEntity("limit", EntityEnum.TEXT, 500));
				params.add(new HotHttpEntity("page", EntityEnum.TEXT, 1));
				HotHttpResponse response = HttpClientUtils.doPost(params,
						"http://ddflow.doordu.com/open_api/d_d/visitorList/v1");
				JSONObject object = JSONObject.fromObject(response.getReturnJson());
				if (object.get("code").equals("200")) {
					if(!object.get("data").equals("null")){
						@SuppressWarnings("unchecked")
						List<Object> list = (List<Object>) object.get("data");
						if (list != null) {
							Map<String, Object> tableParams = super.getTableParams(communityListAllUM.getCid());
							List<Integer> logIds = synchroDoorRecordMapper.queryLogId(tableParams);
							List<DoorRecordPA> in = new ArrayList<>();
							for (Object obj : list) {
								JSONObject data = JSONObject.fromObject(obj);
								PersonDM no = personService.PersonInfoByNo(communityListAllUM.getCid(),
										(String) data.getString("userId"));
								tableParams.put("mac", data.get("doorGuid"));
								Map<String, Object> map = synchroDoorRecordMapper.queryAddrByMac(tableParams);	
								if (logIds.size() != 0) {// 剔除重复的数据，
									if (!logIds.contains(data.get("logId"))) {
										DoorRecordPA pa = new DoorRecordPA();
										pa.setDeviceCode(data.getString("doorGuid"));
										pa.setDoorduLogId(data.getInt("logId"));
										pa.setImgs("http://doordustorage.oss-cn-shenzhen.aliyuncs.com/"
												+ data.getString("imageUrl"));
										if (no != null) {
											pa.setLableId(no.getLableId());
											pa.setPId(no.getPId());
											pa.setName(no.getName());
											pa.setAge(no.getAge());
											pa.setSex(no.getSex());
											pa.setHeadimg(no.getHeadimg());
										}
										pa.setOpenStyleName((String)data.getString("operateStyleName"));
										pa.setRecordAddress((String)map.get("devAddress"));
										pa.setLon((String)map.get("lon"));
										pa.setLat((String)map.get("lat"));
										pa.setRecordTime(data.getString("logTime"));
										int operateStyle = (int)data.getInt("operateStyle")==1?3:(int)data.getInt("operateStyle")==11?2:4;
										pa.setRecordType(operateStyle);
										pa.setVideo(data.getString("videoUrl"));
										in.add(pa);
									}
								} else {
									DoorRecordPA pa = new DoorRecordPA();
									pa.setDeviceCode(data.getString("doorGuid"));
									pa.setDoorduLogId(data.getInt("logId"));
									pa.setImgs("http://doordustorage.oss-cn-shenzhen.aliyuncs.com/"
											+ data.getString("imageUrl"));
									if (no != null) {
										pa.setLableId(no.getLableId());
										pa.setPId(no.getPId());
										pa.setName(no.getName());
										pa.setAge(no.getAge());
										pa.setSex(no.getSex());
										pa.setHeadimg(no.getHeadimg());
									}
									pa.setOpenStyleName((String)data.getString("operateStyleName"));
									pa.setRecordAddress((String)map.get("devAddress"));
									pa.setLon((String)map.get("lon"));
									pa.setLat((String)map.get("lat"));
									pa.setRecordTime(data.getString("logTime"));
									int operateStyle = (int)data.getInt("operateStyle")==1?3:(int)data.getInt("operateStyle")==11?2:4;
									pa.setRecordType(operateStyle);
									pa.setVideo(data.getString("videoUrl"));
									in.add(pa);
								}
							}
							if (in.size() != 0) {// 添加到库
								synchroDoorRecordMapper.insertBatch(tableParams, in);
								for (DoorRecordPA doorRecordPA : in) {
									//推送到大数据页面
									PersonFaceMessageSendUM sendUM = new PersonFaceMessageSendUM();
									sendUM.setCommunityId(communityListAllUM.getCid());
									sendUM.setAddress(doorRecordPA.getRecordAddress());
									sendUM.setLat(doorRecordPA.getLat());
									sendUM.setLng(doorRecordPA.getLon());
									sendUM.setRecordImgs(doorRecordPA.getImgs());
									sendUM.setRecordType(doorRecordPA.getRecordType());
									if (doorRecordPA.getLableId()==null) {
										sendUM.setLableType(PersonLableEN.getValueByIndex(-1).getKey());
									}else {
										sendUM.setLableType(PersonLableEN.getValueByIndex(doorRecordPA.getLableId()).getKey());
									}
									sendUM.setDeviceMac(doorRecordPA.getDeviceCode());
									sendUM.setPName(doorRecordPA.getName());
									sendUM.setSex(doorRecordPA.getSex());
									sendUM.setAge(doorRecordPA.getAge());
									sendUM.setFaceImgs(doorRecordPA.getHeadimg());
									sendUM.setPId(doorRecordPA.getPId());
									sendUM.setLableId(doorRecordPA.getLableId());
									sendUM.setRecordId(doorRecordPA.getId());
									sendUM.setCode("record");
									personRecordService.RecordMessageSend(sendUM);
								}
							}
						}
					}
				}
			}
		}
	}

}
