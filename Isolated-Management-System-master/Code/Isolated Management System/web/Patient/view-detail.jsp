<%@page import="utils.Utils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/detail.css">

<div class="col-10 content">
    <button onclick="history.back()">Back</button>
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> > Hệ thống > Người cách ly > Xem chi tiết</span>
        <div class="spacer2"></div>
    </div>
    <div class="content-header">
        <div class="content-header__info">
            <span>Mã người cách ly: ${patient.getPatientId()}</span>
            <span>Họ và tên: ${patient.getPatientName()}</span>
        </div>
        <div class="content-header__btn"><a href="<%=request.getContextPath()%>/Patient/discharge?id=${patient.getPatientId()}"><i class="fas fa-sign-out-alt"></i> Ra viện</a></div>

    </div>
    <div class="content-middle"> 
        <div class="content-middle__lylich space3">
            <span class="info-label">Lý lịch cá nhân</span>
            <ul class="info-detail">
                <li>Tuổi: ${patient.getAge()}</li>
                <li>Giới tính: ${patient.getGender()}</li>
                <li>Mã căn cước: ${patient.getPassport()}</li>
                <li>Quốc tịch: ${patient.getRegion()}</li>
                <li>Đia chỉ: ${patient.getAddress()}</li>
                <li>Số điện thoại: ${patient.getPhoneNumber()}</li>
            </ul>
        </div>
        <div class="content-middle__cachly ">
            <span class="info-label">Thực hiện cách ly</span>
            <ul class="info-detail">
                <li>Mức độ nghi nhiễm: ${patient.getSuspicionLevel()}</li>
                <li>Ngày vào: <fmt:formatDate value="${patient.getTimeIn()}" pattern="${Utils.DATE_FORMATER}" /></li>
                <c:if test="${empty patient.getTimeOut()}"><li>Ngày ra: Chưa xác định</li></c:if>
                <c:if test="${not empty nguoi.getTimeOut()}"><li><li>Ngày ra: ${Utils.DATE_FORMATER.format(patient.getTimeOut())}</li></li></c:if>
                <li>Số ngày thực hiện: ${Utils.getDistanceTime(patient.getTimeIn(), patient.getTimeIn()) + 1}</li>
                <li>Phòng số: ${patient.getRoom().getRoomName()}</li>
            </ul>
        </div>
    </div>   
    <div class="content-history">
        <span class="info-label">Lịch sử xét nghiệm</span>
        <ul class="info-detail">
            <c:if test="${empty results}">
                <li>Chưa tham gia lần xét nghiệm nào!</li>
                </c:if>
                <c:if test="${not empty results}">
                <table style="width:60%">
                    <tr>
                        <th>Ngày test</th>
                        <th>Tên xét nghiệm</th>
                        <th>Kết quả</th>
                    </tr>
                    <c:forEach var="result" items="${results}">
                        <tr>
                            <td>${result.getCreateDate()}</td>
                            <td>${result.getTestName()}</td>
                            <td>${result.getResult()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </ul>


    </div>

    <div class="content-btn">
        <c:if test="${empty patient.getTimeOut()}">
            <a href="<%=request.getContextPath()%>/Patient/add-test.jsp?id=${patient.getPatientId()}" style="background-color: #6FCF97"><i class="fas fa-pen"></i> Thêm xét nghiệm</a>
        </c:if>        
        <a href="<%=request.getContextPath()%>/Patient/update.jsp?id=${patient.getPatientId()}" style="background-color: #00AAFF"><i class="fas fa-user-edit"></i> Chỉnh sửa hồ sơ</a>
        <a href="<%=request.getContextPath()%>/Patient/delete?id=${patient.getPatientId()}" style="background-color: red;"><i class="fas fa-trash-alt"></i> Xóa</a>
    </div>
</div>