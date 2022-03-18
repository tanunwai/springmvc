var pageNum=0;
var totalPageNumber=0;
$(document).ready(function() {
	//呼叫函式
	table_list();
	//註冊驗証事件
	$('#myForm').validate({
		onsubmit: false,//將按鈕事件設定為自訂義
		onkeyup: false, //將標籤
		rules: {
			fname: {//指input標籤中的name而不是id
				required: true,
				rangelength: [2, 50]
			}
		},
		messages: {
			//自訂義錯誤名稱
			fname: {
				required: '請輸入基金名稱',
				rangelength: '名稱長度必需是介於{0}~{1}之間'
			}
		}
	});
	//註冊相關事件
	$('#add').on('click', function() {
		addOrUpdate('POST');
	});
	$('#myTable').on('click', 'tr', function() {
		//測試抓取欄位equal是指第幾欄
		//console.log($(this).find('td').eq(0).text().trim());		
		getItem(this);
	});
	$('#upt').on('click', function() {
		addOrUpdate('PUT');
	});
	$('#del').on('click', function() {
		deleteItem();
	});
	$('#rst').on('click', function() {
		btnAttr(0);
		$('#myForm').trigger('reset');
	});
});

//分頁查詢
function queryPage(pageNum){
	var path="../mvc/lab/fund/";
	if(pageNum>0){
		path="../mvc/lab/fund/page/"+pageNum;
	}
	this.pageNum=pageNum;
	//取得所有fund資料
	$.get(path, function(datas, status) {
		//alert('Hello Jquery!!!');
		//console.log('Data :'+data);
		//console.log('Status :'+status);
		//清除目前舊有資料
		$('#myTable tbody > tr').remove();
		//依序抓取資料
		$.each(datas, function(i, item) {
			//3個欄位
			var html = '<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td></tr>';			
			//抓取標籤中的id加入並套用util.js裡的stringformat的api
			$('#myTable').append(String.format(html, item.fid, item.fname, item.createtime, parseFundstocks(item.fundstocks)));
		});
	});
}

//解析fundstocks資料
function parseFundstocks(datas){
	if(datas == null) return "";
	var str='';
	$.each(datas, function(i, item){
		str += item.symbol+'、';
	});
	str= str.substring(0, str.length-1);
	return str;
}

//抓取欄位函式
function getItem(elem) {
	//alert($(elem).find('td').eq(0).text().trim());
	var fid = $(elem).find('td').eq(0).text().trim();
	var path = '../mvc/lab/fund/' + fid;
	var func = function(fund, status) {
		//console.log(fund);
		//將資料導入myForm表單
		$('#myForm').find('#fid').val(fund.fid);
		$('#myForm').find('#fname').val(fund.fname);
		//修改btn的狀態
		btnAttr(status);
		//判斷該基金是否有成分股
		if (fund.fundstocks.length > 0) {
			$('#myForm').find('#del').attr('disabled', true);
		}
	};
	$.get(path, func);
}

//新增或修改
function addOrUpdate(method) {
	//驗証
	console.log($('#myForm').valid());
	//判斷是否驗証成功
	if (!$('#myForm').valid()) {
		return;
	}
	//將表單欄位序列化使用util.js的api
	var jsonObject = $('#myForm').serializeObject();
	//將資料轉成json string格式
	var jsonStr = JSON.stringify(jsonObject);
	//console.log(jsonStr);
	//alert(jsonStr);
	//將資料傳向後端
	$.ajax({
		url: '../mvc/lab/fund/',
		type: method,
		contentType: 'application/json;charset=UTF-8',
		data: jsonStr,
		success: function(respData) {
			console.log(respData);
			//列表資料更新
			table_list();
			//回到重置狀態
			$('#myForm').find('#rst').trigger('click');
		}
	});
}
/*刪除 */
function deleteItem() {
	var fid = $('#myForm').find('#fid').val();
	$.ajax({
		url: '../mvc/lab/fund/' + fid,
		type: 'DELETE',
		contentType: 'application/json;charset=UTF-8',
		success: function(respData) {
			console.log(respData);
			//列表資料更新
			table_list();
			//回到重置狀態
			$('#myForm').find('#rst').trigger('click');
		},
		error: function(http, textStatus, errorThrown) {
			//console.log('Http :'+http);
			//console.log('textStatus :'+textStatus);
			console.log('errorThrows :' + errorThrown);
			var errorInfoText = JSON.stringify(http);
			//console.log(errorInfoText.includes('REFERENCES'));
			if (errorInfoText.includes('REFERENCES')) {
				alert('該筆資料不能刪除;原因: 該基金下有成分股參照');
			} else {
				var errorText = JSON.stringify(textStatus);
				alert('該筆資料無法刪除;原因 :' + errorText);
			}
		}
	});
}

/*table data*/
function table_list() {
	//呼叫函式分頁查詢
	queryPage(pageNum);
	//分頁數
	page_list()
}

//分頁
function page_list(){
	var path='../mvc/lab/fund/totalPagecount';
	$('#myPageSpan').empty();
	$.get(path, function(totalPageNumber){
		this.totalPageNumber=totalPageNumber;
		for(var i=1;i<=totalPageNumber;i++){
			var html='<span class="mylink" onclick="queryPage({0});">{1}</span>&nbsp;';			
			$('#myPageSpan').append(String.format(html, i, i));
		}
	});
}

/*按鈕的狀態 */
function btnAttr(status) {
	$('#myForm').find('#add').attr('disabled', status != 0);
	$('#myForm').find('#upt').attr('disabled', status == 0);
	$('#myForm').find('#del').attr('disabled', status == 0);
}