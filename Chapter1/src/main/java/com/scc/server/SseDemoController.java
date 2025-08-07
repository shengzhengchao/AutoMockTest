package com.scc.server;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class SseDemoController {

    @RequestMapping(value = "/sse-demo", produces = "text/event-stream", method = RequestMethod.GET)
    public SseEmitter  streamData() {
        //创建sseEmitter对象, 设置超时时间300s
        SseEmitter sseEmitter = new SseEmitter(300000L);

        //异步任务模拟流式输出
        new Thread( () -> {
            try {
                for (int i = 0; i <= 20; i++) {
                    String message = "第" + i + "条消息";
                    sseEmitter.send(message);
                    Thread.sleep(300);// 每300ms发送一次
                }
                sseEmitter.complete();// 完成推送
            } catch (IOException | InterruptedException e) {
                sseEmitter.completeWithError(e);
            }

        }).start();
        return sseEmitter;
    }

    @RequestMapping(value = "/sse-demo2", produces = "text/event-stream", method = {RequestMethod.GET,
            RequestMethod.POST})
    public SseEmitter  streamData2() {
        //创建sseEmitter对象, 设置超时时间300s
        SseEmitter sseEmitter = new SseEmitter(200000L);
        String originalString = "#  田忌赛马小学语文教案\n" +
                "##  故事背景介绍\n" +
                "###  战国时期齐国赛马故事\n";

        //获取分割后的字符串数组
        String[] split = originalString.split("\\n");
        //异步任务模拟流式输出
        new Thread( () -> {
            try {
                //遍历数组中的元素, 放入sseEmitter中
                for (String s : split) {
                    sseEmitter.send(ServerSentEvent.builder(s).build());
                    Thread.sleep(1000);
                }
                sseEmitter.complete();// 完成推送
            } catch (IOException | InterruptedException e) {
                sseEmitter.completeWithError(e);
            }

        }).start();
        return sseEmitter;
    }

    @RequestMapping(value = "/sse-demo3", method = {RequestMethod.GET, RequestMethod.POST})
    public void customSseStream(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        String originalString = "";

        //获取分割后的字符串数组
        String[] split = originalString.split("\\n");
        try {

            PrintWriter writer = response.getWriter();
            // 自定义格式1：无前缀纯文本
            for (String s : split) {
                writer.write(s + "\n");
                writer.flush();
                Thread.sleep(200);
            }
        }  catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/sse-demo4", method = {RequestMethod.GET, RequestMethod.POST})
    public void customSseStream2(HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        // 自定义格式2：无前缀纯文本
        //使用字符输入流读取文件
        String mockFilePath = "src/main/resources/mocked_response.txt";
        FileReader fileReader = null;
        PrintWriter writer = null;
        char[] buf = new char[8];
        int readLen = 0;

        try {
            fileReader = new FileReader(mockFilePath);
            writer = response.getWriter();
            while ((readLen = fileReader.read(buf)) != -1) {
                writer.write(new String(buf, 0, readLen));
                writer.flush();
                Thread.sleep(50);
            }

        }  catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null){
                    fileReader.close();
                }
                if (writer!= null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
