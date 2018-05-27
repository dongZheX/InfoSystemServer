package webService;
import things.Info;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DBManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import json.test.GsonUnit;

/**
 * Servlet implementation class RequestNotificationList
 */
@WebServlet("/RequestNotificationList")
public class RequestNotificationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestNotificationList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am In");
		response.setContentType("text/html;charset:GB2312");
		response.setCharacterEncoding("gbk");
		PrintWriter printWriter;
		BufferedReader bufferedReader;
		List<Info> infoList = new ArrayList<Info>();
		BufferedWriter bufferedWriter;
		String class_id;
		String jsonBack;
		InputStream in;
		OutputStream out;
		
		//读取客户端数据
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		class_id = bufferedReader.readLine();
		class_id = "2016758";
		//数据库请求数据
		DBManager dbManager = DBManager.createInstance();
		dbManager.initDB();
		dbManager.connectDB("Super", "1097300052dz");
		System.out.println(class_id);
		String sql = "select * from info where Class_id='"+class_id+"' order BY Time DESC;";
		ResultSet resultSet = dbManager.executeQuery(sql);
		printWriter = response.getWriter();
		try {
			while(resultSet.next()) {
				String Class_id = class_id;
				String info_id = resultSet.getString("info_id");
				String info_content = resultSet.getString("info_content");
				String info_title = resultSet.getString("Info_title");
				int look_num = resultSet.getInt("Looked_num");
				Timestamp timestamp = resultSet.getTimestamp("Time");
				Date date = timestamp;
				String dateString = new SimpleDateFormat("yyyy/MM/dd").format(date);
				String info_author = resultSet.getString("Info_author");
				Info info = new Info(Class_id,info_id,info_content,info_title,look_num,dateString,info_author);
				infoList.add(info);
			}
			jsonBack = GsonUnit.listToJson(infoList);
			System.out.println(jsonBack);
			printWriter.write(jsonBack);
			dbManager.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("出错");
			printWriter.write("");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
