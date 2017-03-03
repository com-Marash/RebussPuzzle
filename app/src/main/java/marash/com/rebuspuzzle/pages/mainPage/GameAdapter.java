package marash.com.rebuspuzzle.pages.mainPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.converter.ImageByteToBitmap;
import marash.com.rebuspuzzle.dto.GameCellInfo;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class GameAdapter extends BaseAdapter {

    private ArrayList<GameCellInfo> logos;
    private LayoutInflater inflater;

    public GameAdapter(Context applicationContext, ArrayList<GameCellInfo> logos) {
        this.logos = logos;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return logos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.gridview_item, parent, false); // inflate the layout
        }
        ImageView defaultImage = (ImageView) view.findViewById(R.id.CellImage); // get the reference of ImageView
        defaultImage.setImageBitmap(ImageByteToBitmap.convert(logos.get(i).getImage()));
        return view;
    }
}
