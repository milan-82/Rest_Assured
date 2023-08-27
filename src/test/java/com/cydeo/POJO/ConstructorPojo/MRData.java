package com.cydeo.POJO.ConstructorPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MRData {

    private String total;

    private String limit;

    private ConstructorTable constructorTable;

}
