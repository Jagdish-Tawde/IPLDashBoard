package com.IPL.DashBoard.IPLDashBoard.Controller;

import com.IPL.DashBoard.IPLDashBoard.Model.Team;
import com.IPL.DashBoard.IPLDashBoard.Repository.MatchRepository;
import com.IPL.DashBoard.IPLDashBoard.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository){
        this.teamRepository=teamRepository;
        this.matchRepository=matchRepository;
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeams(@PathVariable String teamName){
        Team team= teamRepository.findByTeamName(teamName);
        team.setListOfMatch(matchRepository.findLatestMatchPlayedByTeam(teamName,4));
        return team;
    }

}
