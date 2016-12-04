package kz.nixwins.periodictable.helper;


/**
 * Created by nixwins on 12/2/16.
 */

public class ParseString {

    public static String superscript (String text){

        return text.replace("^", "<sup>").replace(" ", "</sup> ");
    }

}
