package com.jedrek.graduation.utils;

import org.springframework.stereotype.Component;
import sun.reflect.annotation.ExceptionProxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class DocumentUtil {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\wangjie22438\\Music\\计科1404_0902140427_王杰_调研报告.docx");
        wordToPdf(file);

    }

    public static String handleDocumentPath(String fileName, Date createdDate) {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ans = df.format(day);
        if (!fileName.isEmpty()) {
            String directory = parseDate(ans);
            String path = directory + File.separator + fileName;
            return path;
        }

        return "";
    }

    /**
     * 解析日期的年月日，即从2018-03-18 21:31:16转为20180318
     *
     * @param date
     * @return
     */
    private static String parseDate(String date) {
        String[] pieces = date.split(" ", 2);
        String[] split = pieces[0].split("-");
        String ans = String.join(File.separator, split);
        return ans;
    }

    public static void wordToPdf(File file) {
        String python_script = "C:\\Users\\wangjie22438\\Music\\word_to_pdf.py";
        try {
            String name = file.getName();
            String path = file.getPath();
            if (Objects.equals(name.split("\\.", 2)[1], "docx")) {
                String[] args1 = new String[]{"python", python_script, path};
                Process pr = Runtime.getRuntime().exec(args1);
                pr.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
