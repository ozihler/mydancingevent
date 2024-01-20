package com.example.mydancingevent.creating.core.domain.aggregate;

import com.example.mydancingevent.creating.core.domain.value.DancingEventId;

public class EventOrganizer {
    public DancingEventId getDancingEventId() {
        return new DancingEventId("DE-1");
    }
}
