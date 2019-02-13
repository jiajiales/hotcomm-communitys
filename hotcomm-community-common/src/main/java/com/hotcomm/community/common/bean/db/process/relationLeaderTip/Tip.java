/**
  * Copyright 2018 bejson.com 
  */
package com.hotcomm.community.common.bean.db.process.relationLeaderTip;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Tip {

    private String id;
    private String user_id;
    private String leader_name;
    private String content;
    

}