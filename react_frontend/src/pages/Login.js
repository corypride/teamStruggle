import React, {useState} from 'react';
import '../Styles/Login.css';
import {Link} from 'react-router-dom';
import {user} from "../user";
import { useNavigate } from 'react-router-dom';

function Login() {
    // const [email, setEmail] = useState('');
    // const [password, setPassword] = useState('');

    // const handleSubmit = (e) => {
    //     e.preventDefault();
    //     console.log(email, password);
    // }

    let navigate = useNavigate();

    const [currentUser, setCurrentUser] = useState(user);
    const [success, setSuccess] = useState(false);

    const {username, password} = currentUser;

    const handleUser = (e) => {
        setCurrentUser({...currentUser, [e.target.name] : e.target.value});
    }

    const handleSubmit = async(e) => {
        e.preventDefault();
        user.username = currentUser.username;
        user.password = currentUser.password
        console.log(user);
        setSuccess(true);
    }
    


    return (
        <>
            {success ? (
                <div>
                    <h1>You are logged in!</h1>
                    <br />
                    <p>
                        <Link to="/profile">
                            <a>Go to Profile</a>
                        </Link>
                    </p>
                </div>
            ) : (
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
                        {/* <Link to="/profile">
                            <button>Login</button>
                        </Link> */}
                    </form> 
                </div>
            )}
        </>
    )
}

export default Login