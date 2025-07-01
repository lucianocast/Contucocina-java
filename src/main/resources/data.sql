-- Usuarios (cliente y administradora)
INSERT INTO usuario (id, nombre, email, password, rol)
VALUES 
  (1, 'María Pastelera', 'admin@contucocina.com', 'admin123', 'ADMIN'),
  (2, 'Juan Pérez', 'juan@gmail.com', 'cliente123', 'CLIENTE');

-- Productos visibles
INSERT INTO producto (id, nombre, descripcion, precio, visible)
VALUES 
  (1, 'Torta Selva Negra', 'Bizcochuelo de chocolate con crema y cerezas', 8500.00, true),
  (2, 'Tarta de Frutilla', 'Base de masa sablé, crema pastelera y frutillas frescas', 7500.00, true),
  (3, 'Combo x3 Mini Tartas', 'Elegí 3 variedades entre nuestras mini tartas', 9000.00, true);

-- Recetas privadas (solo para administración)
INSERT INTO receta (id, nombre, descripcion)
VALUES 
  (1, 'Receta Selva Negra', 'Bizcochuelo húmedo, relleno de crema batida y cerezas en almíbar'),
  (2, 'Receta Tarta Frutilla', 'Masa sablé con crema pastelera y frutas de estación');

-- Pedidos (realizados por el cliente)
INSERT INTO pedido (id, usuario_id, fecha, estado)
VALUES 
  (1, 2, CURRENT_DATE, 'CONFIRMADO'),
  (2, 2, CURRENT_DATE, 'PENDIENTE');
