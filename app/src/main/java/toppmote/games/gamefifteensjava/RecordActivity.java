package toppmote.games.gamefifteensjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<?> adapter;
    String[] list = {"2x3", "2x4", "2x5", "2x6", "2x7", "2x8", "3x3", "3x4", "3x5", "4x4"};
    String[] records;
    final String FILENAME = "Records.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            List<String> lines = new ArrayList<String>();
            String line = "";
            while ((line = br.readLine()) != null)
                lines.add(line);
            records = lines.toArray(new String[lines.size()]);
        }catch (IOException e) {
            e.printStackTrace();
        }
        final TextView TVmagic = (TextView) findViewById(R.id.textViewMagSq);
        final TextView Line = (TextView) findViewById(R.id.MagSqLine);
        final LinearLayout LLmagic = (LinearLayout) findViewById(R.id.magicrec);
        final TextView ClassicEz = (TextView) findViewById(R.id.ClassicEz);
        final TextView ClassicNormal = (TextView) findViewById(R.id.ClassicNormal);
        final TextView ClassicHard = (TextView) findViewById(R.id.ClassicHard);
        final TextView MagicEz = (TextView) findViewById(R.id.MagicEz);
        final TextView MagicNormal = (TextView) findViewById(R.id.MagicNormal);
        final TextView MagicHard = (TextView) findViewById(R.id.MagicHard);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, R.layout.item_layout, R.id.sizes, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 6 || position == 9) {
                    String[] writeClassic = records[position].split(" ");
                    for(int i = 0; i < 3; i++)
                    {
                        if(!writeClassic[i].equals("-")) {
                            String WriteRec;
                            int RecTime = Integer.valueOf(writeClassic[i]);
                            int RecMin = RecTime / 60;
                            int RecSec = RecTime % 60;
                            if (RecMin != 0)
                                if(RecSec != 0)
                                WriteRec = String.valueOf(RecMin) + "м " + String.valueOf(RecSec) + "c";
                                else   WriteRec = String.valueOf(RecMin) + "м";
                            else WriteRec = writeClassic[i] + "c";
                            writeClassic[i] = WriteRec;
                        }
                    }
                    String[] writeMagic;
                    if(position == 6)
                        writeMagic = records[10].split(" ");
                    else writeMagic = records[11].split(" ");
                    for(int i = 0; i < 3; i++)
                    {
                        if(!writeMagic[i].equals("-")) {
                            String WriteRec;
                            int RecTime = Integer.valueOf(writeMagic[i]);
                            int RecMin = RecTime / 60;
                            int RecSec = RecTime % 60;
                            if (RecMin != 0)
                                if(RecSec != 0)
                                    WriteRec = String.valueOf(RecMin) + "м " + String.valueOf(RecSec) + "c";
                                else   WriteRec = String.valueOf(RecMin) + "м";
                            else WriteRec = writeMagic[i] + "c";
                            writeMagic[i] = WriteRec;
                        }
                    }
                    ClassicEz.setText("Легко: " + writeClassic[0]);
                    ClassicNormal.setText("Нормально: " + writeClassic[1]);
                    ClassicHard.setText("Сложно: " + writeClassic[2]);
                    MagicEz.setText("Легко: " + writeMagic[0]);
                    MagicNormal.setText("Нормально: " + writeMagic[1]);
                    MagicHard.setText("Сложно: " + writeMagic[2]);
                    TVmagic.setVisibility(View.VISIBLE);
                    Line.setVisibility(View.VISIBLE);
                    LLmagic.setVisibility(View.VISIBLE);
                } else {
                    TVmagic.setVisibility(View.INVISIBLE);
                    Line.setVisibility(View.INVISIBLE);
                    LLmagic.setVisibility(View.INVISIBLE);
                    String[] write = records[position].split(" ");
                    for(int i = 0; i < 3; i++)
                    {
                        if(!write[i].equals("-")) {
                            String WriteRec;
                            int RecTime = Integer.valueOf(write[i]);
                            int RecMin = RecTime / 60;
                            int RecSec = RecTime % 60;
                            if (RecMin != 0)
                                if(RecSec != 0)
                                    WriteRec = String.valueOf(RecMin) + "м " + String.valueOf(RecSec) + "c";
                                else   WriteRec = String.valueOf(RecMin) + "м";
                            else WriteRec = write[i] + "c";
                            write[i] = WriteRec;
                        }
                    }
                    ClassicEz.setText("Легко: " + write[0]);
                    ClassicNormal.setText("Нормально: " + write[1]);
                    ClassicHard.setText("Сложно: " + write[2]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
//кнопка назад
    public void ButtonBackClick(View view) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }
}
