package marash.com.rebuspuzzle.pages.mainPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import marash.com.rebuspuzzle.R;

/**
 * Created by Maedeh on 3/21/2017.
 */

public class CoinBankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_bank);
    }

    public void buyCoin(View view) {

        switch (view.getId()){
            case R.id.smallCoinPrice:
                //TODO buying.
                break;
            case R.id.mediumCoinPrice:

                break;
            case R.id.largeCoinPrice:

                break;
        }
    }
}
