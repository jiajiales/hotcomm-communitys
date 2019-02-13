package com.hotcomm.community.person.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.person.DuoDuRoomMesDM;
import com.hotcomm.community.common.bean.db.person.PassInfoOfDayDM;
import com.hotcomm.community.common.bean.db.person.PassInfoOfMonDM;
import com.hotcomm.community.common.bean.db.person.PersonAlarmByLevelDM;
import com.hotcomm.community.common.bean.db.person.PersonAlarmTypeDM;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.db.person.PersonIDAndNoDM;
import com.hotcomm.community.common.bean.pa.person.AddPersonPA;
import com.hotcomm.community.common.bean.pa.person.PersonPagePA;
import com.hotcomm.community.common.bean.pa.person.PopulationRoomPagePA;
import com.hotcomm.community.common.bean.pa.person.PersonStrangerPagePA;
import com.hotcomm.community.common.bean.ui.house.PersonRoomUM;
import com.hotcomm.community.common.bean.ui.person.PersonFloorsStatisticsUM;
import com.hotcomm.community.common.bean.ui.person.PersonStrangerListUM;
import com.hotcomm.community.common.bean.ui.person.PopulationByLableUM;
import com.hotcomm.community.common.bean.ui.person.PopulationTrendUM;
import com.hotcomm.community.common.bean.ui.person.RecordNumByTypeUM;

public interface PersonMapper {

	/**
	 * 区域总人数
	 * 
	 * @param params
	 * @return
	 */
	@Select("SELECT COUNT(`person`.`p_id`) TotalPopulation FROM ${params.dynamic_dbname}.`hk_person` person LEFT JOIN ${params.dynamic_dbname}.hk_person_lable lable ON person.lable_id=lable.id WHERE IF(person.lable_id!=0,lable.type_code!='F04',1=1) AND person.data_type=1 AND person.lable_id !=-1 AND person.isdelete=0 ")
	Integer getTotalPopulation(@Param("params") Map<String, Object> params);

	/**
	 * 今日通行总人数
	 * 
	 * @param params
	 * @return
	 */
	Integer getTotalNumberOfPass(@Param("params") Map<String, Object> params);

	/**
	 * 今日通行总人次数
	 * 
	 * @param params
	 * @return
	 */
	Integer getTotalTimeOfPass(@Param("params") Map<String, Object> params);

	/**
	 * 通行总人次数
	 *
	 * @param params
	 * @return
	 */
	Integer getTotalTimeOfPassAll(@Param("params") Map<String, Object> params);


	/**
	 * 各类标签人群数
	 * 
	 * @param params
	 * @param lableType F01：关爱人群，F02:危险人群，F03:服务人群，F04:黑名单人群
	 * @return
	 */
	Integer getLablePopulation(@Param("params") Map<String, Object> params, @Param("lableType") String lableType);


	/**
	 * 本月警报次数
	 * 
	 * @param params
	 * @return
	 */
	Integer getAlarmOfTimeToMonth(@Param("params") Map<String, Object> params);

	/**
	 * 人口分类统计人数
	 * 
	 * @param params
	 * @param type       年龄分类(不传 请传个null) 1:小孩 2:劳动人员 3:老人
	 * @param kosekiType 是否本地户口(不传 请传个null) 0:本地户口 1:非本地户口
	 * @return
	 */
	Integer personClassification(@Param("params") Map<String, Object> params, @Param("type") Integer type,
			@Param("kosekiType") Integer kosekiType);

	/**
	 * 警报统计
	 * 
	 * @param params
	 * @return
	 */
	List<PersonAlarmByLevelDM> getPersonAlarmByLevel(@Param("params") Map<String, Object> params);

	/**
	 * 本月警报类型情况
	 * 
	 * @param params
	 * @param params   人群类型
	 * @return
	 */
	PersonAlarmTypeDM getAlarmOfTypeToMonth(@Param("params") Map<String, Object> params);

