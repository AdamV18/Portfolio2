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

    public void clearCourseParticipation() {
        String deleteStudent = "UPDATE Student SET ProgID = 0 WHERE StudID = 1";
        String deleteSubjectModules = "DELETE FROM SubjectModuleParticipation WHERE StudID = 1";
        String deleteBasicCourses = "DELETE FROM BasicCourseParticipation WHERE StudID = 1";
        String deleteElectives = "DELETE FROM ElectiveParticipation WHERE StudID = 1";

        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(deleteStudent)) {
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conn.prepareStatement(deleteSubjectModules)) {
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conn.prepareStatement(deleteBasicCourses)) {
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conn.prepareStatement(deleteElectives)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    public void storeProgram(String programName) {
        String getIdQuery = "SELECT ProgID FROM BachelorProgramme WHERE ProgName = ?";
        String updateQuery = "UPDATE Student SET ProgID = ? WHERE StudID = 1";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery)) {
                getIdStmt.setString(1, programName);
                ResultSet rs = getIdStmt.executeQuery();
                if (rs.next()) {
                    int programId = rs.getInt("ProgID");
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, programId);
                        updateStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    public void storeBasicCourse(String courseName) {
        String getIdQuery = "SELECT BasCourseID FROM BasicCourse WHERE BasCourseName = ?";
        String query = "INSERT INTO BasicCourseParticipation (BasCourseID, StudID) VALUES (?, 1)";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery)) {
                getIdStmt.setString(1, courseName);
                ResultSet rs = getIdStmt.executeQuery();
                if (rs.next()) {
                    int courseId = rs.getInt("BasCourseID");
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setInt(1, courseId);
                        stmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    public void storeSubjectModule1(String moduleName) {
        String getIdQuery = "SELECT ModuleID FROM SubjectModule WHERE ModuleName = ?";


        String clearQuery = "DELETE FROM SubjectModuleParticipation WHERE StudID = 1 AND ModuleNum = 1";
        String updateQuery = "INSERT INTO SubjectModuleParticipation (StudID, ModuleID, ModuleNum) VALUES (1, ?, 1)";


        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement clearStmt = conn.prepareStatement(clearQuery)) {
                clearStmt.executeUpdate();
            }
            try (PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery)) {
                getIdStmt.setString(1, moduleName);
                ResultSet rs = getIdStmt.executeQuery();
                if (rs.next()) {
                    int moduleId = rs.getInt("ModuleID");
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, moduleId);
                        updateStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }



    }

    public void storeSubjectModule2(String moduleName) {
        String getIdQuery = "SELECT ModuleID FROM SubjectModule WHERE ModuleName = ?";
        String clearQuery = "DELETE FROM SubjectModuleParticipation WHERE StudID = 1 AND ModuleNum = 2";
        String updateQuery = "INSERT INTO SubjectModuleParticipation (StudID, ModuleID, ModuleNum) VALUES (1, ?, 2)";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement clearStmt = conn.prepareStatement(clearQuery)) {
                clearStmt.executeUpdate();
            }
            try (PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery)) {
                getIdStmt.setString(1, moduleName);
                ResultSet rs = getIdStmt.executeQuery();
                if (rs.next()) {
                    int moduleId = rs.getInt("ModuleID");
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, moduleId);
                        updateStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    public void storeElectiveCourse(String electiveCourse) {
        String getIdQuery = "SELECT ElCourseID FROM ElectiveCourse WHERE ElCourseName = ?";
        String query = "INSERT INTO ElectiveParticipation (ElCourseID, StudID) VALUES (?, 1)";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery)) {
                getIdStmt.setString(1, electiveCourse);
                ResultSet rs = getIdStmt.executeQuery();
                if (rs.next()) {
                    int electiveId = rs.getInt("ElCourseID");
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setInt(1, electiveId);
                        stmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    public List<String> baseProgram() {
        List<String> programs = new ArrayList<>();
        String query = "SELECT ProgName FROM BachelorProgramme";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                programs.add(rs.getString("ProgName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }

    public List<Activity> baseCourse(String base) {
        List<Activity> courses = new ArrayList<>();
        String query = "SELECT BasCourseName, BasCourseECTS, BasCourseID FROM BasicCourse bc JOIN BachelorProgramme bp ON bc.ProgID = bp.ProgID WHERE bp.ProgName = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, base);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String courseName = rs.getString("BasCourseName");
                    int courseECTS = rs.getInt("BasCourseECTS");
                    int courseID = rs.getInt("BasCourseID");
                    courses.add(new Activity(courseName, courseECTS, courseID, false));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<String> subjectModule(String program) {
        List<String> modules = new ArrayList<>();
        String query;

        if (program.equals("null")) {
            query = "SELECT ModuleName FROM SubjectModule";
        } else {
            query = "SELECT sm.ModuleName " +
                    "FROM SubjectModule sm " +
                    "JOIN BachelorProgramme bp ON sm.ProgID = bp.ProgID " +
                    "WHERE bp.ProgName = ?";
        }

        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt;

            if (program.equals("null")) {
                stmt = conn.prepareStatement(query);
            } else {
                stmt = conn.prepareStatement(query);
                stmt.setString(1, program);
            }

            try (ResultSet rs = stmt.executeQuery()) {
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

    public Activity subjectProject(String subject) {
        String query = "SELECT ModProjName, ModProjECTS, ModProjID FROM ModuleProject mp JOIN SubjectModule sm ON mp.ModuleID = sm.ModuleID WHERE sm.ModuleName = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, subject);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String projName = rs.getString("ModProjName");
                    int projECTS = rs.getInt("ModProjECTS");
                    int projID = rs.getInt("ModProjID");
                    return new Activity(projName, projECTS, projID, true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Activity("Subject module project in " + subject, 0, -1, true);
    }

    public int getBasicCourseCredits(String courseName) {
        String query = "SELECT BasCourseECTS FROM BasicCourse WHERE BasCourseName = ?";
        int credits = 0;
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, courseName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    credits = rs.getInt("BasCourseECTS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return credits;
    }

    public int getBasicProjectCredits(String projectName) {
        String query = "SELECT BasProjECTS FROM BasicProject WHERE BasProjName = ?";
        int credits = 0;
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, projectName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    credits = rs.getInt("BasProjECTS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return credits;
    }

    public int getBachelorProjectCredits(String projectName) {
        String query = "SELECT BachProjECTS FROM BachelorProject WHERE BachProjName = ?";
        int credits = 0;
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, projectName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    credits = rs.getInt("BachProjECTS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return credits;
    }

    public int getElectiveCourseCredits(String electiveName) {
        String query = "SELECT ElCourseECTS FROM ElectiveCourse WHERE ElCourseName = ?";
        int credits = 0;
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, electiveName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    credits = rs.getInt("ElCourseECTS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return credits;
    }

    public int getSubjectModuleCredits(int moduleID) {
        String query = "SELECT ModuleECTS FROM SubjectModule WHERE ModuleID = ?";
        int credits = 0;
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, moduleID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    credits = rs.getInt("ModuleECTS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return credits;
    }

    public int totalCredits() {
        String sumCredit = "SELECT SUM(" +
                "bc.BasCourseECTS + bp.BasProjECTS + bachp.BachProjECTS + mc.ModCourseECTS + mp.ModProjECTS + ec.ElCourseECTS)" +
                " AS total_ects " +
                "FROM Student s " +
                "LEFT JOIN BasicCourseParticipation bcp ON s.StudID = bcp.StudID " +
                "LEFT JOIN SubjectModuleParticipation smp ON s.StudID = smp.StudID " +
                "LEFT JOIN ElectiveParticipation ep ON s.StudID = ep.StudID " +
                "LEFT JOIN BasicCourse bc ON bcp.BasCourseID = bc.BasCourseID " +
                "LEFT JOIN ModuleProject mp ON smp.ModuleID = mp.ModuleID " +
                "LEFT JOIN ModuleCourse mc ON smp.ModuleID = mc.ModuleID " +
                "LEFT JOIN ElectiveCourse ec ON ep.ElCourseID = ec.ElCourseID " +
                "LEFT JOIN BasicProject bp ON s.ProgID = bp.ProgID " +
                "LEFT JOIN BachelorProject bachp ON s.ProgID = bachp.ProgID " +
                "WHERE s.StudID = '1'";

        int totalCredits = 0;
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sumCredit);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalCredits = rs.getInt("total_ects");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return totalCredits;
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