package com.hotcomm.framework.comm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FileUploadResponse {

	private Integer code;

	private String backPath;

	private Integer freeSpace;

	private Integer totalSpace;

}
