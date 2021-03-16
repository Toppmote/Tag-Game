package toppmote.games.gamefifteensjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void StartButtonClick(View view){
        Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(settings);
        finish();
    }

    public void RecButtonClick(View view){
        Intent settings = new Intent(MainActivity.this, RecordActivity.class);
        startActivity(settings);
        finish();
    }
}
