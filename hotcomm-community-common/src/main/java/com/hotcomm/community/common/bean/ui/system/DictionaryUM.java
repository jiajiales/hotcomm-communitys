package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DictionaryUM implements Serializable {

	private static final long serialVersionUID = -7579522708520683271L;
	
	private Integer id;

	private String keyName;

	private String keyValue;

}
