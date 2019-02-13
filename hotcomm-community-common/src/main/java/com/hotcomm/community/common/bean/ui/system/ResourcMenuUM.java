package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ResourcMenuUM implements Serializable {

	private static final long serialVersionUID = -1352325192265532602L;

	private Integer resId;

	private String menuName;

}
