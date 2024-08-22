package org.example.dao.impl;

import org.example.dao.IBugDAO;
import org.example.models.Bug;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public  class BugDAOImpl implements IBugDAO {

    @Override
    public  Bug saveBug(Bug bug) {
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
            if (rs.next()) {
                bug.setId(rs.getInt(1));
            }
            return bug;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Bug findBugById(int bugId) {
        String sql = "SELECT * FROM bugs WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bugId);
            ResultSet rs = ps.executeQuery();
            Bug bug = null;
            if (rs.next()) {
                bug = new Bug(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0,0), rs.getInt("created_by"), rs.getInt("project_id"));
            }
            return bug;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bug> findBugsByProject(int projectId) {
        String sql = "SELECT * FROM bugs WHERE project_id = ?";

        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            List<Bug> bugs =  new ArrayList<>();
            while (rs.next()) {
                Bug bug = new Bug(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0,0), rs.getInt("created_by"), rs.getInt("project_id"));
                bugs.add(bug);
            }
            return bugs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Bug> findBugsByTester(int testerId) {
        String sql = "SELECT * FROM bugs WHERE created_by = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, testerId);
            ResultSet rs = ps.executeQuery();
            List<Bug> bugs =  new ArrayList<>();
            while (rs.next()) {
                Bug bug = new Bug(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0,0), rs.getInt("created_by"), rs.getInt("project_id"));
                bugs.add(bug);
            }
            return bugs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBugById(int bugId) {
        String sql = "DELETE FROM bugs WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bugId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Bug assignTester(int bugId, int testerId) {
        String sql = "UPDATE bugs SET created_by = ? WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, testerId);
            ps.setInt(2, bugId);
            ps.executeUpdate();
            return findBugById(bugId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Bug assignDeveloper(int bugId, int developerId) {
        String sql = "UPDATE bugs SET assigned_to = ? WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, developerId);
            ps.setInt(2, bugId);
            ps.executeUpdate();
            return findBugById(bugId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Bug closeBug(int bugId) {
        String sql = "UPDATE bugs SET status = 'CLOSED' WHERE bug_id = ?";
        try(Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bugId);
            ps.executeUpdate();
            return findBugById(bugId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
