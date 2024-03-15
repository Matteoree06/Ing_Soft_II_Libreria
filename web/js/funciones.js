/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("tr #btnDelete").click(function (){
       var idp=$(this).parent().find("#idp").val();
        swal({
            title: "¿Estas Seguro?",
            text: "Una vez eliminado, no podrás recuperar este registro!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    eliminar(idp);
                    if (willDelete) {
                        swal("Registro Eliminado", {
                            icon: "success",
                        });
                    } else {
                        swal("Registro no Eliminado!");
                    }
                }).then((willDelete)=>{
                    if(willDelete){
                        parent.location.href="Controlador?accion=carrito";
                    }
                });
    });
    function eliminar(idp){
        var url="Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp,
            success: function(data, textStatus, jqXHR){
            }
        });
    }
});

