<%-- 
    Document   : update
    Created on : May 8, 2021, 12:21:39 AM
    Author     : CHUNG
--%>

<%@page import="entity.Nurse"%>
<%@page import="entity.Room"%>
<%@page import="dao.RoomDAO"%>
<%@page import="entity.Area"%>
<%@page import="entity.Patient"%>
<%@page import="dao.PatientDAO"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : add-nguoiCachLy
    Created on : May 6, 2021, 6:24:46 PM
    Author     : CHUNG
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/add.css">
<%  String id = request.getParameter("id");
    int id_int = Integer.parseInt(id);
    PatientDAO patientDAO = new PatientDAO();
    Patient patient = patientDAO.get(id_int);
    HttpSession ss = request.getSession();
    Nurse nurse = (Nurse)ss.getAttribute("nurse");
    RoomDAO roomDAO = new RoomDAO();
    List<Room> rooms = roomDAO.getAllInArea(nurse.getId_area());
    
%>
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> Hệ thống > Ngưới cách ly > Chỉnh sửa lý lịch</span>
        <div class="spacer2"></div>
    </div>

    <div class="form-container">
        <form action="update" method="POST" id="form-dn" class="form">
            <input id="patientId" name="patientId" style="display: none" value="<%=patient.getPatientId() %>">
            <div class="form-header">
                <div class="form-group form-group__special">
                    <label for="fullname" class="form-label">Họ và tên:</label>
                    <input id="fullname" class="form-control" name="fullname" type="text" value="<%=patient.getPatientName()%>" required>
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <label for="suspicionLevel" class="form-label">Phân loại:</label>
                    <select name="suspicionLevel" id="suspicionLevel">
                        <option value="F1">F1</option>
                        <option value="F2">F2</option>
                        <option value="F3">F3</option>
                        <option value="F4">F4</option>
                    </select>
                </div>
            </div>
            <span class="form-span">Lý lịch cá nhân</span>
            <div class="form-group">
                <label for="age" class="form-label">Tuổi:</label>
                <input id="age" class="form-control" name="age" type="text" value="<%=patient.getAge()%>" pattern="[0-9]{1,2}" title="Vui lòng nhập đúng số tuổi">
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="gender" class="form-label">Giới tính:</label>
                <select name="gender" id="gender" class="form-control">
                    <option value="Nam">Nam</option>
                    <option value="Nữ">Nữ</option>
                </select>
            </div>
            <div class="form-group">
                <label for="passport" class="form-label">Mã căn cước:</label>
                <input id="passport" class="form-control" name="passport" type="text" value="<%=patient.getPassport()%>">
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="region" class="form-label">Quốc tịch:</label>
                <input id="region" class="form-control" name="region" type="text" value="<%=patient.getRegion()%>" required>
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="address" class="form-label">Địa chỉ:</label>
                <input id="address" class="form-control" name="address" type="text" value="<%=patient.getAddress()%>" required>
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="phoneNumber" class="form-label">Số điện thoại:</label>
                <input id="phoneNumber" class="form-control" name="phoneNumber" type="text" value="<%=patient.getPhoneNumber()%>" pattern="[0-9]{8,11}" title="Vui lòng nhập đúng số điện thoại">
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="roomName" class="form-label">Xếp vào phòng số</label>
                <select name="roomName" id="phong" class="form-control" required>
                    <% for(Room room : rooms){ %>
                    <option value="<%=room.getRoomId()%>"><%=room.getRoomName()%></option>
                    <% } %>
                </select>
                
            </div>
            <button type = "submit" class="form-submit"><i class="far fa-save"></i><span>Lưu</span></button>
        </form>
    </div>
</div>

<script>
    $('#phan_loai option[value = <%=patient.getSuspicionLevel() %>]').attr('selected','selected');
    $('#gioi_tinh option[value = <%=patient.getGender() %>]').attr('selected','selected');
    $('#phong option[value = <%=patient.getRoom().getRoomId() %>]').attr('selected','selected');
</script>
