package com.hotcomm.community.common.bean.db.car;

import java.io.Serializable;
import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmRuleDM  extends CommunityParams implements Serializable {

	private static final long serialVersionUID = -6466151067180496670L;

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
    
    //动态库名称
    private  String dynamicDbname;
}
