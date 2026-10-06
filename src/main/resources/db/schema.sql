-- =====================================================================
--  Refugio de animales - Programación Web 2026/2027 - Práctica 1
--  Esquema relacional (MySQL 8.0.16+ / MariaDB 10.4+)
--
--  Los literales de los ENUM coinciden con los nombres de los enum Java
--  del paquete model.domain.enums, para mapear con Enum.valueOf(rs.getString(..)).
--  En MySQL un ENUM se ordena por su posición de declaración, por lo que
--  ORDER BY dia_semana devuelve los días en orden cronológico (L -> V).
-- =====================================================================

DROP TABLE IF EXISTS solicitud_dia_preferido;
DROP TABLE IF EXISTS solicitud_visita;
DROP TABLE IF EXISTS centro_educativo;
DROP TABLE IF EXISTS adopcion;
DROP TABLE IF EXISTS adoptante;
DROP TABLE IF EXISTS ingreso;
DROP TABLE IF EXISTS historial_clinico;
DROP TABLE IF EXISTS animal;
DROP TABLE IF EXISTS recinto;
DROP TABLE IF EXISTS disponibilidad_monitor;
DROP TABLE IF EXISTS trabajador;

-- ---------------------------------------------------------------------
--  A. Trabajadores
--  Herencia resuelta con tabla única + discriminador (categoria).
--  num_colegiado solo existe (y es obligatorio) para veterinarios.
-- ---------------------------------------------------------------------
CREATE TABLE trabajador (
    id                INT           NOT NULL AUTO_INCREMENT,
    nombre            VARCHAR(50)   NOT NULL,
    apellidos         VARCHAR(100)  NOT NULL,
    dni               CHAR(9)       NOT NULL,
    fecha_nacimiento  DATE          NOT NULL,
    direccion         VARCHAR(150)  NOT NULL,
    fecha_alta        DATE          NOT NULL,
    categoria         ENUM('ADMINISTRATIVO','MONITOR','VETERINARIO') NOT NULL,
    num_colegiado     VARCHAR(20)   NULL,
    CONSTRAINT pk_trabajador           PRIMARY KEY (id),
    CONSTRAINT uq_trabajador_dni       UNIQUE (dni),
    CONSTRAINT uq_trabajador_colegiado UNIQUE (num_colegiado),
    CONSTRAINT ck_trabajador_colegiado CHECK (
           (categoria =  'VETERINARIO' AND num_colegiado IS NOT NULL)
        OR (categoria <> 'VETERINARIO' AND num_colegiado IS NULL)
    ),
    CONSTRAINT ck_trabajador_fechas    CHECK (fecha_alta > fecha_nacimiento)
) ENGINE = InnoDB;

