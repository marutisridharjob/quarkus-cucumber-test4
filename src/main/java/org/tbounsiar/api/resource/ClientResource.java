package org.tbounsiar.api.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.RestResponse;
import org.tbounsiar.api.bom.ClientBom;
import org.tbounsiar.api.bom.ClientCreateBom;
import org.tbounsiar.api.bom.ResponseBom;
import org.tbounsiar.api.model.Client;
import org.tbounsiar.api.service.ClientService;

import java.util.List;

@Path("/api/v1/client")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseBom<List<Client>> list() {
        return service.all();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<ResponseBom<ClientBom>> get(String id) {
        var bom = service.get(id);
        return RestResponse.status(
                bom.getContent() == null ?
                        RestResponse.Status.NOT_FOUND :
                        RestResponse.Status.OK, bom
        );
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<ResponseBom<ClientBom>> create(ClientCreateBom client) {
        return RestResponse.status(RestResponse.Status.CREATED, service.create(client));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<ResponseBom<ClientBom>> update(ClientCreateBom client) {
        return RestResponse.ok(service.update(client));
    }

    @DELETE
    @Path("{id}")
    public void delete(String id) {
        service.delete(id);
    }
}
