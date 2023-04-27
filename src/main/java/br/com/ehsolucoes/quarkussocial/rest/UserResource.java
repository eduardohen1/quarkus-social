package br.com.ehsolucoes.quarkussocial.rest;

import br.com.ehsolucoes.quarkussocial.dto.CreateUserRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @POST
    public Response createUser(CreateUserRequest userRequest){
        return Response.ok().build();
    }

}
