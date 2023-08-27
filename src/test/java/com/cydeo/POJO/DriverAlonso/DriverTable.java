package com.cydeo.POJO.DriverAlonso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverTable {

    private String driverId;

    private com.cydeo.POJO.DriverAlonso.Drivers Drivers;


}
