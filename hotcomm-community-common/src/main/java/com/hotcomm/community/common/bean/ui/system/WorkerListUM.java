package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorkerListUM implements Serializable {

	private static final long serialVersionUID = 1516060927447616771L;

	private Integer workerId;

	private String username;

}
