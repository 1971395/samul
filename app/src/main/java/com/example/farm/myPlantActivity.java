package com.example.farm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class myPlantActivity extends AppCompatActivity {
    private myPlantDao mMyPlantDao;
    private plantDao mPlantDao;

    private TextView myPlant_name;
    private VideoView myPlant_image;
//    private WebSettings webSettings;
//    private TextView myPlant_type;
    private TextView myPlant_temperA;
    private TextView myPlant_temperP;
    private TextView myPlant_firstDay;
    private Button myPlant_save;
    private Spinner myPlant_type;
    private TextView myPlant_lastWater;
    private Button myPlant_basicInfo;
    private Button water_button;

    private myPlant myPlant = new myPlant();
    private myPlant dbPlant;
    private plant p = new plant();

    long mNow;
    Date mDate;

    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private String waterDate;
    private String wd;

    private StringBuffer fd = new StringBuffer();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant);

        myPlant_name = (TextView) findViewById(R.id.myPlant_name);
        myPlant_image = (VideoView) findViewById(R.id.myPlant_image);
//        myPlant_type = (TextView) findViewById(R.id.myPlant_type_tv);
        myPlant_temperA = (TextView) findViewById(R.id.myPlant_temperA_tv);
        myPlant_temperP = (TextView) findViewById(R.id.myPlant_temperP_tv);
        myPlant_firstDay = (TextView) findViewById(R.id.myPlant_firstDay_tv);
        myPlant_save = (Button) findViewById(R.id.myPlant_save);
        myPlant_type = (Spinner) findViewById(R.id.myPlant_type_tvs);
        myPlant_lastWater = (TextView) findViewById(R.id.myPlant_lastWater_tv);
        myPlant_basicInfo = (Button) findViewById(R.id.myPlant_basicInfo);
        water_button = (Button) findViewById(R.id.waterButton);


        myPlantDB databaseM = Room.databaseBuilder(getApplicationContext(), myPlantDB.class, "myPlant_db")
                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
                .allowMainThreadQueries()           // Main Thread에서 DB에 IO를 가능하게 함
                .build();

        plantDB databaseP = Room.databaseBuilder(getApplicationContext(), plantDB.class, "plant_db")
                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
                .allowMainThreadQueries()           // Main Thread에서 DB에 IO를 가능하게 함
                .build();

        mMyPlantDao = databaseM.myPlantDao();
        mPlantDao = databaseP.plantDao();

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        waterDate = pref.getString("WaterDate", "yyyy-MM-dd a hh:mm:ss");

        myPlant_lastWater.setText(waterDate);

        List<myPlant> myPlantList = mMyPlantDao.getMyPlantDBAll();
        List<plant> PlantList = mPlantDao.getPlantDBAll();

