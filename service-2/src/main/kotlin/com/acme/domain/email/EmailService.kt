package com.acme.domain.email

import com.acme.domain.person.Person
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EmailService(
    private val repository: EmailRepository,
) {
    suspend fun sendEmail(person: Person) {
        val email = Email.from(person, "Welcome ${person.firstName} ${person.lastName}!")
        repository.persist(email).awaitSuspending()
    }
}