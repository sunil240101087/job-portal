import React, { useEffect, useState } from "react";
import { myApplications } from "../api/jobApi";

function SeekerDashboard() {
  const [applications, setApplications] = useState([]);

  useEffect(() => {
    myApplications().then((res) => setApplications(res.data));
  }, []);

  return (
    <div style={{ maxWidth: 700, margin: "30px auto" }}>
      <h2>My Applications</h2>
      {applications.length === 0 && <p>You haven't applied to any jobs yet.</p>}
      {applications.map((app) => (
        <div key={app.id} style={{ border: "1px solid #ccc", padding: 10, margin: "10px 0" }}>
          <h4>{app.job.title}</h4>
          <p>Status: {app.status}</p>
        </div>
      ))}
    </div>
  );
}

export default SeekerDashboard;
