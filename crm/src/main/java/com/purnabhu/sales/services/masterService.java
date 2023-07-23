package com.purnabhu.sales.services;
import com.purnabhu.sales.entities.Amenities;
import com.purnabhu.sales.entities.Source;
import com.purnabhu.sales.repository.AmenitiesRepository;
import com.purnabhu.sales.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class masterService {


 @Autowired
AmenitiesRepository amenitiesrepo;

 @Autowired
SourceRepository sourcerepo;


public List<Amenities> getAmenities(){


    return amenitiesrepo.findAll();

}

    public List<Source> getSourceDetails(){


        return sourcerepo.findAll();

    }

    public Amenities saveAmenities(Amenities amenity){


        return amenitiesrepo.save(amenity);

    }
    public Source saveSourceDetails(Source source){


        return sourcerepo.save(source);

    }
    public void deleteAmenities(Amenities amenity){


         amenitiesrepo.delete(amenity);

    }
    public void deleteSourceDetails(Source source){


         sourcerepo.delete(source);

    }


}




























