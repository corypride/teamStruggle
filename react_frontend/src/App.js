import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from "./pages/Login"
import Register from './pages/Register';
import Search from './pages/Search';
import Navbar from './Components/Navbar';
import Footer from './Components/Footer';
import Profile from './pages/Profile';
import Home from './pages/home';
import React, { useState} from 'react';
import Recommend from './pages/Recommend';

function App() {

  let [user, setUser] = useState({
    username: "",
    pwHash: "",
    id: ""
  });

  let [movieRec, setMovieRec] = useState({
    movieObj: ""
  });

  const userUpdate = (newUser) => {
    setUser(newUser);
  }
  const movieObjUpdate = (newMovieObj) => {
    setMovieRec(newMovieObj);
  }

  return (
    <div className="App">
      {/*Switch tells router we want to route one tag at a time */}
      <Router>
        <Navbar user={user} onUserUpdate={userUpdate}/>
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login onUserUpdate={userUpdate} />} />
          <Route path="/register" element={<Register user={user} onUserUpdate={userUpdate}/>}/>
          <Route path="/search" element={<Search user={user}/>}/>
          <Route path="/profile" element={<Profile user={user} movieObjUpdate={movieObjUpdate} />}/>
          <Route path="/recommend" element={<Recommend user={user} movieRec={movieRec} />}/>
          <Route path="/usersearch" element={<Search user={user}/>}/>
          <Route path="/profile" element={<Profile user={user}/>}/>
        </Routes>
        <Footer />
      </Router>
  
    </div>
  );
}

export default App;
