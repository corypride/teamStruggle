import React from 'react';
import { MovieCard } from "./MovieCard";
import { Grid } from '@mui/material';

export const Watchlist = ({ watchlist }) => {
  return (
    <div>
      <div>
        <h3>{watchlist.name}</h3>
        <div>
          <Grid container spacing={6}>
            {watchlist.moviesInList.map((movie) => (
              <Grid item xs={4}>
                <MovieCard key={movie.id} movie={movie} watchlist={watchlist} />
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
