package com.example.farm;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class plant {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    private String type;

    private String waterPeriod;

    private String temper;

    private String basicInfo;

    private int imageFile = R.drawable.no_image;

    private String basicInfo_site = "https://ncpms.rda.go.kr/mobile/MobileMain.ms";

    @Ignore
    plant(){

    }

    @Ignore
    plant(int id, String type, String waterPeriod, String temper, String basicInfo, int imageFile){
      this.id = id;
      this.type = type;
      this.waterPeriod = waterPeriod;
      this.temper = temper;
      this.basicInfo = basicInfo;
      this.imageFile = imageFile;
    }

    plant(int id, String type, String waterPeriod, String temper, String basicInfo){
        this.id = id;
        this.type = type;
        this.waterPeriod = waterPeriod;
        this.temper = temper;
        this.basicInfo = basicInfo;
    }
    @Ignore
    plant(String type, String waterPeriod, String temper, String basicInfo){
        this.type = type;
        this.waterPeriod = waterPeriod;
        this.temper = temper;
        this.basicInfo = basicInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWaterPeriod() {
        return waterPeriod;
    }

    public void setWaterPeriod(String waterPeriod) {
        this.waterPeriod = waterPeriod;
    }

    public String getTemper() {
        return temper;
    }

    public void setTemper(String temper) {
        this.temper = temper;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public int getImageFile() { return imageFile; }

    public void setImageFile(int imageFile) { this.imageFile = imageFile; }

    public String getBasicInfo_site() { return basicInfo_site; }

    public void setBasicInfo_site(String basicInfo_site) { this.basicInfo_site = basicInfo_site; }
}
