package com.proterra.datadictionary;

import com.proterra.datadictionary.domain.Field;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface FieldRepository extends CrudRepository<Field, String> {

}
