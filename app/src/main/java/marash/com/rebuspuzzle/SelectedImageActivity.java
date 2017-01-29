package marash.com.rebuspuzzle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class SelectedImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_image_activity);

        ImageView selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView

        GameCell_info cellInfo = (GameCell_info) getIntent().getSerializableExtra("gameCellInfo"); // get Intent which we set from Previous Activity

        selectedImage.setImageResource(cellInfo.imageID); // get image from Intent and set it in ImageView

        GridView charGrid = (GridView) findViewById(R.id.characters);
        CharacterAdapter charAdapter = new CharacterAdapter(getApplicationContext(), cellInfo.alphabets);
        charGrid.setAdapter(charAdapter);
        charGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //TextView t = (TextView) view;
                //t.setText(" ");
            }
        });


        userInterface();

    }


    public void userInterface() {

        Button s1 = (Button) findViewById(R.id.sol_1);
        s1.setText("f");
        Button s2 = (Button) findViewById(R.id.sol_2);
        s2.setText("k");
        Button s3 = (Button) findViewById(R.id.sol_3);
        s3.setText("b");


    }
}
