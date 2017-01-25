package marash.com.rebuspuzzle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    int logos[];
    LayoutInflater inflter;
    public CustomAdapter(Context applicationContext, int logos[]) {
        this.context = applicationContext;
        this.logos = logos;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.gridview_item, null); // inflate the layout
        ImageView defaultImage = (ImageView) view.findViewById(R.id.lockedImage); // get the reference of ImageView
        defaultImage.setImageResource(logos[i]); // set logo images
        return view;
    }


}
