//
var extraImagesCount = 0;
//
$(document).ready(function () {
	$("#logoutLink").on("click", function (e) {
		e.preventDefault();
		document.logoutForm.submit();
	})
	customizeDropDownMenu();
	toggleDropDownMenu();
})
function customizeDropDownMenu() {
	$('.dropdown > a').on("click", function () {

		window.location.href = this.href;
	})
}
function toggleDropDownMenu() {
	$('ul.navbar-nav li.dropdown ').hover(function () {

		$(this).find(".dropdown-menu").stop(true, true).delay(100).fadeIn(500);
	}, function () {
		$(this).find(".dropdown-menu").stop(true, true).delay(100).fadeOut(500);
	});
}
function checkEmailUnique(form) {

	url = "http://localhost:8080/SlyClothingAdmin/admins/check_email";
	userEmail = $("#email").val();
	csrfValue = $("input[name ='_csrf']").val();
	userId = $("#id").val();
	params = { id: userId, email: userEmail, _csrf: csrfValue };

	$.post(url, params, function (response) {
		if (response == "OK") {
			form.submit();

		} else if (response == "Duplicated") {
			showWarningDialog("There is another user having the email:" + userEmail);
		}
		else {
			showErrorDialog("Unknown response from server");
		}
	}).fail(function () {
		showErrorDialog("Could not connect to server");
	})

	return false;
}
		function showModalDialog(title, message) {
			$("#modalTitle").text(title);
			$("#modalBody").text(message);
			$("#modalDialog").modal();
		}
		function showWarningDialog(message) {
			showModalDialog("Warning", message);
		}
		function showErrorDialog(message) {
			showModalDialog("Error", message);
		}
	
		function showImageThumbnail(fileInput) {
			var file = fileInput.files[0];//Vì fileIput đang là dạng FileList nên phải files[0]
			var reader = new FileReader();

			reader.onload = function (e) {
				$("#thumbnail").attr("src", e.target.result);
			}
			reader.readAsDataURL(file);//luôn chạy trước onload

		}
function checkPasswordMatch(confirmPassword) {
	if (confirmPassword.value != $("#password").val()) {
		confirmPassword.setCustomValidity("Password not match!");
	} else {
		confirmPassword.setCustomValidity("");
	}
}

$(document).ready(function () {
	$("#btnCancel").click(function () {
		window.location = "/ShopmeAdmin/users";
	})
	//
	$("#fileImage").on("change", function () {
		fileSize = this.files[0].size;
		alert("File size:" + fileSize);

		if (fileSize > 1048576) {//== 1MG
			this.setCustomValidity("File > 1 MB");
			this.reportValidity();
		} else {
			this.setCustomValidity("");

			showImageThumbnail(this)
		}
	});
	//
	$("input[name='extraImage']").each(function(index){
		extraImagesCount++;
	$(this).on("change", function () {
	
		
		showExtraImageThumbnail(this, index)
		
	});
	});
	//
	

})
//
function showImageThumbnail(fileInput) {
	var file = fileInput.files[0];//Vì fileIput đang là dạng FileList nên phải files[0]
	var reader = new FileReader();
	
	reader.onload = function (e) {
		$("#thumbnail").attr("src", e.target.result);
	}
	reader.readAsDataURL(file);//luôn chạy trước onload
	
}
//
function removeExtraImage(index){

	$("#divExtraImage" + index).remove();
	extraImagesCount--;
}
//
function addNextExtraImageSection(index){
	htmlExtraImage = `
		<div class="col border m-3 p-2" id="divExtraImage${index}">
                			<div id="extraImageHeader${index}">
                			<label>Ảnh phụ #${index + 1}:</label>
                			<div class="m-2">
                		<img id="extraThumbnail${index}" alt="Extra image #${index + 1} preview" style="width: 300px" class="img-fluid"
                		src="${defaultImageThumbnailSrc}">
                			</div>
                				<div>
                		<input type="file"  name="extraImage"
                		accept="image/png, image/jpeg" onchange="showExtraImageThumbnail(this, ${index})"
                		 required>
                	</div>
                			</div>
                		</div>
	`;
	htmlLinkRemove = `
		<a class="btn fas fa-times-circle fa-2x icon-dark float-right" title="Xóa ảnh" href="javascript:removeExtraImage(${index-1})"></a>
	`;
	$("#divProductImages").append(htmlExtraImage);
	
	$("#extraImageHeader" + (index - 1)).append(htmlLinkRemove);
	
	extraImagesCount++;
}
//

//
function showExtraImageThumbnail(fileInput, index) {
	var file = fileInput.files[0];//Vì fileIput đang là dạng FileList nên phải files[0]
	var reader = new FileReader();
	
	reader.onload = function (e) {
		$("#extraThumbnail" + index).attr("src", e.target.result);
	}
	reader.readAsDataURL(file);//luôn chạy trước onload
	if(index >= extraImagesCount - 1){
		
	addNextExtraImageSection(index + 1)
	}
}
function checkUnique(form) {
	var cateId = $("#id").val();
	var cateName = $("#name").val();
	var cateAlias = $("#alias").val();
	csrfValue = $("input[name='_csrf']").val();

	url = "[[@{/categories/check_unique}]]";

	params = { id: cateId, name: cateName, alias: cateAlias, _csrf: csrfValue };
	$.post(url, params, function (response) {
		alert(response)
	}).fail(function () {
		alert('failed');
	});
	return false;
}