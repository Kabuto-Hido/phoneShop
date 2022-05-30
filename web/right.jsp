<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-3">
        <div class="side_bar">
          <div class="side_bar_blog">
            <h4>SEARCH</h4>
            <div class="side_bar_search">
                <form action="search" method="post">
                    <div class="input-group stylish-input-group">
                        <input name="txt" value="<c:out value="${txtsearch}"></c:out>" class="form-control" placeholder="Search" type="text">
                        <span class="input-group-addon">
                            <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                </span> </div>
                </form>
            </div>
          </div>
          <div class="side_bar_blog">
            <h4>CATEGORY</h4>
            <c:forEach var ="item" items="${listCategory}">
            <div class="categary">
              <ul>
                  <li class="list-group-item text-white ${tag == item.id ? "active":""}"><a href="Brand?bid=${item.id}"><i class="fa fa-angle-right"></i><c:out value="${item.name}"></c:out></a></li>
              </ul>
            </div>
            </c:forEach>
          </div>
          <div class="side_bar_blog">
          </div>
        </div>
      </div>
