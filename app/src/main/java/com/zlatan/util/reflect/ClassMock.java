package com.zlatan.util.reflect;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class ClassMock implements InvocationHandler {

    Object mBase;

    public ClassMock(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        Log.e("zlatan", method.getName());

        if ("doSomething".equals(method.getName())) {
            print();
        }

        return method.invoke(mBase, objects);
    }

    void print() {
        Log.v("zlatan", "hello");
    }
}
