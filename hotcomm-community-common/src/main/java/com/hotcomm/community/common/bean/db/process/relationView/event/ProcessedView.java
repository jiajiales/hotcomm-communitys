/**
  * Copyright 2018 bejson.com 
  */
package com.hotcomm.community.common.bean.db.process.relationView.event;
import java.util.List;

/**
 * Auto-generated: 2018-12-15 16:41:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ProcessedView {

    private List<Img> img;
    private List<Mp3> mp3;
    private List<Video> video;
    public void setImg(List<Img> img) {
         this.img = img;
     }
     public List<Img> getImg() {
         return img;
     }

    public void setMp3(List<Mp3> mp3) {
         this.mp3 = mp3;
     }
     public List<Mp3> getMp3() {
         return mp3;
     }

    public void setVideo(List<Video> video) {
         this.video = video;
     }
     public List<Video> getVideo() {
         return video;
     }

}