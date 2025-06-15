// 1. Obtener el token de la URL
function getTokenFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('token');
}

// 2. Decodificar el JWT (sin verificar la firma)
function parseJwt(token) {
    if (!token) return null;

    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');

    try {
        const jsonPayload = decodeURIComponent(
            atob(base64).split('').map(c =>
                '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
            ).join('')
        );
        return JSON.parse(jsonPayload);
    } catch (e) {
        alert('Error al decodificar el token.');
        return null;
    }
}

// 3. Guardar token
const token = getTokenFromUrl();
if (token) {
    localStorage.setItem('auth_token', token);
    const payload = parseJwt(token);

    if (payload) {
        alert("✅ Token recibido y decodificado:");
        alert(JSON.stringify(payload, null, 2));
        document.getElementById('per').innerText = `${payload.sub}`;
    }
} else {
    alert("⚠️ No se encontró el token en la URL.")
    window.location.href = 'http://127.0.0.1:5500/index.html';
}

async function adminUser() {
    const url = `http://localhost:8085/api/v1/public/users/`;
  
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
          <td>${user.username}</td>
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
    const url = `http://localhost:8085/api/v1/public/users/${id}`;
  
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
      adminUser(); 
    })
    .catch(error => {
      alert("Error al eliminar el usuario:", error);
    });
    adminUser(); 
}     
document.addEventListener("DOMContentLoaded", function() {
    adminUser();
});
document.getElementById("cerrarSesion").addEventListener("click", function (e) {
    e.preventDefault();

    // Eliminar el token
    localStorage.removeItem("token");

    // Opcional: limpiar otros datos (nombre, roles, etc.)
    localStorage.clear();

    // Alerta de cierre de sesión
    alert("Sesión cerrada correctamente");

    // Redirigir a login o a la raíz
    window.location.href = "http://127.0.0.1:5500/index.html";
});


