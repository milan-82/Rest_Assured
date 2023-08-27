package com.cydeo.POJO.DriverAlonso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MRData {

    private String limit;

    private String total;

    private DriverTable DriverTable;
}
