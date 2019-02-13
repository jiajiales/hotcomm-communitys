package com.hotcomm.community.common.bean.pa.device;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceInstallPA extends CommunityParams implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String dynamic_dbname;
	private Integer devid;// 设备id
	private String pictureList;// 图片url，分割
	private Double x = 0.0;
	private Double y = 0.0;
	private Integer moduleId;// 模块id
	private String devType;// 设备功能类型
	private String devTradeMark;// 设备品牌
	private String mac;
	private Integer buildingNum;// 楼栋编号
	private Integer unitNum;// 单元编号
	private Integer floorNum;// 楼层编号
	private String devAddress;// 设备地址
	private String useType;// 使用场景
	private String lat;// 纬度
	private String lon;// 经度
	private String installExplain;// 安装说明
	private Integer ownId;// 责任人id
	private Integer installUserid;// 安装人id
	private String videoList;// 视频id
}
