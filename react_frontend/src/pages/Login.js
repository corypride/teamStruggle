import React, {useState} from 'react';
import '../Styles/Login.css';
import { useNavigate } from 'react-router-dom';



function Login({onUserUpdate}) {

    //newUser represents current form fields. handleUser updates newUser. handleSubmit sets global user via onUserUpdate
    const [newUser, setNewUser] = useState({
        username: "",
        password: "",
      });
    const {username, password} = newUser;

    let navigate = useNavigate();

    const handleUser = (e) => {
        setNewUser({...newUser, [e.target.name] : e.target.value});
    }

    //TODO: only call onUserUpdate once backend authenticates user
    const handleSubmit = async(e) => {
        e.preventDefault();
        onUserUpdate(newUser);
        navigate("/profile");
    }
    
    return (
        <div className='form-container'>
            <form className='login-form' onSubmit={handleSubmit}>
                <label htmlFor='username'>Username</label>
                <input 
                    value={username} 
                    onChange={(e) => handleUser(e)} 
                    placeholder='Enter username' 
                    name='username' 
                    type='username' 
                    id='username'
                    autoComplete='off'
                />
                
                <label htmlFor='password'>Password</label>
                <input 
                    value={password} 
                    onChange={(e) => handleUser(e)}                    placeholder='Enter password' 
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