<!DOCTYPE html>
<html lang="en">
<head>
<!-- basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<!-- site metas -->
<title>B.A.D Shop</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<!-- site icons -->
<link rel="icon" href="images/fevicon/fevicon.png" type="image/gif" />
<link rel="stylesheet" href="css/paging.css" />
<!-- bootstrap css -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- Site css -->
<link rel="stylesheet" href="css/style.css" />
<!-- responsive css -->
<link rel="stylesheet" href="css/responsive.css" />
<!-- colors css -->
<link rel="stylesheet" href="css/colors1.css" />
<!-- custom css -->
<link rel="stylesheet" href="css/custom.css" />
<!-- wow Animation css -->
<link rel="stylesheet" href="css/animate.css" />
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">

<link rel="stylesheet" href="css/style_profile.css">
</head>
<body id="default_theme" class="it_service">
<!-- loader -->
<div class="bg_load"> <img class="loader_animation" src="images/loaders/loader_1.png" alt="#" /> </div>
<!-- end loader -->
<!-- header -->
<%@include   file="header.jsp" %> <!-- end header -->
<!-- inner page banner -->
<div id="inner_banner" class="section inner_banner_section">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="full">
          <div class="title-holder">
            <div class="title-holder-cell text-left">
              <h1 class="page-title">Profile Page</h1>
              <ol class="breadcrumb">
                <li><a href="Home">Home</a></li>
                <li class="active">Profile</li>
              </ol>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end inner page banner -->
<!-- section -->
<div class="section padding_layout_1 product_list_main">
    <font style="color:#F24638; margin-left: 150px"><c:out value="${msg}"></c:out></font>  
    <form method="post" action="Profile">
                <input type="hidden" name="action" value="update">    
  <div class="container">    
            <div class="row gutters">
            <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="account-settings">
                        <div class="user-profile">
                            <div class="user-avatar">
                                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Maxwell Admin">
                            </div>
                            <h5 class="user-name"><c:out value="${userInfo.name}"></c:out></h5>
                            <h6 class="user-username"><c:out value="${userInfo.username}"></c:out></h6>
                        </div>
<!--                        <div class="about">
                            <h5>About</h5>
                            <p>I'm Yuki. Full Stack Designer I enjoy creating user-centric, delightful and human experiences.</p>
                        </div>-->
                    </div>
                </div>
            </div>
            </div>
            
                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                <div class="card h-100">
                    <div class="card-body">
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h6 class="mb-2 text-primary">Personal Details</h6>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="fullName">Full Name</label>
                                    <input type="text" class="form-control" name="name" id="fullName" placeholder="Enter full name" value="<c:out value="${userInfo.username}"></c:out>">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="eMail">Email</label>
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Enter email ID" value="<c:out value="${userInfo.email}"></c:out>">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="text" class="form-control" name="phone" id="phone" placeholder="Enter phone number" value="<c:out value="${userInfo.phone}"></c:out>">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="website">Address</label>
                                    <input type="text" class="form-control" name="address" id="address" placeholder="Enter address" value="<c:out value="${userInfo.address}"></c:out>">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="phone">Password</label>
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Enter current password">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="website">Confirm Password</label>
                                    <input type="password" class="form-control" name="confirm_password" placeholder="Confirm password">
                                </div>
                            </div>
                        </div>

                        <div class="row gutters" style="margin-top: 10px;">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <button type="submit" id="submit" name="submit" class="btn btn-primary">Update</button>
                                    
                                </div>
                            </div>
                        </div>
                        </form>
                        <div class="row gutters" style="margin-top: 10px;">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <form method="post" action="Profile">
                                        <input type="hidden" name="action" value="cancel">
                                        <button type="submit" id="submit" name="submit" class="btn btn-secondary">Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>    
                </div>
                </div>
            </div>
            </div>
</div>
</div>
<!-- end section -->
<!-- footer -->
<%@include file="footer.jsp" %> 
<!-- end footer -->
<!-- js section -->
<script defer src="js/jquery.min.js"></script>
<script defer src="js/bootstrap.min.js"></script>
<!-- menu js -->
<script defer src="js/menumaker.js"></script>
<!-- wow animation -->
<script defer src="js/wow.js"></script>
<!-- custom js -->
<script defer src="js/custom.js"></script>
<!-- revolution js files -->
<script defer src="revolution/js/jquery.themepunch.tools.min.js"></script>
<script defer src="revolution/js/jquery.themepunch.revolution.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.actions.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.carousel.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.migration.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.navigation.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.parallax.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
<script defer src="revolution/js/extensions/revolution.extension.video.min.js"></script>
</body>
</html>
