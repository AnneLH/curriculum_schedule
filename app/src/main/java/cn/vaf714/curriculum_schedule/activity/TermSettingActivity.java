package cn.vaf714.curriculum_schedule.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

import cn.vaf714.curriculum_schedule.R;
import cn.vaf714.curriculum_schedule.bean.Setting;

public class TermSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button startDate_btn_term;
    private TextView startDate_text_term;
    private Button weeks_btn_term;
    private TextView weeks_text_term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_setting);
        initView();

        updateStartDateView();
        updateWeeksView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_term);
        startDate_btn_term = (Button) findViewById(R.id.startdate_btn_term);
        startDate_text_term = (TextView) findViewById(R.id.startdate_text_term);
        weeks_btn_term = (Button) findViewById(R.id.weeks_btn_term);
        weeks_text_term = (TextView) findViewById(R.id.weeks_text_term);
        startDate_btn_term.setOnClickListener(this);
        weeks_btn_term.setOnClickListener(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startdate_btn_term:
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String date = i + "-" + (i1 + 1) + "-" + i2;
                        Setting.setStartDate(date);
                        updateStartDateView();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.weeks_btn_term:
                final View dialogView = LayoutInflater.from(TermSettingActivity.this).inflate(R.layout.number_picker_layout, null);
                final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.number_picker);
                numberPicker.setMinValue(1);
                numberPicker.setMaxValue(30);
                numberPicker.setValue(Setting.getWeeks());
                new AlertDialog.Builder(TermSettingActivity.this)
                        .setTitle("选择周数")
                        .setView(dialogView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Setting.setWeeks(numberPicker.getValue());
                                updateWeeksView();
                            }
                        }).show();
                break;
        }
    }

    private void updateStartDateView() {
        String date = Setting.getStartDate();
        startDate_text_term.setText(date);
    }

    private void updateWeeksView() {
        weeks_text_term.setText(String.valueOf(Setting.getWeeks()));
    }
}
