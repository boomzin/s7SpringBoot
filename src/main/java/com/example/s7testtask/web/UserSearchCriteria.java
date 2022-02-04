package com.example.s7testtask.web;

import com.example.s7testtask.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserSearchCriteria implements Specification<User> {

    private List<SearchCriteria> searchCriteriaList;

    public UserSearchCriteria() {
        this.searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        searchCriteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(@NonNull Root<User> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : searchCriteriaList) {
            if (criteria.getOperation().equalsIgnoreCase(">")) {
                predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equalsIgnoreCase("<")) {
                predicates.add(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equalsIgnoreCase(":")) {
                predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equalsIgnoreCase("~") && root.get(criteria.getKey()).getJavaType() == String.class) {
                predicates.add(criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
