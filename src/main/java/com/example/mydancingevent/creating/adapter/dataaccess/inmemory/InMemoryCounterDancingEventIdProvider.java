package com.example.mydancingevent.creating.adapter.dataaccess.inmemory;

import com.example.mydancingevent.creating.core.application.port.ProvideDancingEventId;
import com.example.mydancingevent.creating.core.domain.value.DancingEventId;

public class InMemoryCounterDancingEventIdProvider implements ProvideDancingEventId {
    private int counter = 1;

    @Override
    public DancingEventId nextId() {
        return new DancingEventId("DE-" + counter++);
    }
}
