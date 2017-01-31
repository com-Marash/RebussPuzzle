package marash.com.rebuspuzzle.SelectedImage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import marash.com.rebuspuzzle.R;


/**
 * Created by Maedeh on 1/27/2017.
 */

public class CharacterAdapter extends BaseAdapter {

    private char[] chars;
    private LayoutInflater inflater;

    public CharacterAdapter(Context applicationContext, char[] chars) {
        this.chars = chars;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return chars.length;
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
            view = inflater.inflate(R.layout.gridview_charachters, parent, false); // inflate the layout
        }
        TextView tv = (TextView) view.findViewById(R.id.simple_character); // get the reference of ImageView
        tv.setText(chars[i] + ""); // set logo images
        return view;
    }
}