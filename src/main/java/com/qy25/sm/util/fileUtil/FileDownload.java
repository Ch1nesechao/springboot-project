package com.qy25.sm.util.fileUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static javax.xml.transform.OutputKeys.ENCODING;

/**
 * 文件下载
 */
@Component
@Slf4j
public class FileDownload {

    @Autowired
    private RestTemplate restTemplate;

    public static ResponseEntity<byte[]> getFile(String downloadPath) throws IOException {
        URL url = new URL(downloadPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setConnectTimeout(3*1000);
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attament", URLEncoder.encode("a.jpg", "utf-8"));
        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
    }

    public static void pointDownload(HttpServletRequest request, HttpServletResponse response, File proposeFile) {
        log.debug("下载路径" + proposeFile.getPath());
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //设置响应头
            long fSize = proposeFile.length();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(proposeFile.getName(), ENCODING));
            response.setHeader("Accept-Ranges", "bytes");
            //pos开始读取位置   last最后读取位置   sum已读取多少
            long pos = 0, last = fSize - 1, sum = 0;
            if (null!=request.getHeader("Range")){
                //断点续传
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                // 情景一：RANGE: bytes=2000070- 情景二：RANGE: bytes=2000070-2000970

            }

        } catch (Exception e) {

        }
    }
}
