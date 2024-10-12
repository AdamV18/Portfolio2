package com.example.portfolio2;

import java.util.Iterator;
import java.util.*;

class Model{
    public List<String> baseProgram(){return Arrays.asList("NatBach","HumTek");}
    public List<String> subjectModule(){
        return Arrays.asList(
                "Computer Science",
                "Physics",
                "Informatik",
                "Chemistry",
                "Mathematics",
                "Molecular Biology",
                "Astronomy"
        );
    }

    public List<String> baseCourse(String base){
        if(base.equals("NatBach")) {
            return Arrays.asList(
                    "BK1 Empirical Data",
                    "BK2 Experimental Methods",
                    "BK3 Theory of Natural Science",
                    "Logic and Discrete Mathematics",
                    "Functional Biology – Zoology",
                    "Linear Algebra",
                    "Organic Chemistry",
                    "Biological Chemistry",
                    "Statistical Models",
                    "Functional Programming and Language Implementations",
                    "Classical Mechanics",
                    "Environmental Science",
                    "Cell Biology",
                    "Functional biology – Botany",
                    "Supplementary Physics",
                    "Calculus",
                    "The Chemical Reaction",
                    "Scientific Computing",
                    "Energy and Climate Changes"  //All 5 ECTS
            );
        }
        if(base.equals("HumTek")){
            return Arrays.asList(
                    "Design og Konstruktion I+Workshop" ,
                    "Subjektivitet, Teknologi og Samfund I" ,
                    "Teknologiske systemer og artefakter I" ,
                    "Videnskabsteori" ,
                    "Design og Konstruktion II+Workshop" ,
                    "Subjektivitet, Teknologi og Samfund II" ,
                    "Bæredygtige teknologier" ,
                    "Kunstig intelligens" ,
                    "Medier og teknologi - datavisualisering" ,
                    "Teknologiske Systemer og Artefakter II - Sundhedsteknologi" ,
                    "Den (in)humane storby" ,
                    "Interactive Design in the Field" ,
                    "Organisation og ledelse af designprocesser"  //All 5 ECTS
            );
        }
        return null;
    }

    List<String> baseProject(String base) {
        return Arrays.asList("BP1 " + base, "BP2 " + base, "BP3 " + base, "Bachelorproject " + base);
    }

    List<String> subjectCourse(String subject) {
        if (subject.equals("Computer Science")) {
            return Arrays.asList("Essential Computing",
                    "Software Development",
                    "Interactive Digital Systems" ); //5, 10, 5
        }
        if (subject.equals("Physics")) {
            return Arrays.asList("Thermodynamics and Statistical Mechanics",
                    "Electrodynamics",
                    "Quantum Mechanics" ); //5, 5, 10
        }
        if (subject.equals("Informatik")) {
            return Arrays.asList("Organisatorisk forandring og IT",
                    "BANDIT",
                    "Webbased IT-System" ); //5, 10, 5
        }
        if (subject.equals("Chemistry")) {
            return Arrays.asList("General Chemistry",
                    "Atoms and Molecules",
                    "Analytical Chemistry",
                    "Inorganic Chemistry"); //5, 5, 5, 5
        }
        if (subject.equals("Mathematics")) {
            return Arrays.asList("Dynamical Systems and Models",
                    "Algebra",
                    "Mathematical Analysis I",
                    "Mathematical Analysis II"); //5, 5, 5, 5
        }
        if (subject.equals("Molecular Biology")) {
            return Arrays.asList("General Molecular Biology",
                    "Biochemistry",
                    "Genetics",
                    "Methods in Molecular Biology"); //5, 5, 5, 5
        }
        if (subject.equals("Astronomy")) {
            return Arrays.asList("Essential Astronomy",
                    "Cosmology",
                    "Astrobiology",
                    "Planetary science" ); //5, 5, 5, 5
        }
        return null;
    }

    String subjectProject(String subject) {
        return "Subject module project in "+subject;
    }

    int courseWeight(String course){
        if(course.equals("Software Development")) return 10;
        if(course.equals("BANDIT")) return 10;
        if(course.equals("Quantum Mechanics")) return 10;

        return 5;
    }

    boolean isProject(String s){
        for(String fm:subjectModule())if(s.equals(subjectProject(fm))) return true;
        for(String bs:baseProgram())if(baseProject(bs).contains(s)) return true;
        return false;
    }
}
