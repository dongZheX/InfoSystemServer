package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.sg.prism.NGWebView;
import com.sun.org.apache.bcel.internal.generic.Select;

import DataBase.DBManager;

/**
 * Servlet implementation class getMyClassInfo
 */
@WebServlet("/getMyClassInfo")
public class getMyClassInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getMyClassInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter;
		BufferedReader bufferedReader;
		InputStream in;
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		String class_id = bufferedReader.readLine();
		//数据库连接
		DBManager db = DBManager.createInstance();
		db.initDB();
		db.connectDB("Super", "1097300052dz");
		String sql = "select from class where Class_id = '"+class_id+"';";
		ResultSet resultSet = db.executeQuery(sql);
		printWriter = response.getWriter();
		//数据读取
		try {
			String class_name = resultSet.getString("Class_name");
			int class_count = resultSet.getInt("Class_count");
			String result = class_name+"/"+class_count;
			printWriter.write(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter.write("0");
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
