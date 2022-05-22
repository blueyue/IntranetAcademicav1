-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2022 a las 03:54:40
-- Versión del servidor: 10.4.16-MariaDB
-- Versión de PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_proyecto_intranetedu`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `spBuscarUser` (IN `iduser` INT, IN `rol_` INT)  BEGIN
         IF (rol_ > 0 && rol_ < 2 ) THEN/*1*/
         select * from alumno where idusuario=iduser;
         ELSEif (rol_ > 1 && rol_ < 3 ) then
         select * from profesor where idusuario=iduser;
         ELSEif (rol_ > 2 && rol_ < 4 ) then
         select * from Administrador where idusuario=iduser;
         END IF;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConfirmacionPagos` (IN `idboleta_` INT)  begin 
	declare idalumno_ int unsigned ;
    declare idcurso_ int unsigned ;
    
    DECLARE `_rollback` BOOL DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET `_rollback` = 1;
    
    START TRANSACTION;
    
    update boleta set estado = 1 where idboleta = idboleta_;
    update matricula set estado = 1 where idboleta = idboleta_;
      
      select idalumno,idcurso into idalumno_,idcurso_ from boleta b  
      inner join matricula m on b.idboleta = m.idboleta
      inner join detalleprofesorcursos dp on m.iddet = dp.iddet
      where b.idboleta = idboleta_;
    
    insert into notas (idalumno,idcurso) values (idalumno_,idcurso_);
  
    IF `_rollback` THEN
        ROLLBACK;
    ELSE
        COMMIT;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spLogin` (IN `newuser` VARCHAR(50), IN `newpass` VARCHAR(50), IN `tipo` INT)  BEGIN
	SELECT * from usuario where (nomusuario=newuser and contrasena=newpass and rol = tipo and estado=true) ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spnuevoMatricula` (IN `idcarrera_` INT, IN `iddet_` INT, IN `idalumno_` INT)  begin 
	declare idboletoi int unsigned ;
    declare servicio varchar(200) ;
    DECLARE `_rollback` BOOL DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET `_rollback` = 1;
    
    START TRANSACTION;
    
    insert into boleta (idservicio,descripcion,fecha,estado) values (1,null,CURDATE(),0);/*crea boleta para pagar en por POR CANCELADA*/
    select max(idboleta) as bolid into idboletoi from boleta;
	INSERT INTO matricula (idcarrera,iddet,idalumno,fecha_matricula,estado,idboleta) values (idcarrera_,iddet_,idalumno_, CURDATE(),0,idboletoi);
  
    IF `_rollback` THEN
        ROLLBACK;
    ELSE
        COMMIT;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ingresar_notas` (`idalumno_` INT, `idcurso_` INT, `nota1_` INT, `nota2_` INT, `nota3_` INT, `nota4_` INT, `ExParcial_` INT, `ExFinal_` INT, `ExSustitutorio_` INT)  begin
insert into notas (idalumno,idcurso,nota1,nota2,nota3,nota4,ExParcial,ExFinal,ExSustitutorio,promedioFinal) 
values
(idalumno_,idcurso_,nota1_,nota2_,nota3_,nota4_,ExParcial_,ExFinal_,ExSustitutorio_,(((nota1_+nota2_+nota3_+nota4_)/4)*0.45+ExParcial_*0.25+ExFinal_*0.30+ExSustitutorio_));
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_nuevo_usuario` (`nomusuario_` VARCHAR(100), `contrasena_` VARCHAR(100), `rol_` INT)  begin
insert into usuario (nomusuario,contrasena,rol,estado) values
(nomusuario_,contrasena_,rol_,1);
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `idadmin` int(11) NOT NULL,
  `codadmin` varchar(8) NOT NULL,
  `admin_nom` varchar(100) NOT NULL,
  `idusuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`idadmin`, `codadmin`, `admin_nom`, `idusuario`) VALUES
(2, 'AD_001', 'KarlaA', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `idalumno` int(11) NOT NULL,
  `codalum` varchar(8) NOT NULL,
  `alum_nom` varchar(100) NOT NULL,
  `alum_ape` varchar(100) NOT NULL,
  `alum_nac` date NOT NULL,
  `alum_dir` varchar(200) NOT NULL,
  `alum_telf` int(10) NOT NULL,
  `idusuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`idalumno`, `codalum`, `alum_nom`, `alum_ape`, `alum_nac`, `alum_dir`, `alum_telf`, `idusuario`) VALUES
