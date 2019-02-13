package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;
import java.util.List;


import com.hotcomm.community.common.bean.db.person.HoleObjDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleDetailDM;
import com.hotcomm.community.common.bean.db.person.PushDM;
import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AddPersonHolePA extends CommunityParams implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3242717014622670104L;

	private Integer id;
	/**
	 * 布控对象类型  1群体 2个人
	 */
	private Integer holeType;
	
	/**
	 * 布控对象信息
	 */
	private String holePopulations;
	
	/**
	 * 布控规则信息
	 */
	private List<PersonHoleDetailDM> holeDetail;
	
	/**
	 * 布控主题
	 */
	private String holeTitle;
	
	/**
	 * 警报等级
	 */
	private Integer alarmLv;
	
	/**
	 * 创建人id
	 */
	private Integer createUser;
	
	/**
	 * 警报接受人
	 */
	private String push;
	
	private HoleObjDM obj;
	
	private List<PushDM> pushobj;

	/**
	 * 来源
	 */
	private Integer source;
}
