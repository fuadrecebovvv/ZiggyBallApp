package com.example.ziggyballapp.controller;

import com.example.ziggyballapp.model.OtpVerificationDto;
import com.example.ziggyballapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/otp-verification")
    public void verifyOtp(@RequestBody OtpVerificationDto otpVerificationDto){
        userService.verifyOtp(otpVerificationDto);
    }
}
