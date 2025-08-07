package com.scc.server;

import com.scc.bean.User;
import org.junit.Test;

import java.io.*;

public class IOStudy {
    //FileInputStream文件字节输入流
    @Test
    public void readFile1(){
        String filePath = "src/main/resources/mocked_response.txt";
        //创建字节输入流, 用于读取文件
        byte[] bytes = new byte[8];
        int readLen = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            //如果返回-1, 表示读取完毕
            try {
                //使用read(byte[] b)读取
                while ((readLen = fileInputStream.read(bytes)) != -1) {
                    System.out.print(new String(bytes, 0, readLen));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //BufferInputStream缓冲字节输入流
    //ObjectInputStream文件字节输入流
    @Test
    public void creatDir(){
        File pathFile = new File("/dir1");
        boolean mkdir = pathFile.mkdir();
        System.out.println(pathFile.getAbsoluteFile());

        System.out.println("创建文件" + (mkdir?"成功":"失败" + "!"));
    }

    @Test
    public void createFile(){
        try {
            // 创建File对象指向当前目录下的test.txt
            File file = new File("test.txt");

            // 检查文件是否已存在
            if (file.exists())
                file.delete();
            if (file.createNewFile()) {
                System.out.println("文件创建成功: " + file.getAbsolutePath());
            } else {
                System.out.println("文件已存在: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("创建文件时出错: " + e.getMessage());
        }
    }

    //使用FileInputStream和FileOutStream实现复制文件
    @Test
    public void copyFile(){
        String srcFilePath = "src/main/resources/source_photo.jpg";
        String dstFilePath = "src/main/resources/destination_photo.jpg";
        File file = new File(dstFilePath);
        byte[] buf = new byte[1024];
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
//            file.delete();
//        }

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        long startTime = System.currentTimeMillis();

        try {
            fileInputStream = new FileInputStream(srcFilePath);
            fileOutputStream = new FileOutputStream(dstFilePath);
            int readLen = 0;
            int i = 0;
            while ((readLen = fileInputStream.read(buf)) != -1) {
//                fileOutputStream.write(buf, 0, readLen);
                fileOutputStream.write(buf);
//                System.out.println("第" + ++i + "次读取!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                assert fileInputStream != null;
                fileInputStream.close();
                assert fileOutputStream != null;
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时: " + (endTime - startTime) + "ms");
        System.out.println("复制成功!");
    }
}
