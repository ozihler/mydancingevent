package com.example.mydancingevent.creating.adapter.inmemory;

import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.application.port.EventOrganizerRepository;
import com.example.mydancingevent.creating.core.domain.aggregate.EventOrganizer;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;

import java.util.Map;

public final class InMemoryEventOrganizerRepository
        implements EventOrganizerRepository {

    private final Map<EventOrganizerId, EventOrganizer> eventOrganizers;
    private final InMemoryMessagePublisher messages = new InMemoryMessagePublisher();

    public InMemoryEventOrganizerRepository(Map<EventOrganizerId, EventOrganizer> eventOrganizers) {
        this.eventOrganizers = eventOrganizers;
    }

    @Override
    public EventOrganizer fetchById(EventOrganizerId eventOrganizerId)
            throws NonExistentEventOrganizer {
        if (!exists(eventOrganizerId)) {
            throw new NonExistentEventOrganizer();
        }

        return this.eventOrganizers.get(eventOrganizerId);
    }

    @Override
    public boolean exists(EventOrganizerId eventOrganizerId) {
        return this.eventOrganizers.containsKey(eventOrganizerId);
    }

    @Override
    public void store(EventOrganizer eventOrganizer) {
        this.eventOrganizers.put(eventOrganizer.id(), eventOrganizer);
        this.messages.publish(eventOrganizer.unpublishedDomainEvents());
    }

}
