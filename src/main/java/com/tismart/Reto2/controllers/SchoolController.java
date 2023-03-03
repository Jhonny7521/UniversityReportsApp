package com.tismart.Reto2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
import com.tismart.Reto2.models.School;
import com.tismart.Reto2.services.IFacultyService;
import com.tismart.Reto2.services.ISchoolService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private IFacultyService facultyService;
	
	@RequestMapping("/")
	public String home(){		
		return "index";
	}
	
	@GetMapping("/list")
	public String getAllSchools() {
		return "school/index";
	}
	
	@GetMapping("/new")
	public String newSchool(Model model) {
		
		return "school/newSchool";
	}
	
	@PostMapping("/save")
	public String saveSchool(@Valid @ModelAttribute("school") School school, BindingResult result, RedirectAttributes attributes) {
		System.out.println(school);
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "school/newSchool";
		}
		
		try {
			schoolService.saveSchool(school);
			attributes.addFlashAttribute("message", "Se agregó a la escuela correctamente");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "school/newSchool";
		}
		
		return "redirect:/school/list";  
	}
	
	@GetMapping("/edit/{id}")
	public String editSchool(@PathVariable("id") int id ,Model model, RedirectAttributes attributes) {
		
		try {
			
			School school = schoolService.findSchoolById(id);
			
			if(school != null) {
				model.addAttribute("school", school);
				return "school/editSchool"; 
			}
			
			attributes.addFlashAttribute("message_error", "Escuela no encontrada");
			return "redirect:/school/list";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrio un error con el registro");
			return "redirect:/school/list";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteSchool(@PathVariable("id") int id , RedirectAttributes attributes) {
		
		try {
			
			School school = schoolService.findSchoolById(id);
			
			if(school != null) {
				schoolService.deleteSchoolById(id);
				attributes.addFlashAttribute("message", "El registro fue eliminado");
				return "redirect:/school/list";
			}
			
			attributes.addFlashAttribute("message_error", "La escuela no está registrada");
			return "redirect:/school/list";
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con el registro");
			return "redirect:/school/list";
		}
	}
	
	@GetMapping("/search")
	public String schoolSearch(@ModelAttribute("schoolSearch") School school, Model model, RedirectAttributes attributes) {
		
		try {
			ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
			
			Example<School> example = Example.of(school, matcher);
			List<School> hospitalList = schoolService.findSchoolByExample(example);
			long count = schoolService.findSchoolAndCountByExample(example);
			
			if (hospitalList.isEmpty()) {
				
				attributes.addFlashAttribute("message_error", "No se encontraron registros que conincidan con el campo de búsqueda");
				return "redirect:/school/list";
			}
			
			String numberOfSchools = "Existen " + String.valueOf(count) + " escuela(s)";
			model.addAttribute("schools", hospitalList);
			model.addAttribute("numberOfSchools", numberOfSchools);
			return "school/index";
			
		} catch (Exception e) {
			attributes.addFlashAttribute("message_error", "Ocurrió un error con la búsqueda");
			return "school/index";
		}
		
	}
	
	@GetMapping("/report1")
	public String getReport1() {
		return "report/report1";
	}
	
	@GetMapping("/report2")
	public String getReport2() {
		return "report/report2";
	}
	
	
	@ModelAttribute
	public void setGenerateSchoolData(Model model) {
		School school = new School();
		List<Faculty> faculties = facultyService.findAllFaculties();
		Long count = schoolService.count();
		String numberOfSchools = "Existen " + String.valueOf(count) + " escuela(s)";
		model.addAttribute("school", school);
		model.addAttribute("schools", schoolService.findAllSchools());
		model.addAttribute("faculties", faculties);
		model.addAttribute("numberOfSchools", numberOfSchools);
	}

}
