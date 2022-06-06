package com.example.farm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Data Access Object
 */
@Dao
public interface myPlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //삽입
    void setInsertPlant(com.example.farm.myPlant myPlant);

    @Update //수정
    void setUpdatePlant(com.example.farm.myPlant myPlant);

    @Delete //삭제
    void setDeletePlant(com.example.farm.myPlant myPlant);



    //조회 쿼리
    @Query("SELECT * FROM myPlant") //쿼리 : 데이터베이스에 요청하는 명령문
    List<com.example.farm.myPlant> getMyPlantDBAll();

    @Query("SELECT * FROM myPlant WHERE id= :id")
    List<com.example.farm.myPlant> getPlantById(int id);

}
