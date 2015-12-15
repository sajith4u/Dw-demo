package dev.mchoice.tech.talk.api.impl;

import dev.mchoice.tech.talk.api.ChatLoginController;
import dev.mchoice.tech.talk.core.FriendBean;
import dev.mchoice.tech.talk.core.FriendListHolder;
import dev.mchoice.tech.talk.core.JoseAuthentication;
import dev.mchoice.tech.talk.core.LoginBean;
import dev.mchoice.tech.talk.resources.DomainTypes;
import dev.mchoice.tech.talk.resources.StudentLinkBuilder;
import org.springframework.hateoas.Link;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatLoginControllerImpl implements ChatLoginController {

    JoseAuthentication joseAuthentication;
    @Context
    protected HttpServletRequest httpRequest;

    FriendListHolder friendListHolder;

    StudentLinkBuilder studentLinkBuilder;

    public ChatLoginControllerImpl(JoseAuthentication joseAuthentication, StudentLinkBuilder studentLinkBuilder) {
        this.joseAuthentication = joseAuthentication;
        friendListHolder = new FriendListHolder();
        this.studentLinkBuilder = studentLinkBuilder;
    }

    @Override
    public Response loginStudent(LoginBean loginBean) {
        Map<String, Object> response = new HashMap<>();
        String token = joseAuthentication.generateToken(loginBean.getUserName());
        response.put("STATUS", "S1000");
        response.put("token", token);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @Override
    public Response getFriendList() {
        Map<String, Object> response = new HashMap<>();
        String token = httpRequest.getHeader("token");
        System.out.println("Token Frfom Headers : " + token);
        Response.Status responseState = null;
        if (token != null) {
            boolean state = joseAuthentication.validateToken(token);
            if (state) {
                response.put("Status", "S1000");
                responseState = Response.Status.OK;
                List<FriendBean> list = new ArrayList<>();
                for (FriendBean friendBean : friendListHolder.getFriendList()) {
                    List<Link> detail = studentLinkBuilder.createLinks(DomainTypes.LOGIN, friendBean);
                    friendBean.add(detail);
                    list.add(friendBean);
                }
                response.put("list", list);

            } else {
                responseState = Response.Status.UNAUTHORIZED;
                response.put("Status", "E1400");
            }
        } else {
            responseState = Response.Status.UNAUTHORIZED;
            response.put("Status", "Authentication Failed");
        }
        return Response.status(responseState).entity(response).header("allowedOrigins", "*").build();
    }

    @Override
    public Response searchUser(String name) {
        Map<String, Object> response = new HashMap<>();
        String token = httpRequest.getHeader("token");
        System.out.println("Token From Headers : " + token);
        Response.Status responseState = null;
        if (token != null) {
            boolean state = joseAuthentication.validateToken(token);
            if (state) {
                response.put("Status", "S1000");
                responseState = Response.Status.OK;
            } else {
                responseState = Response.Status.UNAUTHORIZED;
                response.put("Status", "E1400");
            }
        } else {
            responseState = Response.Status.UNAUTHORIZED;
            response.put("Status", "TOKEN NOT FOUND");
        }
        return Response.status(responseState).entity(response).build();

    }

}
