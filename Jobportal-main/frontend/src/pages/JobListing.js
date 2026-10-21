import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { searchJobs } from "../api/jobApi";

function JobListing() {
  const [jobs, setJobs] = useState([]);
  const [filters, setFilters] = useState({ search: "", location: "", skills: "" });

  const fetchJobs = async () => {
    const res = await searchJobs(filters);
    setJobs(res.data);
  };

  useEffect(() => {
    fetchJobs();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleChange = (e) => setFilters({ ...filters, [e.target.name]: e.target.value });

  const handleSearch = (e) => {
    e.preventDefault();
    fetchJobs();
  };

  return (
    <div style={{ maxWidth: 800, margin: "30px auto" }}>
      <h2>Browse Jobs</h2>
      <form onSubmit={handleSearch}>
        <input name="search" placeholder="Job title" onChange={handleChange} />
        <input name="location" placeholder="Location" onChange={handleChange} />
        <input name="skills" placeholder="Skills" onChange={handleChange} />
        <button type="submit">Search</button>
      </form>

      {jobs.map((job) => (
        <div key={job.id} style={{ border: "1px solid #ccc", padding: 10, margin: "10px 0" }}>
          <h3>{job.title}</h3>
          <p>{job.location} — {job.experienceRequired}</p>
          <p>Skills: {job.skillsRequired}</p>
          <Link to={`/jobs/${job.id}`}>View Details</Link>
        </div>
      ))}
    </div>
  );
}

export default JobListing;
