package eng.utah.edu.bookface;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

public class Students {
	@SerializedName("CadeLogin")
	public String CadeLogin;
	
	@SerializedName("MagicNumber")
	public int MagicNumber;
	
	@SerializedName("Success")
	public Boolean Success;
	
	@SerializedName("Message")
	public String Message;
	
	@SerializedName("Name")
	public String Name;
	
	@SerializedName("FirstName")
	public String FirstName;
	
	@SerializedName("LastName")
	public String LastName;

	@SerializedName("StudentID")
	public int StudentID;
	
	/**
	 * Students constructor that represents a Boolean return for Login and Edit.
	 * @param Success
	 * @param Message
	 */
	public Students(Boolean Success, String Message)
	{
		this.Success = Success;
		this.Message = Message;
	}
	
	/**
	 * Constructor call for Students/Get
	 * @param StudentID
	 * @param FirstName
	 * @param LastName
	 * @param CadeLogin
	 */
	public Students(int StudentID, String FirstName, String LastName, String CadeLogin){
		this.StudentID = StudentID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.CadeLogin = CadeLogin;
		
	}
	
	/**
	 * Constructor for Students/List
	 * @param StudentID
	 * @param Name
	 * @param CadeLogin
	 */
	public Students(int StudentID, String Name, String CadeLogin)
	{
		this.StudentID = StudentID;
		this.Name = Name;
		this.CadeLogin = CadeLogin;
	}
	
}
