-- =====================================================================
--  Refugio de animales - Datos iniciales de ejemplo
--  Ejecutar después de schema.sql (sobre las tablas recién creadas).
--
--  Datos ficticios pensados para que cada funcionalidad tenga algo que
--  mostrar y cumplan también las reglas de negocio que comprueba el
--  código (mayoría de edad, cobertura de monitores, máx. 5 adopciones...).
--  Los DNI tienen letra de control válida. Fecha de referencia: 06/10/2026.
--
--  Escenarios preparados para probar:
--   A.2  Sergio (monitor 8) acaba de incorporarse y no tiene días. El
--        JUEVES no tiene ningún monitor: es el día vacante que debe
--        ofrecerse primero.
--   A.3  Fechas de alta distintas para probar el orden de más reciente a
--        más antiguo.
--   B.3  Recintos con varios animales, Hurones vacío y Exóticos lleno (2/2).
--   B.4  Tres animales en el hospital (Canela, Max, Thor) con ingresos en
--        fechas distintas. Max llegó sano y enfermó durante su estancia.
--   B.5  Eva lleva a Thor, Max y Rayo (fallecido). Pablo lleva a Luna,
--        Simba (dos ingresos) y Canela.
--   C.1  Mario tiene 5 adopciones activas, así que no puede adoptar más.
--        Si se notifica el fallecimiento de uno de sus animales, podrá
--        adoptar otro.
--   C.3  Adopciones en 2025 y 2026, de perros, gatos, conejos y aves.
--   C.4  Laura tiene una adopción activa y otra concluida (Chispa falleció).
--   D.3  Solicitud 5: se asigna a Marta el viernes.
--        Solicitud 6: no hay monitor libre ni el lunes ni el martes.
--        Solicitud 7: nadie trabaja los jueves hasta que Sergio cubra ese día.
-- =====================================================================

-- Garantiza que las tildes y la ñ se envían en UTF-8 sea cual sea el cliente.
SET NAMES utf8mb4;

-- ---------------------------------------------------------------------
--  A. Trabajadores
-- ---------------------------------------------------------------------
INSERT INTO trabajador (id, nombre, apellidos, dni, fecha_nacimiento, direccion, fecha_alta, categoria, num_colegiado) VALUES
    (1, 'Ana',    'Ruiz Molina',     '30561234F', '1988-04-12', 'C/ Gondomar 8, 14003 Córdoba',          '2024-01-15', 'ADMINISTRATIVO', NULL),
    (2, 'Javier', 'Ortega Luque',    '44378125Q', '1995-11-03', 'Av. de América 21, 14008 Córdoba',      '2025-09-01', 'ADMINISTRATIVO', NULL),
    (3, 'Eva',    'Gil Navarro',     '30987456Q', '1982-07-25', 'C/ Cruz Conde 14, 14001 Córdoba',       '2023-04-10', 'VETERINARIO',    '14-1234'),
    (4, 'Pablo',  'Castro Jiménez',  '45123789C', '1991-02-14', 'C/ Alfonso XIII 5, 14001 Córdoba',      '2026-03-02', 'VETERINARIO',    '14-2087'),
    (5, 'Lucía',  'Romero Prieto',   '31245678V', '1993-09-30', 'C/ San Pablo 3, 14002 Córdoba',         '2024-09-02', 'MONITOR',        NULL),
    (6, 'Carlos', 'Muñoz Serrano',   '80123456N', '1990-06-18', 'Av. del Brillante 40, 14012 Córdoba',   '2025-01-20', 'MONITOR',        NULL),
    (7, 'Marta',  'Vega Delgado',    '46789012C', '1999-12-05', 'C/ Doctor Fleming 22, 14004 Córdoba',   '2025-10-01', 'MONITOR',        NULL),
    (8, 'Sergio', 'Torres Ramos',    '30111222J', '2001-03-22', 'C/ Pintor Espinosa 9, 14004 Córdoba',   '2026-10-05', 'MONITOR',        NULL);

-- Lunes a viernes cubiertos salvo el jueves. Ningún día tiene dos
-- monitores, porque la regla A.2.d no lo permite mientras quede alguno vacante.
INSERT INTO disponibilidad_monitor (id_monitor, dia_semana) VALUES
    (5, 'LUNES'),
    (5, 'MIERCOLES'),
    (6, 'MARTES'),
    (7, 'VIERNES');

-- ---------------------------------------------------------------------
--  B. Animales
-- ---------------------------------------------------------------------
INSERT INTO recinto (id, nombre, ubicacion, capacidad_maxima, tipo_animal) VALUES
    (1, 'Perrera Norte', 'Pabellón A, planta baja', 20, 'PERRO'),
    (2, 'Perrera Sur',   'Pabellón B, patio',       15, 'PERRO'),
    (3, 'Gatera',        'Pabellón C, planta alta', 25, 'GATO'),
    (4, 'Conejera',      'Pabellón C, planta baja', 10, 'CONEJO'),
    (5, 'Hurones',       'Pabellón C, sala 2',       6, 'HURON'),
    (6, 'Aviario',       'Exterior, zona este',     12, 'AVE'),
    (7, 'Exóticos',      'Pabellón D',               2, 'OTRO');

