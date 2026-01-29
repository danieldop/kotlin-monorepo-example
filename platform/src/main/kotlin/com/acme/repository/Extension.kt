package io.greenfiber.repository


import com.acme.rest.Limit
import com.acme.rest.Offset
import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheQuery
import io.smallrye.mutiny.Uni

fun <Entity> ReactivePanacheQuery<Entity>.range(
    limit: Limit,
    offset: Offset
): Uni<List<Entity>> {
    val normalizedLimit = if (limit.limit > 0) limit.limit - 1 else 0
    return this.range(offset.offset, normalizedLimit).list()
}
