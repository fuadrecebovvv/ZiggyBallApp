package com.example.ziggyballapp.service;

import com.example.ziggyballapp.dao.entity.UserEntity;
import com.example.ziggyballapp.dao.repository.UserRepository;
import com.example.ziggyballapp.model.LeaderboardDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeaderboardService {
    private final UserRepository userRepository;

    public List<LeaderboardDto> getTop10Leaderboard() {
        List<Object[]> results = userRepository.findTop10Leaderboard();
        List<LeaderboardDto> leaderboard = new ArrayList<>();

        for (Object[] row : results) {
            String username = (String) row[0];
            Long bestScore = ((Number) row[1]).longValue(); // safer cast
            leaderboard.add(new LeaderboardDto(username, bestScore));
        }

        return leaderboard;
    }

}
