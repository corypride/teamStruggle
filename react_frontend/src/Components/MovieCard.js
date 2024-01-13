import React from 'react'

export const MovieCard = ({ movie }) => {
    return (
        <div>
            <div>
                {movie.poster_path ? (
                    <img
                        src={`https://image.tmdb.org/t/p/w300${movie.poster_path}`}
                        alt={`${movie.title} Art`}
                    />
                ) : (
                    /* this should return a placeholder image */ <div></div>
                )}
            </div>
            <div>
                <h3>{movie.title}</h3>
            </div>
            <div>
                {/*TODO: add an add to watchlist button*/}
            </div>
        </div>
    )
}

export default MovieCard;