package com.IPL.DashBoard.IPLDashBoard.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Team {
    public Team(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    private long totalMatches;
    private long totalWins;
    @Transient
    private List<Match> listOfMatch;

    public List<Match> getListOfMatch() {
        return listOfMatch;
    }

    public void setListOfMatch(List<Match> listOfMatch) {
        this.listOfMatch = listOfMatch;
    }


    public Team(String teamName, long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }


    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", totalMatches=" + totalMatches +
                ", totalWins=" + totalWins +
                '}';
    }
}
