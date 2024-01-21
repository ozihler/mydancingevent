package com.example.mydancingevent.creating.core.domain.value;

public record DomainEvent(DomainEventType dancingEventCreated, EventOrganizerId id, DancingEventId dancingEventId) {
}
