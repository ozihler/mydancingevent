package com.example.mydancingevent.creating.core.application.usecase;

import com.example.mydancingevent.creating.core.application.exception.MissingEventOrganizerId;
import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.application.port.EventOrganizerRepository;
import com.example.mydancingevent.creating.core.domain.exception.FreeEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.exception.PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.value.DancingEventId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;

public class CreateDancingEventUseCase {

    private final EventOrganizerRepository eventOrganizers;

    public CreateDancingEventUseCase(EventOrganizerRepository eventOrganizers) {
        this.eventOrganizers = eventOrganizers;
    }

    public void invoke(EventOrganizerId eventOrganizerId)
            throws
            MissingEventOrganizerId,
            NonExistentEventOrganizer,
            PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit,
            FreeEventOrganizerHasReachedUnpublishedDancingEventLimit {

        if (eventOrganizerId == null) {
            throw new MissingEventOrganizerId();
        }

        var dancingEventId = getDancingEventId();

        var eventOrganizer = eventOrganizers.fetchById(eventOrganizerId);

        eventOrganizer.addDancingEvent(dancingEventId);

        eventOrganizers.store(eventOrganizer);
    }

    int counter = 1;
    private DancingEventId getDancingEventId() {
        return new DancingEventId("DE-" + counter++);
    }
}
