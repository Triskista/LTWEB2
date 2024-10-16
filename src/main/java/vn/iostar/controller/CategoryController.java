package vn.iostar.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category; 
import vn.iostar.service.ICategoryService;
import vn.iostar.service.CategoryService;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update",
		"/admin/category/delete", "/admin/category/search"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	private String getFileName(Part part) {
		return part.getSubmittedFileName();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("categoryid"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("categoryid"));
			cateService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("search")) {
			String key = req.getParameter("keyword");
			List<Category> list = cateService.searchByName(key);
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String url = req.getRequestURI();
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");

	    String categoryname = req.getParameter("categoryname");
	    int status = Integer.parseInt(req.getParameter("status"));
	    String imagesLink = req.getParameter("imageslink"); // Lấy link ảnh từ form

	    String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
	    }

	    String images = ""; // Khởi tạo biến lưu tên ảnh

	    try {
	        // Ưu tiên sử dụng link từ trường imageslink nếu không rỗng
	        if (imagesLink != null && !imagesLink.trim().isEmpty()) {
	            images = imagesLink; // Nếu có link ảnh, sử dụng link này
	        } else {
	            // Kiểm tra nếu người dùng tải lên file ảnh
	            for (Part part : req.getParts()) {
	                if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
	                    String fileName = getFileName(part);
	                    part.write(uploadPath + File.separator + fileName); // Lưu file ảnh lên server
	                    images = fileName; // Lưu đường dẫn ảnh
	                }
	            }
	        }
	    } catch (FileNotFoundException fne) {
	        req.setAttribute("message", "Có lỗi xảy ra: " + fne.getMessage());
	    }

	    if (url.contains("insert")) {
	        // Xử lý thêm mới category
	        Category category = new Category();
	        category.setCategoryname(categoryname);
	        category.setStatus(status);
	        category.setImages(images);
	        cateService.insert(category);
	        resp.sendRedirect(req.getContextPath() + "/admin/categories");
	    } else if (url.contains("update")) {
	        // Xử lý cập nhật category
	        int categoryid = Integer.parseInt(req.getParameter("categoryid"));

	        // Nếu không có ảnh nào (cả file và link), lấy ảnh hiện tại từ DB
	        if (images.isEmpty()) {
	            Category existingCategory = cateService.findById(categoryid);
	            images = existingCategory.getImages(); // Giữ lại ảnh cũ nếu không có ảnh mới
	        }

	        Category category = new Category();
	        category.setCategoryid(categoryid);
	        category.setCategoryname(categoryname);
	        category.setStatus(status);
	        category.setImages(images);
	        cateService.update(category);
	        resp.sendRedirect(req.getContextPath() + "/admin/categories");
	    }
	}
}
