package com.qy25.sm.util.fileUtil;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * 图片上传
 */
@Component
@Data
@PropertySource(value = "classpath:aliyun.properties")
public class PicFileUpload {

    /**
     * Value的赋值  实质上是属性注入
     */
    @Value("${aliyun_endpoint}")
    private String endpoint;
    @Value("${aliyun_key}")
    private String accessKeyId;
    @Value("${aliyun_secret}")
    private String accessKeySecret;
    @Value("${aliyun_bucketName}")
    private String bucketName;
    @Value("${aliyun_baseUrl}")
    private String baseUrl;

    /**
     * part 以formData形式上传文件
     * @param part
     * @return
     * @throws IOException
     */
    public String getFilePath(Part part) throws IOException {
        //文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-","")+"."+ StringUtils.getFilenameExtension(part.getSubmittedFileName());
        return aliyunUpload(fileName, part.getInputStream());
    }

//    public Collection<String> getLocalFilePath(HttpServletRequest request) throws IOException, ServletException {
//        String filePath = request.getServletContext().getRealPath("/upload/");
//        File file = new File(filePath);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        Collection<Part> parts = request.getParts();
//        Collection<String> fileRealPath = new ArrayList<>();
//        parts.forEach(part -> {
//            try {
//                InputStream inputStream = part.getInputStream();
//                String fileName = UUID.randomUUID().toString().replaceAll("-","");
//                String fileType = StringUtils.getFilenameExtension(part.getSubmittedFileName());
//                fileRealPath.add(filePath+fileName+"."+fileType);
//                byte[] bytes = new byte[inputStream.available()];
//                inputStream.read(bytes);
//                FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath+fileName+"."+fileType));
//                fileOutputStream.write(bytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        return fileRealPath;
//    }

    /**
     * base64上传
     * @param strBase64
     * @return
     */
    public String gotFilePath(String strBase64){
        //文件格式
        String fileType = strBase64.substring(strBase64.indexOf("/") + 1, strBase64.lastIndexOf(";"));
        //文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-","")+"."+fileType;
        //字符串转流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(strBase64.getBytes(StandardCharsets.UTF_8));
        return aliyunUpload(fileName, inputStream);
    }


    private String aliyunUpload(String fileName , InputStream inputStream){
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,fileName , inputStream);
// 关闭OSSClient。
        ossClient.shutdown();
        return baseUrl + fileName;
    }

}
