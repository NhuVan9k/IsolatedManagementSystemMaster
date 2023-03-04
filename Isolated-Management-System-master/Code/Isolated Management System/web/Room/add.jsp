<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/add.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> Hệ thống > Quản lý phòng bệnh > Thêm phòng</span>
        <div class="spacer2"></div>
    </div>


    <div class="form-container">
        <form action="add" method="POST" id="form-dn" class="form">

            <div class="form-group form-group__special">
                <label for="roomName" class="form-label">Tên phòng</label>
                <input id="roomName" class="form-control" name="roomName" type="text" required>
                <span class="form-message"></span>
            </div>

            <div class="form-group">
                <label for="bedNumber" class="form-label">Số giường tối đa:</label>
                <input id="bedNumber" class="form-control" name="bedNumber" type="text" pattern="[0-9]{1,2}" title="Vui lòng nhập đúng số giường">
                <span class="form-message"></span>
            </div>

            <div class="form-group">
                <label for="note" class="form-label">Ghi chú</label>
                <input id="note" class="form-control" name="note" type="text" >
                <span class="form-message"></span>
            </div>

            <button type = "submit" class="form-submit"><i class="far fa-save"></i><span>Lưu</span></button>
        </form>

    </div>
</div>