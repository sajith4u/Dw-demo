package dev.mchoice.tech.talk.api;


import dev.mchoice.tech.talk.core.LoginBean;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public interface ChatLoginController {
    @POST
    @Path("/student/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response loginStudent(LoginBean loginBean);

    @GET
    @Path("/student/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response getFriendList();

    @GET
    @Path("/student/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response searchUser(String name);
}
