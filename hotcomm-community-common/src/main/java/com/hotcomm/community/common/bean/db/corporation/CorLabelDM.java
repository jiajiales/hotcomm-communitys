package com.hotcomm.community.common.bean.db.corporation;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorLabelDM extends CommunityParams implements Serializable {
	
	private static final long serialVersionUID = 4283334951064654013L;

	private  Integer id;
    
	 //标签类型id
     private  Integer typeId;
     
	 //标签类型名称
     private  String typeName;
    
	 //标签名
     private  String labelName;
    
	 //标签描述
     private  String describes;
    
	 //来源
     private  Integer labelSource;
    
	 //创建时间
     private  String createTime;
    
	 //创建人
     private  String createUser;
    
	 //更新时间
     private  String updateTime;
    
	 //更改人
     private  String updateUser;
    
	 //是否启用
     private  Integer state;
     
     //动态库名称
     private  String dynamicDbname;
}
