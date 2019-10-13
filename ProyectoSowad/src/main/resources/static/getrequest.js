$(document).ready(function() {
		setTimeout(function(){
			var url = "/cotizacion/buscar/usuario";
				$.ajax({
					contentType : "application/json",
					url: url,
					type: "GET",
					dataType : 'json',
					success: function(datos){
						$("#datosVendedor").html(" "+ datos.nombre+ " ");
						console.log(result);
					}
				});
				
			});
		
});


$(document).on("ready",function() {
	listar();
});





$("#boton-productos").on("click", getUsers);

	      function getUsers() {
	        $.ajax({
	          url: "/form",
	          success: function(respuesta) {

	            var listaUsuarios = $("#lista-usuarios");
	            $.each(respuesta.data, function(index, elemento) {
	              listaUsuarios.append(
	                  "<div>"
	                +     "<p>" + elemento.producto_id + " " + elemento.nombre + "</p>"
	                +     "<p>" + elemento.color + "  " + elemento.cantidad + "</p>"
	                + "</div>"
	              );    
	            });
	          },
	          error: function() {
	            console.log("No se ha podido obtener la información");
	          }
	        });
	      }
