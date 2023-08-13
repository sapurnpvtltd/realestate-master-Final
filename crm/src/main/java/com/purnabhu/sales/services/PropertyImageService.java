package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.PropertyImages;
import com.purnabhu.sales.entities.property;
import com.purnabhu.sales.repository.PropertyImageRepository;
import com.purnabhu.sales.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PropertyImageService {

    @Autowired
    private PropertyImageRepository imageRepo;

    public PropertyImages uploadImage(MultipartFile file, property prop) throws IOException {
        PropertyImages pImage = new PropertyImages();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImageData(ImageUtil.compressImage(file.getBytes()));
        pImage.setProperty(prop);
        return imageRepo.save(pImage);
    }
    public PropertyImages uploadMoreImages(MultipartFile file, int propid) throws IOException {
        property p=new property();
        p.setPropid(propid);
        PropertyImages pImage = new PropertyImages();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImageData(ImageUtil.compressImage(file.getBytes()));
        pImage.setProperty(p);
        return imageRepo.save(pImage);
    }

    public byte[] downloadImage(String fileName){
        Optional<PropertyImages> imageData = imageRepo.findByName(fileName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }

    public void deletePropertyImages(long imageId){

        imageRepo.deleteById(imageId);
    }

}
