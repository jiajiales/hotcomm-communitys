package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class VideoUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7732199645241721730L;
	private List<String> video;
}
