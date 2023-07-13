import React, { useState } from 'react';
import axios from 'axios';
import { Box, Button, Container, TextField, Typography } from '@mui/material';

const RegistrationForm = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Create an object with the user data
    const userData = {
      username,
      email,
      password
    };

    // Send the user data to the backend server for storing in the database
    axios.post('http://localhost:8888/user/add', userData)
      .then(response => {
        // Handle the response from the server
        console.log(response.data);

        // Show success message
        window.alert('Registration successful!');
      })
      .catch(error => {
        console.error('Error:', error);
        window.alert("something went wrong")
      });
  };

  return (
    <Container maxWidth="sm" style={{ display: 'flex',  alignItems: 'center', height: '100vh', background: 'white' }}>
      <form onSubmit={handleSubmit} style={{ backgroundColor: '#ffffff', padding: '20px', borderRadius: '5px' }}>
        <Typography variant="h6" align="center" gutterBottom >
        <strong> Registration Form</strong>
        </Typography>
        <TextField
          label="Username"
          variant="outlined"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
          fullWidth
          margin="normal"
        />
        <TextField
          label="Email"
          variant="outlined"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          fullWidth
          margin="normal"
        />
        <TextField
          label="Password"
          variant="outlined"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          fullWidth
          margin="normal"
        />
        <Button type="submit" variant="contained" color="primary" fullWidth>
          Register
        </Button>
      </form>
    </Container>
  );
};

export default RegistrationForm;
