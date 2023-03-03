package com.tismart.Reto2.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.Reto2.models.Faculty;
import com.tismart.Reto2.repositories.FacultyRepository;
import com.tismart.Reto2.services.IFacultyService;

@Service
public class FacultyServiceImpl implements IFacultyService{
	
	
	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public List<Faculty> findAllFaculties() {
		return facultyRepository.findAll();
	}

	@Override
	public void saveFaculty(Faculty faculty) {
		facultyRepository.save(faculty);
	}

	@Override
	public Faculty findFacultyById(int idFaculty) {
		Optional<Faculty> optional = facultyRepository.findById(idFaculty);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void deleteFacultyById(int idFaculty) {
		facultyRepository.deleteById(idFaculty);
	}

	

}
