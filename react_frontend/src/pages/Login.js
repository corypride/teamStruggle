import React, {useState} from 'react';
import '../Styles/Login.css';
import { useNavigate } from 'react-router-dom';
import axios from "axios";


function Login({onUserUpdate}) {

    //newUser represents current form fields. handleUser updates newUser. handleSubmit sets global user via onUserUpdate
    const [userLoginForm, setUserLoginForm] = useState({
        username: "",
        password: "",
      });

    const {username, password} = userLoginForm;

    let navigate = useNavigate();

    const handleUserLoginForm = (e) => {
        setUserLoginForm({...userLoginForm, [e.target.name] : e.target.value});
    }

    //TODO: only call onUserUpdate once backend authenticates user
    const handleSubmit = async(e) => {
        e.preventDefault();
        const response = await axios.post("http://localhost:8080/login", userLoginForm);
        // Should be the actual user data from backend below
        onUserUpdate(response.data);
        navigate("/profile");
    }
    
    return (
        <div className='form-container'>
            <form className='login-form' onSubmit={handleSubmit}>
                <label htmlFor='username'>Username</label>
                <input 
                    value={username}
                    onChange={(e) => handleUserLoginForm(e)}
                    placeholder='Enter username'
                    name='username'
                    type='username'
                    id='username'
                    autoComplete='off'
                />
                
                <label htmlFor='password'>Password</label>
                <input 
                    value={password} 
                    onChange={(e) => handleUserLoginForm(e)}
                    placeholder='Enter password'
                    name='password'
                    type='password'
                    id='password'
                />
                <button>Login</button>
            </form> 
        </div>
    )
}

export default Login