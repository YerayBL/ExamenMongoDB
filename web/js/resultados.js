$(document).ready(function () {
    $("#btnVolver").click(function (){
       window.location = "index.html"; 
    });
    cargarNotas();
});

function cargarNotas() {
    var url = "notasServlet";
    $.ajax({
        method: "GET",
        url: url,
        data: {},
        success: function (jsn) {
            
            
            $("#tablaNotas > tbody").empty();
            
            $.each(jsn, function (i, item) {
                var DNI = (item.DNI).substring(0, 4) + " * * * * " + (item.DNI).charAt(item.DNI.length - 1);
                var tipo = item.tipoExamen;
                var nota = item.nota;
                var row = "<tr><td>" + DNI + "</td><td>" + tipo + "</td><td>" + nota + "</td></tr>";
                $("#tablaNotas > tbody").append(row);
            });
        },
        error: function (e) {
            
            if (e["responseJSON"] === undefined) {
                showToast("ERROR DESCONOCIDO", "Inténtelo más tarde", "error", "#D43721");
            } else {
                showToast(e["responseJSON"]["error"], "Ups, algo malo ha ocurrido", "error", "#D43721");
            }
        }
    });
}


function showToast(head, text, icon, bgColor) {
    $.toast({
        text: text, 
        heading: head, 
        icon: icon, 
        showHideTransition: 'fade', 
        allowToastClose: false, 
        hideAfter: 3000, 
        position: 'top-center', 
        textAlign: 'left', 
        loader: true, 
        loaderBg: '#9EC600', 
        bgColor: bgColor
    });
}