-- Atributo multivaluado "días disponibles" del monitor (solo L-V).
CREATE TABLE disponibilidad_monitor (
    id_monitor  INT  NOT NULL,
    dia_semana  ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES') NOT NULL,
    CONSTRAINT pk_disponibilidad_monitor PRIMARY KEY (id_monitor, dia_semana),
    CONSTRAINT fk_disponibilidad_monitor FOREIGN KEY (id_monitor)
        REFERENCES trabajador (id) ON DELETE CASCADE
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------
--  B. Animales
-- ---------------------------------------------------------------------
-- El hospital NO es un recinto: estar en el hospital equivale a tener
-- un ingreso abierto (fecha_salida IS NULL). La capacidad actual se
-- calcula con COUNT, no se almacena.
CREATE TABLE recinto (
    id                INT           NOT NULL AUTO_INCREMENT,
    nombre            VARCHAR(50)   NOT NULL,
    ubicacion         VARCHAR(100)  NOT NULL,
    capacidad_maxima  INT           NOT NULL,
    tipo_animal       ENUM('PERRO','GATO','CONEJO','HURON','AVE','OTRO') NOT NULL,
    CONSTRAINT pk_recinto           PRIMARY KEY (id),
    CONSTRAINT uq_recinto_nombre    UNIQUE (nombre),
    CONSTRAINT ck_recinto_capacidad CHECK (capacidad_maxima > 0)
) ENGINE = InnoDB;

-- Datos administrativos del animal. id_recinto = recinto donde está
-- físicamente; NULL si está en el hospital, adoptado o fallecido.
CREATE TABLE animal (
    codigo_registro      CHAR(15)     NOT NULL,
    especie              ENUM('PERRO','GATO','CONEJO','HURON','AVE','OTRO') NOT NULL,
    raza                 VARCHAR(50)  NULL,
    nombre               VARCHAR(50)  NOT NULL,
    edad                 INT          NOT NULL,
    estado_salud         ENUM('SANO','REQUIERE_ATENCION') NOT NULL,
    fecha_llegada        DATE         NOT NULL,
    fecha_fallecimiento  DATE         NULL,
    id_recinto           INT          NULL,
    CONSTRAINT pk_animal               PRIMARY KEY (codigo_registro),
    CONSTRAINT fk_animal_recinto       FOREIGN KEY (id_recinto) REFERENCES recinto (id),
    CONSTRAINT ck_animal_codigo        CHECK (CHAR_LENGTH(codigo_registro) = 15),
    CONSTRAINT ck_animal_edad          CHECK (edad >= 0),
    -- Un animal que requiere atención veterinaria solo puede estar en el hospital
    CONSTRAINT ck_animal_hospital      CHECK (estado_salud = 'SANO' OR id_recinto IS NULL),
    CONSTRAINT ck_animal_fallecimiento CHECK (fecha_fallecimiento IS NULL
                                              OR fecha_fallecimiento >= fecha_llegada)
) ENGINE = InnoDB;

-- Historial clínico (1:1 con animal). El veterinario es siempre el mismo.
CREATE TABLE historial_clinico (
    id              INT       NOT NULL AUTO_INCREMENT,
    codigo_animal   CHAR(15)  NOT NULL,
    id_veterinario  INT       NOT NULL,
    CONSTRAINT pk_historial_clinico PRIMARY KEY (id),
    CONSTRAINT uq_historial_animal  UNIQUE (codigo_animal),
    CONSTRAINT fk_historial_animal  FOREIGN KEY (codigo_animal)  REFERENCES animal (codigo_registro),
    CONSTRAINT fk_historial_vet     FOREIGN KEY (id_veterinario) REFERENCES trabajador (id)
) ENGINE = InnoDB;

-- Cada estancia en el hospital. fecha_salida NULL = ingreso abierto.
CREATE TABLE ingreso (
    id             INT           NOT NULL AUTO_INCREMENT,
    id_historial   INT           NOT NULL,
    fecha_entrada  DATE          NOT NULL,
    fecha_salida   DATE          NULL,
    medicacion     VARCHAR(500)  NULL,
    CONSTRAINT pk_ingreso           PRIMARY KEY (id),
    CONSTRAINT fk_ingreso_historial FOREIGN KEY (id_historial)
        REFERENCES historial_clinico (id) ON DELETE CASCADE,
    CONSTRAINT ck_ingreso_fechas    CHECK (fecha_salida IS NULL OR fecha_salida >= fecha_entrada)
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------
--  C. Adopciones
-- ---------------------------------------------------------------------
CREATE TABLE adoptante (
    id                INT           NOT NULL AUTO_INCREMENT,
    nombre            VARCHAR(50)   NOT NULL,
    apellidos         VARCHAR(100)  NOT NULL,
    dni               CHAR(9)       NOT NULL,
    direccion         VARCHAR(150)  NOT NULL,
    fecha_nacimiento  DATE          NOT NULL,
    telefono          VARCHAR(15)   NOT NULL,
    CONSTRAINT pk_adoptante     PRIMARY KEY (id),
    CONSTRAINT uq_adoptante_dni UNIQUE (dni)
) ENGINE = InnoDB;

-- codigo_animal UNIQUE: no hay devoluciones, un animal se adopta una sola vez.
-- 'activa' es redundante con animal.fecha_fallecimiento, pero el enunciado
-- pide almacenarla; ambas se actualizan en la misma transacción.
CREATE TABLE adopcion (
    id                 INT       NOT NULL AUTO_INCREMENT,
    codigo_animal      CHAR(15)  NOT NULL,
    id_adoptante       INT       NOT NULL,
    id_administrativo  INT       NOT NULL,
    fecha_adopcion     DATE      NOT NULL,
    activa             BOOLEAN   NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_adopcion                PRIMARY KEY (id),
    CONSTRAINT uq_adopcion_animal         UNIQUE (codigo_animal),
    CONSTRAINT fk_adopcion_animal         FOREIGN KEY (codigo_animal)     REFERENCES animal (codigo_registro),
    CONSTRAINT fk_adopcion_adoptante      FOREIGN KEY (id_adoptante)      REFERENCES adoptante (id),
    CONSTRAINT fk_adopcion_administrativo FOREIGN KEY (id_administrativo) REFERENCES trabajador (id)
) ENGINE = InnoDB;

-- ---------------------------------------------------------------------
--  D. Visitas escolares
-- ---------------------------------------------------------------------
CREATE TABLE centro_educativo (
    codigo_centro       CHAR(8)       NOT NULL,
    nombre              VARCHAR(100)  NOT NULL,
    direccion           VARCHAR(150)  NOT NULL,
    nombre_profesor     VARCHAR(50)   NOT NULL,
    apellidos_profesor  VARCHAR(100)  NOT NULL,
    email_profesor      VARCHAR(100)  NOT NULL,
    CONSTRAINT pk_centro_educativo PRIMARY KEY (codigo_centro),
    CONSTRAINT ck_centro_codigo    CHECK (codigo_centro REGEXP '^[A-Za-z0-9]{8}$')
) ENGINE = InnoDB;

-- PENDIENTE: sin monitor ni día. ASIGNADA/FINALIZADA: con monitor y día.
CREATE TABLE solicitud_visita (
    id               INT       NOT NULL AUTO_INCREMENT,
    codigo_centro    CHAR(8)   NOT NULL,
    fecha_solicitud  DATE      NOT NULL,
    num_estudiantes  INT       NOT NULL,
    nivel_educativo  ENUM('INFANTIL','PRIMARIA','SECUNDARIA') NOT NULL,
    estado           ENUM('PENDIENTE','ASIGNADA','FINALIZADA') NOT NULL DEFAULT 'PENDIENTE',
    id_monitor       INT       NULL,
    dia_asignado     ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES') NULL,
    CONSTRAINT pk_solicitud_visita    PRIMARY KEY (id),
    CONSTRAINT fk_solicitud_centro    FOREIGN KEY (codigo_centro) REFERENCES centro_educativo (codigo_centro),
    CONSTRAINT fk_solicitud_monitor   FOREIGN KEY (id_monitor)    REFERENCES trabajador (id),
    CONSTRAINT ck_solicitud_estudiantes CHECK (num_estudiantes > 0),
    CONSTRAINT ck_solicitud_asignacion CHECK (
           (estado =  'PENDIENTE' AND id_monitor IS NULL     AND dia_asignado IS NULL)
        OR (estado <> 'PENDIENTE' AND id_monitor IS NOT NULL AND dia_asignado IS NOT NULL)
    )
) ENGINE = InnoDB;

-- Atributo multivaluado "días preferibles" de la solicitud (al menos uno: lógica de negocio).
CREATE TABLE solicitud_dia_preferido (
    id_solicitud  INT  NOT NULL,
    dia_semana    ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES') NOT NULL,
    CONSTRAINT pk_solicitud_dia_preferido PRIMARY KEY (id_solicitud, dia_semana),
    CONSTRAINT fk_dia_preferido_solicitud FOREIGN KEY (id_solicitud)
        REFERENCES solicitud_visita (id) ON DELETE CASCADE
) ENGINE = InnoDB;
