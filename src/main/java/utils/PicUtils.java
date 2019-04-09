package utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>
 *     程序包com.sun.image.codec.jpeg不存在
 *     解决方案：<a href="https://blog.csdn.net/u011431550/article/details/47712349"></a>
 * </p>
 *
 * 制作图片略缩图
 */
public class PicUtils {
    private String srcFile;
    private String destFile;
    private int width;
    private int height;
    private Image image;

    public PicUtils(String fileName) throws IOException {
        File file = new File(fileName);
        this.srcFile = fileName;
        int index = this.srcFile.lastIndexOf(".");
        String ext = this.srcFile.substring(index);
        this.destFile = this.srcFile.substring(0, index)+"_thumbnail"+ext;
        this.image = ImageIO.read(file);
        // 获取原图宽和高
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);
    }

    /**
     * 强制压缩|放大图片到指定大小
     * @param width
     * @param height
     * @throws IOException
     */
    public void resize(int width, int height) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 绘制缩小后的图
        bufferedImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        FileOutputStream outputStream = new FileOutputStream(destFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
        encoder.encode(bufferedImage);
        outputStream.close();
    }

    /**
     * 按照指定比例缩放图片
     * @param scale
     */
    public void resize(double scale) throws IOException {
        int width = (int) (this.width*scale);
        int height = (int) (this.height*scale);
        resize(width, height);
    }

    /**
     * 以宽度为基准，等比例缩放图片
     * @param width
     */
    public void resizeByWidth(int width) throws IOException {
        int height = this.height * width / this.width;
        resize(width, height);
    }

    /**
     * 以高度为基准，等比例缩放图片
     * @param height
     */
    public void resizeByHeight(int height) throws IOException {
        int width = this.width * height / this.height;
        resize(width, height);
    }

    public void resizeFix(int width, int height) throws IOException {
        if (width/height > this.width/this.height) {
            resizeByWidth(width);
        } else {
            resizeByHeight(height);
        }
    }

    public String getDestFile() {
        return destFile;
    }

    public void setDestFile(String fileName) throws Exception {
        if (!fileName.endsWith(".jpg")) {
            throw new Exception("Dest File must end with .jpg");
        }
        this.destFile = fileName;
    }

    public int getSrcWidth() {
        return width;
    }
    public int getSrcHeight() {
        return height;
    }
}
