package com.hotcomm.framework.comm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PageParams extends CommunityParams {

	protected Integer pageIndex=0; // 分页-页码

	protected Integer pageSize=20; // 分页-页数
	
	protected Integer skip; //起始页	
	
	public Integer getSkip() {
		return this.skip = pageIndex <=1?0:this.pageIndex*this.pageSize;
	}
	
	public PageParams(Integer pageIndex, Integer pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return this.pageIndex == null? 0:this.pageIndex;
	}
	
	
	public Integer getPageSize() {
		return this.pageSize == null ? 20:this.pageSize;
	}
}
