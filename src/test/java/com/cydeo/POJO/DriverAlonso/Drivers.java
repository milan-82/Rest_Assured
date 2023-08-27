package com.cydeo.POJO.DriverAlonso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Drivers {

    private String driverId;
    private String givenName;
    private String familyName;
    private String nationality;
}
