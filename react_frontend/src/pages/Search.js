import React, {useEffect, useState} from 'react'
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';
import Button from '@mui/material/Button';

axios.defaults.withCredentials = true;

function Search({user}) {
    const [searchTerm, setSearchTerm] = useState("");
    let [watchlists, setWatchlists] = useState([]); 
    let [results, setResults] = useState([]);
    let movies;
    
    const fetchWatchlists = async () => {
        const response = await axios.get(`http://localhost:8080/watchlists/${user.id}`);
        watchlists = response.data;
        setWatchlists(watchlists);
    };

    useEffect(() => {
        fetchWatchlists();
    }, [user.id]);

    const handleAddClick = async (aMovie, watchlist) => {
        //save movie to database
        const movieResponse = await axios.post(`http://localhost:8080/movie`,aMovie,
        { withCredentials: true,
            headers: {
                'Access-Control-Allow-Origin': '*', 
                'Content-Type': 'application/json'
            } 
        }); //need to change headers somehow?
        const watchlistResponse = await axios.put(`http://localhost:8080/watchlist/${watchlist.id}/${aMovie.id}`);

        // Fetch watchlists again to update state (and get current list)
        // The "results" variable (and the buttons it contains) are only updated
        // when we call handleSearch(), so do it again right now...
        // TODO: Figure out how to update the button without doing that
        fetchWatchlists();
        handleSearch();
    }

    const handleRemoveClick = async (aMovie, watchlist) => {
        const watchlistResponse = await axios.delete(`http://localhost:8080/watchlist/${watchlist.id}/${aMovie.id}`);

        // Fetch watchlists again to update state (and get current list)
        // The "results" variable (and the buttons it contains) are only updated
        // when we call handleSearch(), so do it again right now...
        // TODO: Figure out how to update the button without doing that
        fetchWatchlists();
        handleSearch();
    }
    
    const handleSearch = async () => {
        const response = await axios.get(`http://localhost:8080/movie/search?searchTerm=${searchTerm}`);
        movies = response.data.slice(0, 10);
        console.log(movies)

        if (movies.length === 0) {
            setResults(<p>{`No results for "${searchTerm}"`}</p>)
        } else {
            setResults(movies.map((movie) => {
                return (
                    <div className='movie-container'>
                        <p>
                            <img src={`https://image.tmdb.org/t/p/w300/${movie.poster_path}`} alt=""></img>
                        </p>
                        <p>
                            <span>Title: </span>{movie.title}
                        </p>
                        <p>
                            <span>Year Released: </span>{movie.release_date}
                        </p>
                        <p>
                            <span>Brief Synopsis: </span>{movie.overview}
                        </p>
                        <ul>
                            {
                                watchlists.map((watchlist) => {
                                    let inList = false;

                                    // Figure out if this movie is already in the list
                                    for (const currentMovie of watchlist.moviesInList) {
                                        if (currentMovie.id === movie.id) {
                                            inList = true;
                                            break;
                                        }
                                    }

                                    if (inList) {
                                        return (
                                            <li>
                                                <Button
                                                    style={{backgroundColor: '#B22222'}}
                                                    key={watchlist.id}
                                                    onClick={() => handleRemoveClick(movie, watchlist)}
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
                                                    onClick={() => handleAddClick(movie, watchlist)}
                                                    variant='contained'>{`ADD TO ${watchlist.name}`}
                                                </Button>
                                                <br></br>
                                            </li>
                                        )
                                    }
                                })
                            }
                        </ul>
                        <br></br>
                    </div>
                )
            }));
        }
    }
    
    return (
        <div id="parent">
            <div className='search-container'>
                <div className='input-button-container'>
                    <input
                        type='text'
                        placeholder='Search for a movie'
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                    <button onClick={handleSearch}>
                        <SearchIcon/>
                    </button>
                </div>
            </div>
            <div>
                <ol>{results}</ol>
            </div>
        </div>
        
    )
  }
  
  export default Search;