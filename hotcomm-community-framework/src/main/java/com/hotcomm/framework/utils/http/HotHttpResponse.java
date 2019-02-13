package com.hotcomm.framework.utils.http;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class HotHttpResponse {
	
	private boolean success;
	private String returnJson;
	
}
