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
        // console.log(name, email, password);
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
            <input value={name} onChange={(event) => setName(event.target.value)} name='name' id='name' placeholder='Full Name' />
            <label htmlFor='email'>Email</label>
            <input value={email} onChange={(event) => setEmail(event.target.value)} type='email' placeholder='Enter email' id='email' name='email'/>
            <label htmlFor='password'>Password</label>
            <input value={password} onChange={(event) => setPassword(event.target.value)} type='password' placeholder='Enter password' id='password' name='password'/>
            <button>Login</button>
        </form> 
    </div>
  )
}

export default Register