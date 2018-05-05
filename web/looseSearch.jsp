
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Loose Search</title>
	
	<!-- load jquery to be used below (ref: https://jquery.com) -->
	<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
	
	<script type="text/javascript">
		// script to send search requests to the server
		// and recieve results

		function doSearch() {
		
			// make AJAX call to server
			$.getJSON( "looseSearch.do",
					
					{ CHARS: $('#searchBox').val() },

					function(data) { // callback code
						
					$('#results').text('');

					for (var index in data) {
						$('#results').append('<p>' + data[index].title);
						}

				});
				
		}
	</script>
	</head>

	<body>
	
	<h1>Loose Search</h1>
	
	<input type="text" id="searchBox" onKeyUp="doSearch();"/>
	<div id="results">
		No results are good results....
	</div>
	
	</body>

</html>
