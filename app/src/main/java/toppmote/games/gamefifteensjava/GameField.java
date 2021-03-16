package toppmote.games.gamefifteensjava;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;


public class GameField extends AppCompatActivity {
    int diff, constantSec, constantMin, constantSteps;
    GameMech f;//Класс игровой механики
    boolean Mod;
    Button[][] buttons;//Поле
    TextView steps;
    Chronometer time;//Таймер
    Dialog btnDialog;
    TextView dialogTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_field);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle param = getIntent().getExtras();
        int sizeX = param.getInt("sizeX");
        int sizeY = param.getInt("sizeY");
        int bSize = param.getInt("size");
        int tSize = param.getInt("t_size");
        int rowP = param.getInt("rowP");
        diff = param.getInt("diff");
        Mod = param.getBoolean("Mod");
        time = findViewById(R.id.time);
        steps = findViewById(R.id.steps);
        btnDialog = new Dialog(GameField.this);
        btnDialog.setContentView(R.layout.dialog_lose);
        dialogTv = (TextView) btnDialog.findViewById(R.id.text);
        final Dialog dialog = new Dialog(GameField.this);
        dialog.setContentView(R.layout.dialog_layout);
        final TextView textTime = (TextView) dialog.findViewById(R.id.text);
        time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                f.Tick();
            }
        });
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.Click((Button) v);
                boolean end;
                if (Mod)
                    end = f.CheckMagic();
                else
                    end = f.CheckClassic();
                if (end) {
                    time.stop();
                    String time = f.Win();
                    textTime.setText("Вы собрали пятнашки.\nВремя: " + time + ".");
                    dialog.show();
                }
            }
        };
        //Создание поля
        buttons = new Button[sizeY][sizeX];
        LinearLayout FieldCont = findViewById(R.id.GameButtons);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams ButtonParams = new LinearLayout.LayoutParams(bSize, bSize);
        ButtonParams.setMargins(5, 5, 5, 5);
        LinearLayout[] layouts = new LinearLayout[sizeY];
        for (int i = 0; i < sizeY; i++) {
            layouts[i] = new LinearLayout(this);
            layouts[i].setOrientation(LinearLayout.HORIZONTAL);
            layouts[i].setLayoutParams(params);
            layouts[i].setGravity(Gravity.CENTER);
            FieldCont.addView(layouts[i], params);
        }
        int max = sizeX * sizeY - 1;
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                buttons[i][j] = new Button(this);
                if (i * sizeX + j != max) {
                    buttons[i][j].setBackground(getResources().getDrawable(R.drawable.button_status_style_green));
                    buttons[i][j].setText(String.format(Locale.US, "%d", i * sizeX + j + 1));
                    buttons[i][j].setTag(i * sizeX + j);
                } else {
                    buttons[i][j].setBackgroundColor(getResources().getColor(R.color.Black));
                    buttons[i][j].setEnabled(false);
                }
                buttons[i][j].setTextSize(tSize);
                buttons[i][j].setTextColor(getResources().getColor(R.color.White));
                buttons[i][j].setStateListAnimator(null);
                buttons[i][j].setOnClickListener(click);
                layouts[i].addView(buttons[i][j], ButtonParams);
            }
        }
        if (Mod){
            String text = buttons[0][0].getText().toString();
            buttons[0][0].setText(buttons[0][1].getText());
            buttons[0][1].setText(text);
        }
        //Создание объекта одного из наследуемых классов
        if (diff == 0) {
            constantSec = 0;
            constantMin = 0;
            constantSteps = 0;
            time.setText(String.format(Locale.US, "%dс", 0));
            steps.setText(String.format(Locale.US, "%d", constantSteps));
            f = new Ez(sizeX, sizeY, constantSec, constantMin, constantSteps, GameField.this, time, Mod, diff, steps, buttons, rowP);

        } else if (diff == 1) {
            constantSec = 0;
            constantMin = 0;
            constantSteps = param.getInt("Steps");
            time.setText(String.format(Locale.US, "%dс", 0));
            steps.setText(String.format(Locale.US, "%d", constantSteps));
            f = new Normal(sizeX, sizeY, constantMin, constantSec, constantSteps, GameField.this, time, Mod, diff, steps, buttons, rowP);
        } else {
            constantSec = param.getInt("Sec");
            constantMin = param.getInt("Min");
            constantSteps = param.getInt("Steps");
            if(constantMin == 0)
                time.setText(String.format(Locale.US, "%dс", constantSec));
            else if(constantSec != 0)
                time.setText(String.format(Locale.US, "%dм %dс", constantMin, constantSec));
            else time.setText(String.format(Locale.US, "%dм", constantMin));
            steps.setText(String.format(Locale.US, "%d", constantSteps));
            f = new Hard(sizeX, sizeY, constantMin, constantSec, constantSteps, GameField.this, time, Mod, diff, steps, buttons, rowP);
        }
        f.Shuffle();
    }
    //Кнопка назад
    public void ButtonBackClick(View view) {
        dialogTv.setText("Вы действительно хотите выйти?\nНесохраненный прогресс будет потерян.");
        dialogTv.setTextSize(16);
        Button btnYes = (Button) btnDialog.findViewById(R.id.buttonYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btnNo = (Button) btnDialog.findViewById(R.id.buttonNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDialog.dismiss();
            }
        });
        btnDialog.show();
    }
    //Кнопка рестарт
    public void ButtonResClick(View view){
        dialogTv.setText("Вы действительно начать игру заново?\nНесохраненный прогресс будет потерян.");
        dialogTv.setTextSize(16);
        Button btnYes = (Button) btnDialog.findViewById(R.id.buttonYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.Restart();
                btnDialog.dismiss();
            }
        });
        Button btnNo = (Button) btnDialog.findViewById(R.id.buttonNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDialog.dismiss();
            }
        });
        btnDialog.show();
    }
    @Override
    public void onBackPressed()
    {
        dialogTv.setText("Вы действительно хотите выйти?\nНесохраненный прогресс будет потерян.");
        dialogTv.setTextSize(16);
        Button btnYes = (Button) btnDialog.findViewById(R.id.buttonYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btnNo = (Button) btnDialog.findViewById(R.id.buttonNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDialog.dismiss();
            }
        });
        btnDialog.show();
    }
}
