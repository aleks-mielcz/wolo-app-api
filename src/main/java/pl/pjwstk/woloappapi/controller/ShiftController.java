package pl.pjwstk.woloappapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.GlobalMapper;
import pl.pjwstk.woloappapi.model.Shift;
import pl.pjwstk.woloappapi.model.dto.ShiftDTO;
import pl.pjwstk.woloappapi.service.ShiftService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/shifts")
public class ShiftController {

    private final ShiftService shiftService;
    private final GlobalMapper globalMapper;

    @GetMapping()
    public ResponseEntity<List<ShiftDTO>> getShifts() {
        List<Shift> shifts = shiftService.getAllShifts();
        List<ShiftDTO> shiftDTOs = globalMapper.shiftListToShiftDTOList(shifts);
        return new ResponseEntity<>(shiftDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftDTO> getShiftById(@PathVariable Long id) {
        Shift shift = shiftService.getShiftById(id);
        ShiftDTO shiftDTO = globalMapper.shiftToShiftDTO(shift);
        return new ResponseEntity<>(shiftDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addShift(@RequestBody ShiftDTO shiftDTO) {
        Shift shift = globalMapper.shiftDTOToShift(shiftDTO);
        shiftService.createShift(shift);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/registeredUsersCount/{id}")
    public ResponseEntity<Integer> getRegisteredUsersCountForShift(@PathVariable Long id) {
        int registeredUsersCount = shiftService.getRegisteredUsersCountForShift(id);
        return ResponseEntity.ok(registeredUsersCount);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editShift(@PathVariable Long id, @RequestBody ShiftDTO updatedShiftDTO) {
        Shift updatedShift = globalMapper.shiftDTOToShift(updatedShiftDTO);
        shiftService.editShift(id, updatedShift);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
