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
import android.widget.TextView;

public class About extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView about_title,about_text,instructions,instructions_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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

        about_title = (TextView)findViewById(R.id.about_title);
        about_text = (TextView)findViewById(R.id.about_text);
        instructions = (TextView)findViewById(R.id.instructions);
        instructions_title = (TextView)findViewById(R.id.instructions_title);

        Typeface Raleway_Regular = Typeface.createFromAsset(getAssets(),"fonts/Raleway_Regular.ttf");
        Typeface Raleway_SemiBold = Typeface.createFromAsset(getAssets(),"fonts/Raleway_SemiBold.ttf");

        about_title.setTypeface(Raleway_SemiBold);
        about_text.setTypeface(Raleway_Regular);
        instructions.setTypeface(Raleway_Regular);
        instructions_title.setTypeface(Raleway_SemiBold);

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
        getMenuInflater().inflate(R.menu.about, menu);
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
            Intent i = new Intent(About.this,MainActivity.class);
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
            Intent i = new Intent(About.this,MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }  else if (id == R.id.nav_dev) {
            Intent i = new Intent(About.this,Developers.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
