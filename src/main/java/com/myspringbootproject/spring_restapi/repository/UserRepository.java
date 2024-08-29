package com.myspringbootproject.spring_restapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.myspringbootproject.spring_restapi.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
