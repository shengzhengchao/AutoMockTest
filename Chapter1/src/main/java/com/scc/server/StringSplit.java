package com.scc.server;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringSplit {

    @Test
    public void splitString1() {
        String originalString = "apple, banana, cherry";
        String[] split = originalString.split(",\\s*");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    public void splitString2() {
        String originalString = "applebananacherry";
        int start = 0;
        int end = originalString.indexOf('a', start); // 从start位置查找字符'a'
        System.out.println("end的值: " + end);

        while (end != -1) {
            String substring = originalString.substring(start, end + 1);
            System.out.println(substring);
            start = end + 1; // 更新起始位置为当前找到的字符的下一个位置
            end = originalString.indexOf('a', start ); // 继续查找下一个'a'

            }
    }

    @Test
    public void splitString3() {
        String originalString = "apple,banana,cherry";
        Pattern pattern = Pattern.compile("[A-z]+"); // 定义正则表达式，这里是以逗号为分隔符
        Matcher matcher = pattern.matcher(originalString);

        while (matcher.find()) { // 查找匹配项
            System.out.println(originalString.substring(matcher.start(), matcher.end())); // 打印匹配到的子字符串
        }

    }

    @Test
    public void splitString4() {
        String originalString = "# 交通安全培训\n" +
                "## 交通安全重要性\n" +
                "### 交通安全的社会意义\n" +
                "#### 减少交通事故发生\n" +
                "* 据公安部统计，2022年全国共发生道路交通事故约24万起，造成6.2万人死亡，通过加强交通安全管理，可有效降低事故发生率，减少人员伤亡和经济损失，提升社会运行效率。\n" +
                "\n" +
                "#### 保护人民生命财产安全\n";
        String[] split = originalString.split("\\n+");
        System.out.println(split.length);
        for (String s : split) {
            System.out.println("子字符串: " + s);
        }

    }
}
