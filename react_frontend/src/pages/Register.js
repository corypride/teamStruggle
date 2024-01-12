import React, {useState} from 'react'
import '../Styles/Register.css';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function Register() {
    // const [name, setName] = useState('');
    // const [email, setEmail] = useState('');
    // const [password, setPassword] = useState('');

    let navigate = useNavigate();

    const [user, setUser] = useState({
      name: "",
      username: "",
      password: "",
    });

    const {name, username, password} = user;

    const handleSubmit = async (e) => {
        e.preventDefault();
        await axios.post("http://localhost:8080/register", user);
        navigate("/home");
    }

    /*  
    e is short for event that is happening (change). 
    target is element that triggered the event (input/typing)
    value is value of input from user
    
    below sets name to value user entered. spread operator
    applies that to all parameters in user
    */

    const onInputChange = (e) => {
      setUser({...user, [e.target.name] : e.target.value});
    }

  return (
    /* id = HTML elements 
    Name identifies the html tag when the data is sent when the form is submitted.
    Name is identifier for Value and value is the actual value
    Value is the real value of the html tag. 
    ie name="username" value="john"*/
    <div className='form-container'>
        <form className='login-form' onSubmit={handleSubmit}>
            <label htmlFor='name'>Full Name</label>
            <input value={name} onChange={(e) => onInputChange(e)} name='name' id='name' placeholder='Full Name' />
            <label htmlFor='username'>Username</label>
            <input value={username} onChange={(e) => onInputChange(e)} type='username' placeholder='Enter username' id='username' name='username'/>
            <label htmlFor='password'>Password</label>
            <input value={password} onChange={(e) => onInputChange(e)} type='password' placeholder='Enter password' id='password' name='password'/>
            <button>Login</button>
        </form> 
    </div>
  )
}

export default Register