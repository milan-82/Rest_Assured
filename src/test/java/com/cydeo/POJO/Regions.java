package com.cydeo.POJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data()
@JsonIgnoreProperties(ignoreUnknown = true)
public class Regions {

    private List<Regions> items;

    @JsonProperty("region_id")
    private int regionId;

    @JsonProperty("region_name")

    private String regionName;

    private int count;



}
