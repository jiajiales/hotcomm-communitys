package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NoticePagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = -1252911482177008340L;

    private String startTime;
    private String endTime;

}
