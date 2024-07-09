package com.aykutsert.repository;

import com.aykutsert.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
//Mongo repoyu extend ettiğin için spring 2.0 ile beraber annotation'a gerek yok.
// UserRepo singleton instance olarak Dependency I. Container'a ekliyor.
