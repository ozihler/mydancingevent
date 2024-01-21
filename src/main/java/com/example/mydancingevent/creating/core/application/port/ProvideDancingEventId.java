package com.example.mydancingevent.creating.core.application.port;

import com.example.mydancingevent.creating.core.domain.value.DancingEventId;

public interface ProvideDancingEventId {
    DancingEventId nextId();
}
