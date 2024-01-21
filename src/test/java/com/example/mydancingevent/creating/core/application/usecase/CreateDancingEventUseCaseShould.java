package com.example.mydancingevent.creating.core.application.usecase;

import com.example.mydancingevent.creating.core.application.exception.MissingEventOrganizerId;
import com.example.mydancingevent.creating.core.application.exception.NonExistentEventOrganizer;
import com.example.mydancingevent.creating.core.domain.aggregate.EventOrganizer;
import com.example.mydancingevent.creating.core.domain.exception.FreeEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.exception.PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.value.DancingEventId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerId;
import com.example.mydancingevent.creating.core.domain.value.EventOrganizerType;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateDancingEventUseCaseShould {

    @Test
    void fail_if_the_event_organizer_id_is_missing() {
        var eventOrganizer = EventOrganizer.create(EventOrganizerType.PREMIUM);

        assertThrows(MissingEventOrganizerId.class, () -> {
            new CreateDancingEventUseCase(eventOrganizer).invoke(null);
        });

        assertTrue(eventOrganizer.unpublishedDancingEvents().isEmpty());
    }

    @Test
    void fail_if_the_event_organizer_does_not_exist() {
        assertThrows(NonExistentEventOrganizer.class, () -> {
            new CreateDancingEventUseCase(null).invoke(EventOrganizerId.create("EO-1"));
        });
    }

    @Test
    void allow_up_to_10_unpublished_dancing_events_for_premium_event_organizers() throws Exception {
        var eventOrganizer = EventOrganizer.create(EventOrganizerType.PREMIUM);

        var useCase = new CreateDancingEventUseCase(eventOrganizer);
        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));

        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));
        useCase.invoke(EventOrganizerId.create("EO-1"));

        assertEquals(
                List.of(
                        new DancingEventId("DE-1"),
                        new DancingEventId("DE-2"),
                        new DancingEventId("DE-3"),
                        new DancingEventId("DE-4"),
                        new DancingEventId("DE-5"),
                        new DancingEventId("DE-6"),
                        new DancingEventId("DE-7"),
                        new DancingEventId("DE-8"),
                        new DancingEventId("DE-9"),
                        new DancingEventId("DE-10")
                ),
                eventOrganizer.unpublishedDancingEvents());
    }

    @Test
    void fail_if_event_organizer_has_already_ten_unpublished_dancing_events_and_is_premium() throws Exception {

        assertThrows(PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit.class, () -> {
            var eventOrganizer = EventOrganizer.create(EventOrganizerType.PREMIUM);
            var useCase = new CreateDancingEventUseCase(eventOrganizer);

            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));

            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));

            // this will raise the exception
            useCase.invoke(EventOrganizerId.create("EO-1"));
        });
    }

    @Test
    void fail_if_event_organizer_has_already_2_unpublished_dancing_events_and_is_free() throws Exception {

        assertThrows(FreeEventOrganizerHasReachedUnpublishedDancingEventLimit.class, () -> {
            var eventOrganizer = EventOrganizer.create(EventOrganizerType.FREE);
            var useCase = new CreateDancingEventUseCase(eventOrganizer);

            useCase.invoke(EventOrganizerId.create("EO-1"));
            useCase.invoke(EventOrganizerId.create("EO-1"));

            // this will raise the exception
            useCase.invoke(EventOrganizerId.create("EO-1"));
        });
    }
}
