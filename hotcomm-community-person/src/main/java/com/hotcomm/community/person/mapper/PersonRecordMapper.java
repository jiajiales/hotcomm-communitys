package com.hotcomm.community.person.mapper;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.ui.person.DeviceMacByRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.person.PersonRecordDM;
import com.hotcomm.community.common.bean.db.person.PersonRecordInfoDM;
import com.hotcomm.community.common.bean.db.person.RecordNumDM;
import com.hotcomm.community.common.bean.ui.person.PersonLast50RecordUM;

public interface PersonRecordMapper {

	/**
	 * 通行记录(分页)
	 * @param tableParams
	 * @return
	 */
	public Page<PersonRecordDM> PersonRecordPage(@Param("tableParams")Map<String, Object> tableParams);
	
	/**
	 * 通行记录详情
	 * @param tableParams
	 * @param id
	 * @return
	 */
	public PersonRecordInfoDM PersonRecordInfo(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	
	/**
	 * 根据id 删除通行记录
	 * @param tableParams
	 * @param id
	 * @return
	 */
	@Update("UPDATE ${tableParams.dynamic_dbname}.hk_person_record record SET record.`isdelete`=1 WHERE record.`uid`=#{id}")
	public Integer delPersonRecord(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	
	/**
	 *	根据用户id与时间筛选通行记录
	 * @param tableParams
	 * @param pId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public PersonRecordDM getPersonRecordByPId(@Param("tableParams")Map<String, Object> tableParams,@Param("pId")Integer pId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	/**
	 * 根据id 和预定时间 统计通行次数
	 * @param tableParams
	 * @param beginTime
	 * @param endTime
	 * @param pId
	 * @return
	 */
	public List<RecordNumDM> selectRecordNum(@Param("tableParams")Map<String, Object> tableParams,@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("pId")Integer pId);
	
	/**
	 * 查询最后50条通行记录
	 * @param tableParams
	 * @param type  1人脸/2通行
	 * @return
	 */
	public List<PersonLast50RecordUM> getRecordLast50(@Param("tableParams")Map<String, Object> tableParams,@Param("type")Integer type);
	
	/**
	 * 获取最后5条通行记录的图片
	 * @param tableParams
	 * @return
	 */
	public List<String> getRecordLast5Img(@Param("tableParams")Map<String, Object> tableParams);
	/**
	 * 添加通行记录
	 * @param tableParams
	 * @param pId	j用户id
	 * @param recordTime	通行时间
	 * @param recordAddress	地址
	 * @param lableId	标签id
	 * @param imgs	图片
	 * @param video	视频
	 * @param faceNo	第三方人脸
	 * @param recordType	通行方式
	 * @param deviceCode	设备mac
	 * @return
	 */
	public Integer addRecord(@Param("tableParams")Map<String, Object> tableParams,
							@Param("pId") Integer pId,
							@Param("recordTime") String recordTime,
							@Param("recordAddress") String recordAddress,
							@Param("lableId") Integer lableId,
							@Param("imgs") String imgs,
							@Param("video") String video,
							@Param("faceNo") String faceNo,
							@Param("recordType") Integer recordType,
							@Param("deviceCode") String deviceCode);

	/**
	 * 获取最后添加的通行记录id
	 * @param tableParams
	 * @param recordTime    接受时间
	 * @param img      接收图片
	 * @return
	 */
	@Select("SELECT uid FROM ${tableParams.dynamic_dbname}.`hk_person_record` WHERE record_time=#{recordTime} AND imgs=#{img} limit 1")
	public Integer getLastRecordId(@Param("tableParams")Map<String, Object> tableParams,@Param("recordTime") String recordTime,@Param("img") String img);

	/**
	 * 根据id查询此人今天是否有通行
	 * @param tableParams
	 * @param pId
	 * @return
	 */
	Integer getRecordByToday(@Param("tableParams") Map<String, Object> tableParams,@Param("pId")Integer pId);
	
	/**
	 * 根据faceNo查询此人今天是否通行
	 * @param tableParams
	 * @param faceNo	人脸编号
	 * @return
	 */
	Integer getRecordByTodayByNo(@Param("tableParams") Map<String, Object> tableParams,@Param("faceNo")String faceNo);

	/**
	 * 根据pid/faceNo 获取最近1个月通行被记录的设备mac 与最新时间
	 * @param tableParams
	 * @param faceNo    人脸id
	 * @param pId   居民id
	 * @return
	 */
	List<DeviceMacByRecord> getDeviceMacByRecord(@Param("tableParams") Map<String, Object> tableParams, @Param("faceNo")String faceNo, @Param("pId")Integer pId);
}
