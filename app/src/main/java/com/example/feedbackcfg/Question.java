package com.example.feedbackcfg;

public class Question {
    private int id;
    private int center_id;
    private int level1;
    private int level2;
    private int level3;
    private int level4;
    private int level5;
    private int level6;
    private int level7;
    private int level8;

    public Question(int id,int center_id,int level1,int level2,int level3,int level4,int level5,int level6,int level7,int level8){
        this.id=id;
        this.center_id=center_id;
        this.level1=level1;
        this.level2=level2;
        this.level3=level3;
        this.level4=level4;
        this.level5=level5;
        this.level6=level6;
        this.level7=level7;
        this.level8=level8;
    }
    Question(){}
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getCenter_id(){return center_id;}
    public void setCenter_id(int center_id){this.center_id=center_id;}
    public int getLevel1(){return level1;}
    public void setLevel1(int level1){this.level1=level1;}
    public int getLevel2(){return level2;}
    public void setLevel2(int level2){this.level2=level2;}
    public int getLevel3(){return level3;}
    public void setLevel3(int level3){this.level3=level3;}
    public int getLevel4(){return level4;}
    public void setLevel4(int level4){this.level4=level4;}
    public int getLevel5(){return level5;}
    public void setLevel5(int level5){this.level5=level5;}
    public int getLevel6(){return level6;}
    public void setLevel6(int level6){this.level6=level6;}
    public int getLevel7(){return level7;}
    public void setLevel7(int level7){this.level7=level7;}
    public int getLevel8(){return level8;}
    public void setLevel8(int level8){this.level8=level8;}

}
