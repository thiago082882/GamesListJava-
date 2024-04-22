package com.thiago.games_java.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.thiago.games_java.R;
import com.thiago.games_java.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        navController = navHostFragment.getNavController();
        // setupActionBarWithNavController(navController);
    }

    // @Override
    // public boolean onSupportNavigateUp() {
    //     return navController.navigateUp() || super.onSupportNavigateUp();
    // }


    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }
}