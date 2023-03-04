
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/list.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Quản lý tài khoản </span>
        <div class="spacer2"></div>
    </div>
    <div class="btn-add">
        <a href="<%=request.getContextPath()%>/Accounts/add-nhansu.jsp"><i class="fas fa-plus-circle"></i>
            <span>Thêm nhân viên</span>
        </a>
    </div>
    <div class="table-list">
        <table class="table-list__user">
            <tr>
                <th width="10%">Mã</th>
                <th width="30%">Họ tên</th>
                <th width="20%">Địa chỉ</th>
                <th width="20%">Số điện thoại</th>
               
                <th width="20%">Hệ thống</th>
            </tr>
            <c:forEach var="nurse" items="${list}">
                <tr>
                    <td>${nurse.getId_nurse()}</td>
                    <td>${nurse.getFullName()}</td>
                    <td>${nurse.getAddress()}</td>
                    <td>${nurse.getPhone()}</td>
                    <td class="td-action">
                        <a href="<%=request.getContextPath()%>/Accounts/ViewDetailNurse?id=${nurse.getId_nurse()}"><i class="far fa-calendar-alt"></i></a>
                        <a href="<%=request.getContextPath()%>/Accounts/DeleteAccount?id=${nurse.getId_nurse()}" style="background-color: red"><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
            <div style="margin: 35px">
                 <nav aria-label="..." class="d-flex justify-content-center">
                                            <ul class="pagination pagination-lg">
                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                    <c:choose>
                                                        <c:when test="${pageIndex == i}">
                                                            <li class="page-item active" aria-current="page">
                                                                <span class="page-link">
                                                                    ${i}
                                                                    <span class="sr-only">(current)</span>
                                                                </span>
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            
                                                                <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/Accounts/view-list-nurse?pageIndex=${i}">${i}</a></li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                            </ul>
                                        </nav>
                
            </div>


</div>
