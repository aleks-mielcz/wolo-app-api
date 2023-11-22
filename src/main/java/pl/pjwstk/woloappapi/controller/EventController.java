package pl.pjwstk.woloappapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.GlobalMapper;
import pl.pjwstk.woloappapi.model.Event;
import pl.pjwstk.woloappapi.model.dto.EventDTO;
import pl.pjwstk.woloappapi.service.EventService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final GlobalMapper globalMapper;

    @GetMapping()
    public ResponseEntity<List<EventDTO>> getEvents() {
        List<Event> events = eventService.getAllEvents();
        List<EventDTO> eventDTOs = globalMapper.eventListToEventDTOList(events);
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventDTO>> filterEvents(@RequestParam(value = "localization", required = false) String[] localizations,
                                                       @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                                       @RequestParam(value = "endDate", required = false) LocalDate endDate,
                                                       @RequestParam(value = "category", required = false) Long category,
                                                       @RequestParam(value = "organizer", required = false) Long organizer,
                                                       @RequestParam(value = "ageRestriction", required = false) Integer ageRestriction,
                                                       @RequestParam(value = "verification", required = false) boolean isPeselVerificationRequired) {

        List<Event> filteredEvents = eventService.filterEvents(localizations, startDate, endDate, category, organizer,
                ageRestriction, isPeselVerificationRequired);
        List<EventDTO> filteredEventDTOs = globalMapper.eventListToEventDTOList(filteredEvents);
        return new ResponseEntity<>(filteredEventDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        EventDTO eventDTO = globalMapper.eventToEventDTO(event);
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addEvent(@RequestBody EventDTO eventDTO) {
        Event event = globalMapper.eventDTOToEvent(eventDTO);
        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editEvent(@PathVariable Long id, @RequestBody EventDTO updatedEventDTO) {
        Event updatedEvent = globalMapper.eventDTOToEvent(updatedEventDTO);
        eventService.editEvent(id, updatedEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
