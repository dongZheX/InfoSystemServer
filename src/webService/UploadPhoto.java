package webService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

import DataBase.DBManager;
import json.test.GsonUnit;
import things.PackPhoto;

/**
 * Servlet implementation class UploadPhoto
 */
@WebServlet("/UploadPhoto")
public class UploadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result;
		String imageName = "";
		Calendar calender = Calendar.getInstance();
		
		imageName = new SimpleDateFormat("yyyyMMdd").format(calender.getTime()).toString();
		response.setContentType("text/html;charset=utf-8");
		try {
			String jsonData = "";
			String line = "";
			int length;			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));		
			while((line=bufferedReader.readLine())!=null) {
				jsonData+=line;
			}
			System.out.println(jsonData);
			PackPhoto image = GsonUnit.jsonToJavaBean(jsonData, PackPhoto.class);						
			String dirpath = getServletContext().getRealPath("/")+"image"+imageName;
			String store = "image"+imageName+image.getUsername()+".png";
			String filepath = getServletContext().getRealPath("/")+"image"+imageName+"/"+store;
			String toDataBase = "/"+"image"+imageName+"/"+store;
			File dir = new File(dirpath);
			if(!dir.exists()) 
				dir.mkdirs();
			File file = new File(filepath);
			if(!file.exists())
				file.createNewFile();
			else {
				file.delete();
				file.createNewFile();
			}
		
			FileOutputStream fileOutputStream = new FileOutputStream(file,false);
			fileOutputStream.write(Base64.decode(image.getData()));
			String sql = "update userx set User_image = '"+toDataBase+"' where Username = '"+image.getUsername()+"';";
			DBManager dbManager = DBManager.createInstance();
			dbManager.connectDB("Super", "1097300052dz");
			dbManager.executeUpdate(sql);
			result = "success";
			dbManager.closeDB();
		}catch(Exception e) {
			result = "fault";
		}
		PrintWriter printWriter = response.getWriter();
		printWriter.write(result);
		printWriter.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
