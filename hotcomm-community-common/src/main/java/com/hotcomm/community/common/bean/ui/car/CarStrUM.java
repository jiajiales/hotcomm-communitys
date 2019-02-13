package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarStrUM implements Serializable {

	private static final long serialVersionUID = 4191750756613592396L;

	private Integer id;
	private String carImg;
	private String carNumImg;
	private String num;
	private String color;
	private String brand;
	private String model;
	private String modelType;
	private String state;
	private Integer enterCount;
	private Integer alarmCount;
	private String createTime;
	private ArrayList<String> labelNameList;
	private ArrayList<Integer> labelIdList;
//	private String uuid;
}
