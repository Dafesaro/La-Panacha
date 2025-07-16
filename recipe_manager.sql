-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-05-2025 a las 19:18:22
-- Versión del servidor: 8.0.41
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestor_recetas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_ingrediente`
--

CREATE TABLE `categoria_ingrediente` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria_ingrediente`
--

INSERT INTO `categoria_ingrediente` (`id`, `nombre`) VALUES
(5, 'Bebidas alcohólicas'),
(2, 'Carnes, huevos y embutidos'),
(1, 'Especias y condimentos'),
(3, 'Frutas y verduras'),
(7, 'Granos y cereales'),
(4, 'Lácteos y grasas'),
(8, 'Otros'),
(6, 'Panadería y repostería');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_receta`
--

CREATE TABLE `categoria_receta` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria_receta`
--

INSERT INTO `categoria_receta` (`id`, `nombre`) VALUES
(4, 'Bebidas'),
(1, 'Panadería'),
(2, 'Platos fuertes'),
(3, 'Postres');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingredientes`
--

CREATE TABLE `ingredientes` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `categoria_ingrediente_id` int NOT NULL,
  `unidad_medida_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ingredientes`
--

INSERT INTO `ingredientes` (`id`, `nombre`, `categoria_ingrediente_id`, `unidad_medida_id`) VALUES
(1, 'Sal', 1, 1),
(2, 'Pimienta negra', 1, 1),
(3, 'Comino', 1, 1),
(4, 'Paprika', 1, 1),
(5, 'Ajo en polvo', 1, 1),
(6, 'Curry', 1, 1),
(7, 'Orégano seco', 1, 1),
(8, 'Albahaca seca', 1, 1),
(9, 'Canela', 1, 1),
(10, 'Clavo de olor', 1, 1),
(11, 'Nuez moscada', 1, 1),
(12, 'Cúrcuma', 1, 1),
(13, 'Perejil seco', 1, 1),
(14, 'Romero seco', 1, 1),
(15, 'Tomillo seco', 1, 1),
(16, 'Laurel seco', 1, 1),
(17, 'Pimienta cayena', 1, 1),
(18, 'Mostaza en polvo', 1, 1),
(19, 'Anís estrellado', 1, 1),
(20, 'Semillas de cilantro', 1, 1),
(21, 'Alas dePollo', 2, 4),
(22, 'Carne molida de res', 2, 1),
(23, 'Chorizo', 2, 4),
(24, 'Jamón', 2, 1),
(25, 'Tocino', 2, 4),
(26, 'Huevos', 2, 4),
(27, 'Salchichas', 2, 4),
(28, 'Pechuga de pollo', 2, 1),
(29, 'Costillas de cerdo', 2, 1),
(30, 'Atún en lata', 2, 1),
(31, 'Huevos de codorniz', 2, 4),
(32, 'Tomate', 3, 4),
(33, 'Cebolla', 3, 4),
(34, 'Ajo', 3, 4),
(35, 'Papa', 3, 4),
(36, 'Zanahoria', 3, 4),
(37, 'Aguacate', 3, 4),
(38, 'Manzana', 3, 4),
(39, 'Platano maduro', 3, 4),
(40, 'Platano verde', 3, 4),
(41, 'Limón', 3, 4),
(42, 'Hojas de lechuga', 3, 4),
(43, 'Espinaca', 3, 1),
(44, 'Leche', 4, 2),
(45, 'Mantequilla', 4, 1),
(46, 'Queso mozarella', 4, 1),
(47, 'Crema de leche', 4, 2),
(48, 'Yogur natural', 4, 2),
(49, 'Queso crema', 4, 1),
(50, 'Leche condensada', 4, 2),
(51, 'Margarina', 4, 1),
(52, 'Queso parmesano', 4, 1),
(53, 'Cuajada', 4, 1),
(54, 'Aceite', 4, 1),
(55, 'Nata para montar', 4, 2),
(56, 'Vino blanco', 5, 2),
(57, 'Vino tinto', 5, 2),
(58, 'Ron', 5, 2),
(59, 'Cerveza', 5, 2),
(60, 'Cerveza negra', 5, 2),
(61, 'Whisky', 5, 2),
(62, 'Vodka', 5, 2),
(63, 'Coñac', 5, 2),
(64, 'Brandy', 5, 2),
(65, 'Licor de café', 5, 2),
(66, 'Tequila', 5, 2),
(67, 'Harina de trigo', 6, 1),
(68, 'Harina de maiz', 6, 1),
(69, 'Azúcar', 6, 1),
(70, 'Polvo de hornear', 6, 3),
(71, 'Bicarbonato de sodio', 6, 3),
(72, 'Levadura seca', 6, 3),
(73, 'Miel', 6, 2),
(74, 'Extracto de vainilla', 6, 3),
(75, 'Cacao en polvo', 6, 1),
(76, 'Chispas de chocolate', 6, 1),
(77, 'Maicena', 6, 1),
(78, 'Café', 8, 3),
(79, 'Salsa Teriyaki', 8, 3),
(80, 'Salsa de soja', 8, 2),
(81, 'Vinagre blanco', 8, 2),
(82, 'Mostaza', 8, 2),
(83, 'Ketchup', 8, 2),
(84, 'Mayonesa', 8, 2),
(85, 'Aceitunas', 8, 4),
(86, 'Alcaparras', 8, 4),
(87, 'Gelatina sin sabor', 8, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasos`
--

