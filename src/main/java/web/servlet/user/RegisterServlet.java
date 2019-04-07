package web.servlet.user;

import entity.User;
import exception.RegisterException;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import utils.ActivationCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captcha = request.getParameter("captcha");
        String captchaSession = (String) request.getSession().getAttribute("captcha_session");
        // 必须从Session中删除
        request.getSession().removeAttribute("captcha_session");
        if (!captchaSession.equalsIgnoreCase(captcha)) {
            request.setAttribute("register.message", "验证码错误");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Map<String, String> map = user.validation();
        if (map.size() != 0) {
            request.setAttribute("map", map);
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }
        // 手动封装激活码
        user.setActivationCode(ActivationCodeUtils.getActivation_code());
        UserService userService = new UserService();
        try {
            userService.register(user);
            response.sendRedirect(request.getContextPath()+"/user/register_success.jsp");
            return;
        } catch (RegisterException e) {
            e.printStackTrace();
            request.setAttribute("register.message", e.getMessage());
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
