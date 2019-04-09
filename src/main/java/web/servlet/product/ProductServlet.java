package web.servlet.product;

import entity.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import service.ProductService;
import utils.PicUtils;
import utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "add":
                add(request, response);
                break;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = new HashMap<>();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // 设置文件缓冲区大小
        diskFileItemFactory.setSizeThreshold(1024 * 100);
        // 设置临时文件存储位置
        diskFileItemFactory.setRepository(new File(getServletContext().getRealPath("/temp")));

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        // 解决上传文件中文名乱码问题
        servletFileUpload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                // 不是上传组件
                if (fileItem.isFormField()) {
                    map.put(fileItem.getFieldName(), new String[]{fileItem.getString("UTF-8")});
                } else {
                    String fileName = fileItem.getName();
                    System.out.println(fileName);
                    String shortFileName = UploadUtils.getShortFileName(fileName);
                    System.out.println(shortFileName);
                    String uuidFileName = UploadUtils.generateRandomFileName(fileName);
                    System.out.println(uuidFileName);
                    String dirPath = UploadUtils.generateRandomDir(uuidFileName);
                    String uploadPath = this.getServletContext().getRealPath("/upload");
                    File parentDir = new File(uploadPath, dirPath);
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }
//                    map.put("imgUrl", new String[]{uploadPath+dirPath});
//                    map.put("imgUrl", new String[]{"/upload" + dirPath + "/" + uuidFileName});
                    File file = new File(parentDir, uuidFileName);
                    IOUtils.copy(fileItem.getInputStream(), new FileOutputStream(file));
                    // 删除临时文件
                    fileItem.delete();


                    // 生成缩略图
                    PicUtils picUtils = new PicUtils(file.getCanonicalPath()); // 获取上传文件的绝对磁盘路径
                    // 生成200*200的缩略图
                    picUtils.resize(200, 200);
                    // 封装imgUrl
                    map.put("imgUrl", new String[]{"/upload" + dirPath + "/" + uuidFileName});
                }
            }
            Product product = new Product();
            BeanUtils.populate(product, map);
            ProductService productService = new ProductService();
            productService.add(product);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
