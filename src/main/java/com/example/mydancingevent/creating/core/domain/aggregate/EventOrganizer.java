package com.example.mydancingevent.creating.core.domain.aggregate;

import com.example.mydancingevent.creating.core.domain.exception.FreeEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.exception.PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.value.DancingEventId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerType;

import java.util.ArrayList;
import java.util.List;

public class EventOrganizer {
    private final EventOrganizerType type;
    private final List<DancingEventId> unpublishedDancingEvents;

    private EventOrganizer(EventOrganizerType type, List<DancingEventId> unpublishedDancingEvents) {
        this.type = type;
        this.unpublishedDancingEvents = unpublishedDancingEvents;
    }

    public static EventOrganizer create(EventOrganizerType type) {
        return new EventOrganizer(type, new ArrayList<>());
    }

    public void addDancingEvent(DancingEventId dancingEventId)
            throws
            PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit,
            FreeEventOrganizerHasReachedUnpublishedDancingEventLimit {

        if (isPremium() && hasReachedLimitForPremium()) {
            throw new PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit();
        }
        if (isFree() && hasReachedLimitForFree()) {
            throw new FreeEventOrganizerHasReachedUnpublishedDancingEventLimit();
        }
        this.unpublishedDancingEvents.add(dancingEventId);
    }

    private boolean hasReachedLimitForFree() {
        return unpublishedDancingEvents.size() >= 2;
    }

    private boolean hasReachedLimitForPremium() {
        return unpublishedDancingEvents.size() >= 10;
    }

    private boolean isFree() {
        return EventOrganizerType.FREE == type;
    }

    private boolean isPremium() {
        return EventOrganizerType.PREMIUM == type;
    }

    public List<DancingEventId> unpublishedDancingEvents() {
        return unpublishedDancingEvents;
    }
}
