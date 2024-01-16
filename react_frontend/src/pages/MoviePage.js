import React from 'react';
import { useLocation } from 'react-router-dom';

function MoviePage({}) {
  let movieData = useLocation().state;
  return (
    <div className='movie-container'>
        <p>
            <img src={`https://image.tmdb.org/t/p/w300/${movieData.poster_path}`} alt=""></img>
        </p>
        <p>
            <span>Title: </span>{movieData.title}
        </p>
        <p>
            <span>Year Released: </span>{movieData.release_date}
        </p>
        <p>
            <span>Brief Synopsis: </span>{movieData.overview}
        </p>
    </div>
  );
}

export default MoviePage;
