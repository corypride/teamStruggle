import React, { useEffect, useState } from 'react'
import Icon from "react-crud-icons";
import "../Styles/react-crud-icons.css";
import axios from 'axios';


export const MovieCard = ({ movie, watchlist, handleWatchlistUpdate }) => {

    let [recResults, setRecResults] = useState([]);
    const [recRevealed, setRecRevealed] = useState(false);

    const DeleteButton = (movie) => (
        <Icon
            name="delete"
            tooltip="Delete"
            theme="light"
            size="medium"
            onClick={() => handleDeleteMovie(movie, watchlist)}
        />
    );

    const handleDeleteMovie = async (movie, watchlist) => {
        try {

            //Not sure why movie is a weird structure, but the correct way to access id is movie.movie.id
            // Make a request to delete a movie from a watchlist 
            const response = await axios.delete(`http://localhost:8080/watchlist/${watchlist.id}/${movie.movie.id}`)
            handleWatchlistUpdate();

        } catch (error) {
            console.error('Error removing movie from watchlist:', error);
        }
    };


    const RecommendationButton = (movie) => (
        <Icon
            name="sun"
            tooltip="Find Similar Movies"
            theme="light"
            size="medium"
            onClick={() => handleRecClick(movie)}
        />
    );

    const handleRecClick = (movie) => {
        setRecRevealed((prevValue) => !prevValue)
        console.log(movie);
        setRecResults(
        //TODO: Hit the recommendations API
        //TODO: Select a recommendation at random and display it
        //TODO: Add button to generate a new recommendation
        //TODO: Add button to save recommendation to watchlist
        <p>I recommend you Harry Potter!</p>
        )
    };

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
                <h4>{movie.title}
                    <DeleteButton movie={movie} watchlist={watchlist} />
                    <RecommendationButton movie={movie} />
                </h4>
                <div>{recRevealed && (recResults)}</div>
            </div>
        </div>
    )
}

export default MovieCard;