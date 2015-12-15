package dev.mchoice.tech.talk.core;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by sajith on 12/9/15.
 */
public class FriendBean extends ResourceSupport{
    private String name;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FriendBean{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
