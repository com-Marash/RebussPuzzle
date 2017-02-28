package marash.com.rebuspuzzle.selected_image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.converter.ImageByteToBitmap;
import marash.com.rebuspuzzle.dto.GameCell_info;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class SelectedImageActivity extends AppCompatActivity {

    GameCell_info cellInfo;
    AlphabetChar[] solutionChars;
    char[] alphabetChars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_image_activity);

        gamePlaying();

    }

    private void gamePlaying() {
        ImageView selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView

        cellInfo = (GameCell_info) getIntent().getSerializableExtra("gameCellInfo"); // get Intent which we set from Previous Activity

        selectedImage.setImageBitmap(ImageByteToBitmap.convert(cellInfo.getImage())); // get image from Intent and set it in ImageView

        GridView solutionGrid = (GridView) findViewById(R.id.sol_1);

        solutionChars = new AlphabetChar[cellInfo.getSolution().length()];
        alphabetChars = cellInfo.getAlphabets().clone();

        int i = 0;
        for (char ch : cellInfo.getSolution().toCharArray()) {
            if (ch != ' ') {
                solutionChars[i] = new AlphabetChar('#', -1);
            } else {
                solutionChars[i] = new AlphabetChar(' ', -1);
            }
            solutionChars[i].setLocation(-1);
            i++;
        }
        final AlphabetCharAdapter solutionCharAdapter = new AlphabetCharAdapter(getApplicationContext(), solutionChars);
        solutionGrid.setAdapter(solutionCharAdapter);
        GridView charGrid = (GridView) findViewById(R.id.characters);
        final CharacterAdapter charAdapter = new CharacterAdapter(getApplicationContext(), alphabetChars);
        charGrid.setAdapter(charAdapter);

        solutionGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                char clickedElement = solutionChars[position].getCharacter();
                if (clickedElement == '#' || clickedElement == ' ') {
                    return;
                }
                alphabetChars[solutionChars[position].getLocation()] = clickedElement;
                solutionChars[position].setCharacter('#');
                solutionChars[position].setLocation(-1);
                solutionCharAdapter.notifyDataSetChanged();
                charAdapter.notifyDataSetChanged();

            }
        });
        charGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (alphabetChars[position] == '#') {
                    return;
                }
                int availablePosition = getAvailablePosition();
                if (availablePosition == -1) {
                    return;
                }
                solutionChars[availablePosition].setCharacter(alphabetChars[position]);
                solutionChars[availablePosition].setLocation(position);
                alphabetChars[position] = '#';
                solutionCharAdapter.notifyDataSetChanged();
                charAdapter.notifyDataSetChanged();
                //checking win condition.
                checkWinCondition();
            }
        });
    }

    private void checkWinCondition() {

        if (getAvailablePosition() == -1) {
            String s = "";
            for (AlphabetChar a:solutionChars) {
                s = s + a.getCharacter();
            }
            if (s.equals(cellInfo.getSolution())) {
                Intent intent = new Intent(SelectedImageActivity.this, WinTransitionActivity.class);
                intent.putExtra("nextLevelPosition", (cellInfo.getLevelNumber() + 1));
                startActivity(intent);
            }
        }

    }

    private int getAvailablePosition() {
        for (int i = 0; i < solutionChars.length; i++) {
            if (solutionChars[i].getCharacter() == '#') {
                return i;
            }
        }
        return -1;
    }

}
