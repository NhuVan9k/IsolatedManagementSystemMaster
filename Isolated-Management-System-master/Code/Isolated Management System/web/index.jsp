
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath()%>/assets/css/index.css" rel="stylesheet">
<div class="body-container">
    <div class="thumb" id="thumb">
        <!-- slide -->
        <div class="thumb__item">
            <img class="thumb-img" src="assets/img/thumb/1.jpg">
            <div class="thumb-message" >Hệ thống quản lý khu cách ly</div>
            <div class="thumb-message-info">Chung tay đẩy lùi Covid-19</div>
        </div>
        <div class="thumb__item">
            <img class="thumb-img" src="assets/img/thumb/3.jpg">
            <div class="thumb-message">Vì hạnh phúc của mọi người dân</div>
            <div class="thumb-message-info">Toàn dân đẩy lùi dịch bệnh</div>
        </div>
    </div>
    <div class="news">
        <div id="news-label">TIN MỚI</div>
        <div id="news-list">
            <div class="news-item">
                <img src="assets/img/thumb/2.jpg" />
                <div class="news-item__label">Thực hiện thông điêp 5K</div>
                <div class="news-item__time">Ngày 14, tháng 2, năm 2021</div>
                <div class="news-item__info">Bộ y tế khuyến nghị người dân thực hiện thông điệp 5K đầy...</div>        
            </div>
            <div class="news-item">
                <img src="assets/img/thumb/3.jpg" />
                <div class="news-item__label">Thực hiện thông điêp 5K</div>
                <div class="news-item__time">Ngày 14, tháng 2, năm 2021</div>
                <div class="news-item__info">Bộ y tế khuyến nghị người dân thực hiện thông điệp 5K đầy...</div>       
            </div>
            <div class="news-item">
                <img src="assets/img/thumb/4.png" />
               <div class="news-item__label">Thực hiện thông điêp 5K</div>
                <div class="news-item__time">Ngày 14, tháng 2, năm 2021</div>
                <div class="news-item__info">Bộ y tế khuyến nghị người dân thực hiện thông điệp 5K đầy...</div> ...</div>        
            </div>
        </div>
    </div>
</div>
<script>
    $("#thumb > div:gt(0)").hide();
    setInterval(function () {
        $('#thumb > div:first')
                .fadeOut(1000)
                .next()
                .fadeIn(1000)
                .end()
                .appendTo('#thumb');
    }, 5000);
</script>