package com.moaaz.resturant.repository;

import com.moaaz.resturant.model.Person;


import com.moaaz.resturant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    public Optional<Person> findByEmailAndPassword(String email, String password);
    public List<User> findAllByRole(String role);


}
