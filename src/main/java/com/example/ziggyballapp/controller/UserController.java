package com.example.ziggyballapp.controller;

import com.example.ziggyballapp.model.UpdatedUserDto;
import com.example.ziggyballapp.model.UserDto;
import com.example.ziggyballapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UpdatedUserDto userDto){
        userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser( @PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/bestScoreUpdate/{userId}")
    public void updateBestScore(@PathVariable Long userId, @RequestParam Long score){
        userService.updateBestScore(userId, score);
    }
}

