package marash.com.rebuspuzzle.repository;

import android.content.res.AssetManager;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import marash.com.rebuspuzzle.dto.GameCell_info;

/**
 * Created by Maedeh on 3/2/2017.
 */

public class GameCellInfoRepository {
    public static ArrayList<GameCell_info> loadGameCellArray(AssetManager assetManager) {
        ArrayList<GameCell_info> result = new ArrayList<>();
        try (InputStream streamIn = assetManager.open("rebussPuzzle.marash")){
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            result = (ArrayList<GameCell_info>) objectinputstream.readObject();
            objectinputstream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
