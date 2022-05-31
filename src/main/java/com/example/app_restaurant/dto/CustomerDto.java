package com.example.app_restaurant.dto;

import com.example.app_restaurant.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Size(min = 3, max = 128, message = "Name should be between 3 and 128 characters")
    private String email;

    @NotBlank
    @Size(min = 3, max = 128, message = "Password should be between 3 and 128 characters")
    private String password;


    public static CustomerDto from(Customer customer) {
        return builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
