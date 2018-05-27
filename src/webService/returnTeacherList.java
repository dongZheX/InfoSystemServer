package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import DataBase.DBManager;
import json.test.GsonUnit;

/**
 * Servlet implementation class returnTeacherList
 */
@WebServlet("/returnTeacherList")
public class returnTeacherList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnTeacherList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("gbk");
	    BufferedReader bufferedReader;
	    PrintWriter printWriter;
	    InputStream in = request.getInputStream();
	    bufferedReader = new BufferedReader(new InputStreamReader(in));
	    String class_id = bufferedReader.readLine();
	    List<Teacher> mList = new ArrayList<>();
	    //数据库连接
	    DBManager db = DBManager.createInstance();
	    db.initDB();
	    db.connectDB("Super", "1097300052dz");
	    System.out.println(class_id);
       String sql = "select * from teacher where Class_id = '" +class_id+"';" ;
	    
	    printWriter = response.getWriter();
	    try {
	
			ResultSet resultSet = db.executeQuery(sql);
			while(resultSet.next()) {
				Teacher teacher;
				String name = resultSet.getString("Teacher_name");
				String course = resultSet.getString("Teach_course");
				String phone = resultSet.getString("teacher_phone");
				teacher = new Teacher(name, course, phone, class_id);
				mList.add(teacher);
			}
			
			String data = GsonUnit.listToJson(mList);
			System.out.println(data);
			printWriter.write(data);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter.write("");
		}
	    in.close();
	    printWriter.close();
	    bufferedReader.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
