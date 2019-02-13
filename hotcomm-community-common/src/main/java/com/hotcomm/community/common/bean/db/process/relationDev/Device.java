package com.hotcomm.community.common.bean.db.process.relationDev;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Device {

    private Integer id;
    private Integer moduleid;

}