import { React, useEffect , useState} from 'react';
import {useParams} from 'react-router-dom';
import { MatchDeatilCard } from '../componet/MatchDetailCard';
import { MatchSmallCard } from '../componet/MatchSmallCard';

export const TeamPage = () => {

    const[team , setTeam]= useState({listOfMatch:[]}); 
    const{ teamName } = useParams();

    useEffect(
        () => {
            const fetchMaches = async () => {
                const response = await fetch(`http://localhost:8080/teams/${teamName}`)
                const data = await response.json();
                setTeam(data);
            }
            fetchMaches();
 
        },[teamName]

    )
    if(!team || !team.teamName){
      return <h1>Team Not found</h1>
    }

    return (
        <div className="TeamPagge">
        <h1>{team.teamName}</h1>
           {console.log(team.listOfMatch)}
            <MatchDeatilCard teamName={team.teamName} match={team.listOfMatch[0]} />
            {team.listOfMatch.slice(1).map(match => <MatchSmallCard teamName={team.teamName} matches={match} />)}
        </div>
    );
}