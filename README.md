# TVtestQR - Aplicación Android TV para Inicio de Sesión con QR

## Descripción

TVtestQR es una aplicación en desarrollo para dispositivos Android TV que facilita a los usuarios el inicio de sesión mediante un código QR. El proyecto está en constante evolución y este documento se actualizará a medida que se agreguen nuevas funcionalidades y mejoras.

## Funcionalidades

- **Inicio de Sesión con QR**: Los usuarios pueden iniciar sesión rápidamente escaneando un código QR generado en la pantalla de su televisor con la cámara de su dispositivo móvil.
- **Actualización periódica del QR**: El código QR se actualiza cada 15 segundos para garantizar la seguridad y la singularidad durante el proceso de inicio de sesión.
- **Inicio de Sesión Manual**: Para los usuarios que prefieren un método tradicional, ofrecemos la opción de iniciar sesión manualmente ingresando correo electrónico y contraseña. (Pendiente)

## Pantallas del Proyecto

A continuación, se presentan algunas capturas de pantalla de la interfaz de usuario de TVtestQR:

### Pantalla de Inicio de Sesión con QR
![Pantalla de Inicio de Sesión con QR](screenshots/login_screen1.png)
![Pantalla de Inicio de Sesión con QR - Actualización](screenshots/login_screen2.png)

## Cómo Funciona

### Generación y Actualización del Código QR

El proceso comienza en la `MainActivity`, donde se genera y se muestra un código QR junto con un código alfanumérico en la pantalla del televisor. Un `Handler` actualiza ambos cada 15 segundos.

### Inicio de Sesión Manual

Al seleccionar "Prefiero otra opción para conectarme", el usuario es dirigido a una pantalla donde puede ingresar sus credenciales de forma manual.(Pendiente)

## Desarrollo

### Configuraciones de Compilación

El proyecto se compila con el SDK 34 y está configurado para admitir desde la versión 21 de Android (Lollipop) en adelante.

### Dependencias

- ZXing para la generación de códigos QR.
- Glide para la gestión de imágenes.
- Leanback para la interfaz de usuario optimizada para TV.

## Referencias

- [Documentación oficial de Android TV](https://developer.android.com/tv)
- [Guía para la generación de códigos QR con ZXing](https://github.com/journeyapps/zxing-android-embedded)
- [Documentación de Glide](https://bumptech.github.io/glide/)


