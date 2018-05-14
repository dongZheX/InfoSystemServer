package json.test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


	public class GsonUnit{
		public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		    Gson gson = new Gson();
		    T result = gson.fromJson(jsonData, type);
		    return result;
		}
	    public static <T> String javabeanToJson(T t) {
	        Gson gson = new Gson();
	        String json = gson.toJson(t);
	        return json;
	    }

	    /**
	     * list to json
	     * 
	     * @param list
	     * @return
	     */
	    public static <T> String listToJson(List<T> list) {

	        Gson gson = new Gson();
	        String json = gson.toJson(list);
	        return json;
	    }

	    /**
	     * map to json
	     * 
	     * @param map
	     * @return
	     */
	    public static <T> String mapToJson(Map<String, T> map) {

	        Gson gson = new Gson();
	        String json = gson.toJson(map);
	        return json;
	    }
	    public static <T> T jsonToJavaBean(String json,Class<T> type) {
	        Gson gson = new Gson();
	        T temp = gson.fromJson(json,type);//����javabeanֱ�Ӹ���classʵ��
	        return temp;
	    }

	    /**
	     * json�ַ���תList����
	     * @param <t>
	     */

	    public static <T> List<T> jsonToList(String json,Class <T> t) {

	        Gson gson = new Gson();
	        Type listType = new ParameterizedTypeT(List.class, new Class[]{t});
	        // ����List<T>����������Result<List<T>>
	            //Type listType = new ParameterizedTypedT(List.class,new Class[]{t});
	        List<T> persons = gson.fromJson(json, listType);//���ڲ������������������������
	        return persons;
	    }

	    public static <T> Map<String,T>jsonToMap(String json) {
	        // TODO Auto-generated method stub
	        Gson gson = new Gson();
	        Map<String, T> maps = gson.fromJson(json, new TypeToken<Map<String, T>>() {
	        }.getType());
	        return maps;
	    }
	}

