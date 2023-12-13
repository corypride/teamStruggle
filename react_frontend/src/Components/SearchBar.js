import React, {useState} from 'react'
import '../Styles/SearchBar.css';
import SearchIcon from '@mui/icons-material/Search';
import CloseIcon from '@mui/icons-material/Close';

function SearchBar({data}) {
    const [filteredData, setFilteredData] = useState([]);
    const [wordEntered, setWordEntered] = useState("");

    const handleFilter = (event) => {
        const searchWord = event.target.value;
        setWordEntered(searchWord);
        const newFilter = data.filter((value) => {
            return value.Title.toLowerCase().includes(searchWord.toLowerCase());
        });

        if(searchWord === "") {
            setFilteredData([]);
        } else {
            setFilteredData(newFilter);
        }
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
                {filteredData.slice(0, 10).map((object, index) => {
                    return (
                    <div className='dataItem' key={index} onClick = {() => handleClick(object)}> 
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