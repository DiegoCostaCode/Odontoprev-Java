function toggleForms(type) {

    document.getElementById('clinicaForm').style.display = 'none';
    document.getElementById('pacienteForm').style.display = 'none';

    if (type === 'clinica') {
        document.getElementById('clinicaForm').style.display = 'block';
    } else if (type === 'paciente') {
        document.getElementById('pacienteForm').style.display = 'block';
    }
}