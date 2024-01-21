package com.example.mydancingevent.creating.core.domain.aggregate;

import com.example.mydancingevent.creating.core.domain.value.DancingEventId;

import java.util.ArrayList;
import java.util.List;

public class EventOrganizer {
    private final List<DancingEventId> unpublishedDancingEvents;

    private EventOrganizer(List<DancingEventId> unpublishedDancingEvents) {
        this.unpublishedDancingEvents = unpublishedDancingEvents;
    }

    public static EventOrganizer create() {
        return new EventOrganizer(new ArrayList<>());
    }

    public void addDancingEvent(DancingEventId dancingEventId) {
        this.unpublishedDancingEvents.add(dancingEventId);
    }

    public List<DancingEventId> unpublishedDancingEvents() {
        return unpublishedDancingEvents;
    }
}
