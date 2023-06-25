package com.purnabhu.sales.repository;


import com.purnabhu.sales.entities.project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface projectRepository extends JpaRepository<project,Integer> {


    public project findByPname(String projectName);


}
