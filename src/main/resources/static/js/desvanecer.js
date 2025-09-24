  // Espera 5 segundos y luego oculta suavemente el mensaje
  setTimeout(() => {
    const mensajeExito = document.getElementById('mensaje');
    const mensajeError = document.getElementById('error');

    if (mensajeExito) {
      mensajeExito.classList.remove('show');
      mensajeExito.classList.add('fade');
      setTimeout(() => mensajeExito.remove(), 500); // lo remueve del DOM
    }

    if (mensajeError) {
      mensajeError.classList.remove('show');
      mensajeError.classList.add('fade');
      setTimeout(() => mensajeError.remove(), 500);
    }
  }, 3000); // tiempo en milisegundos

