package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmRulePA extends PageParams implements Serializable {


	private static final long serialVersionUID = 2461351162254600530L;
	
	//预警规则ID
    private  Integer ruleId;
   
	 //规则名称
    private  String name;
   
	 //预警级别
    private  Integer alarmLeve;
   
	 //接受人员
    private  String  dealUsers;
    
	 //处理人员
    private  String notifyUsers;
   
	 //规则记录
    private  String rule;
   
	 //创建时间
    private  String createTime;
   
	 //创建人
    private  String createUser;
   
	 //更新时间
    private  String updateTime;
   
	 //更改人
    private  String updateUser;
   
	 //是否启用
    private  Integer state;
    
    private  String dynamicDbname;

}
