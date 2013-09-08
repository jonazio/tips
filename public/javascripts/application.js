$(document).ready(function() { 
  $(".tecken").on('click', function() {
	$(this).toggleClass("tecken");
	
	if ($(this).hasClass("tecken") )
	{
	  $(this).text('\xa0');
	}
	else
	{
	  $(this).text($(this).data("value"));
	}
	updateCorrectRowCount();	
  });
  
  $("#fileupload").on('click', function(){
		jsRoutes.controllers.Tips.fileupload().ajax({
			success: function(data){
				$('#mainframe div').empty();
				$("#mainframe").html(data);
			}	
		});	  
  });
  
  $("#correctrow").on('click', function(){
		jsRoutes.controllers.Tips.correctRow().ajax({
			success: function(data){
				$('#mainframe div').empty();
				$("#mainframe").html(data);
			}	
		});	  
});
  
  $("#correctrow2").on('click', function(){
		jsRoutes.controllers.Tips.correctRow2().ajax({
			success: function(data){
				$('#mainframe div').empty();
				$("#mainframe").html(data);
			}	
		});	  
});

  
  function updateCorrectRowCount() { 
    var correctRow = "";
    var tempStr;
    
    var rows = $(".ramheading2 tr:gt(0)");
    
    rows.each(function(index){
    	
    	if ($("td:nth-child(2)", this).text() == "1") {
    		tempStr = "1";
    	} else if (($("td:nth-child(3)", this).text() == "X")){
    		tempStr = "X";
    	} else if (($("td:nth-child(4)", this).text() == "2")){
    		tempStr = "2";
		} else{
			tempStr = "_"
		}
    	
    	correctRow = correctRow + tempStr
    });
	jsRoutes.controllers.Tips.correctRow3(correctRow).ajax({
		success: function(data){
			$(".correctRows").html(data);
		}	
	});	  
  };
  
  function updateCorrectRowCountOld() { 
    var correctRow = '111XXX2221111';
    var signs;
    var count = 0;
    var rows = $(".ramheading2 tr").first().next();
    var length = correctRow.length;
    var corrSign;
    var rownum = 0;
    
    for (var i = 0; i < length; ++i) {
      corrSign = correctRow.charAt(i);
      signs = rows.text();
      if (signs.indexOf(corrSign) > 0) {
        ++count;
      }
      rows = rows.next();   
    } 
    $(".correctRows").text(count);
  };

});