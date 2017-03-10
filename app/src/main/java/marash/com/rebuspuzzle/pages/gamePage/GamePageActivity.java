package marash.com.rebuspuzzle.pages.gamePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.converter.ImageByteToBitmap;
import marash.com.rebuspuzzle.dto.AlphabetChar;
import marash.com.rebuspuzzle.dto.GameCellInfo;
import marash.com.rebuspuzzle.dto.GameProgress;
import marash.com.rebuspuzzle.pages.mainPage.MainActivity;

import static marash.com.rebuspuzzle.AppClass.gameCellArray;
import static marash.com.rebuspuzzle.AppClass.gameProgressArray;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class GamePageActivity extends AppCompatActivity {

    int levelNumber;
    GameProgress gameProgress;
    AlphabetChar[] solutionChars;
    char[] alphabetChars;
    GameCellInfo currentCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_image_activity);

        gamePlaying();

    }

    private void gamePlaying() {
        ImageView selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView

        levelNumber = (int) getIntent().getSerializableExtra("gameCellLevelNumber"); // get Intent which we set from Previous Activity
        currentCell = gameCellArray.get(levelNumber-1);
        gameProgress = new GameProgress();

        selectedImage.setImageBitmap(ImageByteToBitmap.convert(currentCell.getImage())); // get image from Intent and set it in ImageView

        GridView solutionGrid = (GridView) findViewById(R.id.sol_1);

        solutionChars = new AlphabetChar[currentCell.getSolution().length()];
        alphabetChars = currentCell.getAlphabets().clone();

        int i = 0;
        for (char ch : currentCell.getSolution().toCharArray()) {
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
            for (AlphabetChar a : solutionChars) {
                s = s + a.getCharacter();
            }
            if (s.equals(currentCell.getSolution())) {
                //TODO make next level unlock
                gameProgressArray.get(levelNumber-1).setSolved(true);

                if (levelNumber < gameCellArray.size()) {
                    gameProgressArray.get(levelNumber).setLocked(false);
                    Intent intent = new Intent(GamePageActivity.this, WinTransitionActivity.class);
                    intent.putExtra("nextLevelNumber", (levelNumber + 1));
                    startActivity(intent);
                } else {
                    //TODO Game finished for All levels.
                }
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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GamePageActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
