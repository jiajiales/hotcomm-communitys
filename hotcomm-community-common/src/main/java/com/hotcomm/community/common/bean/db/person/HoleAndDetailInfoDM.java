package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class HoleAndDetailInfoDM implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -5832473507851735129L;

	//编号
    private  Integer holeId;
   
	 //布控主题
    private  String holeTitle;
   
	 //布控类型 1 群里 2 单人
    private  Integer holeType;
   
	 //布控个人|群体  使用Json type=1 -->>p_id  type=2 {sex:boy,age:[20,50],nationality:china,people=han,lable=01}
    private  String holePopulations;
   
	 //报警 级别
    private  Integer alarmLv;
   
	 //状态 0 未开启 1 已开启 2 已结束
    private  Integer holeStatus;
   
	 //[{p_id,lastTime},......]
    private  String recordTime;
   
	 //登记时间
    private  String createTime;
   
	 //登入人员
    private  Integer createUser;
   
	 //最新修改时间
    private  String updateTime;
   
	 //最新修改人员
    private  String updateUser;
   
	 //推送人与信息  {"receiver":[{"p_id":1,"message":"GO","p-name":"zzz"},{"p_id":2,"message":"GO","p_name":"aaa"}]}
    private  String push;
    
    //编号
    private  Integer detailId;
      
	 //布控开始时间
    private  String beginTime;
   
	 //布控结束时间
    private  String endTime;
   
	 //未归时间 单位为小时
    private  Integer noReturn;
   
	 //未出时间 单位未小时
    private  Integer noGo;
   
	 //天数
    private  Integer days;
   
	 //次数
    private  Integer nums;
   
	 //是否连续,1:连续，2不连续
    private  Integer rowKey;
   
	 //触发方式 1 未归 2 未出 3 出行频率 4 出现频率-连续
    private  Integer way;
   
    
}
