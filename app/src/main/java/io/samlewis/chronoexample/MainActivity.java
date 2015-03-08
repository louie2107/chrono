package io.samlewis.chronoexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.getbase.floatingactionbutton.FloatingActionButton;

import io.samlewis.chrono.Chrono;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, Chrono.OnChronoActiveListener {

    private BootstrapButton toggle;
    private BootstrapButton reset;
    private FloatingActionButton direction;
    private Chrono chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (BootstrapButton) findViewById(R.id.btn_start);
        reset = (BootstrapButton) findViewById(R.id.btn_reset);
        direction = (FloatingActionButton) findViewById(R.id.fab_direction);
        chrono = (Chrono) findViewById(R.id.counter);

        setupListeners();
    }

    private void setupListeners() {
        toggle.setOnClickListener(this);
        reset.setOnClickListener(this);
        chrono.setOnChronoActiveListener(this);
        direction.setOnClickListener(this);
    }


    private void changeToggleButton() {
        if (chrono.isCounting()) {
            toggle.setText("Stop");
            toggle.setBootstrapType("warning");
        } else {
            toggle.setText("Start");
            toggle.setBootstrapType("success");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                chrono.toggle();
                break;
            case R.id.fab_direction:
                chrono.toggleDirection();
                break;
            case R.id.btn_reset:
                chrono.reset();
                break;
        }
    }

    @Override
    public void onChronoActiveChange(boolean isCounting, long currentCount) {
        changeToggleButton();
    }
}
