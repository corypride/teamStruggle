import React from 'react';
import {Link} from 'react-router-dom';
import Background from '../Media/blue.jpeg';
import '../Styles/Home.css';

function Home() {
  return (
    <div className='home' style={{ backgroundImage: `url(${Background})` }}>
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