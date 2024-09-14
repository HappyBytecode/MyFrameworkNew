package com.hxyc.myframework.event;

public abstract class BaseEvent {

    public final int type;
    public final Object obj1;
    public final Object obj2;
    public final Object obj3;

    public BaseEvent(int type) {
        this.type = type;
        this.obj1 = null;
        this.obj2 = null;
        this.obj3 = null;
    }

    public BaseEvent(int type, Object obj1) {
        this.type = type;
        this.obj1 = obj1;
        this.obj2 = null;
        this.obj3 = null;
    }

    public BaseEvent(int type, Object obj1, Object obj2) {
        this.type = type;
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.obj3 = null;
    }

    public BaseEvent(int type, Object obj1, Object obj2, Object obj3) {
        this.type = type;
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.obj3 = obj3;
    }
}
