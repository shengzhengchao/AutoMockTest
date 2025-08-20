package com.scc.server;

import com.scc.bean.User;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

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

    @Test
    public void copyTxt() {
        String srcFilePath = "src/main/resources/ppt正文.txt";
        String dstFilePath = "src/main/resources/ppt正文2.txt";
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        char[] chars = new char[8];
        int readLen = 0;
        //创建fileReader对象
        try {
            fileReader = new FileReader(srcFilePath);
            fileWriter = new FileWriter(dstFilePath, true);
            //循环读取文件中字符
            while ((readLen = fileReader.read(chars)) != -1) {
                System.out.print(new String(chars, 0, readLen));
                fileWriter.write(new String(chars, 0, readLen));
//                fileWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n数据写入完成");
    }

    @Test
    public void copyByBufStream() {
        String srcFilePath = "src/main/resources/ppt正文.txt";
        String dstFilePath = "src/main/resources/ppt正文3.txt";
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        String line = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(srcFilePath));
            bufferedWriter = new BufferedWriter(new FileWriter(dstFilePath, true));
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    @Test
    public void readProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/mysql.properties"));
//            System.setOut(new PrintStream("src/main/resources/mysql2.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.list(System.out);
        System.out.println(properties.getProperty("ip"));
        properties.setProperty("port", "10086");
        try {
            properties.store(new FileWriter("src/main/resources/mysql2.properties", true),
                    "这是备注");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
