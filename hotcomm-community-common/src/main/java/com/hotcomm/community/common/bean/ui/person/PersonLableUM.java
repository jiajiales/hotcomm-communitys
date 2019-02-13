package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonLableUM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7586446909924021674L;
	
	private Integer id;			//标签编号
	private String typeCode;	//标签类型编号  例如关爱人口,服务人口,危险人群,黑名单
	private Integer typeId;		//类型id
	private String lableTypeName;//标签类型名称
	private String lableName;	//标签名称
	private String lableDesc;	//标签注释
	private String createTime;	//创建时间
	private String userName;	//创建人
	private Integer sourceType; //来源类型 0系统 1 自定义
}	
