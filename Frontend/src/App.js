import React from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import { Registration } from "./components/Registration";
import { Home } from "./components/Home"; // import Home component

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/signup" element={<Registration />} />
        <Route path="/login" element={<Registration />} />
        <Route path="/home" element={<Home />} /> {/* New Home route */}
      </Routes>
    </Router>
  );
}

export default App;
