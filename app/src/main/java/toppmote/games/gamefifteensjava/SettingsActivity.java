package toppmote.games.gamefifteensjava;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    boolean Mod;//Режим
    int size;//Размерность
    int diff;//Сложность
    Button[] btnSize;//Кнопки размеров
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnSize = new Button[]{findViewById(R.id.button2x3), findViewById(R.id.button2x4), findViewById(R.id.button2x5),
                findViewById(R.id.button2x6), findViewById(R.id.button2x7), findViewById(R.id.button2x8),
                findViewById(R.id.button3x3), findViewById(R.id.button3x4), findViewById(R.id.button3x5), findViewById(R.id.button4x4)};
    }
    //Кнопка назад
    public void ButtonBackClick(View view) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }
    //Кнопка помощь
    public void ButtonHelpClick(View view) {
        Intent help = new Intent(this, HelpActivity.class);
        startActivity(help);
    }

    @Override
    public void onBackPressed()
    {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }
    //Кнопки режимов
    public void ButtonClassicClick(View view) {
        Mod = false;
        Button btn = findViewById(R.id.buttonMagic);
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
        btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        for (Button bn: btnSize
        ) {
            bn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
            bn.setEnabled(true);
        }
        btnSize[size].setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void ButtonMagicClick(View view) {
        Mod = true;
        Button btn = findViewById(R.id.ClassicButton);
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
        btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        for (Button bn: btnSize
        ) {
            bn.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
            bn.setEnabled(false);
        }
        btnSize[6].setEnabled(true);
        btnSize[9].setEnabled(true);
        if(size == 6)
        {
            btnSize[6].setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
            btnSize[9].setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        else if(size == 9)
        {
            btnSize[6].setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
            btnSize[9].setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
        }
        else{
            btnSize[6].setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
            btnSize[9].setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
    }
    //Кнопки размеров
    public void Button2x3Click(View view) {
        size = 0;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button2x4Click(View view) {
        size = 1;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button2x5Click(View view) {
        size = 2;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button2x6Click(View view) {
        size = 3;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button2x7Click(View view) {
        size = 4;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button2x8Click(View view) {
        size = 5;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button3x3Click(View view) {
        size = 6;
        if (!Mod)
            for (Button btn : btnSize
            ) {
                btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
            }
        else
            btnSize[9].setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button3x4Click(View view) {
        size = 7;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button3x5Click(View view) {
        size = 8;
        for (Button btn: btnSize
        ) {
            btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        }
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void Button4x4Click(View view) {
        size = 9;
        if (!Mod)
            for (Button btn : btnSize
            ) {
                btn.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
            }
        else
            btnSize[6].setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }
    //Кнопки сложностей
    public void ButtonEzClick(View view) {
        diff = 0;
        Button b1 = findViewById(R.id.buttonNormal);
        Button b2 = findViewById(R.id.buttonHard);
        b1.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        b2.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void ButtonNormalClick(View view) {
        diff = 1;
        Button b1 = findViewById(R.id.buttonEz);
        Button b2 = findViewById(R.id.buttonHard);
        b1.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        b2.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }

    public void ButtonHardClick(View view) {
        diff = 2;
        Button b1 = findViewById(R.id.buttonEz);
        Button b2 = findViewById(R.id.buttonNormal);
        b1.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        b2.setBackground(getResources().getDrawable(R.drawable.non_pushed_button_green));
        view.setBackground(getResources().getDrawable(R.drawable.pushed_button_green));
    }
    //Кнопка старта
    public void ButtonStartGameClick(View view) {
        Intent GameField = new Intent(this, toppmote.games.gamefifteensjava.GameField.class);
        if (Mod) {
            switch (size) {
                case 6:
                    GameField.putExtra("sizeX", 3);
                    GameField.putExtra("sizeY", 3);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 20);
                    GameField.putExtra("Min", 2);
                    GameField.putExtra("Steps", 150);
                    GameField.putExtra("size", 342);
                    GameField.putExtra("t_size", 75);
                    GameField.putExtra("rowP", 10);
                    startActivity(GameField);
                    break;
                case 9:
                    GameField.putExtra("sizeX", 4);
                    GameField.putExtra("sizeY", 4);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 0);
                    GameField.putExtra("Min", 3);
                    GameField.putExtra("Steps", 250);
                    GameField.putExtra("size", 250);
                    GameField.putExtra("t_size", 50);
                    GameField.putExtra("rowP", 11);
                    startActivity(GameField);
                    break;
            }
        } else {
            switch (size) {
                case 0:
                    GameField.putExtra("sizeX", 2);
                    GameField.putExtra("sizeY", 3);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 20);
                    GameField.putExtra("Min", 0);
                    GameField.putExtra("Steps", 20);
                    GameField.putExtra("size", 510);
                    GameField.putExtra("t_size", 100);
                    GameField.putExtra("rowP", 0);
                    startActivity(GameField);
                    break;
                case 1:
                    GameField.putExtra("sizeX", 2);
                    GameField.putExtra("sizeY", 4);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 40);
                    GameField.putExtra("Min", 0);
                    GameField.putExtra("Steps", 60);
                    GameField.putExtra("size", 410);
                    GameField.putExtra("t_size", 87);
                    GameField.putExtra("rowP", 1);
                    startActivity(GameField);
                    break;
                case 2:
                    GameField.putExtra("sizeX", 2);
                    GameField.putExtra("sizeY", 5);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 50);
                    GameField.putExtra("Min", 0);
                    GameField.putExtra("Steps", 100);
                    GameField.putExtra("size", 320);
                    GameField.putExtra("t_size", 67);
                    GameField.putExtra("rowP", 2);
                    startActivity(GameField);
                    break;
                case 3:
                    GameField.putExtra("sizeX", 2);
                    GameField.putExtra("sizeY", 6);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 0);
                    GameField.putExtra("Min", 1);
                    GameField.putExtra("Steps", 120);
                    GameField.putExtra("size", 265);
                    GameField.putExtra("t_size", 52);
                    GameField.putExtra("rowP", 3);
                    startActivity(GameField);
                    break;
                case 4:
                    GameField.putExtra("sizeX", 2);
                    GameField.putExtra("sizeY", 7);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 30);
                    GameField.putExtra("Min", 1);
                    GameField.putExtra("Steps", 140);
                    GameField.putExtra("size", 230);
                    GameField.putExtra("t_size", 40);
                    GameField.putExtra("rowP", 4);
                    startActivity(GameField);
                    break;
                case 5:
                    GameField.putExtra("sizeX", 2);
                    GameField.putExtra("sizeY", 8);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 0);
                    GameField.putExtra("Min", 2);
                    GameField.putExtra("Steps", 170);
                    GameField.putExtra("size", 200);
                    GameField.putExtra("t_size", 38);
                    GameField.putExtra("rowP", 5);
                    startActivity(GameField);
                    break;
                case 6:
                    GameField.putExtra("sizeX", 3);
                    GameField.putExtra("sizeY", 3);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 0);
                    GameField.putExtra("Min", 2);
                    GameField.putExtra("Steps", 100);
                    GameField.putExtra("size", 342);
                    GameField.putExtra("t_size", 75);
                    GameField.putExtra("rowP", 6);
                    startActivity(GameField);
                    break;
                case 7:
                    GameField.putExtra("sizeX", 3);
                    GameField.putExtra("sizeY", 4);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 20);
                    GameField.putExtra("Min", 2);
                    GameField.putExtra("Steps", 150);
                    GameField.putExtra("size", 342);
                    GameField.putExtra("t_size", 72);
                    GameField.putExtra("rowP", 7);
                    startActivity(GameField);
                    break;
                case 8:
                    GameField.putExtra("sizeX", 3);
                    GameField.putExtra("sizeY", 5);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 40);
                    GameField.putExtra("Min", 2);
                    GameField.putExtra("Steps", 170);
                    GameField.putExtra("size", 315);
                    GameField.putExtra("t_size", 65);
                    GameField.putExtra("rowP", 8);
                    startActivity(GameField);
                    break;
                case 9:
                    GameField.putExtra("sizeX", 4);
                    GameField.putExtra("sizeY", 4);
                    GameField.putExtra("diff", diff);
                    GameField.putExtra("Mod", Mod);
                    GameField.putExtra("Sec", 0);
                    GameField.putExtra("Min", 3);
                    GameField.putExtra("Steps", 200);
                    GameField.putExtra("size", 250);
                    GameField.putExtra("t_size", 50);
                    GameField.putExtra("rowP", 9);
                    startActivity(GameField);
                    break;
            }
        }
    }
}
