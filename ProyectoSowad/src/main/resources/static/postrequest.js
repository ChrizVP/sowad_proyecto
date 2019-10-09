$(document).ready(
		function() {

			// SUBMIT FORM
			$("#bookForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					dni : $("#bookId").val()
					
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/demo/saveCliente",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						
							$("#getResultDiv").html(
									"" + result.nombre
											+ "Post Successfully! <br>"
											+ "---> Congrats !!" + "</p>");
						
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})