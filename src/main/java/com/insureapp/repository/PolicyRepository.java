package com.insureapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insureapp.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy,Long>{

}
