package com.example.jobtracker.repository;

import com.example.jobtracker.model.JobApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JobApplicationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<JobApplication> rowMapper = (rs, rowNum) -> {
        JobApplication app = new JobApplication();
        app.setId(rs.getLong("id"));
        app.setCompanyName(rs.getString("company_name"));
        app.setJobTitle(rs.getString("job_title"));
        app.setStatus(rs.getString("status"));
        app.setDateApplied(rs.getDate("date_applied").toLocalDate());
        app.setNotes(rs.getString("notes"));
        return app;
    };

    public JobApplicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<JobApplication> findAll() {
        String sql = "SELECT * FROM job_applications ORDER BY date_applied DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<JobApplication> findByStatus(String status) {
        String sql = "SELECT * FROM job_applications WHERE status = ? ORDER BY date_applied DESC";
        return jdbcTemplate.query(sql, rowMapper, status);
    }

    public Optional<JobApplication> findById(Long id) {
        String sql = "SELECT * FROM job_applications WHERE id = ?";
        List<JobApplication> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.stream().findFirst();
    }

    public void save(JobApplication app) {
        String sql = """
                INSERT INTO job_applications (company_name, job_title, status, date_applied, notes)
                VALUES (?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                app.getCompanyName(),
                app.getJobTitle(),
                app.getStatus(),
                app.getDateApplied(),
                app.getNotes());
    }

    public void update(JobApplication app) {
        String sql = """
                UPDATE job_applications
                SET company_name = ?, job_title = ?, status = ?, date_applied = ?, notes = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(sql,
                app.getCompanyName(),
                app.getJobTitle(),
                app.getStatus(),
                app.getDateApplied(),
                app.getNotes(),
                app.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM job_applications WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}