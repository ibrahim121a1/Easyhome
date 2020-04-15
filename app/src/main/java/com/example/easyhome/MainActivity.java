package com.example.easyhome;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.easyhome.Fragments.Home_Fragment;
import com.example.easyhome.Fragments.Login_fragment;
import com.example.easyhome.Fragments.virtual_view;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{
     private Home_Fragment home_fragment;
     private Login_fragment login_fragment;
     private virtual_view   virtual_fragment;

    private BottomNavigationView objectBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeObjects();
        }
    private void changeFragment(Fragment objectFragment)
    {
        try
        {
            FragmentTransaction objectTransaction=getSupportFragmentManager()
                    .beginTransaction();

            objectTransaction.replace(R.id.container,objectFragment).commit();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "changeFragment:" +
                    e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeObjects() {
        try
        {
            home_fragment=new Home_Fragment();
            login_fragment=new Login_fragment();
            virtual_fragment=new virtual_view();

            objectBottomNavigationView=findViewById(R.id.BNV);
            changeFragment(home_fragment);

            objectBottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected( MenuItem item) {

                            if(item.getItemId()==R.id.menu_home)
                            {
                                changeFragment(home_fragment);
                                return true;
                            }
                            else if(item.getItemId()==R.id.menu_view)
                            {
                                changeFragment(virtual_fragment);
                                return true;
                            }
                            else if(item.getItemId()==R.id.menu_login)
                            {
                                changeFragment(login_fragment);
                                return true;
                            }
                            return false;
                        }
                    }
            );


        }
        catch (Exception e)
        {
            Toast.makeText(this, "initializeObjects:" +
                    e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}

