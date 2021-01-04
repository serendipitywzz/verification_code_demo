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
 * @Description: 后端生成字符串验证码图片
 * @Author: Wangzhuang2
 * @Version: 1.0.0
 * @CreateDate: Created in 2021/1/3 22:37
 * @UpdateDate: [序号][日期YYYY-MM-DD][更改人姓名][变更描述]
 */
public class StrCapatchaUtil {
    private static Random random = new Random();
    private static int width = 60; // 宽度
    private static int height = 30; // 高度
    private static int fontSize = 15; // 字体大小
    public static String flagCode; // 存储服务端每次生成的验证码

    private static Color getRandColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r,g,b);
    }


    // 获取缓存区的图片
    public static BufferedImage getStringVerificationCode(){
        // 1. 创建画板
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        // 2. 创建画笔
        Graphics2D pen = (Graphics2D)bufferedImage.getGraphics();
        // 3. 生成随机内容
        String code = (int) (1000 + Math.random() * 9000) + ""; // 暂时数据模拟
        flagCode = code;
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
