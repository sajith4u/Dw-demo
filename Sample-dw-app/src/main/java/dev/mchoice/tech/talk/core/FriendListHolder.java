package dev.mchoice.tech.talk.core;

import java.util.ArrayList;
import java.util.List;


public class FriendListHolder {
    public List<FriendBean> friendBeanList;

    public List<FriendBean> getFriendList() {
        friendBeanList = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            FriendBean friendBean = new FriendBean();
            friendBean.setName("User" + x);
            friendBean.setStatus("ACTIVE");
            friendBeanList.add(friendBean);
        }
        return friendBeanList;
    }
}
