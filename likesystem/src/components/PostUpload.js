import React, { useState } from 'react';
import axios from 'axios';
import { Box, Button, TextField, Typography } from '@mui/material';
import { Dropzone } from '@mantine/dropzone';

const PostUpload = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [userId, setUserId] = useState('');
  const [file, setFile] = useState(null);

  const handleTitleChange = (e) => {
    setTitle(e.target.value);
  };

  const handleDescriptionChange = (e) => {
    setDescription(e.target.value);
  };

  const handleUserIdChange = (e) => {
    setUserId(e.target.value);
  };

  const handleFileDrop = (files) => {
    setFile(files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
 console.log(file) 
    const formData = new FormData();
    formData.append('title', title);
    formData.append('description', description);
    formData.append('userId', userId);
    formData.append('imageFile', file);
     
    try {

      axios({
        method: "post",
        url: `http://localhost:8888/post`,
        data:formData,
        headers: { "Content-Type": 'multipart/form-data'},
      }).then((res)=>{window.alert('uploaded successfully')}).catch((error)=>{window.alert('something went wrong');})
    
      setTitle('');
      setDescription('');
      setUserId('');
      setFile(null);
    } catch (error) {
      console.error('Error uploading the file:', error);
      window.alert('error')
    }
  };

  return (
    <form onSubmit={handleSubmit} encType='multipart/form-data'>
      <Box sx={{alignItems:"center",display:'flex',flexDirection:'column' }} >

      <Typography variant="h6">Upload Image</Typography>
      <TextField
        label="Title"
        value={title}
        onChange={handleTitleChange}
        sx={{width:'600px',height:'100px'}}
      />
      <TextField
        label="Description"
        value={description}
        onChange={handleDescriptionChange}
        sx={{width:'600px',height:'100px'}}
        />
      <TextField
        label="User ID"
        value={userId}
        onChange={handleUserIdChange}
        sx={{width:'600px',height:'100px'}}
        />
      <Dropzone
      // type='file'
      acceptedFiles={['image/*']}
      filesLimit={1}
      dropzoneText="Drag and drop an image here, or click to select a file."
      showAlerts={false}
      onDrop={handleFileDrop}
      sx={{width:'600px',height:'100px',marginBottom:'10px'}}
      />
      <Button variant="contained" color="primary" type="submit"  sx={{width:'150px',height:'30px'}}>
        Upload
      </Button>
      </Box>
    </form>
  );
};

export default PostUpload;
