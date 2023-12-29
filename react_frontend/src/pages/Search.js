import React, {useState} from 'react'
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';




function Search() {
    const [searchTerm, setSearchTerm] = useState(""); 
    let [results, setResults] = useState([]);
    let movies;

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
                    </div>
                )
            }));
        }
        //console.log(results);
    }

    console.log(`results is now: ${results}`);
    

    return (
        // This works ok below and prints hey in handleSearch
        // <div>
        //     <button onClick={handleSearch}>
        //         <SearchIcon className='searchIcon'/>
        //     </button>
        // </div>

        // This also works the same
            // <div className='search-container'>
            //     <div className='input-button-container'>
            //         <input
            //             type='text'
            //             placeholder='Search for a movie'
            //             value={searchTerm}
            //             onChange={(e) => setSearchTerm(e.target.value)}
            //         />
            //         <button onClick={handleSearch}>
            //             <SearchIcon className='searchIcon'/>
            //         </button>
            //     </div>
            // </div>

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
                        <SearchIcon className='searchIcon'/>
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