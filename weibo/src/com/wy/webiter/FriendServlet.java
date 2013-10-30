package com.wy.webiter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wy.dao.ConsumerDao;
import com.wy.dao.FriendDao;
import com.wy.form.FriendForm;
import com.wy.tool.Chinese;

public class FriendServlet extends HttpServlet {

	private FriendDao friendDao = null;

	private int method;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		method = Integer.parseInt(request.getParameter("method"));
		if (method == 0) {
			this.addFriend(request, response);// ���������Ϣ
		}
		if (method == 1) {
			this.deleteFriend(request, response);// ɾ��������Ϣ
		}
		if (method == 2) {
			this.queryFriendForm(request, response); // �����ݿ�IDΪ��������ѯһ����Ϣ
		}
		if (method == 3) {
			this.updateFriend(request, response); // �����ݿ�IDΪ��������ѯһ����Ϣ
		}

	}

	// �����ݿ�IDΪ�������޸�һ����Ϣ
	public void updateFriend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		friendDao = new FriendDao();
		FriendForm friendForm = new FriendForm();
		friendForm.setId(Integer.valueOf(request.getParameter("id")));
		friendForm.setName(Chinese.toChinese(request.getParameter("name")));
		friendForm.setQQNumber(request.getParameter("QQNumber"));
		friendForm.setDescription(Chinese.toChinese(request
				.getParameter("description")));
		if (friendDao.updateFriend(friendForm)) {
			out
					.print("<script language=javascript>alert('�޸Ĵ�������Ϣ�ɹ��������½��в�ѯ��');window.location.href='back_FriendSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('�޸�������Ϣʧ�ܣ�');history.go(-1);</script>");
		}
	
	}

	// �����ݿ�IDΪ��������ѯһ����Ϣ
	public void queryFriendForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		friendDao = new FriendDao();
		request.setAttribute("form", friendDao.queryFriendForm(request
				.getParameter("id")));

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_FriendUpdate.jsp");
		requestDispatcher.forward(request, response);

	}

	// ɾ��������Ϣ
	public void deleteFriend(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		friendDao = new FriendDao();
		Integer id = Integer.valueOf(request.getParameter("id"));

		if (friendDao.deleteFriend(id)) {
			out
					.print("<script language=javascript>alert('ɾ����������Ϣ�ɹ��������½��в�ѯ��');window.location.href='back_FriendSelect.jsp';</script>");
		} else {
			out
					.print("<script language=javascript>alert('ɾ��������Ϣʧ�ܣ�');history.go(-1);</script>");
		}

	}

	// ���������Ϣ
	public void addFriend(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		friendDao = new FriendDao();
		FriendForm friendForm = new FriendForm();
		friendForm.setName(Chinese.toChinese(request.getParameter("name")));
		friendForm.setQQNumber(request.getParameter("QQNumber"));
		friendForm.setDescription(Chinese.toChinese(request
				.getParameter("description")));
		String result = "��Ӻ���ʧ�ܣ�";
		if (friendDao.addFriend(friendForm)) {
			result = "��Ӻ��ѳɹ���";
		}
		request.setAttribute("result", result);

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("back_FriendAdd.jsp");
		requestDispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
