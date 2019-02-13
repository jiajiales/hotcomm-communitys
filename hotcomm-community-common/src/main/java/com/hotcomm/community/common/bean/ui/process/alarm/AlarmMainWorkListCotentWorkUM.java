package com.hotcomm.community.common.bean.ui.process.alarm;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmMainWorkListCotentWorkUM implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4348837902883155395L;

	private String workNo;
	private String state;
	private String createTime;
	private String workTitle;
	private String orderLevel;
	private String timeConfine;
	private String handlerInfo;
	private String video;
	private String img;
	private List<AlarmMainWorkListWorkUM> content;

}
