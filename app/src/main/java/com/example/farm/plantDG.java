package com.example.farm;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class plantDG extends AppCompatActivity {
    private plantDao mPlantDao;

    private Spinner spinnerP;
    private TextView plant_temperP_tv;
    private TextView plant_temperA_tv;
    private TextView plant_waterPeriod_tv;
    private TextView plant_basicInfo_tv;
    private Button plant_basicInfo_site;


    private plant p;
    private ImageView plant_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        plantDB database = Room.databaseBuilder(getApplicationContext(), plantDB.class, "plant_db")
                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
                .allowMainThreadQueries()           // Main Thread에서 DB에 IO를 가능하게 함
                .build();

        mPlantDao = database.plantDao();            // 인터페이스 객체 할당

        List<plant> PlantList = mPlantDao.getPlantDBAll();

        plant_image = (ImageView) findViewById(R.id.plant_image);
        spinnerP = (Spinner) findViewById(R.id.plant_type_tvs);
        plant_temperA_tv = (TextView) findViewById(R.id.myPlant_temperA_tv);
        plant_waterPeriod_tv = (TextView) findViewById(R.id.plant_waterPeriod_tv);
        plant_basicInfo_tv = (TextView) findViewById(R.id.plant_baisicInfo_tv);
        plant_basicInfo_site = (Button) findViewById(R.id.plant_basicInfo_site);

        spinnerP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                plant_type_tv.setText(adapterView.getItemAtPosition(i).toString());
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                for (int k = 0; k < PlantList.size(); k++) {
                    String getString = adapterView.getItemAtPosition(i).toString();

                    if (PlantList.get(k).getType().equals(getString)) {
                        Log.d("TEST4", "if문");
                        p = PlantList.get(k);
                        Log.d("TEST", PlantList.get(k).getTemper());

                        plant_temperA_tv.setText(p.getTemper());
                        plant_waterPeriod_tv.setText(p.getWaterPeriod());
                        plant_basicInfo_tv.setText(p.getBasicInfo());
                        plant_image.setImageResource(p.getImageFile());

//                        plant_temperP_tv.setText(p.getTemper());
                    }
                }
//                plant_temperP_tv.setText(p.getTemper());
//                plant_waterPeriod_tv.setText(p.getWaterPeriod());
//                plant_basicInfo_tv.setText(p.getBasicInfo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Intent intent = getIntent();
        Integer plant_type_get = intent.getIntExtra("plant_type", 0);
        System.out.println(plant_type_get);

        if(plant_type_get == 0)
            spinnerP.setSelection(0);
        else
            spinnerP.setSelection(plant_type_get-1);

        plant_basicInfo_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(p.getBasicInfo_site()));
                startActivity(intent);
            }
        });

//        SharedPreferences pref = getSharedPreferences("isFirst", MODE_PRIVATE);
//        boolean first = pref.getBoolean("isFirst", false);
//
//
//        if (first==false) {
//            //데이터 삽입
//            plant plant1 = new plant();
//            plant1.setId(1);
//            plant1.setType("고추");
//            plant1.setTemper("25~30(발아시 30~35)");
//            plant1.setWaterPeriod("모종(2~3일) 4~5일 (2~3차례 나눠 뿌리를 적신다) (물온도도 확인)");
//            plant1.setBasicInfo("가지과의 한해살이풀 길이는 6~9cm");
//            mplantDao.setInsertPlant(plant1);
//
//            plant plant2 = new plant();
//            plant2.setId(2);
//            plant2.setType("무");
//            plant2.setTemper("17~23(발아시 15~35)");
//            plant2.setWaterPeriod("4~5일");
//            plant2.setBasicInfo("쌍떡잎식물 십자화목 배추과의 한해살이풀 또는 두해살이풀");
//            mplantDao.setInsertPlant(plant2);
//
//            plant plant3 = new plant();
//            plant3.setId(3);
//            plant3.setType("배추");
//            plant3.setTemper("18~20(발아시 20~25)");
//            plant3.setWaterPeriod("4~5일");
//            plant3.setBasicInfo("쌍떡잎식물 십자화목 십자화과의 두해살이풀");
//            mplantDao.setInsertPlant(plant3);
//
//            plant plant4 = new plant();
//            plant4.setId(4);
//            plant4.setType("오이");
//            plant4.setTemper("20~22도 내외(발아시 22~25)");
//            plant4.setWaterPeriod("저온기에는 5~7일 고온기에는 2~3일 1회(소량으로 여러 번)");
//            plant4.setBasicInfo("박과의 한해살이 덩굴풀");
//            mplantDao.setInsertPlant(plant4);
//
//            plant plant5 = new plant();
//            plant5.setId(5);
//            plant5.setType("콩");
//            plant5.setTemper("10~25(발아시 20~30)");
//            plant5.setWaterPeriod("꽃 필때까지 건조하게 꼬투리가 생긴 이후에는 10~15일간격으로 충분히");
//            plant5.setBasicInfo("콩과에 속하는 일년생 초본식물");
//            mplantDao.setInsertPlant(plant5);
//
//            plant plant6 = new plant();
//            plant6.setId(6);
//            plant6.setType("토마토");
//            plant6.setTemper("17~27도(발아시 25~30)");
//            plant6.setWaterPeriod("흙표면이 마를때");
//            plant6.setBasicInfo("가지과 덩굴식물로 줄이나 지주대를 세워 재배");
//            mplantDao.setInsertPlant(plant6);
//
//            plant plant7 = new plant();
//            plant7.setId(7);
//            plant7.setType("파");
//            plant7.setTemper("15~25도(발아시 15~30)");
//            plant7.setWaterPeriod("1일");
//            plant7.setBasicInfo("백합과의 여러해살이풀 높이 약 70cm");
//            mplantDao.setInsertPlant(plant7);
//
//            plant plant8 = new plant();
//            plant8.setId(8);
//            plant8.setType("호박");
//            plant8.setTemper("10~35(발아시 25~30)");
//            plant8.setWaterPeriod("흙이 바싹 마를때(습도 측정)");
//            plant8.setBasicInfo("박과의 덩굴성 1년생 초본식물");
//            mplantDao.setInsertPlant(plant8);
//
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putBoolean("isFirst", true);
//            editor.commit();
//
//        }else{
//            Log.d("Is first time?", "not first");
//        }


//        //데이터 삽입
//        plant plant = new plant();
//        plant.setId(2);
//        plant.setType("파");
//        plant.setTemper("20도 내외");
//        plant.setWaterPeriod("3일");
//        plant.setBasicInfo("외떡잎식물 백합목 백합과의 여러해살이풀, 높이 약 70cm");
//        mplantDao.setInsertPlant(plant);
//
//
//        List<plant> plantList = mplantDao.getPlantDBAll();
        //데이터 조회
//        for (int i = 0; i < plantList.size(); i++) {
//            Log.d("TEST", plantList.get(i).getType() + "\n"
//                    + plantList.get(i).getWaterPeriod() + "\n"
//                    + plantList.get(i).getTemper() + "\n"
//                    + plantList.get(i).getBasicInfo() + "\n");
//        }

        //데이터 수정
//        plant plant2 = new plant();
//        plant2.setId(2);
//        plant2.setType("토마토");
//        plant2.setWaterPeriod("");
//        plant2.setTemper("");
//        plant2.setBasicInfo("");
//        mplantDao.setUpdatePlant(plant2);

        //데이터 삭제
//        plant plant3 = new plant();
//        plant3.setId(3);
//        mplantDao.setDeletePlant(plant3);
    }
}