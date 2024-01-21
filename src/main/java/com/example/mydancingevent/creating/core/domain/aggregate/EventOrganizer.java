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

        if (EventOrganizerType.PREMIUM == type) {
            if (unpublishedDancingEvents.size() >= 10) {
                throw new PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit();
            }
        }
        if(EventOrganizerType.FREE == type){
            throw new FreeEventOrganizerHasReachedUnpublishedDancingEventLimit();
        }
        this.unpublishedDancingEvents.add(dancingEventId);
    }

    public List<DancingEventId> unpublishedDancingEvents() {
        return unpublishedDancingEvents;
    }
}
