package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonRoomPA extends CommunityParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1350855346937650987L;

	private Integer pId;
	private Integer roomId;
	private Integer rentOrSale;
	private Integer reason;
	private String remark;

}
