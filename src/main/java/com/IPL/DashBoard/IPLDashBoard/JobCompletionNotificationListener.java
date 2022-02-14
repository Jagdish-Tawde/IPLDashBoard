package com.IPL.DashBoard.IPLDashBoard;

import com.IPL.DashBoard.IPLDashBoard.Model.Match;
import com.IPL.DashBoard.IPLDashBoard.Model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!!! Job Finished! Lets verify result ");

            Map<String, Team> teamsMapData = new HashMap<>();
            entityManager.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (Long) e[1]))
                    .forEach(teamMap -> teamsMapData.put(teamMap.getTeamName(), teamMap));

            entityManager.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        Team team = teamsMapData.get((String) e[0]);
                        team.setTotalMatches(team.getTotalMatches() + (Long) e[1]);
                    });

            entityManager.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        Team team = teamsMapData.get((String) e[0]);
                        if(team!=null) team.setTotalWins((Long) e[1]);
                    });
            teamsMapData.values().forEach(team -> entityManager.persist(team));
            teamsMapData.values().forEach(team -> System.out.println(team));
        }

        super.afterJob(jobExecution);
    }
}
