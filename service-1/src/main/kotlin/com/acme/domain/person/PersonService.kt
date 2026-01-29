package com.acme.domain.person

import com.acme.rest.Limit
import com.acme.rest.Offset
import io.greenfiber.repository.range
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.NotFoundException
import kotlinx.coroutines.future.await
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import java.util.*

@ApplicationScoped
class PersonService(
    private val repository: PersonRepository,
) {
    @Inject
    @Channel("person-out")
    lateinit var emitter: Emitter<Person>

    suspend fun listPerson(limit: Limit, offset: Offset): List<Person> =
        repository.findAll().range(limit, offset).awaitSuspending()

    suspend fun get(id: UUID): Person =
        repository.findById(id).awaitSuspending() ?: throw NotFoundException("Person with id $id not found")

    suspend fun create(request: CreatePersonRequest): Person {
        val person = repository.persist(Person.of(request)).awaitSuspending()
        emitter.send(person).await()
        return person
    }
}