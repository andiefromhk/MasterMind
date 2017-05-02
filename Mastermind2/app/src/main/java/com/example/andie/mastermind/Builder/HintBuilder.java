package com.example.andie.mastermind.Builder;

import com.example.andie.mastermind.Model.Hint;
import com.example.andie.mastermind.Model.HintSymbol;
import com.example.andie.mastermind.Model.PinColor;
import com.example.andie.mastermind.Model.PinList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HintBuilder {
    int numPins;

    public HintBuilder(int num){
        this.numPins = num;
    }

    public Hint generateHint(PinList guess, PinList ans){
        Hint hint = new Hint();
        int num_match = 0;
        int num_colorOnly = 0;
        int num_notMatch = 0;

        PinList guessCopy = new PinList(guess);
        PinList ansCopy = new PinList(ans);

        for(int i = 0; i< numPins; i++){
            if (guess.getPinColor(i).equals(ans.getPinColor(i))){
                guessCopy.removePin(i - num_match);
                ansCopy.removePin(i - num_match);
                num_match++;
            }
        }

        //remove duplicated color
        List<PinColor> ansColorList = ansCopy.getList();
        Set<PinColor> ansColorSet = new HashSet<>();
        ansColorSet.addAll(ansColorList);
        ansColorList.clear();
        ansColorList.addAll(ansColorSet);

      if (!ansColorList.isEmpty()){
          for(int i=0; i < ansColorList.size(); i++){
                if (guessCopy.containsColor(ansColorList.get(i)))
                    num_colorOnly++;
            }
        }

        num_notMatch = numPins - num_match - num_colorOnly;

            for (int i=0; i<num_match; i++)
                hint.addHintSymbol(HintSymbol.Match);
            for (int i=0; i<num_colorOnly; i++)
                hint.addHintSymbol(HintSymbol.ColorOnly);
            for (int i=0; i<num_notMatch; i++)
                hint.addHintSymbol(HintSymbol.NotMatch);

        return hint;
    }
}
