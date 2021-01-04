package ink.openmind.simple_slider_captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Wangzhuang2
 * @Version: 1.0.0
 * @CreateDate: Created in 2021/1/2 15:34
 * @UpdateDate: [序号][日期YYYY-MM-DD][更改人姓名][变更描述]
 */
@Controller
@RequestMapping(value = "simpleslidercaptcha")
public class SimpleSliderVerificationCodeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "testpage")
    public String toLoginPage(){
        return "simple_slider_captcha_page";
    }

}
