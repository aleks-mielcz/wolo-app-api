package pl.pjwstk.woloappapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganisationDTO {

    private Long id;
    private String name;
    private String description;
    private String email;
    private String phoneNumber;
    private AddressDTO address;
    private boolean isApproved;
    private UserDTO moderator;
    private String logoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public UserDTO getModerator() {
        return moderator;
    }

    public void setModerator(UserDTO moderator) {
        this.moderator = moderator;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}