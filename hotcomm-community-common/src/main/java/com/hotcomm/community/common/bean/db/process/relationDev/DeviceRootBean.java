package com.hotcomm.community.common.bean.db.process.relationDev;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceRootBean {

    private List<Device> device;
    public void setDevice(List<Device> device) {
         this.device = device;
     }
     public List<Device> getDevice() {
         return device;
     }

}