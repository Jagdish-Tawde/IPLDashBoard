import { React, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDeatilCard } from "../componet/MatchDetailCard";
import { YearSelector } from "../componet/YearSelector";
import "./MatchPage.css";

export const MatchPage = () => {
  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();

  useEffect(() => {
    const fetchMaches = async () => {
      const response = await fetch(
        `${process.env.REACT_APP_API_ROOT_URL}/teams/${teamName}/matches?year=${year}`
      );
      const data = await response.json();
      setMatches(data);
    };
    fetchMaches();
  }, [teamName, year]);
  return (
    <div className="MatchPage">
        <div className="year-selector">
            <h3>Select Year</h3>
            <YearSelector teamNames={teamName}/>
        </div>
      <div>
        <h1 className="match-heading">{teamName} matches in {year}</h1>
        {matches.map((match) => (
          <MatchDeatilCard key={match.id} teamName={teamName} match={match} />
        ))}
      </div>
    </div>
  );
};
