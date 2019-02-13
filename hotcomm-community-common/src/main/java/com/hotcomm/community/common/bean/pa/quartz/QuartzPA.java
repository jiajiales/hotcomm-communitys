package com.hotcomm.community.common.bean.pa.quartz;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class QuartzPA extends PageParams implements Serializable {

	private static final long serialVersionUID = -3906983047787018699L;
	

	private Integer startIndex;

	private Integer endIndex;
}
