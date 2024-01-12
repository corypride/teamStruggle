import React, { useState } from 'react';

import { useNavigate } from 'react-router-dom';

export default function AddUser() {

    let navigate = useNavigate();

    const [user, setUser]=useState({
        name:"",
        email:"",
        password:"",
    })

    const {name, email, password} = user

    const handleNewUser=(e)=>{
        setUser({...user, [e.target.name]:e.target.value});
    }


  return (
    <div className="containmer">
        <div className='row'>
            <div className="col-md-6 offset-md-6 border rounded p-4 mt-2 shadow">
                <h2 className='text-center m-4'>Sign Up for Binder</h2>

                    <form>
                    <div className='mb-3'>
                        <label htmlFor='Name' className='form-label'>
                            Name
                        </label>
                        <input
                        type='text'
                        className='form-control'
                        placeholder="Enter your name"
                        name="name" 
                        value={name}
                        onChange={(e)=>handleNewUser(e)}
                        />
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='Email' className='form-label'>
                            Email
                        </label>
                        <input
                        type='email'
                        className='form-control'
                        placeholder="Enter email"
                        name="email" 
                        value={email}
                        onChange={(e)=>handleNewUser(e)}
                        />
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='Password' className='form-label'>
                            Password
                        </label>
                        <input
                        type='password'
                        className='form-control'
                        placeholder="Enter password"
                        name="password" 
                        value={password}
                        onChange={(e)=>handleNewUser(e)}
                        />
                    </div>
                    <button type="submit" className='btn btn-outline-dark'>Submit</button>
                    <button type="submit" className='btn btn-outline-danger m-2'>Cancel</button>
                </form>
            </div>
        </div>


    </div>
  )
}
