package com.tismart.Reto2.services;

import java.util.List;

import org.springframework.data.domain.Example;

import com.tismart.Reto2.models.School;

public interface ISchoolService {
	
	List<School> findAllSchools();
	void saveSchool(School condition);
	School findSchoolById(int idCondition);
	void deleteSchoolById(int idCondition);
	//Page<School> findAllSchools(Pageable page);
	long count();
	List<School> findSchoolByExample(Example<School> example);
	long findSchoolAndCountByExample(Example<School> example);
}
