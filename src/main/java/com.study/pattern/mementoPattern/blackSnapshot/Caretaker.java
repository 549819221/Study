package com.study.pattern.mementoPattern.blackSnapshot;

public class Caretaker {

    private MementoIF memento;
    /**
     * 备忘录取值方法
     */
    public MementoIF getMemento(){
        return memento;
    }
    /**
     * 备忘录赋值方法
     */
    public void setMemento(MementoIF memento){
        this.memento = memento;
    }
}
