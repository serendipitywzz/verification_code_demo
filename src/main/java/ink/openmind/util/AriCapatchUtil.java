package ink.openmind.util;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Description: 后端生成算术验证码
 * @Author: Wangzhuang2
 * @Version: 1.0.0
 * @CreateDate: Created in 2021/1/4 17:52
 * @UpdateDate: [序号][日期YYYY-MM-DD][更改人姓名][变更描述]
 */
public class AriCapatchUtil {


    private static Random random = new Random(); // 随机数对象
    private static int width = 140; // 宽度
    private static int height = 30; // 高度
    private static int fontSize = 18; // 字体大小
    private static String str = "+-";
    public static int result = -1; // 保存计算结果
    public  static String flagCode; // 存储服务端每次生成的验证码

    // 生成随机字符串
    private static String randCode(){
        int one = random.nextInt(30);
        int two = random.nextInt(30);
        char op = str.charAt(random.nextInt(str.length()));
        switch (op){
            case '+':
                result = one + two;
                break;
            case '-':
                result = one - two;
                break;
//            case '*':
//                result = one * two;
//                break;
//            case '/':
//                result = one / two;
//                break;
        }
        return "" + one + op + two + " = ?";
    }

    // 随机颜色构成
    private static Color getRandColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r,g,b);
    }

    // 生成验证码
    public static BufferedImage createArimeticCaptcha() throws IOException {
        // 1. 创建画板
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        // 2. 创建画笔
        Graphics2D pen = (Graphics2D)bufferedImage.getGraphics();
        // 3. 生成随机内容
        String code = randCode();
//        String code = (int) (1000 + Math.random() * 9000) + ""; // 暂时数据模拟
        // 4. 绘制内容
        // 4.1 设置绘制区域
        pen.fillRect(0, 0, width, height);
        // 4.2 设置字体
        pen.setFont(new Font("微软雅黑",Font.BOLD,fontSize));
        // 4.3 按顺序逐个绘制字符
        for(int i = 0; i < code.length(); i++){
            pen.setColor(getRandColor());
            // 绘制字符
            pen.drawString(code.charAt(i) + "",5+i*fontSize,(fontSize + height) / 2);
        }
        // 4.4 干扰线添加
        for(int i=0;i<40;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            pen.drawLine(x, y, x + x1, y + y1);
        }

        return bufferedImage;
    }
}
