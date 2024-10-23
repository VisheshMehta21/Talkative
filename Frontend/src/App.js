import Login from "./components/Login";
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import { Registration } from "./components/Registration";
import { Home } from "./components/Home";


function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/signup" element={<Registration />} />
          <Route path="/login" element={<Registration />} />
          <Route path="/home" element={<Home />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
