// value slider
const alturaSlider = document.getElementById('alturaSlider');
const pesoSlider = document.getElementById('pesoSlider');
// value txt
const alturaValor = document.getElementById('altura-valor');
const pesoValor = document.getElementById('peso-valor');
const resultado = document.getElementById('resultado');



// Escuchar cambios en los sliders
alturaSlider.addEventListener('input', actualizarValoresYCalcular);
pesoSlider.addEventListener('input', actualizarValoresYCalcular);

function actualizarValoresYCalcular() {
    const alturaCm = parseFloat(alturaSlider.value);
    const peso = parseFloat(pesoSlider.value);
    const alturaM = alturaCm / 100;

    alturaValor.textContent = alturaCm;
    pesoValor.textContent = peso;

    const imc = peso / (alturaM * alturaM);
    let categoria = '';
    let bgColor = '';

    if (imc < 18.5) {
        categoria = 'Bajo peso';
        bgColor = '#f57948';
    } else if (imc < 24.9) {
        categoria = 'Normal';
        bgColor = '#6cad6cff';
    } else if (imc < 30) {
        categoria = 'Sobrepeso';
        bgColor = '#daad44ff';
    } else if (imc < 35) {
        categoria = 'obesidad tipo 1';
        bgColor = '#f57948';
    } else if (imc < 40) {
        categoria = 'obesidad tipo 2';
        bgColor = '#fa5210';
    } else {
        categoria = 'Obesidad tipo 3';
        bgColor = '#ff0c0c';
    }

    resultado.className = 'custom-alert mt-4';
    resultado.innerHTML = `
      <strong>IMC:</strong> ${imc.toFixed(2)}<br>
      <strong>Categoría:</strong> ${categoria}
    `;
    resultado.style.backgroundColor = bgColor;
    resultado.classList.add('text-white');
    resultado.classList.remove('d-none');
}

// Calcular al cargar la página con los valores por defecto
actualizarValoresYCalcular();