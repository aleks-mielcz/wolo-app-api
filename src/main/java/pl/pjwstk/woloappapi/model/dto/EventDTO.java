package pl.pjwstk.woloappapi.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class EventDTO {

    private Long id;
    private String name;
    private String description;
    private OrganisationDTO organisation;
    private Set<CategoryDTO> categories;
    private boolean isPeselVerificationRequired;
    private boolean isAgreementNeeded;
    private List<AddressToEventDTO> addressToEvents;
    private String imageUrl;
    private boolean approved;

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

    public OrganisationDTO getOrganisation() {
        return organisation;
    }

    public void setOrganisation(OrganisationDTO organisation) {
        this.organisation = organisation;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public boolean isPeselVerificationRequired() {
        return isPeselVerificationRequired;
    }

    public void setPeselVerificationRequired(boolean peselVerificationRequired) {
        isPeselVerificationRequired = peselVerificationRequired;
    }

    public boolean isAgreementNeeded() {
        return isAgreementNeeded;
    }

    public void setAgreementNeeded(boolean agreementNeeded) {
        isAgreementNeeded = agreementNeeded;
    }

    public List<AddressToEventDTO> getAddressToEvents() {
        return addressToEvents;
    }

    public void setAddressToEvents(List<AddressToEventDTO> addressToEvents) {
        this.addressToEvents = addressToEvents;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
