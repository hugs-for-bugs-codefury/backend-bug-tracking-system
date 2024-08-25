package org.example.dao.impl;

import org.example.dao.IBugDAO;
import org.example.exceptions.bugs.BugNotAssignableException;
import org.example.exceptions.bugs.BugNotFoundException;
import org.example.exceptions.bugs.NotAssignableReason;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public  class BugDAOImpl implements IBugDAO {

    @Override
    public  Bug saveBug(Bug bug) throws SQLException {
        String sql = "INSERT INTO bugs (title, description, status, severity, project_id, created_by) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, bug.getTitle());
            ps.setString(2, bug.getDescription());
            ps.setString(3, bug.getStatus());
            ps.setString(4, bug.getSeverity());
            ps.setInt(5, bug.getProjectId());
            ps.setInt(6, bug.getCreatedById());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            bug.setId(rs.getInt(1));

            return bug;
        }

    }

    @Override
    public Bug findBugById(int bugId) throws SQLException, BugNotFoundException {
        String sql = "SELECT * FROM bugs WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bugId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Bug(rs.getInt("bug_id"), rs.getString("title"), rs.getString("description"), rs.getString("severity"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0,0), rs.getInt("created_by"), rs.getInt("project_id"));

            }
            throw new BugNotFoundException(bugId);

        }
    }

    @Override
    public List<Bug> findBugsByProject(int projectId) throws SQLException {
        String sql = "SELECT * FROM bugs WHERE project_id = ?";

        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            List<Bug> bugs =  new ArrayList<>();
            while (rs.next()) {
                Bug bug = new Bug(rs.getInt("bug_id"), rs.getString("title"), rs.getString("description"), rs.getString("severity"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0,0), rs.getInt("created_by"), rs.getInt("project_id"));
                bugs.add(bug);
            }
            return bugs;
        }

    }

    @Override
    public List<Bug> findBugsByTester(int testerId) throws SQLException {
        String sql = "SELECT * FROM bugs WHERE created_by = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, testerId);
            ResultSet rs = ps.executeQuery();
            List<Bug> bugs = new ArrayList<>();
            while (rs.next()) {
                Bug bug = new Bug(rs.getInt("bug_id"), rs.getString("title"), rs.getString("description"), rs.getString("severity"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0, 0), rs.getInt("created_by"), rs.getInt("project_id"));
                bugs.add(bug);
            }
            return bugs;
        }
    }

    @Override
    public void deleteBugById(int bugId) throws SQLException {
        String sql = "DELETE FROM bugs WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bugId);
            ps.executeUpdate();
        }

    }



    @Override
    public Bug assignDeveloper(int bugId, int developerId) throws SQLException, BugNotAssignableException {
        Bug bug = this.findBugById(bugId);
        if (!bug.getStatus().equals("open")) {
            throw new BugNotAssignableException(NotAssignableReason.BUG_NOT_OPEN, bugId);
        }
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
        Developer developer = developerDAO.findByID(developerId);
        if(!developer.getProjectIds().contains(bug.getProjectId())) {
            throw new BugNotAssignableException(NotAssignableReason.BUG_NOT_IN_USER_PROJECTS, bugId);
        }
        String sql = "UPDATE bugs SET assigned_to = ? WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, developerId);
            ps.setInt(2, bugId);
            ps.executeUpdate();
            return findBugById(bugId);
        }
    }

    @Override
    public Bug closeBug(int bugId) throws SQLException{
        String sql = "UPDATE bugs SET status = 'CLOSED' WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bugId);
            ps.executeUpdate();
            return findBugById(bugId);
        }
    }
}
