package bd.edu.ulab.teacherassistant;

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
import android.widget.TextView;

public class choose_type extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView page_title;
    Button meeting_btn,class_btn,workshop_btn,conference_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        meeting_btn = (Button)findViewById(R.id.meeting_btn);
        class_btn = (Button)findViewById(R.id.class_btn);
        workshop_btn = (Button)findViewById(R.id.workshop_btn);
        conference_btn = (Button)findViewById(R.id.conference_btn);



        Typeface Raleway_SemiBold = Typeface.createFromAsset(getAssets(),"fonts/Raleway_SemiBold.ttf");
        page_title = (TextView)findViewById(R.id.page_title);
        page_title.setTypeface(Raleway_SemiBold);
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
        go_to_add_schedule();
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
        getMenuInflater().inflate(R.menu.choose_type, menu);
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
            finish();
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
            Intent i = new Intent(choose_type.this,MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(choose_type.this,About.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        } else if (id == R.id.nav_dev) {
            Intent i = new Intent(choose_type.this,Developers.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void go_to_add_schedule(){
        meeting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_schedule.title = "SET MEETING";
                add_schedule.type = "MEETING";
                Intent i = new Intent(choose_type.this,add_schedule.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        class_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_schedule.title = "SET CLASS";
                add_schedule.type = "CLASS";
                Intent i = new Intent(choose_type.this,add_schedule.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        workshop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_schedule.title = "SET WORKSHOP";
                add_schedule.type = "WORKSHOP";
                Intent i = new Intent(choose_type.this,add_schedule.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        conference_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_schedule.title = "SET CONFERENCE";
                add_schedule.type = "CONFERENCE";
                Intent i = new Intent(choose_type.this,add_schedule.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }
}
