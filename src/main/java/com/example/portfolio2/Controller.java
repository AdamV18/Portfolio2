package com.example.portfolio2;

import java.util.List;
import javafx.application.Platform;

public class Controller {
    private Model model;
    private HelloApplication view;

    private String selectedSub1 = null;
    private String selectedSub2 = null;

    Controller(Model model, HelloApplication view) {
        this.model = model;
        this.view = view;
    }

    public void fillComboBox() {
        // Populate comboProgram with strings (BachelorProgramme)
        view.comboProgram.getItems().addAll(model.baseProgram());

        // Populate comboSub1 and comboSub2 with strings (SubjectModule)
        view.comboSub1.getItems().addAll(model.subjectModule());
        view.comboSub2.getItems().addAll(model.subjectModule());

        // Populate elective courses with ECTS and Activity names
        for (Activity elective : model.electiveCourse()) {
            view.comboElectiveCourse.getItems().add(elective.getActivityECTS() + " - " + elective.getActivityName());
        }

        // Add event listeners for user actions
        view.comboProgram.setOnAction(event -> {
            String selectedBase = view.comboProgram.getValue();
            resetSelections();
            addProjects(selectedBase);
            updateComboBasicCourse(selectedBase);
        });

        view.addBasicCourse.setOnAction(event -> {
            String selectedCourse = view.comboBasicCourse.getValue();
            if (selectedCourse != null) {
                addSelectedCourseToTextAreaBasicCourse(selectedCourse);
                view.comboBasicCourse.getItems().remove(selectedCourse);
            }
        });

        view.comboSub1.setOnAction(event -> {
            selectedSub1 = view.comboSub1.getValue();
            updateTextAreaSub1(selectedSub1);
            //updateComboSub2Options(selectedSub1);
            view.comboSub2.getItems().remove(selectedSub1);
        });

        view.comboSub2.setOnAction(event -> {
            selectedSub2 = view.comboSub2.getValue();
            updateTextAreaSub2(selectedSub2);
            //updateComboSub1Options(selectedSub2);
            view.comboSub1.getItems().remove(selectedSub2);
        });

        view.addElectiveCourse.setOnAction(event -> {
            String selectedElectiveCourse = view.comboElectiveCourse.getValue();
            if (selectedElectiveCourse != null) {
                addSelectedCourseToTextAreaElective(selectedElectiveCourse);
                view.comboElectiveCourse.getItems().remove(selectedElectiveCourse);
            }
        });
    }

    private void addProjects(String selectedBase) {
        // Append bachelor projects
        for (Activity project : model.bachelorProject(selectedBase)) {
            view.textAreaBasicCourse.appendText(project.getActivityECTS() + " - " + project.getActivityName() + "\n");
        }

        // Append base projects
        for (Activity baseProject : model.baseProject(selectedBase)) {
            view.textAreaBasicCourse.appendText(baseProject.getActivityECTS() + " - " + baseProject.getActivityName() + "\n");
        }
    }

    private void updateComboBasicCourse(String base) {
        view.comboBasicCourse.getItems().clear();
        List<Activity> baseCourses = model.baseCourse(base);
        if (baseCourses != null) {
            for (Activity course : baseCourses) {
                view.comboBasicCourse.getItems().add(course.getActivityECTS() + " - " + course.getActivityName());
            }
        }
    }

    private void addSelectedCourseToTextAreaBasicCourse(String course) {
        view.textAreaBasicCourse.appendText(course + "\n");
    }

    private void updateComboSub2Options(String subjecttook) {
        view.comboSub2.getItems().clear();
        for (String subject : model.subjectModule()) {
            if (!subject.equals(selectedSub1)) {
                view.comboSub2.getItems().add(subject);
            }
        }
        if (selectedSub2 != null) {
            view.comboSub2.getSelectionModel().select(selectedSub2);
        }
    }

    private void updateComboSub1Options(String subjecttook) {
        view.comboSub1.getItems().clear();
        for (String subject : model.subjectModule()) {
            if (!subject.equals(selectedSub2)) {
                view.comboSub1.getItems().add(subject);
            }
        }
        if (selectedSub1 != null) {
            view.comboSub1.getSelectionModel().select(selectedSub1);
        }
    }

    private void addSelectedCourseToTextAreaElective(String course) {
        view.textAreaElectiveCourse.appendText(course + "\n");
    }

    private void resetSelections() {
        view.textAreaBasicCourse.clear();
        view.comboBasicCourse.getItems().clear();
        view.textAreaSub1.clear();
        view.textAreaSub2.clear();
        view.textAreaElectiveCourse.clear();
        view.comboSub1.getSelectionModel().clearSelection();
        view.comboSub2.getSelectionModel().clearSelection();
        view.comboElectiveCourse.getSelectionModel().clearSelection();

        selectedSub1 = null;
        selectedSub2 = null;
    }

    private void updateTextAreaSub1(String subject) {
        view.textAreaSub1.clear();
        view.textAreaSub1.appendText(model.subjectProject(subject).getActivityECTS() + " - " + model.subjectProject(subject).getActivityName() + "\n");
        List<Activity> subjectCourses = model.subjectCourse(subject);
        if (subjectCourses != null) {
            for (Activity course : subjectCourses) {
                view.textAreaSub1.appendText(course.getActivityECTS() + " - " + course.getActivityName() + "\n");
            }
        }
    }

    private void updateTextAreaSub2(String subject) {
        view.textAreaSub2.clear();
        view.textAreaSub2.appendText(model.subjectProject(subject).getActivityECTS() + " - " + model.subjectProject(subject).getActivityName() + "\n");
        List<Activity> subjectCourses = model.subjectCourse(subject);
        if (subjectCourses != null) {
            for (Activity course : subjectCourses) {
                view.textAreaSub2.appendText( course.getActivityECTS() + " - " + course.getActivityName() + "\n");
            }
        }
    }
}
