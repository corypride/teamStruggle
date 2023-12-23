import React, {useState} from 'react';
import Logo from '../Media/filmlogo.jpeg';
import {Link} from 'react-router-dom';
import "../Styles/Navbar.css";
import ReorderIcon from '@mui/icons-material/Reorder';



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
              <Link to='/search'> Search </Link>
            </div>
        </div>
        <div className='rightSide'>
          <Link to='/'> Home </Link>
          <Link to='/login'> Login </Link>
          <Link to='/register'> Register </Link>
          <Link to='/search'> Search </Link>
          <button onClick={toggleNavbar}>
            <ReorderIcon />
          </button>
        </div>
    </div>
  )
}

export default Navbar