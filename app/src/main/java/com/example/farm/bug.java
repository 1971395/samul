package com.example.farm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class bug {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    private String type;

    private String basicInfo;

    private String solution;

    private int imageFile = R.drawable.no_image;

    private String basicInfo_site = "https://ncpms.rda.go.kr/mobile/MobileMain.ms";

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

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getImageFile() { return imageFile; }

    public void setImageFile(int imageFile) { this.imageFile = imageFile; }

    public String getBasicInfo_site() { return basicInfo_site; }

    public void setBasicInfo_site(String basicInfo_site) { this.basicInfo_site = basicInfo_site; }
}
