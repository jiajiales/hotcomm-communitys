package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPassRecordsUM implements Serializable {

	private static final long serialVersionUID = -764972888704097006L;
	
	private Integer id;
	private String carImgPath;
	private String carNumImgPath;
	private  String firstImgPath;
	private String num;
	private String carType;
	private Integer carTypeId;
	private String adress;
	private String color;
	private String model;
	private String brand;
	private String modelType;
	private String videoCode;
	private String passType;
	private String createTime;
//	private String uuid;
	private ArrayList<String> labelNameList;
	private ArrayList<Integer> labelIdList;
	private Integer lat;
	private Integer lon;
}
