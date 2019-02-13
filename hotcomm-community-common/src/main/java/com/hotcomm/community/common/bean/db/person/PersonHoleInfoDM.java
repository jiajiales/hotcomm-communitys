package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper =false)
@NoArgsConstructor
public class PersonHoleInfoDM implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1951091977307192972L;

	/**
	 * 布控id
	 */
	private Integer id;
	
	/**
	 * 布控主题
	 */
	private String hole_title;
	
	/**
	 * 1群体 2个人
	 */
	private Integer hole_type;
	
	/**
	 * 具体布控对象
	 */
	private String hole_populations;
	
	/**
	 * 警报等级
	 */
	private Integer alarm_level;
	
	/**
	 * 0未开启 1开启 2已结束
	 */
	private Integer hole_status;
	
	/**
	 * 该对象最后一次通行时间
	 */
	private String record_population_time;
	
	/**
	 * 创建时间
	 */
	private String create_time;
	
	/**
	 * 创建人id
	 */
	private Integer create_user;
	
	/**
	 * 修改时间
	 */
	private String update_time;
	
	/**
	 * 修改人id
	 */
	private Integer update_user;
	
	/**
	 * 报警推送情况
	 */
	private String push;
}
