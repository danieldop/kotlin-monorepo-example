package com.acme.domain.person

import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class PersonRepository : ReactivePanacheMongoRepositoryBase<Person, UUID> {
}