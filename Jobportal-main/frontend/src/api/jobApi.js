import axiosClient from "./axiosClient";

export const searchJobs = (params) => axiosClient.get("/jobs", { params });
export const getJob = (id) => axiosClient.get(`/jobs/${id}`);
export const myJobs = () => axiosClient.get("/jobs/my");
export const postJob = (data) => axiosClient.post("/jobs", data);
export const updateJob = (id, data) => axiosClient.put(`/jobs/${id}`, data);
export const deleteJob = (id) => axiosClient.delete(`/jobs/${id}`);

export const applyToJob = (jobId) => axiosClient.post(`/applications/${jobId}`);
export const myApplications = () => axiosClient.get("/applications/my");
export const applicantsForJob = (jobId) => axiosClient.get(`/applications/job/${jobId}`);
export const updateApplicationStatus = (id, status) =>
  axiosClient.put(`/applications/${id}/status`, { status });
