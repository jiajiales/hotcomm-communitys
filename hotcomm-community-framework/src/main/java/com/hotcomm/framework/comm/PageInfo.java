package com.hotcomm.framework.comm;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PageInfo<T>  {

	private Long total;
	private List<T> data;
	private Integer pageSize = 20;
	private Integer totalPage = 0;
	private Integer pageIndex = 1;
	
	public PageInfo(Long total, List<T> data) {
		super();
		this.total = total;
		this.data = data;
		if(total!=null) this.totalPage = total%this.pageSize==0?total.intValue()/this.pageSize:total.intValue()/this.pageSize+1;
	}

	public PageInfo(Long total, List<T> data, Integer pageSize, Integer pageIndex) {
		super();
		this.total = total;
		this.data = data;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		if(total!=null) this.totalPage = total%this.pageSize==0?total.intValue()/this.pageSize:total.intValue()/this.pageSize+1;
	}
	
}
