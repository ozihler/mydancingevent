package com.example.mydancingevent.creating.core.application.usecase;

import com.example.mydancingevent.creating.core.application.exception.MissingEventOrganizerId;
import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.domain.aggregate.EventOrganizer;
import com.example.mydancingevent.creating.core.domain.value.DancingEventId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateDancingEventUseCaseShould {

    @Test
    void fail_if_the_event_organizer_id_is_missing() {
        var eventOrganizer = new EventOrganizer();

        assertThrows(MissingEventOrganizerId.class, () -> {
            new CreateDancingEventUseCase(eventOrganizer).invoke(null);
        });

        assertNull(eventOrganizer.dancingEventId());
    }

    @Test
    void fail_if_the_event_organizer_does_not_exist() {
        assertThrows(NonExistentEventOrganizer.class, () -> {
            new CreateDancingEventUseCase(null).invoke(EventOrganizerId.create("EO-1"));
        });
    }

    @Test
    void add_a_dancing_event_to_an_event_organizer() throws Exception {
        var eventOrganizer = new EventOrganizer();

        new CreateDancingEventUseCase(eventOrganizer).invoke(EventOrganizerId.create("EO-1"));

        assertEquals(new DancingEventId("DE-1"), eventOrganizer.dancingEventId());
    }

    @Test
    void add_multiple_dancing_events_to_an_event_organizer() throws Exception {
        var eventOrganizer = new EventOrganizer();

        new CreateDancingEventUseCase(eventOrganizer).invoke(EventOrganizerId.create("EO-1"));

        assertEquals(List.of(new DancingEventId("DE-1")), eventOrganizer.unpublishedDancingEvents());
    }
}
