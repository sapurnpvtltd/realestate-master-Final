package com.purnabhu.sales.controllers;


import com.purnabhu.sales.entities.Amenities;
import com.purnabhu.sales.entities.Source;
import com.purnabhu.sales.services.masterService;
import jakarta.persistence.criteria.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class MasterConntroller {

@Autowired
masterService masterservice;



    @GetMapping("/getAmenities")
     public List<Amenities> getAllAmenities(){
        return  masterservice.getAmenities();
    }
     @GetMapping("/getSources")
    public List<Source> getAllSources(){
         return   masterservice.getSourceDetails();
    }
    @PostMapping("/addAmenities")
    public Amenities addAmenities(@RequestBody  Amenities amenities){
        return   masterservice.saveAmenities(amenities);
    }
    @PostMapping("/addSources")
    public Source addSources(@RequestBody Source sources){
        return   masterservice.saveSourceDetails(sources);
    }
    @DeleteMapping("/deleteAmenities")
    public void deleteAmenities(@RequestBody Amenities amenities){
           masterservice.deleteAmenities(amenities);
    }
    @DeleteMapping("/deleteSources")
    public void deleteSources(@RequestBody Source source){
           masterservice.deleteSourceDetails(source);
    }
}
