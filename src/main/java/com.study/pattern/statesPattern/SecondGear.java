package com.study.pattern.statesPattern;

public class SecondGear implements LuFeiState{

    @Override
    public void change() {
        System.out.println("路飞开启二挡战斗");
    }

}
