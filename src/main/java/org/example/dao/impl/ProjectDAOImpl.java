package org.example.dao.impl;

import org.example.dao.IProjectDAO;
import org.example.exceptions.projects.ProjectNotFoundException;
import org.example.models.*;
import org.example.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDAOImpl  implements IProjectDAO {

    @Override
    public Project saveProject(Project project) throws SQLException {
       String query = "INSERT INTO projects (project_name, start_date, status, project_manager_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getProjectName());
            ps.setDate(2, Date.valueOf(project.getStartDate().toLocalDate()));
            ps.setString(3, project.getStatus());
            ps.setInt(4, project.getProjectManagerId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            project.setProjectId(rs.getInt(1));

            return project;

        }

    }

    @Override
    public Project findByID(int projectId) throws SQLException, ProjectNotFoundException {
//       get project and all the bugs associated with it
        String sql = "SELECT * FROM projects WHERE project_id = ?";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Project project = new Project(rs.getInt("project_id"), rs.getString("project_name"), rs.getDate("start_date").toLocalDate().atTime(0, 0), rs.getString("status"), rs.getInt("project_manager_id"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

                project.setDevelopers(this.getAssignedDevelopers(projectId).stream().map(User::getId).collect(Collectors.toList()));
                project.setTesters(this.getAssignedTesters(projectId).stream().map(User::getId).collect(Collectors.toList()));
                project.setBugs(this.getProjectBugs(projectId));
                return project;
            }
            throw new ProjectNotFoundException(projectId);
        }

    }

    @Override
    public List<Project> findProjectsByManager(int managerId) throws SQLException {
        String sql = "SELECT * FROM projects WHERE project_manager_id = ?";
        List<Project> projects = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project(rs.getInt("project_id"), rs.getString("name"), rs.getDate("start_date").toLocalDate().atTime(0,0), rs.getString("status"), rs.getInt("project_manager_id"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                project.setDevelopers(this.getAssignedDevelopers(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setTesters(this.getAssignedTesters(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setBugs(this.getProjectBugs(project.getProjectId()));
                projects.add(project);
            }
            return projects;
        }
    }

    @Override
    public List<Project> findProjectsByTester(int testerId) throws SQLException {
        String sql = "SELECT * FROM projects WHERE project_id IN (SELECT project_id FROM testers_projects WHERE tester_id = ?)";
        List<Project> projects = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, testerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project(rs.getInt("project_id"), rs.getString("project_name"), rs.getDate("start_date").toLocalDate().atTime(0,0), rs.getString("status"), rs.getInt("project_manager_id"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                project.setDevelopers(this.getAssignedDevelopers(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setTesters(this.getAssignedTesters(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setBugs(this.getProjectBugs(project.getProjectId()));
                projects.add(project);
            }
            return projects;
        }
    }

    @Override
    public List<Project>  findProjectsByDeveloper(int developerId) throws SQLException {
        String sql = "SELECT * FROM projects WHERE project_id IN (SELECT project_id FROM developers_projects WHERE developer_id = ?)";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, developerId);
            ResultSet rs = ps.executeQuery();
            List<Project> projects = new ArrayList<>();

            while (rs.next()) {
                Project project = new Project(rs.getInt("project_id"), rs.getString("project_name"), rs.getDate("start_date").toLocalDate().atTime(0,0), rs.getString("status"), rs.getInt("project_manager_id"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                project.setDevelopers(this.getAssignedDevelopers(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setTesters(this.getAssignedTesters(project.getProjectId()).stream().map(User::getId).collect(Collectors.toList()));
                project.setBugs(this.getProjectBugs(project.getProjectId()));
                projects.add(project);
            }
            return projects;
        }

    }

    @Override
    public Project assignTester(int projectId, int testerId) throws SQLException {
        String sql = "INSERT INTO testers_projects (project_id, tester_id) VALUES (?, ?)";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, testerId);
            ps.executeUpdate();
            return findByID(projectId);
        }
    }

    @Override
    public Project assignDeveloper(int projectId, int developerId) throws SQLException {
      String sql = "INSERT INTO developers_projects (developer_id, project_id) VALUES (?, ?)";
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, developerId);
            ps.setInt(2, projectId);
            ps.executeUpdate();
            return findByID(projectId);
        }
    }

    @Override
    public List<Developer> getAssignedDevelopers(int projectId) throws SQLException {
        String sql  = "SELECT * FROM developers INNER JOIN users ON developers.user_id = users.user_id WHERE developers.user_id IN (SELECT developer_id FROM developers_projects WHERE project_id = ?)";
        List<Developer> developers = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Developer developer = new Developer(rs.getInt("user_id"), rs.getInt("developer_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));
                developers.add(developer);
            }
            return developers;
        }
    }

    @Override
    public List<Tester> getAssignedTesters(int projectId) throws SQLException {
        String sql  = "SELECT * FROM testers INNER JOIN users ON testers.user_id = users.user_id WHERE tester_id IN (SELECT tester_id FROM testers_projects WHERE project_id = ?)";
        List<Tester> testers = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tester tester = new Tester(rs.getInt("user_id"), rs.getInt("tester_id"), rs.getString("name"), rs.getString("email"), rs.getString("password_hash"));
                testers.add(tester);
            }
            return testers;
        }
    }

    @Override
    public List<Bug> getProjectBugs(int projectId) throws SQLException {
        String sql = "SELECT * FROM bugs WHERE project_id = ?";
        List<Bug> bugs = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bug bug = new Bug(rs.getInt("bug_id"), rs.getString("title"), rs.getString("description"), rs.getString("severity"), rs.getString("status"), rs.getDate("created_on").toLocalDate().atTime(0,0), rs.getInt("created_by"), rs.getInt("project_id"));
                bugs.add(bug);
            }
            return bugs;
        }
    }
}
