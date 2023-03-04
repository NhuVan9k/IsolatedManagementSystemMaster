<%@page import="entity.Nurse"%>
<%@page import="dao.RoomDAO"%>
<%@page import="entity.Room"%>
<%@page import="entity.Area"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/add.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> Hệ thống > Ngưới cách ly > Thêm người cách ly</span>
        <div class="spacer2"></div>
    </div>

    <%
        HttpSession ss = request.getSession();
        Nurse nurse = (Nurse) ss.getAttribute("nurse");
        int area_id = nurse.getId_area();
        RoomDAO phongDAO = new RoomDAO();
        List<Room> phongs = phongDAO.getAllInArea(area_id);
    %>

    <div class="form-container">
        <form action="add" method="POST" id="form-dn" class="form">
            <div class="form-header">
                <div class="form-group form-group__special">
                    <label for="fullname" class="form-label">Họ và tên:</label>
                    <input id="fullname" class="form-control" name="fullname" type="text" required>
                    <span class="form-message"></span>
                </div>
                <div class="form-group">
                    <label for="suspicionLevel" class="form-label">Phân loại:</label>
                    <select name="suspicionLevel" id="suspicionLevel">
                        <option value="F1">F1</option>
                        <option value="F2">F2</option>
                        <option value="F3">F3</option>
                        <option value="F4" selected="selected">F4</option>
                    </select>
                </div>
            </div>
            <span class="form-span">Lý lịch cá nhân</span>
            <div class="form-group">
                <label for="age" class="form-label">Tuổi:</label>
                <input id="age" class="form-control" name="age" type="text" pattern="[0-9]{1,2}" title="Vui lòng nhập đúng số tuổi">
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="gender" class="form-label">Giới tính:</label>
                <select name="gender" id="gender" class="form-control">
                    <option value="Nam" selected="selected">Nam</option>
                    <option value="Nữ">Nữ</option>
                </select>
            </div>
            <div class="form-group">
                <label for="passport" class="form-label">Mã căn cước:</label>
                <input id="passport" class="form-control" name="passport" type="text" >
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="region" class="form-label">Quốc tịch:</label>
                <input id="region" class="form-control" name="region" type="text" required>
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="address" class="form-label">Địa chỉ:</label>
                <input id="address" class="form-control" name="address" type="text" required>
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="phone" class="form-label">Số điện thoại:</label>
                <input id="phone" class="form-control" name="phone" type="text" pattern="[0-9]{8,11}" title="Vui lòng nhập đúng số điện thoại">
                <span class="form-message"></span>
            </div>
            <div class="form-group">
                <label for="roomName" class="form-label">Xếp vào phòng số</label>
                <select name="roomName" id="roomName" class="form-control" required>
                    <% for (Room phong : phongs) {%>
                    <option value="<%=phong.getRoomId()%>"><%=phong.getRoomName()%></option>
                    <% }%>
                </select>
            </div>
            <button type = "submit" class="form-submit"><i class="far fa-save"></i><span>Lưu</span></button>
        </form>
    </div>
</div>
