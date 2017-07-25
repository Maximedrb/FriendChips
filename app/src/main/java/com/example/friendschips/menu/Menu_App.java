package com.example.friendschips.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.DialogPreference;
import android.sax.StartElementListener;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.fragment.AddFriendFragment;
import com.example.friendschips.fragment.GestionFragment;
import com.example.friendschips.friendschips.MainActivity;
import com.example.friendschips.friendschips.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static android.os.StrictMode.setThreadPolicy;

/**
 * Created by MaximeDrx on 13/06/2016.
 */


public class Menu_App extends AppCompatActivity {

    private ViewPager viewPager;

    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private Button btnSkip, btnNext,btGo;

    private int idLayout;
    private PrefManager prefManager;

    public Utilisateur user = new Utilisateur();

    private Connexion con = new Connexion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_menu);

       Intent intent = getIntent();
       user = intent.getExtras().getParcelable("userConnecter");




        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.app_menu);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);

        btnNext = (Button) findViewById(R.id.btn_next);
        btGo = (Button)findViewById(R.id.btn_go);





        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.food_page,
                R.layout.birthday_page,
                R.layout.drinks_page,
                R.layout.friends_page};

        // adding bottom dots
        //addBottomDots(0);
        idLayout = layouts[0];

        // making notification bar transparent
        changeStatusBarColor();


        myViewPagerAdapter = new MyViewPagerAdapter();


        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(-1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);

                } else {
                    launchHomeScreen();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(idLayout){
                    case R.layout.food_page:
                        Intent i = new Intent(Menu_App.this, FoodActivity.class);
                        i.putExtra("userConnecter",user);
                        startActivity(i);
                        finish();
                        break;
                    case R.layout.birthday_page:
                         i = new Intent(Menu_App.this, BirthdayActivity.class);
                        i.putExtra("userConnecter",user);
                        startActivity(i);
                        finish();
                        break;
                    case R.layout.drinks_page:
                         i = new Intent(Menu_App.this, DrinksActivity.class);
                        i.putExtra("userConnecter",user);
                        startActivity(i);
                        finish();
                        break;
                    case R.layout.friends_page:
                        i = new Intent(Menu_App.this, FriendActivity.class);
                        i.putExtra("userConnecter",user);
                        startActivity(i);
                        finish();
                        break;
                }


            }
        });

    }

    public void onBackPressed()
    {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        if(con.Deconnexion(user.getIdUtilisateur()))
                        {
                            Intent i = new Intent(Menu_App.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            break;
                        }

                    case DialogInterface.BUTTON_NEGATIVE:

                    break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Voulez-vous vous déconnecter?").setPositiveButton("Oui", dialogClickListener)
                .setNegativeButton("Non", dialogClickListener).show();
    }






    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        /*prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Menu_App.this, MainActivity.class));
        finish();*/
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
           //addBottomDots(position);

           idLayout = layouts[position];
        }


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {


        }




    };


    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }
}

