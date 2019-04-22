package com.example.chiennv9.hellorecycle.model;

public class ItemNumber {

    private String number;
    private boolean isChoice = false;

    public ItemNumber(String number, boolean isChoice) {
        this.number = number;
        this.isChoice = isChoice;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
