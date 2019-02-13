package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ResourceUM implements Serializable {

	private static final long serialVersionUID = 3027295355077311002L;

	private Integer resId;

	private String icon;

	private String path;

	private Integer type;

	private String name;

	private Integer weight;

	private Integer status;

	private Integer pid;

	private String key;

	private String pname;

}
