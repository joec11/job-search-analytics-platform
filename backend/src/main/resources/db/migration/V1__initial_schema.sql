CREATE TABLE job_application (
    id UUID PRIMARY KEY,
    job_title VARCHAR(255),
    company_name VARCHAR(255),
    location VARCHAR(255),
    salary_min NUMERIC,
    salary_max NUMERIC,
    source VARCHAR(100),
    date_applied DATE,
    created_at TIMESTAMP
);
