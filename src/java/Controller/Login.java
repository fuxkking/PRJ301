package Controller;

import DAL.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Đọc cookie username nếu có
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    username = c.getValue();
                    break;
                }
            }
        }

        request.setAttribute("username", username);
        request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        if ("login".equals(type)) {
            // Đăng nhập
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();

            if (dao.getAccount(username, password) != null) {
                session.setAttribute("account", dao.getAccount(username, password));

                // Nếu user chọn "Remember me", lưu username vào cookie
                String remember = request.getParameter("remember");
                if ("ON".equalsIgnoreCase(remember)) {
                    Cookie ck = new Cookie("username", username);
                    ck.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
                    response.addCookie(ck);
                }

                response.sendRedirect("Home");
            } else {
                request.setAttribute("error", "Username or password incorrect");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
            }

        } else {
            // Đăng xuất
            doLogout(request, response);
        }
    }

    protected void doLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        ses.removeAttribute("account");
        response.sendRedirect("Views/Login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet with cookie and session handling";
    }
}
