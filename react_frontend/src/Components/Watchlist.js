import React, { useState}  from 'react';
import { MovieCard } from "./MovieCard";
import { Grid } from '@mui/material';

export const Watchlist = ({ watchlist , handleWatchlistUpdate}) => {

  return (
    <div>
      <div>
        <h3>{watchlist.name}</h3>
        <div>
          <Grid container spacing={6}>
          {watchlist.moviesInList.length === 0 && <p>No movies in the watchlist</p>}

            {watchlist.moviesInList.map((movie) => (
              <Grid item xs={4}>
                <MovieCard key={movie.id} movie={movie} watchlist={watchlist} handleWatchlistUpdate={handleWatchlistUpdate} />
              </Grid>
            ))}
          </Grid>
          {watchlist.length === 0 && <p>No movies in the watchlist</p>}
        </div>
      </div>
    </div>
  )
}

export default Watchlist;
