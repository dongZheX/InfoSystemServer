package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DBManager;

/**
 * Servlet implementation class removeInfo
 */
@WebServlet("/removeInfo")
public class removeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
	    BufferedReader bufferedReader;
	    PrintWriter printWriter;
	    InputStream in = request.getInputStream();
	    bufferedReader = new BufferedReader(new InputStreamReader(in));    
	    String info_id = bufferedReader.readLine();
	    //数据库连接
	    DBManager dbManager = DBManager.createInstance();
	    dbManager.initDB();
	    dbManager.connectDB("Super", "1097300052dz");
	    String sql ="delete from info where Info_id = '"+info_id+"'";
	    printWriter = response.getWriter();
	    try {
	    	dbManager.executeUpdate(sql);
	    	printWriter.write("1");
		} catch (Exception e) {
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
