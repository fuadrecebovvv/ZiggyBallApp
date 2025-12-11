package com.example.ziggyballapp.service;

import com.example.ziggyballapp.dao.entity.OtpVerificationEntity;
import com.example.ziggyballapp.dao.repository.OtpVerificationRepository;
import com.example.ziggyballapp.dao.repository.UserRepository;
import com.example.ziggyballapp.mapper.UserMapper;
import com.example.ziggyballapp.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final OtpVerificationRepository otpVerificationRepository;

    public List<UserDto> getUsers() {
        log.info("UserService.getUsers.start");
        var users = userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
        log.info("UserService.getUsers.end");
        return users;
    }

    public void addUser(UserDto userDto){
        log.info("UserService.addUser.start");
        var user = userMapper.toEntity(userDto);
        user.setAccountStatus(AccountStatus.INACTIVE);
        user.setBestScore(0L);
        //Generate Otp
        String otp = OTPGenerator.generateOTP(6);
        //Save Otp to DB
        var otpEntity = new OtpVerificationEntity();
        otpEntity.setEmail(userDto.getEmail());
        otpEntity.setOtp(otp);
        otpEntity.setExpireDate(LocalDate.from(LocalDateTime.now().plusMinutes(1)));
        otpVerificationRepository.save(otpEntity);
        //Send email
        emailService.sendSimpleEmail(
                userDto.getEmail(),
                "Confirmation",
                "Welcome to the ZiggyBallApp\n" +
                "This is your confirmation code: " + otp);
        userRepository.save(user);
        log.info("UserService.addUser.end");
    }

    public void verifyOtp(OtpVerificationDto otpVerificationDto){
        var otpVerificationEntity = otpVerificationRepository.
                findOtpVerificationEntitiesByEmail(otpVerificationDto.getEmail());

        if (!otpVerificationEntity.getOtp().equals(otpVerificationDto.getOtp())){
            throw new RuntimeException("Invalid OTP Code");
        }

        if (otpVerificationEntity.getExpireDate().isBefore(LocalDate.now())){
            throw new RuntimeException("OTP Code expired");
        }

        otpVerificationRepository.delete(otpVerificationEntity);
        var userEntity = userRepository.findUserEntitiesByEmail(otpVerificationDto.getEmail());
        userEntity.setAccountStatus(AccountStatus.ACTIVE);
        userRepository.save(userEntity);
    }

    public void updateUser(Long userId, UpdatedUserDto userDto){
        log.info("UserService.updateUser.start userId: {}", userId);
        var userEntity = userRepository.findById(userId).orElseThrow();
        userMapper.toEntity(userDto, userEntity);
        userRepository.save(userEntity);
        log.info("UserService.updateUser.end userId: {}", userId);
    }

    public void deleteUser(Long userId){
        log.info("UserService.deleteUser.start userId: {}", userId);
        userRepository.deleteById(userId);
        log.info("UserService.deleteUser.start userId: {}", userId);
    }

    public void updateBestScore(Long userId, Long score){
        log.info("UserService.updateBestScore.start userId: {}", userId);
        var userEntity = userRepository.findById(userId).orElseThrow();
        userEntity.setBestScore(score);
        userRepository.save(userEntity);
        log.info("UserService.updateBestScore.end userId: {}", userId);
    }
}
