import React, {useState} from 'react'
import '../Styles/Register.css';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

axios.defaults.withCredentials = true;


function Register({user, onUserUpdate}) {

    let navigate = useNavigate();

    const [newUserForm, setNewUserForm] = useState({
      username: "",
      password: "",
      verifyPassword: "",
    });
    const {username, password, verifyPassword} = newUserForm;

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await axios.post("http://localhost:8080/register", newUserForm);
        // Should be the actual user data from backend below
        onUserUpdate(response.data);
        console.log(response.data)
        navigate("/profile");
    };

    /*  
    e is short for event that is happening (change). 
    target is element that triggered the event (input/typing)
    value is value of input from user
    
    below sets name to value user entered. spread operator
    applies that to all parameters in user
    */

    const handleNewUserForm = (e) => {
      setNewUserForm({...newUserForm, [e.target.name] : e.target.value});
    }

  return (
    /* id = HTML elements 
    Name identifies the html tag when the data is sent when the form is submitted.
    Name is identifier for Value and value is the actual value
    Value is the real value of the html tag. 
    ie name="username" value="john"*/
    <div className='form-container'>
        <form className='login-form' onSubmit={handleSubmit}>
            <label htmlFor='username'>Username</label>
            <input value={username} onChange={(e) => handleNewUserForm(e)} placeholder='Enter username' name='username' type='username' id='username'/>
            <label htmlFor='password'>Password</label>
            <input value={password} onChange={(e) => handleNewUserForm(e)} placeholder='Enter password' name='password' type='password' id='password'/>
            <label htmlFor='verifyPassword'>Verify Password</label>
            <input value={verifyPassword} onChange={(e) => handleNewUserForm(e)} placeholder='Verify password' name='verifyPassword' type='text' id='verifyPassword'/>
            <button>Register</button>
        </form> 
    </div>
  )
}

export default Register