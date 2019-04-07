package web.servlet.user;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/activate")
public class ActivateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activationCode = request.getParameter("activationCode");
        UserService userService = new UserService();
        // 注意激活有效时间
        userService.activate(activationCode);
        response.getWriter().write("激活成功，请<a href='"+request.getContextPath()+"/index.jsp'>登录</a>");
        return;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
