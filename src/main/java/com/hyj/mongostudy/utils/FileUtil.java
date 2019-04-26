package com.hyj.mongostudy.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author huyuanjia
 * @date 2019/3/5 14:13
 * 文件处理
 */
public class FileUtil {
    /**
     * static文件夹路径
     */
    public static final String STATIC_URL = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/";

    /**
     * 下载文件
     *
     * @param path
     * @param request
     * @param response
     */
    public static void download(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String fileName = file.getName();
            String agent = (String) request.getHeader("USER-AGENT");
            if (CheckParamsUtil.check(fileName)) {
                try {

                    if (agent != null && agent.indexOf("Firefox") > -1) {// 处理火狐乱码
                        fileName = "=?UTF-8?B?" + (new String(Base64
                                .encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
                    } else {
                        //其他浏览器
                        fileName = URLEncoder.encode(fileName, "UTF-8");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }


            // 取得文件的后缀名。
//            String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();

            // 清空response
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            // 设置response的Header
//            response.addHeader("Content-Length", "" + file.length());
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[1024];

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            int len=-1;
            while ((len=fis.read(buffer) )!= -1) {
                toClient.write(buffer,0,len);
            }
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
            fis.close();

            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
