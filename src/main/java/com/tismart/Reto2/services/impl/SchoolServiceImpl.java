package com.tismart.Reto2.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tismart.Reto2.models.School;
import com.tismart.Reto2.repositories.SchoolRepository;
import com.tismart.Reto2.services.ISchoolService;

@Service
public class SchoolServiceImpl implements ISchoolService{

	@Autowired
	private SchoolRepository schoolRepository;
	
	@Override
	public List<School> findAllSchools() {
		return schoolRepository.findAll();
	}

	@Override
	public void saveSchool(School condition) {
		schoolRepository.save(condition);
	}

	@Override
	public School findSchoolById(int idCondition) {
		Optional<School> optional = schoolRepository.findById(idCondition);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void deleteSchoolById(int idCondition) {
		schoolRepository.deleteById(idCondition);
	}

	@Override
	public long count() {
		return schoolRepository.count();
	}

	@Override
	public List<School> findSchoolByExample(Example<School> example) {
		return schoolRepository.findAll(example);
	}

	@Override
	public long findSchoolAndCountByExample(Example<School> example) {
		return schoolRepository.count(example);
	}


}
