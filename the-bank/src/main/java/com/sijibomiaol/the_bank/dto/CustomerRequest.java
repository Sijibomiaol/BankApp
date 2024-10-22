package com.sijibomiaol.the_bank.dto;

import com.sijibomiaol.the_bank.ENUM.Role;
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

    private Role role;

    private String email;

    private String phone;

    private String password;

    private String address;

    private String stateofOrigin;

}
