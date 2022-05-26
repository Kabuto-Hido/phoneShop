<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN PAGE</title>
<!-- Bootstrap CSS -->
  <link href="admin/css/bootstrap.min.css" rel="stylesheet">
  <link rel="icon" href="../images/fevicon/fevicon.png" type="image/gif" />
  <!-- bootstrap theme -->
  <link href="admin/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- full calendar css-->
  <link href="admin/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
  <link href="admin/assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
  <!-- easy pie chart-->
  <link href="admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen" />
  <!-- owl carousel -->
  <link rel="stylesheet" href="admin/css/owl.carousel.css" type="text/css">
  <link href="admin/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
  <!-- font icon -->
  <link href="admin/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="admin/css/font-awesome.min.css" rel="stylesheet" />
  <!-- Custom styles -->
  <link rel="stylesheet" href="admin/css/fullcalendar.css">
  <link href="admin/css/widgets.css" rel="stylesheet">
  <link href="admin/css/style.css" rel="stylesheet">
  <link href="admin/css/style-responsive.css" rel="stylesheet" />
  <link href="admin/css/xcharts.min.css" rel=" stylesheet">
  <link href="admin/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
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
            <h3 class="page-header"><i class="fa fa fa-bars"></i>Orders</h3>
            <div style="padding:5px; color:red;font-style:italic;">
		       ${Message}
		    </div>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="manageorder?action=show">Home</a></li>
              <li style="color: #688a7e; margin-bottom:50px">Orders</li>
            </ol>
          </div>
        </div>
        
        <!-- page start-->
        	<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">USERNAME</th>
                              <th scope="col">FULL NAME</th>
			      <th scope="col">EMAIL</th>
			      <th scope="col">ADDRESS</th>
			      <th scope="col">TOTAL PRICE</th>
			      <th scope="col">CREATE DATE</th>
			      <th scope="col">STATUS</th>
			      <th scope="col">VOUCHER</th>
                              <th scope="col">SHIP CODE</th>
                              <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
			    <c:forEach var ="item" items="${listOrders}">
                                    <tr>
				      <th scope="row" style="width: 40px">${item.id}</th>
				      <td style="width: 120px">${item.userId.getUsername()}</td>
				      <td style="width: 140px">${item.userId.getName()}</td>
				      <td style="width: 120px">${item.userId.getEmail()}</td>
				      <td style="width: 160px">${item.userId.getAddress()}</td>
				      <td style="width: 90px">${item.getTotalPriceCurrencyFormat()}</td>
				      <td style="width: 120px">${item.createDate}</td>
				      <td style="width: 80px">${item.status}</td>
                                      <c:choose>
                                            <c:when test="${item.voucher == 1}">
                                                <td style="width: 60px">0%</td>
                                            </c:when>
                                            <c:when test="${item.voucher != 1}">
                                                <td style="width: 60px">10%</td>                                                
                                            </c:when>
                                        </c:choose>
				      <td style="width: 100px">${item.shipCode}</td>
                                      <td>
                                        <c:choose>
                                            <c:when test="${item.status == 'Da thanh toan'}">
                                                <form method="post" action="ManageOrderDetail">
                                                    <input type="hidden" name="orderId" value="${item.id}">
                                                    <input value="Detail" type="submit" style="color: #fff; text-align: center; border: none;
                                                        background: #039ee3;border-radius: 5px; height: 38px; min-width:
                                                        70px; padding: 0 15px; margin-left: 5px">
                                                </form>
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${item.status == 'Chua thanh toan'}">
                                                <form method="post" action="manageorder">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="orderId" value="${item.id}">
                                                    <input value="Delete" type="submit" style="color: #fff; text-align: center; border: none;
                                                        background: #039ee3;border-radius: 5px; height: 38px; min-width:
                                                        70px; padding: 0 15px; margin-left: 5px; margin-top: 5px">
                                                </form>
                                            </c:when>
                                        </c:choose>
                                        
                                      </td>
				    </tr>
				</c:forEach>
			  </tbody>
			</table>
        <!-- page end-->
        
      </section>
    </section>
    <!--main content end-->
    <div class="text-right">
      
    </div>
  </section>
  <!-- javascripts -->
  <script src="admin/js/jquery.js"></script>
  <script src="admin/js/jquery-ui-1.10.4.min.js"></script>
  <script src="admin/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="admin/js/jquery-ui-1.9.2.custom.min.js"></script>
  <!-- bootstrap -->
  <script src="admin/js/bootstrap.min.js"></script>
  <!-- nice scroll -->
  <script src="admin/js/jquery.scrollTo.min.js"></script>
  <script src="admin/js/jquery.nicescroll.js" type="text/javascript"></script>
  <!-- charts scripts -->
  <script src="admin/assets/jquery-knob/js/jquery.knob.js"></script>
  <script src="admin/s/jquery.sparkline.js" type="text/javascript"></script>
  <script src="admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
  <script src="admin/js/owl.carousel.js"></script>
  <!-- jQuery full calendar -->
  <script src="admin/js/fullcalendar.min.js"></script>
    <!-- Full Google Calendar - Calendar -->
    <script src="admin/ssets/fullcalendar/fullcalendar/fullcalendar.js"></script>
    <!--script for this page only-->
    <script src="admin/js/calendar-custom.js"></script>
    <script src="admin/js/jquery.rateit.min.js"></script>
    <!-- custom select -->
    <script src="admin/js/jquery.customSelect.min.js"></script>
    <script src="admin/assets/chart-master/Chart.js"></script>

    <!--custome script for all page-->
    <script src="admin/js/scripts.js"></script>
    <!-- custom script for this page-->
    <script src="admin/js/sparkline-chart.js"></script>
    <script src="admin/js/easy-pie-chart.js"></script>
    <script src="admin/js/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="admin/js/jquery-jvectormap-world-mill-en.js"></script>
    <script src="admin/js/xcharts.min.js"></script>
    <script src="admin/js/jquery.autosize.min.js"></script>
    <script src="admin/js/jquery.placeholder.min.js"></script>
    <script src="admin/js/gdp-data.js"></script>
    <script src="admin/js/morris.min.js"></script>
    <script src="admin/js/sparklines.js"></script>
    <script src="admin/js/charts.js"></script>
    <script src="admin/js/jquery.slimscroll.min.js"></script>
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