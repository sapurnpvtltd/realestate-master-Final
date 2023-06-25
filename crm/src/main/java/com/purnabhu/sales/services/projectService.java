package com.purnabhu.sales.services;



import com.purnabhu.sales.entities.project;
import com.purnabhu.sales.repository.projectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class projectService {

      @Autowired
      projectRepository projrepo;

      public List<project> getAllprojects(){

       return projrepo.findAll();

      }

      public project saveProject(project proj){

          return  projrepo.save(proj);
      }

      public void deleteProject(int projectId){

           projrepo.deleteById(projectId);
      }

    public  project getProjectdetailsByName(String projectname){

         return projrepo.findByPname(projectname);
    }
    public Optional<project> getProjectdetailsById(int pid){

        return projrepo.findById(pid);
    }
    /*public project updateProjectDetails(project project){
          return  projrepo.save(project);
    }*/

}
