package org.example.dao.impl;

import org.example.dao.IProjectManagerDAO;
import org.example.models.Project;
import org.example.models.ProjectManager;
import org.example.models.Tester;
import org.example.models.User;
import org.example.services.impl.ProjectServiceImpl;
import org.example.util.Log;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.stream.Collectors;

public class ProjectManagerDAOImpl extends UserDAOImpl implements IProjectManagerDAO {
    @Override
    public ProjectManager saveUser(String name, String email, String password) {
        User savedUser = super.saveUser(name, email, password, "project_manager");

        String sql = "INSERT INTO project_managers (user_id) VALUES (?)";
        try {
            Connection connection = MySQLConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, savedUser.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new ProjectManager(savedUser.getId(), id, savedUser.getName(), savedUser.getEmail(), savedUser.getPasswordHash());
            }
        } catch (Exception e) {

            throw new RuntimeException(e);

        }
        throw new RuntimeException("Project Manager not saved");
    }

    @Override
    public ProjectManager findByID(int id) {
        String sql = "SELECT * FROM project_managers INNER JOIN users ON project_managers.user_id = users.id WHERE project_managers.id = ?";


        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ProjectManager pm = null;
            if (rs.next()) {
                ProjectServiceImpl projectService = new ProjectServiceImpl();

                pm = new ProjectManager(rs.getInt("user_id"), rs.getInt("manager_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));

                pm.setProjectIds(projectService.getProjectsByManager(pm.getManagerId()).stream().map(Project::getProjectId).collect(Collectors.toList()));

                return pm;

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Tester not found");
    }

    @Override
    public ProjectManager findByEmail(String email) {
        String sql = "SELECT * FROM project_managers INNER JOIN users ON project_managers.user_id = users.user_id WHERE users.email = ?";

        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            ProjectManager pm = null;
            if (rs.next()) {
                ProjectServiceImpl projectService = new ProjectServiceImpl();

                pm = new ProjectManager(rs.getInt("user_id"), rs.getInt("project_manager_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));

                pm.setProjectIds(projectService.getProjectsByManager(pm.getManagerId()).stream().map(Project::getProjectId).collect(Collectors.toList()));

                return pm;

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Tester not found");

    }
}
