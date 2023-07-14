
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import RegistrationForm from './components/RegistrationForm';
import PostUpload from './components/PostUpload';
import Like from './components/Like';
import AllPosts from './components/AllPosts';

function App() {
  return (
   <>
   <BrowserRouter>
   <Routes>
    <Route exact path='/' element={<RegistrationForm/>}/>
    <Route  path='/postUpload' element={<PostUpload/>}/>
    <Route path='/like' element={<Like/>}/>
    <Route path='/allPosts' element={<AllPosts/>}/>
   </Routes>
   </BrowserRouter>
   </>
  );
}

export default App;
