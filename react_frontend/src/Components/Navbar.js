import React, {useState} from 'react';
import Logo from '../Media/filmlogo.jpeg';
import {Link} from 'react-router-dom';
import "../Styles/Navbar.css";


function Navbar({user}) {

  //TODO implement logout component
  return (
    <>
    {user.username ? (
      <div className='navbar'>
        <div className='rightSide'>
            <Link to='/'> Home </Link>
            <Link to='/search'> Search </Link>
            <Link to='/'> Logout </Link>
        </div>
      </div>
    ) : (
      <div className='navbar'>
        <div className='rightSide'>
            <Link to='/'> Home </Link>
            <Link to='/login'> Login </Link>
            <Link to='/register'> Register </Link>
            <Link to='/search'> Search </Link>
        </div>
      </div>
    )}
    </>
  )
}

export default Navbar