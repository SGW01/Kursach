package sgw.kursach.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.kursach.Candidate;
import sgw.kursach.R;
import sgw.kursach.adapters.RecyclerAdapter;
import sgw.kursach.database.DataBaseModule;

public class ResultEnd extends Activity {

    DataBaseModule dataBaseModule;
    private double[][] data;
    private int n;
    private Double[] sortedFFinal;
    private Double[] fFinal;
    private double[] fPlus;
    private double[] fMinus;
    private double[][] sumRange, dataExpectation, dataInitiative, dataMotivation, dataFlexibility, dataResponsibility, dataEfficiency, dataFrustration;
    private String[] names;
    private ArrayList<Candidate> list = new ArrayList<>();

    @BindView(R.id.recycler)
    RecyclerView recyclerView;


    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_end);
        ButterKnife.bind(this);

        dataBaseModule = new DataBaseModule();

        AsyncGetAllEnd asyncGetAllEnd = new AsyncGetAllEnd();
        asyncGetAllEnd.execute();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

    }

    private void setValues() {
        n = data.length;
        Log.d("NewInfo", "n = " + n);
        dataExpectation = new double[n][n];
        dataInitiative = new double[n][n];
        dataMotivation = new double[n][n];
        dataFlexibility = new double[n][n];
        dataResponsibility = new double[n][n];
        dataFrustration = new double[n][n];
        dataEfficiency = new double[n][n];
        sumRange = new double[n][n];
        fMinus = new double[n];
        fPlus = new double[n];
        fFinal = new Double[n];
        sortedFFinal = new Double[n];
        rerange();
    }

    private void rerange() {
        //Expectation
        for (int i = 0; i < n; i++) {
            double d = data[i][0];

            for (int k = 0; k < n; k++) {
                dataExpectation[i][k] = d - data[k][0];

            }
        }
        //Initiative
        for (int i = 0; i < n; i++) {
            double d = data[i][1];
            for (int k = 0; k < n; k++) {
                dataInitiative[i][k] = d - data[k][1];

            }
        }
        //Motivation
        for (int i = 0; i < n; i++) {
            double d = data[i][2];
            for (int k = 0; k < n; k++) {
                dataMotivation[i][k] = d - data[k][2];

            }
        }
        //Flexibility
        for (int i = 0; i < n; i++) {
            double d = data[i][3];
            for (int k = 0; k < n; k++) {
                dataFlexibility[i][k] = d - data[k][3];

            }
        }
        //   Responsibility
        for (int i = 0; i < n; i++) {
            double d = data[i][4];
            for (int k = 0; k < n; k++) {
                dataResponsibility[i][k] = d - data[k][4];

            }
        }
        // Frustration
        for (int i = 0; i < n; i++) {
            double d = data[i][5];
            for (int k = 0; k < n; k++) {
                dataFrustration[i][k] = d - data[k][5];

            }
        }
        //Efficiency
        for (int i = 0; i < n; i++) {
            double d = data[i][6];
            for (int k = 0; k < n; k++) {
                dataEfficiency[i][k] = d - data[k][6];
            }
        }
        dataExpectation = rerange1(dataExpectation);
        dataInitiative = rerange1(dataInitiative);
        dataMotivation = rerange1(dataMotivation);
        dataFlexibility = rerange1(dataFlexibility);
        dataResponsibility = rerange1(dataResponsibility);
        dataFrustration = rerange1(dataFrustration);
        dataEfficiency = rerange1(dataEfficiency);
        rerange2();
    }

    private void rerange2() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumRange[i][j] = dataExpectation[i][j] * 0.5 + dataInitiative[i][j] * 0.1 + dataMotivation[i][j] * 0.1 +
                        dataFlexibility[i][j] * 0.1 + dataResponsibility[i][j] * 0.1 + dataFrustration[i][j] * 0.1 +
                        dataEfficiency[i][j] * 0.1;
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

    private double[][] rerange1(double[][] someData) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (someData[i][j] <= 0) {
                    someData[i][j] = 0;
                } else if (someData[i][j] > 0 && someData[i][j] <= 25) {
                    someData[i][j] = someData[i][j] / 25;
                } else if (someData[i][j] > 25) {
                    someData[i][j] = 1;
                }

            }
        }
        return someData;
    }

    private void setInfoIntoRecycler() {
        list = dataBaseModule.readDataForRecycler(ResultEnd.this);
        adapter.setList(list);
    }

    class AsyncGetAllEnd extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            data = dataBaseModule.readAllFromDBEnd(ResultEnd.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setValues();
            cancel(true);

        }
    }

    class AsyncGetSurnames extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            dataBaseModule.updateDBSecondStep(ResultEnd.this, fFinal);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setInfoIntoRecycler();
            cancel(true);
        }
    }


}
