package yu.mthgh123.booksmall.controller.common;

import yu.mthgh123.booksmall.common.Constants;
import yu.mthgh123.booksmall.util.BooksMallUtils;
import yu.mthgh123.booksmall.util.Result;
import yu.mthgh123.booksmall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author yu
 * @link https://github.com/mthgh123/booksmall
 */
@Controller
@RequestMapping("/admin")
public class UploadController {

    @PostMapping({"/upload/file"})
    @ResponseBody
    //@RequestParam("file")注释的file就是从外界选中的那个file，此时作为该上传函数的参数来使用
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        //形成文件名
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //文件路径
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
        //创建文件（文件路径名(pathname)=文件路径(Constants.FILE_UPLOAD_DIC)+文件名(newFileName)）
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            //将file的文件内容copy到destfile中
            file.transferTo(destFile);
            Result resultSuccess = ResultGenerator.genSuccessResult();
            //设定返回的Result对象的data值为图片的uri
            //注意：getHost()方法是获得host部分，也就是：http://localhost:8080，不获取后面的部分
            resultSuccess.setData(BooksMallUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            return resultSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }

}
