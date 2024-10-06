package com.allabo.fyl.dto;

public class RecommendationDto {
    private String preferredCategory; // 예: 신용카드, 예적금
    private int age;
    private int incomeLevel;
    // 기타 설문 관련 필드들

    // Getter and Setter for preferredCategory
    public String getPreferredCategory() {
        return preferredCategory;
    }

    public void setPreferredCategory(String preferredCategory) {
        this.preferredCategory = preferredCategory;
    }

    // Getter and Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter and Setter for incomeLevel
    public int getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(int incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    // 기타 설문 관련 필드들에 대한 getter와 setter도 필요에 따라 추가
}
