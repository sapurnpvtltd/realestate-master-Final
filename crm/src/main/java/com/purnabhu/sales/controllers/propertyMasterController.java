package com.purnabhu.sales.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.purnabhu.sales.entities.Amenities;
import com.purnabhu.sales.entities.project;
import com.purnabhu.sales.entities.property;
import com.purnabhu.sales.response.ResponseEntityObject;
import com.purnabhu.sales.services.PropertyImageService;
import com.purnabhu.sales.services.masterService;
import com.purnabhu.sales.services.projectService;
import com.purnabhu.sales.services.propertyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/property")
public class propertyMasterController {

    @Autowired
    private PropertyImageService productImageService;

    @Autowired
    propertyService propservice;

    @Autowired
    masterService masterservice;

    @Autowired
    projectService projservice;

    @GetMapping("/getAllPropertyDetails")
    public List<property> fetchPropertyDetails(){

        return propservice.getAllproperty();
    }

    @GetMapping("/getPropertyByName/{name}")
    public List<property> getPropertyByName(@PathVariable(value="name") String ProprtyName){

        return propservice.getPropertydetailsByName(ProprtyName);

    }

    @PutMapping("/updatePropertyDetails/{id}")
    public property modifyPropertyDetails(@PathVariable (value="id") int id,@RequestBody property proj,@RequestParam("image") MultipartFile file[]) throws IOException {
        Optional<property> project = propservice.getPropertydetailsById(id);
        project.get().setAgentContact(proj.getAgentContact());
        project.get().setPropAge(proj.getPropAge());
        project.get().setPaymentMode(proj.getPaymentMode());
        project.get().setLoanAvailibilty(proj.getLoanAvailibilty());

        project.get().setPropSize(proj.getPropSize());
        project.get().setPropType(proj.getPropType());
        project.get().setPropDescription(proj.getPropDescription());
        project.get().setAmenities(proj.getAmenities());
        project.get().setPropTitle(proj.getPropTitle());
        project.get().setRefAgent(proj.getRefAgent());

        property updatedproj =propservice.saveProperty(project.get());


            List<String> fileNames = new ArrayList<>();

            Arrays.asList(file).stream().forEach(files -> {
                try {
                    productImageService.uploadImage(files,proj);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileNames.add(files.getOriginalFilename());
            });


        return updatedproj;


    }



    @PostMapping(value = "/addNewProperty", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> addPropertyDetails(@RequestPart("propertydetails") String propertydetails, @RequestPart("files") List<MultipartFile> files,@RequestPart("propjectid") String propjectid, @RequestPart ("amenities") String amenities) throws JsonProcessingException {
        property prop = propservice.getJson(propertydetails, files);
        List<Integer> amenitiesarr=null;
        if(!StringUtils.isEmpty(amenities)) {
            String[] amenitiesarrys = amenities.split(",");
            int[] values = Arrays.stream(amenitiesarrys)
                    .mapToInt(Integer::parseInt)
                    .toArray();
             amenitiesarr = Arrays.stream(values)     // IntStream
                    .boxed()        // Stream<Integer>
                    .collect(Collectors.toList());
        }
       Optional<project> proj= projservice.getProjectdetailsById(Integer.parseInt(propjectid));
       if(proj.isEmpty()){
              return ResponseEntityObject.generateResponse("Project does not exists with this Title!", HttpStatus.NOT_ACCEPTABLE, prop);
       }

       List<Amenities> amenitieList=masterservice.getAmenityByIds(amenitiesarr);
        if(amenitieList.isEmpty()){
            return ResponseEntityObject.generateResponse("Amenities does not exists with this Title!", HttpStatus.NOT_ACCEPTABLE, prop);
        }
        prop.setProjectassociatted(proj.get());
        prop.setAmenities(amenitieList);
       Optional<List<property>> propdetails = Optional.ofNullable(propservice.getPropertydetailsByName(prop.getPropTitle()));
       if(propdetails.get().size()>0){
           return ResponseEntityObject.generateResponse("Property already exists with this Title!", HttpStatus.OK, prop);

       }
        property result= propservice.saveProperty(prop);

        List<String> fileNames = new ArrayList<>();

        files.stream().forEach(f -> {
            try {
                productImageService.uploadImage(f,prop);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileNames.add(f.getOriginalFilename());
        });
        return ResponseEntityObject.generateResponse("Successfully added Property!", HttpStatus.OK, result);

    }

    @DeleteMapping("/deletePropertyById")
    public ResponseEntity<Object> deleteProperty(@RequestParam int propertyId){
        propservice.deleteProperty(propertyId);
        return ResponseEntityObject.generateResponse("Successfully added Property!", HttpStatus.OK, null);
    }

    @DeleteMapping("/deletePropertyImagesById")
    public ResponseEntity<Object> deletePropertyImages(@RequestParam long propertyImageId){
        productImageService.deletePropertyImages(propertyImageId);
        return ResponseEntityObject.generateResponse("Successfully added Property!", HttpStatus.OK, null);

    }

    @PutMapping("/addMorePropertyImages")
    public ResponseEntity<Object> updatePropertyImages(@RequestParam("propertyid") int propertyid, @RequestParam("files") List<MultipartFile> files)
    {
        List<String> fileNames = new ArrayList<>();

        files.stream().forEach(f -> {
            try {
                productImageService.uploadMoreImages(f,propertyid);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileNames.add(f.getOriginalFilename());
        });
        return ResponseEntityObject.generateResponse("Successfully added Property!", HttpStatus.OK, null);
    }

}

