<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="entity.Patient"%>
<%@page import="entity.Patient"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.js" integrity="sha512-Lii3WMtgA0C0qmmkdCpsG0Gjr6M0ajRyQRQSbTF6BsrVh/nhZdHpVZ76iMIPvQwz1eoXC3DmAg9K51qT5/dEVg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/base/home.css">
<div class="col-10 content">

    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Bảng điều khiển </span>
        <div class="spacer2"></div>
    </div>
    <div class="report">
        <div class="report__table">
            <label>Số lượng người cách ly mới</label>
            <div style="height: 600px;width: 1000px">
                <canvas id="myChart"></canvas>
                <script>
                    <%
                        int[] arr = (int[]) request.getAttribute("new");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                        String[] dates = (String[]) request.getAttribute("dates");
                        int newToday = arr[5];
                        Integer newWeek = (Integer) request.getAttribute("newWeek");
                    %>
                    const ctx = document.getElementById('myChart');
                    const myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ['<%=dates[0]%>', '<%=dates[1]%>', '<%=dates[2]%>', '<%=dates[3]%>', '<%=dates[4]%>', '<%=dates[5]%>'],
                            datasets: [{
                                    label: 'Số lượng người cách ly mới',
                                    data: [<%=arr[0]%>, <%=arr[1]%>, <%=arr[2]%>, <%=arr[3]%>, <%=arr[4]%>, <%=arr[5]%>],
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
                                    max: 10,
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
        <div class="report__info">
            <div class="report__info-class report__info-class__blue">
                <label class="report-label"><i class="fas fa-user"></i> Người cách ly</label>
                <ul class="report-label__info">
                    <li>Hôm nay <span><%=newToday%></span></li>
                    <li>Tuần này <span><%=newWeek%></span></li>
                </ul>
            </div>

            <div class="report__info-class report__info-class__orange">
                <label class="report-label"><i class="fas fa-bed"></i> Sức chứa</label>
                <ul class="report-label__info">
                    <li>Hiện tại <span>${requestScope.totalPatient}</span></li>
                    <li>Còn trống <span>${requestScope.availableBed}</span></li>
                    <li>Tổng <span>${requestScope.totalBed}</span></li>
                </ul>
            </div>
        </div>
    </div>
</div>

