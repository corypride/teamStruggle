import React from 'react';
import { MovieCard } from "./MovieCard";

export const Watchlist = ( {watchlist} ) => {
  return (
    <div>
        <div>
            <h3>{watchlist.name}</h3>
        <div>
        {watchlist.moviesInList.map( (movie) => (
            <MovieCard key={movie.id} movie={movie} />
          ))}
            
            {watchlist.length === 0 && <p>No movies in the watchlist</p>}
        </div>
        </div>
    </div>
  )
}

export default Watchlist;
