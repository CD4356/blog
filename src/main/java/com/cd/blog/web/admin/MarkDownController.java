package com.cd.blog.web.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/27 23:30
 */
@RestController
public class MarkDownController {

    //时间格式
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    //随机类
    private static Random random = new Random();

    /**
     * MarkDown编辑器图片上传
     * @param uploadFile
     * @return
     */
    @PostMapping("/blog_img")
    public Map<String,Object> uploadBlogImg(@RequestParam(value = "editormd-image-file", required = false) MultipartFile uploadFile){
        Map<String,Object> map = new HashMap<>();
        if(uploadFile != null){
            // 获取文件随机名称
            String realFileName = getRandomFileName();
            // 获取上传文件的原文件名
            String fileName = uploadFile.getOriginalFilename();
            // 获取上传文件的后缀名
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            // 指定文件存储根目录
            String basePath = "/home/image/blog/";
            // 创建File对象,注意这里不是创建一个目录或一个文件,你可以理解为是 获取指定目录中文件的管理权限(增改删除判断等 . . .)
            File tempFile=new File(basePath);
            // 判断File对象对应的目录是否存在
            if(!tempFile.exists()){
                // 创建以此抽象路径名命名的目录,注意mkdir()只能创建一级目录,所以它的父级目录必须存在
                tempFile.mkdirs();
            }
            //拼接文件存储的绝对路径
            String targetPath = basePath + realFileName + suffix;
            // 在指定路径中创建一个文件(该文件是空的)
            File file=new File(targetPath);
            try{
                // 将上传的文件写入指定文件
                uploadFile.transferTo(file);
                map.put("success", 1);
                map.put("message", "图片上传成功！");
                map.put("url", targetPath);
            }catch (Exception e){
                map.put("success", 0);
                map.put("message", "图片上传失败：" + e.getMessage());
            }
        }
        return map;
    }


    /**
     * 生产随机文件名 ~ ~ 当前的(年月日时分秒 +5位随机数)
     * @return
     */
    public static String getRandomFileName() {
        //获取10000~99999之间的5位随机数
        int randomNumber = random.nextInt(99999) + 10000;
        //获取当前时间,并按指定时间格式返回
        String nowTime = format.format(new Date());
        String randomFileName = nowTime + randomNumber;
        return randomFileName;
    }

}
