package com.hotcomm.community.person.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hotcomm.community.person.mapper.PersonHoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.person.DuoDuRoomMesDM;
import com.hotcomm.community.common.bean.db.person.PassInfoOfDayDM;
import com.hotcomm.community.common.bean.db.person.PassInfoOfMonDM;
import com.hotcomm.community.common.bean.db.person.PersonAlarmByLevelDM;
import com.hotcomm.community.common.bean.db.person.PersonAlarmTypeDM;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.db.person.PersonIDAndNoDM;
import com.hotcomm.community.common.bean.en.person.PersonLableEN;
import com.hotcomm.community.common.bean.pa.person.AddPersonPA;
import com.hotcomm.community.common.bean.pa.person.PersonPagePA;
import com.hotcomm.community.common.bean.pa.person.PopulationRoomPagePA;
import com.hotcomm.community.common.bean.pa.person.PersonStrangerPagePA;
import com.hotcomm.community.common.bean.ui.house.PersonRoomUM;
import com.hotcomm.community.common.bean.ui.person.LablePopulationUM;
import com.hotcomm.community.common.bean.ui.person.PersonClassificationUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfDayUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfMonUM;
import com.hotcomm.community.common.bean.ui.person.PersonStrangerListUM;
import com.hotcomm.community.common.bean.ui.person.PopulationSituationUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.PersonRoomService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.person.mapper.PersonMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.comm.SetParamNull;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

@Service
@Transactional
public class PersonServiceImpl extends BaseService implements PersonService {

    @Autowired
    private PersonMapper person;

    @Autowired
    private PersonRoomService personRoom;


	@Autowired
	private PersonHoleMapper personHoleMapper;

    @Value("${face.url}")
    private String url;

	@Override
	public Map<String, Integer> getThePopulation(Integer communityId) {
		Map<String, Object> params = super.getTableParams(communityId);

		Map<String, Integer>result=new HashMap<>();
		result.put("localChildren",IfNullSet0(person.personClassification(params, 1, 0)));
		result.put("localPeople",IfNullSet0(person.personClassification(params, 2, 0)));
		result.put("localOldMan",IfNullSet0(person.personClassification(params, 3, 0)));
		result.put("nonLocalChildren",IfNullSet0(person.personClassification(params, 1, 1)));
		result.put("nonLocalPeople",IfNullSet0(person.personClassification(params, 2, 1)));
		result.put("nonLoaclOldMan",IfNullSet0(person.personClassification(params, 3, 1)));
		result.put("allLocalPopulation",IfNullSet0(person.personClassification(params, null, 0)));
		result.put("allNonLocalPopulation",IfNullSet0(person.personClassification(params, null, 1)));
		return result;
	}

	@Override
    public PopulationSituationUM getPopulationSituation(Integer communityId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        PopulationSituationUM result = new PopulationSituationUM();
        result.setTotalPopulation(IfNullSet0(person.getTotalPopulation(params)));// 区域总人数
        result.setTotalNumberOfPass(IfNullSet0(person.getTotalNumberOfPass(params)));// 今日通行总人数
        result.setTotalTimeOfPass(IfNullSet0(person.getTotalTimeOfPass(params)));// 今日通行总人次数
        result.setAlarmOfTimeToMonth(IfNullSet0(person.getAlarmOfTimeToMonth(params)));// 本月报警次数
        result.setCarePopulation(IfNullSet0(person.getLablePopulation(params, PersonLableEN.CARE_PERSON.getKey())));// 关爱人群数
        result.setBlacklistPopulation(IfNullSet0(person.getLablePopulation(params, PersonLableEN.BLACKLIST_PERSON.getKey())));// 黑名单数
        return result;
    }

    @Override
    public LablePopulationUM getLablePopulationSituation(Integer communityId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        LablePopulationUM result = new LablePopulationUM();
        result.setCarePerson(IfNullSet0(person.getLablePopulation(params, PersonLableEN.CARE_PERSON.getKey())));
        result.setRiskPerson(IfNullSet0(person.getLablePopulation(params, PersonLableEN.RISK_PERSON.getKey())));
        result.setServerPerson(IfNullSet0(person.getLablePopulation(params, PersonLableEN.SERVER_PERSON.getKey())));
        result.setBlacklistPerson(IfNullSet0(person.getLablePopulation(params, PersonLableEN.BLACKLIST_PERSON.getKey())));
        return result;
    }

