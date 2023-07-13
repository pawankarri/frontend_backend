import React, { useEffect, useState } from 'react';
import axios from 'axios';


const Like = () => {
  const [images, setImages] = useState([]);

  // Fetch all images
  useEffect(() => {
    axios.get('http://localhost:8888/post')
      .then(response => {
        console.log(response)
        setImages(response.data);
      })
      .catch(error => {
        console.error('Error fetching images:', error);
      });
  }, []);

  // Handle image like
  const handleLike = (postId, userId) => {
    axios.post(`http://localhost:8888/post/${19}/like`, { userId })
      .then(response => {
        // Image liked successfully, handle any additional logic or UI updates
        console.log('Image liked:', response.data);
        
        // Update the state to reflect the liked image
        setImages(prevImages => {
          return prevImages.map(image => {
            if (image.id === postId) {
              return {
                ...image,
                likes: image.likes + 1
              };
            }
            return image;
          });
        });
      })
      .catch(error => {
        console.error('Error liking image:', error);
      });
  };
const getPic='http://localhost:8888/post/id/19'
  return (
    <div>
      <h1>Image Gallery</h1>
     
      <div  style={{backgroundImage:`url(${getPic})`,width:'190px',height:'100px'}}>
     
        
        
        
        
      </div>
      <div >
       
            <button  style={{color:'red'}}onClick={() => handleLike(getPic, 1)}>Like</button>
          </div>
        
    </div>
  );
};

export default Like;
