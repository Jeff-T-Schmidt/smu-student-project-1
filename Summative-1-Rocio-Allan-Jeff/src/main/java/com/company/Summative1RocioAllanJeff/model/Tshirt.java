package com.company.Summative1RocioAllanJeff.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Tshirt {
    @NotEmpty(message = "You must supply a value for artist")
    private String artist;
    @NotEmpty(message = "You must supply a value for album")
    private String album;
    @NotEmpty(message = "You must supply a value for year")
    @Size(min = 4, max = 4, message = "Year must be exactly 4 digits")
    private String year;
    private int id;
}
