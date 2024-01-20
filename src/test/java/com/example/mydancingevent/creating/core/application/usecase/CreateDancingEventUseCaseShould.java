package com.example.mydancingevent.creating.core.application.usecase;

import com.example.mydancingevent.creating.core.application.exception.MissingEventOrganizerId;
import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.domain.exception.InvalidEventOrganizerId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateDancingEventUseCaseShould {

    @Test
    void fail_if_the_event_organizer_id_is_missing() {
        assertThrows(MissingEventOrganizerId.class, () -> {
            new CreateDancingEventUseCase().invoke(null);
        });
    }

    @Test
    void fail_if_the_event_organizer_does_not_exist() {
        assertThrows(NonExistentEventOrganizer.class, () -> {
            new CreateDancingEventUseCase().invoke(EventOrganizerId.create("EO-1"));
        });
    }
}
