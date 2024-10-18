package com.sijibomiaol.the_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequest {

    private String firstName;

    private String lastName;

    private String otherName;

    private int age;

    private String email;

    private String phone;

    private String address;

    private String stateofOrigin;

}
