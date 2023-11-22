package pl.pjwstk.woloappapi.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private RoleDTO role;
    private boolean isPeselVerified;
    private boolean isAgreementSigned;
    private boolean isAdult;
    private OrganisationDTO organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public boolean isPeselVerified() {
        return isPeselVerified;
    }

    public void setPeselVerified(boolean peselVerified) {
        isPeselVerified = peselVerified;
    }

    public boolean isAgreementSigned() {
        return isAgreementSigned;
    }

    public void setAgreementSigned(boolean agreementSigned) {
        isAgreementSigned = agreementSigned;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public OrganisationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganisationDTO organization) {
        this.organization = organization;
    }
}
