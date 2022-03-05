import {React} from 'react';
import {Link} from 'react-router-dom';


export const MatchSmallCard = ({teamName,matches})=>{
  if(!matches) return null;
  const otherTeam = matches.team1 === teamName ? matches.team2 : matches.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  return(
      <div className='MatchSmallCard'>
            <h2> vs <Link to={otherTeamRoute}> {otherTeam} </Link></h2>
            <p>{matches.matchWinner} won by {matches.resultMargin} {matches.result}</p>
      </div>
  );
}

