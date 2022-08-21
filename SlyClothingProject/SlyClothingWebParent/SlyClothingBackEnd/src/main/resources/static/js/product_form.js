/**
 * 
 */
 function addNextDetailSection(){
	let allDivDetails = $("[id^='divDetail']");
	let divDetailsCount = allDivDetails.length;
	let nextDivDetailId = divDetailsCount
	
	let htmlDetailSection = `
	<div class="form-inline" id="divDetail${divDetailsCount}">
                		<label class="m-3">Name:</label>
                		<input type="text" class="form-control w-25" name="detailNames" maxlength="255">
                		<label class="m-3">Value:</label>
                		<input type="text" class="form-control w-25" name="detailValues" maxlength="255">
                	</div>
	`;
	$("#divProductDetails").append(htmlDetailSection);
	let previousDivDetailSection = allDivDetails.last();
	previousDivDetailID = previousDivDetailSection.attr("id");
	htmlLinkRemove = `
		<a class="btn fas fa-times-circle fa-2x icon-dark"
		href="javascript:removeDetailSectionById('${previousDivDetailID}')"
		 title="Xóa ảnh"></a>
	`;
	
	
	previousDivDetailSection.append(htmlLinkRemove);
	
	$("input[name='detailNames']").last().focus();
}
function removeDetailSectionById(id){
	$("#" + id).remove();
}