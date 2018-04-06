package com.jedrek.graduation.utils;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DocumentUtil {
    public static void main(String[] args) {

    }
    public static String handleDocumentPath(String fileName, String uploadDate) {
//        Date day=new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String ans = df.format(day);
//        2018-03-18 21:31:16
        if ( !fileName.isEmpty()) {
            String directory = parseDate(uploadDate);
            String path = directory + File.separator + fileName;
            return path;
        }

        return "";
    }

    /**
     *  解析日期的年月日，即从2018-03-18 21:31:16转为20180318
     * @param date
     * @return
     */
    private static String parseDate(String date) {
        String[] pieces = date.split(" ",2);
        String[] split = pieces[0].split("-");
        String ans = String.join("", split);
        return ans;
    }


}
