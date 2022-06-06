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
public interface bugDao {

    @Insert //삽입
    void setInsertBug(bug bug);

    @Update //수정
    void setUpdateBug(bug bug);

    @Delete //삭제
    void setDeleteBug(bug bug);

    //조회 쿼리
    @Query("SELECT * FROM bug") //쿼리 : 데이터베이스에 요청하는 명령문
    List<bug> getBugDBAll();

}
