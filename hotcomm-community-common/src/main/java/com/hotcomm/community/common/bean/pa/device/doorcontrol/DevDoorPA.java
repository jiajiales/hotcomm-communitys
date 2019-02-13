package com.hotcomm.community.common.bean.pa.device.doorcontrol;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DevDoorPA extends CommunityParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mac;
	private String devAddress;            //设备安装地址
	private String lat;                   //维度
	private String lon;                   //经度
	private Double x;                     //图片水平位置
	private Double y;                     //图片垂直位置
	private Integer useType;              //设备应用场景 0通行 1消防 2监控 3其他
	private String pictureList;           //设备图片地址 picture
	private String installExplain;        //安装说明
	private Integer ownId;                //责任人
	private Integer installUserid;        //安装人
	private String videoList;             //关联视频
	private Integer buildingNum;          //楼栋编号
	private Integer unitNum;              //单元编号
	private Integer floorNum;             //楼层编号
	private Integer installState;
	
}
