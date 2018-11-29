package com.aaa.sb.util;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * className:FileUtill
 * discription:
 * author:zz
 * createTime:2018-11-23 08:32
 */
public class FileUtill {
    public static String upLaodFile(String savepath , MultipartFile multipartFile){
        String originalFilename=multipartFile.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID()+suffix;
        File file= new File(savepath+newFileName);
        try {
           multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFileName;
    }

    /**
     * 通用下载方法
     * @param filename
     * @param response
     * @return
     */
    public static String downLoad(String filename,HttpServletResponse response){
        String filePath = "D:/file" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");//MIME类型
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
           /* int i = bis.read(buffer);
            while(i != -1){
                os.write(buffer);
                i = bis.read(buffer);
            }*/
                int i=0;
                while((i = bis.read(buffer))!=-1){
                    os.write(buffer,0,i);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

}
