package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonLablePagePA extends PageParams implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5277508799507170094L;
	/**
	 * 标签类型 0普通居民  1 关爱人口  2 危险人群 3 服务人群 4黑名单
	 */
	private Integer typeCode;
	
	/**
	 * 来源 0系统 1 自定义
	 */
	private Integer sourceType;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 模糊搜索  姓名
	 */
	private String userName;
}
