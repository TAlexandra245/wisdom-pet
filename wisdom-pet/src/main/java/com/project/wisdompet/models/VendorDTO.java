package com.project.wisdompet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VendorDTO {
    private Integer vendorId;
    private String name;
    private String contact;
    private String phoneNumber;
    private String emailAddress;
    private String address;
}
