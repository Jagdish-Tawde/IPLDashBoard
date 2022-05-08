import { React, useEffect , useState} from 'react';
import {useParams} from 'react-router-dom';
import { MatchDeatilCard } from '../componet/MatchDetailCard';
import './MatchPage.css'


export const MatchPage = () =>{
    const[matches , setMatches]= useState([]); 
    const{ teamName, year} = useParams();

    useEffect(
        () => {
            const fetchMaches = async () => {
                const response = await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`)
                const data = await response.json();
                setMatches(data);
            }
            fetchMaches();
 
        },[]

    )
    return(
      <div className="MatchPage">
        <h1>Match Page</h1> 
        {matches.map(match => <MatchDeatilCard teamName={teamName} match={match} />)} 
        </div>
    );

    }