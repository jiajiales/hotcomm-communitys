/**
  * Copyright 2018 bejson.com 
  */
package com.hotcomm.community.common.bean.db.process.relationView.event;

/**
 * Auto-generated: 2018-12-15 16:41:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class EventViewJsonRootBean {

    private ClosedView closedView;
    private PendingView pendingView;
    private ProcessedView processedView;
    public void setClosedView(ClosedView closedView) {
         this.closedView = closedView;
     }
     public ClosedView getClosedView() {
         return closedView;
     }

    public void setPendingView(PendingView pendingView) {
         this.pendingView = pendingView;
     }
     public PendingView getPendingView() {
         return pendingView;
     }

    public void setProcessedView(ProcessedView processedView) {
         this.processedView = processedView;
     }
     public ProcessedView getProcessedView() {
         return processedView;
     }

}