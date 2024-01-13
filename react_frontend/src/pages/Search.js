import React, {useState} from 'react'
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';
import Button from '@mui/material/Button';


// fake watchlists until connected to backend
//
// DONE: make buttons via mapping (using watchlist name to populate
// button info, and on click display the name and id of the clicked button)
// https://mui.com/material-ui/react-button-group/#split-button (good example of dropdown)
//
// TODO: after that works, use the backend to get the watchlists,
// and test adding with the button via a post onClick
//
// TODO: after that, check to see if this movie
// is already in the watchlist and instead say remove from watchlist (and call)
// the appropriate API with the id and movie etc
//
let watchlists = [
    {
        id: 0,
        name: "Watched"
    },
    {
        id: 1,
        name: "General Watchlist"
    },
    {
        id: 2,
        name: "Custom user watchlist1"
    },
    {
        id: 3,
        name: "Custom user watchlist2"
    }
]


function Search({user}) {
    const [searchTerm, setSearchTerm] = useState(""); 
    let [results, setResults] = useState([]);
    let movies;

    const handleClick = async (aMovie, watchlist) => {
        //save movie to database
        const response = await axios.post(`http://localhost:8080/movie`, aMovie);

        //TODO: add movie to the specific watchlist selected
        //--maybe this could be done by when you hover over the add to watchlist button, it shows you options?
        console.log(`Added ${aMovie.title} to watchlist ${watchlist.name}!`);

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
                        <ul>
                            {watchlists.length > 0 ? (
                                watchlists.map((watchlist) => (
                                    <li>
                                        <Button 
                                            key={watchlist.id}
                                            onClick={() => handleClick(movie, watchlist)}
                                            variant='contained'>{watchlist.name}
                                        </Button>
                                        <br></br>
                                    </li>
                                ))
                            ) : (
                                <h3>No Watchlists Found!</h3>
                            )}
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