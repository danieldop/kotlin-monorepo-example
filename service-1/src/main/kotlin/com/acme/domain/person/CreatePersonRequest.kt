package com.acme.domain.person

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class CreatePersonRequest(
    @field:NotEmpty
    @JsonProperty("firstName")
    val firstName: String,

    @field:NotEmpty
    @JsonProperty("lastName")
    val lastName: String,

    @field:Email
    @JsonProperty("email")
    val email: String,
)