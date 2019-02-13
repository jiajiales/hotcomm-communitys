/**
  * Copyright 2018 bejson.com 
  */
package com.hotcomm.community.common.bean.db.process.relationView.worder;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Auto-generated: 2018-11-27 20:20:0
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderHandleProcessedViewDM {

    private List<WorderMp3DM> mp3;
    private List<WorderImageDM> image;
    private List<WorderVideoDM> video;

}