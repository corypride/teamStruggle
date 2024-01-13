import React from 'react';
import { MovieCard } from "./MovieCard";

export const Watchlist = (watchlist) => {
  return (
    <div>
        <div>
            <h1>{watchlist.name}</h1>
        <div>
        {watchlist.map((movieInList) => (
            <MovieCard key={movieInList.id} movie={movieInList} />
          ))}
            
            {watchlist.length === 0 && <p>No movies in the watchlist</p>}
        </div>
        </div>
    </div>
  )
}
