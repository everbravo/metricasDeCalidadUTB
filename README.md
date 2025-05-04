# Automatización de Pruebas Funcionales para Mesfix

## Descripción del Proyecto

Este repositorio contiene el código y los scripts necesarios para la **automatización de pruebas funcionales** para la plataforma web *Mesfix*. El proyecto fue desarrollado como parte de la **Actividad Evaluativa 2** de la **Especialización en Ingeniería de Software** de la UTB Virtual, con el objetivo de mejorar la eficiencia en la validación de funcionalidades críticas, reducir el tiempo de ejecución de las pruebas manuales y aumentar la cobertura de testing.

## Objetivo

El principal objetivo de este proyecto es optimizar el proceso de pruebas funcionales, centrándose en:

- **Automatización de pruebas** utilizando **Java 17**, **Gradle** y **Selenium**.
- **Optimización y priorización de casos de prueba** según su impacto en el sistema.
- **Mejorar la eficiencia en la validación de funcionalidades críticas** para asegurar la estabilidad y confiabilidad de la plataforma.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal para la creación de los scripts de prueba.
- **Gradle**: Herramienta de automatización para la gestión de dependencias y tareas del proyecto.
- **Selenium**: Framework para la automatización de pruebas funcionales en la web.
- **JUnit**: Framework de testing utilizado para la ejecución de las pruebas.
- **WebDriver Manager**: Gestión automática de controladores de navegador.
- **Lombok**: Reducción de código boilerplate en Java (getters, setters, etc.).
- **OkHttp**: Librería para realizar peticiones HTTP.
- **Data Faker**: Generación de datos falsos para pruebas.

## Aviso Importante

Debido al uso de automatización en la resolución del **reCAPTCHA**, le solicitamos amablemente su espera. El proceso no debería tardar más de un minuto en completarse. 
**No es necesaria la intervención manual**, ya que cualquier acción manual podría interferir con el resultado de la prueba.
Agradecemos su comprensión y paciencia.

## ADVERTENCA

Es **importante** que los tests sean ejecutados de forma **individual** debido al comportamiento reactivo del sitio web *Mesfix*. Este comportamiento hace que sea difícil controlar de manera precisa la interacción con los elementos de la página (botones, checkboxes, radio buttons, etc.), ya que pueden presentar variabilidad en su estado y respuesta. 
Además, **la velocidad de carga del sitio** puede influir significativamente en los resultados de las pruebas. Dependiendo de factores como la conectividad o el tiempo de respuesta del servidor, es posible que se generen errores o se añada "ruido" en los resultados, lo que puede afectar la fiabilidad de las pruebas.
Es también fundamental ser **paciente** durante la **resolución dinámica del reCAPTCHA**. Este proceso puede tardar hasta un minuto y **no es necesaria la intervención manual**. Cualquier intento de intervención manual podría alterar el flujo de la prueba y afectar su validez.
Finalmente, es importante tener en cuenta que los casos de prueba fueron aplicados sobre un **sitio web existente** sobre el cual no tenemos control, lo que puede contribuir a comportamientos imprevistos durante la ejecución de las pruebas.

Agradecemos su comprensión y paciencia en todo el proceso.

## Proceso de Implementación

El proceso de automatización siguió las siguientes etapas:

1. **Revisión y Optimización de Casos de Prueba**: Se realizó una auditoría de los casos de prueba existentes, mejorando su estructura y alineándolos con los cambios recientes en la plataforma.
   
2. **Priorización de Casos de Prueba**: Los casos fueron priorizados según su relevancia e impacto en las funcionalidades críticas de la plataforma, asegurando que las pruebas más importantes fueran las primeras en ser automatizadas.
   
3. **Automatización**: Se implementó la automatización utilizando Selenium y las herramientas mencionadas, lo que permitió crear un entorno de pruebas eficiente y escalable.

## Contribuciones

Este proyecto está abierto a contribuciones. Si tienes ideas para mejorar el proceso de automatización, agregar nuevos casos de prueba o mejorar la cobertura, no dudes en realizar un **pull request**. También puedes abrir un **issue** si encuentras algún problema o tienes alguna sugerencia.

Para contribuir:

1. Haz un fork de este repositorio.
2. Crea una rama con tu cambio (`git checkout -b feature-nueva-prueba`).
3. Realiza los cambios y haz commit.
4. Envía un pull request describiendo tu contribución.

## Autores

- **Nelson Flórez De Ávila**
- **Ever Bravo Vergara**
- **Nicolás Olarte Sandoval**

## Docente

- **Juan Carlos Martínez**

## Fecha

**Abril 2025**

---

¡Gracias por tu interés en este proyecto de automatización de pruebas! Si tienes preguntas o sugerencias, no dudes en ponerte en contacto.


