<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="utils.Utils"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.js" integrity="sha512-Lii3WMtgA0C0qmmkdCpsG0Gjr6M0ajRyQRQSbTF6BsrVh/nhZdHpVZ76iMIPvQwz1eoXC3DmAg9K51qT5/dEVg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/list.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Người cách ly </span>
        <div class="spacer2"></div>
    </div>
    <div class="report">
        <div class="report__table">
            <div style="height: 600px;width: 1000px">
                <canvas id="myChart"></canvas>
                <script>
                    <%
                        List<Integer> data = (List<Integer>) request.getAttribute("susList");
                    %>
                    const ctx = document.getElementById('myChart');
                    const myChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: ['F0', 'F1', 'F2', 'F3', 'F4'],
                            datasets: [{
                                    label: 'Số lượng người cách ly theo loại',
                                    data: ['<%=data.get(0)%>', '<%=data.get(1)%>', '<%=data.get(2)%>', '<%=data.get(3)%>', '<%=data.get(4)%>'],
                                    backgroundColor: [
                                        'rgb(75, 192, 192)'
                                    ],
                                    borderColor: [
                                        'rgba(255, 99, 132, 1)',
                                        'rgba(54, 162, 235, 1)',
                                        'rgba(255, 206, 86, 1)',
                                        'rgba(75, 192, 192, 1)',
                                        'rgba(153, 102, 255, 1)',
                                        'rgba(255, 159, 64, 1)'
                                    ],
                                    borderWidth: 1
                                }],
                            fill: false,
                            tension: 0.1
                        },
                        options: {
                            responsive: true,
                            maintainAspectRatio: false,
                            scales: {
                                y: {
                                    max: 15,
                                    min: 0,
                                    ticks: {
                                        stepSize: 1
                                    }
                                }
                            }
                        }
                    });
                </script>
            </div>
        </div>
    </div>
    <div class="btn-add">
        <a href="<%=request.getContextPath()%>/Patient/add.jsp"><i class="fas fa-plus-circle"></i>
            <span>Thêm</span>
        </a>
    </div>
    <div class="table-list">
        <table class="table-list__user">
            <tr>
                <th width="5%">Mã Bệnh Nhân</th>
                <th width="17%">Họ tên</th>
                <th width="5%">Tuổi</th>
                <th width="5%">Phòng</th>
                <th width="5%">Khu cách ly</th>
                <th width="10%">Quốc tịch</th>
                <th width="15%">Tình trạng</th>
                <th width="19%">Mức độ nghi nhiễm</th>
                <th width="19%">Hành động</th>
            </tr>
            <c:forEach var="patient" items="${list}">
                <tr>
                    <td>${patient.getPatientId()}</td>
                    <td>${patient.getPatientName()}</td>
                    <td>${patient.getAge()}</td>
                    <td>${patient.getRoom().getRoomName()}</td>
                    <td>${patient.getArea().getAreaId()}</td>
                    <td>${patient.getRegion()}</td>
                    <c:if test="${empty patient.getTimeOut()}"> <td>${Utils.getDistanceTime(patient.getTimeIn(), patient.getTimeOut()) + 1} ngày</td>
                    </c:if>
                    <c:if test="${not empty patient.getTimeOut()}"><td style="color: red; font-weight: 500;">Đã ra</td></c:if>
                    <td data-filetype="${patient.getSuspicionLevel()}">${patient.getSuspicionLevel()}</td>
                    <td class="td-action">
                        <a href="<%=request.getContextPath()%>/Patient/view-detail?id=${patient.getPatientId()}"><i class="far fa-calendar-alt"></i></a>
                        <a href="<%=request.getContextPath()%>/Patient/update.jsp?id=${patient.getPatientId()}"><i class="fas fa-pen"></i></a>
                        <a href="<%=request.getContextPath()%>/Patient/delete?id=${patient.getPatientId()}" style="background-color: red"><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
            </c:forEach>
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
                <a href="<%=request.getContextPath()%>/Patient/view-list?page=${currentPage - 1}"> < </a>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a href="">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<%=request.getContextPath()%>/Patient/view-list?page=${i}"> ${i} </a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test = "${currentPage lt noOfPages}">
                <a href="<%=request.getContextPath()%>/Patient/view-list?page=${currentPage+1}"> > </a>
            </c:if>
        </div>
    </div>
</div>

<script>
    $('td[data-filetype="F1"]').css({'color': 'red', 'font-weight': '500'});
    $('td[data-filetype="F2"]').css({'color': '#FFC806', 'font-weight': '500'});
    $('td[data-filetype="F3"]').css({'color': 'blue', 'font-weight': '500'});
    $('td[data-filetype="F4"]').css({'color': 'black', 'font-weight': '500'});
</script>
