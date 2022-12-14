package com.itheima.servlet.admin;



import com.itheima.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin_noticeDetail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	AdminService as = new AdminService();
	String act = request.getParameter("act");//获得动作类型
	String nid = request.getParameter("nid");
	List<Map<String, Object>> list = as.selectAnotice(nid);
	request.setAttribute("anotice", list.get(0));
	RequestDispatcher rds = null;
	//查看详情
	if("detail".equals(act)){
		rds = request.getRequestDispatcher("admin/noticeDetail.jsp");
	}
	//删除
	else if("delete".equals(act)){
		as.deleteAnotice(nid);
		//删除后回到查询
		rds = request.getRequestDispatcher("admin_noticeManager?act=manager");
	}
	rds.forward(request, response);
	}

}
