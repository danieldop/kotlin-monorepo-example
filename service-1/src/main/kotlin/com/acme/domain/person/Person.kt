package com.acme.domain.person

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.mongodb.panache.common.MongoEntity
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import java.time.Instant
import java.util.*

@MongoEntity(database = "demo")
data class Person @BsonCreator constructor(
    @field:Valid @BsonId @JsonProperty("id")
    val id: UUID,

    @field:NotEmpty @BsonProperty("firstName") @JsonProperty("firstName")
    val firstName: String,

    @field:NotEmpty @BsonProperty("lastName") @JsonProperty("lastName")
    val lastName: String,

    @field:Email @BsonProperty("email") @JsonProperty("email")
    val email: String,

    @field:Valid @BsonProperty("createdAt") @JsonProperty("createdAt")
    val createdAt: Instant,

    @field:Valid @BsonProperty("updatedAt") @JsonProperty("updatedAt")
    val updatedAt: Instant,

    @field:Valid @BsonProperty("deletedAt") @JsonProperty("deletedAt")
    val deletedAt: Instant?
) {
    companion object {
        fun of(request: CreatePersonRequest) = Person(
            UUID.randomUUID(),
            request.firstName,
            request.lastName,
            request.email,
            Instant.now(),
            Instant.now(),
            null
        )
    }
}
