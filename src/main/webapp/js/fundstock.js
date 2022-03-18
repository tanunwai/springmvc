function drawChart(){
	drawChart(1);
	drawStockChart('^TWII');
}

function updateFundstock(sid) {		
		document.getElementById('fundstock').action='/springmvc/mvc/lab/fundstock/'+sid;
		document.getElementById('fundstock').submit();
	}
	
	function deleteFundstock(sid){
		document.getElementById('_method').value='DELETE';
		updateFundstock(sid);
	}
	
	function resetFundstock(){
		document.getElementById('fundstockForm').reset();
	}