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
</head>
<body id="default_theme" class="it_shop_list">
<!-- loader -->
<div class="bg_load"> <img class="loader_animation" src="images/loaders/loader_1.png" alt="#" /> </div>
<!-- end loader -->
<!-- header -->
<%@include   file="header.jsp" %> 
<!-- end header -->
<!-- inner page banner -->
<div id="inner_banner" class="section inner_banner_section">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="full">
          <div class="title-holder">
            <div class="title-holder-cell text-left">
              <h1 class="page-title">Shop Page</h1>
              <ol class="breadcrumb">
                <li><a href="Home">Home</a></li>
                <li class="active">Shop</li>
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
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <div class="row">
            <c:forEach var ="item" items="${listProduct}">
          <div class="col-md-4 col-sm-6 col-xs-12 margin_bottom_30_all">
            <div class="product_list">
                <div class="product_img"><a href="productdetail?pid=${item.id}"> <img class="img-responsive" src="<c:out value="${item.image}"></c:out>" alt="product"> </div>
              <div class="product_detail_btm">
                <div class="center">
                    <h4><a href="productdetail?pid=${item.id}"><c:out value="${item.name}"></c:out></a></h4>
                </div>
                <div class="starratin">
                  <div class="center"> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star-o" aria-hidden="true"></i> </div>
                </div>
                <div class="product_price">
                <c:choose>
                    <c:when test="${item.sale != 1}">
                        <p><span class="old_price"><c:out value="${item.getPriceCurrencyFormat()}"></c:out> </span> ? <span class="new_price"><c:out value="${item.getSalePriceFormat()}"></c:out></span></p>
                    </c:when>
                    <c:when test="${item.sale == 1}">
                        <p><span class="new_price"><c:out value="${item.getPriceCurrencyFormat()}"></c:out></span></p>
                    </c:when>           
                </c:choose>
                    
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
        </div>
      </div>
    <%@include file="right.jsp" %>
    </div>
  </div>
    <div class="container1"> 
  <div class="paging">
    <a href="#">&laquo;</a>
    <c:forEach var="i" begin="1" end="${pageNum}">
        <a class="text-dark ${indexPage==i?"active":""}" href="paging?index=${i}&brandid=${tag}">${i}</a>
    </c:forEach>
    <a href="#">&raquo;</a>
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

</body>
</html>
