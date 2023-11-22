package pl.pjwstk.woloappapi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pjwstk.woloappapi.model.dto.*;
import pl.pjwstk.woloappapi.model.*;

import java.util.List;

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

    List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categories);

    List<DistrictDTO> districtListToDistrictDTOList(List<District> districts);

    List<EventDTO> eventListToEventDTOList(List<Event> events);

    List<OrganisationDTO> organisationListToOrganisationDTOList(List<Organisation> organisations);

    List<RoleDTO> roleListToRoleDTOList(List<Role> roles);

    List<ShiftDTO> shiftListToShiftDTOList(List<Shift> shifts);

    List<UserDTO> userListToUserDTOList(List<User> users);
}
