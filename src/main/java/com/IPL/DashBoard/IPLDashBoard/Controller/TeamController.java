package com.IPL.DashBoard.IPLDashBoard.Controller;

import com.IPL.DashBoard.IPLDashBoard.Model.Match;
import com.IPL.DashBoard.IPLDashBoard.Model.Team;
import com.IPL.DashBoard.IPLDashBoard.Repository.MatchRepository;
import com.IPL.DashBoard.IPLDashBoard.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }


    @GetMapping("/teams/{teamName}")
    public Team getTeams(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        team.setListOfMatch(matchRepository.findLatestMatchPlayedByTeam(teamName, 4));
        return team;
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getMatchesPlayedYear(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return matchRepository.getMatchesByTeamNameBetweenDates(teamName, startDate, endDate);
    }


}
