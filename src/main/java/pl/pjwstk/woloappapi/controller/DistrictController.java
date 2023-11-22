package pl.pjwstk.woloappapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.GlobalMapper;
import pl.pjwstk.woloappapi.model.District;
import pl.pjwstk.woloappapi.model.dto.DistrictDTO;
import pl.pjwstk.woloappapi.service.DistrictService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/districts")
public class DistrictController {

    private final DistrictService districtService;
    private final GlobalMapper globalMapper;

    @GetMapping()
    public ResponseEntity<List<DistrictDTO>> getDistricts() {
        List<District> districts = districtService.getAllDistricts();
        List<DistrictDTO> districtDTOs = globalMapper.districtListToDistrictDTOList(districts);
        return new ResponseEntity<>(districtDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictDTO> getDistrictById(@PathVariable Long id) {
        District district = districtService.getDistrictById(id);
        DistrictDTO districtDTO = globalMapper.districtToDistrictDTO(district);
        return new ResponseEntity<>(districtDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addDistrict(@RequestBody DistrictDTO districtDTO) {
        District district = globalMapper.districtDTOToDistrict(districtDTO);
        districtService.createDistrict(district);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editDistrict(@PathVariable Long id, @RequestBody DistrictDTO updatedDistrictDTO) {
        District updatedDistrict = globalMapper.districtDTOToDistrict(updatedDistrictDTO);
        districtService.editDistrict(id, updatedDistrict);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
