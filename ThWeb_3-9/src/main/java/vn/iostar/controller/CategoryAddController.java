package vn.iostar.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.model.Category;
import vn.iostar.service.CategoryService;
import vn.iostar.service.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryAddController
 */
@WebServlet("/admin/category/add")
@MultipartConfig
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService cateService = new CategoryServiceImpl();
    private static final String UPLOAD_DIR = "E:\\upload";

    public CategoryAddController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        String message = null;

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // Lấy thông tin từ request
            String name = req.getParameter("name");
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên danh mục không được rỗng.");
            }
            category.setCatename(name);

            // Xử lý file upload
            Part filePart = req.getPart("icon");
            String fileName = filePart != null ? filePart.getSubmittedFileName() : null;

            // Chỉ xử lý nếu người dùng chọn file
            if (fileName != null && !fileName.isEmpty()) {
                int index = fileName.lastIndexOf(".");
                if (index < 0) {
                    throw new IllegalArgumentException("File không có phần mở rộng hợp lệ.");
                }
                String ext = fileName.substring(index + 1);
                String newFileName = System.currentTimeMillis() + "." + ext;

                // Đường dẫn lưu file
                String uploadPath = UPLOAD_DIR + File.separator + "category";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String filePath = uploadPath + File.separator + newFileName;
                filePart.write(filePath);
                category.setIcon("category/" + newFileName);
            }

            // Thêm danh mục vào database
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            req.setAttribute("category", category);
            req.setAttribute("message", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/add-category.jsp");
            dispatcher.forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
            message = "Lỗi khi xử lý file upload: " + e.getMessage();
            req.setAttribute("category", category);
            req.setAttribute("message", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/add-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}