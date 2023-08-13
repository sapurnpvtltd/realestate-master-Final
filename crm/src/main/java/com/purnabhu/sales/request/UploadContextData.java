package com.purnabhu.sales.request;

import com.purnabhu.sales.entities.property;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadContextData {

    private MultipartFile image;
    private property prop;
}
