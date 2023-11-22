package pl.pjwstk.woloappapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjwstk.woloappapi.model.Category;
import pl.pjwstk.woloappapi.model.District;
import pl.pjwstk.woloappapi.repository.DistrictRepository;
import pl.pjwstk.woloappapi.utils.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District getDistrictById(Long id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("District id not found!"));
    }

    public void createDistrict(District district) {
        districtRepository.save(district);
    }

    public void deleteDistrict(Long id) {
        if (!districtRepository.existsById(id)) {
            throw new IllegalArgumentException("District with ID " + id + " does not exist");
        }
        districtRepository.deleteById(id);
    }
    public void editDistrict(Long id, District updatedDistrict) {
        if (!districtRepository.existsById(id)) {
            throw new NotFoundException("District with ID " + id + " does not exist");
        }

        District existingDistrict = districtRepository.findById(id).orElseThrow();
        existingDistrict.setName(updatedDistrict.getName());
        existingDistrict.setCity(updatedDistrict.getCity());
        districtRepository.save(existingDistrict);
    }
}
