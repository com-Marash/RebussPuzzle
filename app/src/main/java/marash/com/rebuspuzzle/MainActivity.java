package marash.com.rebuspuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gameGrid;

    ArrayList<GameCell_info> gameImages = new ArrayList<GameCell_info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = (GridView) findViewById(R.id.gameGridView);

        gameCreator();

        GameAdapter gameAdapter = new GameAdapter(getApplicationContext(), gameImages);
        gameGrid.setAdapter(gameAdapter);

        gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (gameImages.get(position).isLocked) {
                    //TODO show message that this level is lock!
                } else if (!gameImages.get(position).isSolved) {
                    //TODO open the puzzle for that picture.
                    Intent intent = new Intent(MainActivity.this, SelectedImageActivity.class);
                    intent.putExtra("gameCellInfo", gameImages.get(position)); // put gameCellInfo in Intent
                    startActivity(intent); // start Intent
                }
            }
        });
    }

    public void gameCreator() {

        GameCell_info g_1 = new GameCell_info(R.drawable.carrot, "carrot", false, false, new char[]{'c', 'r', 'r', 'a', 'o', 't', 'g', 'f'});
        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
    }
}
