package com.hotcomm.community.common.bean.pa.process.event;


import java.io.Serializable;
import java.util.Map;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EventNewPA extends CommunityParams implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 9074245659035071354L;
	
	//事件类型 (1.设备 2.户政 3.消防 4.治安 5.刑事 6.交通 7.生产安全 8.其他)
	private String sourceType;
	
	//事件标题
	private String eventTitle;
	
	//详细说明
	private String eventDesc;
	
	//事件发生时间
	private String eventHTime;
	
	//事件位置--经度
	private String addressLong;
	
	//事件位置--纬度
	private String addressLat;
	
	//要求处理时间 (1:立即 2:1小时 3:2小时 参考字典表或者枚举 与紧急程度做关联)
	private String timeConfine;
	
	//处理人
	private String handleUserId;
	
	//相关图片添加
	private String sourceImage;
	private String sourceMp3;
	private String sourceVideo;
	private String videoRelation;
	private String addressStr;
	private Integer eventSourceFrom;
	private Integer userid;
	private Map<String, Object> map;
	
	private String eventView;
	
	private Integer eventId;
	
	private String sourceId;

}
