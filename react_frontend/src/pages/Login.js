import React, {useState} from 'react'
import '../Styles/Login.css';
import {Link} from 'react-router-dom';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email, password);
    }

    return (
        <div className='form-container'>
            <form className='login-form' onSubmit={handleSubmit}>
                <label htmlFor='email'>Email</label>
                <input value={email} onChange={(event) => setEmail(event.target.value)} type='email' placeholder='Enter email' id='email' name='email'/>
                <label htmlFor='password'>Password</label>
                <input value={password} onChange={(event) => setPassword(event.target.value)} type='password' placeholder='Enter password' id='password' name='password'/>
                <button>Login</button>
                {/* <Link to="/profile">
                    <button>Login</button>
                </Link> */}
            </form> 
        </div>
    )
}

export default Login