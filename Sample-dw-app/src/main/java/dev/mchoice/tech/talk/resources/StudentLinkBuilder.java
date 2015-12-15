package dev.mchoice.tech.talk.resources;

import dev.mchoice.tech.talk.core.FriendBean;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;


public class StudentLinkBuilder {
    String baseUrl;

    public StudentLinkBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Link> createLinks(DomainTypes domainTypes, Object object) {
        List<Link> links = new ArrayList<>();
        switch (domainTypes) {
            case STUDENT:
                Link updateLink = new Link(baseUrl + "/student/update", "update");
                links.add(updateLink);
                break;
            case LOGIN:
                FriendBean friendBean;
                if (object instanceof FriendBean) {
                    friendBean = (FriendBean) object;
                    Link getFriendList = new Link(baseUrl + "/student/search/name?" + friendBean.getName(), "search");
                    links.add(getFriendList);
                }
                break;
            case BOOK:
                Link deleteLink = new Link(baseUrl + "/book/delete", "delete");
                Link getDetails = new Link(baseUrl + "/book/details", "getDetails");
                Link updateBook = new Link(baseUrl + "/book/update", "update");
                links.add(deleteLink);
                links.add(getDetails);
                links.add(updateBook);
                break;
        }
        return links;
    }

}
