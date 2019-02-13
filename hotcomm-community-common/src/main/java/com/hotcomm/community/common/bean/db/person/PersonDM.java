package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonDM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4332001995047156447L;

	/**
	 * 编号
	 */
	private int pId;
	
	/**
	 * 头像地址 人脸图片
	 */
	private String headimg;


	/**
	 * 用户姓名
	 */
	private String name;

	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 性别 1 男 2 女
	 */
	private int sex;

	/**
	 * 标签id
	 */
	private Integer lableId;
	
	/**
	 * 标签类型
	 */
	private String lableType;
	/**
	 * 人口标签 
	 */
	private String lableName;
	
	/**
	 * 联系手机
	 */
	private String phone;

	/**
	 * 证件类型  1身份证号, 2港澳通行证,3台胞证, 4护照，默认1
	 */
	private Integer cardType;
	
	/**
	 * 证件号码 身份证或者证件照
	 */
	private String cardNo;

	/**
	 * 门禁卡号
	 */
	private String entranceCardno;

	/**
	 * 登记时间
	 */
	private String createTime;
	
	/**
	 * 报警次数
	 */
	private int alarmNums;
	
	/**
	 * 户籍 0 本地 1 外地
	 */
	private int koseki;

	/**
	 * 户口地址
	 */
	private String accountAddress;
	
	/**
	 * 出生年月日
	 */
	private String birthday;

	/**
	 * 国籍
	 */
	private String nationality;

	/**
	 * 民族
	 */
	private String people;

	/**
	 * 学历
	 */
	private String degree;

	/**
	 * 政治面貌
	 */
	private String politicalOutlook;

	/**
	 * 婚姻状态
	 */
	private String maritalStatus;


	/**
	 * 登入人员
	 */
	private String createUser;

	/**
	 * 最新修改时间
	 */
	private String updateTime;

	/**
	 * 最新修改人员
	 */
	private String updateUser;

	/**
	 * 数据类型：是否本小区用户,1:是,2:不是
	 */
	private int dataType;

	/**
	 * 数据来源   1:xxx门禁  2xxx摄像头
	 */
	private Integer dataSource;
	
	/**
	 * 是否删除,0:否  1:是
	 */
	private Integer isdelete;
	
	/**
	 * 人脸编号
	 */
	private String faceNo;
	/**
	 * 多度身份证id
	 */
	private Integer duoduCardId;
	
	/**
	 * 多度用户id
	 */
	private String pNo;
}
