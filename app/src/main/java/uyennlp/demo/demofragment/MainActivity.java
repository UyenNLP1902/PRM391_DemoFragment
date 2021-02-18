package uyennlp.demo.demofragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import uyennlp.demo.demofragment.ui.bikes.BikesFragment;
import uyennlp.demo.demofragment.ui.bikes.BikesFragment2;
import uyennlp.demo.demofragment.ui.dashboard.DashboardFragment;
import uyennlp.demo.demofragment.ui.user.UserFragment;
import uyennlp.demo.demofragment.utils.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navView;
    private ViewPager2 viewPager;

    private BikesFragment bikesFragment;
//    private BikesFragment2 bikesFragment2;
    private DashboardFragment dashboardFragment;
    private UserFragment userFragment;

    private boolean isDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_bikes, R.id.navigation_dashboard, R.id.navigation_users)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isDriver = true;

        viewPager = findViewById(R.id.viewPager);
        navView = findViewById(R.id.nav_view);

        navView.setSelectedItemId(R.id.navigation_dashboard);

        // change fragment
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_bikes:
                        viewPager.setCurrentItem(0, false);
                        return true;
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1, false);
                        return true;
                    case R.id.navigation_users:
                        viewPager.setCurrentItem(2, false);
                        return true;
                }
                return false;
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                switch (position) {
                    case 0:
                        navView.getMenu().findItem(R.id.navigation_bikes).setChecked(true);
                    case 1:
                        navView.getMenu().findItem(R.id.navigation_dashboard).setChecked(true);
                    case 2:
                        navView.getMenu().findItem(R.id.navigation_users).setChecked(true);
                }
            }
        });

        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager2 viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

//        if (isDriver) {
//            bikesFragment = new BikesFragment();
//        } else {
//            bikesFragment2 = new BikesFragment2();
//        }
        bikesFragment = new BikesFragment();
        dashboardFragment = new DashboardFragment();
        userFragment = new UserFragment();

        adapter.addFragment(bikesFragment);
        adapter.addFragment(dashboardFragment);
        adapter.addFragment(userFragment);

        viewPager.setAdapter(adapter);
    }
}