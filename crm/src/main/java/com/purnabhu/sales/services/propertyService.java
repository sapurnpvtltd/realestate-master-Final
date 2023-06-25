package com.purnabhu.sales.services;





import com.purnabhu.sales.entities.property;
import com.purnabhu.sales.repository.propertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class propertyService {

    @Autowired
    propertyRepository proprepo;

    public List<property> getAllproperty(){

        return proprepo.findAll();

    }

    public property saveProperty(property prop){

        return  proprepo.save(prop);
    }

    public void deleteProperty(int propertyId){

        proprepo.deleteById(propertyId);
    }

    public property getPropertydetailsByName(String projectname){

        return proprepo.findBypropTitle(projectname);
    }
    public Optional<property> getPropertydetailsById(int pid){

        return proprepo.findById(pid);
    }



}
