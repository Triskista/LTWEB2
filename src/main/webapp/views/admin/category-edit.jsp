<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
    <!-- Category ID (hidden) -->
    <input type="text" id="categoryid" name="categoryid" value="${cate.categoryid }" hidden="hidden"><br>
    
    <!-- Category Name -->
    <label for="categoryname">Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname }"><br>

    <!-- Images File -->
    <label for="imagesfile">Images File:</label><br>
    <c:if test="${not empty cate.images and cate.images.length() >= 5 and cate.images.substring(0,5) != 'https'}">
        <c:url value="/upload/${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <c:if test="${not empty cate.images and cate.images.length() >= 5 and cate.images.substring(0,5) == 'https'}">
        <c:url value="${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <c:if test="${empty cate.images}">
        <c:set var="imgUrl" value="/path/to/default-image.jpg" />
    </c:if>
    <img height="150" width="200" src="${imgUrl}" /><br>
    <input type="file" id="imagesfile" name="imagesfile" value="${cate.images }"><br>

    <!-- Images Link -->
    <label for="imageslink">Images Link:</label><br>
    <input type="text" id="imageslink" name="imageslink"><br>

    <!-- Status -->
    <label for="status">Status:</label><br>
    <input type="text" id="status" name="status" value="${cate.status }"><br>
    
    <!-- Submit Button -->
    <br>
    <input type="submit" value="Submit">
</form>
