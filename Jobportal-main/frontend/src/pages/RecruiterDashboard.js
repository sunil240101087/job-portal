import React, { useEffect, useState } from "react";
import { myJobs, postJob, applicantsForJob, updateApplicationStatus } from "../api/jobApi";

function RecruiterDashboard() {
  const [jobs, setJobs] = useState([]);
  const [form, setForm] = useState({
    title: "", description: "", skillsRequired: "", location: "", experienceRequired: "",
  });
  const [selectedJobId, setSelectedJobId] = useState(null);
  const [applicants, setApplicants] = useState([]);

  const loadJobs = async () => {
    const res = await myJobs();
    setJobs(res.data);
  };

  useEffect(() => {
    loadJobs();
  }, []);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handlePost = async (e) => {
    e.preventDefault();
    await postJob(form);
    setForm({ title: "", description: "", skillsRequired: "", location: "", experienceRequired: "" });
    loadJobs();
  };

  const viewApplicants = async (jobId) => {
    setSelectedJobId(jobId);
    const res = await applicantsForJob(jobId);
    setApplicants(res.data);
  };

  const handleStatusChange = async (appId, status) => {
    await updateApplicationStatus(appId, status);
    viewApplicants(selectedJobId);
  };

  return (
    <div style={{ maxWidth: 800, margin: "30px auto" }}>
      <h2>Post a New Job</h2>
      <form onSubmit={handlePost}>
        <input name="title" placeholder="Job Title" value={form.title} onChange={handleChange} required />
        <br />
        <textarea name="description" placeholder="Description" value={form.description} onChange={handleChange} required />
        <br />
        <input name="skillsRequired" placeholder="Skills (comma separated)" value={form.skillsRequired} onChange={handleChange} />
        <br />
        <input name="location" placeholder="Location" value={form.location} onChange={handleChange} />
        <br />
        <input name="experienceRequired" placeholder="Experience Required" value={form.experienceRequired} onChange={handleChange} />
        <br />
        <button type="submit">Post Job</button>
      </form>

      <h2>My Job Postings</h2>
      {jobs.map((job) => (
        <div key={job.id} style={{ border: "1px solid #ccc", padding: 10, margin: "10px 0" }}>
          <h4>{job.title}</h4>
          <button onClick={() => viewApplicants(job.id)}>View Applicants</button>
        </div>
      ))}

      {selectedJobId && (
        <div>
          <h3>Applicants</h3>
          {applicants.length === 0 && <p>No applicants yet.</p>}
          {applicants.map((app) => (
            <div key={app.id} style={{ border: "1px solid #eee", padding: 8, margin: "6px 0" }}>
              <p>{app.jobSeeker.name} — {app.jobSeeker.email}</p>
              <p>Status: {app.status}</p>
              <button onClick={() => handleStatusChange(app.id, "SHORTLISTED")}>Shortlist</button>
              <button onClick={() => handleStatusChange(app.id, "REJECTED")}>Reject</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default RecruiterDashboard;
