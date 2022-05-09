import { React, useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { MatchDeatilCard } from "../componet/MatchDetailCard";
import { MatchSmallCard } from "../componet/MatchSmallCard";
import { PieChart } from "react-minimal-pie-chart";
import "./TeamPage.css";

export const TeamPage = () => {
  const [team, setTeam] = useState({ listOfMatch: [] });
  const { teamName } = useParams();

  useEffect(() => {
    const fetchMaches = async () => {
      const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams/${teamName}`);
      const data = await response.json();
      setTeam(data);
    };
    fetchMaches();
  }, [teamName]);
  if (!team || !team.teamName) {
    return <h1>Team Not found</h1>;
  }

  return (
    <div className="TeamPage">
      <div className="team-name-section">
        <h1 className="Team-name">{team.teamName}</h1>
      </div>
      <div className="win-loss-section">
        Wins / Losses
        <PieChart
          data={[
            {
              title: "losses",
              value: team.totalMatches - team.totalWins,
              color: "#a34d5d",
            },
            { title: "Wins", value: team.totalWins, color: "#4da375" } 
          ]}
        />
      </div>
      <div className="match-detail-section">
      <h3>Latest Matches</h3>
        <MatchDeatilCard teamName={team.teamName} match={team.listOfMatch[0]} />
      </div>
      {team.listOfMatch.slice(1).map((match) => (
        <MatchSmallCard key={match.id} teamName={team.teamName} matches={match} />
      ))}
      <div className="more-link">
      <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}> More > </Link> 
      </div>
    </div>
  );
};
