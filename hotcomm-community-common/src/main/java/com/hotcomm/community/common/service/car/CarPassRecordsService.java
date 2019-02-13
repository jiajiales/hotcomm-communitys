package com.hotcomm.community.common.service.car;

import java.util.List;
import java.util.Map;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPA;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPagePA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.community.common.bean.ui.car.CarAttentionCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassHoursCountUM;
import com.hotcomm.community.common.bean.ui.car.CarMonParkCountListUM;
import com.hotcomm.community.common.bean.ui.car.CarMonTimeCountListUM;
import com.hotcomm.community.common.bean.ui.car.CarPassHourCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPassRecordsUM;
import com.hotcomm.community.common.bean.ui.car.CarPostureParkingCountUM;
import com.hotcomm.community.common.bean.ui.car.CarmMonEnterCountUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CarPassRecordsService {
	
	/**
	 * 分页查询车辆通行记录列表
	 * @param carPassRecordsPagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CarPassRecordsUM> page(CarPassRecordsPagePA carPassRecordsPagePA) throws HKException;
	
	/**
	 * 新增车辆通行记录
	 * @param carPassRecordsPA
	 * @return
	 * @throws HKException
	 */
	public Integer insert(CarPassRecordsPA carPassRecordsPA)  throws HKException;;

	/**
	 * 查询通行记录详情
	 * @param communityId
	 * @param id
	 * @return
	 * @throws HKException
	 */
	public CarPassRecordsUM detail(Integer communityId ,Integer id) throws HKException;
	
	/**
	 * 查询指定天数车辆的总通行数
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public  Integer selectDayCount(Map<String, Object> map)  throws HKException;
	
	/**
	 * 查询指定天数通行的车辆数
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public Integer selectDayCarCount(Map<String, Object> map) throws HKException;
	
	/**
	 * 查询当月敏感时段车辆总数
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public  Integer selectSenTimeCount(Map<String, Object> map)  throws HKException;
	
	/**
	 * 查询上个月份各时段进出车辆总数(通行时段分析)
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarMonTimeCountListUM selectMonTimeCount(Integer communityId) throws HKException;
	
	/**
	 * 查询本月停车情况(登记车辆、非登记车辆)
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarMonParkCountListUM selectMonParkingCount(Integer communityId) throws HKException;
	
	/**
	 * 本月关注车辆通行记录统计
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<CarAttentionCountUM> selectAttentionCount(Integer communityId) throws HKException;

	/**
	 * 查询陌生车辆当月进入次数
	 * @param communityId
	 * @param carAlarmRuleUM
	 * @return
	 * @throws HKException
	 */
	public  List<CarmMonEnterCountUM>  selectMonEnterCount(Integer communityId,CarAlarmRuleUM carAlarmRuleUM)  throws HKException;
	
	/**
	 * 按小时查询今日通行车辆数
	 * @param dynamicDbname
	 * @return
	 * @throws HKException
	 */
	public List<CarFeelPassHoursCountUM>  selectDistinctCarPassCount(String dynamicDbname) throws HKException;
	
	/**
	 *  按小时查询今日通行车辆次数
	 * @param dynamicDbname
	 * @return
	 * @throws HKException
	 */
	public List<CarFeelPassHoursCountUM>  selectCarPassCount(String dynamicDbname) throws HKException;

	/**
	 * 按小时查询最一个月、一年车辆进、出次数(态势分析)
	 * @param dynamicDbname
	 * @param timeType
	 * @return
	 * @throws HKException
	 */
	public  CarPassHourCountUM selectMonYearCarPassCount(String dynamicDbname,Integer timeType) throws HKException;
	
	/**
	 * 按天数查询最一个月、一年停车车辆数(态势分析)
	 * @param dynamicDbname
	 * @param timeType
	 * @return
	 * @throws HKException
	 */
	public CarPostureParkingCountUM  selectPostureParkingCount(String dynamicDbname,Integer timeType)  throws HKException;
	
	/**
	 * 关注车辆出入统计(态势分析)
	 * @param dynamicDbname
	 * @param timeType
	 * @return
	 * @throws HKException
	 */
	public List<CarAttentionCountUM> selectPosAttentionCount(String dynamicDbname,Integer timeType) throws HKException;

	/**
	 * 查询车辆最新一条通行记录
	 * @param communityId
	 * @param num
	 * @return
	 * @throws HKException
	 */
	public CarPassRecordsUM  selectNewCarAddress(Integer communityId,String num) throws HKException;
	
	/**
	 * 查询车辆列表
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public  List<CarPassRecordsUM> selectPassRecordList(Map<String, Object> map) throws HKException;
	
}
