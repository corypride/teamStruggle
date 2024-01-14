import React, {useState} from 'react'
import Icon from "react-crud-icons";
import "../Styles/react-crud-icons.css";
import axios from 'axios';


export const MovieCard = ({ movie , watchlist }) => {

    const [movies, setMovies] = useState([]);

    const DeleteButton = (movie, watchlistId) => (
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

            fetchMovies();
        } catch (error) {
            console.error('Error removing movie from watchlist:', error);
        }
    };

    const fetchMovies = async () => {
        try {
            // Fetch movies based on WatchlistId
            const moviesData = await axios.get(`http://localhost:8080/watchlist/movies/${watchlist.id}`);
            setMovies(moviesData.data);
        } catch (error) {
            console.error('Error fetching movies:', error);
        }
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
                <h4>{movie.title}</h4>
            </div>
            <div>
                <DeleteButton movie={movie} watchlist = {watchlist}/>
            </div>
        </div>
    )
}

export default MovieCard;