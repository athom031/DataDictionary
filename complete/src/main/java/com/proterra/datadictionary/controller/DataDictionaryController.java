package com.proterra.datadictionary.controller;

import com.proterra.datadictionary.domain.EditDetails;
import com.proterra.datadictionary.domain.Field;
import com.proterra.datadictionary.repository.EditDetailsRepository;
import com.proterra.datadictionary.repository.FieldRepository;
import com.proterra.datadictionary.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/fields") // This means URL's start with /demo (after Application path)
public class DataDictionaryController {

	private DataDictionaryService dataDictionaryService;

	public DataDictionaryController(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}

	@GetMapping("/list")
	public Iterable<Field> list() {
		return dataDictionaryService.list();
	}

	@GetMapping("/edited")
	public Iterable<EditDetails> edited() {
		return dataDictionaryService.edited();
	}

	@PostMapping("/save")
	public String save(String user) throws IOException {
		return dataDictionaryService.save(user);
	}

	/*
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String fieldId,
											@RequestParam String displayName,
											@RequestParam String description,
											@RequestParam String formula,
											@RequestParam String unit,
											@RequestParam String aggregation,
											@RequestParam String validation,
											@RequestParam String page,
											@RequestParam String module,
											@RequestParam String reference) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Field f = new Field();
		f.setFieldId(fieldId);
		f.setDisplayName(displayName);
		f.setDescription(description);
		f.setFormula(formula);
		f.setUnit(unit);
		f.setAggregation(aggregation);
		f.setValidation(validation);
		f.setPage(page);
		f.setModule(module);
		f.setReference(reference);

		fieldRepository.save(f);

		return "Saved";
	}

	*/
}