(1, '76955318', 'Bille', 'Gong', '2006-07-24', 'Las Passas', 654874568, 2),
(2, '22222222', 'Lia', 'Hio', '2006-01-09', 'Las Passas', 654874568, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta`
--

CREATE TABLE `boleta` (
  `idboleta` int(11) NOT NULL,
  `idservicio` int(11) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `fecha` date NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `boleta`
--

INSERT INTO `boleta` (`idboleta`, `idservicio`, `descripcion`, `fecha`, `estado`) VALUES
(1, 1, NULL, '2022-05-21', 1),
(2, 1, NULL, '2022-05-21', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE `carrera` (
  `idcarrera` int(11) NOT NULL,
  `nomcarrera` varchar(100) NOT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`idcarrera`, `nomcarrera`, `estado`) VALUES
(1, 'AGRONOMIA', NULL),
(2, 'MEDICINA', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `idcurso` int(11) NOT NULL,
  `nomcurso` varchar(100) NOT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `requisito` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`idcurso`, `nomcurso`, `estado`, `requisito`) VALUES
(1, 'Quimica Basica', 1, 'Sin Requisitos'),
(2, 'Quimica Avanzada I', 1, 'Quimica Basica'),
(3, 'Historia Universal', 1, 'Sin Requisitos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecarreracurso`
--

CREATE TABLE `detallecarreracurso` (
  `idcc` int(11) NOT NULL,
  `idcarrera` int(11) NOT NULL,
  `ciclo` int(11) DEFAULT NULL,
  `curso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detallecarreracurso`
--

INSERT INTO `detallecarreracurso` (`idcc`, `idcarrera`, `ciclo`, `curso`) VALUES
(1, 1, 1, 1),
(2, 1, 6, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleprofesorcursos`
--

CREATE TABLE `detalleprofesorcursos` (
  `iddet` int(11) NOT NULL,
  `idprofesor` int(11) NOT NULL,
  `idcurso` int(11) NOT NULL,
  `idturno` int(11) DEFAULT NULL,
  `horainicurso` varchar(11) NOT NULL,
  `horafincurso` varchar(11) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalleprofesorcursos`
--

INSERT INTO `detalleprofesorcursos` (`iddet`, `idprofesor`, `idcurso`, `idturno`, `horainicurso`, `horafincurso`, `estado`) VALUES
(1, 3, 1, 1, '10:08', '11:11', 1),
(2, 3, 2, 2, '15:33', '18:00', 1),
(4, 2, 3, 1, '14:00', '18:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historialcursoalum`
--

CREATE TABLE `historialcursoalum` (
  `idalumno` int(11) DEFAULT NULL,
  `idcurso` int(11) DEFAULT NULL,
  `estado` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `nro_matricula` int(11) NOT NULL,
  `idcarrera` int(11) NOT NULL,
  `iddet` int(11) NOT NULL,
  `idalumno` int(11) NOT NULL,
  `fecha_matricula` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 0,
  `idboleta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`nro_matricula`, `idcarrera`, `iddet`, `idalumno`, `fecha_matricula`, `estado`, `idboleta`) VALUES
(1, 1, 4, 1, '2022-05-21', 1, 1),
(2, 2, 4, 2, '2022-05-21', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notas`
--

CREATE TABLE `notas` (
  `idreporte` int(11) NOT NULL,
  `idalumno` int(11) NOT NULL,
  `idcurso` int(11) NOT NULL,
  `nota1` int(11) DEFAULT 0,
  `nota2` int(11) DEFAULT 0,
  `nota3` int(11) DEFAULT 0,
  `nota4` int(11) DEFAULT 0,
  `ExParcial` int(11) DEFAULT 0,
  `ExFinal` int(11) DEFAULT 0,
  `ExSustitutorio` int(11) DEFAULT 0,
  `promedioFinal` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `notas`
--

INSERT INTO `notas` (`idreporte`, `idalumno`, `idcurso`, `nota1`, `nota2`, `nota3`, `nota4`, `ExParcial`, `ExFinal`, `ExSustitutorio`, `promedioFinal`) VALUES
(1, 2, 3, 20, 12, 15, 20, 0, 0, 0, NULL),
(2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `idprofesor` int(11) NOT NULL,
  `codprof` varchar(8) NOT NULL,
  `prof_nom` varchar(100) DEFAULT NULL,
  `prof_ape` varchar(100) DEFAULT NULL,
  `prof_nac` date DEFAULT NULL,
  `prof_dir` varchar(200) DEFAULT NULL,
  `prof_telf` int(11) DEFAULT NULL,
  `prof_especialidad` varchar(200) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`idprofesor`, `codprof`, `prof_nom`, `prof_ape`, `prof_nac`, `prof_dir`, `prof_telf`, `prof_especialidad`, `idusuario`) VALUES
(2, '65466542', 'Hill', 'Braw', '1993-05-03', 'Los Molinos', 454546668, 'Historia', 3),
(3, '45655455', 'Julia', 'Durian', '1960-05-16', 'Los Olivos', 5456544, 'Quimica', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `idrol` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idrol`, `descripcion`, `estado`) VALUES
(1, 'ALUMNO', 1),
(2, 'PROFESOR', 1),
(3, 'ADMINISTRADOR', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifaservicio`
--

CREATE TABLE `tarifaservicio` (
  `idservicio` int(11) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tarifaservicio`
--

INSERT INTO `tarifaservicio` (`idservicio`, `descripcion`, `fecha`, `monto`) VALUES
(1, 'MATRICULA', '2022-05-05', 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `idturno` int(1) NOT NULL,
  `nomturno` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`idturno`, `nomturno`) VALUES
(1, 'mañana'),
(2, 'noche');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `nomusuario` varchar(100) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `rol` int(11) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nomusuario`, `contrasena`, `rol`, `estado`) VALUES
(1, 'admin', 'admin', 3, 1),
(2, 'alumno1', 'alumno1', 1, 1),
(3, 'profesor1', 'profesor1', 2, 1),
(4, 'profesor2', 'profesor2', 2, 1),
(5, 'lia', '1234', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idadmin`),
  ADD UNIQUE KEY `codadmin` (`codadmin`),
  ADD KEY `FK_UsuarioAdministrador` (`idusuario`);

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`idalumno`),
  ADD UNIQUE KEY `codalum` (`codalum`),
  ADD KEY `FK_UsuarioAlumno` (`idusuario`);

--
-- Indices de la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD PRIMARY KEY (`idboleta`);

--
-- Indices de la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`idcarrera`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`idcurso`);

--
-- Indices de la tabla `detallecarreracurso`
--
ALTER TABLE `detallecarreracurso`
  ADD PRIMARY KEY (`idcc`),
  ADD KEY `FK_CarreraDetalle` (`idcarrera`),
  ADD KEY `FK_CursoDetalle` (`curso`);

--
-- Indices de la tabla `detalleprofesorcursos`
--
ALTER TABLE `detalleprofesorcursos`
  ADD PRIMARY KEY (`iddet`),
  ADD KEY `FK_ProfesorCDetalle` (`idprofesor`),
  ADD KEY `FK_CursoPDetalle` (`idcurso`),
  ADD KEY `FK_TurnoPCDetalle` (`idturno`);

--
-- Indices de la tabla `historialcursoalum`
--
ALTER TABLE `historialcursoalum`
  ADD KEY `FK_AlumnoHistorial1` (`idalumno`),
  ADD KEY `FK_AlumnoHistorial2` (`idcurso`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`nro_matricula`),
  ADD KEY `FK_CarreraAlumno` (`idcarrera`),
  ADD KEY `FK_AlumnoMatricula` (`idalumno`),
  ADD KEY `FK_CursoMatricula` (`iddet`),
  ADD KEY `FK_BoletaMatricula` (`idboleta`);

--
-- Indices de la tabla `notas`
--
ALTER TABLE `notas`
  ADD PRIMARY KEY (`idreporte`),
  ADD KEY `FK_AlumnoNotas` (`idalumno`),
  ADD KEY `FK_CursoNotas` (`idcurso`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`idprofesor`),
  ADD UNIQUE KEY `codprof` (`codprof`),
  ADD KEY `FK_UsuarioProfesor` (`idusuario`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `tarifaservicio`
--
ALTER TABLE `tarifaservicio`
  ADD PRIMARY KEY (`idservicio`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`idturno`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `FK_RolUsuario` (`rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `idadmin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `idalumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `boleta`
--
ALTER TABLE `boleta`
  MODIFY `idboleta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `carrera`
--
ALTER TABLE `carrera`
  MODIFY `idcarrera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `curso`
--
ALTER TABLE `curso`
  MODIFY `idcurso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detallecarreracurso`
--
ALTER TABLE `detallecarreracurso`
  MODIFY `idcc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalleprofesorcursos`
--
ALTER TABLE `detalleprofesorcursos`
  MODIFY `iddet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `matricula`
--
ALTER TABLE `matricula`
  MODIFY `nro_matricula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `notas`
--
ALTER TABLE `notas`
  MODIFY `idreporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `profesor`
--
ALTER TABLE `profesor`
  MODIFY `idprofesor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tarifaservicio`
--
ALTER TABLE `tarifaservicio`
  MODIFY `idservicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `idturno` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `FK_UsuarioAdministrador` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`);

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `FK_UsuarioAlumno` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`);

--
-- Filtros para la tabla `detallecarreracurso`
--
ALTER TABLE `detallecarreracurso`
  ADD CONSTRAINT `FK_CarreraDetalle` FOREIGN KEY (`idcarrera`) REFERENCES `carrera` (`idcarrera`),
  ADD CONSTRAINT `FK_CursoDetalle` FOREIGN KEY (`curso`) REFERENCES `curso` (`idcurso`);

--
-- Filtros para la tabla `detalleprofesorcursos`
--
ALTER TABLE `detalleprofesorcursos`
  ADD CONSTRAINT `FK_CursoPDetalle` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`),
  ADD CONSTRAINT `FK_ProfesorCDetalle` FOREIGN KEY (`idprofesor`) REFERENCES `profesor` (`idprofesor`),
  ADD CONSTRAINT `FK_TurnoPCDetalle` FOREIGN KEY (`idturno`) REFERENCES `turno` (`idturno`);

--
-- Filtros para la tabla `historialcursoalum`
--
ALTER TABLE `historialcursoalum`
  ADD CONSTRAINT `FK_AlumnoHistorial1` FOREIGN KEY (`idalumno`) REFERENCES `alumno` (`idalumno`),
  ADD CONSTRAINT `FK_AlumnoHistorial2` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`);

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `FK_AlumnoMatricula` FOREIGN KEY (`idalumno`) REFERENCES `alumno` (`idalumno`),
  ADD CONSTRAINT `FK_BoletaMatricula` FOREIGN KEY (`idboleta`) REFERENCES `boleta` (`idboleta`),
  ADD CONSTRAINT `FK_CarreraAlumno` FOREIGN KEY (`idcarrera`) REFERENCES `carrera` (`idcarrera`),
  ADD CONSTRAINT `FK_CursoMatricula` FOREIGN KEY (`iddet`) REFERENCES `detalleprofesorcursos` (`iddet`);

--
-- Filtros para la tabla `notas`
--
ALTER TABLE `notas`
  ADD CONSTRAINT `FK_AlumnoNotas` FOREIGN KEY (`idalumno`) REFERENCES `alumno` (`idalumno`),
  ADD CONSTRAINT `FK_CursoNotas` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`);

--
-- Filtros para la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD CONSTRAINT `FK_UsuarioProfesor` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_RolUsuario` FOREIGN KEY (`rol`) REFERENCES `roles` (`idrol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
