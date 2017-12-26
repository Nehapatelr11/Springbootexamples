package com.example.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.data.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {

}
