<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action = "${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
  <label for="categoryname">Category name:</label><br>
  <input type="text" id="categoryname" name="categoryname"><br>
  <label for="imagesfile">Images File:</label><br>
  <input type="file" id="imagesfile" name="imagesfile"><br>
  <label for="imageslink">Images Link:</label><br>
  <input type="text" id="imageslink" name="imageslink"><br>
<label for="html">Status</label><br>
<input type="radio" id="ston" name="status" value="1">
<label for="css">Hoạt động</label><br>
<input type="radio" id="stoff" name="status" value="0">
<label for="javascript">Khóa</label>
<br><br>
<input type="submit" value="Insert">
</form>