import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';
import Anon from '../Media/anon.jpeg';
import "../Styles/Profile.css";
import Watchlist from '../Components/Watchlist';
import { Button } from '@mui/material';
import axios from 'axios';

Modal.setAppElement('#root'); 

function Profile({ user }) {

    const [userDetailsId, setUserDetailsId] = useState(user.userDetailsId);
    const [watchlists, setWatchlists] = useState([]);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [newWatchlistName, setNewWatchlistName] = useState('');

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

    useEffect(() => {

        // Call the fetchWatchlists function when userDetailsId changes -- I don't think I need this now? TODO: refactor
        if (userDetailsId) {
            fetchWatchlists();
        }
    }, [userDetailsId]);

    const handleCreateWatchlist = async () => {
        try {
            // Make a request to create a new watchlist 
            const response = await axios.post(`http://localhost:8080/watchlist/${userDetailsId}/${newWatchlistName}`) 

                setModalIsOpen(false);
                fetchWatchlists();

        } catch (error) {
            console.error('Error creating watchlist:', error);
        }
    };

    return (
        <div className='profile'>
            <h1>{user.username}</h1>
            {/* pull full name from database in another component and pass in state?*/}
            <img src={Anon} className='anon' alt="profile pic" />
            <div>
                <h2>My Watchlists</h2>
                <div>
                    <Button onClick={() => setModalIsOpen(true)} > Create New Watchlist</Button>
            </div>

            {/* Modal for creating a new watchlist */}
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={() => setModalIsOpen(false)}
            >
                <h2>Create New Watchlist</h2>
                <input
                    type="text"
                    placeholder="Watchlist Name"
                    value={newWatchlistName}
                    onChange={(e) => setNewWatchlistName(e.target.value)}
                />
                <button onClick={handleCreateWatchlist}>Create</button>
                <button onClick={() => setModalIsOpen(false)}>Cancel</button>
            </Modal>

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
