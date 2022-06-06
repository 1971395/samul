package com.example.farm;

import androidx.room.Entity;

@Entity
public class myPlant extends plant {
    private int plantId = 1;
    private String myPlant_name;
    private String myPlant_humidity;
    private String myPlant_firstDay="yyyymmdd";

    myPlant(){

    }

    myPlant(String myPlant_name, String myPlant_humidity, String myPlant_firstDay,  plant plant){
        super(plant.getId(), plant.getType(), plant.getWaterPeriod(), plant.getTemper(), plant.getBasicInfo());
        this.myPlant_name = myPlant_name;
        this.myPlant_humidity = myPlant_humidity;
        this.myPlant_firstDay = myPlant_firstDay;
        this.setImageFile(R.drawable.no_image);
    }

    myPlant(String myPlant_name, String myPlant_firstDay, plant plant){
        super(plant.getId(), plant.getType(), plant.getWaterPeriod(), plant.getTemper(), plant.getBasicInfo());
        this.myPlant_name = myPlant_name;
        this.myPlant_firstDay = myPlant_firstDay;
        this.setImageFile(R.drawable.no_image);
    }
    myPlant(String myPlant_name, plant plant){
        super(plant.getId(), plant.getType(), plant.getWaterPeriod(), plant.getTemper(), plant.getBasicInfo());
        this.myPlant_name = myPlant_name;
    }
    myPlant(plant plant){
        super(plant.getId(), plant.getType(), plant.getWaterPeriod(), plant.getTemper(), plant.getBasicInfo());
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int myId) {
        this.plantId = myId;
    }

    public String getMyPlant_name() {
        return myPlant_name;
    }

    public void setMyPlant_name(String myPlant_name) {
        this.myPlant_name = myPlant_name;
    }

    public String getMyPlant_humidity() {
        return myPlant_humidity;
    }

    public void setMyPlant_humidity(String myPlant_humidity) {
        this.myPlant_humidity = myPlant_humidity;
    }

    public String getMyPlant_firstDay() {
        return myPlant_firstDay;
    }

    public void setMyPlant_firstDay(String myPlant_firstDay) {
        this.myPlant_firstDay = myPlant_firstDay;
    }
}
