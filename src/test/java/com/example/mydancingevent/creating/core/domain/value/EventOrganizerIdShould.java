package com.example.mydancingevent.creating.core.domain.value;

import com.example.mydancingevent.creating.core.application.usecase.CreateDancingEventUseCase;
import com.example.mydancingevent.creating.core.domain.exception.InvalidEventOrganizerId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventOrganizerIdShould {
    @Test
    void fail_if_event_organizer_id_is_invalid() {
        assertThrows(InvalidEventOrganizerId.class, () -> {
           EventOrganizerId.create(null);
        });

        assertThrows(InvalidEventOrganizerId.class, () -> {
           EventOrganizerId.create("");
        });

        assertThrows(InvalidEventOrganizerId.class, () -> {
           EventOrganizerId.create("E");
        });
    }

    @Test
    void be_created_if_valid() throws InvalidEventOrganizerId {
        assertNotNull(EventOrganizerId.create("EO-1"));
    }
}
