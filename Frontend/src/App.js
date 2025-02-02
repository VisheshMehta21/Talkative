import React, { useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate, useNavigate } from "react-router-dom";
import { Registration } from "./components/Registration/Registration";
import Status from './components/Status/Status';
import StatusViewer from './components/Status/StatusViewer';
import HomePage from "./components/HomePage"; // import Home component
import PrivateRoute from "./PrivateRoute"; // import the PrivateRoute

const AppContent = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const checkTokenExpiration = () => {
      const token = localStorage.getItem("token");
      if (!token) return navigate("/login");

      const payload = JSON.parse(atob(token.split(".")[1]));
      if (payload.exp * 1000 < Date.now()) {
        localStorage.removeItem("token");
        navigate("/login");
      }
    };

    checkTokenExpiration();
    const interval = setInterval(checkTokenExpiration, 60000); // Check every minute

    return () => clearInterval(interval);
  }, [navigate]);

  return (
    <Routes>
      <Route path="/" element={<Navigate to="/login" />} />
      <Route path="/signup" element={<Registration />} />
      <Route path="/login" element={<Registration />} />

      {/* Protect the /home route with PrivateRoute */}
      <Route element={<PrivateRoute />}>
        <Route path="/home" element={<HomePage />} />
        <Route path='/status' element={<Status />} />
        <Route path='/status/:userId' element={<StatusViewer />} />
      </Route>
    </Routes>
  );
};

const App = () => {
  return (
    <Router>
      <AppContent />
    </Router>
  );
};

export default App;
