package com.hotcomm.community.common.bean.ui.person;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceMacByRecord implements Serializable {

	private static final long serialVersionUID = 6123522445440894309L;

	private String time;
	private String mac;
}
