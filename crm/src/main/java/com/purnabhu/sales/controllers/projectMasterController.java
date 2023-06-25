package com.purnabhu.sales.controllers;


import com.purnabhu.sales.entities.project;
import com.purnabhu.sales.services.projectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class projectMasterController {

    @Autowired
    projectService projservice;

    @GetMapping("/getAllProjectDetails")
    public List<project> fetchProjectDetails(){

        return projservice.getAllprojects();
    }

    @GetMapping("/getProjectByName/{name}")
    public project getProjectByName(@PathVariable(value="name") String ProjectName){

        return projservice.getProjectdetailsByName(ProjectName);

    }

    @PutMapping("/updateProjectDetails/{id}")
    public project modifyProjectDetails(@PathVariable (value="id") int id,@RequestBody project proj)
    {
        Optional<project> project = projservice.getProjectdetailsById(id);
        project.get().setParea(proj.getParea());
        project.get().setPdesc(proj.getPdesc());
        project.get().setPlocation(proj.getPlocation());
        project.get().setPname(proj.getPname());
        project.get().setPdate(proj.getPdate());
        project updatedproj =projservice.saveProject(project.get());
        return updatedproj;


    }

    @PostMapping("/addNewProject")
    public project addProjectDetails(@RequestBody  project proj){

        return projservice.saveProject(proj);
    }

    @DeleteMapping("/deleteProjectById")
    public void deleteProject(@RequestParam int projectId){

         projservice.deleteProject(projectId);
    }

}
