import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Like = () => {
  const [likeCount, setLikeCount] = useState('likecoun');
  const [isLiked, setIsLiked] = useState(false);
  const getPic = 'http://localhost:8888/post/id/19'; // Image URL

  useEffect(() => {
    fetchLikeCount();
  }, [isLiked]);


  const fetchLikeCount = () => {
    axios
      .get('http://localhost:8888/like/count/19')
      .then((response) => {
        console.log(response)
        if(response.status===200){
        setLikeCount(response.data.result);
        setIsLiked(response.data.isLiked);
        } 
      })
      .catch(error => {
        console.error('Error fetching like count:', error);
      });
  };

  const handleLike = () => {
    axios
      .post('http://localhost:8888/post/19/like')
      .then(response => {
        const updatedLikeCount = response.data.likeCount;
        setLikeCount(updatedLikeCount);
        setIsLiked(true);
      })
      .catch(error => {
        console.error('Error liking image:', error);
      });
  };

  return (
    <div>
      <h1>Image Gallery</h1>
      <div
        style={{
          backgroundImage: `url(${getPic})`,
          width: '190px',
          height: '100px',
          backgroundSize: 'cover',
          backgroundPosition: 'center',
          marginBottom: '10px',
        }}
      ></div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <button style={{ color: isLiked ? 'red' : 'black' }} onClick={handleLike} >
          Like
        </button>
        <p style={{ marginLeft: '10px' }}>{likeCount} likes</p>
      </div>
    </div>  
  );
};

export default Like;