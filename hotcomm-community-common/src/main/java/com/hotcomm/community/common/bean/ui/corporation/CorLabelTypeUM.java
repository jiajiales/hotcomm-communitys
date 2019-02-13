package com.hotcomm.community.common.bean.ui.corporation;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorLabelTypeUM implements Serializable {

	private static final long serialVersionUID = 6966245280937794185L;

//标签类型ID
   private  Integer id;
  
	 //标签类型名称
   private  String typeName;
  
	 //状态
   private  Integer state;
}
