package com.IPL.DashBoard.IPLDashBoard;

import com.IPL.DashBoard.IPLDashBoard.Data.MatchInput;
import com.IPL.DashBoard.IPLDashBoard.Model.Match;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
    @Override
    public Match process(MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());
        String firstInningTeam, secondInningTeam;

        // Logic to set Team 1 and Team 2 according to Innings the played
        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningTeam = matchInput.getToss_winner();
            secondInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam1() : matchInput.getTeam2();
        } else {
            firstInningTeam = matchInput.getToss_winner();
            secondInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam1() : matchInput.getTeam2();
        }
        match.setTeam1(firstInningTeam);
        match.setTeam2(secondInningTeam);

        match.setTossWinner(match.getTossWinner());
        match.setMatchWinner(match.getMatchWinner());
        match.setTossDecision(match.getTossDecision());
        match.setResult(match.getResult());
        match.setResultMargin(match.getResultMargin());
        match.setUmpire1(match.getUmpire1());
        match.setUmpire2(match.getUmpire2());
        return match;
    }
}
