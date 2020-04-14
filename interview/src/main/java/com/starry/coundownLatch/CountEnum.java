package com.starry.coundownLatch;

import lombok.Getter;

public enum  CountEnum {
    ONE(1, "齐"), TWO(2, "楚"),THREE(3, "燕"),FOUR(4, "赵"),FIVE(5, "魏"),SIX(6, "韩");

    @Getter private Integer id;
    @Getter private String message;

    CountEnum(int id, String message) {
        this.id = id;
        this.message =message;
    }

    public static CountEnum getMessage(Integer id) {
        CountEnum[] elements =  CountEnum.values();
        for (CountEnum element : elements) {
            if(id == element.getId()) {
                return element;
            }
        }
        return null;
    }
}
