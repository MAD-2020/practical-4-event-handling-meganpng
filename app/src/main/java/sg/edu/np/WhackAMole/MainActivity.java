package sg.edu.np.WhackAMole;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Whack A Mole!";
    TextView resultTextView;
    int score = 0;
    DecimalFormat df = new DecimalFormat("#");
    String strscore = df.format(score);
    Button button1;
    Button button2;
    Button button3;

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "Finished Pre-Initialisation!");
        resultTextView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

    }
    @Override
    protected void onStart(){
        super.onStart();

        Log.v(TAG, "Starting GUI!");
        resultTextView.setText(strscore);
        setNewMole();
        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        Log.v(TAG, "Button Left Clicked!");
                        doCheck(button1);
                        break;
                    case R.id.button2:
                        Log.v(TAG, "Button Middle Clicked!");
                        doCheck(button2);
                        break;
                    case R.id.button3:
                        Log.v(TAG, "Button Right Clicked!");
                        doCheck(button3);
                        break;


                }
            }
        };
        button1.setOnClickListener(onclick);
        button2.setOnClickListener(onclick);
        button3.setOnClickListener(onclick);
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
        if(checkButton.getText() == "*"){
            score += 1;
            strscore = df.format(score);
            resultTextView.setText(strscore);
            Log.v(TAG, "Hit, score added!\n");
            setNewMole();
        }else {
            score -= 1;
            strscore = df.format(score);
            resultTextView.setText(strscore);
            Log.v(TAG, "Missed, score deducted!\n");
            setNewMole();
        }

        if(score % 10 == 0 && score > 0){
            nextLevelQuery();
        }

    }

    private void nextLevelQuery(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning! Insane Whack-A-Mole incoming!");
        Log.v(TAG, "Advance option given to user!");
        builder.setMessage("Would you like to advance to the advanced mode?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Log.v(TAG, "User accepts!");
                nextLevel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Log.v(TAG, "User decline!");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void nextLevel(){
        /* Launch advanced page */
        Intent advancedLevel = new Intent(this, Main2Activity.class);
        advancedLevel.putExtra("Score", score);
        startActivity(advancedLevel);
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        if(randomLocation == 0){
            button1.setText("*");
            button2.setText("O");
            button3.setText("O");
        }
        else if(randomLocation == 1){
            button2.setText("*");
            button1.setText("O");
            button3.setText("O");
        }
        else if(randomLocation == 2){
            button3.setText("*");
            button2.setText("O");
            button1.setText("O");
        }
    }

}
