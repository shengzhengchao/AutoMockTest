package com.scc.server;

import io.swagger.models.auth.In;
import org.junit.Test;

import java.util.*;

public class CollectionStudy {
    @Test
    public void arrayList(){
        ArrayList<String> strings = new ArrayList<>(10);
        strings.add("集合学习");

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("贾宝玉");
        linkedList.add("林黛玉");
        linkedList.add("薛宝钗");
        String s = linkedList.remove();
        System.out.println(s);
        System.out.println(linkedList.toString());
    }


    @Test
    public void set(){
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(17);
        hashSet.add(33);
    }

    @Test
    public void map(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("王宝强", "马云");
        hashMap.put("邓超", "孙俪");
        hashMap.put("邓超", "娘娘");
        hashMap.put(null, "胡歌");

        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entries){
            System.out.println(entry.getKey() + "=" + entry.getValue() + "=======" + entry.getClass());
        }

        int i = 0;
        int j = (int) (Math.random()*10);
        if ((i = j) > 5) {
            System.out.println("i大于5" );
        }else {
            System.out.println(i);
        }
    }
}
