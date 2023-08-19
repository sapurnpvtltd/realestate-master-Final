package com.purnabhu.sales.services;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.purnabhu.sales.entities.property;
import com.purnabhu.sales.repository.propertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class propertyService {

    @Autowired
    propertyRepository proprepo;

    public List<property> getAllproperty() {

        return proprepo.findAll();

    }

    public property saveProperty(property prop) {

        return proprepo.save(prop);
    }

    public void deleteProperty(int propertyId) {

        proprepo.deleteById(propertyId);
    }

    public List<property> getPropertydetailsByName(String projectname) {

        return proprepo.findBypropTitle(projectname);
    }

    public Optional<property> getPropertydetailsById(int pid) {

        return proprepo.findById(pid);
    }

    public property getJson(String user, List<MultipartFile> file) throws JsonProcessingException {

        property userJson = new property();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userJson = objectMapper.readValue(user, property.class);
        } catch (IOException e) {

        }

        int fileCount = file.size();
        userJson.setImagecount(fileCount);

        return userJson;


    }
}
