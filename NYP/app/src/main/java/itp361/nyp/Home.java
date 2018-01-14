package itp361.nyp;
import android.app.Fragment;
import android.app.FragmentManager;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
CircleImageView circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.home, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getFragmentManager();

        if (id == R.id.nav_wd) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new wd())
                    .commit();
        }  else if (id == R.id.nav_lend) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new lend())
                    .commit();
        } else if (id == R.id.nav_manageexpense) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new manage())
                    .commit();
        }
        else if (id == R.id.nav_entrep) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new entrep())
                    .commit();
        }

        else if (id == R.id.nav_mobilepayment) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new mobilepayment())
                    .commit();

        } else if (id == R.id.nav_rewards) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new reward())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
