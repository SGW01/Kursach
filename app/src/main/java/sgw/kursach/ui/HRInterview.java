package sgw.kursach.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.kursach.R;
import sgw.kursach.database.DataBaseModule;

public class HRInterview extends Activity implements View.OnClickListener {

    @BindView(R.id.seekBarExpectation)
    SeekBar seekBarExpectation;

    @BindView(R.id.seekBarInitiative)
    SeekBar seekBarInitiative;

    @BindView(R.id.seekBarMotivation)
    SeekBar seekBarMotivation;

    @BindView(R.id.seekBarFlexibility)
    SeekBar seekBarFlexibility;

    @BindView(R.id.enterInfo)
    Button enterInfo;

    @BindView(R.id.seekResultExpectation)
    TextView seekResultExpectation;

    @BindView(R.id.seekResultInitiative)
    TextView seekResultInitiative;

    @BindView(R.id.seekResultMotivation)
    TextView seekResultMotivation;

    @BindView(R.id.seekResultFlexibility)
    TextView seekResultFlexibility;

    @BindView(R.id.seekResultResponsibility)
    TextView seekResultResponsibility;

    @BindView(R.id.seekBarResponsibility)
    SeekBar seekBarResponsibility;

    @BindView(R.id.seekResultFrustration)
    TextView seekResultFrustration;

    @BindView(R.id.seekBarFrustration)
    SeekBar seekBarFrustration;

    @BindView(R.id.seekBarEfficiency)
    SeekBar seekBarEfficiency;

    @BindView(R.id.seekResultEfficiency)
    TextView seekResultEfficiency;

    @BindView(R.id.textViewAssurance)
    TextView textViewAssurance;

    @BindView(R.id.candidateSurname)
    EditText candidateSurname;

    int expectation, initiative, motivation, flexibility, responsibility, frustration, efficiency;

    public static final int ASSURANCE = 8;
    public static final int MAX_ASSURANCE = 10;

    DataBaseModule dataBaseModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hr_layout);
        ButterKnife.bind(this);

        enterInfo.setOnClickListener(this);

        dataBaseModule = new DataBaseModule();

        textViewAssurance.setText(ASSURANCE + "/" + MAX_ASSURANCE);

        initializeSeekBars();

    }

    private void initializeSeekBars() {

        seekBarExpectation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekResultExpectation.setText(String.valueOf(progress));
                expectation = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarInitiative.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekResultInitiative.setText(String.valueOf(progress));
                initiative = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarMotivation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekResultMotivation.setText(String.valueOf(progress));
                motivation = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarFlexibility.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekResultFlexibility.setText(String.valueOf(progress));
                flexibility = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarResponsibility.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekResultResponsibility.setText(String.valueOf(progress));
                responsibility = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarFrustration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekResultFrustration.setText(String.valueOf(progress));
                frustration = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarEfficiency.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekResultEfficiency.setText(String.valueOf(progress));
                efficiency = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        String surname = candidateSurname.getText().toString();
        dataBaseModule.updateDB(this, surname,  expectation, initiative, motivation, flexibility, responsibility, frustration, efficiency);
    }
}
