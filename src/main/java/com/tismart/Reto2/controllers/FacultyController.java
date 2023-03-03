package com.tismart.Reto2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tismart.Reto2.models.Faculty;
import com.tismart.Reto2.services.IFacultyService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
	
	@Autowired
	private IFacultyService facultyService;
	
	@GetMapping("/list")
	public String findAllFaculties(){
		return "faculty/index";
	}
	
	@GetMapping("/new")
	public String newFaculty() {
		return "faculty/newFaculty";
	}
	
	@PostMapping("/save")
	public String saveFaculty(@Valid @ModelAttribute("faculty") Faculty faculty, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "faculty/newFaculty";
		}
		
		try {
			facultyService.saveFaculty(faculty);
			attributes.addFlashAttribute("message", "Facultad creada corectamente");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "faculty/newFaculty";
		}
		
		return "redirect:/faculty/list";
	}
	
	@GetMapping("edit/{id}")
	public String editFaculty(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
		
		try {
			Faculty faculty = facultyService.findFacultyById(id);
			
			if(faculty != null) {
				model.addAttribute("faculty", faculty);
				return "faculty/editFaculty"; 
			}
			
			attributes.addFlashAttribute("message_error", "La facultad no fue encontrada");
			return "redirect:/faculty/list";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el regitro");
			return "redirect:/faculty/list";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteFaculty(@PathVariable("id") int id, RedirectAttributes attributes) {
		
		try {
			
			Faculty faculty = facultyService.findFacultyById(id);
			
			if(faculty != null) {
				facultyService.deleteFacultyById(id);
				attributes.addFlashAttribute("message", "El registro fue eliminado");
				return "redirect:/faculty/list"; 
			}
			
			attributes.addFlashAttribute("message_error", "La facultad no está registrada");
			return "redirect:/faculty/list";			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el regitro");
			return "redirect:/faculty/list";
		}
	}
	
	@ModelAttribute
	public void setGeneralFacultyData(Model model) {
		Faculty faculty =  new Faculty();
		model.addAttribute("faculty", faculty);
		model.addAttribute("faculties", facultyService.findAllFaculties());
	}

}
