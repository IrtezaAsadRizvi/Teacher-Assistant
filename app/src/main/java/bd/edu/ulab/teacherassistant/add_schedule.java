package bd.edu.ulab.teacherassistant;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class add_schedule extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //vars
    public static String title;
    public static String type;
    public static String name;
    public static String s_type;
    public static String important = "0";

    TextView page_title;
    EditText s_name;
    Button btn_date;
    Button btn_time;
    Button btn_importace;
    Button btn_submit;

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();

    int click_count = 0;

    public static int day_in_month;
    public static int day_in_week;
    public static int month;
    public static int year;
    public static int hour;
    public static int min;

    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        Typeface Raleway_SemiBold = Typeface.createFromAsset(getAssets(),"fonts/Raleway_SemiBold.ttf");

        s_name = (EditText)findViewById(R.id.s_name);
        s_name.setText(type);

        s_type = type;


        page_title = (TextView)findViewById(R.id.page_title);
        page_title.setTypeface(Raleway_SemiBold);
        page_title.setText(title);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mydb = new DatabaseHelper(this);

        btn_date = (Button)findViewById(R.id.set_date);
        btn_time = (Button)findViewById(R.id.set_time);
        btn_importace = (Button)findViewById(R.id.importance);
        btn_submit = (Button)findViewById(R.id.submit_btn);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        btn_importace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click_count++;
                if (click_count % 2 == 0){
                    important = "0";
                    btn_importace.setBackgroundResource(R.drawable.importance_btn);
                }else{
                    important = "1";
                    btn_importace.setBackgroundResource(R.drawable.importance_btn_activated);
                }
            }
        });

        updateTextLabel();
        addData();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.close) {
            Intent i = new Intent(add_schedule.this,MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.schedules) {
            Intent i = new Intent(add_schedule.this,MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(add_schedule.this,About.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        } else if (id == R.id.nav_dev) {
            Intent i = new Intent(add_schedule.this,Developers.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateTextLabel();
            btn_date.setText(day_in_month+"|"+month+"|"+year);
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);

            updateTextLabel();
            btn_time.setText(hour+":"+min);
        }
    };

    private void updateTextLabel(){
        day_in_month = dateTime.get(Calendar.DAY_OF_MONTH);
        day_in_week = dateTime.get(Calendar.DAY_OF_WEEK);
        year = dateTime.get(Calendar.YEAR);
        month = dateTime.get(Calendar.MONTH)+1;
        hour = dateTime.get(Calendar.HOUR_OF_DAY);
        min = dateTime.get(Calendar.MINUTE);



    }
    public void addData(){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydb.insertData(s_name.getText().toString(),
                        Integer.toString(day_in_month),
                        Integer.toString(day_in_week),
                        Integer.toString(month),
                        Integer.toString(year),
                        Integer.toString(hour),
                        Integer.toString(min),
                        important
                );
                if (isInserted == true){
                    Toast.makeText(add_schedule.this, "schedule added", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(add_schedule.this,MainActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(add_schedule.this, "error", Toast.LENGTH_SHORT).show();
                }
                setupAlarm(dateTime);
                MainActivity.viewAll();
            }
        });
    }
    public void setupAlarm(java.util.Calendar calendar){
        calendar.add(Calendar.MINUTE,-61);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        Intent intent = new Intent("asad.irteza.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),broadcast);
    }
}
