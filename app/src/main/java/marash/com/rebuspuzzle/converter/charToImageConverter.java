package marash.com.rebuspuzzle.converter;

import marash.com.rebuspuzzle.R;

/**
 * Created by Maedeh on 1/31/2017.
 */

public class charToImageConverter {

    public static int getID(char myChar){

        switch (myChar){
            case '#':
                return R.drawable.empty_letter;
            case 'A':
                return R.drawable.icon_a;
            case 'B':
                return R.drawable.icon_b;
            case 'C':
                return R.drawable.icon_c;
            case 'D':
                return R.drawable.icon_d;
            case 'E':
                return R.drawable.icon_e;
            case 'F':
                return R.drawable.icon_f;
            case 'G':
                return R.drawable.icon_g;
            default:
                return -1;
        }
    }
}
