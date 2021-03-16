package toppmote.games.gamefifteensjava;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public abstract class GameMech {
    protected int SizeX, SizeY, diff, row;
    protected int timeSec, timeMin, steps;
    protected int constantSec, constantMin, constantSteps;
    protected boolean Mod;
    protected Chronometer time;//Таймер
    protected TextView step;
    protected Button[][] buttons;//Поле

    GameMech (int x, int y, int min, int sec, int stp, boolean mod, int df, Chronometer tm, TextView sl, Button[][] btns, int rowP)
    {
        SizeX = x;
        SizeY = y;
        timeMin = min;
        timeSec = sec;
        steps = stp;
        constantMin = min;
        constantSec = sec;
        constantSteps = stp;
        Mod = mod;
        diff = df;
        time = tm;
        step = sl;
        buttons = btns;
        row = rowP;
    }
    public abstract void Tick(); //Тик таймера
    public abstract String Win();//Функция победы
    public abstract void Click(Button btn);//Нажатие кнопки
    //Проверка классики
    public boolean CheckClassic() {
        int count = 0;
        boolean f = false;
        for (int i = 0; i < SizeY; i++) {
            for (int j = 0; j < SizeX; j++) {
                Object bTag = buttons[i][j].getTag();
                if (buttons[i][j].getText().equals("")) {
                    f = true;
                    break;
                } else {
                    String tValue = buttons[i][j].getText().toString();
                    int txt = Integer.parseInt(tValue);
                    int tag = (Integer) bTag;
                    tag += 1;
                    if (tag == txt)
                        count++;
                    else {
                        f = true;
                        break;
                    }
                }
            }
            if (f)
                break;
        }
        if (count == SizeX * SizeY - 1) {
            for (Button[] btn : buttons
            ) {
                for (Button bn : btn
                ) {
                    bn.setEnabled(false);
                }
            }
            return true;
        } else return false;
    }
    //Проверка магического квадрата
    public boolean CheckMagic() {
        boolean f = true;
        int summ, magnum;
        if (SizeX == 4)
            magnum = 30;
        else magnum = 12;
        for (int i = 0; i < SizeX; i++) {
            summ = 0;
            for (int j = 0; j < SizeX; j++)
                if (!buttons[i][j].getText().equals("")) {
                    String tValue = buttons[i][j].getText().toString();
                    int slag = Integer.parseInt(tValue);
                    summ += slag;
                }
            if (summ != magnum) {
                f = false;
                break;
            }
        }
        if (f)
            for (int j = 0; j < SizeX; j++) {
                summ = 0;
                for (int i = 0; i < SizeX; i++)
                    if (!buttons[i][j].getText().equals("")) {
                        String tValue = buttons[i][j].getText().toString();
                        int slag = Integer.parseInt(tValue);
                        summ += slag;
                    }
                if (summ != magnum) {
                    f = false;
                    break;
                }
            }
        if (f)
        {
            for (Button[] btn : buttons
            ) {
                for (Button bn : btn
                ) {
                    bn.setEnabled(false);
                }
            }
            return true;
        } else return false;
    }
    //Рестарт
    void Restart()
    {
        if (diff == 0) {
            timeSec = 0;
            timeMin = 0;
            steps = 0;
            step.setText(String.format(Locale.US, "%d", 0));
            time.setText(String.format(Locale.US, "%dс", 0));
        }
        else if (diff == 1) {
            timeSec = 0;
            timeMin = 0;
            steps = constantSteps;
            step.setText(String.format(Locale.US, "%d", constantSteps));
            time.setText(String.format(Locale.US, "%dс", 0));
        }
        else {
            timeSec = constantSec;
            timeMin = constantMin;
            steps = constantSteps;
            step.setText(String.format(Locale.US, "%d", constantSteps));
            if (constantMin == 0)
                time.setText(String.format(Locale.US, "%dс", constantSec));
            else if (constantSec == 0)
                time.setText(String.format(Locale.US, "%dм", constantMin));
            else time.setText(String.format(Locale.US, "%dм %dс", constantMin, constantSec));
        }
        time.stop();
        Shuffle();
    }
    //Перемешивание
    void Shuffle(){
        Random rand = new Random();
        for (int shCount = 0; shCount < 500; shCount++)
            for (int i = 0; i < SizeY; i++)
                for (int j = 0; j < SizeX; j++)
                    if (buttons[i][j].getText().equals("")) {

                        if ((j == SizeX - 1) && (i == SizeY - 1))
                        {
                            int sh = rand.nextInt(2);
                            switch (sh)
                            {
                                case 0:
                                    Up (j, i);
                                    break;
                                case 1:
                                    Left(j, i);
                                    break;
                            }
                            break;
                        }
                        if ((i == 0) && (j == 0))
                        {
                            int sh = rand.nextInt(2);
                            switch (sh)
                            {
                                case 0:
                                    Down(j, i);
                                    break;
                                case 1:
                                    Right(j, i);
                                    break;
                            }
                            break;
                        }
                        if ((i == 0) && (j == SizeX - 1))
                        {
                            int sh = rand.nextInt(2);
                            switch (sh)
                            {
                                case 0:
                                    Down(j, i);
                                    break;
                                case 1:
                                    Left(j, i);
                                    break;
                            }
                            break;
                        }
                        if ((i == SizeY - 1) && (j == 0))
                        {
                            int sh = rand.nextInt(2);
                            switch (sh)
                            {
                                case 0:
                                    Up(j, i);
                                    break;
                                case 1:
                                    Right(j, i);
                                    break;
                            }
                            break;
                        }
                        else if ((j == SizeX - 1) && (i != SizeY - 1) && (i != 0))
                        {
                            int sh = rand.nextInt(3);
                            switch (sh)
                            {
                                case 0:
                                    Up(j, i);
                                    break;
                                case 1:
                                    Left(j, i);
                                    break;
                                case 2:
                                    Down(j, i);
                                    break;
                            }
                            break;
                        }
                        else if ((i == SizeY - 1) && (j != SizeX - 1) && (j != 0))
                        {
                            int sh = rand.nextInt(3);
                            switch (sh)
                            {
                                case 0:
                                    Up(j, i);
                                    break;
                                case 1:
                                    Left(j, i);
                                    break;
                                case 2:
                                    Right(j, i);
                                    break;
                            }
                            break;
                        }
                        else if ((i == 0) && (j != SizeX - 1))
                        {
                            int sh = rand.nextInt(3);
                            switch (sh)
                            {
                                case 0:
                                    Down(j, i);
                                    break;
                                case 1:
                                    Left(j, i);
                                    break;
                                case 2:
                                    Right(j, i);
                                    break;
                            }
                            break;
                        }
                        else if ((j == 0) && (i != SizeY - 1))
                        {
                            int sh = rand.nextInt(3);
                            switch (sh)
                            {
                                case 0:
                                    Up(j, i);
                                    break;
                                case 1:
                                    Right(j, i);
                                    break;
                                case 2:
                                    Down(j, i);
                                    break;
                            }
                            break;
                        }
                        else
                        {
                            int sh = rand.nextInt(4);
                            switch (sh)
                            {
                                case 0:
                                    Up(j, i);
                                    break;
                                case 1:
                                    Right(j, i);
                                    break;
                                case 2:
                                    Down(j, i);
                                    break;
                                case 3:
                                    Left(j, i);
                                    break;
                            }
                            break;
                        }
                    }
    }
    //Перемещение вверх
    private void Up(int j, int i)
    {
        Drawable dr = buttons[i][j].getBackground();
        buttons[i][j].setBackground(buttons[i - 1][j].getBackground());
        buttons[i][j].setText(buttons[i - 1][j].getText());
        buttons[i][j].setEnabled(true);
        buttons[i - 1][j].setText(null);
        buttons[i - 1][j].setBackground(dr);
        buttons[i - 1][j].setEnabled(false);
    }
    //Перемещение вниз
    private void Down(int j, int i)
    {
        Drawable dr = buttons[i][j].getBackground();
        buttons[i][j].setBackground(buttons[i + 1][j].getBackground());
        buttons[i][j].setText(buttons[i + 1][j].getText());
        buttons[i][j].setEnabled(true);
        buttons[i + 1][j].setText(null);
        buttons[i + 1][j].setBackground(dr);
        buttons[i + 1][j].setEnabled(false);
    }
    //Перемещение влево
    private void Left(int j, int i)
    {
        Drawable dr = buttons[i][j].getBackground();
        buttons[i][j].setBackground(buttons[i][j - 1].getBackground());
        buttons[i][j].setText(buttons[i][j - 1].getText());
        buttons[i][j].setEnabled(true);
        buttons[i][j - 1].setText(null);
        buttons[i][j - 1].setBackground(dr);
        buttons[i][j - 1].setEnabled(false);
    }
    //Перемещение вправо
    private void Right(int j, int i)
    {
        Drawable dr = buttons[i][j].getBackground();
        buttons[i][j].setBackground(buttons[i][j + 1].getBackground());
        buttons[i][j].setText(buttons[i][j + 1].getText());
        buttons[i][j].setEnabled(true);
        buttons[i][j + 1].setText(null);
        buttons[i][j + 1].setBackground(dr);
        buttons[i][j + 1].setEnabled(false);
    }
}
