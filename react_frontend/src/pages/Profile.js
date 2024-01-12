import React from 'react'
import Anon from '../Media/anon.jpeg';
import "../Styles/Profile.css";
import {user} from '../user';
import { useLocation } from 'react-router-dom';

function Profile() {

    const email = useLocation().state;
    console.log(email);

    return (
        <div className='profile'>
            <h1>{user[email].name}</h1>
            {/* pull full name from database*/}
            <img src={Anon} className='anon' />
        </div>
    )
}

export default Profile