package cn.kai.mianshiTest.ListDome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDome_01 {
    public static void main(String[] args) {
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        MyArrayList arrayList = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i+"");
        }
        System.out.println(arrayList.getSize());
        arrayList.add("11");
        System.out.println(arrayList.getSize());


    }
}
