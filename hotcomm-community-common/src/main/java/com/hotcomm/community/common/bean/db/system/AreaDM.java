package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AreaDM implements Serializable {

	private static final long serialVersionUID = 8373344109819330739L;

	private Integer id;

	private Integer parentId;

	private Integer level;

	private String areaCode;

	private String zipCode;

	private String cityCode;

	private String name;

	private String shortName;

	private String mergerName;

	private String pinyin;

	private Double lng;

	private Double lat;

}
