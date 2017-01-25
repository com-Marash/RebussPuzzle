package marash.com.rebuspuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gameGrid;
    int lockedImages[] = {R.drawable.lock, R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,
            R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,
            R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,
            R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,R.drawable.lock,};

    ArrayList<Integer> dynamicImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = (GridView) findViewById(R.id.gameGridView);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), lockedImages);
        gameGrid.setAdapter(customAdapter);

        gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(lockedImages[i] == R.drawable.lock){
                    //TODO show message that this level is lock!
                }else{
                    //TODO open the puzzle for that picture.
                    Intent intent = new Intent(MainActivity.this, SelectedImageActivity.class);
                    intent.putExtra("image", lockedImages[i]); // put image data in Intent
                    startActivity(intent); // start Intent
                }
            }
        });
    }
}
