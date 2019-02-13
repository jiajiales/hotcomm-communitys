package com.hotcomm.framework.utils.http;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class HotHttpEntity {
	
	private String key;
	private EntityEnum type;
	private Object value;
	
	public HotHttpEntity(String key, EntityEnum type, Object value) {
		super();
		this.key = key;
		this.type = type;
		this.value = value;
	}
	
	
}
