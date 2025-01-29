import React from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import { Registration } from "./components/Registration/Registration";
import { HomePage } from "./components/HomePage/HomePage"; // import Home component

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/signup" element={<Registration />} />
        <Route path="/login" element={<Registration />} />
        <Route path="/home" element={<HomePage />} /> {/* New Home route */}
      </Routes>
    </Router>
  );
}

export default App;
