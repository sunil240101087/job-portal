import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getJob, applyToJob } from "../api/jobApi";

function JobDetails() {
  const { id } = useParams();
  const [job, setJob] = useState(null);
  const [message, setMessage] = useState("");
  const role = localStorage.getItem("role");

  useEffect(() => {
    getJob(id).then((res) => setJob(res.data));
  }, [id]);

  const handleApply = async () => {
    try {
      await applyToJob(id);
      setMessage("Applied successfully!");
    } catch (err) {
      setMessage(err.response?.data?.message || "Could not apply. You may need to log in.");
    }
  };

  if (!job) return <p>Loading...</p>;

  return (
    <div style={{ maxWidth: 700, margin: "30px auto" }}>
      <h2>{job.title}</h2>
      <p><b>Location:</b> {job.location}</p>
      <p><b>Experience:</b> {job.experienceRequired}</p>
      <p><b>Skills:</b> {job.skillsRequired}</p>
      <p>{job.description}</p>

      {role === "JOB_SEEKER" && <button onClick={handleApply}>Apply Now</button>}
      {message && <p>{message}</p>}
    </div>
  );
}

export default JobDetails;
