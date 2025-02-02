import { Navigate, Outlet } from "react-router-dom";

const PrivateRoute = () => {
  const token = localStorage.getItem("token");

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  const decodeToken = (token) => {
    try {
      const payload = JSON.parse(atob(token.split(".")[1]));
      return payload.exp * 1000 > Date.now(); // Check if token is expired
    } catch (error) {
      return false;
    }
  };

  return decodeToken(token) ? <Outlet /> : <Navigate to="/login" replace />;
};

export default PrivateRoute;
