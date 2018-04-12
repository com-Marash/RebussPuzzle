package marash.com.rebuspuzzle.pages.gamePage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import marash.com.rebuspuzzle.AppClass;
import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.converter.ImageByteToBitmap;
import marash.com.rebuspuzzle.dto.AlphabetChar;
import marash.com.rebuspuzzle.dto.GameCell_info;
import marash.com.rebuspuzzle.dto.GameProgress;
import marash.com.rebuspuzzle.pages.mainPage.CoinBankActivity;
import marash.com.rebuspuzzle.pages.mainPage.MainActivity;
import marash.com.rebuspuzzle.repository.PlayerProgressRepository;

import static marash.com.rebuspuzzle.AppClass.gameCellArray;
import static marash.com.rebuspuzzle.AppClass.playerProgress;

/**
 * Created by Maedeh on 1/24/2017.
 */

public class GamePageActivity extends AppCompatActivity {

    int levelNumber;
    GameProgress gameProgress;
    AlphabetChar[] solutionChars;
    char[] alphabetChars;
    GameCell_info currentCell;
    ArrayList<GameProgress> gameProgressArray = AppClass.playerProgress.gameProgressArray;
    boolean isLoaded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_image_activity);

        gamePlaying();

    }

    private void gamePlaying() {

        if(!isLoaded) {
            ImageView selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
            TextView levelNumbertText = (TextView) findViewById(R.id.levelNumber);
            TextView userCoins = (TextView) findViewById(R.id.numberOfCoins);

            userCoins.setText(String.format("%s", String.valueOf(playerProgress.userMoney)));
            levelNumber = (int) getIntent().getSerializableExtra("gameCellLevelNumber"); // get Intent which we set from Previous Activity
            levelNumbertText.setText( "LEVEL " + levelNumber );

            currentCell = gameCellArray.get(levelNumber - 1);

            selectedImage.setImageBitmap(ImageByteToBitmap.convert(currentCell.getImage())); // get image from Intent and set it in ImageView

            GridView solutionGrid = (GridView) findViewById(R.id.sol_1);

            alphabetChars = currentCell.getAlphabets().clone();

            String currentSolution = currentCell.getSolution();

            //TODO this is just for words which is up to three parts.
            int sizeOfSolution = currentSolution.length();
            if (sizeOfSolution>8){
                if(currentSolution.contains(" ")){
                    int firstSpaceIndex = currentSolution.indexOf(" ");
                    int secondSpaceIndex = currentSolution.indexOf(" " , currentSolution.indexOf(" ") + 1);
                    if(firstSpaceIndex < 9 ){
                        if(firstSpaceIndex < 8){
                            if(secondSpaceIndex < 1) {
                                String[] tempArray = currentSolution.split(" ");
                                String result = tempArray[0];
                                int numberOfExtraSpace = 8 - firstSpaceIndex;
                                for (int i = 0; i < numberOfExtraSpace; i++) {
                                    result = result + " ";
                                }
                                result = result + tempArray[1];
                                currentSolution = result;
                            }else{
                                if(secondSpaceIndex < 9){
                                    if(secondSpaceIndex < 8){
                                        String[] tempArray = currentSolution.split(" ");
                                        String result = tempArray[0] + " " + tempArray[1];
                                        int numberOfExtraSpace = 8 - secondSpaceIndex;
                                        for (int i = 0; i < numberOfExtraSpace; i++) {
                                            result = result + " ";
                                        }
                                        result = result + tempArray[2];
                                        currentSolution = result;
                                    }
                                }else{
                                    String[] tempArray = currentSolution.split(" ");
                                    String result = tempArray[0];
                                    int numberOfExtraSpace = 8 - firstSpaceIndex;
                                    for (int i = 0; i < numberOfExtraSpace; i++) {
                                        result = result + " ";
                                    }
                                    result = result + tempArray[1];
                                    currentSolution = result+ " " + tempArray[2];

                                    if(secondSpaceIndex < 17){
                                        if(secondSpaceIndex < 16){
                                            int numberOfExtraSpace2 = 16 - secondSpaceIndex;
                                            for (int i = 0; i < numberOfExtraSpace2; i++) {
                                                result = result + " ";
                                            }
                                            result = result + tempArray[2];
                                            currentSolution = result;

                                        }else{
                                            // nemisazim injuri ke tule kalame 2 bozorgtar az 8 beshe
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        if(firstSpaceIndex == 9){
                            // no problem occure
                        }else{
                            // we pay attention to avoid setting up these kind of game. because first word is longer than 8 character.
                        }
                    }
                }else{

                }
            }else{
                //nothing
            }

            //TODO end

            solutionChars = new AlphabetChar[currentSolution.length()];


            int i = 0;
            for (char ch : currentSolution.toCharArray()) {
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
    }

    private void checkWinCondition() {

        if (getAvailablePosition() == -1) {
            String s = "";
            for (AlphabetChar a : solutionChars) {
                s = s + a.getCharacter();
            }
            //eliminatining all spaces between strings
            s = s.replaceAll("\\s+","");
            if (s.equals(currentCell.getSolution().replaceAll("\\s+",""))) {

                if(!gameProgressArray.get(levelNumber - 1).isSolved()){
                    gameProgressArray.get(levelNumber - 1).setSolved(true);
                    playerProgress.userMoney += 100;
                    PlayerProgressRepository.savePlayerProgress(this,playerProgress);
                }

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

    public void coinBank(View view) {
        Intent intent = new Intent(GamePageActivity.this,CoinBankActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GamePageActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void BackToHomePage(View view) {
        Intent intent = new Intent(GamePageActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void helpWanted(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(R.layout.help_page);
        builder.create();
        builder.show();
    }
}
