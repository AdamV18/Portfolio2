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


    public List<String> baseCourse(String base) {
        List<String> courses = new ArrayList<>();
        String query = "SELECT BasCourseName FROM BasicCourse bc JOIN BachelorProgramme bp ON bc.ProgID = bp.ProgID WHERE bp.ProgName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, base);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        courses.add(rs.getString("BasCourseName"));
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


    public List<String> baseProject(String base) {
        List<String> projects = new ArrayList<>();
        String query = "SELECT BasProjName FROM BasicProject bp JOIN BachelorProgramme prog ON bp.ProgID = prog.ProgID WHERE prog.ProgName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, base);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        projects.add(rs.getString("BasProjName"));
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


    public List<String> bachelorProject(String base) {
        List<String> bachproject = new ArrayList<>();
        String query = "SELECT BachProjName FROM BachelorProject bcp JOIN BachelorProgramme prog ON bcp.ProgID = prog.ProgID WHERE prog.ProgName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, base);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        bachproject.add(rs.getString("BachProjName"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return bachproject;
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



    public List<String> subjectCourse(String subject) {
        List<String> courses = new ArrayList<>();
        String query = "SELECT ModCourseName FROM ModuleCourse mc JOIN SubjectModule sm ON mc.ModuleID = sm.ModuleID WHERE sm.ModuleName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, subject);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        courses.add(rs.getString("ModCourseName"));
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

    public String subjectProject(String subject) {
        String query = "SELECT ModProjName FROM ModuleProject mp JOIN SubjectModule sm ON mp.ModuleID = sm.ModuleID WHERE sm.ModuleName = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, subject);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("ModProjName");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return "Subject module project in " + subject;
    }

    public List<String> electiveCourse() {
        List<String> electives = new ArrayList<>();
        String query = "SELECT ElCourseName FROM ElectiveCourse";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    electives.add(rs.getString("ElCourseName"));
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
        return 5;
    }

    boolean isProject(String s) {
        for (String fm : subjectModule()) {
            if (s.equals(subjectProject(fm))) return true;
        }

        for (String bs : baseProgram()) {
            if (baseProject(bs).contains(s)) return true;
        }

        for (String bs : baseProgram()) {
            if (bachelorProject(bs).contains(s)) return true;
        }

        return false;
    }
}