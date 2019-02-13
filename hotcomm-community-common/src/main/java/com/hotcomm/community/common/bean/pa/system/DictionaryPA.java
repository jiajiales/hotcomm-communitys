package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DictionaryPA implements Serializable {

	private static final long serialVersionUID = -5130088660648633237L;

	private String type;

	private String keyName;

}
