create table outboxevent
(
    id                 uuid                        not null,
    aggregatetype      varchar(255)                not null,
    aggregateid        uuid                        not null,
    type               varchar(255)                not null,
    timestamp          timestamp(6) with time zone not null,
    payload            varchar(8000),
    tracingspancontext varchar(256),
    primary key (id)
);