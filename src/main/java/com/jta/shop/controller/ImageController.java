package com.jta.shop.controller;

import com.jta.shop.JtaApplication;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;

/**
 * @author azozello
 */

@RestController
public class ImageController {

    private final String PATH = "src/main/resources/photo/";

    private HashMap<String, String> TO_MIME = new HashMap<>();
    private HashMap<String, String> FROM_MIME = new HashMap<>();

    public ImageController(){
        FROM_MIME.put("image/gif","gif");
        FROM_MIME.put("image/jpeg","jpeg");
        FROM_MIME.put("image/pjpeg","pjpeg");
        FROM_MIME.put("image/png","png");
        FROM_MIME.put("image/tiff","tiff");
        FROM_MIME.put("image/svg+xml","svg");
        FROM_MIME.put("image/vnd.microsoft.icon","icon");
        FROM_MIME.put("image/vnd.wap.wbmp","wbmp");
        FROM_MIME.put("image/webp","webp");

        TO_MIME.put("gif","mage/gif");
        TO_MIME.put("jpeg","image/jpeg");
        TO_MIME.put("pjpeg","image/pjpeg");
        TO_MIME.put("png","image/png");
        TO_MIME.put("tiff","image/tiff");
        TO_MIME.put("svg","image/svg+xml");
        TO_MIME.put("icon","image/vnd.microsoft.icon");
        TO_MIME.put("wbmp","image/vnd.wap.wbmp");
        TO_MIME.put("webp","image/webp");
    }

    @RequestMapping(value = "/image/{name}")
    public void getImageByName(@PathVariable String name, HttpServletResponse response){
        File f = new File(PATH+name);
        try (InputStream is = new FileInputStream(f);
             OutputStream os = response.getOutputStream()){

            response.setContentType(TO_MIME.get(name.split("_")[0]));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes,0,read);
            }

            response.flushBuffer();
        } catch (IOException ioe){
            ioe.printStackTrace();
            JtaApplication.getLogger().error(ioe.getMessage());
        }
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String tempPhotoName = createPhotoName(file.getContentType(),file.getOriginalFilename());

                BufferedOutputStream stream = new BufferedOutputStream
                        (new FileOutputStream(new File(PATH+tempPhotoName)));

                stream.write(bytes);
                stream.close();
                JtaApplication.getLogger().info("Photo`s name: "+tempPhotoName);
                return tempPhotoName;
            } catch (Exception e) {
                e.printStackTrace();
                JtaApplication.getLogger().error(e.getMessage());
                return e.getMessage();
            }
        } else {
            JtaApplication.getLogger().error(file.getOriginalFilename()+" is empty");
            return "File is empty!";
        }
    }

    private String createPhotoName(String contentType, String name){
        Date date = new Date();
        String result = FROM_MIME.get(contentType)+"_"+name;
        result += "_"+date.getTime();
        result = result.replaceAll("\\.","");
        return result;
    }
}
