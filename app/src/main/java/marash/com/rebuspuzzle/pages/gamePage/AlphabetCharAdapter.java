package marash.com.rebuspuzzle.pages.gamePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.converter.CharToImageConverter;
import marash.com.rebuspuzzle.dto.AlphabetChar;


/**
 * Created by Maedeh on 1/27/2017.
 */

public class AlphabetCharAdapter extends BaseAdapter {

    private AlphabetChar[] alphabetCharList;
    private LayoutInflater inflater;

    public AlphabetCharAdapter(Context applicationContext, AlphabetChar[] alphabetCharList) {
        this.alphabetCharList = alphabetCharList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return alphabetCharList.length;
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
        if (alphabetCharList[i].getCharacter() == ' ') {
            iv.setVisibility(View.GONE);
        } else {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(CharToImageConverter.getID(alphabetCharList[i].getCharacter(), view)); // set logo images
        }
        return view;
    }
}