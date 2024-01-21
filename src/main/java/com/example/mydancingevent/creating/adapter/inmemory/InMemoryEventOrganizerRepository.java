package com.example.mydancingevent.creating.adapter.inmemory;

import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.application.port.EventOrganizerRepository;
import com.example.mydancingevent.creating.core.domain.aggregate.EventOrganizer;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;

import java.util.Map;

public record InMemoryEventOrganizerRepository(
        Map<EventOrganizerId, EventOrganizer> eventOrganizers)
        implements EventOrganizerRepository {

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
    }
}
