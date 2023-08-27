package com.cydeo.POJO.ConstructorPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class Constructors {

    private List<Constructors> constructors;

    private String constructorId;
    private String url;
    private String name;
    private String nationality;

}
