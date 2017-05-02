package com.example.andie.mastermind.Builder;

import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.Model.PinList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PinListBuilder {

    int numPins;
    int min = 1;
    int max;

    public PinListBuilder(int num_pin, int num_color){
        numPins = num_pin;
        max = num_color;
    }

    public PinList generateAnsList() {
        PinList list = new PinList();

        for(int i=0;i<numPins;i++){
            int rNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            PinColor pinColor;
            switch (rNum) {
                case 1:
                    pinColor = PinColor.color1;
                    break;
                case 2:
                    pinColor = PinColor.color2;
                    break;
                case 3:
                    pinColor = PinColor.color3;
                    break;
                case 4:
                    pinColor = PinColor.color4;
                    break;
                case 5:
                    pinColor = PinColor.color5;
                    break;
                case 6:
                    pinColor = PinColor.color6;
                    break;
                case 7:
                    pinColor = PinColor.color7;
                    break;
                case 8:
                    pinColor = PinColor.color8;
                    break;
                case 9:
                    pinColor = PinColor.color9;
                    break;
                default:
                    pinColor=PinColor.color1;
            }
            list.addPin(i, pinColor);
        }
        return list;
    }

    public PinList resetPinList(){
        PinList pList= new PinList();

        List<PinColor> list = new ArrayList<PinColor>() {{
            for(int i=0;i<numPins;i++){
                add(PinColor.color0);
            }
        }};
        pList.setList(list);

        return pList;
    }
}
