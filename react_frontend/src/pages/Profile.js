import React, { useState, useEffect } from 'react';
import Anon from '../Media/anon.jpeg';
import "../Styles/Profile.css";
import Watchlist from '../Components/Watchlist';
import { user } from '../user';
import { useLocation } from 'react-router-dom';

function Profile() {

    const username = useLocation().state;
    console.log(username);

    //TODO: Need to figure out how to pass in userDetailsId properly
    const [userDetailsId, setUserDetailsId] = useState(user.userDetailsId);
    console.log(userDetailsId);

    const [watchlists, setWatchlists] = useState([]);

    useEffect(() => {
        const fetchWatchlists = async () => {
            try {
                // Fetch watchlists based on userDetailsId
                const watchlistsResponse = await fetch(`http://localhost:8080/watchlists/${user.userDetailsId}`);
                const watchlistData = await watchlistsResponse.json();
                setWatchlists(watchlistData);
            } catch (error) {
                console.error('Error fetching watchlist:', error);
            }
        };

        // Call the fetchWatchlists function when userDetailsId changes
        if (userDetailsId) {
            fetchWatchlists();
        }
    }, [userDetailsId]);

    return (
        <div className='profile'>
            <h1>{user.username}</h1>
            {/* pull full name from database in another component and pass in state?*/}
            <img src={Anon} className='anon' alt="profile pic" />
            <div>
                <h2>My Watchlists</h2>
                <ul>
                    {watchlists.length > 0 ? (
                        watchlists.map((watchlist) => (
                            <Watchlist key={watchlist.id} watchlist={watchlist} />
                        ))
                    ) : (
                        <h3>No Watchlists Found!</h3>
                    )}
                </ul>
            </div>
        </div>
    )
}

export default Profile;
