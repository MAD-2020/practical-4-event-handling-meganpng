package sg.edu.np.WhackAMole;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Whack A Mole!";
    TextView resultTextView;
    int advscore;
    DecimalFormat df = new DecimalFormat("#");
    String advstrscore = df.format(advscore);
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    List<Button> buttonList = new ArrayList<Button>();
    private CountDownTimer readyTimer;
    private CountDownTimer locationTimer;
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */



    private void readyTimer(){
        readyTimer = new CountDownTimer(10000, 1000){
            @Override
            public void onTick(long millisUntilFinished){
                Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
                Toast.makeText(getApplicationContext(), "Get Ready In " +
                                millisUntilFinished/1000 + "seconds",
                        Toast.LENGTH_SHORT).show();
            }

            public void onFinish(){
                Log.v(TAG, "Ready CountDown Complete!");
                Toast.makeText(getApplicationContext(), "GO",
                        Toast.LENGTH_SHORT).show();
                placeMoleTimer();
                readyTimer.cancel();
            }
        };
        readyTimer.start();
    }
    private void placeMoleTimer(){
        locationTimer = new CountDownTimer(1000, 1000){
            @Override
            public void onTick(long millisUntilFinished){
                Log.v(TAG, "New Mole Location!");
                setNewMole();
            }

            public void onFinish(){
                locationTimer.start();
            }
        };
        locationTimer.start();
    }
    private static final int[] BUTTON_IDS = {
        R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9,
            R.id.button10, R.id.button11, R.id.button12
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.v(TAG, "Current User Score: " + String.valueOf(advscore));
        Intent fromMain = getIntent();
        advscore= fromMain.getIntExtra("Score", 0);
        resultTextView = findViewById(R.id.textView2);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);

        for(final int id : BUTTON_IDS){

        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAG, "Starting GUI!");

        resultTextView.setText(advstrscore);
        setNewMole();
        readyTimer();
        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.button4:
                        Log.v(TAG, " Button Top Left Clicked!");
                        doCheck(button4);
                        locationTimer.cancel();
                        locationTimer.start();
                        break;
                    case R.id.button5:
                        Log.v(TAG, "Button Top Middle Clicked!");
                        doCheck(button5);
                        locationTimer.cancel();
                        locationTimer.start();
                        break;
                    case R.id.button6:
                        Log.v(TAG, "Button Top Right Clicked!");
                        doCheck(button6);
                        locationTimer.cancel();
                        locationTimer.start();
                        break;
                    case R.id.button7:
                        Log.v(TAG, "Button Middle Left Clicked!");
                        doCheck(button7);
                        locationTimer.cancel();
                        locationTimer.start();
                        break;
                    case R.id.button8:
                        Log.v(TAG, "Button Middle Clicked!");
                        doCheck(button8);
                        locationTimer.cancel();
                        locationTimer.start();
                        break;
                    case R.id.button9:
                        Log.v(TAG, "Button Middle Right Clicked!");
                        doCheck(button9);
                        locationTimer.cancel();
                        locationTimer.start();
                        break;
                    case R.id.button10:
                        Log.v(TAG, "Button Bottom Left Clicked!");
                    doCheck(button10);
                    break;
                    case R.id.button11:
                        Log.v(TAG, "Button Middle Bottom Clicked!");
                        doCheck(button11);
                        break;
                    case R.id.button12:
                        Log.v(TAG, "Button Bottom Right Clicked!");
                        doCheck(button12);
                        break;


                }
            }
        };
        button4.setOnClickListener(onclick);
        button5.setOnClickListener(onclick);
        button6.setOnClickListener(onclick);
        button7.setOnClickListener(onclick);
        button8.setOnClickListener(onclick);
        button9.setOnClickListener(onclick);
        button10.setOnClickListener(onclick);
        button11.setOnClickListener(onclick);
        button12.setOnClickListener(onclick);
    }

    private void doCheck(Button checkButton)
    {
        if(checkButton.getText() == "*"){
            advscore += 1;
            advstrscore = df.format(advscore);
            resultTextView.setText(advstrscore);
            Log.v(TAG, "Hit, score added!\n");
            setNewMole();
        }else {
            advscore -= 1;
            advstrscore = df.format(advscore);
            resultTextView.setText(advstrscore);
            Log.v(TAG, "Missed, score deducted!\n");
            setNewMole();
        }
    }

    public void setNewMole()
    {
        button4.setText("O");
        button5.setText("O");
        button6.setText("O");
        button7.setText("O");
        button8.setText("O");
        button9.setText("O");
        button10.setText("O");
        button11.setText("O");
        button12.setText("O");
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        if(randomLocation == 0){
            button4.setText("*");
        }
        else if(randomLocation == 1){
            button5.setText("*");
        }
        else if(randomLocation == 2){
            button6.setText("*");
        }
        else if(randomLocation == 3){
            button7.setText("*");
        }
        else if(randomLocation == 4){
            button8.setText("*");
        }
        else if(randomLocation == 5){
            button9.setText("*");
        }
        else if(randomLocation == 6){
            button10.setText("*");
        }
        else if(randomLocation == 7){
            button11.setText("*");
        }
        else if(randomLocation == 8){
            button12.setText("*");
        }
    }
}

