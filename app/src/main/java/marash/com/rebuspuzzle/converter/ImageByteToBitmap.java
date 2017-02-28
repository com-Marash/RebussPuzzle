package marash.com.rebuspuzzle.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Maedeh on 2/27/2017.
 */

public class ImageByteToBitmap {
    public static Bitmap convert(byte[] imageByteArray){
        return BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
    }
}
