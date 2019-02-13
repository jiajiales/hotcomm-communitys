package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceOnMacUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1595118190664103121L;
	private Integer id;// 设备id
	private Integer moduleId;// 设备类型
	private String module;// 设备类型
	private Integer state;// 设备状态
	private String devType;// 设备功能类型
	private String devTradeMark;// 设备品牌
	private String devNum;// 设备编号
	private String mac;//
	private Integer buildingNum;// 楼栋id
	private Integer unitNum;// 楼栋id
	private Integer floorNum;// 楼层id
	private String devAddress;// 设备详细地址
	private String code;// 设备地址
	private String lat;// 纬度
	private String lon;// 经度
	private String installTime;// 安装时间
	private double x;//
	private double y;//
	private Integer useType;// 应用场景
	private String pictureList;// 图片json
	private List<String> pictureListMap;// 图片对象json
	private String installExplain;// 安装说明
	private Integer ownId;// 责任人id
	private String ownRealName;// 责任人姓名
	private String ownTelephone;// 责任人电话
	private Integer installUserid;// 安装人id
	private String installRealName;// 安装人姓名
	private String installTelephone;// 安装人电话
	private String videoList;
	private List<SelectVideoDeviceOnMacUM> videoArrayList;

	public void setPictureList(String pictureList) {
		Gson gson = new Gson();
		Device_GzUM device_GzPA = gson.fromJson(pictureList, Device_GzUM.class);

		setPictureListMap(device_GzPA.getPicture());
		this.pictureList = pictureList;
	}

	public void setPictureListMap(List<String> pictureListMap) {
		this.pictureListMap = pictureListMap;
	}

}
