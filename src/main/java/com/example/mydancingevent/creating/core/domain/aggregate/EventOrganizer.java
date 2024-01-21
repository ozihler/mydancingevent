package com.example.mydancingevent.creating.core.domain.aggregate;

import com.example.mydancingevent.creating.core.domain.exception.FreeEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.exception.PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit;
import com.example.mydancingevent.creating.core.domain.value.*;

import java.util.ArrayList;
import java.util.List;

public class EventOrganizer {
    private final EventOrganizerType type;
    private final List<DancingEventId> unpublishedDancingEvents;
    private final EventOrganizerId id;
    private final List<DomainEvent> unpublishedDomainEvents;

    private EventOrganizer(EventOrganizerType type, List<DancingEventId> unpublishedDancingEvents, EventOrganizerId id) {
        this.type = type;
        this.id = id;
        this.unpublishedDancingEvents = unpublishedDancingEvents;
        this.unpublishedDomainEvents = new ArrayList<>();
    }

    public static EventOrganizer create(EventOrganizerType type, EventOrganizerId id) {
        return new EventOrganizer(type, new ArrayList<>(), id);
    }

    public void addDancingEvent(DancingEventId dancingEventId)
            throws
            PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit,
            FreeEventOrganizerHasReachedUnpublishedDancingEventLimit {

        if (isPremium() && hasReachedLimitForPremium()) {
            throw new PremiumEventOrganizerHasReachedUnpublishedDancingEventLimit();
        }
        if (isFree() && hasReachedLimitForFree()) {
            throw new FreeEventOrganizerHasReachedUnpublishedDancingEventLimit();
        }

        addNewDancingEventWithId(dancingEventId);
    }

    private void addNewDancingEventWithId(DancingEventId dancingEventId) {
        this.unpublishedDancingEvents.add(dancingEventId);
        this.unpublishedDomainEvents.add(new DomainEvent(
                DomainEventType.DANCING_EVENT_CREATED,
                this.id(),
                dancingEventId
        ));
    }


    private boolean hasReachedLimitForFree() {
        return unpublishedDancingEvents.size() >= 2;
    }

    private boolean hasReachedLimitForPremium() {
        return unpublishedDancingEvents.size() >= 10;
    }

    private boolean isFree() {
        return EventOrganizerType.FREE == type;
    }

    private boolean isPremium() {
        return EventOrganizerType.PREMIUM == type;
    }

    public List<DancingEventId> unpublishedDancingEvents() {
        return unpublishedDancingEvents;
    }

    public EventOrganizerId id() {
        return id;
    }

    public List<DomainEvent> unpublishedDomainEvents() {
        return unpublishedDomainEvents;
    }
}
