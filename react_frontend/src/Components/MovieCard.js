import React, { useEffect, useState } from 'react'
import Icon from "react-crud-icons";
import "../Styles/react-crud-icons.css";
import axios from 'axios';
import { Button } from '@mui/material';


export const MovieCard = ({ movie, watchlist, handleWatchlistUpdate }) => {

    let [recResults, setRecResults] = useState([]);
    const [recRevealed, setRecRevealed] = useState(false);
    const [currentWatchlist, setCurrentWatchlist] = useState(watchlist);

    useEffect(() => {
      setCurrentWatchlist(watchlist);
    }, [watchlist]);
  
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

    const handleAddClick = async (aMovie, watchlist) => {
        // Save movie to database
        const movieResponse = await axios.post(`http://localhost:8080/movie`, aMovie,
            {
                withCredentials: true
            }); // Need to change headers somehow?
        const watchlistResponse = await axios.put(`http://localhost:8080/watchlist/${watchlist.id}/${aMovie.id}`);

        //update the watchlist
        handleWatchlistUpdate();
        //close recommendation section
        setRecRevealed(false);
    }

    const handleRemoveClick = async (aMovie, watchlist) => {
        const watchlistResponse = await axios.delete(`http://localhost:8080/watchlist/${watchlist.id}/${aMovie.id}`);

        //update the watchlist
        handleWatchlistUpdate();
        //close recommendation section
        setRecRevealed(false);
    }

    const handleButtons = ( randomRec, watchlist) => {

        console.log(watchlist)
        let inList = false;

        // Figure out if this movie is already in the list
        for (const currentMovie of watchlist.moviesInList) {
            if (currentMovie.id === randomRec.id) {
                inList = true;
                break;
            }
        }

        if (inList) {
            return (
                <li>
                    <Button
                        style={{ backgroundColor: '#B22222' }}
                        key={watchlist.id}
                        onClick={() => handleRemoveClick(randomRec, watchlist)}
                        variant='contained'>{`REMOVE FROM ${watchlist.name}`}
                    </Button>
                    <br></br>
                </li>
            )
        } else {
            return (
                <li>
                    <Button
                        key={watchlist.id}
                        onClick={() => handleAddClick(randomRec, watchlist)}
                        variant='contained'>{`ADD TO ${watchlist.name}`}
                    </Button>
                    <br></br>
                </li>
            )
        }
    }
    

    const fetchRandomRecommendation = async (movie , watchlist) => {
        const response = await axios.get(`http://localhost:8080/recommendation/${movie.movie.id}`);
        if (response.data.length === 0) {
            setRecResults(<p>I recommend you Harry Potter!</p>)
        }
        else {
            let randomRec;
            //Select a recommendation at random and display it
            console.log(response.data)
            randomRec = response.data[(Math.floor(Math.random() * response.data.length))];
            setRecResults(
                <div>
                    <h3>{randomRec.title}</h3>

                    {randomRec.poster_path ? (
                        <img
                            src={`https://image.tmdb.org/t/p/w300${randomRec.poster_path}`}
                            alt={`${movie.title} Art`}
                        />
                    ) : (
                    /* this should return a placeholder image */ <div></div>
                    )}
                    <div>
                        {handleButtons(randomRec, watchlist)}
                    </div>
                </div>
            )
        }
    }

    const RecommendationButton = (movie) => (
        <Icon
            name="sun"
            tooltip="Find Similar Movies"
            theme="light"
            size="medium"
            onClick={() => handleRecClick(movie, currentWatchlist)}
        />
    );

    const handleRecClick = (movie , watchlist) => {
        setRecRevealed((prevValue) => !prevValue)
        //TODO: Hit the recommendations API
        fetchRandomRecommendation(movie , watchlist);
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