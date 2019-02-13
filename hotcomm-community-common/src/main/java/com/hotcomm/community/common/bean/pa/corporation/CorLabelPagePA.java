package com.hotcomm.community.common.bean.pa.corporation;

import java.io.Serializable;
import com.hotcomm.framework.comm.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorLabelPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = 1978388479216976013L;
	
	private String labelName;
	private Integer labelTypeId;
    private String startTime;
    private String endTime;

}
