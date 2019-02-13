package com.hotcomm.community.common.bean.db.corporation;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorporationDM extends CommunityParams implements Serializable {
	
	private static final long serialVersionUID = 8332853975644901570L;

	 //单位id
    private  Integer id;
   
	 //单位名称
    private  String corName;
    
    //单位楼层信息
    private String floor;
    
    //单位地址
    private String address;
   
	 //单位类型（字典值）
    private  Integer corTypeId;
   
	 //经营范围
    private  String scope;  
   
	 //经度
    private  double longitude;
   
	 //纬度
    private  double latitude;
   
	 //标签id
    private  Integer labelId;
    
    //关联设别数
    private Integer devCount;
   
	 //营业执照
    private  String busLicense;  
   
	 //创建时间
    private  String createTime;
   
	 //创建人
    private  String createUser;
   
	 //更新时间
    private  String updateTime;
   
	 //更改人
    private  String updateUser;
    
    //动态库名称
    private  String dynamicDbname;

}
