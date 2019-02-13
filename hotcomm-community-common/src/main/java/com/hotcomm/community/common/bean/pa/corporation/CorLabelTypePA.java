package com.hotcomm.community.common.bean.pa.corporation;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorLabelTypePA extends CommunityParams implements Serializable {
	
	private static final long serialVersionUID = 6224831383876344191L;

	 //标签类型ID
    private  Integer id;
   
	 //标签类型名称
    private  String typeName;
   
	 //状态
    private  Integer state;
    
}
