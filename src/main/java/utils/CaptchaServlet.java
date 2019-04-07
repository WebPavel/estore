package utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private List<String> words = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        String path = getServletContext().getRealPath("/WEB-INF/captcha_words.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取画布和画笔
        int width = 120;
        int height = 30;
        // 绘制一张内存中的图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(getRangeColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        // 换颜色画边框
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics.setFont(new Font("宋体", Font.BOLD, 18));
        Random random = new Random();
        int index = random.nextInt(words.size());
        String word = words.get(index);
        int x = 10;
        int y = 20;
        for (int i = 0; i < word.length(); i++) {
            graphics2D.setColor(new Color(20+random.nextInt(110), 20+random.nextInt(110), 20+random.nextInt(110)));
            // 旋转角度-30、30之间
            int angle = random.nextInt(60) - 30;
            // 换成弧度
            double theta = angle * Math.PI / 180;
            char c = word.charAt(i);
            graphics2D.rotate(theta, x,y);
            graphics2D.drawString(String.valueOf(c), x, y);
            graphics2D.rotate(-theta, x, y);
            x += 30;
        }
        request.getSession().setAttribute("captcha_session", word);
        // 绘制干扰线
        graphics.setColor(getRangeColor(160, 200));
        int x1, x2, y1, y2;
        for (int i = 0; i < 10; i++) {
            x1 = random.nextInt(width);
            y1 = random.nextInt(height);
            x2 = random.nextInt(width);
            y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }
        // 释放资源
        graphics.dispose();
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    /**
     * 获取某一范围内的Color
     * @param fc
     * @param bc
     * @return
     */
    private Color getRangeColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}