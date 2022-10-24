import logo from './logo.svg';
import './App.css';
import AddVideo from './components/addVideo';
import ViewPost from './components/viewPost';
import { ToastContainer } from 'react-bootstrap';
import {BrowserRouter , Routes ,Route } from 'react-router-dom';


function App() {
  return (
  <BrowserRouter>
  <ToastContainer />
  <Routes>
    <Route path="/" element={<AddVideo />} />
    <Route path="/view" element={<ViewPost />} />
  </Routes>
  </BrowserRouter>
  );
}

export default App;

