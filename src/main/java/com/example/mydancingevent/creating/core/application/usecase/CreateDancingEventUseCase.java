package com.example.mydancingevent.creating.core.application.usecase;

import com.example.mydancingevent.creating.core.application.exception.MissingEventOrganizerId;
import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.domain.aggregate.EventOrganizer;
import com.example.mydancingevent.creating.core.domain.value.DancingEventId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;

public class CreateDancingEventUseCase {

    private final EventOrganizer eventOrganizer;

    public CreateDancingEventUseCase(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public void invoke(EventOrganizerId eventOrganizerId)
            throws
            MissingEventOrganizerId,
            NonExistentEventOrganizer {

        if (eventOrganizerId == null) {
            throw new MissingEventOrganizerId();
        }

        if (eventOrganizer == null) {
            throw new NonExistentEventOrganizer();
        }

        eventOrganizer.addDancingEvent(new DancingEventId("DE-1"));
    }

}
