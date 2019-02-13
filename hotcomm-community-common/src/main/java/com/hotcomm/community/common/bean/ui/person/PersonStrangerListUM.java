package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonStrangerListUM  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7876525449914697726L;
	private Integer id;
	private String faceNo;
	private String headimg;
	private Integer frequency;
	private String firstTime;
	private Integer age;
	private Integer sex;
	private String name;
	private String cardNo;
	private String phone;
	private String address;
}
