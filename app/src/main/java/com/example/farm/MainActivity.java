package com.example.farm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


public class MainActivity extends AppCompatActivity {
    private Button myPlantButton;
    private Button plantButton;
    private Button bugButton;

    private plantDao mplantDao;
    private bugDao mbugDao;
    private myPlantDao mMyPlantDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myPlantButton = findViewById(R.id.activity_main_myPlantButton);
        plantButton = findViewById(R.id.activity_main_plantButton);
        bugButton = findViewById(R.id.activity_main_bugButton);


        myPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myPlantActivity.class);
                startActivity(intent);
            }
        });

        plantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), plantDG.class);
                startActivity(intent);
            }
        });

        bugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), bugDG.class);
                startActivity(intent);
            }
        });

        plantDB databaseP = Room.databaseBuilder(getApplicationContext(), plantDB.class, "plant_db")
                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
                .allowMainThreadQueries()           // Main Thread에서 DB에 IO를 가능하게 함
                .build();

        bugDB databaseB = Room.databaseBuilder(getApplicationContext(), bugDB.class, "bug_db")
                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
                .allowMainThreadQueries()           // Main Thread에서 DB에 IO를 가능하게 함
                .build();

        myPlantDB databaseM = Room.databaseBuilder(getApplicationContext(), myPlantDB.class, "myPlant_db")
                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
                .allowMainThreadQueries()           // Main Thread에서 DB에 IO를 가능하게 함
                .build();


        mplantDao = databaseP.plantDao();
        mbugDao = databaseB.bugDao();
        mMyPlantDao = databaseM.myPlantDao();


        SharedPreferences pref = getSharedPreferences("isFirst", MODE_PRIVATE);
        boolean first = pref.getBoolean("isFirst", false);


        if (!first) {
            //데이터 삽입
            plant plant1 = new plant();
            plant1.setId(1);
            plant1.setType("고추");
            plant1.setTemper("25~30(발아시 30~35)");
            plant1.setWaterPeriod("모종(2~3일) 4~5일 (2~3차례 나눠 뿌리를 적신다) (물온도도 확인)");
            plant1.setBasicInfo("가지과의 한해살이풀 길이는 6~9cm");
            plant1.setImageFile(R.drawable.pepper);
            plant1.setBasicInfo_site("https://www.yongin.go.kr/home/atc/cityConsum/cityConsum05/cityConsum05_02/cityConsum05_02_03.jsp");
            mplantDao.setInsertPlant(plant1);

            plant plant2 = new plant();
            plant2.setId(2);
            plant2.setType("무");
            plant2.setTemper("17~23(발아시 15~35)");
            plant2.setWaterPeriod("4~5일");
            plant2.setBasicInfo("쌍떡잎식물 십자화목 배추과의 한해살이풀 또는 두해살이풀");
            plant2.setImageFile(R.drawable.whiteradish);
            mplantDao.setInsertPlant(plant2);

            plant plant3 = new plant();
            plant3.setId(3);
            plant3.setType("배추");
            plant3.setTemper("18~20(발아시 20~25)");
            plant3.setWaterPeriod("4~5일");
            plant3.setBasicInfo("쌍떡잎식물 십자화목 십자화과의 두해살이풀");
            mplantDao.setInsertPlant(plant3);

            plant plant4 = new plant();
            plant4.setId(4);
            plant4.setType("오이");
            plant4.setTemper("20~22도 내외(발아시 22~25)");
            plant4.setWaterPeriod("저온기에는 5~7일 고온기에는 2~3일 1회(소량으로 여러 번)");
            plant4.setBasicInfo("박과의 한해살이 덩굴풀");
            mplantDao.setInsertPlant(plant4);

            plant plant5 = new plant();
            plant5.setId(5);
            plant5.setType("콩");
            plant5.setTemper("10~25(발아시 20~30)");
            plant5.setWaterPeriod("꽃 필때까지 건조하게 꼬투리가 생긴 이후에는 10~15일간격으로 충분히");
            plant5.setBasicInfo("콩과에 속하는 일년생 초본식물");
            mplantDao.setInsertPlant(plant5);

            plant plant6 = new plant();
            plant6.setId(6);
            plant6.setType("토마토");
            plant6.setTemper("17~27도(발아시 25~30)");
            plant6.setWaterPeriod("흙표면이 마를때");
            plant6.setBasicInfo("가지과 덩굴식물로 줄이나 지주대를 세워 재배");
            mplantDao.setInsertPlant(plant6);

            plant plant7 = new plant();
            plant7.setId(7);
            plant7.setType("파");
            plant7.setTemper("15~25도(발아시 15~30)");
            plant7.setWaterPeriod("1일");
            plant7.setBasicInfo("백합과의 여러해살이풀 높이 약 70cm");
            mplantDao.setInsertPlant(plant7);

            plant plant8 = new plant();
            plant8.setId(8);
            plant8.setType("호박");
            plant8.setTemper("10~35(발아시 25~30)");
            plant8.setWaterPeriod("흙이 바싹 마를때(습도 측정)");
            plant8.setBasicInfo("박과의 덩굴성 1년생 초본식물");
            mplantDao.setInsertPlant(plant8);

            bug bug1 = new bug();
            bug1.setId(1);
            bug1.setType("검거세미밤나방");
            bug1.setBasicInfo("나비목 밤나방과의 곤충");
            bug1.setSolution("다이아지논, 에토펜프록스, 델타메트린, 비펜트린");
            bug1.setImageFile(R.drawable.dark_sword_grass);
            bug1.setBasicInfo_site("https://ncpms.rda.go.kr/mobile/UntySrchR.ms?mainSrhTxt=%EA%B2%80%EA%B1%B0%EC%84%B8%EB%AF%B8%EB%B0%A4%EB%82%98%EB%B0%A9&srchSelect=0&srchWord=%EA%B2%80%EA%B1%B0%EC%84%B8%EB%AF%B8%EB%B0%A4%EB%82%98%EB%B0%A9");
            mbugDao.setInsertBug(bug1);

            bug bug2 = new bug();
            bug2.setId(2);
            bug2.setType("꽃노랑총채벌레");
            bug2.setBasicInfo("총채벌레목 총채벌레과의 곤충");
            bug2.setSolution("칼탑·부프로페진(다갈, 멸스타), 스피노사드(부메랑,\n 올가미), 이미다클로프리드(코니도), 피프로닐(리전트)");
            bug2.setImageFile(R.drawable.occidentalis);
            bug2.setBasicInfo_site("https://ncpms.rda.go.kr/npms/UntySrchListR.np?srchWord=%EA%BD%83%EB%85%B8%EB%9E%91%EC%B4%9D%EC%B1%84%EB%B2%8C%EB%A0%88");
            mbugDao.setInsertBug(bug2);

            bug bug3 = new bug();
            bug3.setId(3);
            bug3.setType("담배가루이");
            bug3.setBasicInfo("매미목 가루이과의 곤충");
            bug3.setSolution("레몬밤(식물)로 유인");
            mbugDao.setInsertBug(bug3);

            bug bug4 = new bug();
            bug4.setId(4);
            bug4.setType("담배거세미나방");
            bug4.setBasicInfo("나비목 밤나방과의 곤충");
            bug4.setSolution("조사중");
            mbugDao.setInsertBug(bug4);

            bug bug5 = new bug();
            bug5.setId(5);
            bug5.setType("담배나방");
            bug5.setBasicInfo("나비목 밤나방과의 곤충");
            bug5.setSolution("조사중");
            mbugDao.setInsertBug(bug5);

            bug bug6 = new bug();
            bug6.setId(6);
            bug6.setType("도둑나방");
            bug6.setBasicInfo("나비목 밤나방과의 곤충");
            bug6.setSolution("조사중");
            mbugDao.setInsertBug(bug6);

            bug bug7 = new bug();
            bug7.setId(7);
            bug7.setType("먹노린재");
            bug7.setBasicInfo("노린재목 노린재과의 곤충");
            bug7.setSolution("조사중");
            mbugDao.setInsertBug(bug7);

            bug bug8 = new bug();
            bug8.setId(8);
            bug8.setType("목화바둑명나방");
            bug8.setBasicInfo("나비목 명나방과의 곤충");
            bug8.setSolution("조사중");
            mbugDao.setInsertBug(bug8);

            bug bug9 = new bug();
            bug9.setId(9);
            bug9.setType("무잎벌");
            bug9.setBasicInfo("벌목 잎벌과의 곤충");
            bug9.setSolution("조사중");
            mbugDao.setInsertBug(bug9);

            bug bug10 = new bug();
            bug10.setId(10);
            bug10.setType("배추좀나방");
            bug10.setBasicInfo("나비목 집나방과의 곤충");
            bug10.setSolution("조사중");
            mbugDao.setInsertBug(bug10);

            bug bug11 = new bug();
            bug11.setId(11);
            bug11.setType("배추흰나비");
            bug11.setSolution("살충제");
            bug11.setBasicInfo("나비목 흰나비과의 곤충");
            mbugDao.setInsertBug(bug11);

            bug bug12 = new bug();
            bug12.setId(12);
            bug12.setType("벼룩잎벌레");
            bug12.setBasicInfo("딱정벌레목 잎벌레과의 곤충");
            bug12.setSolution("조사중");
            mbugDao.setInsertBug(bug12);

            bug bug13 = new bug();
            bug13.setId(13);
            bug13.setType("복숭아혹진딧물");
            bug13.setBasicInfo("매미목 진딧물과의 곤충");
            bug13.setSolution("조사중");
            mbugDao.setInsertBug(bug13);

            bug bug14 = new bug();
            bug14.setId(14);
            bug14.setType("비단노린재");
            bug14.setBasicInfo("노린재목 노린재과의 곤충");
            bug14.setSolution("조사중");
            mbugDao.setInsertBug(bug14);

            bug bug15 = new bug();
            bug15.setId(15);
            bug15.setType("썩덩나무노린재");
            bug15.setBasicInfo("노린재목 노린재과의 곤충");
            bug15.setSolution("조사중");
            mbugDao.setInsertBug(bug15);

            bug bug16 = new bug();
            bug16.setId(16);
            bug16.setType("알락수염노린재");
            bug16.setBasicInfo("노린재목 노린재과의 곤충");
            bug16.setSolution("조사중");
            mbugDao.setInsertBug(bug16);

            bug bug17 = new bug();
            bug17.setId(17);
            bug17.setType("열대거세미나방");
            bug17.setBasicInfo("나비목 밤나방과의 곤충");
            bug17.setSolution("조사중");
            mbugDao.setInsertBug(bug17);

            bug bug18 = new bug();
            bug18.setId(18);
            bug18.setType("큰28점박이무당벌레");
            bug18.setBasicInfo("딱정벌레목 무당벌레과의 곤충");
            bug18.setSolution("조사중");
            mbugDao.setInsertBug(bug18);

            bug bug19 = new bug();
            bug19.setId(19);
            bug19.setType("톱다리개미허리노린재");
            bug19.setBasicInfo("노린재목 호리허리노린재과의 곤충");
            bug19.setSolution("조사중");
            mbugDao.setInsertBug(bug19);

            bug bug20 = new bug();
            bug20.setId(20);
            bug20.setType("파밤나방");
            bug20.setBasicInfo("나비목 밤나방과의 곤충");
            bug20.setSolution("에마멕틴벤조에이트 유제");
            mbugDao.setInsertBug(bug20);

            myPlant myPlant = new myPlant();
            mMyPlantDao.setInsertPlant(myPlant);




            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();

        }else{
            Log.d("Is first time?", "not first");
        }



    }
}