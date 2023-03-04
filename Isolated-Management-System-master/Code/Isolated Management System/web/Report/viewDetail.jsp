<%@page import="utils.Utils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/detail.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Báo cáo sức khỏe > Báo cáo chi tiết </span>
        <div class="spacer2"></div>
    </div>

    <div class="content-header">
        <div class="content-header__info">
            <span>Khu cách ly: ${areaName}</span>
            <span>Mã phòng: ${roomId}  -  Tên phòng: ${roomName}</span>
        </div>
        
    </div>
    <div class="content-middle"> 
        <div class="content-middle__lylich space3">
            <span class="info-label">Thông tin</span>
            <ul class="info-detail">
                <li>Số giường tối đa: ${bedNumber}</li>
                <li>Số bệnh nhân hiện có: ${patientNumber}</li>
                <li>Ghi chú: ${note}</li>
                
            </ul>
        </div>
        
    </div>   
    <div class="content-history">
        <span class="info-label">Bệnh nhân hiện có</span>
        <ul class="info-detail">
            <c:if test="${empty listPatient}">
                <li>Phòng trống!</li>
                </c:if>
                <c:if test="${not empty listPatient}">
                <style>
                    .info-detail li a:hover {
                        text-decoration: none;
                        opacity: 80%;
                    }
                </style>
                    <c:forEach var="patient" items="${listPatient}">
                        
                    <li><a href="<%=request.getContextPath()%>/Patient/viewdetail?id=${patient.getPatientId()}" style="color: var(--text-color)">
                            Bệnh nhân số ${patient.getPatientId()} : ${patient.getPatientName()}   -------  Tình trạng: ${patient.getSuspicionLevel()}
                        </a></li>
                    </c:forEach>
                </c:if>
        </ul>
    </div>

    <div class="content-btn">
        
        <a href="<%=request.getContextPath()%>/Room/update?roomId=${roomId}" style="background-color: #00AAFF"><i class="fas fa-user-edit"></i> Cập nhật phòng</a>
        <a href="<%=request.getContextPath()%>/Room/delete?roomId=${roomId}" style="background-color: red;"><i class="fas fa-trash-alt"></i> Xóa</a>
    </div>
</div>