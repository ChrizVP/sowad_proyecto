$(document).ready(
		function() {

			// SUBMIT FORM
			$("#datosCotizacion").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					dni : $("#clienteDni").val()
					
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/cotizacion/buscarcliente",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						
							$("#getResultDiv").html(
									"<br>"+
									"<br>"+
									"<label for='nombre' class='col-md-1 control-label'>NOMBRE:</label>"+ 
									"<div class='col-md-3'>"+
									"<input type='text' class='form-control' id='' placeholder="+result.nombre+" readonly='readonly'>"+
									"</div>"+
									"<label for='apellidos' class='col-md-1 control-label'>APELLIDOS:</label>"+
									"<div class='col-md-3'>"+
									"<input type='text' class='form-control' id='' placeholder="+result.apellidos+" readonly='readonly'>"+     
									"</div>");
						
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})
		
		
		

/*		
$(document).ready(
		function() {
			$("#enviarProducto").submit(function(event) {
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				var formData = {
					producto_id :16,
					cantidad : $("#cantidad").val()
					
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/add/cotizacion",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						
							$("#resultadoAddCotizacion").html(result);
						
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})*/