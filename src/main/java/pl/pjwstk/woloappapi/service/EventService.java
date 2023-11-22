package pl.pjwstk.woloappapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjwstk.woloappapi.model.Event;
import pl.pjwstk.woloappapi.model.User;
import pl.pjwstk.woloappapi.repository.EventRepository;
import pl.pjwstk.woloappapi.utils.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

     public List<Event> getAllEvents() {
         return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event id not found!"));
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        if (!eventRepository.existsById(event.getId())) {
            throw new IllegalArgumentException("Event with ID " + event.getId() + " does not exist");
        }
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Event with ID " + id + " does not exist");
        }
        eventRepository.deleteById(id);
    }

    public List<Event> filterEvents(String[] localizations, LocalDate startDate, LocalDate endDate,
                                    Long category, Long organizer, Integer ageRestriction,
                                    boolean isPeselVerificationRequired) {
        return eventRepository.findAllByFilter(localizations, startDate, endDate,
                category, organizer, ageRestriction, isPeselVerificationRequired);
    }
    public void editEvent(Long id, Event updatedEvent) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Event with ID " + id + " does not exist");
        }

        Event existingEvent = eventRepository.findById(id).orElseThrow();
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setOrganisation(updatedEvent.getOrganisation());
        existingEvent.setCategoryToEventSet(updatedEvent.getCategoryToEventSet());
        existingEvent.setPeselVerificationRequired(updatedEvent.isPeselVerificationRequired());
        existingEvent.setAgreementNeeded(updatedEvent.isAgreementNeeded());
        existingEvent.setAddressToEvents(updatedEvent.getAddressToEvents());
        existingEvent.setImageUrl(updatedEvent.getImageUrl());
        existingEvent.setApproved(updatedEvent.isApproved());

        eventRepository.save(existingEvent);
    }
}
