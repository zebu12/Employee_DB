package com.zebu.employee.dao;

import com.zebu.employee.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean-pierre Salum
 */

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {


    private final EntityManager entityManager;

    public List<Employee> findAllBySimpleQuery(
            String firstName,
            String lastName,
            String email
    ){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        // select *  from employee
        Root<Employee> root = cq.from(Employee.class);

        // prepare WHERE clause
        // WHERE firstName like '%ali%'
        Predicate firstNamePredicate = cb.like(root.get("firstName"), "%" + firstName + "%");
        Predicate emailPredicate = cb.like(root.get("email"), "%" + email + "%");
        Predicate lastNamePredicate = cb.equal(root.get("lastName"), lastName);
        Predicate firstNameorLastNamePredicate = cb.or(
                firstNamePredicate,
                lastNamePredicate
        );
        // => final query ==> select * from employee where firstName like '%ali%'
        // or lastName like '%ali&' and email like '%email%'
        var andEmailPredicate = cb.and(firstNameorLastNamePredicate, emailPredicate);
        cq.where(andEmailPredicate);
        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Employee> findAllByCriteriaQuery(
            SearchRequest searchRequest
    ){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        // select from employee
        Root<Employee> root = cq.from(Employee.class);
        if(searchRequest.getFirstName() != null){
            Predicate firstNamePredicate = cb.like(
                    root.get("firstName"), "%" + searchRequest.getFirstName() + "%"
            );
            predicates.add(firstNamePredicate);
        }
        if(searchRequest.getLastName() != null){
            Predicate lastNamePredicate = cb.like(root.get("lastName"),
                    "%" + searchRequest.getLastName() + "%");

            predicates.add(lastNamePredicate);
        }
        if(searchRequest.getEmail() != null){
            Predicate emailPredicate = cb.like(
                    root.get("email"), "%" + searchRequest.getEmail() + "%"
            );
            predicates.add(emailPredicate);
        }

        cq.where(cb.or(predicates.toArray(new Predicate[0])));

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();


    }
}
