package com.purnabhu.sales.response;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntityObject {
    private int responseCode;
    private String responseMessage;
    private Map<String,Object> dataMap;

}
