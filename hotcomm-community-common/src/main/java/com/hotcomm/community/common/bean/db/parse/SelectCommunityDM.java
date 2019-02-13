package com.hotcomm.community.common.bean.db.parse;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectCommunityDM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5281588017095842160L;
	private Integer cId;
	private String dataBaseNo;
}
