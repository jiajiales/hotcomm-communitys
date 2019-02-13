package com.hotcomm.community.common.bean.pa.corporation;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorporationPagePA extends PageParams implements Serializable {
	
	private static final long serialVersionUID = -4942016070477058953L;
	
    private  Integer corTypeId;
    private String startTime;
    private String endTime;
    private Integer  labelId;
    private String corName;
    private Integer typeId;

}
