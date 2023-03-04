<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/list.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Quản lý feedback </span>
        <div class="spacer2"></div>
    </div>
    <div class="table-list">
        <table class="table-list__user">
            <tr>
                <th width="10%">Mã</th>
                <th width="30%">Nội Dung</th>
                <th width="20%">Ngày Tạo</th>
            </tr>
            <c:forEach var="report" items="${list}">
                <tr>
                    <td>${report.getId_report()}</td>
                    <td>${report.getContent()}</td>
                    <td>${report.getCreateDate()}</td>
                 
                </tr>
            </c:forEach>

        </table>
    </div>
    
</div>


</div>
