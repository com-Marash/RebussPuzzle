package marash.com.rebuspuzzle.converter;

import android.view.View;

import marash.com.rebuspuzzle.R;

/**
 * Created by Maedeh on 1/31/2017.
 */

public class charToImageConverter {

    public static int getID(char myChar, View view) {
        myChar = Character.toLowerCase(myChar);

        int resID = view.getResources().getIdentifier("icon_" + myChar, "drawable", view.getContext().getPackageName());
        if (resID != 0) {
            return resID;
        }
        switch (myChar) {
            case '#':
                return R.drawable.empty_letter;
            default:
                return -1;
        }
    }
}
