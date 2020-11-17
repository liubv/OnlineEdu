package com.lbvguli.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.lbvguli.demo.excel.DemoData;
import com.lbvguli.demo.excel.ExcelListener;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {

    public static void main(String[] args) {
        //写操作
//        String fileName = "/Users/liubiwei/Desktop/test.xlsx";
//        EasyExcel.write(fileName, DemoData.class).sheet("test1").doWrite(data());

        //读操作
        String fileName = "/Users/liubiwei/Desktop/test.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lq"+i);
            list.add(data);
        }
        return list;
    }
}
