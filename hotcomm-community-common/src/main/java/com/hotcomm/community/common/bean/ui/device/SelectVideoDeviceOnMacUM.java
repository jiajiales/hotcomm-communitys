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
public class SelectVideoDeviceOnMacUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3993659465063547799L;
	private Integer id;// 设备id
	private String module;// 设备类型
	private Integer state;// 设备状态,0正常，1离线
	private Integer devType;// 设备类型，0人脸相机，1车牌相机，2球机，3半球机
	private Integer devLocation;// 监控位置，0入口，1出口，2路边，3其他
	private String devTradeMark;// 设备品牌
	private String userName;// 账号
	private String passWord;// 密码
	private String videoIp;// 摄像头ip
	private String nvrIp;// nvrip
	private String videoPort;// 摄像头端口
	private String port;// 端口
	private String nvrUserName;// nvr账号
	private String nvrPassWord;// nvr密码
	private String channel;// 通道号
	private String devNum;// 设备编号
	private String mac;//
	private Integer buildingNum;// 楼栋id
	private Integer unitNum;// 单元id
	private Integer floorNum;// 楼层id
	private String devAddress;// 设备详细地址描述
	private String code;// 设备完整详细地址
	private String lat;// 纬度
	private String lon;// 经度
	private Double x;//
	private Double y;//
	private String useType;// 设备应用场景;0车辆监控;1消防;2监控;3其他
	private String pictureList;// 图片json
	private List<String> pictureListMap;// 图片对象json
	private String videoList;// 视频json
	private List<SelectVideoDeviceOnMacUM> videoArrayList;// 关联视频信息
	private String installExplain;// 安装说明
	private Integer ownId;// 责任人id
	private String ownRealName;// 责任人姓名
	private String ownTelephone;// 责任人电话
	private Integer installUserid;// 安装人id
	private String installRealName;// 安装人姓名
	private String installTelephone;// 安装人电话
	private String installTime;// 安装时间

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
