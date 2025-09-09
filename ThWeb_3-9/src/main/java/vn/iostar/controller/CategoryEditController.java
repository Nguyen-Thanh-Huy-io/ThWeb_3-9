package vn.iostar.controller;

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
import vn.iostar.utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet("/admin/category/edit")
@MultipartConfig
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService cateService = new CategoryServiceImpl();
    private static final String UPLOAD_DIR = "E:\\upload";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String message = null;
        Category category = null;

        try {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                int id = Integer.parseInt(idStr);
                category = cateService.get(id);
                if (category == null) {
                    message = "Danh mục không tồn tại với ID: " + id;
                }
            } else {
                message = "ID danh mục không được cung cấp.";
            }
        } catch (NumberFormatException e) {
            message = "ID danh mục không hợp lệ.";
        }

        request.setAttribute("category", category);
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/edit-category.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Category categoryToUpdate = new Category();
        String message = null;

        try {
            // Lấy ID và các thông tin trực tiếp từ request.getParameter
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                throw new IllegalArgumentException("ID danh mục không được cung cấp.");
            }
            int id = Integer.parseInt(idStr);
            String name = request.getParameter("name");
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên danh mục không được rỗng.");
            }

            // Lấy category hiện có từ database
            categoryToUpdate = cateService.get(id);
            if (categoryToUpdate == null) {
                throw new SQLException("Danh mục không tồn tại với ID: " + id);
            }
            
            // Cập nhật tên mới
            categoryToUpdate.setCatename(name);

            // Xử lý file upload
            Part filePart = request.getPart("icon");
            String fileName = filePart != null ? filePart.getSubmittedFileName() : null;

            // Chỉ xử lý nếu người dùng chọn file mới
            if (fileName != null && !fileName.isEmpty()) {
                // Xóa file ảnh cũ
                String oldIconPath = categoryToUpdate.getIcon();
                if (oldIconPath != null && !oldIconPath.isEmpty()) {
                    File oldFile = new File(UPLOAD_DIR + File.separator + oldIconPath);
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }
                
                // Tạo tên file mới
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
                
                // Lưu file mới
                filePart.write(filePath);
                
                // Cập nhật đường dẫn ảnh mới
                categoryToUpdate.setIcon("category/" + newFileName);
            }

            // Cập nhật vào database
            cateService.edit(categoryToUpdate);
            response.sendRedirect(request.getContextPath() + "/admin/category/list");

        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            request.setAttribute("category", categoryToUpdate);
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/edit-category.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Lỗi khi cập nhật danh mục: " + e.getMessage();
            request.setAttribute("category", categoryToUpdate);
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/edit-category.jsp");
            dispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            message = "Lỗi khi xử lý file upload: " + e.getMessage();
            request.setAttribute("category", categoryToUpdate);
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/edit-category.jsp");
            dispatcher.forward(request, response);
        }
    }
}