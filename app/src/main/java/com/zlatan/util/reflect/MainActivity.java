package com.zlatan.util.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zlatan.reflect.R;

import java.lang.reflect.Proxy;

public class MainActivity extends Activity {

    Button btnNormal;
    Button btnHook;
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);

        tvShow = (TextView) findViewById(R.id.txtShow);


        btnNormal = (Button) findViewById(R.id.btnNormal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //测试
//                test2_1();

                //测试method
//                test3();
//                test3_1();

//                //测试field
//                test4();
//                test4_1();

                //测试Singleton
                AMN.getDefault().doSomething();
                test5();
                AMN.getDefault().doSomething();
            }
        });
    }

    final String className = "om.zlatan.util.reflect.TestClassCase";

    public void test2_1() {
        //通过反射，获取一个类，然后调用它
        try {
            Class r = Class.forName(className);

            //无参
            Object obj = RefInvoke.createObject(className);
            Object obj2 = RefInvoke.createObject(r);

            //1个参数
            Class p = int.class;
            Object v = 1;
            Object obj3 = RefInvoke.createObject(className, p, v);
            Object obj4 = RefInvoke.createObject(r, p, v);

            //多个参数
            Class[] p3 = {int.class, String.class};
            Object[] v3 = {1, "zlatan"};
            Object ob5 = RefInvoke.createObject(className, p3, v3);
            Object obj6 = RefInvoke.createObject(r, p3, v3);

            int a = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.1.获取类的private实例方法，调用它
    public void test3() {
        try {
            //创建一个对象
            Class[] p3 = {int.class, String.class};
            Object[] v3 = {1, "zlatan"};
            Object obj = RefInvoke.createObject(className, p3, v3);

            //一个参数
            Object result = RefInvoke.invokeInstanceMethod(obj, "doSOmething", String.class, "zlatan");

            //无参数
            Object result2 = RefInvoke.invokeInstanceMethod(obj, "doSOmething2");

            //多个参数
            Class[] p4 = {String.class, int.class};
            Object[] v4 = {"zlatan", 1};
            Object result3 = RefInvoke.invokeInstanceMethod(obj, "doSOmething3", p4, v4);

            int a = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.2.获取类的private静态方法，调用它
    public void test3_1() {
        try {
            RefInvoke.invokeStaticMethod(className, "work");

            RefInvoke.invokeStaticMethod(className, "work2", String.class, "zlatan");

            Class[] p3 = {String.class, int.class};
            Object[] v3 = {"zlatan", 1};
            RefInvoke.invokeStaticMethod(className, "work3", p3, v3);

            int a = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4.1.获取类的private实例字段，修改它
    public void test4() {
        try {
            //创建一个对象
            Class[] p3 = {int.class, String.class};
            Object[] v3 = {1, "zlatan"};
            Object obj = RefInvoke.createObject(className, p3, v3);

            //获取name字段，private
            Object fieldObject = RefInvoke.getFieldObject(obj, "name");
            Object fieldObject3 = RefInvoke.getFieldObject(className, obj, "name");

            // 设置name字段
            // 只对obj有效
            RefInvoke.setFieldObject(obj, "name", "zlatan");
            RefInvoke.setFieldObject(className, obj, "name", "zlatan");

            int a = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取类的private静态字段，修改它
    public void test4_1() {
        try {
            Object fieldObject = RefInvoke.getFieldObject(className, "address");
            RefInvoke.setStaticFieldObject(className, "address", "ABCD");

            //静态变量，一次修改，终生受用
            TestClassCase.printAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test5() {
        try {
            //获取AMN的gDefault单例gDefault，gDefault是静态的
            Object gDefault = RefInvoke.getStaticFieldObject("om.zlatan.util.reflect.AMN", "gDefault");

            Class clazz = gDefault.getClass();
            Class clazz2 = gDefault.getClass().getSuperclass();

            // gDefault是一个 android.util.Singleton对象; 我们取出这个单例里面的mInstance字段
            Object rawB2Object = RefInvoke.getFieldObject(
                    "om.zlatan.util.reflect.Singleton",
                    gDefault, "mInstance");


            // 创建一个这个对象的代理对象ClassB2Mock, 然后替换这个字段, 让我们的代理对象帮忙干活
            Class<?> ClassInterface = Class.forName("om.zlatan.util.reflect.ClassInterface");
            Object proxy = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class<?>[] { ClassInterface },
                    new ClassMock(rawB2Object));

            //把Singleton的mInstance替换为proxy
            RefInvoke.setFieldObject("om.zlatan.util.reflect.Singleton", gDefault, "mInstance", proxy);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}