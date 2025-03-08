package com.business.coffeshop.controller;

import com.business.coffeshop.dto.AuthRequest;
import com.business.coffeshop.dto.RegisterAccountDto;
import com.business.coffeshop.service.AccountService;
import com.business.coffeshop.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("business-coffeeshop/account")
@RequiredArgsConstructor
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("customer-login")
    public String showLoginForm(Model model) {
        model.addAttribute("authRequest", new AuthRequest());
        return "account/customer/customer-login";
    }

    @PostMapping("customer-login")
    public String login(@Valid @ModelAttribute("authRequest") AuthRequest authRequest, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getMsisdn(), authRequest.getPassword())
            );
            String token = jwtUtil.generateToken(authentication);
            model.addAttribute("jwtToken", token);
            model.addAttribute("authRequest", authRequest);

            return "redirect:/business-coffeeshop/product/customer-product-list";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials!");
            return "account/customer/customer-login";
        }
    }

    @GetMapping("/customer-register")
    public String showCustomerRegisterForm(Model model) {
        model.addAttribute("registerAccount", new RegisterAccountDto());
        return "account/customer/customer-register";
    }

    @PostMapping("/customer-register")
    public String registerCustomerAccount(@Valid @ModelAttribute("registerAccount") RegisterAccountDto registerAccount, Model model) {
        model.addAttribute("registerAccount", registerAccount);
        try {
            accountService.registerCustomerAccount(registerAccount);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            return "redirect:customer-register";
        }
        return "redirect:customer-login";
    }

    //==========================BACKOFFICE========================================
    @GetMapping("/admin-account-list")
    public String showAccountListForm(Model model) {
        model.addAttribute("accounts", accountService.getAllAccount());
        return "account/backoffice/admin-account-list";
    }

    @GetMapping("/admin-account-detail/{id}")
    public String showAccountDetailForm(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getAccountDtoById(id));
        return "account/backoffice/admin-account-detail";
    }

//    @GetMapping("/admin-account-add")
//    public String showAccountAddForm(@Valid @ModelAttribute("registerAccount") RegisterAccountDto registerAccount, Model model) {
//        model.addAttribute("account", accountService.getAccountDtoById(id));
//        return "account/backoffice/admin-account-detail";
//    }

}
