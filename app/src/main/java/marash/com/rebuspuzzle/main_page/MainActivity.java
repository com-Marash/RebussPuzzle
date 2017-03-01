package marash.com.rebuspuzzle.main_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.selected_image.SelectedImageActivity;

import static marash.com.rebuspuzzle.dto.DataBaseCreator.gameCellArray;

public class MainActivity extends AppCompatActivity {

    GridView gameGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = (GridView) findViewById(R.id.gameGridView);

        GameAdapter gameAdapter = new GameAdapter(getApplicationContext(), gameCellArray);
        gameGrid.setAdapter(gameAdapter);

        gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (gameCellArray.get(position).isLocked()) {
                    //TODO show message that this level is lock!
                } else if (!gameCellArray.get(position).isSolved()) {
                    //TODO open the puzzle for that picture.
                    Intent intent = new Intent(MainActivity.this, SelectedImageActivity.class);
                    intent.putExtra("gameCellInfo", gameCellArray.get(position)); // put gameCellInfo in Intent
                    startActivity(intent); // start Intent
                }
            }
        });
    }
}
