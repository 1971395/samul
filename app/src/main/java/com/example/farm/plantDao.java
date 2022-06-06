package com.example.farm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Data Access Object
 */
@Dao
public interface plantDao {

    @Insert //삽입
    void setInsertPlant(plant plant);

    @Update //수정
    void setUpdatePlant(plant plant);

    @Delete //삭제
    void setDeletePlant(plant plant);

    //조회 쿼리
    @Query("SELECT * FROM plant") //쿼리 : 데이터베이스에 요청하는 명령문
    List<plant> getPlantDBAll();

}
