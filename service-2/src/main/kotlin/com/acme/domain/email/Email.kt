package com.acme.domain.email

import com.fasterxml.jackson.annotation.JsonProperty
import com.acme.domain.person.Person
import io.quarkus.mongodb.panache.common.MongoEntity
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import java.util.*

@MongoEntity(database = "demo", collection = "Emails")
data class Email @BsonCreator constructor(
    @field:Valid @BsonId @JsonProperty("id")
    val id: UUID,

    @field:NotEmpty @BsonProperty("firstName") @JsonProperty("firstName")
    val firstName: String,

    @field:NotEmpty @BsonProperty("lastName") @JsonProperty("lastName")
    val lastName: String,

    @field:Email @BsonProperty("email") @JsonProperty("email")
    val email: String,

    @field:Valid @BsonProperty("content") @JsonProperty("content")
    val content: String,
) {
    companion object {
        fun from(person: Person, content: String) = Email(
            UUID.randomUUID(),
            person.firstName,
            person.lastName,
            person.email,
            content
        )
    }
}