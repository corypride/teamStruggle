import React, {useState} from 'react';
import Logo from '../Media/filmlogo.jpeg';
import {Link} from 'react-router-dom';
import "../Styles/Navbar.css";
import ReorderIcon from '@mui/icons-material/Reorder';
import SearchBar from './SearchBar.js';
import MovieData from '../Data.json';


function Navbar() {

  const [openLinks, setOpenLinks] = useState(false)
  const toggleNavbar = () => {
    setOpenLinks(!openLinks)
  }

  return (
    <div className='navbar'>
        <div className='leftSide' id={openLinks ? "open" : "close"}>
            <img src={Logo} />
            <div className='hiddenLinks'>
              <Link to='/'> Home </Link>
              <Link to='/login'> Login </Link>
              <Link to='/register'> Register </Link>
            </div>
        </div>
        <div className='rightSide'>
          <Link to='/'> Home </Link>
          <Link to='/login'> Login </Link>
          <Link to='/register'> Register </Link>
          <button onClick={toggleNavbar}>
            <ReorderIcon />
          </button>
        </div>
        <span>
        <SearchBar data={MovieData}/>
        </span>
    </div>
  )
}

export default Navbar