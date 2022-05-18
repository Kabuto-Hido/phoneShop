<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/favicon.png">
<title>ADMIN PAGE</title>
<!-- Bootstrap CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="css/elegant-icons-style.css" rel="stylesheet" />
  <link href="css/font-awesome.min.css" rel="stylesheet" />
  <!-- Custom styles -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet" />
</head>
<body>

	<!-- container section start -->
  <section id="container" class="">
    <!--header start-->
	<jsp:include page="header.jsp"></jsp:include>
    <!--header end-->

    <!--sidebar start-->
	<jsp:include page="sidebar.jsp"></jsp:include>
    <!--sidebar end-->

    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h4 class="page-header"><i class="fa fa fa-bars"></i> Pages</h4>
            <div style="padding:5px; color:red;font-style:italic;">
		       ${errorMessage}
		    </div>
          </div>
        </div>
        
        <!-- page start-->
        <form method="post" action="InsertForward" enctype="multipart/form-data">
		  <div class="form-group row">
		    <label for="staticEmail" class="col-sm-2 col-form-label">Tên sản phẩm</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="" name="ProductName">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label">Giá</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="" name="Price">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label">Kích thước màn hình</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="" name="ScreenSize">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label">Cân nặng</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="" name="Weight">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label">Xuất xứ</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="" name="Origin">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label">Ảnh</label>
		    <div class="col-sm-10">
		      <input type="file" class="form-control" id="" name="Image">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label">Mô tả</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="" name="Description">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label">Danh mục</label>
		    <div class="col-sm-10">
		      <select name="category">
				  <option value="1" selected>iPhone</option>
				  <option value="2">iPad</option>
				  <option value="3">iMac</option>
				  <option value="4">Macbook</option>
				  <option value="5">Watch</option>
				</select>
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary" value="Upload" >Thêm mới vào kho</button>
		</form>
        	
        <!-- page end-->
        
      </section>
    </section>
    <!--main content end-->
    <div class="text-right">
      
    </div>
  </section>
  <!-- javascripts -->
  <script src="js/jquery.js"></script>
  <script src="js/jquery-ui-1.10.4.min.js"></script>
  <script src="js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
  <!-- bootstrap -->
  <script src="js/bootstrap.min.js"></script>
  <!-- nice scroll -->
  <script src="js/jquery.scrollTo.min.js"></script>
  <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
  <!-- charts scripts -->
  <script src="assets/jquery-knob/js/jquery.knob.js"></script>
  <script src="js/jquery.sparkline.js" type="text/javascript"></script>
  <script src="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
  <script src="js/owl.carousel.js"></script>
  <!-- jQuery full calendar -->
  <<script src="js/fullcalendar.min.js"></script>
    <!-- Full Google Calendar - Calendar -->
    <script src="assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
    <!--script for this page only-->
    <script src="js/calendar-custom.js"></script>
    <script src="js/jquery.rateit.min.js"></script>
    <!-- custom select -->
    <script src="js/jquery.customSelect.min.js"></script>
    <script src="assets/chart-master/Chart.js"></script>

    <!--custome script for all page-->
    <script src="js/scripts.js"></script>
    <!-- custom script for this page-->
    <script src="js/sparkline-chart.js"></script>
    <script src="js/easy-pie-chart.js"></script>
    <script src="js/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="js/jquery-jvectormap-world-mill-en.js"></script>
    <script src="js/xcharts.min.js"></script>
    <script src="js/jquery.autosize.min.js"></script>
    <script src="js/jquery.placeholder.min.js"></script>
    <script src="js/gdp-data.js"></script>
    <script src="js/morris.min.js"></script>
    <script src="js/sparklines.js"></script>
    <script src="js/charts.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    <script>
      //knob
      $(function() {
        $(".knob").knob({
          'draw': function() {
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
        $("#owl-slider").owlCarousel({
          navigation: true,
          slideSpeed: 300,
          paginationSpeed: 400,
          singleItem: true

        });
      });

      //custom select box

      $(function() {
        $('select.styled').customSelect();
      });

      /* ---------- Map ---------- */
      $(function() {
        $('#map').vectorMap({
          map: 'world_mill_en',
          series: {
            regions: [{
              values: gdpData,
              scale: ['#000', '#000'],
              normalizeFunction: 'polynomial'
            }]
          },
          backgroundColor: '#eef3f7',
          onLabelShow: function(e, el, code) {
            el.jsp(el.jsp() + ' (GDP - ' + gdpData[code] + ')');
          }
        });
      });
    </script>

</body>
</html>