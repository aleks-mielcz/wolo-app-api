package pl.pjwstk.woloappapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjwstk.woloappapi.model.Shift;
import pl.pjwstk.woloappapi.model.Shift;
import pl.pjwstk.woloappapi.repository.ShiftRepository;
import pl.pjwstk.woloappapi.utils.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Shift getShiftById(Long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shift id not found!"));
    }

    public void createShift(Shift Shift) {
        shiftRepository.save(Shift);
    }

    public void editShift(Long id, Shift updatedShift) {
        if (!shiftRepository.existsById(id)) {
            throw new NotFoundException("Shift with ID " + id + " does not exist");
        }

        Shift existingShift = shiftRepository.findById(id).orElseThrow();
        existingShift.setAddressToEvent(updatedShift.getAddressToEvent());
        existingShift.setShiftToUsers(updatedShift.getShiftToUsers());
        existingShift.setStartTime(updatedShift.getStartTime());
        existingShift.setEndTime(updatedShift.getEndTime());
        existingShift.setDate(updatedShift.getDate());
        existingShift.setCapacity(updatedShift.getCapacity());
        existingShift.setLeaderRequired(updatedShift.isLeaderRequired());
        existingShift.setRequiredMinAge(updatedShift.getRequiredMinAge());

        shiftRepository.save(existingShift);
    }

    public void deleteShift(Long id) {
        if (!shiftRepository.existsById(id)) {
            throw new IllegalArgumentException("Shift with ID " + id + " does not exist");
        }
        shiftRepository.deleteById(id);
    }

    public int getRegisteredUsersCountForShift(Long shiftId) {
        Shift shift = shiftRepository.findById(shiftId).orElse(null);

        if (shift != null) {
            return shift.getShiftToUsers().size();
        } else {
            return 0; // lub można zwrócić odpowiedni kod błędu
        }
    }
}