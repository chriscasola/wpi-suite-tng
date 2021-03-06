<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WPI Suite Admin Console</title>
<%!
//the first index of the fields array must be the unique
//identifier for this object
String coreusertitle = "CoreUser";
String coreuserpath = "core/user";
String[] coreuser = {
		"username",
		"name",
		"password",
		"id"
};
int coreuserlength = 4;

String coreprojecttitle = "CoreProject";
String coreprojectpath = "core/project";
String[] coreproject = {
		"idNum",
		"name"
};
int coreprojectlength = 2;

public String createModelField(String title, String path, String[] args, int length)
{
	String text = "<h3>"+title+"</h3>";
	for(int i = 0; i < length; i++)
	{
		text = text + "<label>" + args[i] +": </label><input id="+args[i]+title+" maxlength=\'40\' /><br />\n";
	}
	text += "<button onclick=\""+title+"('create')\">create "+title+"</button>\n";
	text += "<button onclick=\""+title+"('read')\">read "+title+"</button>\n";
	text += "<button onclick=\""+title+"('update')\">update "+title+"</button>\n";
	text += "<button onclick=\""+title+"('delete')\">delete "+title+"</button><br />\n";
	text += "<textarea id="+title+"textArea rows=\"5\" cols=\"40\"></textarea>\n";
	return text + "<br />";
}
public String createModelScript(String title, String path, String[] args, int length)
{
	String scriptText = "<script type=\"text/javascript\" >\n";
	scriptText += "function "+title+"(action){\n";
	scriptText += "send = new Object();\n";
	for(int i = 0; i < length; i++)
	{
		scriptText += "send."+args[i]+"= document.getElementById(\""+args[i]+title+"\").value\n";
	}
	scriptText += "jsend = JSON.stringify(send);\n";
	scriptText += "var request = new XMLHttpRequest();\n" +
			"request.onreadystatechange = function() {\n" +
				"if(request.readyState == 4 && request.status > 0) {\n" +
					"document.getElementById(\""+title+"textArea\").value = request.responseText;\n" +
				"}\n" +
			"};\n";
	scriptText += "if(action == 'create'){ \n"+
				"request.open('PUT', \"API/"+path+"/\", true)\n"+
		    	"request.send(jsend);\n" +
			"}else if(action == 'read'){ \n" +
				"request.open('GET', \"API/"+path+"/\"+send."+args[0]+", true)\n"+
		    	"request.send();\n" +
			"}else if(action == 'update'){ \n" +
				"request.open('POST', \"API/"+path+"/\"+send."+args[0]+", true)\n"+
		    	"request.send(jsend);\n" +
			"}else if(action == 'delete'){ \n" +
				"request.open('DELETE', \"API/"+path+"/\"+send."+args[0]+", true)\n"+
		    	"request.send();\n" +
			"}}\n";
			
	scriptText += "</script>";	
	return scriptText;
}
%>
<%= createModelScript(coreusertitle, coreuserpath, coreuser, coreuserlength) %>
<%= createModelScript(coreprojecttitle, coreprojectpath, coreproject, coreprojectlength) %>
</head>
<body>
<h1>WPI Suite Admin Console</h1>


<%= createModelField(coreusertitle, coreuserpath, coreuser, coreuserlength) %>
<%= createModelField(coreprojecttitle, coreprojectpath, coreproject, coreprojectlength) %>
</body>
</html>