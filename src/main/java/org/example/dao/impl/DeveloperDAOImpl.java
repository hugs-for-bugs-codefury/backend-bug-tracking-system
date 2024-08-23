package org.example.dao.impl;

import org.example.dao.IDeveloperDAO;
import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Bug;
import org.example.models.Developer;
import org.example.models.Project;
import org.example.models.User;

import org.example.services.impl.ProjectServiceImpl;

import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperDAOImpl extends UserDAOImpl implements IDeveloperDAO {
    @Override
    public Developer saveUser(String name, String email, String password) throws SQLException {
        User savedUser = super.saveUser(name, email, password, "developer");

        String sql = "INSERT INTO developers (user_id) VALUES (?)";
        try (Connection connection = MySQLConnection.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, savedUser.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return new Developer(savedUser.getId(), id, savedUser.getName(), savedUser.getEmail(), savedUser.getPasswordHash());


        }


    }


    @Override
    public List<Project> getAssignedProjects(int developerId) throws SQLException {
        String sql = "SELECT * FROM projects INNER JOIN project_developers ON projects.project_id = project_developers.project_id WHERE project_developers.developer_id = ?";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, developerId);
            ResultSet rs = ps.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (rs.next()) {

                ProjectServiceImpl projectService = new ProjectServiceImpl();

                Project project = new Project(rs.getInt("project_name"), rs.getString("name"), rs.getDate("created_on").toLocalDate().atTime(0, 0), rs.getString("status"), rs.getInt("created_by"), null, null, null);


                project.setTesters(projectService.getAssignedTesters(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setDevelopers(projectService.getAssignedDevelopers(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setBugs(projectService.getProjectBugs(project.getProjectId()));
                projects.add(project);
            }
            return projects;

        }

    }

    @Override
    public List<Bug> getAssignedBugs(int developerId) {

        String sql = "SELECT * FROM bugs WHERE assigned_to = ?";

        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, developerId);
            ResultSet rs = ps.executeQuery();
            List<Bug> bugs = new ArrayList<>();
            while (rs.next()) {
                Bug bug = new Bug(rs.getInt("bug_id"), rs.getString("title"), rs.getString("description"), rs.getString("severity"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0, 0), rs.getInt("created_by"), rs.getInt("project_id"));
                bugs.add(bug);
            }
            return bugs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Developer findByID(int developer_id) throws UserNotFoundException, SQLException {
        String sql = "SELECT * FROM developers INNER JOIN users ON developers.user_id = users.user_id WHERE developers.developer_id = ?";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, developer_id);
            ResultSet rs = ps.executeQuery();
            Developer developer = null;
            if (rs.next()) {
                developer = new Developer(rs.getInt("user_id"), rs.getInt("developer_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));
                developer.setProjectIds(new ProjectServiceImpl().getProjectByDeveloper(developer.getDeveloperId()).stream().map(Project::getProjectId).collect(Collectors.toList()));
                return developer;
            }

            throw new UserNotFoundException("Developer with id " + developer_id + " not found");
        }
    }

    @Override
    public Developer findByEmail(String email) throws UserNotFoundException, SQLException {
        String sql = "SELECT * FROM developers INNER JOIN users ON developers.user_id = users.user_id WHERE users.email = ?";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            Developer developer = null;
            if (rs.next()) {
                developer = new Developer(rs.getInt("user_id"), rs.getInt("developer_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));
                return developer;
            }
            throw new RuntimeException("Developer not found");
        }
    }
}
