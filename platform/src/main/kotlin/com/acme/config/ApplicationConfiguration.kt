package com.acme.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import jakarta.enterprise.context.Dependent
import jakarta.enterprise.inject.Produces
import jakarta.inject.Singleton

@Dependent
class ApplicationConfiguration {

    @Singleton
    @Produces
    fun objectMapper(): ObjectMapper {
        val validationModule = SimpleModule()

        return ObjectMapper().apply {
            JsonInclude.Include.NON_DEFAULT
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            registerModule(validationModule)
            registerModule(JavaTimeModule())
            registerModule(kotlinModule())
            findAndRegisterModules()
        }
    }
}
