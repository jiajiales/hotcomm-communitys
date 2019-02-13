package com.hotcomm.community.common.bean.pa.corporation;

import java.io.Serializable;
import java.util.List;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorPerRelationListPA extends CommunityParams implements Serializable {
	
	private static final long serialVersionUID = -2162600171160147033L;
	
	private  Integer id;
	private Integer corId;
	private String  personIdStr;
	private String personName;
	private List<Integer> personIdList;
	
	public CorPerRelationListPA(Integer corId, String  personIdStr) {
		super();
		this.corId = corId;
		this.personIdStr = personIdStr;
	}

}
