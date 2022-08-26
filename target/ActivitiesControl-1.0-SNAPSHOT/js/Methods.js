var $table = $('#table');
let idtoDel = 0;

function doPost(event, form) {
    fetch(form.action, {
        method: "POST",
        body: new FormData(form)
    }).then((response) => {
        if (response.ok) {
            alert("Actividad ingresada correctamente");
            $("#table").bootstrapTable("refresh");
        } else {
            alert("No se agregó ninguna actividad");
        }
    });
    event.preventDefault();
    document.getElementById('closeInsert').click();
}

function operations(value, row, index) {
    return [
        `<button type='button' 
            class='btn btn-warning actionBtn' 
            data-bs-toggle='modal' 
            data-bs-target='#upDateModal'
            onclick="getUpdateData(this)">
            Actualizar
    </button>
    <button 
            type='button' 
            class='btn btn-danger actionBtn'
             data-bs-toggle="modal" 
             data-bs-target="#deleteActivity"
            onclick="delButton(this)">
            Eliminar
    </button>`
    ];
}

const getUpdateData = (e) => {
  let updateData = e.parentElement.parentElement.getElementsByTagName("td");
  let uForm = document
    .getElementById("update")
    .getElementsByClassName("form-control");
  for (let i = 0; i < updateData.length - 1; i++) {
    uForm[i].value = updateData[i].innerHTML;
  }  
}

function doPut(event, form) {
    fetch(form.action, {
        method: "PUT",
        body: new FormData(form)
    }).then((response) => {
        if (response.ok) {
            alert("Actividad actualizada");
            $("#table").bootstrapTable("refresh");
        } else {
            alert("No se actualizó ninguna actividad");
        }
    });
    event.preventDefault();
    document.getElementById('CloseUp').click();
}

function doDelete(id) {
    fetch(`./ControlS?id=${id}`, {
        method: "DELETE"
    }).then((response) => {
        if (response.ok) {
            alert("Actividad Eliminada");
            $("#table").bootstrapTable("refresh");
        } else {
            alert("Actividad no eliminada");
        }
    });
    document.getElementById('closeDelete').click();
}

function delButton(e) {
    idtoDel = e.parentElement.parentElement.querySelector("td").innerHTML;
}