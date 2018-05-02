package sgw.kursach.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.kursach.R;
import sgw.kursach.database.DataBaseModule;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.cl_info_about_candidate)
    ConstraintLayout constraintLayoutInfoAboutCandidate;

    @BindView(R.id.cl_prof_info)
    ConstraintLayout constraintLayoutProfInfo;

    @BindView(R.id.cl_work_exp)
    ConstraintLayout constraintLayoutWorkExp;

    @BindView(R.id.name)
    EditText editTextName;

    @BindView(R.id.surname)
    EditText editTextSurname;

    @BindView(R.id.age)
    EditText editTextAge;

    @BindView(R.id.email)
    EditText editTextEmail;

    @BindView(R.id.trackRecord)
    EditText editTextTrackRecord;

    @BindView(R.id.editTextCountForeignLanguage)
    EditText editTextCountForeignLanguage;

    @BindView(R.id.highEducation)
    RadioButton radioButtonHighEducation;

    @BindView(R.id.incompleteHigherEducation)
    RadioButton radioButtonIncompleteHigherEducation;

    @BindView(R.id.noneOfEducation)
    RadioButton radioButtonNoneOfEducation;

    @BindView(R.id.rg_command_work)
    RadioGroup radioGroupCommandWork;

    @BindView(R.id.commandWorkYes)
    RadioButton radioButtonCommandWorkYes;

    @BindView(R.id.commandWorkNo)
    RadioButton radioButtonCommandWorkNo;

    @BindView(R.id.rg_leadership)
    RadioGroup radioGroupLeadership;

    @BindView(R.id.leadershipYes)
    RadioButton radioButtonLeadershipYes;

    @BindView(R.id.leadershipNo)
    RadioButton radioButtonLeadershipNo;

    @BindView(R.id.rg_driver)
    RadioGroup radioGroupDriver;

    @BindView(R.id.driverYes)
    RadioButton radioButtonDriverYes;

    @BindView(R.id.driverNo)
    RadioButton radioButtonDriverNo;

    @BindView(R.id.buttonGoToProfInfo)
    Button buttonGoToProfInfo;

    @BindView(R.id.buttonGoToWorkExp)
    Button buttonGoToWorkExp;

    @BindView(R.id.buttonGoToResult)
    Button buttonGoToResult;

    DataBaseModule dataBaseModule = new DataBaseModule();

    int commandWork, driver, leadership;
    int foreignLanguage, age, education, trackRecord;
    private String name, surname, email;

    public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ButterKnife.bind(this);

        buttonGoToProfInfo.setOnClickListener(this);
        buttonGoToResult.setOnClickListener(this);
        buttonGoToWorkExp.setOnClickListener(this);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonGoToProfInfo:
                constraintLayoutInfoAboutCandidate.setVisibility(View.GONE);
                constraintLayoutProfInfo.setVisibility(View.VISIBLE);
                readInfoAboutCandidate();
                break;
            case R.id.buttonGoToWorkExp:
                constraintLayoutProfInfo.setVisibility(View.GONE);
                constraintLayoutWorkExp.setVisibility(View.VISIBLE);
                readProfInfo();
                break;
            case R.id.buttonGoToResult:
                constraintLayoutWorkExp.setVisibility(View.GONE);
                constraintLayoutInfoAboutCandidate.setVisibility(View.VISIBLE);
                readWorkInfo();
                doWriteIntoDB();
                Intent intent = new Intent(this, Results.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void doWriteIntoDB() {
        dataBaseModule.saveToDB(this, name, surname, age, email, trackRecord, foreignLanguage, education,
                commandWork, leadership, driver);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES, surname);
        editor.apply();
    }

    private void readInfoAboutCandidate() {
        name = editTextName.getText().toString();
        surname = editTextSurname.getText().toString();
        age = Integer.parseInt(editTextAge.getText().toString());
        email = editTextEmail.getText().toString();
    }

    private void readProfInfo() {
        trackRecord = Integer.parseInt(editTextTrackRecord.getText().toString());
        foreignLanguage = Integer.parseInt(editTextCountForeignLanguage.getText().toString());
        if (radioButtonHighEducation.isChecked()) {
            education = 1;
        } else if (radioButtonIncompleteHigherEducation.isChecked()) {
            education = 1;
        } else if (radioButtonNoneOfEducation.isChecked()) {
            education = 0;
        } else {
            education = 0;
        }
    }

    private void readWorkInfo() {
        if (radioButtonCommandWorkYes.isChecked()) {
            commandWork = 1;
        } else if (radioButtonCommandWorkNo.isChecked()) {
            commandWork = 0;
        } else {
            commandWork = 0;
        }
        if (radioButtonLeadershipYes.isChecked()) {
            leadership = 1;
        } else if (radioButtonLeadershipNo.isChecked()) {
            leadership = 0;
        } else {
            leadership = 0;
        }
        if (radioButtonDriverYes.isChecked()) {
            driver = 1;
        } else if (radioButtonDriverNo.isChecked()) {
            driver = 0;
        } else {
            driver = 0;
        }
    }
}
