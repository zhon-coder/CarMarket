package com.example.carmarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.GravityCompat;

import com.example.carmarket.databinding.ActivityMainBinding;
import com.example.carmarket.repository.CarRepository;
import com.example.carmarket.util.UserPreferenceUtil;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ActivityMainBinding homeBinding;
    private List<CarItem> allItems = CarRepository.getAllCars();
    private List<CarItem> displayItems = new ArrayList<>();
    private GridAdapter adapter;

    private String selCategory = "All";
    private String selfuel = "All";
    private String selBrand = "All";
    private String queryText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        setUpHome();
        initGridAdapter();
        setUpFilterListeners();
        // 发送一个简单的测试通知
        NotificationTest();
        getDeviceToken();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void initGridAdapter() {
        GridView grid = homeBinding.grid;
        displayItems.addAll(allItems);
        adapter = new GridAdapter(displayItems);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(gridClickListener);
    }

    private void setUpHome() {
        homeBinding.ibProfile.setOnClickListener(v -> {
            // 如果为右侧
            if(!homeBinding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                homeBinding.drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        homeBinding.navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.nav_logout) {
                UserPreferenceUtil.logout(this);
                Intent intent = new Intent(this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }else if(id == R.id.nav_settings) {

            } else if(id == R.id.nav_profile) {

            }else {

            }
            homeBinding.drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });
    }

    private void getDeviceToken() {
        FirebaseMessaging.getInstance()
        .getToken()
        .addOnSuccessListener(token -> {
            android.util.Log.d("FCM", token);
        });
    }

    private void NotificationTest() {
        NotificationHelper.getInstance().sendNotification(this, "Local Notification",
                "This is local notification.");
    }

    private void setUpFilterListeners() {
        homeBinding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                queryText = s;
                applyFilters();
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String s) { return false; }
        });

        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String val = adapterView.getItemAtPosition(i).toString();
                int id = adapterView.getId();
                if(id == R.id.sp_category) {
                    selCategory = val;
                }else if(id == R.id.sp_fuel_type) {
                    selfuel = val;
                }else if(id == R.id.sp_brand) {
                    selBrand = val;
                }
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        homeBinding.spCategory.setOnItemSelectedListener(spinnerListener);
        homeBinding.spFuelType.setOnItemSelectedListener(spinnerListener);
        homeBinding.spBrand.setOnItemSelectedListener(spinnerListener);
    }

    private void applyFilters() {
        displayItems.clear();
        for(CarItem item : allItems) {
            boolean matchesSearch = queryText.isEmpty() ||
                    item.getBrand().toLowerCase().contains(queryText.toLowerCase()) ||
                    item.getModel().toLowerCase().contains(queryText.toLowerCase());

            boolean matchesCategory = selCategory.equals("All") ||
                    item.getCategory().equals(selCategory);

            boolean matchesFuel = selfuel.equals("All") ||
                    item.getFuelType().equals(selfuel);

            boolean matchesBrand = selBrand.equals("All") ||
                    item.getBrand().equals(selBrand);

            if(matchesSearch && matchesCategory && matchesFuel && matchesBrand) {
                displayItems.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private final AdapterView.OnItemClickListener gridClickListener
            = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CarItem item = (CarItem) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_PARAM_ID, item.getId());

            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    HomeActivity.this,
                    new Pair<>(view.findViewById(R.id.iv_item),
                            DetailsActivity.VIEW_NAME_HEADER_IMAGE),
                    new Pair<>(view.findViewById(R.id.tv_tittle_text),
                            DetailsActivity.VIEW_NAME_HEADER_TITLE));

            ActivityCompat.startActivity(HomeActivity.this, intent, activityOptions.toBundle());
        }
    };
}