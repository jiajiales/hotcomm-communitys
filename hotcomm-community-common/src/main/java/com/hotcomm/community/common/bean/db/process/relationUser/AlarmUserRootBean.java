package com.hotcomm.community.common.bean.db.process.relationUser;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmUserRootBean {

    private List<User> user;

}