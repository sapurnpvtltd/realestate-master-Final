package com.purnabhu.sales.controllers;


import com.purnabhu.sales.entities.property;
import com.purnabhu.sales.services.propertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class propertyMasterController {


    @Autowired
    propertyService propservice;

    @GetMapping("/getAllPropertyDetails")
    public List<property> fetchProjectDetails(){

        return propservice.getAllproperty();
    }

    @GetMapping("/getPropertyByName/{name}")
    public property getProjectByName(@PathVariable(value="name") String ProprtyName){

        return propservice.getPropertydetailsByName(ProprtyName);

    }

    @PutMapping("/updatePropertyDetails/{id}")
    public property modifyProjectDetails(@PathVariable (value="id") int id,@RequestBody property proj)
    {
        Optional<property> project = propservice.getPropertydetailsById(id);
        project.get().setAgentContact(proj.getAgentContact());
        project.get().setPropAge(proj.getPropAge());
        project.get().setPaymentMode(proj.getPaymentMode());
        project.get().setLoanAvailibilty(proj.getLoanAvailibilty());

        project.get().setPropImage(proj.getPropImage());
        project.get().setPropSize(proj.getPropSize());
        project.get().setPropType(proj.getPropType());
        project.get().setPropDescription(proj.getPropDescription());

        project.get().setPropAmenities(proj.getPropAmenities());
        project.get().setPropTitle(proj.getPropTitle());
        project.get().setRefAgent(proj.getRefAgent());

        property updatedproj =propservice.saveProperty(project.get());
        return updatedproj;


    }

    @PostMapping("/addNewProperty")
    public property addProjectDetails(@RequestBody  property proj){

        return propservice.saveProperty(proj);
    }

    @DeleteMapping("/deletePropertyById")
    public void deleteProject(@RequestParam int propertyId){

        propservice.deleteProperty(propertyId);
    }





}

