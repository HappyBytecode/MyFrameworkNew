package com.hxyc.myframework.event;

public class TestEvent extends BaseEvent{
    public TestEvent(int type) {
        super(type);
    }

    public TestEvent(int type, Object obj1) {
        super(type, obj1);
    }

    public TestEvent(int type, Object obj1, Object obj2) {
        super(type, obj1, obj2);
    }

}
