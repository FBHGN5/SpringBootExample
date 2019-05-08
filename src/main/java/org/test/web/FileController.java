package org.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 *
 * 模拟文件上传下载
 */
@Controller
@RequestMapping("/index")
public class FileController {
    @RequestMapping(value = "/up")
    public String Inde2()
    {
        return "upload";
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws IOException {
        for(int i=0;i<file.length;i++){
            System.out.println("file name = "+file[i].getOriginalFilename());
            // 获取文件名
            String fileName = file[i].getOriginalFilename();

            // 文件上传的路径
            String filePath = "C:/upload/";
          //  String filePath= ClassUtils.getDefaultClassLoader().getResource("").getPath();
            System.out.println("文件上传的路径--"+filePath);
            // fileName处理
            fileName = filePath+ UUID.randomUUID()+fileName;

            // fileName = filePath+"/"+fileName;
            // 文件对象
            File dest = new File(fileName);
            // 创建路径
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();

            }
            try {
                file[i].transferTo(dest);
                return "上传成功";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传失败";

        // String path = request.getSession().getServletContext().getRealPath("../../src/main/webapp/resources/uploadfile");

    }
    @RequestMapping("/download")
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //模拟文件，myfile.txt为需要下载的文件
        String fileName="D:\\sql文件\\1.jpg";
        // String fileName = request.getSession().getServletContext().getRealPath("../../src/main/webapp/resources/uploadfile")+"/"+file;
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = "1.jpg";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();

    }
}
