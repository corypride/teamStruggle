import React, {useState} from 'react'
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';



function Search() {
    const [searchTerm, setSearchTerm] = useState(""); 
    const [movie, setMovie] = useState();

    const handleSearch = async () => {
        const response = await axios.get(`http://localhost:8080/movie/search?searchTerm=${searchTerm}`);
        setMovie(response.data[0]);
    }

    return (
      <div className='search-container'>
          <div className='input-button-container'>
              <input
                  type='text'
                  placeholder='Search for a movie'
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
              />
              <button onClick={handleSearch}>
                  <SearchIcon className='searchIcon'/>
              </button>
          </div>
          {movie && (
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
                </div>
          )}
      </div>
  
    )
  }
  
  export default Search;