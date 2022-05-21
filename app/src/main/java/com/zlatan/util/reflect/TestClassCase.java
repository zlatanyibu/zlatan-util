package com.zlatan.util.reflect;

import android.util.Log;

public class TestClassCase {
    private String name;


    private static String address = "abc";

    public TestClassCase() {
        name = "zlatan";
    }

    public TestClassCase(int a) {

    }

    public TestClassCase(int a, String b) {
        name = b;
    }

    private TestClassCase(int a, double c) {

    }

    private String doSOmething(String d) {
        Log.v("zlatan", "TestClassCase, doSOmething");

        return "abcd";
    }

    private String doSOmething2() {
        Log.v("zlatan", "TestClassCase, doSOmething2");

        return "abcd2";
    }

    private String doSOmething3(String d, int abc) {
        Log.v("zlatan", "TestClassCase, doSOmething3");

        return "abcd3";
    }

    private static void work() {
        Log.v("zlatan", "TestClassCase, work");
    }

    private static void work2(String d) {
        Log.v("zlatan", "TestClassCase, work2");
    }

    private static void work3(String d, int abc) {
        Log.v("zlatan", "TestClassCase, work3");
    }

    public String getName() {
        return name;
    }

    public static void printAddress() {
        Log.v("zlatan", address);
    }
}
