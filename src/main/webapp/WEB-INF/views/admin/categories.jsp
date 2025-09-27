<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Danh mục</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="container mt-4">

		<h2>Quản lý Danh mục sản phẩm</h2>

		<p>
			<button class="btn btn-success" data-bs-toggle="modal"
				data-bs-target="#addCategoryModal">
				<i class="fas fa-plus"></i> Thêm mới
			</button>
		</p>

		<table class="table table-striped table-bordered">
			<thead class="table-dark">
				<tr>
					<th>Id</th>
					<th>Icon</th>
					<th>Tên Danh mục</th>
					<th style="width: 15%;">Hành động</th>
				</tr>
			</thead>
			<tbody>
				</tbody>
		</table>

	</div>

	<div class="modal fade" id="addCategoryModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="addCategoryForm" method="post"
					enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Thêm mới Danh mục</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="categoryName" class="form-label">Tên Danh mục</label>
							<input type="text" class="form-control" name="categoryName"
								placeholder="Ví dụ: Điện thoại" required>
						</div>
						<div class="mb-3">
							<label for="icon" class="form-label">Icon</label> <input
								type="file" class="form-control" name="icon" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Đóng</button>
						<button type="submit" class="btn btn-primary">Lưu</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="updateCategoryModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="updateCategoryForm" method="post"
					enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Cập nhật Danh mục</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<input type="hidden" name="categoryId" id="updateCategoryId">

						<div class="mb-3">
							<label for="updateCategoryName" class="form-label">Tên
								Danh mục</label> <input type="text" class="form-control"
								name="categoryName" id="updateCategoryName" required>
						</div>
						<div class="mb-3">
							<label for="updateIcon" class="form-label">Icon mới (để
								trống nếu không muốn thay đổi)</label> <input type="file"
								class="form-control" name="icon" id="updateIcon">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Đóng</button>
						<button type="submit" class="btn btn-primary">Lưu thay
							đổi</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		$(document).ready(function() {
			function loadCategories() {
				$.ajax({
					url : contextPath + '/api/category',
					type : 'GET',
					dataType : 'json',
					success : function(response) {
						if (response.status === true && Array.isArray(response.body)) {
							var listCategory = response.body;
							var tableBody = $('table tbody');
							tableBody.empty();

							$.each(listCategory,
								function(i, category) {
									var iconUrl = contextPath + '/uploads/' + category.icon;
									var row = '<tr>'
										+ '<td>' + category.categoryId + '</td>'
										+ '<td><img src="' + iconUrl + '" style="width:70px" class="img-fluid rounded" alt="icon"></td>'
										+ '<td>' + category.categoryName + '</td>'
										+ '<td>'
										+ '<a href="#" class="btn btn-sm btn-warning me-2 btn-edit"><i class="fas fa-edit"></i> Sửa</a> '
										+ '<a href="#" data-id="' + category.categoryId + '" class="btn btn-sm btn-danger btn-delete"><i class="fas fa-trash"></i> Xóa</a>'
										+ '</td>'
										+ '</tr>';
									tableBody.append(row);
								});
						}
					},
					error : function() {
						alert("Không thể tải danh sách danh mục!");
					}
				});
			}

			loadCategories();
			$("#addCategoryForm").submit(function(e) {
				e.preventDefault();
				var formData = new FormData(this);
				$.ajax({
					url : contextPath + '/api/category/addCategory',
					type : 'POST',
					data : formData,
					processData : false,
					contentType : false,
					success : function(response) {
						if (response.status === true) {
							alert("Thêm thành công!");
							$('#addCategoryModal').modal('hide');
							loadCategories();
						} else {
							alert("Thêm thất bại: " + response.message);
						}
					},
					error : function(jqXHR) {
						alert("Đã xảy ra lỗi: " + jqXHR.responseText);
					}
				});
			});

			$(document).on('click', '.btn-delete', function(e) {
				e.preventDefault();
				var categoryId = $(this).data('id');
				if (confirm('Bạn có chắc chắn muốn xóa danh mục này?')) {
					$.ajax({
						url : contextPath + '/api/category/deleteCategory?categoryId=' + categoryId,
						type : 'DELETE',
						success : function(response) {
							if (response.status === true) {
								alert("Xóa thành công!");
								loadCategories();
							} else {
								alert("Xóa thất bại: " + response.message);
							}
						},
						error : function() {
							alert("Đã xảy ra lỗi khi xóa!");
						}
					});
				}
			});

			$(document).on('click', '.btn-edit', function(e) {
				e.preventDefault();

				var categoryRow = $(this).closest('tr');
				var categoryId = categoryRow.find('td:eq(0)').text().trim();
				var categoryName = categoryRow.find('td:eq(2)').text().trim();

				$('#updateCategoryId').val(categoryId);
				$('#updateCategoryName').val(categoryName);
				$('#updateIcon').val('');				
				$('#updateCategoryModal').modal('show');
			});

			$("#updateCategoryForm").submit(function(e) {
			    e.preventDefault();
			    
			    var formData = new FormData(this);

			    $.ajax({
			        url: contextPath + '/api/category/updateCategory',
			        type: 'PUT',
			        data: formData,
			        processData: false,
			        contentType: false,
			        
			        success: function(response) {
			            if (response.status === true) {
			                alert("Cập nhật thành công!");
			                $('#updateCategoryModal').modal('hide');
			                loadCategories(); 
			            } else {
			                alert("Cập nhật thất bại: " + response.message);
			            }
			        },
			        error: function(jqXHR) {
			            alert("Đã xảy ra lỗi: " + jqXHR.responseText);
			        }
			    });
			});
		});
	</script>
</body>
</html>