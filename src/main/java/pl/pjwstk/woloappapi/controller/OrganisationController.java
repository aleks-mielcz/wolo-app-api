package pl.pjwstk.woloappapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.GlobalMapper;
import pl.pjwstk.woloappapi.model.Organisation;
import pl.pjwstk.woloappapi.model.Event;
import pl.pjwstk.woloappapi.model.dto.OrganisationDTO;
import pl.pjwstk.woloappapi.service.OrganisationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;
    private final GlobalMapper globalMapper;

    @GetMapping()
    public ResponseEntity<List<OrganisationDTO>> getOrganisations() {
        List<Organisation> organisations = organisationService.getAllOrganisations();
        List<OrganisationDTO> organisationDTOs = globalMapper.organisationListToOrganisationDTOList(organisations);
        return new ResponseEntity<>(organisationDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganisationDTO> getOrganisationById(@PathVariable Long id) {
        Organisation organisation = organisationService.getOrganisationById(id);
        OrganisationDTO organisationDTO = globalMapper.organisationToOrganisationDTO(organisation);
        return new ResponseEntity<>(organisationDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addOrganisation(@RequestBody OrganisationDTO organisationDTO) {
        Organisation organisation = globalMapper.organisationDTOToOrganisation(organisationDTO);
        organisationService.createOrganisation(organisation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOrganisation(@PathVariable Long id) {
        organisationService.deleteOrganisation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<List<Event>> getEventsByOrganizer(@PathVariable Long id) {
        List<Event> eventsByOrganizer = organisationService.getEventsByOrganizer(id);
        return new ResponseEntity<>(eventsByOrganizer, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editOrganisation(@PathVariable Long id, @RequestBody OrganisationDTO updatedOrganisationDTO) {
        Organisation updatedOrganisation = globalMapper.organisationDTOToOrganisation(updatedOrganisationDTO);
        organisationService.editOrganisation(id, updatedOrganisation);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
