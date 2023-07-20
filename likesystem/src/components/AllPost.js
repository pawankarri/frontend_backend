import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AllPost = () => {
  const [images, setImages] = useState([]);

  useEffect(() => {
    fetchImages();
  }, []);

  const fetchImages = () => {
    axios
      .get('http://localhost:8888/post/posts')
      .then(response => {
        setImages(response.data);
      })
      .catch(error => {
        console.error('Error fetching images:', error);
      });
  };

  const handleLike = (postId) => {
    const userId=3
    axios
      .post(`http://localhost:8888/post/like/${postId}/${userId}`)
      .then(response => {
        const updatedImages = images.map(image => {
          console.log("image.id");
          if (image.id === postId) {
            return {
              ...image,
              isLiked: true,
              likes: response.data.likeCount,
            };
          }
          return image;
        });
        setImages(updatedImages);
        window.alert('Post liked successfully!');
      })
      .catch(error => {
        if (error.response && error.response.status === 406) {
          window.alert('You have already liked this post.');
        } else {
          console.error('Error liking image:', error);
          window.alert('Error liking post. Please try again later.');
        }
      });
  };

  return (
    <div>
      <h1>Image Gallery</h1>
      {images.map(image => (
        <div key={image.id}>
          <div
            style={{
              backgroundImage: `url(${image.url})`,
              width: '190px',
              height: '100px',
              backgroundSize: 'cover',
              backgroundPosition: 'center',
              marginBottom: '10px',
            }}
          ></div>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <button style={{ color: image.isLiked ? 'red' : 'black' }} onClick={() => handleLike(image.id) } >
              Like
            </button>
            <p style={{ marginLeft: '10px' }}>{image.likes} likes</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default AllPost;
