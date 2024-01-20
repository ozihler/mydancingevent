package com.example.mydancingevent.creating.core.domain.aggregate;

import com.example.mydancingevent.creating.core.domain.value.DancingEventId;

import java.util.List;

public class EventOrganizer {

    private DancingEventId dancingEventId;

    public DancingEventId dancingEventId() {
        return dancingEventId;
    }

    public void addDancingEvent(DancingEventId dancingEventId) {
        this.dancingEventId = dancingEventId;
    }

    public List<DancingEventId> unpublishedDancingEvents() {
        return List.of(new DancingEventId("DE-1"));
    }
}
