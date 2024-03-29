import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';
import Anon from '../Media/anon.jpeg';
import "../Styles/Profile.css";
import Watchlist from '../Components/Watchlist';
import { Button } from '@mui/material';
import axios from 'axios';
import Icon from "react-crud-icons";
import { Grid } from '@mui/material';

import "../Styles/react-crud-icons.css";

axios.defaults.withCredentials = true;

Modal.setAppElement('#root');

function Profile({ user }) {

    console.log(user.id)
    const [userDetailsId, setUserDetailsId] = useState(user.id);
    const [watchlists, setWatchlists] = useState([]);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [newWatchlistName, setNewWatchlistName] = useState('');
    const [renameWatchlistFieldRevealed, setRenameWatchlistFieldRevealed] = useState(false);
    const [renameWatchlistFields, setRenameWatchlistFields] = useState({});

    const handleWatchlistUpdate = () => {
        fetchWatchlists();
    };

    const fetchWatchlists = async () => {
        try {
            // Fetch watchlists based on userId
            const watchlistsResponse = await axios.get(`http://localhost:8080/watchlists/${user.id}`);
            const watchlistData = watchlistsResponse.data;
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

    const handleDeleteWatchlist = async (watchlistId) => {
        try {
            // Make a request to create a delete a watchlist 
            const response = await axios.delete(`http://localhost:8080/watchlist/${watchlistId}`)

            fetchWatchlists();
        } catch (error) {
            console.error('Error deleting watchlist:', error);
        }
    };

    const handleAddFriend = async (addFriend) => {
        try {
            //add friend
            const response = await axios.post(`http://localhost:8080/watchlist/${addFriend}`)

        } catch (error) {
            console.error('Error adding friend:', error);
        }

    }
    const handleRenameWatchlist = async (watchlist) => {
        try {
            // Make a request to rename an existing watchlist 
            const response = await axios.put(`http://localhost:8080/api/watchlist/${watchlist.id}`, watchlist)

            fetchWatchlists();

        } catch (error) {
            console.error('Error renaming watchlist:', error);
        }
    };

    const handleRenameInputChange = (watchlistId, value) => {
        setRenameWatchlistFields(prevState => ({
            ...prevState,
            [watchlistId]: value,
        }));
    };

    return (
        <div className='profile'>
            <h1>{user.username}</h1>
            {/* pull full name from database in another component and pass in state?*/}
            {/* <img src={Anon} className='anon' alt="profile pic" /> */}
            <div>
                <h2>MY WATCHLISTS</h2>
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
                            <div>
                                <Grid container spacing={1}>
                                    <div>
                                        <Icon
                                            name="edit"
                                            tooltip="Edit"
                                            theme="light"
                                            size="medium"
                                            onClick={() => setRenameWatchlistFieldRevealed((prevValue) => !prevValue)}
                                        />
                                    </div>
                                    {renameWatchlistFieldRevealed && (
                                        <div>
                                            <input
                                                name={watchlist.name}
                                                placeholder={`Rename ${watchlist.name}`}
                                                value={renameWatchlistFields[watchlist.id]}
                                                type="text"
                                                onChange={(e) => handleRenameInputChange(watchlist.id, e.target.value)}
                                            />
                                            <Button onClick={(e) => {
                                                watchlist.name = renameWatchlistFields[watchlist.id];
                                                handleRenameWatchlist(watchlist);
                                            }
                                            }>Submit</Button>
                                            <Button onClick={ // TODO: add an "Are you sure?" popup here
                                                () => handleDeleteWatchlist(watchlist.id)} > Delete Watchlist</Button>
                                        </div>
                                    )}
                                    <Watchlist key={watchlist.id} watchlist={watchlist} user = {user} handleWatchlistUpdate={handleWatchlistUpdate} />
                                </Grid>

                            </div>
                        ))
                    ) : (
                        <h3>No Watchlists Found!</h3>
                    )}
                </ul>
                <h2>Friends</h2>
                <Button onClick={handleAddFriend}>Add friend</Button>
            </div>
        </div>
    )
}

export default Profile;
