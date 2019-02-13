package com.hotcomm.community.person.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.person.PersonAlarmInfoDM;
import com.hotcomm.community.common.bean.db.person.PopulationAlarmDM;
import com.hotcomm.community.common.bean.ui.person.PersonLast50AlarmUM;

public interface PersonAlarmMapper {

	public Page<PopulationAlarmDM> PersonAlarmPage(@Param("tableParams")Map<String, Object> tableParams);
	
	public PersonAlarmInfoDM PersonAlarmInfo(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	
	@Update("UPDATE ${tableParams.dynamic_dbname}.hk_population_alarm alarm SET alarm.`isdelete`=1 WHERE alarm.`id`=#{id}")
	public Integer delPersonAlarm(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	
	/**
	 * 新增警报记录
	 * @param tableParams 
	 * @param time 创建时间
	 * @param address 警报地址
	 * @param alarmLv 警报等级
	 * @param lableId	标签id
	 * @param img 图片
	 * @param video 音频
	 * @param doorduLogId 多度id号
	 * @param faceNo 人脸编号 
	 * @param type 记录类型 1 人脸感知 2 门禁摄像头 3 门卡开门  4 手机APP开门 5 密码开门
	 * @param pId 居民id
	 * @param reason	警报原因
	 * @param way 警报来源类型 1未出 2未归 3 出行频率 4 出行频率 连续
	 * @return
	 */
	public Integer addPersonAlarm(@Param("tableParams")Map<String, Object> tableParams,
									@Param("time")String time,
									@Param("address")String address,
									@Param("alarmLv")Integer alarmLv,
									@Param("lableId")Integer lableId,
									@Param("img")String img,
									@Param("video")String video,
									@Param("doorduLogId")String doorduLogId,
									@Param("faceNo")String faceNo,
									@Param("type")Integer type,
									@Param("pId")Integer pId,
									@Param("reason")String reason,
									@Param("way")Integer way);
	
	/**
	 * 查找规定时间内某个人物的最后一次通行时间
	 * @param tableParams
	 * @param pId
	 * @param beginTime
	 * @param endTime
	 * @param way  1 未归 2 未出 3 出行频率 4 出现频率-连续
	 * @return
	 */
	public String selectLastAlarmTime(@Param("tableParams")Map<String, Object> tableParams,@Param("pId")Integer pId,@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("way")Integer way);
	
	/**
	 * 查找最后50条报警记录
	 * @param tableParams
	 * @param type  1人脸报警/2通行
	 * @return
	 */
	List<PersonLast50AlarmUM> getAlarmLast50(@Param("tableParams")Map<String, Object> tableParams,@Param("type")Integer type);
	
	/**
	 * 本周警报数
	 * @param tableParams
	 * @param type 1人脸报警/2通行
	 * @return
	 */
	Integer getAlarmLastWeekNum(@Param("tableParams")Map<String, Object> tableParams,@Param("type")Integer type);
}
