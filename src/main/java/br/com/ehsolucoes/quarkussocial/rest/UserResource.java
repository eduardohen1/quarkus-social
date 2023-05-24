package br.com.ehsolucoes.quarkussocial.rest;

import br.com.ehsolucoes.quarkussocial.domain.model.User;
import br.com.ehsolucoes.quarkussocial.dto.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    @Transactional
    public Response createUser(CreateUserRequest createUserRequest){
        User user = new User();
        user.setAge(createUserRequest.getAge());
        user.setName(createUserRequest.getName());

        user.persist();

        return Response.ok(user).build();
    }

    @GET
    public Response listaAllUsers(){
        PanacheQuery<User> query = User.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id){
        User user = User.findById(id);
        if(user == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        user.delete();
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest createUserRequest){
        User user = User.findById(id);
        if(user == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        user.setName(createUserRequest.getName());
        user.setAge(createUserRequest.getAge());
        user.persist();
        return Response.ok(user).build();
    }

}
