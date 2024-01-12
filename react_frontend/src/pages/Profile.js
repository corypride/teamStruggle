import React from 'react'
import "../Styles/Profile.css";
//import {users} from '../users';
import Avatar from '@mui/material/Avatar';
import { useLocation } from 'react-router-dom';

function Profile() {
    const email = useLocation().state;
    console.log(email);
  return (
    <div className='profile'>
        {/* pull full name from database*/}
        <Avatar alt="user profile pic" src="https://xsgames.co/randomusers/avatar.php?g=pixel" />
        <img alt=" user profile pic big" src="https://xsgames.co/randomusers/avatar.php?g=pixel" className='anon' />
    </div>
  )
}

export default Profile;