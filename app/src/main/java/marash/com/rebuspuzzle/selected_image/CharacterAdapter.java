package marash.com.rebuspuzzle.selected_image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import marash.com.rebuspuzzle.R;


/**
 * Created by Maedeh on 1/27/2017.
 */

public class CharacterAdapter extends BaseAdapter {

    private AlphabetChar[] alphabetChars;
    private LayoutInflater inflater;

    public CharacterAdapter(Context applicationContext, AlphabetChar[] alphabetChars) {
        this.alphabetChars = alphabetChars;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return alphabetChars.length;
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
        ImageView iv = (ImageView) view.findViewById(R.id.simple_character); // get the reference of ImageView
        if (alphabetChars[i].getCharacter() == ' ') {
            iv.setVisibility(View.GONE);
        } else {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(GetIconID.getID(alphabetChars[i].getCharacter())); // set logo images
        }
        return view;
    }
}