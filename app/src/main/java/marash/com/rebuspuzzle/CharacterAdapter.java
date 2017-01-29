package marash.com.rebuspuzzle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by Maedeh on 1/27/2017.
 */

public class CharacterAdapter extends BaseAdapter {

    Context context;
    char[] chars;
    LayoutInflater inflator;

    public CharacterAdapter(Context applicationContext, char[] chars) {
        this.context = applicationContext;
        this.chars = chars;
        inflator = (LayoutInflater.from(applicationContext));
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflator.inflate(R.layout.gridview_charachters, null); // inflate the layout
        TextView tv = (TextView) view.findViewById(R.id.simple_character); // get the reference of ImageView

        tv.setText("hello"); // set logo images
        return view;
    }
}