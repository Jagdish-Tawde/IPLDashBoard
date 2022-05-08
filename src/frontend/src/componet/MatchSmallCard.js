import { React } from "react";
import { Link } from "react-router-dom";
import './MatchSmallCard.css';

export const MatchSmallCard = ({ teamName, matches }) => {
  if (!matches) return null;
  const otherTeam = matches.team1 === teamName ? matches.team2 : matches.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  const isTeamWon = teamName === matches.matchWinner;
  return (
    <div
      className={
        isTeamWon ? "MatchSmallCard won-card" : "MatchSmallCard lost-card "
      }
    >
      <span className="vs"> vs </span>
      <h1>
         <Link to={otherTeamRoute}> {otherTeam} </Link>
      </h1>
      <p className="match-result">
        {matches.matchWinner} won by {matches.resultMargin} {matches.result}
      </p>
    </div>
  );
};
