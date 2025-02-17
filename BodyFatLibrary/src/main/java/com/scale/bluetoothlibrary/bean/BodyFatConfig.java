package com.scale.bluetoothlibrary.bean;

import java.io.Serializable;

public class BodyFatConfig implements Serializable {
    public int height;
    public int age;
    public int sex;
    public int impedance;
    public double weight;
    public double BMI;
    public double fatRate;
    public double fatKg;
    public double subcutaneousFatRate;
    public double subcutaneousFatKg;
    public double muscleRate;
    public double muscleKg;
    public double waterRate;
    public double waterKg;
    public int visceralFat;
    public double visceralFatKg;
    public double boneRate;
    public double boneKg;
    public double BMR;
    public double proteinPercentageRate;
    public double proteinPercentageKg;
    public int bodyAge;
    public int bodyScore;
    public double standardWeight;
    public double notFatWeight;
    public double controlWeight;
    public double controlFatKg;
    public double controlMuscleKg;
    public int obesityLevel;
    public int healthLevel;
    public int bodyType;
    public int impedanceStatus;
    public String mac;

    public BodyFatConfig(BodyFatBean bodyFatBean) {
        this.height = bodyFatBean.getHeight();
        this.age = bodyFatBean.getAge();
        this.sex = bodyFatBean.getSex();
        this.impedance = bodyFatBean.getImpedance();
        this.weight = bodyFatBean.getWeight();
        this.BMI = bodyFatBean.getBMI();
        this.fatRate = bodyFatBean.getFatRate();
        this.fatKg = bodyFatBean.getFatKg();
        this.subcutaneousFatRate = bodyFatBean.getSubcutaneousFatRate();
        this.subcutaneousFatKg = bodyFatBean.getSubcutaneousFatKg();
        this.muscleRate = bodyFatBean.getMuscleRate();
        this.muscleKg = bodyFatBean.getMuscleKg();
        this.waterRate = bodyFatBean.getWaterRate();
        this.waterKg = bodyFatBean.getWaterKg();
        this.visceralFat = bodyFatBean.getVisceralFat();
        this.visceralFatKg = bodyFatBean.getVisceralFatKg();
        this.boneRate = bodyFatBean.getBoneRate();
        this.boneKg = bodyFatBean.getBoneKg();
        this.BMR = bodyFatBean.getBMR();
        this.proteinPercentageRate = bodyFatBean.getProteinPercentageRate();
        this.proteinPercentageKg = bodyFatBean.getProteinPercentageKg();
        this.bodyAge = bodyFatBean.getBodyAge();
        this.bodyScore = bodyFatBean.getBodyScore();
        this.standardWeight = bodyFatBean.getStandardWeight();
        this.notFatWeight = bodyFatBean.getNotFatWeight();
        this.controlWeight = bodyFatBean.getControlWeight();
        this.controlFatKg = bodyFatBean.getControlFatKg();
        this.controlMuscleKg = bodyFatBean.getControlMuscleKg();
        this.obesityLevel = bodyFatBean.getObesityLevel();
        this.healthLevel = bodyFatBean.getHealthLevel();
        this.bodyType = bodyFatBean.getBodyType();
        this.impedanceStatus = bodyFatBean.getImpedanceStatus();
        this.mac = bodyFatBean.getMac();
    }

    /*
     * 肥胖等级
     * <p>
     * 0.无肥胖
     * 1.肥胖1级
     * 2.肥胖2级
     * 3.肥胖3级
     * 4.肥胖4级
     */

    /*
     * 健康等级
     * <p>
     * 1.偏瘦
     * 2.标准
     * 3.超重
     * 4.肥胖
     */

    /*
     * 身体类型
     *
     * 1.偏瘦型
     * 2.偏瘦肌肉型
     * 3.标准型
     * 4.标准肌肉型
     * 5.缺乏运动型
     * 6.偏胖型
     * 7.偏胖肌肉型
     * 8.浮肿肥胖型
     * 9.肥胖型
     * 10.肥胖肌肉型
     */
}
