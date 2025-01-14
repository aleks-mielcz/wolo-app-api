package pl.pjwstk.woloappapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import pl.pjwstk.woloappapi.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> findAllByFilter(String[] localizations, LocalDate startDate,
                                       LocalDate endDate, Long category, Long organizer,
                                       Integer ageRestriction, boolean isPeselVerificationRequired) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> root = criteriaQuery.from(Event.class);

        List<Predicate> predicates = new ArrayList<>();

        addLocalizationPredicate(localizations, criteriaBuilder, root, predicates);
        addDatePredicates(startDate, endDate, criteriaBuilder, root, predicates);
        addCategoryPredicate(category, criteriaBuilder, root, predicates);
        addOrganizerPredicate(organizer, criteriaBuilder, root, predicates);
        addAgeRestrictionPredicate(ageRestriction, criteriaBuilder, root, predicates);

    //    if (!isPeselVerificationRequired)
    //        predicates.add(criteriaBuilder.equal(root.get("isPeselVerificationRequired"), isPeselVerificationRequired));


        if (predicates.isEmpty()) {
            TypedQuery<Event> query = entityManager.createQuery(criteriaQuery.select(root));
            return query.getResultList();
        }

        criteriaQuery.select(root).distinct(true);
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Event> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private void addLocalizationPredicate(String[] localizations, CriteriaBuilder criteriaBuilder,
                                          Root<Event> root, List<Predicate> predicates) {
        if (localizations != null && localizations.length > 0) {
            List<Predicate> orPredicates = Arrays.stream(localizations)
                    .map(localization -> {
                        Join<Event, AddressToEvent> addressToEventJoin = root.join("addressToEvents", JoinType.INNER);
                        Join<AddressToEvent, Address> addressJoin = addressToEventJoin.join("address", JoinType.INNER);
                        Join<Address, District> districtJoin = addressJoin.join("district", JoinType.INNER);
                        return criteriaBuilder.equal(districtJoin.get("name"), localization);
                    }).toList();
            predicates.add(criteriaBuilder.or(orPredicates.toArray(new Predicate[0])));
        }
    }

    private void addDatePredicates(LocalDate startDate, LocalDate endDate, CriteriaBuilder criteriaBuilder,
                                   Root<Event> root, List<Predicate> predicates) {
        if (startDate != null) {
            Join<Event, AddressToEvent> addressToEventJoin = root.join("addressToEvents", JoinType.INNER);

            Join<AddressToEvent, Shift> shiftJoin = addressToEventJoin.join("shifts", JoinType.INNER);

            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shiftJoin.get("date"), startDate));
        }

        if (endDate != null) {
            Join<Event, AddressToEvent> addressToEventJoin = root.join("addressToEvents", JoinType.INNER);

            Join<AddressToEvent, Shift> shiftJoin = addressToEventJoin.join("shifts", JoinType.INNER);

            predicates.add(criteriaBuilder.lessThanOrEqualTo(shiftJoin.get("date"), endDate));
        }
    }

    private void addCategoryPredicate(Long category, CriteriaBuilder criteriaBuilder,
                                      Root<Event> root, List<Predicate> predicates) {
        if (category != null) {
            Join<Event, Category> categoryJoin = root.join("category", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(categoryJoin.get("id"), category));
        }
    }

    private void addOrganizerPredicate(Long organizer, CriteriaBuilder criteriaBuilder,
                                       Root<Event> root, List<Predicate> predicates) {
        if (organizer != null) {
            Join<Event, Organisation> organisationJoin = root.join("organisation", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(organisationJoin.get("id"), organizer));
        }
    }

    private void addAgeRestrictionPredicate(Integer ageRestriction, CriteriaBuilder criteriaBuilder,
                                            Root<Event> root, List<Predicate> predicates) {
        if (ageRestriction != null) {
            Join<Event, AddressToEvent> addressToEventJoin = root.join("addressToEvents", JoinType.INNER);

            Join<AddressToEvent, Shift> shiftJoin = addressToEventJoin.join("shifts", JoinType.INNER);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shiftJoin.get("requiredMinAge"), ageRestriction));
        }
    }
}

