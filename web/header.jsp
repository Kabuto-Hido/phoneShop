<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header id="default_header" class="header_style_1">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!-- header top -->
  <div class="header_top">
    <div class="container">
      <div class="row">
        <div class="col-md-8">
          <div class="full">
            <div class="topbar-left">
              <ul class="list-inline">
                <li> <span class="topbar-label"><i class="fa  fa-home"></i></span> <span class="topbar-hightlight">1, Vo Van Ngan</span> </li>
                <li> <span class="topbar-label"><i class="fa fa-envelope-o"></i></span> <span class="topbar-hightlight"><a href="mailto:badteam2021@gmail.com">badteam2021@gmail.com</a></span> </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="col-md-4 right_section_header_top">
          <div class="float-right">
              <ul class="list-inline">
                    <c:choose>
                        <c:when test="${session == null}">
                            <li><div class="make_appo"> <a class="btn white_btn" href="it_login.jsp">Login</a> </div></li>
                            <li><div class="make_appo"> <a class="btn blue_btn" href="it_signup.jsp">Sign up</a> </div></li>
                        </c:when>
                        <c:when test="${session != null}">
                            <li><a href="logout" style="color: #F24638;font-size:16px">Logout</a></li>
                            <c:choose>
                                <c:when test="${permission == 0}">
                                    <li><a href="#" style="font-size:14px"><b><c:out value="${username}"></c:out></b></a></li>
                                </c:when>
                                <c:when test="${permission == 1}">
                                    <li><a href="manageorder?action=show" style="font-size:14px"><b><c:out value="${username}"></c:out></b></a></li>
                                </c:when>
                            </c:choose>
                            <li><a href="Profile?action=show" style="font-size:14px"><b>Profile</b></a></li>
                        </c:when>
                    </c:choose>                    
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end header top -->
  <!-- header bottom -->
  <div class="header_bottom">
    <div class="container">
      <div class="row">
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
          <!-- logo start -->
          <div class="logo"> <a href="Home"><img src="images/logos/logo.jpg" alt="logo" /></a> </div>
          <!-- logo end -->
        </div>
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
          <!-- menu start -->
          <div class="menu_side">
            <div id="navbar_menu">
              <ul class="first-ul">
                <li> <a  href="Home">Home</a>
                </li>
                <li><a href="it_about.jsp">About Us</a></li>
                
                <li> <a href="it_blog.jsp">Blog</a>
                  
                </li>
                <li> <a href="Shop">Shop</a>
                  <ul>
                    <li><a href="Shop">Shop List</a></li>
                    <li><a href="cart?action=show">Shopping Cart</a></li>
                    <li><a href="order?action=show">Checkout</a></li>
                  </ul>
                </li>
                <li> <a href="it_contact.jsp">Contact</a>
                  
                </li>
              </ul>
            </div>
            <div class="search_icon">
              <ul>
                <li><a href="#" data-toggle="modal" data-target="#search_bar"><i class="fa fa-search" aria-hidden="true"></i></a></li>
              </ul>
            </div>
          </div>
          <!-- menu end -->
        </div>
      </div>
    </div>
  </div>
  <!-- header bottom end -->
</header>