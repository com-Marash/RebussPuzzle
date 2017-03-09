package marash.com.rebuspuzzle.repository;

import android.content.res.AssetManager;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import marash.com.rebuspuzzle.dto.GameCellInfo;

/**
 * Created by Maedeh on 3/2/2017.
 */

public class GameCellInfoRepository {
    public static ArrayList<GameCellInfo> loadGameCellArray(AssetManager assetManager) {
        ArrayList<GameCellInfo> result = new ArrayList<>();
        try (InputStream streamIn = assetManager.open("rebussPuzzle.marash")){
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            result = (ArrayList<GameCellInfo>) objectinputstream.readObject();
            objectinputstream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
