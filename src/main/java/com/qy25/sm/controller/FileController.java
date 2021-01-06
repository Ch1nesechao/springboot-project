package com.qy25.sm.controller;

import com.qy25.sm.common.http.AjaxResult;
import com.qy25.sm.util.fileUtil.FileDownload;
import com.qy25.sm.util.fileUtil.PicFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private PicFileUpload picFileUpload;
    @Autowired
    private FileDownload fileDownload;

    @PostMapping()
    public AjaxResult<String> upload(@RequestPart Part part){
        try {
            String filePath = picFileUpload.getFilePath(part);
            return AjaxResult.success(filePath);
        } catch (IOException e) {
            return AjaxResult.error();
        }
    }

    @GetMapping()
    public ResponseEntity<byte[]> download(@RequestParam String path) throws IOException {
        return fileDownload.getFile(path);
    }
}
