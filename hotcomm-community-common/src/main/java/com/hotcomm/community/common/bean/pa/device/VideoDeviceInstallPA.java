package com.hotcomm.community.common.bean.pa.device;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class VideoDeviceInstallPA extends CommunityParams implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String dynamic_dbname;
	private Integer devid;// 设备id
	private String pictureList;// 图片url，分割
	private String videoList;// 视频mac，分割
	private Double x = 0.0;
	private Double y = 0.0;
	private String mac;
	private String newMac;
	private Integer devType;// 设备类型，0人脸相机，1车牌相机，2球机，3半球机
	private Integer devLocation;// 监控位置，0入口，1出口，2路边，3其他
	private String devTradeMark;// 设备品牌
	private String userName;// 账号
	private String passWord;// 密码
	private String videoIp;// 摄像头ip
	private String nvrIp;// nvrip
	private String videoPort;
	private String nvrUserName;// nvr账号
	private String nvrPassWord;// nvr密码
	private String port;// 端口
	private String channel;// 通道号
	private String lat;// 纬度
	private String lon;// 经度
	private Integer ownId;// 责任人id
	private Integer buildingNum;// 楼栋编号
	private Integer unitNum;// 单元id
	private Integer floorNum;// 楼层编号
	private String devAddress;// 设备地址
	private Integer useType;// 设备应用场景;0车辆监控;1消防;2监控;3其他
	private String installExplain;// 安装说明
	private Integer installUserid;// 安装人id
}
