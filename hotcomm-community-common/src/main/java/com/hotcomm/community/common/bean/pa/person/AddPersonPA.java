package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AddPersonPA extends CommunityParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2372789653176069518L;

	private Integer pId;
	/**
	 * 头像路径
	 */
	private String headImg;

	/**
	 * 门禁卡号
	 */
	private String entranceCardno;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 证件类型 1身份证号, 2港澳通行证,3台胞证, 4护照
	 */
	private Integer cardType;
	/**
	 * 证件号码
	 */
	private String  cardNo;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别   1男 2 女
	 */
	private Integer sex;

	/**
	 * 是否本地户口   0否 1 是
	 */
	private Integer koseki;

	/**
	 * 户籍地址
	 */
	private String accountAddress;

	/**
	 * 标签
	 */
	private Integer  lableId;

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
	 * 数据来源: 1:xxx门禁  2xxx摄像头
	 */
	private Integer dataSource;
	/**
	 * 是否本区居民 1 是 2否
	 */
	private Integer dateType;

	/**
	 * 创建人id
	 */
	private Integer createUser;

	/**
	 * 人脸编号
	 */
	private String faceNo;
	/**
	 * 居民编号
	 */
	private String pNo;

	/**
	 * 第三方身份证id
	 */
	private String duoduCardId;

	public AddPersonPA(String headImg, String entranceCardno, String name, String phone, Integer cardType, String cardNo, Integer age, Integer sex, Integer koseki, String accountAddress, Integer lableId, String nationality, String people, String degree, String politicalOutlook, String maritalStatus, Integer dateType, Integer createUser) {
		this.headImg = headImg;
		this.entranceCardno = entranceCardno;
		this.name = name;
		this.phone = phone;
		this.cardType = cardType;
		this.cardNo = cardNo;
		this.age = age;
		this.sex = sex;
		this.koseki = koseki;
		this.accountAddress = accountAddress;
		this.lableId = lableId;
		this.nationality = nationality;
		this.people = people;
		this.degree = degree;
		this.politicalOutlook = politicalOutlook;
		this.maritalStatus = maritalStatus;
		this.dateType = dateType;
		this.createUser = createUser;
	}
}
