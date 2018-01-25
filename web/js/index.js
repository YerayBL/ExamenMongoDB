$(document).ready(function () {
    cargarNombresExamenes();
    
    $("#btnResulados").click(function () {
        window.location = "resultados.html";
    });

    
    $("form").submit(function () {
        $("#modalTest").modal("show");
        return false;
    });

    
    $("#btnConfirmarTest").click(function () {
        var examen = $("#selectExamenes :selected").text();
        var DNI = $("#inpDNI").val();

        sessionStorage.setItem("_DNI", DNI);
        sessionStorage.setItem("_tipoExamen", examen);

        window.location = "examen.html";
    });
});

function cargarNombresExamenes() {
    var url = "getNameExamen";
    $.ajax({
        method: "GET",
        url: url,
        data: {},
        success: function (jsn) {
            
            $.each(jsn, function (i, item) {
                var option = "<option>" + item + "</option>";
                $("#selectExamenes").append(option);
            });
        },
        error: function (e) {
            
            if (e["responseJSON"] === undefined) {
                showToast("Error", "Inténtelo en otro momento", "error", "#D43721");
            } else {
                showToast(e["responseJSON"]["error"], "Error inesperado", "error", "#D43721");
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