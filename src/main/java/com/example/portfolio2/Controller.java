package com.example.portfolio2;

import java.util.ArrayList;
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
        view.comboProgram.getItems().addAll(model.baseProgram());
        view.comboSub1.getItems().addAll(model.subjectModule());
        view.comboSub2.getItems().addAll(model.subjectModule());
        view.comboElectiveCourse.getItems().addAll(model.electiveCourse());

        view.comboProgram.setOnAction(event -> {
            String selectedBase = view.comboProgram.getValue();
            resetSelections();
            addProjects(selectedBase);
            updateComboBasicCourse(selectedBase);
        });

        view.addBasicCourse.setOnAction(event -> {
            String selectedCourse = view.comboBasicCourse.getValue();
            if (selectedCourse != null) {
                addSelectedCourseToTextAreaBasicCourse(selectedCourse); // Methode um Kurs in das TextArea zu schreiben
                view.comboBasicCourse.getItems().remove(selectedCourse); // Entfernt den ausgewählten Kurs
            }
        });

        view.comboSub1.setOnAction(event -> {
            selectedSub1 = view.comboSub1.getValue();
            updateTextAreaSub1(selectedSub1);
            updateComboSub2Options();
        });

        view.comboSub2.setOnAction(event -> {
            selectedSub2 = view.comboSub2.getValue();
            updateTextAreaSub2(selectedSub2);
            updateComboSub1Options();
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
        view.textAreaBasicCourse.appendText(model.bachelorProject(selectedBase) + "\n");
        for ( String baseproject : model.baseProject(selectedBase)){
            view.textAreaBasicCourse.appendText(baseproject + "\n");
        }
    }


    private void updateComboBasicCourse(String base) {
        view.comboBasicCourse.getItems().clear();
        List<String> baseCourses = model.baseCourse(base);
        if (baseCourses != null) {
            view.comboBasicCourse.getItems().addAll(baseCourses);
        }
    }


    private void addSelectedCourseToTextAreaBasicCourse(String course) {
        view.textAreaBasicCourse.appendText(course + "\n"); // Fügt den ausgewählten Kurs in das TextArea ein
    }


    private void updateComboSub2Options() {
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

    private void updateComboSub1Options() {
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
        List<String> subjectCourses = model.subjectCourse(subject);
        view.textAreaSub1.appendText(model.subjectProject(subject) + "\n");
        if (subjectCourses != null) {
            for (String course : subjectCourses) {
                view.textAreaSub1.appendText(course + "\n");
            }
        }
    }

    private void updateTextAreaSub2(String subject) {
        view.textAreaSub2.clear();
        List<String> subjectCourses = model.subjectCourse(subject);
        view.textAreaSub2.appendText(model.subjectProject(subject) + "\n");
        if (subjectCourses != null) {
            for (String course : subjectCourses) {
                view.textAreaSub2.appendText(course + "\n");
            }
        }
    }
}