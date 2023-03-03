package com.tismart.Reto2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tismart.Reto2.models.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer>{

}
