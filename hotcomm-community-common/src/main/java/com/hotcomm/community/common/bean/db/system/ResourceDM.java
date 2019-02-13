package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ResourceDM implements Serializable {

	private static final long serialVersionUID = 7365748550082460977L;

	private Integer resId;

	private String icon;

	private String path;

	private String key;

	private String name;

	private Integer type;

	private Integer status;

	private Integer pid;

	private Integer weight;

}
