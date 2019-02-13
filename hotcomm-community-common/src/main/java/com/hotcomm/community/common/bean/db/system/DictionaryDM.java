package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DictionaryDM implements Serializable {

	private static final long serialVersionUID = -7429060501444630852L;

	private Integer id; // 编号

	private String keyName; // 键

	private String keyValue; // 值

}
