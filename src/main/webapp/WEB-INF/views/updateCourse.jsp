<%@page import="student.com.repository.CourseRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="util.common"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- Link to Font Awesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="<%=common.url%>assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="<%=common.url%>assets/css/style.css">
<link rel="shortcut icon" href="<%=common.url%>assets/images/favicon.ico" />

</head>
<body>

<div class="container-scroller">
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container-fluid page-body-wrapper">
<jsp:include page="sidebar.jsp"></jsp:include>

<!-- partial -->
        <div class="main-panel">
          <div class="content-wrapper">
            
            <div class="row">
              <div class="col-md-6 m-auto grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Update Course</h4>
                    
                    <form:form class="forms-sample" method="post" modelAttribute="courseDto">
                        
                      <div class="form-group row">
                        <label for="exampleInputUsername2" class="col-sm-3 col-form-label">Code</label>
                        <div class="col-sm-9">
                          <form:input class="form-control" id="exampleInputUsername2" path="courseId" value="${courseDto.courseId}" readonly="true"/>
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="exampleInputCoursename2" class="col-sm-3 col-form-label" >Name</label>
                        <div class="col-sm-9">
                          <form:input type="text" class="form-control" id="exampleInputUsername2" path="name" value="${courseDto.name}"/>
                          <form:errors path="name" cssClass="error" />
                        </div>
                      </div>
                     
                      <button type="submit" class="btn btn-primary me-2">Submit</button>
                    </form:form>
                  </div>
                </div>
              </div>
              
             </div>
          </div>
          <!-- content-wrapper ends -->
         <jsp:include page="footer.jsp"></jsp:include>
        </div>
        <!-- main-panel ends -->
         </div>
      <!-- page-body-wrapper ends -->
     </div>
    <!-- container-scroller -->
  <!-- plugins:js -->
    <script src="<%=common.url%>assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="<%=common.url%>assets/js/off-canvas.js"></script>
    <script src="<%=common.url%>assets/js/hoverable-collapse.js"></script>
    <script src="<%=common.url%>assets/js/misc.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="<%=common.url%>assets/js/file-upload.js"></script>
    <!-- End custom js for this page -->
</body>
</html>