INSERT INTO animal (codigo_registro, especie, raza, nombre, edad, estado_salud, fecha_llegada, fecha_fallecimiento, id_recinto) VALUES
    -- Alojados en recintos
    ('724098100000001', 'PERRO',  'Podenco',              'Toby',     3, 'SANO', '2026-02-10', NULL, 1),
    ('724098100000002', 'PERRO',  'Mestizo',              'Luna',     2, 'SANO', '2026-07-20', NULL, 1),
    ('724098100000003', 'PERRO',  'Galgo español',        'Rocky',    5, 'SANO', '2026-05-04', NULL, 1),
    ('724098100000004', 'PERRO',  'Pastor alemán',        'Bruno',    7, 'SANO', '2026-01-18', NULL, 2),
    ('724098100000005', 'PERRO',  'Mestizo',              'Kira',     1, 'SANO', '2026-09-09', NULL, 2),
    ('724098100000006', 'GATO',   'Común europeo',        'Misi',     4, 'SANO', '2026-03-12', NULL, 3),
    ('724098100000007', 'GATO',   'Siamés',               'Simba',    6, 'SANO', '2026-04-15', NULL, 3),
    ('724098100000008', 'GATO',   NULL,                   'Nala',     0, 'SANO', '2026-09-20', NULL, 3),
    ('724098100000009', 'CONEJO', 'Belier',               'Copito',   2, 'SANO', '2026-06-01', NULL, 4),
    ('724098100000010', 'AVE',    'Agapornis',            'Kiwi',     1, 'SANO', '2026-08-14', NULL, 6),
    ('724098100000011', 'OTRO',   'Tortuga mediterránea', 'Manolita',12, 'SANO', '2026-04-02', NULL, 7),
    ('724098100000012', 'OTRO',   'Iguana verde',         'Verdi',    4, 'SANO', '2026-07-07', NULL, 7),
    -- En el hospital (ingreso abierto, sin recinto)
    ('724098100000013', 'PERRO',  'Mestizo',              'Thor',     4, 'REQUIERE_ATENCION', '2026-09-28', NULL, NULL),
    ('724098100000014', 'PERRO',  'Bodeguero andaluz',    'Max',      8, 'REQUIERE_ATENCION', '2026-06-12', NULL, NULL),
    ('724098100000015', 'GATO',   'Común europeo',        'Canela',   2, 'REQUIERE_ATENCION', '2026-10-03', NULL, NULL),
    -- Falleció en el hospital sin llegar a ser adoptado
    ('724098100000016', 'PERRO',  'Mestizo',              'Rayo',    10, 'REQUIERE_ATENCION', '2026-07-01', '2026-07-05', NULL),
    -- Adoptados (ya no están en el refugio)
    ('724098100000017', 'PERRO',  'Beagle',               'Coco',     3, 'SANO', '2025-01-20', NULL, NULL),
    ('724098100000018', 'CONEJO', NULL,                   'Nube',     1, 'SANO', '2025-05-10', NULL, NULL),
    ('724098100000019', 'AVE',    'Periquito',            'Pipo',     1, 'SANO', '2025-08-01', NULL, NULL),
    ('724098100000020', 'PERRO',  'Mestizo',              'Bimba',    2, 'SANO', '2025-12-03', NULL, NULL),
    ('724098100000021', 'GATO',   'Común europeo',        'Tom',      3, 'SANO', '2026-02-20', NULL, NULL),
    ('724098100000022', 'GATO',   'Persa',                'Chispa',  11, 'SANO', '2025-09-15', '2026-07-15', NULL),
    ('724098100000023', 'GATO',   'Común europeo',        'Lola',     1, 'SANO', '2026-03-30', NULL, NULL),
    ('724098100000024', 'PERRO',  'Labrador',             'Rex',      5, 'SANO', '2026-08-05', NULL, NULL);

-- Un historial por animal que ha pasado por el hospital, siempre con el mismo veterinario.
INSERT INTO historial_clinico (id, codigo_animal, id_veterinario) VALUES
    (1, '724098100000002', 4),   -- Luna   -> Pablo
    (2, '724098100000007', 4),   -- Simba  -> Pablo
    (3, '724098100000013', 3),   -- Thor   -> Eva
    (4, '724098100000014', 3),   -- Max    -> Eva
    (5, '724098100000015', 4),   -- Canela -> Pablo
    (6, '724098100000016', 3);   -- Rayo   -> Eva

