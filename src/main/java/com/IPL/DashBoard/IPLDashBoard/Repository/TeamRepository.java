package com.IPL.DashBoard.IPLDashBoard.Repository;

import com.IPL.DashBoard.IPLDashBoard.Model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByTeamName(String teamName);
}
