package web.servlet.user;

import entity.User;
import exception.ActivateException;
import exception.LoginException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 校验参数
        Map<String, String> map = new HashMap<>();
        if (username == null || username.trim().length() == 0) {
            map.put("login.username.error", "用户名不能为空");
        }
        if (password == null || password.trim().length() == 0) {
            map.put("login.password.error", "密码不能为空");
        }
        if (map.size() != 0) {
            request.setAttribute("map", map);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        UserService userService = new UserService();
        try {
            User user = userService.login(username, password);
            // 是否勾选了记住用户名
            String remember = request.getParameter("remember");
            if ("on".equals(remember)) {
                Cookie cookie = new Cookie("remember", URLEncoder.encode(username, "UTF-8"));
                cookie.setMaxAge(7*24*60*60);
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("remember", URLEncoder.encode(username, "UTF-8"));
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            String autoLogin = request.getParameter("autologin");
            if ("on".equals(autoLogin)) {
                Cookie cookie = new Cookie("auto_login", URLEncoder.encode(username, "UTF-8")+"::"+password);
                cookie.setMaxAge(7*24*60*60);
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("auto_login", URLEncoder.encode(username, "UTF-8")+"::"+password);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            request.getSession().invalidate(); // 先销毁session
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return;
        } catch (LoginException e) {
            e.printStackTrace();
            request.setAttribute("login.message", e.getMessage());
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        } catch (ActivateException e) {
            e.printStackTrace();
            request.setAttribute("login.message", e.getMessage());
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}