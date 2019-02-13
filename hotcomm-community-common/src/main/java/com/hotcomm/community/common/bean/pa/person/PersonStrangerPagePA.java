package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PersonStrangerPagePA extends PageParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1444808589780161854L;
	private Integer sex;	//性别 1男2 女
	private Integer beginNum;	//开始次数
	private Integer endNum;		//结束次数
	private Integer dataSource;	//数据来源 1 门禁 2 摄像头
	private String content;		//搜索内容
	private String startTime; //开始时间
	private String endTime;     //结束时间
}
