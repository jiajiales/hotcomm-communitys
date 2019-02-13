package com.hotcomm.community.common.bean.db.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarLabelRelationDM extends CommunityParams implements Serializable {

	private static final long serialVersionUID = 9195391462928341531L;

	//ID
    private  Integer id;
    
	 //车牌号
    private  String carNum;
   
	 //车辆标签ID
    private  Integer labelId;
    
    //标签ID列表(陌生车)
    private String labelIdList;
    
    //动态库名称
    private  String dynamicDbname;
}
