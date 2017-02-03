package marash.com.rebuspuzzle.selected_image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.main_page.GameCell_info;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class SelectedImageActivity extends AppCompatActivity {

    GameCell_info cellInfo;
    AlphabetChar[] solutionChars, alphabetChars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_image_activity);

        gamePlaying();

    }



    private void gamePlaying() {
        ImageView selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView

        cellInfo = (GameCell_info) getIntent().getSerializableExtra("gameCellInfo"); // get Intent which we set from Previous Activity

        selectedImage.setImageResource(cellInfo.getImageID()); // get image from Intent and set it in ImageView

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
            i++;
        }
        final CharacterAdapter solutionCharAdapter = new CharacterAdapter(getApplicationContext(), solutionChars);
        solutionGrid.setAdapter(solutionCharAdapter);
        GridView charGrid = (GridView) findViewById(R.id.characters);
        final CharacterAdapter charAdapter = new CharacterAdapter(getApplicationContext(), alphabetChars);
        charGrid.setAdapter(charAdapter);

        solutionGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlphabetChar clickedElement = solutionChars[position];
                if (clickedElement.getCharacter() == '#' || clickedElement.getCharacter() == ' ') {
                    return;
                }
                alphabetChars[clickedElement.getLocation()].setCharacter(clickedElement.getCharacter());
                clickedElement.setCharacter('#');
                clickedElement.setLocation(-1);
                solutionCharAdapter.notifyDataSetChanged();
                charAdapter.notifyDataSetChanged();

            }
        });
        charGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlphabetChar clickedElement = alphabetChars[position];
                if (clickedElement.getCharacter() == '#') {
                    return;
                }
                int availablePosition = getAvailablePosition();
                if (availablePosition == -1) {
                    return;
                }
                solutionChars[availablePosition].setCharacter(clickedElement.getCharacter());
                solutionChars[availablePosition].setLocation(position);
                clickedElement.setCharacter('#');
                solutionCharAdapter.notifyDataSetChanged();
                charAdapter.notifyDataSetChanged();
                //checking win condition.
                checkWinCondition();
            }
        });
    }

    private void checkWinCondition() {

        if(getAvailablePosition() == -1){
            String s = "";
            for(int i = 0; i < solutionChars.length; i++){
                s = s + solutionChars[i].getCharacter();
            }
            if(s.equals(cellInfo.getSolution())){
                Intent intent = new Intent(SelectedImageActivity.this, WinTransitionActivity.class);
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
