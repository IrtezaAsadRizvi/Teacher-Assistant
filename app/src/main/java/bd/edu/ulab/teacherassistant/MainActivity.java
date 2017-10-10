package bd.edu.ulab.teacherassistant;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //vars
    static DatabaseHelper db;
    static StringBuffer id_buffer;
    static StringBuffer name_buffer;
    static StringBuffer day_in_month_buffer;
    static StringBuffer month_buffer;
    static StringBuffer year_buffer;
    static StringBuffer day_buffer;
    static StringBuffer hour_buffer;
    static StringBuffer min_buffer;
    static StringBuffer importance_buffer;

    static LinearLayout main_layout;

    static int schedule_num = 0;
    java.util.Calendar[] dateTime = new java.util.Calendar[100];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





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
        Typeface Raleway_SemiBold = Typeface.createFromAsset(getAssets(),"fonts/Raleway_SemiBold.ttf");

        main_layout = (LinearLayout)findViewById(R.id.main_layout);
        db = new DatabaseHelper(this);

        id_buffer = new StringBuffer();
        name_buffer = new StringBuffer();

        day_in_month_buffer = new StringBuffer();
        month_buffer = new StringBuffer();
        year_buffer = new StringBuffer();

        day_buffer = new StringBuffer();

        hour_buffer = new StringBuffer();
        min_buffer = new StringBuffer();

        importance_buffer = new StringBuffer();

        viewAll();
        showAll();

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.plus) {
            Intent i = new Intent(MainActivity.this,choose_type.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_set_schedule) {
            Intent i = new Intent(MainActivity.this,choose_type.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }  else if (id == R.id.thismonth) {
            Intent i = new Intent(MainActivity.this,this_month.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(MainActivity.this,About.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        } else if (id == R.id.nav_dev) {
            Intent i = new Intent(MainActivity.this,Developers.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void viewAll(){
        Cursor res = db.getAllData();
        if (res.getCount() == 0){
            //show no result msg
            return;
        }else{
            while(res.moveToNext()){
                id_buffer.append(res.getString(0)+ "\n");
                name_buffer.append(res.getString(1)+ "\n");

                day_in_month_buffer.append(res.getString(2)+ "\n");
                month_buffer.append(res.getString(4)+ "\n");
                year_buffer.append(res.getString(5)+ "\n");

                day_buffer.append(res.getString(3)+ "\n");

                hour_buffer.append(res.getString(6)+ "\n");
                min_buffer.append(res.getString(7)+ "\n");

                importance_buffer.append(res.getString(8)+ "\n");
            }
            schedule_num = res.getCount();
        }
    }
    public void showAll(){
        //GETTING THE INFO
        final String ids[] = new String(id_buffer).split("\n");
        String names[] = new String(name_buffer).split("\n");

        String day_in_months[] = new String(day_in_month_buffer).split("\n");
        String months[] = new String(month_buffer).split("\n");
        String years[] = new String(year_buffer).split("\n");


        String days[] = new String(day_buffer).split("\n");

        String importance[] = new String(importance_buffer).split("\n");
        String hours[] = new String(hour_buffer).split("\n");
        String mins[] = new String(min_buffer).split("\n");

        //DYNAMICALLY CREATING EACH SCHEDULE(notification bajbe)
        for (int i = 0;i<schedule_num;i++){
            final TextView schedule = new TextView(this);
            schedule.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Raleway_SemiBold.ttf"));
            //SETTING UP CALENDER
            dateTime[i] = java.util.Calendar.getInstance();
            dateTime[i].set(java.util.Calendar.DAY_OF_MONTH,Integer.parseInt(day_in_months[i]));
            dateTime[i].set(java.util.Calendar.MONTH,(Integer.parseInt(months[i])-1));
            dateTime[i].set(java.util.Calendar.YEAR,Integer.parseInt(years[i]));
            dateTime[i].set(java.util.Calendar.HOUR_OF_DAY,Integer.parseInt(hours[i]));
            dateTime[i].set(java.util.Calendar.MINUTE,(Integer.parseInt(mins[i])-60));

            //SETTING UP THE TEXTBOX
            schedule.setText(Html.fromHtml("<b>"+names[i]+"</b><br><br><small>"+day_in_months[i]+"/"+months[i]+"/"+years[i]+"</small><br>"+hours[i]+":"+mins[i]));
            schedule.setId(Integer.parseInt(Integer.toString(i)));
            main_layout.addView(schedule);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.height = 200;
            params.setMargins(40, 10, 40, 10);
            schedule.setLayoutParams(params);
            schedule.setPadding(30,20,30,30);
            if (importance[i] == "1"){
                schedule.setBackgroundResource(R.drawable.tile_star);
            }else{
                schedule.setBackgroundResource(R.drawable.tile);
            }

            //SETTING UP A DELETE METHOD
            final int id = i;
            schedule.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    db.deleteData(ids[id]);
                        if (view.getId() == id) {
                            view.setVisibility(View.GONE);
                        }
                    return false;
                }
            });
        }
    }


}
