package com.example.mydancingevent.creating.adapter.inmemory;

import com.example.mydancingevent.creating.core.domain.value.DomainEvent;

import java.util.List;

public class InMemoryMessagePublisher {
    public void publish(List<DomainEvent> domainEvents) {
        System.out.println("Publishing: "+domainEvents);
    }
}