CREATE TABLE `pasos` (
  `id` int NOT NULL,
  `receta_id` int NOT NULL,
  `descripcion` text NOT NULL,
  `orden` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recetas`
--

CREATE TABLE `recetas` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tiempo_preparacion` int DEFAULT NULL,
  `categoria_receta_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta_ingredientes`
--

CREATE TABLE `receta_ingredientes` (
  `id` int NOT NULL,
  `receta_id` int NOT NULL,
  `ingrediente_id` int NOT NULL,
  `cantidad` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidad_medida`
--

CREATE TABLE `unidad_medida` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `unidad_medida`
--

INSERT INTO `unidad_medida` (`id`, `nombre`) VALUES
(3, 'cucharadas'),
(1, 'gr'),
(2, 'ml'),
(4, 'unidades');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria_ingrediente`
--
ALTER TABLE `categoria_ingrediente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `categoria_receta`
--
ALTER TABLE `categoria_receta`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_ingrediente_id`),
  ADD KEY `unidad_medida_id` (`unidad_medida_id`);

--
-- Indices de la tabla `pasos`
--
ALTER TABLE `pasos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `receta_id` (`receta_id`);

--
-- Indices de la tabla `recetas`
--
ALTER TABLE `recetas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_receta_id`);

--
-- Indices de la tabla `receta_ingredientes`
--
ALTER TABLE `receta_ingredientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `receta_id` (`receta_id`),
  ADD KEY `ingrediente_id` (`ingrediente_id`);

--
-- Indices de la tabla `unidad_medida`
--
ALTER TABLE `unidad_medida`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria_ingrediente`
--
ALTER TABLE `categoria_ingrediente`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `categoria_receta`
--
ALTER TABLE `categoria_receta`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- AUTO_INCREMENT de la tabla `pasos`
--
ALTER TABLE `pasos`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `recetas`
--
ALTER TABLE `recetas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `receta_ingredientes`
--
ALTER TABLE `receta_ingredientes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `unidad_medida`
--
ALTER TABLE `unidad_medida`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD CONSTRAINT `ingredientes_ibfk_1` FOREIGN KEY (`categoria_ingrediente_id`) REFERENCES `categoria_ingrediente` (`id`),
  ADD CONSTRAINT `ingredientes_ibfk_2` FOREIGN KEY (`unidad_medida_id`) REFERENCES `unidad_medida` (`id`);

--
-- Filtros para la tabla `pasos`
--
ALTER TABLE `pasos`
  ADD CONSTRAINT `pasos_ibfk_1` FOREIGN KEY (`receta_id`) REFERENCES `recetas` (`id`);

--
-- Filtros para la tabla `recetas`
--
ALTER TABLE `recetas`
  ADD CONSTRAINT `recetas_ibfk_1` FOREIGN KEY (`categoria_receta_id`) REFERENCES `categoria_receta` (`id`);

--
-- Filtros para la tabla `receta_ingredientes`
--
ALTER TABLE `receta_ingredientes`
  ADD CONSTRAINT `receta_ingredientes_ibfk_1` FOREIGN KEY (`receta_id`) REFERENCES `recetas` (`id`),
  ADD CONSTRAINT `receta_ingredientes_ibfk_2` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingredientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
