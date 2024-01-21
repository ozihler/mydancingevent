package com.example.mydancingevent.creating.core.application.port;

import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.domain.aggregate.EventOrganizer;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;

public interface EventOrganizerRepository {
    EventOrganizer fetchById(EventOrganizerId eventOrganizerId)
            throws NonExistentEventOrganizer;

    boolean exists(EventOrganizerId eventOrganizerId);

    void store(EventOrganizer eventOrganizer);
}
