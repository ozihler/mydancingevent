package com.example.mydancingevent.creating.core.application.usecase;

import com.example.mydancingevent.creating.core.application.exception.MissingEventOrganizerId;
import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.domain.aggregate.EventOrganizer;
import com.example.mydancingevent.creating.core.domain.exception.FreeEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.exception.PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.value.DancingEventId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;

public class CreateDancingEventUseCase {

    private final EventOrganizer eventOrganizer;
    private final EventOrganizer eventOrganizer2;
    private final EventOrganizer eventOrganizer3;

    public CreateDancingEventUseCase(
            EventOrganizer eventOrganizer,
            EventOrganizer eventOrganizer2,
            EventOrganizer eventOrganizer3) {
        this.eventOrganizer = eventOrganizer;
        this.eventOrganizer2 = eventOrganizer2;
        this.eventOrganizer3 = eventOrganizer3;
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

        if (eventOrganizer == null
                || eventOrganizer2 == null
                || eventOrganizer3 == null
        ) {
            throw new NonExistentEventOrganizer();
        }

        if ("EO-1".equals(eventOrganizerId.value())) {
            eventOrganizer.addDancingEvent(dancingEventId);
        } else if ("EO-2".equals(eventOrganizerId.value())) {
            eventOrganizer2.addDancingEvent(dancingEventId);
        } else {
            eventOrganizer3.addDancingEvent(dancingEventId);
        }
    }

    int counter = 1;
    private DancingEventId getDancingEventId() {
        return new DancingEventId("DE-" + counter++);
    }
}
