package com.example.ziggyballapp.controller;

import com.example.ziggyballapp.model.LeaderboardDto;
import com.example.ziggyballapp.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping
    public List<LeaderboardDto> getTop10Leaderboard(){
        return leaderboardService.getTop10Leaderboard();
    }
}
