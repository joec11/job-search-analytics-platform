CREATE TABLE job_applications (
    id BIGSERIAL PRIMARY KEY,
    job_title VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    location VARCHAR(255),

    work_type VARCHAR(50),
    employment_type VARCHAR(50),

    salary_range VARCHAR(100),

    source VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,

    date_applied DATE,
    status_date DATE,

    job_url VARCHAR(2000),
    notes VARCHAR(2000),

    recruiter_name VARCHAR(255),
    recruiter_email VARCHAR(255),
    referral BOOLEAN,
    industry VARCHAR(255),

    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
