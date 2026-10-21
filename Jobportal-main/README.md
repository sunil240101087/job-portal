# Job Portal — Java Full Stack Project

A complete full stack Job Portal with role-based access (Job Seeker / Recruiter),
JWT authentication, job posting, search/filter, and application tracking.

## Tech Stack
- **Backend:** Java 17, Spring Boot 3, Spring Security, Spring Data JPA, MySQL, JWT
- **Frontend:** React 18, React Router, Axios

## Folder Structure
```
jobportal/
 ├── backend/    (Spring Boot project)
 └── frontend/   (React project)
```

## Backend Setup
1. Install Java 17 and Maven.
2. Create a MySQL database (or let the app auto-create it via
   `createDatabaseIfNotExist=true` in `application.properties`).
3. Update `backend/src/main/resources/application.properties` with your
   MySQL username/password.
4. From `backend/`, run:
   ```
   mvn spring-boot:run
   ```
5. Backend runs on `http://localhost:8080`.

## Frontend Setup
1. Install Node.js (v18+).
2. From `frontend/`, run:
   ```
   npm install
   npm start
   ```
3. Frontend runs on `http://localhost:3000`.

## How It Works
1. Register as either a Job Seeker or Recruiter.
2. **Recruiters** can post jobs and view/manage applicants (shortlist/reject).
3. **Job Seekers** can browse/search jobs and apply; track application status
   on their dashboard.
4. JWT token is stored in `localStorage` and attached to every API request.

## API Endpoints Summary
- `POST /api/auth/register` — register (name, email, password, role)
- `POST /api/auth/login` — login, returns JWT
- `GET /api/jobs?search=&location=&skills=` — search jobs (public)
- `POST /api/jobs` — post a job (recruiter, auth required)
- `PUT /api/jobs/{id}` / `DELETE /api/jobs/{id}` — edit/delete job
- `GET /api/jobs/my` — recruiter's own postings
- `POST /api/applications/{jobId}` — apply to a job (seeker)
- `GET /api/applications/my` — seeker's applications
- `GET /api/applications/job/{jobId}` — applicants for a job (recruiter)
- `PUT /api/applications/{id}/status` — shortlist/reject an applicant

## Next Steps to Extend
- Resume file upload (store on S3/local disk, save URL to `resumeUrl`)
- Email notifications on application status change
- Admin panel for managing users/jobs
- Deploy: backend to Render/Railway, frontend to Vercel/Netlify, DB to Aiven

## Talking Points for Interviews
- Explain the JWT flow: login → token issued → token sent in `Authorization`
  header → `JwtFilter` validates it on every request → `SecurityContext` set.
- Explain role-based access: `JOB_SEEKER` vs `RECRUITER` vs `ADMIN` stored on
  `User`, checked in controllers/frontend routing.
- Be ready to walk through the DB schema and relationships (User → Job,
  User → Application, Job → Application).
