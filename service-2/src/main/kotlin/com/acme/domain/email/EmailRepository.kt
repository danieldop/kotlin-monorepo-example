package com.acme.domain.email

import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class EmailRepository : ReactivePanacheMongoRepositoryBase<Email, UUID> {
}