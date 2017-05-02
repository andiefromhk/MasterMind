package com.example.andie.mastermind.Model;

public enum HintSymbol {
    Match("hint1"),ColorOnly("hint2"), NotMatch("hint3");
    private String drawableName;

    private HintSymbol(String drawableName) {
        this.drawableName = drawableName;
    }

    public String getDrawableName(){
        return drawableName;
    }

    public String toString(){
        return name();
    }
}
