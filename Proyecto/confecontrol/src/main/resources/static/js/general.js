
function actualizarHora() {
    const ahora = new Date();
    const horas = String(ahora.getHours()).padStart(2, '0'); // Obtiene las horas y añade un 0 al inicio si es necesario
    const minutos = String(ahora.getMinutes()).padStart(2, '0'); // Obtiene los minutos
    const segundos = String(ahora.getSeconds()).padStart(2, '0'); // Obtiene los segundos

    document.getElementById('horaActual').textContent = `${horas}:${minutos}:${segundos}`;
}
setInterval(actualizarHora, 1000);




const currentPath = window.location.pathname;
const navLinks = document.querySelectorAll('.sidebar .nav-link');
navLinks.forEach(link => {
  // Si el enlace tiene un href que coincide con la URL actual, añades la clase 'active'
  if (link.getAttribute('href') === currentPath) {
    link.classList.add('active');
  } else {
    link.classList.remove('active');
  }
});
