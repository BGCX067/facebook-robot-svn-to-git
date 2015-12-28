<%@ page import="controller.*" %>
<%@ page import="bean.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList;" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/reset.css" rel="stylesheet">		
		<link href="css/demo_page.css" rel="stylesheet">
		<link href="css/demo_table.css" rel="stylesheet">
		<link href="css/stylesheet.css" rel="stylesheet">
		<script src="js/jquery-1.8.3.js"></script>
		<script src="js/jquery.dataTables.min.js"></script>	
		<title>Daniel's Facebook Robot</title>
	</head>
	<body>
		<img id="loadingImage" src="images/loading.gif">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#loadingImage').fadeOut(500);
				$('#error').fadeIn(1000);
				$('#container').fadeIn(1000);
	    		$('.tb_comments').dataTable();		
			} );		
		</script>
	 	<div id="page_header">
	 			<span>Daniel's Facebook Robot </span>
	 			<a href="http://www.facebook.com/naturanet"><img src="images/natura_logo.png" align="middle"></a>
 		</div>
 		<% 
			out.flush();
			DatabaseData dbData = new DatabaseData();
			ArrayList<Post> array = null;
			try{
				dbData.eraseDatabase();
				FacebookData.startOperations();
		 		array = dbData.getAllPostsAndComments();
			}catch(Exception ex){
				String error = "<div id='error'>"+ "ERROR: Please, contact the service provider. "+"</div>";
				out.print(error);
				out.close();
			}		
	 	%>
	 	<div id="container">
	 			<%
	 				int i=1;
	 				for(Post post : array){
	 			 %>
	 			<div class="post">
	 				<div class="post_metadata">
	 					<span class="label">POST ID:</span><%=post.getId() %>
	 					<%
	 						SimpleDateFormat sdfPost = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm a");
	 						SimpleDateFormat sdfComment = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	 					 %>
	 					<span class="label">CREATED TIME:</span><%=sdfPost.format(post.getDate()) %>
	 					<span class="label id">#<%=i %></span>
	 				</div>
	 				<div class="post_data">
	 					<div class="parameter"><span class="label">MESSAGE: </span><%=post.getMessage() %></div>
	 					<div class="parameter"><span class="label">LINK:</span>
	 										<%
	 											if(post.getLink()!=null){
	 								 		%>
	 												<a href="<%=post.getLink() %>"><%=post.getLink() %></a>
	 										<%
	 											}else{
	 												out.print(post.getLink());
	 											}
	 								 		%>
	 								</div>
						<div class="parameter"><span class="label label_comments">COMMENTS:</span>
									<table class="tb_comments display">
										<thead>
											<tr>
											<th>ID</th>
											<th>USER ID</th>
											<th>USERNAME</th>
											<th>MESSAGE</th>
											<th>DATE</th>											
											</tr>
										</thead>
										<tbody>
											<%
											for(Comment comment : post.getComments()){
										 %>
										 		<tr>
										 			<td><%=comment.getId() %></td>
										 			<td><%=comment.getUser_id() %></td>
										 			<td><%=comment.getUsername() %></td>
										 			<td><%=comment.getMessage() %></td>
										 			<td><%=sdfComment.format(comment.getDate()) %></td>
										 		</tr>
										 <%
										 	}
										  %>
										</tbody>
									</table>
									</div>
	 				</div>
	 			</div>
	 			<%
	 					i++;
	 				}
	 				
	 			 %>
	 		</div>
	</body>
</html>