	/**
	 * 24小时通行人数
	 * 
	 * @param params
	 * @param type 1人脸  2全部
	 * @return
	 */
	List<PassInfoOfDayDM> getPassInfoOfdayByNum(@Param("params") Map<String, Object> params,@Param("type") Integer type);

	/**
	 * 24小时通行人次数
	 * 
	 * @param params
	 * @param type 1人脸  2全部
	 * @return
	 */
	List<PassInfoOfDayDM> getPassInfoOfdayByTime(@Param("params") Map<String, Object> params,@Param("type") Integer type);

	/**
	 * 本周通行人数
	 *
	 * @param params
	 * @return
	 */
	List<PassInfoOfMonDM> getPassInfoOfWeekByNum(@Param("params") Map<String, Object> params);

	/**
	 * 本周通行人次数
	 *
	 * @param params
	 * @return
	 */
	List<PassInfoOfMonDM> getPassInfoOfWeekByTime(@Param("params") Map<String, Object> params);

	/**
	 * 31天通行人数
	 * 
	 * @param params
	 * @return
	 */
	List<PassInfoOfMonDM> getPassInfoOfMonByNum(@Param("params") Map<String, Object> params);

	/**
	 * 31天通行人次数
	 * 
	 * @param params
	 * @return
	 */
	List<PassInfoOfMonDM> getPassInfoOfMonByTime(@Param("params") Map<String, Object> params);

	/**
	 * 居民信息(分页)
	 * @param params
	 * @param PAparam
	 * @return
	 */
	Page<PersonDM> PersonPageList(@Param("params") Map<String, Object> params,@Param("PAparam")PersonPagePA PAparam,@Param("lableType")String lableType);
	

	/**
	 * 根据id查询居民信息
	 * @param params
	 * @param pId  居民id
	 * @return
	 */
	PersonDM PersonInfo(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);
	
	/**
	 * 根据id删除居民
	 * @param params
	 * @param pId
	 * @return
	 */
	@Update("UPDATE ${params.dynamic_dbname}.hk_person person SET person.`isdelete`=1 WHERE person.`p_id`=#{pId}")
	Integer deletePersonMessage(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);
	
	/**
	 * 添加居民
	 * @param params
	 * @param PAparam
	 * @return
	 */
	Integer AddPersonMessage(@Param("params") Map<String, Object> params,@Param("PAparam")AddPersonPA PAparam);
	
	/**
	 * 修改居民
	 * @param params
	 * @param PAparam
	 * @param pId	修改的居民id
	 * @return
	 */
	Integer updatePersonMessage(@Param("params") Map<String, Object> params,@Param("PAparam")AddPersonPA PAparam,@Param("pId")Integer pId);
	
	/**
	 * 获取居民id 和 编号
	 * @param params
	 * @param Sage
	 * @param Eage
	 * @param sex
	 * @param lableId   标签id(-2,查的是该小区居民且不为黑名单的)
	 * @param people
	 * @param nationality
	 * @return
	 */
	List<PersonIDAndNoDM> getPersonIDAndNo(@Param("params") Map<String, Object> params,
											@Param("Sage") Integer Sage,
											@Param("Eage") Integer Eage,
											@Param("sex") Integer sex,
											@Param("lableId") Integer lableId,
											@Param("people") String people,
											@Param("nationality") String nationality,
											@Param("name")String name);
	/**
	 * 添加警报次数
	 * @param params
	 * @param pId
	 * @return
	 */
	@Update("UPDATE ${params.dynamic_dbname}.hk_person person SET person.`alarm_nums`=person.`alarm_nums`+1 WHERE person.`p_id`=#{pId}")
	Integer updatePersonAlarmTime(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);
	
	/**
	 * 关联房间
	 * @param params
	 * @param pa
	 * @return
	 */
	Page<PersonRoomUM> PeopleRoomRePage(@Param("params") Map<String, Object> params,@Param("pa")PopulationRoomPagePA pa);
	

