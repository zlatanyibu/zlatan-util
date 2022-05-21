package com.zlatan.util.reflect;

public class AMN {

    private static final Singleton<ClassInterface> gDefault = new Singleton<ClassInterface>() {
        protected ClassInterface create() {
            ClassImpl b2 = new ClassImpl();
            b2.id = 2;
            return b2;
        }
    };

    static public ClassInterface getDefault() {
        return gDefault.get();
    }
}
