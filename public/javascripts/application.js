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
  
  
  function updateCorrectRowCount() { 
    var correctRow = '111XXX2221111';
    var signs;
    var count = 0;
    var rows = $(".ramheading tr").first().next();
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