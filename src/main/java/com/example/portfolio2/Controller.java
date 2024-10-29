package com.example.portfolio2;

import java.util.List;
import javafx.application.Platform;

public class Controller {
    private Model model;
    private HelloApplication view;

    private String selectedSub1 = null;
    private String selectedSub2 = null;

    private int basicCredits = 0;
    private int sub1Credits = 0;
    private int sub2Credits = 0;
    private int electiveCredits = 0;
    private int totalCredits = 0;

    private final int MAX_CREDITS = 110;
    private final int MAX_ELECTIVE_CREDITS = 10;

    Controller(Model model, HelloApplication view) {
        this.model = model;
        this.view = view;
    }

    public void fillComboBox() {
        view.comboProgram.getItems().addAll(model.baseProgram());

        view.comboProgram.setOnAction(event -> {
            String selectedBase = view.comboProgram.getValue();
            resetSelections();
            resetDatabase();
            addProjects(selectedBase);
            updateComboBasicCourse(selectedBase);

            view.comboSub1.getItems().clear();
            view.comboSub1.getItems().addAll(model.subjectModule(selectedBase));

            view.comboSub2.getItems().clear();

            model.storeProgram(selectedBase);
        });

        view.addBasicCourse.setOnAction(event -> {
            if (basicCredits + electiveCredits < MAX_CREDITS) {
                String selectedCourse = view.comboBasicCourse.getValue();
                if (selectedCourse != null) {
                    String basicName = selectedCourse.split(" - ")[1];
                    int credits = model.getBasicCourseCredits(basicName);

                    addSelectedCourseToTextAreaBasicCourse(selectedCourse);
                    view.comboBasicCourse.getItems().remove(selectedCourse);
                    updateBasicCredits(credits);

                    model.storeBasicCourse(basicName);
                }
            }
        });

        view.comboSub1.setOnAction(event -> {
            selectedSub1 = view.comboSub1.getValue();
            updateTextAreaSub1(selectedSub1);

            List<String> allModules = model.subjectModule("null");
            allModules.remove(selectedSub1);
            view.comboSub2.getItems().clear();
            view.comboSub2.getItems().addAll(allModules);

            model.storeSubjectModule1(selectedSub1);
        });

        view.comboSub2.setOnAction(event -> {
            selectedSub2 = view.comboSub2.getValue();
            updateTextAreaSub2(selectedSub2);

            model.storeSubjectModule2(selectedSub2);
        });

        for (Activity elective : model.electiveCourse()) {
            view.comboElectiveCourse.getItems().add(elective.getActivityECTS() + " - " + elective.getActivityName());
        }

        view.addElectiveCourse.setOnAction(event -> {
            if (basicCredits + electiveCredits < MAX_CREDITS) {
                if (electiveCredits < MAX_ELECTIVE_CREDITS) {
                    String selectedElectiveCourse = view.comboElectiveCourse.getValue();
                    if (selectedElectiveCourse != null) {
                        String electiveName = selectedElectiveCourse.split(" - ")[1];
                        int credits = model.getElectiveCourseCredits(electiveName);

                        addSelectedCourseToTextAreaElective(selectedElectiveCourse);
                        view.comboElectiveCourse.getItems().remove(selectedElectiveCourse);
                        updateElectiveCredits(credits);

                        model.storeElectiveCourse(electiveName);
                    }
                }
            }
        });
    }

    private void addProjects(String selectedBase) {
        for (Activity project : model.bachelorProject(selectedBase)) {
            view.textAreaBasicCourse.appendText(project.getActivityECTS() + " - " + project.getActivityName() + "\n");
            updateBasicCredits(model.getBachelorProjectCredits(project.getActivityName()));
        }

        for (Activity baseProject : model.baseProject(selectedBase)) {
            view.textAreaBasicCourse.appendText(baseProject.getActivityECTS() + " - " + baseProject.getActivityName() + "\n");
            updateBasicCredits(model.getBasicProjectCredits(baseProject.getActivityName()));
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

    private void addSelectedCourseToTextAreaElective(String course) {
        view.textAreaElectiveCourse.appendText(course + "\n");
    }

    private void updateTextAreaSub1(String subject) {
        view.textAreaSub1.clear();
        sub1Credits = 0;
        Activity project = model.subjectProject(subject);
        view.textAreaSub1.appendText(project.getActivityECTS() + " - " + project.getActivityName() + "\n");
        updateSub1Credits(project.getActivityECTS());

        List<Activity> subjectCourses = model.subjectCourse(subject);
        if (subjectCourses != null) {
            for (Activity course : subjectCourses) {
                view.textAreaSub1.appendText(course.getActivityECTS() + " - " + course.getActivityName() + "\n");
                updateSub1Credits(course.getActivityECTS());

            }
        }
    }

    private void updateTextAreaSub2(String subject) {
        view.textAreaSub2.clear();
        sub2Credits = 0;
        Activity project = model.subjectProject(subject);
        view.textAreaSub2.appendText(project.getActivityECTS() + " - " + project.getActivityName() + "\n");
        updateSub2Credits(project.getActivityECTS());

        List<Activity> subjectCourses = model.subjectCourse(subject);
        if (subjectCourses != null) {
            for (Activity course : subjectCourses) {
                view.textAreaSub2.appendText(course.getActivityECTS() + " - " + course.getActivityName() + "\n");
                updateSub2Credits(course.getActivityECTS());
            }
        }
    }

    private void updateBasicCredits(int credits) {
        basicCredits += credits;
        view.crBasic.setText("Basic Credits: " + basicCredits);
        updateTotalCredits();
    }

    private void updateSub1Credits(int credits) {
        sub1Credits += credits;
        view.crSub1.setText("SubMod 1 Credits: " + sub1Credits);
        updateTotalCredits();
    }

    private void updateSub2Credits(int credits) {
        sub2Credits += credits;
        view.crSub2.setText("SubMod 2 Credits: " + sub2Credits);
        updateTotalCredits();
    }

    private void updateElectiveCredits(int credits) {
        electiveCredits += credits;
        view.crElective.setText("Elective Credits: " + electiveCredits);
        updateTotalCredits();
    }

    private void updateTotalCredits() {
        totalCredits = basicCredits + sub1Credits + sub2Credits + electiveCredits;
        view.crTotal.setText("Programme Credits: " + totalCredits);
        checkMaxCreditsReached();
    }

    private void checkMaxCreditsReached() {
        if (basicCredits + electiveCredits >= MAX_CREDITS) {
            view.addBasicCourse.setDisable(true);
            view.addElectiveCourse.setDisable(true);
        } else if (electiveCredits >= MAX_ELECTIVE_CREDITS){
            view.addElectiveCourse.setDisable(true);
        }
        else {
            view.addBasicCourse.setDisable(false);
            view.addElectiveCourse.setDisable(false);
        }
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

        basicCredits = 0;
        sub1Credits = 0;
        sub2Credits = 0;
        electiveCredits = 0;
        totalCredits = 0;

        view.addBasicCourse.setDisable(false);
        view.addElectiveCourse.setDisable(false);
    }

    private void resetDatabase() {
        model.clearCourseParticipation();
    }
}