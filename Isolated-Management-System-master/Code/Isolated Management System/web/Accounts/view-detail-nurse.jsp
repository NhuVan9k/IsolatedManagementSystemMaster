

<%@page import="utils.Utils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/detail.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Quản lý tài khoản > Xem chi tiết</span>
        <div class="spacer2"></div>
    </div>
    <div class="content-body">
        <div>
            <div class="content-header__info content-header__account">
                <span>Mã tài khoản: ${account.getAccountId()}</span>
                <span>Họ và tên: ${nurse.getFullName()}</span>
                <span style="color: blue;font-size: 2rem;font-weight: 500;">Tên đăng nhập: ${account.getUserName()}</span>
            </div>

            <div class="content-middle"> 
                <div class="content-middle__lylich">
                    <span class="info-label">Lý lịch cá nhân</span>
                    <ul class="info-detail">
                        <li>Số điện thoại: ${nurse.getPhone()}</li>
                        <li>Địa chỉ: ${nurse.getAddress()}
                    </ul>
                </div>
            </div>
        </div>
        <div><img class="content-img" src="<%=request.getContextPath()%>/${initParam.imgPath}${account.getAvatar()}"/></div>
    </div>

   
</div>
