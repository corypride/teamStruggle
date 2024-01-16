import React from 'react';
import { MovieCard } from "./MovieCard";
import { Grid } from '@mui/material';

export const Watchlist = ({ watchlist , handleWatchlistUpdate, movieObjUpdate}) => {
  console.log('received in watchlist :', movieObjUpdate)

  let [movieRec, setMovieRec] = useState({
    movieObj: ""
  });

  return (
    <div>
      <div>
        <h3>{watchlist.name}</h3>
        <div>
          <Grid container spacing={6}>
            {watchlist.moviesInList.map((movie) => (
              <Grid item xs={4}>
                <MovieCard key={movie.id} movie={movie} watchlist={watchlist} handleWatchlistUpdate={handleWatchlistUpdate} movieObjUpdate={movieObjUpdate} />
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
