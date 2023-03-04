<%@page import="dao.PatientDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/list.css">

<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Theo dõi báo cáo sức khỏe bệnh nhân</span>
        <div class="spacer2"></div>
    </div>

    <!--Filter-->
    <!--
    <div class="table-list">
        <form action="list" method="POST" style="font-size: initial; margin-block: 40px;font-weight: 700; margin-top: 10px">
            <label>Tên bệnh nhân</label>
            <input style="margin-left: 100px;font-weight: 100;" type="text" name="fname" value="${fname}" />
            <br>
            <label>Sắp xếp theo thời gian</label>
            
            <br>
            <label>Từ ngày: 
                <div id="datepicker" class="input-group date" data-date-format="dd-mm-yyyy"> 
                    <input class="form-control" readonly="" type="text" name="ffrom" value="${ffrom}"> 
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-calendar"></i>
                    </span> 
                </div>
            </label>
            <label>Đến ngày: 
                <div id="datepicker" class="input-group date" data-date-format="dd-mm-yyyy"> 
                    <input class="form-control" readonly="" type="text" name="fto" value="${fto}"> 
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-calendar"></i>
                    </span> 
                </div>
            </label>
            <br>
            <input style="margin-left: 100px;" type="radio" name="fsort" value="ASC" ${fsort == "ASC" ? "checked" : ""}/>Cũ nhất 
            <input style="margin-left: 100px;" type="radio" name="fsort" value="DESC" ${fsort == "DESC" ? "checked" : ""}/>Mới nhất
            <br><br>
            <input style="margin-left: 100px;" type="submit" value="Lọc danh sách" name="submit" />
            <br>
        </form>
    </div>
    -->
    <div style="margin: 50px 350px 50px 50px;">
    <h2>Lọc danh sách</h2>
    <p style="font-size: 1.6rem;">Nhập bất kỳ thông tin liên quan trong bảng:</p>  
    <input style="font-size: 1.6rem;" class="form-control" id="myInput" type="text" placeholder="Search..">
    </div>
    
    <div class="table-list">
        <table class="table-list__user">
            <tr>
                <th width="10%">Mã bệnh nhân</th>
                <th width="15%">Tên bệnh nhân</th>
                <th width="40%">Báo cáo sức khỏe</th>  
                <th width="20">Thời gian</th>  
                <th width="15%">Hành động</th>
            </tr>
            <tbody id="myTable">
                <tr></tr>
            <c:forEach var="report" items="${listReport}">
                <tr>
                    <td>${report.getPatient().getPatientId()}</td>
                    <td>${report.getPatient().getPatientName()}</td>
                    <td>${report.getContent()}</td>
                    <td>${report.getCreateDate()}</td>
                    <td class="td-action">
                        <a href="<%=request.getContextPath()%>/Report/viewDetail?roomId=${room.getRoomId()}"><i class="far fa-calendar-alt"></i></a>
                        <a href="<%=request.getContextPath()%>/Report/update?roomId=${room.getRoomId()}"><i class="fas fa-pen"></i></a>
                        <a href="<%=request.getContextPath()%>/Report/delete?roomId=${room.getRoomId()}" style="background-color: red"><i class="fas fa-trash-alt"></i></a>
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
<!--
<style>label{
        margin-left: 20px;
    }
    #datepicker{
        width:180px; 
        margin: 0 20px 20px 20px;
    }
    #datepicker > span:hover{
        cursor: pointer;
    }</style>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="js/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
    $(function () {
        $("#datepicker").datepicker({
            autoclose: true,
            todayHighlight: true
        }).datepicker('update', new Date());
    });
</script>

<link rel="stylesheet prefetch" href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
-->