package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmRuleUM implements Serializable {

	private static final long serialVersionUID = 2322169785030677100L;
	
	private Integer ruleId;
	private String ruleName;
	private String rule;
	private Integer alarmLeve;
	private String notifyUsers;
	private String dealUsers;
	private List<String> dealUserNameList;
	private List<String> notifyUserNameList;
	private Integer state ;
	private String createUser;
	private String createTime;
}
