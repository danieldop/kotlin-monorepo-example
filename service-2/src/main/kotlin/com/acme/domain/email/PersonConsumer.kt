package com.acme.domain.email

import com.acme.domain.person.Person
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.reactive.messaging.Incoming

@ApplicationScoped
class PersonConsumer(
    private val service: EmailService,
) {

    @Incoming("person-in")
    suspend fun consumerPerson(person: Person) {
        service.sendEmail(person)
    }
}