package marash.com.rebuspuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class SelectedImageActivity extends AppCompatActivity {

    char[] alphabets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_puzzle_activity);

        ImageView selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView

        Intent intent = getIntent(); // get Intent which we set from Previous Activity

        selectedImage.setImageResource(intent.getIntExtra("imageID", 0)); // get image from Intent and set it in ImageView

        alphabets = intent.getCharArrayExtra("imageAlphabet");
        GridView charGrid = (GridView) findViewById(R.id.characters);
        CharacterAdapter charAdapter = new CharacterAdapter(getApplicationContext(), alphabets);
        charGrid.setAdapter(charAdapter);
        charGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView t = (TextView) view;
                t.setText(" ");
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