	/**
	 * 人脸感知人数(根据类型分类)
	 * @param params
	 * @param lableType 
	 * @return
	 */
	Integer FaceInduction(@Param("params") Map<String, Object> params,@Param("lableType")String lableType);
	
	/**
	 * 人脸感知人次数(根据类型分类)
	 * @param params
	 * @param lableType 
	 * @return
	 */
	Integer FaceInductionNum(@Param("params") Map<String, Object> params,@Param("lableType")String lableType);
	
	/**
	 * 进出通行人数
	 * @param params
	 * @return
	 */
	Integer getRecordNumberOfPass(@Param("params") Map<String, Object> params);
	
	/**
	 * 进出通行人次数(可根据开门方式统计次数)
	 * @param params
	 * @return
	 */
	Integer getRecordTotalTimeOfPass(@Param("params") Map<String, Object> params);
	
	/**
	 * 开门方式统计
	 * @param params
	 * @return
	 */
	List<RecordNumByTypeUM> getRecordTotalByType(@Param("params") Map<String, Object> params);
	
	/**
	 * 1年通行人数
	 * 
	 * @param params
	 * @return
	 */
	List<PassInfoOfMonDM> getPassInfoOfYearByNum(@Param("params") Map<String, Object> params);

	/**
	 * 1年通行人次数
	 * 
	 * @param params
	 * @return
	 */
	List<PassInfoOfMonDM> getPassInfoOfYearByTime(@Param("params") Map<String, Object> params);
	
	/**
	 * 人口趋势
	 * @param params
	 * @param year 年份
	 * @return
	 */
	PopulationTrendUM getPopulationTrend(@Param("params") Map<String, Object> params,@Param("year") Integer year,@Param("month")Integer month);
	
	/**
	 * 各类标签人群统计(去年)
	 * @param params
	 * @return
	 */
	PopulationByLableUM getLastYearLablePopulation(@Param("params") Map<String, Object> params);
	
	/**
	 * 楼栋常驻人群统计
	 * @param params
	 * @return
	 */
	List<PersonFloorsStatisticsUM> getPersonNumByfloors(@Param("params") Map<String, Object> params);
	
	/**
	 * 根据人员编号查询人员信息
	 * @param params
	 * @param pNo
	 * @return
	 */
	PersonDM PersonInfoByNo(@Param("params") Map<String, Object> params,@Param("pNo")String pNo);

	/**
	 * 根据人员编号查询人员信息
	 * @param params
	 * @param faceNo
	 * @return
	 */
	PersonDM PersonInfoByFaceNo(@Param("params") Map<String, Object> params,@Param("faceNo")String faceNo);
	

	
	
	/**
	 * 根据roomid获取多度的单元id与房间id
	 * @param params
	 * @param roomId
	 * @return
	 */
	DuoDuRoomMesDM getDuoDuRoomData(@Param("params") Map<String, Object> params,@Param("roomId") Integer roomId);
	
	
	
	/** 陌生人操作 **/	
	/**
	 * 陌生人页面
	 * @param params
	 * @param pa
	 * @return
	 */
	Page<PersonStrangerListUM> PersonStrangerPage(@Param("params") Map<String, Object> params,@Param("pa")PersonStrangerPagePA pa);
	
	/**
	 * 根据人脸标号查找陌生人
	 * @param params
	 * @param faceNo
	 * @return
	 */
	PersonStrangerListUM getStrangerByNo(@Param("params") Map<String, Object> params,@Param("faceNo")String faceNo);
	/**
	 * 获取陌生人人数
	 * @param params
	 * @return
	 */
	Integer getStrangerNum(@Param("params") Map<String, Object> params);
	/**
	 * 陌生人次数
	 * @param params
	 * @return
	 */
	Integer StrangerFaceInduction(@Param("params") Map<String, Object> params,@Param("type")Integer type);

