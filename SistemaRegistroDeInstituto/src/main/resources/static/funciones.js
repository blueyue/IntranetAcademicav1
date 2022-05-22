function eliminarmatricula(id){
	swal({
  title: "Desea eliminar el registro seleccionado?",
  text: "Una vez eliminado no se podra recuperar",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((OK) => {
  if (OK) {
  
  $.ajax({
  	url:"/matriculas/mismatriculas/cancelar/"+id,
  	sucess:function(res){
  		console.log(res)
  	}
  	});
    swal("El registro ha sido eliminado!", {
      icon: "success",
    }).then((ok)=>{
    	if(ok){
    		location.href="/matriculas/visor/alumno";
    	}
    });
  } else {
    swal("La Eliminación ha sido cancelada");
  }
});
}

function eliminarprofesor(id){
	swal({
	  title: "Desea eliminar el registro seleccionado",
	  text: "Una vez eliminado no se puede recuperar el registro",
	  icon: "warning",
	  buttons: true,
	  dangerMode: true,
	})
	.then((OK) => {
	  if (OK) {
	  
		$.ajax({
			url:"/profesores/eliminar/"+id,
			success: function(res){
				console.log(res);
			}
		});
	    swal("El registro ha sido eliminado!", {
	      icon: "success",
	    }).then((ok)=>{
			if(ok){
				location.href="/profesores/listar";
			}
		});
	  } else {
	    swal("La Eliminación ha sido cancelada");
	  }
	});	
}

function confirmarpago(id){
	swal({
  title: "Desea confirmar el pago por el servicio?",
  text: "Una vez confirmado no se podra revertir",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((OK) => {
  if (OK) {
  
  $.ajax({
  	url:"/boletas/confirmacion-pago/"+id,
  	sucess:function(res){
  		console.log(res)
  	}
  	});
    swal("Se ha confirmado el pago con Exito!", {
      icon: "success",
    }).then((ok)=>{
    	if(ok){
    		location.href="/boletas/proceso";
    	}
    });
  } else {
    swal("La acción ha sido cancelada");
  }
});
}