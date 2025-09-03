package vn.iostar.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.model.Category;
import vn.iostar.utils.Constant;
import vn.iostar.service.CategoryService;
import vn.iostar.service.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryAddController
 */
@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
	CategoryService cateService = new CategoryServiceImpl();
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CategoryAddController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	RequestDispatcher dispatcher =request.getRequestDispatcher("add-category.jsp");
    	dispatcher.forward(request, response);
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	Category category = new Category();
    	DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
    	ServletFileUpload servletFileUpload = new
    	ServletFileUpload(diskFileItemFactory);
    	servletFileUpload.setHeaderEncoding("UTF-8");
    	try 
    	{
	    	response.setContentType("text/html");
	    	response.setCharacterEncoding("UTF-8");
	    	request.setCharacterEncoding("UTF-8");
	    	List<FileItem> items = servletFileUpload.parseRequest(request);
	    	for (FileItem item : items) {
	    	if (item.getFieldName().equals("name")) {
	    		category.setCatename(item.getString("UTF-8"));
	    	} 
	    	else if (item.getFieldName().equals("icon")) 
	    	{
	    		String originalFileName = item.getName();
	    		int index = originalFileName.lastIndexOf(".");
	    		String ext = originalFileName.substring(index + 1);
	    		String fileName = System.currentTimeMillis() + "." + ext;
	    		File file = new File(Constant.DIR + "/category/" + fileName);
	    		item.write(file);
	    		category.setIcon("category/"+fileName);
    		}
	    	}
    		cateService.insert(category);
    		response.sendRedirect(request.getContextPath() + "/admin/category/list");
    	} 
    	catch (FileUploadException e) {
    		e.printStackTrace();
    	} 
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}

}
