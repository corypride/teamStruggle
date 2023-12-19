import React from 'react';
import {Link} from 'react-router-dom';
import CinemaBackground from '../Media/cinema.jpeg';
import '../Styles/Home.css';

function Home() {
  return (
    <div className='home' style={{ backgroundImage: `url(${CinemaBackground})` }}>
      <div className='headerContainer'>
        <h1>binder</h1>
        <br></br>
        <p>Manage your favorite movies!</p>
        <Link to="/register">
        <button>Sign up now</button>
        </Link>
      </div>
    </div>
  )
}

export default Home