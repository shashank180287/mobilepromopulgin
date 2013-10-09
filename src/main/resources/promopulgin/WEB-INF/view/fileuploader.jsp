<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Mobile Tool File Upload Page</title>
<script
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length - 1;
        $('#fileTable').append(
                '<tr><td>'+
                '   <input type="file" name="files['+ fileIndex +']" />'+
                '</td></tr>');
    });
     
});
</script>
</head>
<body>
<h1>Spring Multiple File Upload example</h1>
 
<form:form method="post" action="http://localhost:8080/plugin/upload"
        modelAttribute="uploadForm" enctype="multipart/form-data">
 
    <p>Select files to upload. Press Add button to add more file inputs.</p>
 
    <input id="addFile" type="button" value="Add File" />
    <table id="fileTable">
        <tr>
            <td>
            	<select name="uploadFileType">
  					<option value="csv">CSV</option>
  					<option value="tsv">TSV</option>
  					<option value="excel_xls">EXCEL 2003</option>
					<option value="excel_xlsx">EXCEL 2007</option>
				</select>
			</td>
			<td>
	           	<input type="hidden" name="moduleHandler" value="<%=request.getAttribute("moduleHandler")%>"/>
			</td>
        </tr>
        <tr>
            <td><input name="files[0]" type="file" /></td>
        </tr>
    </table>
    <br/><input type="submit" value="Upload" />
</form:form>
</body>
</html>