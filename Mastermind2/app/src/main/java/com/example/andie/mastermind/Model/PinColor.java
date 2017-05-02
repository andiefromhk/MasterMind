package com.example.andie.mastermind.Model;

public enum PinColor {

    color0("pin00"),color1("pin01"), color2("pin02"), color3("pin03"),
    color4("pin04"), color5("pin05"), color6("pin06"),
    color7("pin07"), color8("pin08"), color9("pin09");
    private String drawableName;

    private PinColor(String drawableName) {
        this.drawableName = drawableName;
    }

    public String getDrawableName(){
        return drawableName;
    }

    public String toString(){
        return name();
    }
}
