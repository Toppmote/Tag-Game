package toppmote.games.gamefifteensjava;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Normal extends GameMech {
    private final Dialog dialog;//Диалог выигрыша
    private final TextView textTime;
    private final Dialog dialog_lose;//Диалог проигрыша
    private final TextView textTime_lose;
    Normal(int x, int y, int min, int sec, int stp, Context context, Chronometer tm, boolean mod, int df, TextView sl, Button[][] btns, int rowP) {
        super(x, y, min, sec, stp, mod, df, tm, sl, btns, rowP);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout);
        textTime = (TextView) dialog.findViewById(R.id.text);
        dialog_lose = new Dialog(context);
        dialog_lose.setContentView(R.layout.dialog_lose);
        textTime_lose = (TextView) dialog_lose.findViewById(R.id.text);
        buttons = btns;
        time = tm;
        Mod = mod;
        Button dialogYes = (Button) dialog_lose.findViewById(R.id.buttonYes);
        dialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restart();
                dialog_lose.dismiss();
            }
        });
        Button dialogNo = (Button) dialog_lose.findViewById(R.id.buttonNo);
        dialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_lose.dismiss();
            }
        });
    }
    //Функция победы
    public String Win() {
        String OLDFILENAME = "/data/data/toppmote.games.gamefifteensjava/files/Records.txt";
        String[] records;
        File oldFile = new File(OLDFILENAME);
        List<String> lines = new ArrayList<String>();
        try {
            FileInputStream read = new FileInputStream(oldFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(read));
            int WinTime = timeMin * 60 + timeSec;
            String line = "";
            try {
                for(int i = 0; i < row; i++) {
                    line = br.readLine();
                    lines.add(line);
                }
                line = br.readLine();
                String[] recstr = line.split(" ");
                if(!recstr[1].equals("-"))
                {
                    int oldrec = Integer.valueOf(recstr[0]);
                    if(oldrec > WinTime)
                        recstr[1] = String.valueOf(WinTime);
                }
                else recstr[1] = String.valueOf(WinTime);
                String toFile = recstr[0] + " " + recstr[1] + " " + recstr[2];
                lines.add(toFile);
                for(int i = row + 1; i < 12; i++) {
                    line = br.readLine();
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        records = lines.toArray(new String[lines.size()]);
        try {
            FileOutputStream write = new FileOutputStream(oldFile);
            for(int i = 0; i < 12; i++) {
                records[i] += "\n";
                byte[] bytes = records[i].getBytes();
                write.write(bytes);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        String time;
        if (timeMin > 0)
            if (timeSec == 0)
                time = String.valueOf(timeMin) + "м";
            else time = String.valueOf(timeMin) + "м " + String.valueOf(timeSec) +"с";
        else time = String.valueOf(timeSec) +"с";
        return time;
    }
    //Нажатие
    public void Click(Button btn) {
        boolean f = false;
        for (int i = 0; i < SizeY; i++)
            for (int j = 0; j < SizeX; j++)
                if (buttons[i][j] == btn)
                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++)
                            if (x >= 0 && x < SizeY && y >= 0 && y < SizeX && (i == x || j == y))
                                if (buttons[x][y].getText().equals("")) {
                                    time.start();
                                    f = true;
                                    steps--;
                                    step.setText(String.format(Locale.US, "%d", steps));
                                    Drawable dr = buttons[x][y].getBackground();
                                    buttons[x][y].setBackground(buttons[i][j].getBackground());
                                    buttons[x][y].setText(buttons[i][j].getText());
                                    buttons[x][y].setEnabled(true);
                                    buttons[i][j].setText(null);
                                    buttons[i][j].setBackground(dr);
                                    buttons[i][j].setEnabled(false);
                                    break;
                                }
                        if (f)
                            break;
                    }
        boolean end;
        if (Mod)
            end = CheckMagic();
        else
            end = CheckClassic();
        if (end) {
            time.stop();
            String time = Win();
            textTime.setText("Вы собрали пятнашки.\nВремя: " + time + ".");
            dialog.show();
        } else if (steps == 0) {
            time.stop();
            for (Button[] butn : buttons
            ) {
                for (Button bn : butn
                ) {
                    bn.setEnabled(false);
                }
            }
            textTime_lose.setText("Ваши шаги закончились.\nНачать заново?");
            dialog_lose.show();
        }
    }
    //Тик
    public void Tick(){
        timeSec++;
        if (timeSec == 60)
        {
            timeMin++;
            timeSec = 0;
        }
        if (timeMin == 0)
            time.setText(String.format(Locale.US, "%dс", timeSec));
        else if (timeSec == 0)
            time.setText(String.format(Locale.US, "%dм", timeMin));
        else time.setText(String.format(Locale.US, "%dм %dс", timeMin, timeSec));
    }
}
