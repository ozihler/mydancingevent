package com.example.mydancingevent.creating.core.application.usecase;

import com.example.mydancingevent.creating.core.application.exception.MissingEventOrganizerId;
import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;

public class CreateDancingEventUseCase {
    public void invoke(EventOrganizerId eventOrganizerId)
            throws
            MissingEventOrganizerId,
            NonExistentEventOrganizer {

        if (eventOrganizerId == null) {
            throw new MissingEventOrganizerId();
        }

        throw new NonExistentEventOrganizer();
    }

}
