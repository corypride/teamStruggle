import React, {useState} from 'react';
import Logo from '../Media/filmlogo.jpeg';
import {Link} from 'react-router-dom';
import "../Styles/Navbar.css";


function Navbar({user, onUserUpdate}) {

  const handleLogoutClick = () => {
    onUserUpdate({
      name: "",
      username: "",
      password: "",
      userId: "",
      userDetailsId: "1"
      //TODO: clear userDetailsId?
  });
  }
  
  return (
    <>
    {user.username ? (
      <div className='navbar'>
        <div className='rightSide'>
            <Link to='/profile'> My Profile </Link>
            <Link to='/search'> Search </Link>
            <Link to='/' onClick={handleLogoutClick}> Logout </Link>
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