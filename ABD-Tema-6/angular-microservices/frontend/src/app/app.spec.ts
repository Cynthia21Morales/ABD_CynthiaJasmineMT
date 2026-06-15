import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  showSupport() {

    alert(`
SOPORTE

Instituto Tecnológico de Frontera Comalapa

Correo:
soporte@itfc.edu.mx

Horario:
Lunes a Viernes
08:00 AM - 05:00 PM
`);

  }

  showAbout() {

    alert(`
SISTEMA INTEGRAL DE GESTIÓN ACADÉMICA

Versión 1.0

Desarrollado para la administración de:

• Estudiantes
• Profesores
• Cursos

Instituto Tecnológico de Frontera Comalapa
`);

  }

}