    @Override
    public PersonClassificationUM getPersonClassification(Integer communityId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        PersonClassificationUM result = new PersonClassificationUM();
        result.setPersonNum(person.getTotalPopulation(params));
        result.setChildren(IfNullSet0(person.personClassification(params, 1, null)));
        result.setYoungpeople(IfNullSet0(person.personClassification(params, 2, null)));
        result.setOldpeople(IfNullSet0(person.personClassification(params, 3, null)));
        result.setLocalaccount(IfNullSet0(person.personClassification(params, null, 0)));
        result.setUnlocalaccount(IfNullSet0(person.personClassification(params, null, 1)));
        return result;
    }

    @Override
    public List<PersonAlarmByLevelDM> getAlarmCondition(Integer communityId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        List<PersonAlarmByLevelDM> data = person.getPersonAlarmByLevel(params);// 数据库中的数据
        List<PersonAlarmByLevelDM> result = new ArrayList<>(); // 需组装的数据
        int i = 1;
        int z = 1;
        for (PersonAlarmByLevelDM db : data) {
            if(db ==null) {
                while(i<13) {
                    result.add(new PersonAlarmByLevelDM(i, 0, 0, 0));
                    i++;
                }
            }
            else {
                while (i < 13) { // 循环12个月
                    if (db.getMon() == i) { // 若当前月份有数据,则添加数据
                        result.add(db);
                        i++;
                        if (data.size() == z) {
                            continue;
                        }
                        z++;
                        break;
                    } else { // 若当前月份没有数据,则添加0
                        result.add(new PersonAlarmByLevelDM(i, 0, 0, 0));
                        i++;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public PersonAlarmTypeDM getAlarmOfTypeToMonth(Integer communityId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        PersonAlarmTypeDM result = person.getAlarmOfTypeToMonth(params);
        if(result==null) {
            result=new PersonAlarmTypeDM(0, 0, 0);
        }
        result.setBlacknum(result.getBlacknum());
        result.setCarenum(result.getCarenum());
        result.setRisknum(result.getRisknum());
        return result;
    }

    @Override
    public List<PersonPassInfoOfDayUM> getPassInfoOfDay(Integer communityId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        List<PassInfoOfDayDM> DBdata1 = person.getPassInfoOfdayByNum(params,0);// 今日通行人数
        List<PassInfoOfDayDM> DBdata2 = person.getPassInfoOfdayByTime(params,0);// 今日通行人次数
        int i = 1;
        int z = 1;
        List<PersonPassInfoOfDayUM> result = new ArrayList<>();
        for (PassInfoOfDayDM data : DBdata1) {
            if(data==null) {
                while(i<25) {
                    result.add(new PersonPassInfoOfDayUM(i, 0, 0));
                }
            }else{
                while (i < 25) {
                    if (data.getH() == i) {
                        result.add(new PersonPassInfoOfDayUM(i, data.getNum(), 0));
                        i++;
                        if (DBdata1.size() == z) {
                            continue;
                        }
                        z++;
                        break;
                    } else {
                        result.add(new PersonPassInfoOfDayUM(i, 0, 0));
                        i++;
                    }
                }
            }
        }

        for (PassInfoOfDayDM data : DBdata2) {
            result.get(data.getH()-1).setPassTime(data.getNum());
        }
        if(DBdata1.toString()=="[]" || DBdata2.toString()=="[]") {
            while (i < 25) {
                result.add(new PersonPassInfoOfDayUM(i, 0, 0));
                i++;
            }
        }
        return result;
    }

    @Override
    public List<PersonPassInfoOfMonUM> getPassInfoOfMon(Integer communityId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        List<PassInfoOfMonDM> DBdata1 = person.getPassInfoOfMonByNum(params);// 本月通行人数
        List<PassInfoOfMonDM> DBdata2 = person.getPassInfoOfMonByTime(params);// 本月通行人次数
        List<PersonPassInfoOfMonUM> result = new ArrayList<>();
        int i = 1;
        int z = 1;
	    while (i < 32) {
		    result.add(new PersonPassInfoOfMonUM(i,0,0));
		    i++;
	    }
        for (PassInfoOfMonDM data : DBdata1) {
            result.get(data.getD()-1).setPeopleNum(data.getNum());
        }
        for (PassInfoOfMonDM data : DBdata2) {
            result.get(data.getD()-1).setPassTime(data.getNum());
        }
        return result;
    }

	@Override
	public Map<String, Object> getPassInfoOfWeek(Integer communityId) {
		Map<String, Object> params = super.getTableParams(communityId);

		List<PassInfoOfMonDM> DBdata1 = person.getPassInfoOfWeekByNum(params);// 本月通行人数
		List<PassInfoOfMonDM> DBdata2 = person.getPassInfoOfWeekByTime(params);// 本月通行人次数
		List<PersonPassInfoOfMonUM> data = new ArrayList<>();
		for (int i=0;i<DBdata1.size();i++) {
			data.add(new PersonPassInfoOfMonUM(DBdata1.get(i).getD(),DBdata1.get(i).getNum(),DBdata2.get(i).getNum()));
		}
		Map<String, Object> result=new HashMap<>();
		result.put("data", data);
		return result;
	}

	@Override
    public PageInfo<PersonDM> getPersonPageList(PersonPagePA PAparam) throws HKException {
        Map<String, Object>  params = super.getTableParams(PAparam.getCommunityId());

        PageHelper.startPage(PAparam.getPageIndex(), PAparam.getPageSize());
        String labelType=null;
        if(PAparam.getLableType()!=null) {
            labelType=PersonLableEN.getValueByIndex(PAparam.getLableType()).getKey();
        }
        Page<PersonDM> page=person.PersonPageList(params,PAparam,labelType);
        List<PersonDM> personPageList=page;
        return new PageInfo<>(page.getTotal(), personPageList,PAparam.getPageSize(),PAparam.getPageIndex());
    }

    @Override
    public PersonDM getPersonInfo(Integer communityId, Integer pId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);
        return person.PersonInfo(params, pId);
    }

    @Override
    public Integer deletePersonMessage(Integer communityId, Integer pId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        if(person.checkPersonHoleById(params, pId)!=0)throw this.exceptionManager.configLog(error).errorRecord("PE0005", new Exception());
        Integer result;
        try {
            result = person.deletePersonMessage(params, pId);//删除该人员信息
            person.deletePersonRecordRe(params, pId);//删除通行记录
            person.deletePersonAlarmRe(params, pId);//删除报警记录
            person.deletePersonRoomRe(params, pId);//删除房屋关联
        } catch (Exception e) {
            throw this.exceptionManager.configLog(error).errorRecord("PE0003", e);
        }

        return result;
    }

    @Override
    public Integer addPersonMessage(AddPersonPA pa) throws HKException {
        Map<String, Object> params = super.getTableParams(pa.getCommunityId());
        AddPersonPA PAparam = SetParamNull.setObjVal(pa);
        if (person.checkEntranceCardno(params,PAparam.getEntranceCardno())!=0) throw this.exceptionManager.configLog(error).errorRecord("PE0004", new Exception());
        if (person.checkEntrancePhone(params,PAparam.getPhone())!=0) throw this.exceptionManager.configLog(error).errorRecord("PE0006", new Exception());
        if (person.checkEntranceCardno(params,PAparam.getCardNo())!=0) throw this.exceptionManager.configLog(error).errorRecord("PE0007", new Exception());
        Integer result;
        try {
            if(PAparam.getHeadImg()!=null) {//人脸头像是否为空
                //提交人脸图片
                List<HotHttpEntity> param = new ArrayList<>();
                param.add(new HotHttpEntity("img",  EntityEnum.TEXT,pa.getHeadImg()));
                HotHttpResponse response = HttpClientUtils.doPost(param, url);
                System.out.println(response.getReturnJson());
                JSONObject fromObject = JSONObject.fromObject(response.getReturnJson());
                if (!fromObject.get("code").equals("200")) {
                    throw exceptionManager.configLog(service).serviceRecord("PE0011");
                }
                PAparam.setFaceNo(fromObject.get("faceNo").toString()); //获取人脸编号
            }
            result = person.AddPersonMessage(params, PAparam);//添加用户
        } catch (Exception e) {
            throw this.exceptionManager.configLog(error).errorRecord("PE0001", e);
        }

        return result;
    }

    @Override
    public Integer updatePersonMessage(AddPersonPA PAparam, Integer pId) throws HKException {
        Map<String, Object> params = super.getTableParams(PAparam.getCommunityId());
        Integer result;
	    personHoleMapper.updateHolePopulationLableId(params, pId, PAparam.getLableId());
        try {
	        if(PAparam.getHeadImg()!=null) {//人脸头像是否为空
		        //提交人脸图片
		        List<HotHttpEntity> param = new ArrayList<>();
		        param.add(new HotHttpEntity("img",  EntityEnum.TEXT,PAparam.getHeadImg()));
		        HotHttpResponse response = HttpClientUtils.doPost(param, url);
		        JSONObject fromObject = JSONObject.fromObject(response.getReturnJson());
		        if (!fromObject.get("code").equals("200")) {
			        throw exceptionManager.configLog(service).serviceRecord("PE0011");
		        }
		        PAparam.setFaceNo(fromObject.get("faceNo").toString()); //获取人脸编号
	        }
            result = person.updatePersonMessage(params, PAparam, pId);
        } catch (Exception e) {
            throw this.exceptionManager.configLog(error).errorRecord("PE0002", e);
        }

        return result;
    }

    @Override
    public PersonStrangerListUM StrangerInfo(Integer communityId, String faceNo) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        return person.StrangerInfo(params, faceNo);
    }

    @Override
    public List<PersonIDAndNoDM> getPersonIDAndNo(Integer communityId, Integer Sage, Integer Eage, Integer sex,
                                                  Integer lableId, String people, String nationality, String name) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        return person.getPersonIDAndNo(params, Sage, Eage, sex, lableId, people, nationality, name);
    }

    @Override
    public Integer updatePersonAlarmTime(Integer communityId, Integer pId) throws HKException {
        Map<String, Object> params = super.getTableParams(communityId);

        return person.updatePersonAlarmTime(params, pId);
    }

    @Override
    public PageInfo<PersonRoomUM> PeopleRoomRePage(PopulationRoomPagePA pa) throws HKException {
        Map<String, Object>  params = super.getTableParams(pa.getCommunityId());

        PageHelper.startPage(pa.getPageIndex(), pa.getPageSize());
        Page<PersonRoomUM> page=person.PeopleRoomRePage(params, pa);
        List<PersonRoomUM> personPageList=page;
        return new PageInfo<>(page.getTotal(), personPageList,pa.getPageSize(),pa.getPageIndex());
    }

    private Integer IfNullSet0(Integer a) {
        if(a!=null) {
            return a;
        }else
            return 0;
    }

    @Override
    public PersonDM PersonInfoByNo(Integer communityId, String pNo)  throws HKException{
        Map<String, Object>  params = super.getTableParams(communityId);

        return person.PersonInfoByNo(params, pNo);
    }

    @Override
    public PageInfo<PersonStrangerListUM> PersonStrangerPage(PersonStrangerPagePA pa)  throws HKException{
        Map<String, Object>  params = super.getTableParams(pa.getCommunityId());

        PageHelper.startPage(pa.getPageIndex(), pa.getPageSize());
        Page<PersonStrangerListUM> page=person.PersonStrangerPage(params, pa);
        List<PersonStrangerListUM> personPageList=page;

        return new PageInfo<>(page.getTotal(), personPageList,pa.getPageSize(),pa.getPageIndex());
    }

    @Override
    public Integer delPersonStranger(Integer communityId,Integer id)  throws HKException{
        Map<String, Object>  params = super.getTableParams(communityId);

        return person.delStrangerById(params,id);
    }

    @Override
    public Integer SetStrangerToPerson(AddPersonPA PAparam, Integer id) throws HKException{
        Integer addPersonMessage = this.addPersonMessage(PAparam);
        if(addPersonMessage>0) {
            Integer delPersonStranger = this.delPersonStranger(PAparam.getCommunityId(), id);
            if(delPersonStranger<=0) {
                throw this.exceptionManager.configLog(error).errorRecord("PE0002", new  Exception());
            }
        }
        return 1;
    }

    @Override
    public Integer addPersonReRoom(com.hotcomm.community.common.bean.pa.house.PersonRoomPA pa) {
        Map<String, Object>  params = super.getTableParams(pa.getCommunityId());

        //添加人房关联
        personRoom.addData(pa);
/*

        PersonDM personInfo = person.PersonInfo(params, pa.getPId());//获取个人信息
        String[] url = personInfo.getHeadimg().split("/");
        String[] people = personInfo.getPeople().split("族");
        DuoDuRoomMesDM duoDuRoomData = person.getDuoDuRoomData(params, pa.getRoomId());//获取该多度房间与单元信息

        //第三方添加人员
        List<HotHttpEntity> param = new ArrayList<>();
        param.add(new HotHttpEntity("token",  EntityEnum.TEXT,getToken()));
        param.add(new HotHttpEntity("platId",  EntityEnum.TEXT,7));//平台ID'1-物管,2-综合治理,7-新网智能'
        param.add(new HotHttpEntity("depId",  EntityEnum.TEXT,params.get("doorduDepId")));//小区id
        param.add(new HotHttpEntity("unitId",  EntityEnum.TEXT,duoDuRoomData.getDuUnitId()));//单元ID
        param.add(new HotHttpEntity("roomNumberId",  EntityEnum.TEXT,duoDuRoomData.getDuRoomId()));//房号ID
        param.add(new HotHttpEntity("nationCode",  EntityEnum.TEXT,"86"));//手机号国家码前缀,默认‘86’
        param.add(new HotHttpEntity("mobileNo",  EntityEnum.TEXT,personInfo.getPhone()));//手机号码
        param.add(new HotHttpEntity("userName",  EntityEnum.TEXT,personInfo.getName()));//姓名(长度50)
        param.add(new HotHttpEntity("isUserAppFlag",  EntityEnum.TEXT,1));//是否开启APP授权[0：否，1：是]
        param.add(new HotHttpEntity("isForeign",  EntityEnum.TEXT,"1"));//证件是否外籍证件是否外籍[0外藉，1中国大陆，2中国香港，3中国澳门，4中国台湾，5新加坡]，默认1
        param.add(new HotHttpEntity("personCard",  EntityEnum.TEXT,personInfo.getCardNo()));//证件号码，长度[20]
        param.add(new HotHttpEntity("zjzl",  EntityEnum.TEXT,personInfo.getCardType()));//证件种类[1身份证号, 2港澳通行证,3台胞证, 4护照]，默认1
        param.add(new HotHttpEntity("birthPlace",  EntityEnum.TEXT,personInfo.getAccountAddress()));//户籍地址，长度[150]
        param.add(new HotHttpEntity("sentGovernment",  EntityEnum.TEXT,"广东省"));//签发机关，长度[60]
        param.add(new HotHttpEntity("validity",  EntityEnum.TEXT,"2028.12.30"));//证件有效期限，长度[20]
        param.add(new HotHttpEntity("cardIco",  EntityEnum.TEXT,url[url.length-1]));//头像地址，长度[200]
        param.add(new HotHttpEntity("authType",  EntityEnum.TEXT,2));//用户类型[0-业主,1-家人, 2-租客, 3-临时客人,4-代理人]，默认2
        param.add(new HotHttpEntity("nation",  EntityEnum.TEXT,people[0]));//民族，长度[10]，注意不带族字，默认汉
        param.add(new HotHttpEntity("careType",  EntityEnum.TEXT,1));//关爱人员类型 [1=>普通,2=>伤残人士,3=>孤寡老人,4=>老上访户,5=>老干部,6=>留守儿童,7=>五保户,8=>其他]
        param.add(new HotHttpEntity("deviceTypeId",  EntityEnum.TEXT,1));//卡类型[5: ‘IC卡’;11:’身份证卡’; 30:’CPU卡’;24:’蓝牙设备’] 默认1
        param.add(new HotHttpEntity("cardNo",  EntityEnum.TEXT,personInfo.getEntranceCardno()));//门禁卡卡号
        param.add(new HotHttpEntity("expireTimeStr",  EntityEnum.TEXT,"2099-12-30"));//门禁卡过期时间字符串[yyyy-MM-dd]
        param.add(new HotHttpEntity("beginTimeStr",  EntityEnum.TEXT,"2018-01-01"));//APP授权必填，APP授权有效时间(开始时间)yyyy-MM-dd
        param.add(new HotHttpEntity("endTimeStr",  EntityEnum.TEXT,"2099-12-30"));//APP授权必填APP授权有效时间(结束时间)yyyy-MM-dd(长度20)
        HotHttpResponse response = HttpClientUtils.doPost(param, "http://ddflow.doordu.com/open_api/u_o_c/userRegistThird/v1");
        JSONObject fromObject = JSONObject.fromObject(response.getReturnJson());
        if (!fromObject.get("code").equals("200")) {
            throw exceptionManager.configLog(service).serviceRecord("PE0011");
        }

        //把第三方返回的结果存入到数据库
        JSONObject object = JSONObject.fromObject(fromObject.get("data"));
        AddPersonPA addPersonPA = new  AddPersonPA();
        addPersonPA.setPNo(object.get("userId").toString());
        addPersonPA.setDuoduCardId(object.get("cardId").toString());
        person.updatePersonMessage(params, addPersonPA, pa.getPId());

        //添加第三方人脸
        List<HotHttpEntity> param2 = new ArrayList<>();
        param2.add(new HotHttpEntity("token",  EntityEnum.TEXT,getToken()));
        param2.add(new HotHttpEntity("cardId",  EntityEnum.TEXT,object.get("cardId").toString()));//身份证id
        param2.add(new HotHttpEntity("roomNumberId", EntityEnum.TEXT,duoDuRoomData.getDuRoomId()));//房号id
        param2.add(new HotHttpEntity("photos",  EntityEnum.TEXT,personInfo.getHeadimg()));//人工拍摄图片
        HotHttpResponse response1 = HttpClientUtils.doPost(param2, "http://ddflow.doordu.com/pf/addFace/v1");
        JSONObject fromObject1 = JSONObject.fromObject(response1.getReturnJson());
        if (!fromObject1.get("code").equals("200")) {
            throw exceptionManager.configLog(service).serviceRecord("PE0011");
        }
*/

        return 1;
    }

	@Override
	public Map<String, Integer> personInfoTask(Integer communityId) {
		Map<String, Object>  params = super.getTableParams(communityId);

		Map<String,Integer> result=new HashMap<>();
		result.put("personNum",person.getTotalPopulation(params));
		result.put("localPopulation", person.personClassification(params, null, 0));
		result.put("nonLocalPopulation",person.personClassification(params,null, 1));
		result.put("carePersonNum",person.getLablePopulation(params,PersonLableEN.CARE_PERSON.getKey()));
		result.put("blackPersonNum",person.getLablePopulation(params,PersonLableEN.BLACKLIST_PERSON.getKey()));
		result.put("strangerPersonNum",person.StrangerFaceInductionNum(params,1));
		result.put("strangerPersonTime",person.StrangerFaceInduction(params,1));
		result.put("recordNum",person.getTotalTimeOfPassAll(params));
		return result;
	}


	private List<Integer> getTimeInterval(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		List<Integer> result=new ArrayList<>();
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String imptimeBegin = sdf.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		result.add(Integer.parseInt(imptimeBegin));
		result.add(Integer.parseInt(imptimeEnd));
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return result;
	}

}