INSERT INTO ingreso (id, id_historial, fecha_entrada, fecha_salida, medicacion) VALUES
    (1, 2, '2026-05-02', '2026-05-09', 'Amoxicilina 20 mg/kg cada 12 h durante 7 días'),
    (2, 6, '2026-07-01', '2026-07-05', 'Fluidoterapia intravenosa y analgesia con buprenorfina'),
    (3, 1, '2026-08-10', '2026-08-20', 'Doxiciclina 10 mg/kg cada 24 h y antiparasitario interno'),
    (4, 2, '2026-09-02', '2026-09-12', 'Prednisolona 1 mg/kg cada 24 h en pauta descendente'),
    (5, 3, '2026-09-28', NULL,         'Meloxicam 0,1 mg/kg cada 24 h y curas diarias de la herida'),
    (6, 4, '2026-10-01', NULL,         'Metronidazol 15 mg/kg cada 12 h y dieta gastrointestinal'),
    (7, 5, '2026-10-03', NULL,         'Colirio de tobramicina, 1 gota cada 8 h');

-- ---------------------------------------------------------------------
--  C. Adopciones
-- ---------------------------------------------------------------------
INSERT INTO adoptante (id, nombre, apellidos, dni, direccion, fecha_nacimiento, telefono) VALUES
    (1, 'Mario',  'Sanz Ortiz',    '30222333B', 'C/ Claudio Marcelo 6, 14002 Córdoba', '1979-05-17', '611234567'),
    (2, 'Laura',  'Medina Castro', '45666777R', 'Av. Gran Capitán 30, 14006 Córdoba',  '1986-10-02', '622345678'),
    (3, 'Andrés', 'Navas León',    '31888999M', 'C/ Sevilla 11, 14003 Córdoba',        '2004-01-09', '633456789');

-- Javier (administrativo 2) solo tramita adopciones desde su alta (01/09/2025).
INSERT INTO adopcion (id, codigo_animal, id_adoptante, id_administrativo, fecha_adopcion, activa) VALUES
    (1, '724098100000017', 1, 1, '2025-03-14', TRUE),    -- Coco   -> Mario
    (2, '724098100000018', 1, 1, '2025-06-02', TRUE),    -- Nube   -> Mario
    (3, '724098100000019', 1, 2, '2025-09-20', TRUE),    -- Pipo   -> Mario
    (4, '724098100000022', 2, 2, '2025-11-05', FALSE),   -- Chispa -> Laura (falleció el 15/07/2026)
    (5, '724098100000020', 1, 2, '2026-02-11', TRUE),    -- Bimba  -> Mario
    (6, '724098100000021', 1, 1, '2026-04-08', TRUE),    -- Tom    -> Mario (5.ª activa: límite)
    (7, '724098100000023', 2, 1, '2026-05-21', TRUE),    -- Lola   -> Laura
    (8, '724098100000024', 3, 2, '2026-09-15', TRUE);    -- Rex    -> Andrés

-- ---------------------------------------------------------------------
--  D. Visitas escolares
-- ---------------------------------------------------------------------
INSERT INTO centro_educativo (codigo_centro, nombre, direccion, nombre_profesor) VALUES
    ('14000101', 'CEIP Los Olivos',    'C/ Los Olivos 2, 14005 Córdoba',            'Rosa'  ),
    ('14000202', 'IES Sierra Morena',  'Av. de la Arruzafa 5, 14012 Córdoba',       'Antonio'),
    ('14000303', 'CEIP Valle Verde',   'C/ Escritor Conde Zamora 7, 14011 Córdoba', 'Carmen'),
    ('14000404', 'Colegio San Rafael', 'C/ Arcos de la Frontera 3, 14014 Córdoba',  'Javier');

INSERT INTO solicitud_visita (id, codigo_centro, fecha_solicitud, num_estudiantes, nivel_educativo, estado, id_monitor, dia_asignado) VALUES
    (1, '14000101', '2026-09-21', 25, 'PRIMARIA',   'FINALIZADA', 5,    'LUNES'),
    (2, '14000202', '2026-09-28', 30, 'SECUNDARIA', 'ASIGNADA',   5,    'LUNES'),      -- Lucía libre el lunes tras finalizar la 1
    (3, '14000303', '2026-09-29', 18, 'INFANTIL',   'ASIGNADA',   5,    'MIERCOLES'),  -- lunes ocupado: pasa al miércoles
    (4, '14000101', '2026-09-30', 22, 'PRIMARIA',   'ASIGNADA',   6,    'MARTES'),
    (5, '14000404', '2026-10-02', 20, 'PRIMARIA',   'PENDIENTE',  NULL, NULL),         -- asignable: Marta el viernes
    (6, '14000202', '2026-10-05', 28, 'SECUNDARIA', 'PENDIENTE',  NULL, NULL),         -- sin monitor libre (L y M ocupados)
    (7, '14000303', '2026-10-05', 15, 'INFANTIL',   'PENDIENTE',  NULL, NULL);         -- nadie cubre el jueves

INSERT INTO solicitud_dia_preferido (id_solicitud, dia_semana) VALUES
    (1, 'LUNES'), (1, 'MARTES'),
    (2, 'LUNES'),
    (3, 'LUNES'), (3, 'MIERCOLES'),
    (4, 'MARTES'), (4, 'JUEVES'),
    (5, 'VIERNES'),
    (6, 'LUNES'), (6, 'MARTES'),
    (7, 'JUEVES');
