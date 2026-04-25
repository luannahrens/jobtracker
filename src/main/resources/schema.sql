CREATE TABLE job_applications (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  company_name VARCHAR(100) NOT NULL,
                                  job_title VARCHAR(100) NOT NULL,
                                  status VARCHAR(50) NOT NULL,
                                  date_applied DATE NOT NULL,
                                  notes VARCHAR(1000)
);