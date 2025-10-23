# Mental Health App

[![Kotlin](https://img.shields.io/badge/Kotlin-FFDD00?style=for-the-badge&logo=kotlin&logoColor=black)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetbrains&logoColor=white)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Aplicación de **salud mental** desarrollada en **Kotlin** para Android, siguiendo una arquitectura limpia (**Clean Architecture**) y modular.  
Permite a los usuarios registrar emociones, realizar ejercicios de respiración y meditación, llevar un diario y gestionar sesiones de terapia.

---

## Features

- Registro de emociones y análisis de patrones de ánimo
- Historial de estados de ánimo con visualización en gráficos
- Ejercicios de respiración y meditación guiados
- Agenda y seguimiento de sesiones de terapia
- Diario personal para reflexiones y notas
- Perfil personalizado y preferencias del usuario
- Notificaciones y recordatorios para hábitos saludables

---

## Estructura del proyecto

```bash
app/src/main/java/com/example/ingswproyect/
│
├── data/                 # Manejo de datos locales y remotos
│   ├── local/
│   │   ├── database/
│   │   │   └── dao/
│   │   └── preferences/
│   ├── remote/
│   │   ├── api/
│   │   └── dto/
│   └── repository/
│
├── domain/               # Modelo de negocios, repositorios, y casos de uso
│   ├── model/
│   ├── repository/
│   └── usecase/
│       ├── mood/
│       ├── therapy/
│       └── exercises/
│
├── presentation/         # UI, navegacion, temas, componentes
│   ├── navigation/
│   ├── screens/
│   │   ├── home/
│   │   ├── mood/
│   │   ├── exercises/
│   │   ├── therapy/
│   │   ├── journal/
│   │   └── profile/
│   ├── components/
│   └── theme/
│
├── di/                   # Inyeccion de dependencias
└── util/                 # Utilidades y validaciones, utilidades generales
    └── validators/
```
