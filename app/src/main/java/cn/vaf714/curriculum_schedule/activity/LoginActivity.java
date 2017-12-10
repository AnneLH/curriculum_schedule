package cn.vaf714.curriculum_schedule.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.vaf714.curriculum_schedule.R;
import cn.vaf714.curriculum_schedule.entity.Student;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    private AutoCompleteTextView username_login;
    private EditText password_login;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        username_login = (AutoCompleteTextView) findViewById(R.id.username_login);
        password_login = (EditText) findViewById(R.id.password_login);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                Student student = new Student();
                student.setNo("1508100218");
                student.setAtClass("152");
                student.setMajor("软件工程");
                student.setDept("信息学院");
                student.setSchool("QUST");
                student.setStudentName("周生昌");
                student.save();
                finish();
                break;
        }
    }
}

