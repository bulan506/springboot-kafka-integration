


# 🔄 Información Compartida entre Servicios - GringottsBank 🏦

## 📋 Descripción

Este proyecto tiene como objetivo demostrar el funcionamiento de la compartición de información entre servicios utilizando **Apache Kafka** como sistema de mensajería. Está basado en un patrón **Producer-Consumer** donde los microservicios intercambian eventos para mantener sincronizados los datos.

El sistema simula un entorno bancario llamado **GringottsBank** y se enfoca en la comunicación efectiva entre servicios para compartir eventos de manera asíncrona y escalable.

## 🛠 Tecnologías utilizadas

- 🚀 **Spring Boot**: Framework para construir los microservicios REST.
- 🐳 **Docker**: Para contenerizar y orquestar servicios dependientes.
- 🎯 **Apache Kafka**: Sistema de mensajería para intercambio de eventos Producer-Consumer.
- 🍃 **MongoDB**: Base de datos NoSQL para persistencia de datos.
- 📊 **Grafana y Loki**: Para visualización y monitoreo centralizado de logs.

## ⚙️ Requisitos previos

- 🐳 Docker y Docker Compose instalados en el sistema.
- ☕ Java JDK 17+ para correr los proyectos Spring Boot localmente.
- 🐘 Kafka, Zookeeper y MongoDB serán levantados vía Docker Compose.

## 🚀 Instalación y ejecución

1. Clonar el repositorio:
   ```bash
   git clone <https://github.com/bulan506/investigacionIngenieria.git>
   cd comunicationProcess

2. Levantar los servicios de Kafka, Zookeeper y MongoDB usando Docker Compose:

   ```bash
   docker-compose up -d
   ```

3. Ejecutar cada microservicio por separado en su entorno local (por ejemplo, desde tu IDE o usando comandos Gradle):

   * 🟢 Producer Service (ClientDataService)
   * 🔵 Consumer Service (ConsumerDataService)
- Para ver el grafana y loki, abre el navegador y ingresa a la siguiente URL:
   ```bash
   http://localhost:3000
   ```
4. Monitorear los logs y métricas desde Grafana y Loki, configurados en el `docker-compose.yml`.

## 📂 Estructura del proyecto

* 🟢 **Producer Service (ClientDataService)**: Servicio encargado de publicar eventos en Kafka.
* 🔵 **Consumer Service (ConsumerDataService)**: Servicio encargado de consumir eventos y actualizar datos en MongoDB.
* 🐳 **docker-compose.yml**: Archivo para levantar Kafka, Zookeeper, MongoDB, Grafana y Loki.
* 📄 Documentación adicional con configuraciones específicas para Spring Boot y visualización.

## 🎯 Uso

El sistema simula la emisión y recepción de eventos relacionados con clientes bancarios. El Producer emite eventos como **"ClientSaved"** o **"AddressUpdated"**, que el Consumer procesa para mantener actualizada la información en su base de datos.

## ⚠️ Consideraciones

* No se requieren configuraciones adicionales para correr el proyecto, solo tener Docker y Java configurados correctamente.
* Los servicios pueden correr en paralelo y comunicarse vía Kafka sin dependencias directas.




