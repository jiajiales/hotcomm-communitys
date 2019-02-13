package com.hotcomm.community.common.bean.ui.corporation;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorPerRelationUM implements Serializable {
	
	private static final long serialVersionUID = 6644173615631901512L;
	private Integer id;
	private Integer personId;
	private  String headImg;
	private  String personName;
	private  String phone;
	private  String cardNo;
	private  String createTime;
	private  String  sex;
}
