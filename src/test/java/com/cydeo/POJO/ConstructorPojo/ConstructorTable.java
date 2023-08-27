package com.cydeo.POJO.ConstructorPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ConstructorTable {

    private String constructorStandings;

    private Constructors constructors;

}
