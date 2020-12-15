package com.proterra.datadictionary.repository;

import com.proterra.datadictionary.domain.EditDetails;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called editDetailsRepository
// CRUD refers Create, Read, Update, Delete
public interface EditDetailsRepository extends CrudRepository<EditDetails, Integer> {

}
