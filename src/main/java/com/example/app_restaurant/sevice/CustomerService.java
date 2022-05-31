package com.example.app_restaurant.sevice;

import com.example.app_restaurant.dto.CustomerDto;
import com.example.app_restaurant.entity.Customer;
import com.example.app_restaurant.exception.UserAlreadyRegisteredException;
import com.example.app_restaurant.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }

    public boolean checkUser(String email) {
        return customerRepository.existsByEmail(email);
    }

    public CustomerDto registration(CustomerDto customerDto) {
        if (checkUser(customerDto.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }
        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .password(encoder.encode(customerDto.getPassword()))
                .build();
        customerRepository.save(customer);
        return CustomerDto.from(customer);
    }

}
