package com.example.app_restaurant.controller;

import com.example.app_restaurant.dto.CustomerDto;
import com.example.app_restaurant.sevice.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/registration")
    public String getCustomerRegistration(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new CustomerDto());
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewCustomer(@Valid CustomerDto customerDto, BindingResult bindingResult,
                                 RedirectAttributes attributes) {
        if (customerService.checkUser(customerDto.getEmail())) {
            attributes.addFlashAttribute("dto", customerDto);
            return "redirect:/registration";
        }
        if (bindingResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            return "redirect:/registration";
        }
        customerService.registration(customerDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }
}

