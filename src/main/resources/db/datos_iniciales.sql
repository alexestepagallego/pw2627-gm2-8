-- =====================================================================
--  Datos iniciales. Los recintos no tienen funcionalidad de alta en el
--  enunciado, así que se precargan. Ajustad nombres y capacidades.
-- =====================================================================

INSERT INTO recinto (nombre, ubicacion, capacidad_maxima, tipo_animal) VALUES
    ('Perrera Norte',  'Pabellón A, planta baja', 20, 'PERRO'),
    ('Perrera Sur',    'Pabellón B, patio',       15, 'PERRO'),
    ('Gatera',         'Pabellón C, planta alta', 25, 'GATO'),
    ('Conejera',       'Pabellón C, planta baja', 10, 'CONEJO'),
    ('Hurones',        'Pabellón C, sala 2',       6, 'HURON'),
    ('Aviario',        'Exterior, zona este',     12, 'AVE'),
    ('Exóticos',       'Pabellón D',               8, 'OTRO');
