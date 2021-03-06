package sgw.kursach.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.kursach.R;
import sgw.kursach.database.DataBaseModule;

public class NewInfo extends AppCompatActivity {

    @BindView(R.id.textViewYourRangeNow)
    TextView textViewYourRangeNow;
    @BindView(R.id.hrInterview)
    Button buttonHrInterview;

    public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mSettings;
    private String name;
    int n;
    double[][] data, dataAge, dataTrack, dataLanguage, dataCommand, dataEducation, dataDriver, dataLeader;

    double[][] sumRange;
    double[] fMinus, fPlus;

    String names[];

    Double fFinal[], sortedFFinal[];
    DataBaseModule dataBaseModule = new DataBaseModule();
    private int index;
    private int rang;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.range_layout);
        ButterKnife.bind(this);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        name = mSettings.getString(APP_PREFERENCES, "no name");

        textViewYourRangeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rerange();
            }
        });

        buttonHrInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHrInterview();
            }
        });

        AsyncGetAll asyncGetAll = new AsyncGetAll();
        asyncGetAll.execute();


    }

    private void setValues() {
        n = data.length;
        Log.d("NewInfo", "n = " + n);
        dataAge = new double[n][n];
        dataTrack = new double[n][n];
        dataLeader = new double[n][n];
        dataLanguage = new double[n][n];
        dataEducation = new double[n][n];
        dataDriver = new double[n][n];
        dataCommand = new double[n][n];
        sumRange = new double[n][n];
        fMinus = new double[n];
        fPlus = new double[n];
        fFinal = new Double[n];
        sortedFFinal = new Double[n];
        rerange();
    }

    private void goToHrInterview() {
        Intent intent = new Intent(this, HRInterview.class);
        startActivity(intent);
    }

    private void rerange() {
        //AGE
        for (int i = 0; i < n; i++) {
            double d = data[i][0];

            for (int k = 0; k < n; k++) {
                dataAge[i][k] = -(d - data[k][0]);

            }
        }
        //TRACK
        for (int i = 0; i < n; i++) {
            double d = data[i][1];
            for (int k = 0; k < n; k++) {
                dataTrack[i][k] = d - data[k][1];

            }
        }
        //LANGUAGE
        for (int i = 0; i < n; i++) {
            double d = data[i][2];
            for (int k = 0; k < n; k++) {
                dataLanguage[i][k] = d - data[k][2];

            }
        }
        //COLUMN_EDUCATION
        for (int i = 0; i < n; i++) {
            double d = data[i][3];
            for (int k = 0; k < n; k++) {
                dataEducation[i][k] = d - data[k][3];

            }
        }
        //   COLUMN_COMMAND
        for (int i = 0; i < n; i++) {
            double d = data[i][4];
            for (int k = 0; k < n; k++) {
                dataCommand[i][k] = d - data[k][4];

            }
        }
        // COLUMN_LEADERSHIP
        for (int i = 0; i < n; i++) {
            double d = data[i][5];
            for (int k = 0; k < n; k++) {
                dataLeader[i][k] = d - data[k][5];

            }
        }
        //COLUMN_DRIVER
        for (int i = 0; i < n; i++) {
            double d = data[i][6];
            for (int k = 0; k < n; k++) {
                dataDriver[i][k] = d - data[k][6];
            }
        }
        rerange1();
    }

    private void rerange1() {
        //AGE
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dataAge[i][j] <= 0) {
                    dataAge[i][j] = 0;
                } else if (dataAge[i][j] > 0 && dataAge[i][j] <= 10) {
                    dataAge[i][j] = dataAge[i][j] / 10;
                } else if (dataAge[i][j] > 10) {
                    dataAge[i][j] = 1;
                }


            }
        }


        //TRACK
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dataTrack[i][j] <= 0) {
                    dataTrack[i][j] = 0;
                } else if (dataTrack[i][j] > 0 && dataTrack[i][j] <= 3) {
                    dataTrack[i][j] = dataTrack[i][j] / 3;
                } else if (dataTrack[i][j] > 3) {
                    dataTrack[i][j] = 1;
                }

            }
        }

        //LANGUAGE
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dataLanguage[i][j] <= 0) {
                    dataLanguage[i][j] = 0;
                } else if (dataLanguage[i][j] > 0 && dataLanguage[i][j] <= 1) {
                    dataLanguage[i][j] = dataLanguage[i][j] / 1;
                } else if (dataLanguage[i][j] > 1) {
                    dataLanguage[i][j] = 1;
                }

            }
        }
        //COLUMN_EDUCATION
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dataEducation[i][j] <= 0) {
                    dataEducation[i][j] = 0;
                } else if (dataEducation[i][j] > 0) {
                    dataEducation[i][j] = 1;
                }


            }
        }
        //   COLUMN_COMMAND
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dataCommand[i][j] <= 0) {
                    dataCommand[i][j] = 0;
                } else if (dataCommand[i][j] > 0) {
                    dataCommand[i][j] = 1;
                }

            }
        }
        // COLUMN_LEADERSHIP
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dataLeader[i][j] <= 0) {
                    dataLeader[i][j] = 0;
                } else if (dataLeader[i][j] > 0) {
                    dataLeader[i][j] = 1;
                }

            }
        }
        //COLUMN_DRIVER
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dataDriver[i][j] <= 0) {
                    dataDriver[i][j] = 0;
                } else if (dataDriver[i][j] > 0) {
                    dataDriver[i][j] = 1;
                }
            }
        }
        rerange2();
    }

    private void rerange2() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumRange[i][j] = dataAge[i][j] * 0.07 + dataTrack[i][j] * 0.35 + dataLanguage[i][j] * 0.21 +
                        dataEducation[i][j] * 0.07 + dataCommand[i][j] * 0.17 + dataDriver[i][j] * 0.03 +
                        dataLeader[i][j] * 0.1;
            }
        }
        rerange3();
    }

    private void rerange3() {
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                fPlus[i] = fPlus[i] + sumRange[i][j];
            }
            double k = 1.0 / (n - 1);
            fPlus[i] = k * fPlus[i];
            //  Log.d("NewInfo", "fPlus after  [" + i + "] = " + fPlus[i]);
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                fMinus[j] = fMinus[j] + sumRange[i][j];
            }

            double k = 1.0 / (n - 1);

            fMinus[j] = k * fMinus[j];

        }
        rerange4();
    }

    private void rerange4() {
        for (int i = 0; i < n; i++) {
            fFinal[i] = fPlus[i] - fMinus[i];
            sortedFFinal[i] = fFinal[i];
            Log.d("NewInfo", "fFinal   [" + i + "] = " + fFinal[i]);
        }

        Arrays.sort(sortedFFinal, Collections.reverseOrder());

        AsyncGetSurnames asyncGetSurnames = new AsyncGetSurnames();
        asyncGetSurnames.execute();


    }

    private void onScreen() {
        textViewYourRangeNow.setText("Ваш оновлений рейтинг: " + (rang + 1));
        if (fFinal.length > 0) {
            dataBaseModule.updateDBFirstStep(this, fFinal);
        }
    }

    private void sortANdAdd() {
        for (int i = 0; i < n; i++) {
            if (name.equals(names[i])) {
                index = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (fFinal[index].equals(sortedFFinal[i])) {
                rang = i;
            }
        }

        onScreen();
    }

    class AsyncGetSurnames extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            names = dataBaseModule.getSurnames(NewInfo.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            sortANdAdd();

        }
    }

    class AsyncGetAll extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            data = dataBaseModule.readAllFromDB(NewInfo.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setValues();
        }
    }


}
