package ink.openmind.string_captcha;

import ink.openmind.util.StrCapatchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：Wangzhuang2
 * @version : 1.0.0
 * @date ：Created in 2020/11/29 22:31
 * DESC
 */
@Controller()
@RequestMapping(value = "strcaptcha")
public class StringVerificationCodeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 跳转到字符串验证码测试页面
    @RequestMapping(value = "testpage")
    public String loginstr(){
        return "string_captcha_page";
    }


    // 字符串验证码验证成功后跳转到首页(index.html)
    @RequestMapping(value = "index")
    public String loginIndex(){
        return "index";
    }

      /////////////////////////////////////////////////////////////////////////////////////////////////////
     //                                  表单验证（验证码验证）
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "form01")
    public String judgeValiCode(@RequestParam(name = "inCode")String incode, HttpServletRequest request){
        logger.warn("incode: {}, flagCode: {}",incode, StrCapatchaUtil.flagCode);
        if(incode.equals(StrCapatchaUtil.flagCode)){
            return "index";
        }else{
            request.setAttribute("err","验证码输入有误！请重新输入！");
            return "string_captcha_page";
        }

    }
    // 后端生成验证码
    @RequestMapping(value = "createstrcapatch")
    public void getStringVerificationCode(HttpServletResponse response) throws IOException {
        // 存为图片发送
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(StrCapatchaUtil.getStringVerificationCode(),"png",outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
