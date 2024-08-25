package org.example.dao.impl;

import org.example.dao.ITesterDAO;
import org.example.exceptions.users.UserNotFoundException;
import org.example.models.Tester;
import org.example.models.User;
import org.example.services.impl.BugServiceImpl;
import org.example.services.impl.ProjectServiceImpl;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TesterDAOImpl extends UserDAOImpl implements ITesterDAO {
    @Override
    public Tester saveUser(String name, String email, String password) throws SQLException {
        User savedUser = super.saveUser(name, email, password, "tester");

        String sql = "INSERT INTO testers (user_id) VALUES (?)";
        try (Connection connection = MySQLConnection.getConnection()) {


            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, savedUser.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return new Tester(savedUser.getId(), id, savedUser.getName(), savedUser.getEmail(), savedUser.getPasswordHash());

        }
    }

    @Override
    public Tester findByID(int tester_id) throws SQLException, UserNotFoundException {

        String sql_tester = "SELECT * FROM testers INNER JOIN users ON testers.user_id = users.user_id WHERE testers.tester_id = ?";
        BugServiceImpl bugService = new BugServiceImpl();
        ProjectServiceImpl projectService = new ProjectServiceImpl();


        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql_tester);
            ps.setInt(1, tester_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Tester t = new Tester(rs.getInt("user_id"), rs.getInt("tester_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));

                t.setProjects(projectService.getProjectsByTester(t.getTesterId()));

                t.setReportedBugs(bugService.getBugsByTester(t.getTesterId()));


                return t;


            }
            throw new UserNotFoundException("Tester with id " + tester_id + " not found.");
        }

    }

    @Override
    public Tester findByEmail(String email) throws UserNotFoundException, SQLException {
        String sql_tester = "SELECT * FROM testers INNER JOIN users ON testers.user_id = users.user_id WHERE users.email = ?";
        BugServiceImpl bugService = new BugServiceImpl();
        ProjectServiceImpl projectService = new ProjectServiceImpl();


        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql_tester);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Tester t = new Tester(rs.getInt("user_id"), rs.getInt("tester_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));

                t.setProjects(projectService.getProjectsByTester(t.getTesterId()));

                t.setReportedBugs(bugService.getBugsByTester(t.getTesterId()));


                return t;


            }
            throw new UserNotFoundException("Tester with email " + email + " not found.");
        }
    }

    @Override
    public List<Tester> findTestersByProject(int projectId) throws SQLException {
        String sql = "SELECT * FROM testers WHERE tester_id IN (SELECT tester_id FROM testers_projects WHERE project_id = ?)";

        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            List<Tester> testers = new ArrayList<>();
            while (rs.next()) {
                Tester t = new Tester(rs.getInt("user_id"), rs.getInt("tester_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));
                testers.add(t);
            }
            return testers;
        }


    }


}
