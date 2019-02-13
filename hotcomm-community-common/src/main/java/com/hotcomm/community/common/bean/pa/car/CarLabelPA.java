package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarLabelPA extends PageParams implements Serializable {

	private static final long serialVersionUID = 7445536679203630466L;

	 //ID
    private  Integer id;
   
	 //标签类型ID
    private  Integer carLabelId;
   
	 //标签类型名称
    private  String name;
   
    //标签描述
    private  String  labelDescribe;
   
	 //来源（0：系统，1：自定义）
    private  Integer labelSource;
   
	 //警报级别
    private  Integer alarmLeve;
   
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
