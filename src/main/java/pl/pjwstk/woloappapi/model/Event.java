package pl.pjwstk.woloappapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is required")
    @Size(max = 250, message = "Name cannot exceed 250 characters")
    private String name;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description is required")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organisation_id", nullable = false)
    @NotNull(message = "Organisation ID is required")
    @Valid
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Valid
    private Category category;

    @Column(name = "is_pesel_ver_req", nullable = false)
    private boolean isPeselVerificationRequired;

    @Column(name = "is_agreement_needed", nullable = false)
    private boolean isAgreementNeeded;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @Valid
    private List<AddressToEvent> addressToEvents = new ArrayList<>();

}
