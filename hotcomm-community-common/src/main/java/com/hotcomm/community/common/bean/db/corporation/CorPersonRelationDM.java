package com.hotcomm.community.common.bean.db.corporation;

import java.io.Serializable;
import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorPersonRelationDM extends CommunityParams implements Serializable {
	
	private static final long serialVersionUID = 1651508957314016214L;

	 //关联Id
    private  Integer id;
   
	 //单位id
    private  Integer corporationId;
   
	 //人口id
    private  Integer personId;
     
    private String dynamicDbname;
}
