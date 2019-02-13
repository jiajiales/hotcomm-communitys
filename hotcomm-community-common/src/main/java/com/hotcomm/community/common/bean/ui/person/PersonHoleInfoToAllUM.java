package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;
import java.util.List;

import com.hotcomm.community.common.bean.db.person.HoleObjDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleDetailDM;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonHoleInfoToAllUM implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -402902476566921611L;

	/**
	 * 布控对象信息
	 */
	private HoleObjDM obj;
	
	/**
	 * 标签名称
	 */
	private String lableName;
	
	/**
	 *布控主题 
	 */
	private String holeTitle;
	
	/**
	 * 布控警报等级
	 */
	private Integer alarmLv;
	
	/**
	 * 布控规则信息
	 */
	private List<PersonHoleDetailDM> detail;
	
	/**
	 * 报警推送情况
	 */
	private String push;
}
