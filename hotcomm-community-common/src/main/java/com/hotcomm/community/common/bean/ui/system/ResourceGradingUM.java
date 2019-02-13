package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceGradingUM implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = -7663892138962257321L;

	private Integer resId;

	private String icon;

	private String path;

	private Integer type;

	private String name;

	private Integer weight;

	private Integer status;

	private Integer pid;

	private String key;

	private List<ResourceGradingUM> childrens;

	public ResourceGradingUM() {
		childrens = new ArrayList<>();
	}

	@Override
	public int compareTo(Object o) {
		ResourceGradingUM resource = (ResourceGradingUM) o;
		return resource.getWeight().compareTo(weight);	// 根据权重降序排序
	}

}
