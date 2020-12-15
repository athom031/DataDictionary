package com.proterra.datadictionary.repository;

import com.proterra.datadictionary.domain.Field;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called fieldRepository
// CRUD refers Create, Read, Update, Delete

public interface FieldRepository extends CrudRepository<Field, String> {

}
