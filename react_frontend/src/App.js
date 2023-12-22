import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from "./pages/Login"
import Register from './pages/Register';
import Search from './pages/Search';
import Navbar from './Components/Navbar';
import Footer from './Components/Footer';
import Home from './pages/home';
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
          <Route path="/search" exact Component={Search}/>
        </Routes>
        <Footer />
      </Router>
  
    </div>
  );
}

export default App;
