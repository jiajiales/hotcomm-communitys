package com.hotcomm.community.common.bean.db.corporation;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorLabelTypeDM extends CommunityParams implements Serializable {
	
	private static final long serialVersionUID = 2241766235228672086L;

	 //标签类型ID
    private  Integer id;
   
	 //标签类型名称
    private  String typeName;
   
	 //状态
    private  Integer state;
    
    //动态库名称
    private  String dynamicDbname;
}
