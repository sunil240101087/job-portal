import React from "react";
import { BrowserRouter, Routes, Route, Link, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import JobListing from "./pages/JobListing";
import JobDetails from "./pages/JobDetails";
import SeekerDashboard from "./pages/SeekerDashboard";
import RecruiterDashboard from "./pages/RecruiterDashboard";

function Nav() {
  const role = localStorage.getItem("role");
  const name = localStorage.getItem("name");

  const logout = () => {
    localStorage.clear();
    window.location.href = "/login";
  };

  return (
    <nav style={{ padding: 10, borderBottom: "1px solid #ccc" }}>
      <Link to="/jobs">Browse Jobs</Link>{" | "}
      {role === "JOB_SEEKER" && <Link to="/dashboard">My Applications</Link>}
      {role === "RECRUITER" && <Link to="/dashboard">Recruiter Dashboard</Link>}
      {" | "}
      {name ? (
        <>
          <span>Hi, {name}</span>{" "}
          <button onClick={logout}>Logout</button>
        </>
      ) : (
        <>
          <Link to="/login">Login</Link>{" | "}
          <Link to="/register">Register</Link>
        </>
      )}
    </nav>
  );
}

function App() {
  const role = localStorage.getItem("role");

  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={<Navigate to="/jobs" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/jobs" element={<JobListing />} />
        <Route path="/jobs/:id" element={<JobDetails />} />
        <Route
          path="/dashboard"
          element={role === "RECRUITER" ? <RecruiterDashboard /> : <SeekerDashboard />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
