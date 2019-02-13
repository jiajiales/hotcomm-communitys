package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarLabelUM implements Serializable {

	private static final long serialVersionUID = 3571443790981557431L;
	
	private Integer id;
	private String type;
	private Integer labelTypeId;
	private String labelName;
	private String labelDescribe;
	private String labelSource;
	private Integer alarmLeve;
	private String createTime;
	private String createUser;
	private Integer state;
	private Boolean stateBl;
}
