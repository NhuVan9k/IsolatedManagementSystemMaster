<%@page import="dao.PatientDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/list.css">

<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Báo cáo sức khỏe</span>
        <div class="spacer2"></div>
    </div>
    
  
    <div style="margin: 50px 350px 50px 50px;">
    <h2>Lọc danh sách</h2>
    <p style="font-size: 1.6rem;">Nhập bất kỳ thông tin liên quan trong bảng:</p>  
    <input style="font-size: 1.6rem;" class="form-control" id="myInput" type="text" placeholder="Search..">
    </div>
    
    
    <div class="btn-add">
        <a href="<%=request.getContextPath()%>/Report/add.jsp"><i class="fas fa-plus-circle"></i>
            <span>Thêm</span>
        </a>
    </div>

    <div class="table-list">
        <table class="table-list__user">
            <thead>
                <th width="15%">Mã report</th>
                <th width="40%">Nội dung báo cáo</th>
                <th width="20%">Thời gian</th>  
                <th width="25%">Hành động</th>
            </thead>
            <tbody id="myTable">
                <tr></tr>
            <c:forEach var="report" items="${listReport}">
                <tr>
                    <td>${report.getId_report()}</td>
                    <td>${report.getContent()}</td>
                    <td>${report.getCreateDate()}</td>
                    <td class="td-action">
                        <a href="<%=request.getContextPath()%>/Report/update?reportId=${report.getId_report()}"><i class="fas fa-pen"></i></a>
                        <a href="<%=request.getContextPath()%>/Report/delete?reportId=${report.getId_report()}" style="background-color: red"><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
            </c:forEach>
            
            </tbody>
        </table>
    </div>
    <div class="content-pad">
        <c:if test="${currentPage * 10 > noOfRecords}">
            <div class="content-pad__label">Showing ${(currentPage-1)*10+1} to ${noOfRecords} of ${noOfRecords} entries</div>
        </c:if>
        <c:if test="${currentPage * 10 <= noOfRecords}">
            <div class="content-pad__label">Showing ${(currentPage-1)*10+1} to ${currentPage * 10} of ${noOfRecords} entries</div>
        </c:if>

        <div class="content-pad__info">
            <c:if test="${currentPage != 1}">
                <a href="<%=request.getContextPath()%>/Report/list?page=${currentPage - 1}"> < </a>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a href="">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<%=request.getContextPath()%>/Report/list?page=${i}"> ${i} </a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test = "${currentPage lt noOfPages}">
                <a href="<%=request.getContextPath()%>/Report/list?page=${currentPage+1}"> > </a>
            </c:if>
        </div>
    </div>
</div>

<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
    });
  });
});
</script>