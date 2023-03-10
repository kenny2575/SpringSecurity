package com.example.springsecurity.repository;

import com.example.springsecurity.entity.BasePerson;
import com.example.springsecurity.entity.Persons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Persons, BasePerson> {
   @Query("select a from Persons a where a.city = :city")
   List<Persons> findPersonsByCityOfLiving(@Param(value = "city") String city);

   @Query("select p from Persons p where p.basePerson.age<:age order by p.basePerson.age asc ")
   List<Persons> findPersonsByAgeAsc(int age);

   @Query("select p.basePerson from Persons p where p.basePerson.age<:age order by p.basePerson.age desc ")
   List<BasePerson> findBasePersonByAge(int age);

   @Query("select p.phoneNumber from Persons p where p.basePerson.age<:age")
   List<String> findPhoneNumberByAge(int age);

   @Query("select p from Persons p where p.basePerson.name=:name and p.basePerson.surname=:surname")
   List<Optional<Persons>> findPersonsByFio(String name, String surname);
}