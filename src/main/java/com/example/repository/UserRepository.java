package com.example.repository;

import com.example.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <UserEntity, Integer> {


     UserEntity findByName(String heej);
    /* UserEntity findByNameAndId(String ygeheg, Integer id); */
}
