package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarRegUM implements Serializable {

	private static final long serialVersionUID = -4714306282981060645L;
	private Integer id;
	private String frontPhoto;//正面照
	private String num;//车牌号
	private String color;
	private String brand;
	private String model;
	private Integer personId;
	private String modelType;
	private String carType;
	private String labelName;
	private Integer labelId;
	private String personPhone;
	private String personName;
	private Integer alarmCount;
	private String createTime;
	private String createUser;
	private Integer labelRelationId;
}
