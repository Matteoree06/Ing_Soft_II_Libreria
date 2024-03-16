/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("tr #deleteItem").click(function (e) {
        e.preventDefault();
        var idp = $(this).parent().find('#item2').val();        
        swal({
            title: "Esta Seguro de Eliminar?",
            text: "Una una Vez Eliminado, Debera Agregar de Nuevo!",
            icon: "warning",
            buttons: true,
            dangerMode: true
        }).then((willDelete) => {
            if (willDelete) {
                eliminar(idp);
                swal(" ¡Oh! ¡Registro Borrado! ", {
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        parent.location.href = "Controlador?accion=carrito";
                    }
                });
            }
        });
    });
    function eliminar(idp){
        var url = "Controlador?accion=Delete&id=" + idp;
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp,
            success: function(data, textStatus, jqXHR){
            }
        });
    }
    
    $("#Cantidad").click(function () {
        var idp=$(this).parent().find("#idpro").val();
        var cantidad=$(this).parent().find("#Cantidad").val();
        var url="Controlador?accion=ActualizarCantidad";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp+"&Cantidad=" + cantidad,
            success: function (data, textStatus, jqXHR) {
                location.href="Controlador?accion=Carrito"
            }
        });
    });
});

