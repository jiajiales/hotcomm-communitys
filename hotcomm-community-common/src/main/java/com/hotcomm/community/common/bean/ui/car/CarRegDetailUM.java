package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarRegDetailUM implements Serializable{
	
	private static final long serialVersionUID = 2422485330598969068L;
	
		private Integer id;
		//侧面照
		private String sidePhoto;
		//尾部照
		private String tailPhoto;
		//正面照
		private String frontPhoto;
		//其他照
		private String elsePhoto;
		private String num;//车牌号
		private String shortNum;//车牌号尾号：S12345，B12345
		private String area;//车牌号
		private String color;
		private String brand;
		private String model;
		private String modelType;
		private String carType;
		private Integer carTypeId;
		
		private String labelName;
		private Integer carTypeCode;
		private String labelTypeName;
		private Integer roomId;
		private String roomAdress;
		
		private Integer companyId;
		private String companyName;
		private String companyAddress;
		
		private String personPhone;
		private String personName;
		
		private Integer alarmCount;
//		private String uuid;
		private Integer personId;
		private Integer labelId;
		private Integer labelRelationId;

}
