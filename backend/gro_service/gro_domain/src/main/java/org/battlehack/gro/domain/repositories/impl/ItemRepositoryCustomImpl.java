package org.battlehack.gro.domain.repositories.impl;

import org.battlehack.gro.domain.entities.Item;
import org.battlehack.gro.domain.repositories.ItemRepositoryCustom;
import org.battlehack.gro.domain.tos.FilterTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

/**
 * Created by aakhmerov on 22/06/14.
 */
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    @PersistenceContext
    private EntityManager em;


    @Override
    public Collection<Item> findFiltered(FilterTO filter) {
        Collection<Item> result = null;

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Item> root = criteriaQuery.from(Item.class);

        Predicate predicates = criteriaBuilder.conjunction();

        predicates = processName (predicates,criteriaBuilder,filter, root);
        predicates = processTags(predicates, criteriaBuilder, filter, root);
        predicates = processUser(predicates, criteriaBuilder, filter, root);

        criteriaQuery.select(root).where(predicates).distinct(true);
        result = em.createQuery(criteriaQuery).getResultList();

        return result;
    }

    private Predicate processUser(Predicate predicates, CriteriaBuilder criteriaBuilder, FilterTO filter, Root<Item> root) {
        Predicate wrapper = predicates;
        if (filter.getUser() != null) {
            Predicate predicate;
            predicate = criteriaBuilder.equal(root.get("userData"), filter.getUser());
            wrapper = criteriaBuilder.and(wrapper, predicate);
        }
        return wrapper;
    }

    private Predicate processTags(Predicate predicates, CriteriaBuilder criteriaBuilder, FilterTO filter, Root<Item> root) {
        Predicate wrapper = predicates;
        if (filter.getTags() != null) {
            Predicate predicate;
            predicate = criteriaBuilder.like(root.<String>get("tags"), "%" + filter.getTags() + "%");
            wrapper = criteriaBuilder.and(wrapper, predicate);
        }
        return wrapper;
    }

    private Predicate processName(Predicate predicates, CriteriaBuilder criteriaBuilder, FilterTO filter, Root<Item> root) {
        Predicate wrapper = predicates;
        if (filter.getName() != null) {
            Predicate predicate;
            predicate = criteriaBuilder.like(root.<String>get("name"), "%" + filter.getName() + "%");
            wrapper = criteriaBuilder.and(wrapper, predicate);
        }
        return wrapper;
    }
}
