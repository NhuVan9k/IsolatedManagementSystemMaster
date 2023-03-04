<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/assets/css/list/add.css">
<div class="col-10 content">
    <div class="content-label">
        <span class="content-label__menu">Bảng điều khiển</span>
        <span class="content-label__info"><i class="fas fa-home"></i> Hệ thống > Báo cáo sức khỏe > Cập nhật báo cáo</span>
        <div class="spacer2"></div>
    </div>

    <div class="form-container">
        <h3>Báo cáo sức khỏe chi tiết</h3>
        <br><br>
        <h4>Vui lòng cập nhật lại toàn bộ các triệu chứng trong 24 giờ qua:</h4>
        <form action="update" method="POST" id="form-dn" class="form">
            <input id="reportId" class="form-control" name="reportId" type="text" value="${report.getId_report()}" style="display: none">
            <div class="input-group mb-3">
                <div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s1" value="Sốt hoặc ớn lạnh"> Sốt hoặc ớn lạnh
                    </label>
                </div>
                <div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s2" value="Đau nhức cơ hoặc cơ thể"> Đau nhức cơ hoặc cơ thể
                    </label>
                </div>
                <div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s3" value="Mất vị giác hoặc mùi mới"> Mất vị giác hoặc mùi mới
                    </label>
                </div><div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s4" value="Nghẹt mũi hay sổ mũi"> Nghẹt mũi hay sổ mũi
                    </label>
                </div>
                <br><div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s5" value="Tiêu chảy"> Tiêu chảy
                    </label>
                </div><div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s6" value="Mệt mỏi"> Mệt mỏi
                    </label>
                </div><div class="form-check" style="margin: 50px 20px"
                           <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s7" value="Đau đầu"> Đau đầu
                    </label>
                </div><div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s8" value="Đau họng"> Đau họng
                    </label>
                </div><div class="form-check" style="margin: 50px 20px">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="s9" value="Buồn nôn hoặc nôn mửa"> Buồn nôn hoặc nôn mửa
                    </label>
                </div>
                <div class="form-check-inline">
                    <input type="checkbox" name="cother" value="1"> 
                    <input type="text" placeholder="Các triệu trứng khác" name="other" value="">
                </div>
            </div>

            <br>
            <h4>Kết quả xét nghiệm COVID-19 trong vòng 5 ngày qua :</h4>
            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="test" value="Âm tính" checked>Âm tính
                </label>
            </div>
            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="test" value="Dương tính">Dương tính
                </label>
            </div>
            <div class="form-group">
                <button type = "submit" class="form-submit"><i class="far fa-save"></i><span>Lưu</span></button>
            </div>
        </form>

    </div>
</div>