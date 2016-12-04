package kz.nixwins.periodictable.helper;

import android.view.View;

import kz.nixwins.periodictable.R;
import kz.nixwins.periodictable.model.Element;

/**
 * Created by nixwins on 11/30/16.
 */

public class ElementColor {

    private int backgroundColor;
    private int elementSymbolColor;

    public  ElementColor(){}

    public ElementColor(int backgroundColor, int elementSymbolColor){
        this.backgroundColor = backgroundColor;
        this.elementSymbolColor = elementSymbolColor;
    }

    public static  void setElementBackgroundColor(String elementType, View container){

        switch (elementType){
            case "d":
                container.setBackgroundResource(R.color.elementEnergyTypeD);
                break;
            case "f":
                container.setBackgroundResource(R.color.elementEnergyTypeF);
                break;
            case "p":
                container.setBackgroundResource(R.color.elementEnergyTypeP);
                break;
            case "s":
                container.setBackgroundResource(R.color.elementEnergyTypeS);
                break;
            default:
                container.setBackgroundResource(R.color.elementEnergyTypeDefault);
                break;
        }

    }


}
