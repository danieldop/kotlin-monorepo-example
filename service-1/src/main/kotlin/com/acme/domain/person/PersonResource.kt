package com.acme.domain.person

import com.acme.rest.Limit
import com.acme.rest.Offset
import com.acme.rest.ValidLimit
import com.acme.rest.ValidOffset
import io.quarkus.logging.Log
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import org.jboss.resteasy.reactive.RestQuery
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/v1/person")
class PersonResource(
    private val service: PersonService
) {

    @GET
    suspend fun listPerson(
        @ValidLimit @DefaultValue("100") @RestQuery limit: Limit,
        @ValidOffset @DefaultValue("0") @RestQuery offset: Offset
    ): RestResponse<List<Person>> {
        return RestResponse.ok(service.listPerson(limit, offset))
    }

    @GET
    @Path("/{id}")
    suspend fun getPerson(id: UUID): RestResponse<Person> = RestResponse.ok(service.get(id))

    @POST
    suspend fun createPerson(request: CreatePersonRequest): RestResponse<Person> =
        RestResponse.ok(service.create(request))
}