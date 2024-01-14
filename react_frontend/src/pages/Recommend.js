import React from 'react'
import MovieCard from '../Components/MovieCard';
import axios from 'axios';

function Recommend({ user , movieRec }) {

    //TODO: move this to Recommendations page
    const handleRecommendMovie = async (movieRec) => {
        try {

            //Not sure why movie is a weird structure, but the correct way to access id is movie.movie.id
            // Make a request to recommend a movie based on a movie id 
            const response = await axios.get(`http://localhost:8080/recommendation/${movieRec.movie.id}`)
            console.log(response.data)

        } catch (error) {
            console.error('Error fetching recommendation:', error);
        }
    };


    return (
        <div id="parent">
            <MovieCard movie={movieRec} />
        </div>
    )
}

export default Recommend;
