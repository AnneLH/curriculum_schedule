package cn.vaf714.curriculum_schedule.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.vaf714.curriculum_schedule.R;
import cn.vaf714.curriculum_schedule.bean.ColorManager;
import cn.vaf714.curriculum_schedule.bean.CourseVo;
import cn.vaf714.curriculum_schedule.dao.CourseDao;
import cn.vaf714.curriculum_schedule.dao.StudentDao;
import cn.vaf714.curriculum_schedule.entity.Course;
import cn.vaf714.curriculum_schedule.entity.Schedule;
import cn.vaf714.curriculum_schedule.bean.CourseSchedule;
import cn.vaf714.curriculum_schedule.utils.ConvertUtils;
import cn.vaf714.curriculum_schedule.utils.DateUtils;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private FloatingActionButton fab;
    private NavigationView navigationView;
    private TextView weekTitleText;
    private ImageButton mainMenuBnt;
    private ImageButton lastWeekBtn;
    private ImageButton nextWeekBtn;
    private CircleImageView navHeadPhoto;
    private TextView navHeadUserName;
    private TextView navHeadEmail;
    //<editor-fold desc="课程格子">
    private TextView course1_1;
    private TextView course1_3;
    private TextView course1_5;
    private TextView course1_7;
    private TextView course1_9;
    private TextView course2_1;
    private TextView course2_3;
    private TextView course2_5;
    private TextView course2_7;
    private TextView course2_9;
    private TextView course3_1;
    private TextView course3_3;
    private TextView course3_5;
    private TextView course3_7;
    private TextView course3_9;
    private TextView course4_1;
    private TextView course4_3;
    private TextView course4_5;
    private TextView course4_7;
    private TextView course4_9;
    private TextView course5_1;
    private TextView course5_3;
    private TextView course5_5;
    private TextView course5_7;
    private TextView course5_9;
    private TextView course6_1;
    private TextView course6_3;
    private TextView course6_5;
    private TextView course6_7;
    private TextView course6_9;
    private TextView course7_1;
    private TextView course7_3;
    private TextView course7_5;
    private TextView course7_7;
    private TextView course7_9;
    //</editor-fold>
    private Map<String, TextView> textViewMap = new HashMap<>();
    private CourseSchedule courseSchedule = new CourseSchedule();
    private StudentDao studentDao = new StudentDao();
    private int showWeek;//记录当前展示的周

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            //android5.0 以上执行
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//状态栏透明
        }
        setContentView(R.layout.activity_main);
        initView();

        //跳转到当前周
        showWeek = DateUtils.getCurrWeek();
        skipWeek(showWeek);
    }

    public void startLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        skipWeek(showWeek);
    }

    private void skipWeek(int week) {
        String weekStr = String.valueOf(week);
        if (week < 10) {
            weekStr = " " + weekStr + " ";
        }
        weekTitleText.setText("第 " + weekStr + " 周");
        if (week == DateUtils.getCurrWeek()) {
            weekTitleText.setTextColor(0xFFFFA54F);
            weekTitleText.getPaint().setFakeBoldText(true);
        } else {
            weekTitleText.setTextColor(0xFFFFFFFF);
            weekTitleText.getPaint().setFakeBoldText(false);
        }
        //刷新格子
        refreshCheck(week);
    }

    /**
     * 刷新格子
     */
    private void refreshCheck(int week) {
        //更新课程表
        courseSchedule.updateCourse(week);
        resetTextView();
        //显示到格子
        Iterator<Map.Entry<String, CourseVo>> entryIterator = courseSchedule.getCourseMap().entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, CourseVo> entry = entryIterator.next();
            String time = entry.getKey();//获得时间
            CourseVo courseVo = entry.getValue();
            TextView textView = textViewMap.get(time);//获得相应时间的textview
            if (textView != null) {
                //设置格子的字体和颜色等属性
                textView.setText(courseVo.getCourse().getCourseName() + "@" + courseVo.getSchedule().getClassroom());
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(20);
                gradientDrawable.setColor(courseVo.getCourse().getCourseColor());
                textView.setBackgroundDrawable(gradientDrawable);
            }
        }
    }

    private void resetTextView() {
        Iterator<Map.Entry<String, TextView>> entryIterator = textViewMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, TextView> entry = entryIterator.next();
            TextView textView = entry.getValue();
            textView.setText("");
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(0);
            textView.setBackgroundDrawable(gradientDrawable);
        }
    }

    private void initTextViewMap() {
        textViewMap.put("1_1", course1_1);
        textViewMap.put("1_3", course1_3);
        textViewMap.put("1_5", course1_5);
        textViewMap.put("1_7", course1_7);
        textViewMap.put("1_9", course1_9);
        textViewMap.put("2_1", course2_1);
        textViewMap.put("2_3", course2_3);
        textViewMap.put("2_5", course2_5);
        textViewMap.put("2_7", course2_7);
        textViewMap.put("2_9", course2_9);
        textViewMap.put("3_1", course3_1);
        textViewMap.put("3_3", course3_3);
        textViewMap.put("3_5", course3_5);
        textViewMap.put("3_7", course3_7);
        textViewMap.put("3_9", course3_9);
        textViewMap.put("4_1", course4_1);
        textViewMap.put("4_3", course4_3);
        textViewMap.put("4_5", course4_5);
        textViewMap.put("4_7", course4_7);
        textViewMap.put("4_9", course4_9);
        textViewMap.put("5_1", course5_1);
        textViewMap.put("5_3", course5_3);
        textViewMap.put("5_5", course5_5);
        textViewMap.put("5_7", course5_7);
        textViewMap.put("5_9", course5_9);
        textViewMap.put("6_1", course6_1);
        textViewMap.put("6_3", course6_3);
        textViewMap.put("6_5", course6_5);
        textViewMap.put("6_7", course6_7);
        textViewMap.put("6_9", course6_9);
        textViewMap.put("7_1", course7_1);
        textViewMap.put("7_3", course7_3);
        textViewMap.put("7_5", course7_5);
        textViewMap.put("7_7", course7_7);
        textViewMap.put("7_9", course7_9);
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mainMenuBnt = (ImageButton) findViewById(R.id.main_menu_img_bnt);
        weekTitleText = (TextView) findViewById(R.id.week_title_text);
        course1_1 = (TextView) findViewById(R.id.course_1_1);
        course1_3 = (TextView) findViewById(R.id.course_1_3);
        course1_5 = (TextView) findViewById(R.id.course_1_5);
        course1_7 = (TextView) findViewById(R.id.course_1_7);
        course1_9 = (TextView) findViewById(R.id.course_1_9);
        course2_1 = (TextView) findViewById(R.id.course_2_1);
        course2_3 = (TextView) findViewById(R.id.course_2_3);
        course2_5 = (TextView) findViewById(R.id.course_2_5);
        course2_7 = (TextView) findViewById(R.id.course_2_7);
        course2_9 = (TextView) findViewById(R.id.course_2_9);
        course3_1 = (TextView) findViewById(R.id.course_3_1);
        course3_3 = (TextView) findViewById(R.id.course_3_3);
        course3_5 = (TextView) findViewById(R.id.course_3_5);
        course3_7 = (TextView) findViewById(R.id.course_3_7);
        course3_9 = (TextView) findViewById(R.id.course_3_9);
        course4_1 = (TextView) findViewById(R.id.course_4_1);
        course4_3 = (TextView) findViewById(R.id.course_4_3);
        course4_5 = (TextView) findViewById(R.id.course_4_5);
        course4_7 = (TextView) findViewById(R.id.course_4_7);
        course4_9 = (TextView) findViewById(R.id.course_4_9);
        course5_1 = (TextView) findViewById(R.id.course_5_1);
        course5_3 = (TextView) findViewById(R.id.course_5_3);
        course5_5 = (TextView) findViewById(R.id.course_5_5);
        course5_7 = (TextView) findViewById(R.id.course_5_7);
        course5_9 = (TextView) findViewById(R.id.course_5_9);
        course6_1 = (TextView) findViewById(R.id.course_6_1);
        course6_3 = (TextView) findViewById(R.id.course_6_3);
        course6_5 = (TextView) findViewById(R.id.course_6_5);
        course6_7 = (TextView) findViewById(R.id.course_6_7);
        course6_9 = (TextView) findViewById(R.id.course_6_9);
        course7_1 = (TextView) findViewById(R.id.course_7_1);
        course7_3 = (TextView) findViewById(R.id.course_7_3);
        course7_5 = (TextView) findViewById(R.id.course_7_5);
        course7_7 = (TextView) findViewById(R.id.course_7_7);
        course7_9 = (TextView) findViewById(R.id.course_7_9);
        lastWeekBtn = (ImageButton) findViewById(R.id.last_week_btn);
        nextWeekBtn = (ImageButton) findViewById(R.id.next_week_btn);
        View headVIew = navigationView.getHeaderView(0);
        navHeadPhoto = (CircleImageView) headVIew.findViewById(R.id.nav_head_photo);
        navHeadUserName = (TextView) headVIew.findViewById(R.id.nav_head_username);
        navHeadEmail = (TextView) headVIew.findViewById(R.id.nav_head_email);
        //将格子的textview放入map中维护
        initTextViewMap();
        //事件
        navigationView.setNavigationItemSelectedListener(this);
        mainMenuBnt.setOnClickListener(this);
        fab.setOnClickListener(this);
        lastWeekBtn.setOnClickListener(this);
        nextWeekBtn.setOnClickListener(this);
        weekTitleText.setOnClickListener(this);
        navHeadPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_head_photo:
                drawerLayout.closeDrawers();
                if (studentDao.getStudent() == null){
                    startLoginActivity();
                }else {
                    showLogoutDialogFromNav();
                }
                break;
            case R.id.last_week_btn:
                if (showWeek <= 1) {
                    return;
                }
                showWeek--;
                skipWeek(showWeek);
                break;
            case R.id.next_week_btn:
                if (showWeek >= getSharedPreferences("setting", MODE_PRIVATE).getInt("weeks", 22)) {
                    return;
                }
                showWeek++;
                skipWeek(showWeek);
                break;
            case R.id.week_title_text:
                showWeek = DateUtils.getCurrWeek();
                skipWeek(showWeek);
                break;
            case R.id.fab:
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.rotate);
                fab.startAnimation(animation);
                final View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_dialog, null);
                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("添加课程")
                        .setView(dialogView)
                        .setPositiveButton("确定", null).create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //实例化控件
                        Spinner weekSpinner = (Spinner) dialogView.findViewById(R.id.weeks_spinner);
                        Spinner timesSpinner = (Spinner) dialogView.findViewById(R.id.times_spinner);
                        Spinner whichWeekSpinner = (Spinner) dialogView.findViewById(R.id.which_week_spinner);
                        EditText courseNameEdit = (EditText) dialogView.findViewById(R.id.course_name_text);
                        EditText courseClassroomEdit = (EditText) dialogView.findViewById(R.id.course_classroom_text);
                        EditText courseTeacherEdit = (EditText) dialogView.findViewById(R.id.course_teacher_text);
                        EditText courseStartWeekEdit = (EditText) dialogView.findViewById(R.id.course_start_week_text);
                        EditText courseEndWeekEdit = (EditText) dialogView.findViewById(R.id.course_end_week_text);
                        // 获取空间中的内容
                        String courseName = courseNameEdit.getText().toString();//必填
                        String courseClassroom = courseClassroomEdit.getText().toString();//必填
                        String courseTeacher = courseTeacherEdit.getText().toString();
                        String startWeek = courseStartWeekEdit.getText().toString();//必填
                        String endWeek = courseEndWeekEdit.getText().toString();//必填
                        int week = ConvertUtils.convert(weekSpinner.getSelectedItem().toString());
                        int section = ConvertUtils.convert(timesSpinner.getSelectedItem().toString());
                        int whichWeek = ConvertUtils.convert(whichWeekSpinner.getSelectedItem().toString());
                        //判断必填控件是否为空，若空则提示并中断执行
                        if (courseName.equals("")) {
                            courseNameEdit.setFocusable(true);
                            courseNameEdit.requestFocus();
                            Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
                            courseNameEdit.startAnimation(shake);
                            return;
                        } else if (courseClassroom.equals("")) {
                            courseClassroomEdit.setFocusable(true);
                            courseClassroomEdit.requestFocus();
                            Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
                            courseClassroomEdit.startAnimation(shake);
                            return;
                        } else if (startWeek.equals("")) {
                            courseStartWeekEdit.setFocusable(true);
                            courseStartWeekEdit.requestFocus();
                            Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
                            courseStartWeekEdit.startAnimation(shake);
                            return;
                        } else if (endWeek.equals("")) {
                            courseEndWeekEdit.setFocusable(true);
                            courseEndWeekEdit.requestFocus();
                            Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
                            courseEndWeekEdit.startAnimation(shake);
                            return;
                        }
                        //若不为空
                        Schedule schedule = new Schedule(courseClassroom, whichWeek, week, section);
                        Course course = new Course(courseName, courseTeacher, Integer.parseInt(startWeek), Integer.parseInt(endWeek), ColorManager.getANoUsedColor());
                        schedule.setCourse(course);
                        course.getSchedules().add(schedule);
                        //添加课程到课程表
                        courseSchedule.addCourse(course);
                        //刷新格子
                        showWeek = DateUtils.getCurrWeek();
                        skipWeek(showWeek);
                        dialog.dismiss();
                        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_anti);
                        fab.startAnimation(animation);
                    }
                });
                break;
            case R.id.main_menu_img_bnt:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_term_setting:
                Intent intent = new Intent(MainActivity.this, TermSettingActivity.class);
                startActivityFromNav(intent);
                //动画
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.nav_inf_seeting:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_advise:
                break;
            case R.id.nav_about:
                break;
        }
        return true;
    }

    private void startActivityFromNav(final Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }

    private void showLogoutDialogFromNav() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("警告")
                                .setMessage("你确认要退出当前用户吗？这会清空所有本地数据，退出前建议先同步数据至服务器")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        studentDao.clear();
                                        courseSchedule.clearSchedule();
                                        skipWeek(showWeek);
                                    }
                                }).setNegativeButton("取消", null).show();
                    }
                });
            }
        }).start();
    }
}
