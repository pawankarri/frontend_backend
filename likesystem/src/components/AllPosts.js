import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AllPosts = () => {
  const [imageUrls, setImageUrls] = useState([]);

  const fetchImages = async () => {
    try {
      const response = await axios.get('http://localhost:8888/post/posts');
      setImageUrls(response.data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  useEffect(() => {
    fetchImages();
  }, []);

  return (
    <div>
      <h1>All Posts</h1>
      {imageUrls.map((imageUrl, index) => (
        <img
          key={index}
          src={imageUrl}
          alt="Image"
          style={{ maxWidth: '300px', maxHeight: '300px', marginBottom: '10px' }}
        />
      ))}
    </div>
  );
};

export default AllPosts;
