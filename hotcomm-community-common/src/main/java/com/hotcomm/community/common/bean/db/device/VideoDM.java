package com.hotcomm.community.common.bean.db.device;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class VideoDM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3807239716716094960L;
	private List<String> video;
}
