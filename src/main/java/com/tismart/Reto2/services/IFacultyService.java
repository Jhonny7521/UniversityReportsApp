package com.tismart.Reto2.services;

import java.util.List;

import com.tismart.Reto2.models.Faculty;

public interface IFacultyService {
	
	List<Faculty> findAllFaculties();
	void saveFaculty(Faculty faculty);
	Faculty findFacultyById(int idFaculty);
	void deleteFacultyById(int idFaculty);
}
