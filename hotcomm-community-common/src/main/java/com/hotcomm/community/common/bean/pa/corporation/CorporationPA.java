package com.hotcomm.community.common.bean.pa.corporation;

import java.io.Serializable;
import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorporationPA extends PageParams implements Serializable {


	private static final long serialVersionUID = -7423404707086281664L;
	
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
    
    //楼栋
    private  Integer building;
    
    //单元
    private Integer  unit;
    
    //层
    private  Integer layer;
    
    //房间号
    private  Integer roomNum;
   
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

    private String peronName;
    
    private Integer labelTypeId;
    
	public CorporationPA(String corName, String address, Integer corTypeId, String scope,
			Integer building, Integer unit, Integer layer, Integer roomNum, double longitude, double latitude,
			Integer labelId, Integer devCount, String busLicense,  String createUser) {
		super();
		this.corName = corName;
		this.address = address;
		this.corTypeId = corTypeId;
		this.scope = scope;
		this.longitude = longitude;
		this.latitude = latitude;
		this.labelId = labelId;
		this.devCount = devCount;
		this.busLicense = busLicense;
		this.createUser = createUser;
	}
}

