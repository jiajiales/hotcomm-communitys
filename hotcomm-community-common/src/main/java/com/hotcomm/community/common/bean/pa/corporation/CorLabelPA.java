package com.hotcomm.community.common.bean.pa.corporation;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorLabelPA extends PageParams implements Serializable {
	
	private static final long serialVersionUID = -6521838206302859797L;
	
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
    
}