	/**
	 * 陌生人人脸数
	 * @param params
	 * @return
	 */
	Integer StrangerFaceInductionNum(@Param("params") Map<String, Object> params,@Param("type")Integer type);
	/**
	 * 根据id查询陌生人信息
	 * @param params
	 * @param faceNo
	 * @return
	 */
	PersonStrangerListUM StrangerInfo(@Param("params") Map<String, Object> params,@Param("faceNo")String faceNo);
	
	/**
	 * 删除陌生人信息
	 * @param id
	 * @return
	 */
	@Delete("delete from ${params.dynamic_dbname}.hk_person_stranger where id=#{id}")
	Integer delStrangerById(@Param("params") Map<String, Object> params,@Param("id")Integer id);
	
	/**
	 * 添加陌生人信息
	 * @param params
	 * @param faceNo	人脸编号
	 * @param headImg	图片路径
	 * @param address	地址
	 * @return
	 */
	Integer addStrangerMessage(@Param("params") Map<String, Object> params,
								@Param("faceNo") String faceNo,
								@Param("headImg") String headImg,
								@Param("address") String address);
	
	/**
	 * 根据人脸编号修改陌生人频率与时间
	 * @param params
	 * @param faceNo	编号
	 * @param frequency	出现频率
	 * @return
	 */
	Integer updateStrangerMessage(@Param("params") Map<String, Object> params,@Param("faceNo") String faceNo,@Param("frequency") Integer frequency);

	/**
	 * 根据pId判断是否存在与房间的关联
	 * @param params
	 * @param pId   用户id
	 * @return
	 */
	Integer getPersonRoomReExists(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);

	/*验证*/

	/**
	 * 检查是否存在相同的卡号
	 * @param params
	 * @param cardNo
	 * @return
	 */
	@Select("SELECT COUNT(person.p_id) FROM  ${params.dynamic_dbname}.`hk_person` person WHERE  person.entrance_cardno =#{cardNo}")
	Integer checkEntranceCardno(@Param("params") Map<String, Object> params,@Param("cardNo") String cardNo);

	/**
	 * 检查是否存在相同的卡号
	 * @param params
	 * @param phone
	 * @return
	 */
	@Select("SELECT COUNT(person.p_id) FROM  ${params.dynamic_dbname}.`hk_person` person WHERE  person.phone =#{phone}")
	Integer checkEntrancePhone(@Param("params") Map<String, Object> params,@Param("phone") String phone);

	/**
	 * 检查是否存在相同的卡号
	 * @param params
	 * @param IdentityCard
	 * @return
	 */
	@Select("SELECT COUNT(person.p_id) FROM  ${params.dynamic_dbname}.`hk_person` person WHERE  person.card_no =#{IdentityCard}")
	Integer checkEntranceIdentityCard(@Param("params") Map<String, Object> params,@Param("IdentityCard") String IdentityCard);

	/**
	 * 检查是否还有布控
	 * @param params
	 * @param pId
	 * @return
	 */
	Integer checkPersonHoleById(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);

	/**
	 * 根据id删除所有通行记录
	 * @param params
	 * @param pId
	 * @return
	 */
	@Update("UPDATE ${params.dynamic_dbname}.hk_person_record SET isdelete=1 WHERE p_id=#{pId}")
	Integer deletePersonRecordRe(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);

	/**
	 * 根据id删除所有报警记录
	 * @param params
	 * @param pId
	 * @return
	 */
	@Update("UPDATE ${params.dynamic_dbname}.hk_population_alarm SET isdelete=1 WHERE p_id=#{pId}")
	Integer deletePersonAlarmRe(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);

	/**
	 * 删除人房关联
	 * @param params
	 * @param pId
	 * @return
	 */
	@Update("UPDATE ${params.dynamic_dbname}.hk_person_room SET isdelete=1 WHERE p_id=#{pId}")
	Integer deletePersonRoomRe(@Param("params") Map<String, Object> params,@Param("pId")Integer pId);
}
