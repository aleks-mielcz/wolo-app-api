package pl.pjwstk.woloappapi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pjwstk.woloappapi.model.dto.*;
import pl.pjwstk.woloappapi.model.*;

@Mapper
public interface GlobalMapper {
    GlobalMapper INSTANCE = Mappers.getMapper(GlobalMapper.class);

    AddressDTO addressToAddressDTO(Address address);
    Address addressDTOToAddress(AddressDTO addressDTO);

    AddressToEventDTO addressToEventToDTO(AddressToEvent addressToEvent);
    AddressToEvent addressToEventDTOToAddress(AddressToEventDTO addressToEventDTO);

    CategoryDTO categoryToCategoryDTO(Category category);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    CategoryToEventDTO categoryToEventToDTO(CategoryToEvent categoryToEvent);
    CategoryToEvent categoryToEventDTOToCategory(CategoryToEventDTO categoryToEventDTO);

    DistrictDTO districtToDistrictDTO(District district);
    District districtDTOToDistrict(DistrictDTO districtDTO);

    EventDTO eventToEventDTO(Event event);
    Event eventDTOToEvent(EventDTO eventDTO);

    OrganisationDTO organisationToOrganisationDTO(Organisation organisation);
    Organisation organisationDTOToOrganisation(OrganisationDTO organisationDTO);

    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);

    ShiftDTO shiftToShiftDTO(Shift shift);
    Shift shiftDTOToShift(ShiftDTO shiftDTO);

    ShiftToUserDTO shiftToUserToDTO(ShiftToUser shiftToUser);
    ShiftToUser shiftToUserDTOToShiftToUser(ShiftToUserDTO shiftToUserDTO);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
