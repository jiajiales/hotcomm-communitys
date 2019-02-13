package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarStrPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = -464850851398346124L;

    private  Integer labelId;
    private String num;
    private String color;
    private String brand;
    private Integer state;
    private String   modelType;
}
