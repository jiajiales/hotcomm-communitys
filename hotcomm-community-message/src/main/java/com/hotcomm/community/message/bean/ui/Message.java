package com.hotcomm.community.message.bean.ui;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8070911410091910993L;

	// 小区ID
	private String communityId;
	// 发送位置
	private String source;// 0:大数据，1：后台
	// 类别
	private String category;// {态势：C01，感知：C02}
	// 编号指向具体的方法，保留字段
	private String code;// 编号，保留字段
	// 具体推送的信息
	private Object data;

}
