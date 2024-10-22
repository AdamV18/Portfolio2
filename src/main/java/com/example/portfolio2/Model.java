package com.example.portfolio2;

import java.sql.*;
import java.util.*;

class Model {

    private Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:identifier.sqlite";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return conn;
    }

    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<String> baseProgram() {
        List<String> programs = new ArrayList<>();
        String query = "SELECT ProgName FROM BachelorProgramme";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    programs.add(rs.getString("ProgName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return programs;
    }

    public List<Activity> baseCourse(String base) {
        List<Activity> courses = new ArrayList<>();
        String query = "SELECT BasCourseName, BasCourseECTS, BasCourseID FROM BasicCourse bc JOIN BachelorProgramme bp ON bc.ProgID = bp.ProgID WHERE bp.ProgName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, base);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String courseName = rs.getString("BasCourseName");
                        int courseECTS = rs.getInt("BasCourseECTS");
                        int courseID = rs.getInt("BasCourseID");
                        courses.add(new Activity(courseName, courseECTS, courseID, false));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return courses;
    }

    public List<Activity> baseProject(String base) {
        List<Activity> projects = new ArrayList<>();
        String query = "SELECT BasProjName, BasProjECTS, BasProjID FROM BasicProject bp JOIN BachelorProgramme prog ON bp.ProgID = prog.ProgID WHERE prog.ProgName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, base);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String projName = rs.getString("BasProjName");
                        int projECTS = rs.getInt("BasProjECTS");
                        int projID = rs.getInt("BasProjID");
                        projects.add(new Activity(projName, projECTS, projID, true));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return projects;
    }

    public List<Activity> bachelorProject(String base) {
        List<Activity> bachProjects = new ArrayList<>();
        String query = "SELECT BachProjName, BachProjECTS, BachProjID FROM BachelorProject bcp JOIN BachelorProgramme prog ON bcp.ProgID = prog.ProgID WHERE prog.ProgName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, base);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String projName = rs.getString("BachProjName");
                        int projECTS = rs.getInt("BachProjECTS");
                        int projID = rs.getInt("BachProjID");
                        bachProjects.add(new Activity(projName, projECTS, projID, true));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return bachProjects;
    }

    public List<String> subjectModule() {
        List<String> modules = new ArrayList<>();
        String query = "SELECT ModuleName FROM SubjectModule";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    modules.add(rs.getString("ModuleName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return modules;
    }

    public List<Activity> subjectCourse(String subject) {
        List<Activity> courses = new ArrayList<>();
        String query = "SELECT ModCourseName, ModCourseECTS, ModCourseID FROM ModuleCourse mc JOIN SubjectModule sm ON mc.ModuleID = sm.ModuleID WHERE sm.ModuleName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, subject);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String courseName = rs.getString("ModCourseName");
                        int courseECTS = rs.getInt("ModCourseECTS");
                        int courseID = rs.getInt("ModCourseID");
                        courses.add(new Activity(courseName, courseECTS, courseID, false));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return courses;
    }

    public Activity subjectProject(String subject) {
        String query = "SELECT ModProjName, ModProjECTS, ModProjID FROM ModuleProject mp JOIN SubjectModule sm ON mp.ModuleID = sm.ModuleID WHERE sm.ModuleName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, subject);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String projName = rs.getString("ModProjName");
                        int projECTS = rs.getInt("ModProjECTS");
                        int projID = rs.getInt("ModProjID");
                        return new Activity(projName, projECTS, projID, true);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return new Activity("Subject module project in " + subject, 0, -1, true);
    }

    public List<Activity> electiveCourse() {
        List<Activity> electives = new ArrayList<>();
        String query = "SELECT ElCourseName, ElCourseECTS, ElCourseID FROM ElectiveCourse";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String courseName = rs.getString("ElCourseName");
                    int courseECTS = rs.getInt("ElCourseECTS");
                    int courseID = rs.getInt("ElCourseID");
                    electives.add(new Activity(courseName, courseECTS, courseID, false));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return electives;
    }

    int courseWeight(String course) {
        String query = "SELECT ModCourseECTS FROM ModuleCourse WHERE ModCourseName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, course);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("ModCourseECTS");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return 5; // Default ECTS value
    }
}

class Activity {
    private String activityName;
    private int activityECTS;
    private int activityID;
    private boolean activityProject;

    public Activity(String Name, int ECTS, int ID, boolean Project) {
        this.activityName = Name;
        this.activityECTS = ECTS;
        this.activityID = ID;
        this.activityProject = Project;
    }

    public String getActivityName() {
        return activityName;
    }

    public int getActivityECTS() {
        return activityECTS;
    }

    public int getActivityID() {
        return activityID;
    }

    public boolean isActivityProject() {
        return activityProject;
    }

    @Override
    public String toString() {
        return "Activity Name: " + activityName + ", ECTS: " + activityECTS + ", ID: " + activityID + ", Project: " + activityProject;
    }
}