//        if (myPlantList.size() > 0) {
//            myPlant_name.setText(myPlantList.get(0).getMyPlant_name());
//            StringBuffer fd = new StringBuffer();
//            fd.append(myPlantList.get(0).getMyPlant_firstDay());
//            fd.insert(4, "-");
//            fd.insert(7, "-");
//
//            myPlant_firstDay.setText(fd);
//            myPlant_type.setSelection(myPlantList.get(0).getPlantId()-1);
//
//        }


        myPlant_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                for (int k = 0; k < PlantList.size(); k++) {
                    String getString = adapterView.getItemAtPosition(i).toString();

                    if (PlantList.get(k).getType().equals(getString)) {
                        p = PlantList.get(k);

                        myPlant_temperA.setText(p.getTemper());
//                        myPlant.setType(p.getType());
//                        myPlant.setWaterPeriod(p.getWaterPeriod());
//                        myPlant.setTemper(p.getTemper());
//                        myPlant.setBasicInfo(p.getBasicInfo());
//                        myPlant.setId(p.getId());
//                        mMyPlantDao.setInsertPlant(myPlant);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        myPlant_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fd != null)
                    myPlant_firstDay.setText(fd);
                System.out.println(myPlant_firstDay.getText());
                if(myPlant_firstDay.getText().toString().equals("") && myPlant_name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;

//                    System.out.println(myPlant_name.getText() + " " + myPlant_firstDay.getText());
//                    myPlant = new myPlant(myPlant_name.getText() + "", myPlant_firstDay.getText() + "", p);
//                    mMyPlantDao.setInsertPlant(myPlant);
//                    System.out.println(myPlant.getMyPlant_name());
//                    Toast.makeText(getApplicationContext(),"저장 완료1",Toast.LENGTH_SHORT).show();
//                    return;

                }
                else if(!myPlant_name.getText().toString().equals("") && myPlant_firstDay.getText().toString().equals("")){
                    myPlant = new myPlant(myPlant_name.getText() + "", p);
                    myPlant.setPlantId(p.getId());
                    myPlant.setId(1);
                    mMyPlantDao.setInsertPlant(myPlant);
                    Toast.makeText(getApplicationContext(),"저장 완료1",Toast.LENGTH_SHORT).show();

                }
                else if(!myPlant_name.getText().toString().equals("") && !myPlant_firstDay.getText().toString().equals("")){
                    myPlant = new myPlant(myPlant_name.getText() + "", myPlant_firstDay.getText() + "", p);
                    myPlant.setPlantId(p.getId());
                    myPlant.setId(1);
                    mMyPlantDao.setInsertPlant(myPlant);
                    Toast.makeText(getApplicationContext(),"저장 완료2",Toast.LENGTH_SHORT).show();
                }
//                else if(myPlant_firstDay.getText() != null && myPlant_name != null){
//                    System.out.println(myPlant_name.getText() + " " + myPlant_firstDay.getText());
//                    myPlant.setId(1);
//                    myPlant.setMyPlant_firstDay(myPlant_firstDay.getText() + "");
//                    myPlant.setMyPlant_name(myPlant_name.getText() + "");
//                    myPlant.setType(p.getType());
//                    myPlant.setWaterPeriod(p.getWaterPeriod());
//                    myPlant.setTemper(p.getTemper());
//                    myPlant.setBasicInfo(p.getBasicInfo());
//                    mMyPlantDao.setUpdatePlant(myPlant);
//                    System.out.println(myPlant.getMyPlant_name());
//                    Toast.makeText(getApplicationContext(),"저장 완료2",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }


//                else if(myPlant_name.getText() != null){
//                    System.out.println(myPlant_name.getText());
//                    myPlant.setId(1);
//                    myPlant.setMyPlant_name(myPlant_name.getText() + "");
//                    myPlant.setMyPlant_firstDay("");
//                    myPlant.setType(p.getType());
//                    myPlant.setWaterPeriod(p.getWaterPeriod());
//                    myPlant.setTemper(p.getTemper());
//                    myPlant.setBasicInfo(p.getBasicInfo());
//                    mMyPlantDao.setUpdatePlant(myPlant);
//                    System.out.println(myPlant.getMyPlant_name());
//                    Toast.makeText(getApplicationContext(),"저장 완료2",Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
                else{
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        if (myPlantList.size() > 0) {
            myPlant_name.setText(myPlantList.get(0).getMyPlant_name());

            fd.append(myPlantList.get(0).getMyPlant_firstDay());
            fd.insert(4, "-");
            fd.insert(7, "-");

            myPlant_firstDay.setText(fd);
            myPlant_type.setSelection(myPlantList.get(0).getPlantId()-1);

        }


        myPlant_basicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), plantDG.class);

                intent.putExtra("plant_type", p.getId());

                startActivity(intent);
            }
        });

        water_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wd = getTime();

                editor.putString("WaterDate", wd);
                editor.apply();
                myPlant_lastWater.setText(wd);

                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);

                startActivity(intent);
            }
        });

//        myPlant_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(getApplicationContext(), Stream.class);
////
////                startActivity(intent);
//
//                myPlant_image.setVideoURI(Uri.parse("rtsp://172.20.10.2:8081/"));
//                myPlant_image.setMediaController(new MediaController(this));
//                myPlant_image.requestFocus();
//                myPlant_image.start();
//            }
//        });

        myPlant_image.setVideoPath("rtsp://172.20.10.2:8081/");
        myPlant_image.start();

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);

                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

}

