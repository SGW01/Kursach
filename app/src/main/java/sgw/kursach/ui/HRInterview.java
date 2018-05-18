package sgw.kursach.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.kursach.R;

public class HRInterview extends Activity implements View.OnClickListener {

    @BindView(R.id.seekBarExpectation)
    SeekBar seekBarExpectation;

    @BindView(R.id.seekBarLoyalty)
    SeekBar seekBarLoyalty;

    @BindView(R.id.seekBarMotivation)
    SeekBar seekBarMotivation;

    @BindView(R.id.seekBarFlexibility)
    SeekBar seekBarFlexibility;

    @BindView(R.id.enterInfo)
    Button enterInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hr_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {

    }
}
