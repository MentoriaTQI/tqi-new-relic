package br.com.tqi.dio.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostalCodeDTO implements Serializable {

    private String state;
    private String city;
    private String district;
    private String postalCode;
    private String address;

}
