package com.example.portfolio2;
import java.util.ArrayList;

public class Controller {
    private Model model;
    private HelloApplication view;

    Controller(Model model, HelloApplication view) {
        this.model = model;
        this.view = view;
    }

    void selectSubject(String subject) {

    }
    /*
    void enterText(String s){
        model.add(s);
        view.clearField();
        String toarea="";
        for(String t:model.get()){
            toarea+=t+"\n";
        }
        view.setArea(toarea);
    }

    void deleteLastEntry() {
        if (!model.list.isEmpty()) {
            model.list.removeLast();
            updateArea();
        }
    }

    void deleteAllEntries() {
        model.list.clear();
        updateArea();
    }


    void updateArea() {
        StringBuilder toarea = new StringBuilder();
        for (String t : model.get()) {
            toarea.append(t).append("\n");
        }
        view.setArea(toarea.toString());
    }*/
}
