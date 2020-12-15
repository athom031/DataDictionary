package com.proterra.datadictionary.service;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.proterra.datadictionary.domain.EditDetails;
import com.proterra.datadictionary.domain.Field;
import com.proterra.datadictionary.repository.EditDetailsRepository;
import com.proterra.datadictionary.repository.FieldRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Iterator;

@Service
public class DataDictionaryService {

    @Autowired
    private EditDetailsRepository editDetailsRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public Iterable<Field> list() {
        return fieldRepository.findAll();
    }

    public Iterable<EditDetails> edited() {
        return editDetailsRepository.findAll();
    }

    public String save(String user) throws IOException {
        // won't work if executing project from jar
        //File resource = new ClassPathResource("/json/fields.json").getFile();
        //String text = new String(Files.readAllBytes(resource.toPath()));

        String text = GuavaWrapInputStream();

        JSONObject dataDictionaryToUpload = new JSONObject(text);
        Iterator<String> keys = dataDictionaryToUpload.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            if(dataDictionaryToUpload.get(key) instanceof JSONObject) {
                JSONObject field = (JSONObject) dataDictionaryToUpload.get(key);

                Field f = fieldObjectHelper(field);
                fieldRepository.save(f);
            }
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        EditDetails ed = editDetailsObjectHelper(user, timestamp);
        editDetailsRepository.save(ed);

        return "Saved json data by " + user + " at " + timestamp;
    }

    private Field fieldObjectHelper(JSONObject field) {
        Field f = new Field();

        f.setFieldId(field.getString("fieldId"));
        f.setDisplayName(field.getString("displayName"));
        f.setDescription(field.getString("description"));
        f.setFormula(field.getString("formula"));
        f.setUnit(field.getString("unit"));
        f.setAggregation(field.getString("aggregation"));
        f.setValidation(field.getString("validation"));
        f.setPage(field.getString("page"));
        f.setModule(field.getString("module"));
        f.setReference("");

        return f;
    }

    private EditDetails editDetailsObjectHelper(String user, Timestamp timestamp) {
        EditDetails ed = new EditDetails();

        ed.setId(1);
        ed.setUser(user);
        ed.setTimestamp(timestamp);

        return ed;
    }

    private String GuavaWrapInputStream() throws IOException {
        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return new ClassPathResource("/json/fields.json").getInputStream();
            }
        };

        String text = byteSource.asCharSource(Charsets.UTF_8).read();

        return text;
    }
}
