import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { register } from "../api/authApi";

function Register() {
  const [form, setForm] = useState({ name: "", email: "", password: "", role: "JOB_SEEKER" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      const res = await register(form);
      localStorage.setItem("token", res.data.token);
      localStorage.setItem("role", res.data.role);
      localStorage.setItem("name", res.data.name);
      navigate("/jobs");
    } catch (err) {
      setError("Registration failed. Email may already be in use.");
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "50px auto" }}>
      <h2>Register</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <input name="name" placeholder="Full Name" onChange={handleChange} required />
        <br />
        <input name="email" placeholder="Email" onChange={handleChange} required />
        <br />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
        <br />
        <select name="role" onChange={handleChange} value={form.role}>
          <option value="JOB_SEEKER">Job Seeker</option>
          <option value="RECRUITER">Recruiter</option>
        </select>
        <br />
        <button type="submit">Register</button>
      </form>
      <p>
        Already have an account? <Link to="/login">Login</Link>
      </p>
    </div>
  );
}

export default Register;
