package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AreaPA implements Serializable {

	private static final long serialVersionUID = 2549816958034940837L;

	private Integer level;

	private Integer parentId;

}
