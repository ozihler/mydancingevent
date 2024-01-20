package com.example.mydancingevent.creating.core.domain.value;

import com.example.mydancingevent.creating.core.domain.exception.InvalidEventOrganizerId;

import java.util.Objects;

public final class EventOrganizerId {
    private static final String VALID_EVENT_ORGANIZER_ID_PATTERN = "EO-([1-9]\\d*)";
    private final String value;

    private EventOrganizerId(String value) {
        this.value = value;
    }

    public static EventOrganizerId create(String eventOrganizerId) throws InvalidEventOrganizerId {
        if (!isValid(eventOrganizerId)) {
            throw new InvalidEventOrganizerId();
        }
        return new EventOrganizerId(eventOrganizerId);
    }

    private static boolean isValid(String eventOrganizerId) {
        return eventOrganizerId != null && eventOrganizerId.matches(VALID_EVENT_ORGANIZER_ID_PATTERN);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (EventOrganizerId) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "EventOrganizerId[" +
                "value=" + value + ']';
    }

}
