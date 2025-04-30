async function adminUser() {
    const url = `http://localhost:8085/api/v1/user/`;
  
    let headersList = {
      "Accept": "*/*",
      "User-Agent": "web",
      "Content-Type": "application/json"
    };
  
    try {
      const response = await fetch(url, {
        method: "GET",
        headers: headersList
      });
  
      if (!response.ok) {
        console.error("Error al obtener el detalle de los usuarios:", response.status);
        return;
      }
  
      const user = await response.json();
      console.log(user);
      const tbody = document.getElementById("userList");
      tbody.innerHTML = ""; 
      user.forEach(user => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${user._name}</td>
          <td>${user.email}</td>
          <td>${user._registration_date}</td>
          <td>
            <button class="btn btn-sm btn-danger" onclick="eliminarUsuario(${user.id_user})" >Banear cuenta</button>
          </td>

        `;
        tbody.appendChild(row);
      });
    } catch (error) {
      alert("Error al obtener los detalles de la reserva:", error);
    }
}
function eliminarUsuario(id) {
    const url = `http://localhost:8085/api/v1/user/${id}`;
  
    let headersList = {
      "Accept": "*/*",
      "User-Agent": "web",
      "Content-Type": "application/json"
    };
  
    fetch(url, {
      method: "DELETE",
      headers: headersList
    })
    .then(response => {
      if (!response.ok) {
        console.error("Error al eliminar el usuario:", response.status);
        return;
      }
      alert("Usuario eliminado con éxito");
      adminUser(); // Actualizar la lista de usuarios después de eliminar
    })
    .catch(error => {
      alert("Error al eliminar el usuario:", error);
    });
    adminUser(); // Actualizar la lista de usuarios después de eliminar
}     
document.addEventListener("DOMContentLoaded", function() {
    adminUser();
});