import React, {useState} from 'react'
import '../Styles/SearchBar.css';
import SearchIcon from '@mui/icons-material/Search';
import CloseIcon from '@mui/icons-material/Close';

function SearchBar({data}) {
    const [filteredData, setFilteredData] = useState([]);
    const [wordEntered, setWordEntered] = useState("");

    const handleFilter = (event) => {
        /* event.target.value = value of input field */
        const searchWord = event.target.value;
        setWordEntered(searchWord);
        const newFilteredData = data.filter((value) => {
            if(searchWord === "") {
                return false;
            } else {
                return value.Title.toLowerCase().includes(searchWord.toLowerCase());
            }
        });
            setFilteredData(newFilteredData);
    }

    const clearInput = () => {
        setFilteredData([]);
        setWordEntered("");
    }

    const handleClick = (value) => {
        console.log(value.Title);
    }

    return (
        <div className="search">
            <div className="searchInput">
                <input 
                    type="text" 
                    placeholder="Search for a movie" 
                    value={wordEntered}
                    onChange={handleFilter}
                />
                <div className="searchIcon">
                    {filteredData.length === 0 ? (
                        <SearchIcon /> 
                    ) : (
                        <CloseIcon id="clearBtn" onClick={clearInput}/>
                    )}
                </div>
            </div>
            {filteredData.length !== 0 && (
            <div className="dataResults">
                {filteredData.map((object) => {
                    return (
                    <div className='dataItem' onClick = {() => handleClick(object)}> 
                        <p>{object.Title}</p> 
                    </div>
                    //div to a when link is present
                    );
                })}
            </div>
            )}
        </div>
    )
    }

export default SearchBar