package com.hotcomm.community.common.bean.db.car;

import java.io.Serializable;
import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmRecordsDM extends CommunityParams  implements Serializable {

	private static final long serialVersionUID = 7658351613635122390L;

	 //ID
    private  Integer id;
   
	 //车牌号
    private  String num;
   
	 //车辆类型（0:小区车辆，1：单位车辆，2:外来车辆,3：陌生车）
    private  Integer type;
   
	 //预警规则ID
    private  Integer ruleId;
   
    //地址
    private  String address;
    
    //照片
    private  String photo;
    
    //mac地址
    private  String mac;
    
	 //创建时间
    private  String createTime;
    
    //动态库名称
    private  String dynamicDbname;
}
