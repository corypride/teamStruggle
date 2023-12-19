import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import SearchBar from './Components/SearchBar';
import Login from './Pages/Login';
import Register from './Pages/Register';
import Navbar from './Components/Navbar';
import Footer from './Components/Footer';
import Home from './Pages/Home';
import axios from 'axios';
import MovieData from './Data.json';
import React, {useState} from "react";



function App() {
  return (
    <div className="App">
      {/*Switch tells router we want to route one tag at a time */}
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" exact Component={Home}/>
          <Route path="/login" exact Component={Login}/>
          <Route path="/register" exact Component={Register}/>
        </Routes>
        <Footer />
      </Router>
  
    </div>
  );
}

export default App;
