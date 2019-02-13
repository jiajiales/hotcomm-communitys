package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class HoleObjDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2248332102135669120L;

	private Integer p_id;

	private List<Integer> age;

	private Integer sex;

	private String people;

	private Integer lable_id;

	private String nationality;
}
