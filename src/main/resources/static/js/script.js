function toggleForms(type) {

    document.getElementById('clinicaForm').style.display = 'none';
    document.getElementById('pacienteForm').style.display = 'none';

    if (type === 'clinica') {
        document.getElementById('clinicaForm').style.display = 'block';
    } else if (type === 'paciente') {
        document.getElementById('pacienteForm').style.display = 'block';
    }
}

function toggleSenha() {
    var senhaInput = document.getElementById('senha');
    var eyeIcon = document.getElementById('eyeIcon');

    if (senhaInput.type === "text") {
        senhaInput.type = "password";
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
    } else {
        senhaInput.type = "text";
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
    }
}