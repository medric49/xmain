package model;

import brain.BrainProcessing;

import java.util.ArrayList;

public class Knowledge {
    private String hypothesisLine;
    private String verificationLine;
    private String problem;
    private String solution;
    private ArrayList<String> symptoms;
    private String id;

    public String getId() {
        return id;
    }

    public String getHypothesisLine() {
        return hypothesisLine;
    }

    public String getVerificationLine() {
        return verificationLine;
    }

    public String getProblem() {
        return problem;
    }

    public String getSolution() {
        return solution;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public Knowledge(String id, String hypothesisLine, String verificationLine, String problem, String solution, ArrayList<String> symptoms) {
        this.id = id;
        this.hypothesisLine = hypothesisLine;
        this.verificationLine = verificationLine;
        this.problem = problem;
        this.solution = solution;
        this.symptoms = symptoms;
    }

    public void delete() {
        BrainProcessing.delete(this);
    }
}
