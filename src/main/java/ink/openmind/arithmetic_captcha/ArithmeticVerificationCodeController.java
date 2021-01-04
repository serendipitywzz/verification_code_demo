package ink.openmind.arithmetic_captcha;

import ink.openmind.util.AriCapatchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author ：Wangzhuang2
 * @version : 1.0.0
 * @date ：Created in 2020/12/29 16:41
 * DESC 算术验证码
 */
@Controller
@RequestMapping(value = "aricaptcha")
public class ArithmeticVerificationCodeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String numPattern = "^([\\-]?+[0-9]*)"; // 匹配负数或整数【0-9内】


    // 跳转到算术验证码测试页面
    @RequestMapping(value = "testpage")
    public String loginIndexPageTest(){
        return "arithmetic_captcha_page";
    }

    // 算术验证码验证成功后跳转到首页(index.html)
    @RequestMapping(value = "index")
    public String loginPage(){
        return "index";
    }

      /////////////////////////////////////////////////////////////////////////////////////////////////////
     //                                  表单验证（验证码验证）
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "form01")
    public String judgeValiCode(@RequestParam(value = "inCode")String inCode, HttpServletRequest request){
        logger.warn("incode: " + inCode + "result: " + AriCapatchUtil.result + "来了，老弟！");
        if(Pattern.matches(numPattern,inCode) && inCode != ""){
            int inArithmeticCode = Integer.parseInt(inCode);
            if(inArithmeticCode == AriCapatchUtil.result){
                return "index";
            }else{
                request.setAttribute("err","输入数据与计算结果不符，请重新输入！");
                return "arithmetic_captcha_page";
            }
        }{
            request.setAttribute("err","输入数据为非数字，请重新输入！");
            return "arithmetic_captcha_page";
        }
    }

    // 生成验证码
    @RequestMapping(value = "arithmeticverificationcode")
    public void getArimeticCaptch(HttpServletResponse response) throws IOException {

        //存为图片发送
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(AriCapatchUtil.createArimeticCaptcha(),"png",outputStream);
        outputStream.flush();
        outputStream.close();
    }


}
