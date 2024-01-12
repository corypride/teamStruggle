import React, {useState} from 'react'
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';
import Button from '@mui/material/Button';

//currently prints selected movie title to console when "add to watchlist 
//button" is clicked. will add info to database


function Search() {
    const [searchTerm, setSearchTerm] = useState(""); 
    let [results, setResults] = useState([]);
    let movies;

    const handleClick = async (aMovie) => {
        //save movie to database
        const response = await axios.post(`http://localhost:8080/movie`, aMovie);

        //TODO: add movie to the specific watchlist selected
        //--maybe this could be done by when you hover over the add to watchlist button, it shows you options?
        console.log(`Added ${aMovie.title} to watchlist!`);
    }

    const handleSearch = async () => {
        const response = await axios.get(`http://localhost:8080/movie/search?searchTerm=${searchTerm}`);
        movies = response.data.slice(0, 10);

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
                        <p>
                            <Button onClick={() => handleClick(movie)} variant='contained'>Add to Watchlist</Button>
                        </p>
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