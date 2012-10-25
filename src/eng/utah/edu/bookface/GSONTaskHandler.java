package eng.utah.edu.bookface;

import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

public class GSONTaskHandler {

	protected final String charset = "utf-8";
	protected final String studentAddress = "http://android.eng.utah.edu/Students/";
	protected final String postAddress = "http://android.eng.utah.edu/Posts/";
	protected final String commentAddress = "http://android.eng.utah.edu/Comments/";

	protected Gson gson = new Gson();
	
	public Students doLogin(String CadeLogin, int MagicNumber) {
		Students student;

		String address = studentAddress + "Login";

		try {
			String query = String.format("CadeLogin=%s&MagicNumber=%s",
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset));

			URL url = new URL(address + "?" + query);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			student = gson.fromJson(arr, Students.class);
			
			return student;
			
		} catch (Exception e) {
			student = new Students(false, e.getMessage());
			return student;
		}

	}

	public Students[] getStudentsList() {
		Students[] students;

		String address = studentAddress + "List";

		try {

			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			students = gson.fromJson(arr, Students[].class);
			
			return students;
			
		} catch (Exception e) {
			return null;
		}
	}

	public Students getStudent(int studentID) {
		Students student;

		String address = studentAddress + "Get";

		try {
			String query = String.format("studentID=%s",
					URLEncoder.encode(Integer.toString(studentID), charset));
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			student = gson.fromJson(arr, Students.class);
			
			return student;
			
		} catch (Exception e) {
			student = new Students(false, e.getMessage());
			return student;
		}
	}

	public Students editStudent(int studentID, String CadeLogin, int MagicNumber,
			String FirstName, String LastName) {
		Students student;

		String address = studentAddress + "Edit";

		try {
			String query = String.format("StudentID=%s&CadeLogin=%s&MagicNumber=%s&FirstName=%s&LastName=%s",
					URLEncoder.encode(Integer.toString(studentID), charset),
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset),
					URLEncoder.encode(FirstName, charset),
					URLEncoder.encode(LastName, charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			student = gson.fromJson(arr, Students.class);
			
			return student;
			
		} catch (Exception e) {
			student = new Students(false, e.getMessage());
			return student;
		}
	}

	public Posts getPostsList(Integer skip, Integer take) {
		Posts post;

		String address = postAddress + "List";

		try {
			String query = String.format("skip=%s&take=%s",
					URLEncoder.encode(Integer.toString(skip), charset),
					URLEncoder.encode(Integer.toString(take), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			post = gson.fromJson(arr, Posts.class);
			
			return post;
			
		} catch (Exception e) {
			post = new Posts(false, e.getMessage());
			return post;
		}
	}

	public Posts getPost(int postID) {
		Posts post;

		String address = postAddress + "Get";

		try {
			String query = String.format("postID=%s",
					URLEncoder.encode(Integer.toString(postID), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			post = gson.fromJson(arr, Posts.class);
			
			return post;
			
		} catch (Exception e) {
			post = new Posts(false, e.getMessage());
			return post;
		}
	}

	public Posts doCreatePost(String CadeLogin, int MagicNumber, String Text) {
		Posts post;

		String address = postAddress + "Create";

		try {
			String query = String.format("CadeLogin=%s&MagicNumber=%s&Text=%s",
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset),
					URLEncoder.encode(Text, charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			post = gson.fromJson(arr, Posts.class);
			
			return post;
			
		} catch (Exception e) {
			post = new Posts(false, e.getMessage());
			return post;
		}
	}

	public Posts doDeletePost(int postID, String CadeLogin, int MagicNumber) {
		Posts post;

		String address = postAddress + "Delete";

		try {
			String query = String.format("postID=%s&CadeLogin=%s&MagicNumber=%s",
					URLEncoder.encode(Integer.toString(postID), charset),
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			post = gson.fromJson(arr, Posts.class);
			
			return post;
			
		} catch (Exception e) {
			post = new Posts(false, e.getMessage());
			return post;
		}
	}
	
	public Posts doEditPost(int postID, String text, String CadeLogin,
			int MagicNumber) {
		Posts post;

		String address = postAddress + "Edit";

		try {
			String query = String.format("postID=%s&text=%s&CadeLogin=%s&MagicNumber=%s",
					URLEncoder.encode(Integer.toString(postID), charset),
					URLEncoder.encode(text, charset),
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			post = gson.fromJson(arr, Posts.class);
			
			return post;
			
		} catch (Exception e) {
			post = new Posts(false, e.getMessage());
			return post;
		}
	}
	
	public Comments getCommentsList(int postID, int skip, int take) {
		Comments comment;

		String address = commentAddress + "List";

		try {
			String query = String.format("postID=%s&skip=%s&take=%s",
					URLEncoder.encode(Integer.toString(postID), charset),
					URLEncoder.encode(Integer.toString(skip), charset),
					URLEncoder.encode(Integer.toString(take), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			comment = gson.fromJson(arr, Comments.class);
			
			return comment;
			
		} catch (Exception e) {
			comment = new Comments(false, e.getMessage());
			return comment;
		}

	}

	public Comments doCreateComment(int postID, String text, String CadeLogin,
			int MagicNumber) {
		Comments comment;

		String address = commentAddress + "Create";

		try {
			String query = String.format("postID=%s&text=%s&CadeLogin=%s&MagicNumber=%s",
					URLEncoder.encode(Integer.toString(postID), charset),
					URLEncoder.encode(text, charset),
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			comment = gson.fromJson(arr, Comments.class);
			
			return comment;
			
		} catch (Exception e) {
			comment = new Comments(false, e.getMessage());
			return comment;
		}
	}

	public Comments doEditComment(int commentID, String text, String CadeLogin,
			int MagicNumber) {
		Comments comment;

		String address = commentAddress + "Edit";

		try {
			String query = String.format("commentID=%s&text=%s&CadeLogin=%s&MagicNumber=%s",
					URLEncoder.encode(Integer.toString(commentID), charset),
					URLEncoder.encode(text, charset),
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			comment = gson.fromJson(arr, Comments.class);
			
			return comment;
			
		} catch (Exception e) {
			comment = new Comments(false, e.getMessage());
			return comment;
		}

	}

	public Comments doDeleteComment(int commentID, String CadeLogin, int MagicNumber) {
		Comments comment;

		String address = commentAddress + "Delete";

		try {
			String query = String.format("commentID=%s&text=%s&CadeLogin=%s&MagicNumber=%s",
					URLEncoder.encode(Integer.toString(commentID), charset),
					URLEncoder.encode(CadeLogin, charset),
					URLEncoder.encode(Integer.toString(MagicNumber), charset));
			
			URL url = new URL(address + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			String s = new Scanner(is).useDelimiter("\\A").next();
			String json = gson.toJson(s);
			JsonParser jp = new JsonParser();
			JsonArray arr = jp.parse(json).getAsJsonArray();
			comment = gson.fromJson(arr, Comments.class);
			
			return comment;
			
		} catch (Exception e) {
			comment = new Comments(false, e.getMessage());
			return comment;
		}
	}





}
