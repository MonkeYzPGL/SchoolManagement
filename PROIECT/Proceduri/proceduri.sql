-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: proiect
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping routines for database 'proiect'
--
/*!50003 DROP PROCEDURE IF EXISTS `addGrades` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addGrades`(in nume_stud varchar(20), in prenume_stud varchar(20), in CNP varchar(13), in nume_curs varchar(45),in nume_prof varchar(20), in prenume_prof varchar(20), in notas int, in notal int, in notac int)
begin
SET @idstudent = (SELECT student.idstudent
               from student
               where student.nume = nume_stud 
               and student.prenume = prenume_stud
               and  student.cnp = CNP);
SELECT @idstudent;
set @idcurs = ( SELECT cursuri.idcurs
                from cursuri,informatii_profesor,profesor
                where cursuri.nume_curs = nume_curs
                and cursuri.idcurs = informatii_profesor.idcurs
                and informatii_profesor.idprofesor = profesor.idprofesor
                and profesor.nume = nume_prof
                and profesor.prenume = prenume_prof );


set @ordineNote=( SELECT MAX(note_student.idordine) FROM note_student) + 1;
if @ordineNote IS NULL then
set @ordineNote =1;

SET @idlaborator = ( SELECT MAX(laborator_nota.idlaborator_nota) FROM laborator_nota) + 1;
if @idlaborator IS NULL then
set @idlaborator=1;
end if; 


SET @idseminar = ( SELECT MAX(seminar_nota.idseminar_nota) FROM seminar_nota) + 1;
if @idseminar IS NULL then
set @idseminar=1;
end if; 


end if;
SET SQL_SAFE_UPDATES=0;
SET @data =(select inscriere.data_inscriere from inscriere where inscriere.idstudent=@idstudent and inscriere.idcurs=@idcurs);
set @data1 =(select inscriere.data_renunt from inscriere where inscriere.idstudent=@idstudent and inscriere.idcurs=@idcurs);
	if @data IS NOT NULL then
    if @data1 is NULL then
   
     insert into proiect.laborator_nota (idlaborator_nota, idcurs, nota_laborator, idstudent) 
		values (@ordineNote,@idcurs, notal, @idstudent);
	insert into proiect.seminar_nota (idseminar_nota, idcurs, nota_seminar, idstudent) 
		values (@ordineNote, @idcurs, notas, @idstudent);
		insert into proiect.curs_nota (idcurs_nota, idcurs, nota_curs, idstudent)
		values (@ordineNote,@idcurs, notac, @idstudent);
        
         insert into proiect.note_student
    values (@idstudent, @idcurs, @idcurs, @idcurs, @ordineNote);
	
       
    update seminar_nota set  seminar_nota.sem = @idseminar where seminar_nota.idseminar_nota=@ordineNote;
	update curs_nota set  curs_nota.curs = @idcurs where curs_nota.idcurs_nota=@ordineNote;
	update laborator_nota set  laborator_nota.lab = @idlaborator where laborator_nota.idlaborator_nota=@ordineNote;
    
    update seminar_nota set  seminar_nota.nota_seminar = notas where seminar_nota.sem=@idseminar and seminar_nota.idseminar_nota=@ordineNote; 
	update curs_nota set  curs_nota.nota_curs = notac where curs_nota.curs=@idcurs and curs_nota.idcurs_nota=@ordineNote;
	update laborator_nota set  laborator_nota.nota_laborator = notal where laborator_nota.lab=@idlaborator and laborator_nota.idlaborator_nota=@ordineNote;
    
    
    
    update seminar_nota set  seminar_nota.idstudent = @idstudent where seminar_nota.sem=@idseminar and seminar_nota.idseminar_nota=@ordineNote; 
	update curs_nota set  curs_nota.idstudent = @idstudent where curs_nota.curs=@idcurs and curs_nota.idcurs_nota=@ordineNote;
	update laborator_nota set  laborator_nota.idstudent = @idstudent where laborator_nota.lab=@idlaborator and laborator_nota.idlaborator_nota=@ordineNote;
    end if;
    end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addProcente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addProcente`( in nume varchar(45),in curspr int, in seminarpr int, in labpr int)
begin
set @id =(select activitati.idcurs from activitati, cursuri where cursuri.nume_curs=nume and cursuri.idcurs=activitati.idcurs);
update activitati set activitati.laboratorpr=labpr where activitati.idcurs=@id;
update activitati set activitati.seminarpr=seminarpr where activitati.idcurs=@id;
update activitati set activitati.curspr=curspr where activitati.idcurs=@id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addPRof` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addPRof`(in numep varchar(20))
BEGIN
	set @idp = (select idprofesor from profesor where profesor.nume = numep);
    set @idc = (select idcurs from informatii_profesor where informatii_profesor.idprofesor = @idp);
    set @idg = (select idgrup_studiu from grup_studiu where grup_studiu.idcurs = @idc);
    set @idactiv = (select idactiv_grup from activ_grup where activ_grup.id_grup_studiu = @idg);
    update activ_grup
    set idprof = @idp where activ_grup.idactiv_grup = @idactiv;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addTeacherToGroup` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addTeacherToGroup`(in nume_grup varchar(25), in nume_prof varchar(25), in prenume_prof varchar(20))
begin
set @id=(select idgrup_studiu from grup_studiu where grup_studiu.nume=nume_grup);
set @idProf=(select idprofesor from profesor where profesor.nume=nume_prof and profesor.prenume=prenume_prof );
 update grup_studiu set idprofesor = @idProf where grup_studiu.idgrup_studiu=@id; 
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addToCalendar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addToCalendar`(in nume_curs varchar(10), in data_sem date, in data1_sem date, in data_lab date, in data1_lab date, in data_curs date, in data1_curs date)
begin
set @idcurs = ( SELECT cursuri.idcurs
                from cursuri
                where cursuri.nume_curs = nume_curs );
insert into proiect.calendar values(@idcurs, data_curs, data1_curs, data_lab, data1_lab, data_sem, data1_sem);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `createActivityGroup` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createActivityGroup`(in nume_grup varchar(20), in nume_activ varchar(10),  in data_desf varchar(20), in ora_exp varchar(10), in nr_min int)
begin
set @ID=( SELECT MAX(activ_grup.idactiv_grup) FROM activ_grup) + 1;
if @ID IS NULL then
set @ID=1;
end if;
set @idgrup =( select idgrup_studiu from grup_studiu where grup_studiu.nume=nume_grup);
insert into proiect.activ_grup values (@ID, @idgrup, nr_min, data_desf, ora_exp,0, nume_activ);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `createcursuri` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createcursuri`( in nume varchar(45),in descriere varchar(45), in nr_max_stud int, in zi_curs varchar(20), in zi_seminar varchar(20), in zi_lab varchar(20), in ora_curs int , in ora_lab int, in ora_sem int, in a varchar(10))
begin
set @ID=( SELECT MAX(cursuri.idcurs) FROM cursuri) + 1;
if @ID IS NULL then
set @ID=1;
end if;
INSERT INTO `proiect`.`cursuri` (`idcurs`, `nume_curs`,`descriere`,`nr_max_studenti`,`nr_actual_studenti`) 
VALUES (@ID, nume, descriere, nr_max_stud,'0');
insert into activitati (idcurs, zi_curs, zi_lab, zi_seminar, ora_curs, ora_lab, ora_sem) 
values (@ID, zi_curs, zi_lab, zi_seminar, ora_curs, ora_lab, ora_sem);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `creategrup_studiu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `creategrup_studiu`( in nume varchar(20), in nr_max_studenti int, in nume_curs varchar(20), in nume_prof varchar(20), in prenume_prof varchar(20),in a varchar(10),in a1 varchar(10),in a2 varchar(10),in a3 varchar(10), in a4 varchar(10))
begin
set @idcurs = ( SELECT cursuri.idcurs
                from cursuri,informatii_profesor,profesor
                where cursuri.nume_curs = nume_curs
                and cursuri.idcurs = informatii_profesor.idcurs
                and informatii_profesor.idprofesor = profesor.idprofesor
                and profesor.nume = nume_prof
                and profesor.prenume = prenume_prof );
set @ID=( SELECT MAX(grup_studiu.idgrup_studiu) FROM grup_studiu) + 1;
if @ID IS NULL then
set @ID=1;
end if;
insert into grup_studiu(idgrup_studiu, nume, nr_max_studenti, idcurs,nr_actual_studenti)
values (@ID, nume, nr_max_studenti,@idcurs,0);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteadmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteadmin`(in cnp varchar(13))
BEGIN
DELETE  
FROM administrator
WHERE administrator.CNP = cnp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deletecurs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletecurs`( in nume_curs varchar(45), in nume_prof varchar(20), in prenume_prof varchar(20), in a varchar(10),in a1 varchar(10),in a2 varchar(10),in a3 varchar(10),in a4 varchar(10),in a5 varchar(10),in a6 varchar(10) )
BEGIN
set @idcurs = ( SELECT cursuri.idcurs
                from cursuri,informatii_profesor,profesor
                where cursuri.nume_curs = nume_curs
                and cursuri.idcurs = informatii_profesor.idcurs
                and informatii_profesor.idprofesor = profesor.idprofesor
                and profesor.nume = nume_prof
                and profesor.prenume = prenume_prof );
DELETE cursuri , activitati 
 FROM cursuri  INNER JOIN activitati 
WHERE cursuri.idcurs= activitati.idcurs 
and cursuri.idcurs = @idcurs;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deletegrup_studiu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletegrup_studiu`( in nume_grup varchar(45), in nume_curs varchar(45), in nume_prof varchar(20), in prenume_prof varchar(20),in a varchar(10),in a2 varchar(10),in a3 varchar(10),in a4 varchar(10),in a5 varchar(10),in a6 varchar(10))
begin
set @idcurs = ( SELECT cursuri.idcurs
                from cursuri,informatii_profesor,profesor
                where cursuri.nume_curs = nume_curs
                and cursuri.idcurs = informatii_profesor.idcurs
                and informatii_profesor.idprofesor = profesor.idprofesor
                and profesor.nume = nume_prof
                and profesor.prenume = prenume_prof );
SET @idgrup_studiu = (SELECT grup_studiu.idgrup_studiu
                      from grup_studiu
                      where grup_studiu.nume = nume_grup);
DELETE grup_studiu
from grup_studiu
where grup_studiu.idgrup_studiu = idgrup_studiu;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteprofesor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteprofesor`(in cnp varchar(13))
BEGIN
SET @idprof = (SELECT profesor.idprofesor
               from profesor
               where profesor.cnp = CNP);
DELETE  
FROM profesor
WHERE profesor.idprofesor = @idprof;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deletestudent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletestudent`(in CNPS varchar(20))
BEGIN
SET @idstud = (SELECT student.idstudent
               from student
               where student.CNP = CNPS);
DELETE 
FROM student
WHERE student.idstudent = @idstud;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_infoadministrator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_infoadministrator`(in CNP varchar(13), in aux int)
BEGIN
	if(aux = 1) then
		update administrator
        set administrator.nume = NULL where administrator.CNP = CNP;
        end if;
        if(aux = 2) then
		update administrator
        set administrator.prenume = NULL where administrator.CNP = CNP;
        end if;
        if(aux = 3) then
		update administrator
        set administrator.adresa = NULL where administrator.CNP = CNP;
        end if;
        if(aux = 4) then
		update administrator
        set administrator.telefon = NULL where administrator.CNP = CNP;
        end if;
        if(aux = 5) then
		update administrator
        set administrator.email = NULL where administrator.CNP = CNP;
        end if;
        if(aux = 6) then
		update administrator
        set administrator.IBAN = NULL where administrator.CNP = CNP;
        end if;
        if(aux = 7) then
		update administrator
        set administrator.nr_contract = NULL where administrator.CNP = CNP;
        end if;
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_infoprofesor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_infoprofesor`(in CNP varchar(13), in aux int)
BEGIN
	if(aux = 1) then
		update profesor
        set profesor.nume = NULL where profesor.CNP = CNP;
        end if;
        if(aux = 2) then
		update profesor
        set profesor.prenume = NULL where profesor.CNP = CNP;
        end if;
        if(aux = 3) then
		update profesor
        set profesor.adresa = NULL where profesor.CNP = CNP;
        end if;
        if(aux = 4) then
		update profesor
        set profesor.telefon = NULL where profesor.CNP = CNP;
        end if;
        if(aux = 5) then
		update profesor
        set profesor.email = NULL where profesor.CNP = CNP;
        end if;
        if(aux = 6) then
		update profesor
        set profesor.IBAN = NULL where profesor.CNP = CNP;
        end if;
        if(aux = 7) then
		update profesor
        set profesor.nr_contract = NULL where profesor.CNP = CNP;
        end if;
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_infostudent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_infostudent`(in CNP varchar(13), in aux int)
BEGIN
	if(aux = 1) then
		update student
        set student.nume = NULL where student.CNP = CNP;
        end if;
        if(aux = 2) then
		update student
        set student.prenume = NULL where student.CNP = CNP;
        end if;
        if(aux = 3) then
		update student
        set student.adresa = NULL where student.CNP = CNP;
        end if;
        if(aux = 4) then
		update student
        set student.telefon = NULL where student.CNP = CNP;
        end if;
        if(aux = 5) then
		update student
        set student.email = NULL where student.CNP = CNP;
        end if;
        if(aux = 6) then
		update student
        set student.IBAN = NULL where student.CNP = CNP;
        end if;
        if(aux = 7) then
		update student
        set student.nr_contract = NULL where student.CNP = CNP;
        end if;
        if(aux = 8) then
		update student
        set student.anstudiu = NULL where student.CNP = CNP;
        end if;
        if(aux = 9) then
		update student
        set student.nrore = NULL where student.CNP = CNP;
		end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dropCourse` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dropCourse`(in curs1 varchar(20),  in nume varchar(20))
begin
set @stud =(select student.idstudent from student where student.nume = nume);
set @id =(select cursuri.idcurs from cursuri where cursuri.nume_curs= curs1);
update cursuri set cursuri.nr_actual_studenti= cursuri.nr_actual_studenti-1 where cursuri.idcurs = @id;
update inscriere set inscriere.data_renunt=current_date()  where inscriere.idstudent = @stud and inscriere.idcurs=@id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dropStudyGroup` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dropStudyGroup`(in nume_stud varchar(20),in prenume_stud varchar(20),in CNP varchar(13), in nume_grup varchar(20))
begin
SET @idstudent = (SELECT student.idstudent
               from student
               where student.nume = nume_stud 
               and student.prenume = prenume_stud
               and  student.cnp = CNP);
set @idcurs = ( SELECT grup_studiu.idcurs
                from grup_studiu
                where grup_studiu.nume = nume_grup );
set @idgrup = (SELECT grup_studiu.idgrup_studiu
               from grup_studiu
               where grup_studiu.nume = nume_grup );
set @nr_actual = (select grup_studiu.nr_actual_studenti
                 from grup_studiu
				 where grup_studiu.nume = nume_grup );
set @idrelatii = (SELECT relatii_grup.idrelatii_grup
                  from relatii_grup
                  where relatii_grup.idstudent = @idstudent
                  and relatii_grup.idgrup = @idgrup);
if @idrelatii is not null then
if @idstudent is not null then
update grup_studiu set grup_studiu.nr_actual_studenti = @nr_actual-1 where grup_studiu.idcurs= @idcurs  and grup_studiu.nume = nume_grup;
delete relatii_grup from relatii_grup where relatii_grup.idgrup = @idgrup and relatii_grup.idstudent = @idstudent;
end if;
 end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `incercare` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `incercare`(in curs1 varchar(20))
begin

select distinct student.nume, student.prenume, nota_curs,nota_laborator,nota_seminar  from student, inscriere, note_student,curs_nota,seminar_nota,laborator_nota  where 
student.idstudent= inscriere.idstudent and inscriere.idcurs=(select cursuri.idcurs from cursuri where cursuri.nume_curs= curs1)
and student.idstudent=note_student.idstudent and note_student.idlaborator=(select cursuri.idcurs from cursuri where cursuri.nume_curs= curs1) 
and note_student.idseminar = (select cursuri.idcurs from cursuri where cursuri.nume_curs= curs1) 
and note_student.idcurs=(select cursuri.idcurs from cursuri where cursuri.nume_curs= curs1) 
and student.idstudent=laborator_nota.idstudent and student.idstudent=curs_nota.idstudent and student.idstudent=seminar_nota.idstudent;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertadministrator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertadministrator`( in cnp varchar(13), in nume varchar(20), in prenume varchar(20), in adresa varchar(10), in telefon varchar(10), in email varchar(20), in iban varchar (16), in nr_contact varchar(10))
begin
insert into proiect.administrator values (cnp,nume,prenume,adresa,telefon,email,iban,nr_contact);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertcursuri` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertcursuri`( in nume varchar(45),in descriere varchar(45), in nr_max_stud int, in zi_curs varchar(20), in zi_seminar varchar(20), in zi_lab varchar(20), in ora_curs int , in ora_lab int, in ora_sem int, in a varchar(10))
begin
set @ID=( SELECT MAX(cursuri.idcurs) FROM cursuri) + 1;
if @ID IS NULL then
set @ID=1;
end if;
INSERT INTO `proiect`.`cursuri` (`idcurs`, `nume_curs`,`descriere`,`nr_max_studenti`,`nr_actual_studenti`) 
VALUES (@ID, nume, descriere, nr_max_stud,'0');
insert into activitati (idcurs, zi_curs, zi_lab, zi_seminar, ora_curs, ora_lab, ora_sem) 
values (@ID, zi_curs, zi_lab, zi_seminar, ora_curs, ora_lab, ora_sem);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertinformatii_profesor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertinformatii_profesor`( in nume_curs varchar(45), in nume_prof varchar(20), in prenume_prof varchar(20), in nr_min int, in nr_max int, in departament varchar(20),in a varchar(10),in a1 varchar(10),in a2 varchar(10),in a3 varchar(10))
begin
set @ID=( SELECT MAX(informatii_profesor.idinformatii_profesor) FROM informatii_profesor) + 1;
if @ID IS NULL then
set @ID=1;
end if;
SELECT @ID;
SET @IDprof = (SELECT profesor.idprofesor
               from profesor
			   where profesor.nume = nume_prof
               and profesor.prenume = prenume_prof);
SELECT @IDPROF;
set @idcurs = ( SELECT cursuri.idcurs
                from cursuri
                where cursuri.nume_curs = nume_curs);
SELECT @IDCURS;
insert into proiect.informatii_profesor 
values (@ID, @IDprof, @idcurs, nr_min, nr_max, departament); 
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertprofesor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertprofesor`(in cnp varchar(13), in nume varchar(20), in prenume varchar(20), in adresa varchar(10), in telefon varchar(10), in email varchar(20), in iban varchar (16), in nr_contact varchar(10))
begin
set @ID=( SELECT MAX(profesor.idprofesor) FROM profesor) + 1;
if @ID IS NULL then
set @ID=1;
end if;
insert into proiect.profesor 
values (@ID,cnp,nume,prenume,adresa,telefon,email,iban,nr_contact);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertstudent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertstudent`( in cnp varchar(13), in nume varchar(20), in prenume varchar(20), in adresa varchar(10), in telefon varchar(10), in email varchar(20), in iban varchar (16), in nr_contact varchar(10), in anstudiu int, in nrore int)
begin
set @ID=( SELECT MAX(student.idstudent) FROM student) + 1;
if @ID IS NULL then
set @ID=1;
end if;
insert into proiect.student 
values (@ID,cnp,nume,prenume,adresa,telefon,email,iban,nr_contact, anstudiu, nrore);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `membersGroup` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `membersGroup`( in nume_grup varchar(20))
BEGIN
set @idgrup = (SELECT idgrup_studiu from grup_studiu where grup_studiu.nume = nume_grup );
select student.nume, student.prenume
from student,relatii_grup
where student.idstudent = relatii_grup.idstudent
and relatii_grup.idgrup = @idgrup;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtinmaterie` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtinmaterie`(in CNP varchar(45))
BEGIN
SELECT cursuri.nume_curs FROM cursuri,profesor,informatii_profesor WHERE CNP = profesor.CNP AND profesor.idprofesor = informatii_profesor.idprofesor AND cursuri.idcurs = informatii_profesor.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `searchCursuri` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchCursuri`(in CNP varchar(45))
BEGIN
set @idstud = (select student.idstudent from student where student.CNP = CNP);
select nume_curs, profesor.nume, profesor.prenume from cursuri,profesor, inscriere where inscriere.idprofesor=profesor.idprofesor and inscriere.idcurs=cursuri.idcurs and inscriere.idstudent=@idstud;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `seeStudents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `seeStudents`(in CNP varchar(20))
BEGIN
	set @idp = (select profesor.idprofesor from profesor where profesor.CNP = CNP);
    set @idc = (select informatii_profesor.idcurs from informatii_profesor where informatii_profesor.idprofesor = @idp);
    Select nume, prenume from student, inscriere where inscriere.idstudent = student.idstudent and inscriere.idcurs = @idc and inscriere.idprofesor = @idp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectUser`(IN tblName1 varchar (50), in nume varchar(45), in prenume varchar(20), in cnp varchar(15))
begin
  SET @ddl = CONCAT(' SELECT * FROM ', tblName1, ' where ' , tblName1, '.CNP="', cnp, '" and ',  tblName1,'.nume="', nume, '" and ',  tblName1,'.prenume="', prenume,'";');
  PREPARE STMT FROM @ddl;
  EXECUTE STMT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showActivitati` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showActivitati`( in nume_stud varchar(20), in prenume_stud varchar(20), in CNP_stud varchar(13))
begin
SET @idstudent1 = (SELECT student.idstudent
               from student
               where student.nume = nume_stud 
               and student.prenume = prenume_stud
               and  student.cnp = CNP_stud);

select nume_curs from cursuri, inscriere where inscriere.idstudent=@idstudent1 and cursuri.idcurs=inscriere.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showCourses` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showCourses`( in nume1 varchar(20))
begin
select nume_curs, nume from cursuri, profesor, informatii_profesor 
where cursuri.nume_curs=nume1 
and profesor.idprofesor=informatii_profesor.idprofesor 
and cursuri.idcurs=informatii_profesor.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showGroups` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showGroups`( in nume_curs varchar(45))
begin
SELECT grup_studiu.nume, grup_studiu.nr_max_studenti,cursuri.nume_curs
from grup_studiu,cursuri
where cursuri.idcurs = (SELECT cursuri.idcurs 
                      FROM cursuri
                      WHERE cursuri.nume_curs = nume_curs )
and grup_studiu.idcurs = cursuri.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showGrupuri` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showGrupuri`( in nume_stud varchar(20), in prenume_stud varchar(20), in CNP_stud varchar(13))
begin
SET @idstudent1 = (SELECT student.idstudent
               from student
               where student.nume = nume_stud 
               and student.prenume = prenume_stud
               and  student.cnp = CNP_stud);

select nume from grup_studiu, relatii_grup where relatii_grup.idstudent=@idstudent1 and grup_studiu.idgrup_studiu=relatii_grup.idgrup;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showInfoAdmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showInfoAdmin`( in CNP varchar(13))
begin
SELECT *
FROM administrator
WHERE CNP = administrator.CNP;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showInfoProf` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showInfoProf`( in CNP varchar(13))
begin
SELECT *
FROM profesor
WHERE CNP = profesor.CNP;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showInfoStud` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showInfoStud`( in CNP varchar(13))
begin
SELECT *
FROM student
WHERE CNP = student.CNP;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showInfoSuperAdmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showInfoSuperAdmin`( in CNP varchar(13))
begin
SELECT *
FROM super_administrator
WHERE CNP = super_administrator.CNP;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showMembers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showMembers`(in numeg varchar(20))
BEGIN
	set @idg = ( select grup_studiu.idgrup_studiu from grup_studiu where grup_studiu.nume = numeg);
    select nume, prenume from student, relatii_grup where relatii_grup.idgrup = @idg and student.idstudent = relatii_grup.idstudent;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showNotes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showNotes`(in CNP varchar(13) )
begin
SET @idstud = (SELECT student.idstudent
               from student
               where student.cnp = CNP);
select distinct  nume_curs, nota_curs, nota_laborator, nota_seminar 
from cursuri, curs_nota, laborator_nota, seminar_nota, student, note_student, inscriere 
where @idStud=student.idstudent and student.idstudent = note_student.idstudent 
and student.idstudent =  inscriere.idstudent 
and note_student.idlaborator=laborator_nota.idcurs
and note_student.idcurs=curs_nota.idcurs 
and note_student.idseminar=seminar_nota.idcurs
and note_student.idcurs=cursuri.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarProf` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarProf`(in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in zi varchar(10))
begin

select ora_lab from activitati join (select idcurs from informatii_profesor where informatii_profesor.idprofesor=(SELECT profesor.idprofesor
               from profesor
               where profesor.nume = nume 
               and profesor.prenume = prenume
               and  profesor.cnp = cnp) ) as cursuri on activitati.idcurs=cursuri.idcurs where activitati.zi_lab=zi;
select ora_sem from activitati join (select idcurs from informatii_profesor where informatii_profesor.idprofesor=(SELECT profesor.idprofesor
               from profesor
               where profesor.nume = nume 
               and profesor.prenume = prenume
               and  profesor.cnp = cnp) ) as cursuri on activitati.idcurs=cursuri.idcurs where activitati.zi_seminar=zi;
select ora_curs from activitati join (select idcurs from informatii_profesor where informatii_profesor.idprofesor=(SELECT profesor.idprofesor
               from profesor
               where profesor.nume = nume 
               and profesor.prenume = prenume
               and  profesor.cnp = cnp) ) as cursuri on activitati.idcurs=cursuri.idcurs where activitati.zi_curs=zi;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarProfCurs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarProfCurs`(in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in zi varchar(10))
begin
select ora_curs from activitati join (select idcurs from informatii_profesor where informatii_profesor.idprofesor=(SELECT profesor.idprofesor
               from profesor
               where profesor.nume = nume 
               and profesor.prenume = prenume
               and  profesor.cnp = cnp) ) as cursuri on activitati.idcurs=cursuri.idcurs where activitati.zi_curs=zi;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarProfLab` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarProfLab`(in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in zi varchar(10))
begin

select ora_lab from activitati join (select idcurs from informatii_profesor where informatii_profesor.idprofesor=(SELECT profesor.idprofesor
               from profesor
               where profesor.nume = nume 
               and profesor.prenume = prenume
               and  profesor.cnp = cnp) ) as cursuri on activitati.idcurs=cursuri.idcurs where activitati.zi_lab=zi;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarProfSem` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarProfSem`(in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in zi varchar(10))
begin
select ora_sem from activitati join (select idcurs from informatii_profesor where informatii_profesor.idprofesor=(SELECT profesor.idprofesor
               from profesor
               where profesor.nume = nume 
               and profesor.prenume = prenume
               and  profesor.cnp = cnp) ) as cursuri on activitati.idcurs=cursuri.idcurs where activitati.zi_seminar=zi;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarStud` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarStud`( in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in ziua varchar(45))
begin

SELECT orar_student.ora_curs, cursuri.nume_curs
FROM orar_student,cursuri
WHERE orar_student.idstudent = (SELECT student.idstudent from student where student.nume = nume and student.prenume = prenume and  student.cnp = cnp)
AND orar_student.zi_curs = ziua
AND cursuri.idcurs = orar_student.idcurs;

SELECT orar_student.ora_lab, cursuri.nume_curs
FROM orar_student,cursuri
WHERE orar_student.idstudent = (SELECT student.idstudent from student where student.nume = nume and student.prenume = prenume and  student.cnp = cnp)
AND orar_student.zi_lab = ziua
AND cursuri.idcurs = orar_student.idcurs;

SELECT orar_student.ora_sem, cursuri.nume_curs
FROM orar_student,cursuri
WHERE orar_student.idstudent = (SELECT student.idstudent from student where student.nume = nume and student.prenume = prenume and  student.cnp = cnp)
AND orar_student.zi_sem = ziua
AND cursuri.idcurs = orar_student.idcurs;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarStudCurs` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarStudCurs`(in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in zi varchar(10))
begin
SELECT orar_student.ora_curs, cursuri.nume_curs
FROM orar_student,cursuri
WHERE orar_student.idstudent = (SELECT student.idstudent from student where student.nume = nume and student.prenume = prenume and  student.cnp = cnp)
AND orar_student.zi_curs = zi
AND cursuri.idcurs = orar_student.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarStudLab` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarStudLab`(in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in zi varchar(10))
begin
SELECT orar_student.ora_lab, cursuri.nume_curs
FROM orar_student,cursuri
WHERE orar_student.idstudent = (SELECT student.idstudent from student where student.nume = nume and student.prenume = prenume and  student.cnp = cnp)
AND orar_student.zi_lab = zi
AND cursuri.idcurs = orar_student.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showOrarStudSem` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showOrarStudSem`(in nume varchar(20), in prenume varchar(20), in cnp varchar(20), in zi varchar(10))
begin
SELECT orar_student.ora_lab, cursuri.nume_curs
FROM orar_student,cursuri
WHERE orar_student.idstudent = (SELECT student.idstudent from student where student.nume = nume and student.prenume = prenume and  student.cnp = cnp)
AND orar_student.zi_sem = zi
AND cursuri.idcurs = orar_student.idcurs;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showStudentToACourse` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showStudentToACourse`(in curs varchar(20))
BEGIN
	set @idcurs1 = (select cursuri.idcurs from cursuri where cursuri.nume_curs = curs);
    select student.nume, student.prenume from student,inscriere where inscriere.idcurs = @idcurs1 and inscriere.idstudent = student.idstudent;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showSugestii` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showSugestii`(in CNPS varchar(20))
BEGIN
	set @ids = (select idstudent from student where student.CNP = CNPS);
    select distinct grup_studiu.nume from grup_studiu
    join inscriere on inscriere.idcurs = grup_studiu.idcurs 
     join relatii_grup on grup_studiu.idgrup_studiu = relatii_grup.idgrup 
    where inscriere.idstudent = @ids and not EXISTS (SELECT * FROM relatii_grup WHERE relatii_grup.idgrup and relatii_grup.idstudent = @ids);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `studentGroup` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `studentGroup`( in nume_stud varchar(20),in prenume_stud varchar(20),in CNP varchar(13), in nume_grup varchar(20))
begin
SET @idstudent1 = (SELECT student.idstudent
               from student
               where student.nume = nume_stud 
               and student.prenume = prenume_stud
               and  student.cnp = CNP);
set @idcurs1 = ( SELECT grup_studiu.idcurs
                from grup_studiu
                where grup_studiu.nume = nume_grup );
set @ID=( SELECT MAX(relatii_grup.idrelatii_grup) FROM relatii_grup) + 1;
if @ID IS NULL then
set @ID=1;
end if;
set @idstudent =(select idstudent from inscriere where inscriere.idstudent=@idstudent1 and inscriere.idcurs=@idcurs1);
SET @nrStudActual =(select nr_actual_studenti from grup_studiu where grup_studiu.idcurs=@idcurs1 and grup_studiu.nume = nume_grup);
SET @nrStudMax =(select nr_max_studenti from grup_studiu where grup_studiu.idcurs=@idcurs1  and grup_studiu.nume = nume_grup);
SET @idgrup = (select idgrup_studiu from grup_studiu where grup_studiu.idcurs=@idcurs1  and grup_studiu.nume = nume_grup);
 if @idstudent is not null then
  if(@nrStudActual<@nrStudMax) then
   insert into proiect.relatii_grup values (@ID, @idstudent1, @idgrup);
   update grup_studiu set grup_studiu.nr_actual_studenti= grup_studiu.nr_actual_studenti+1 where grup_studiu.idcurs= @idcurs1  and grup_studiu.nume = nume_grup;
 end if;
 end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `studentRegistration` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `studentRegistration`( in nume_stud varchar(20), in prenume_stud varchar(20), in CNP_stud varchar(13), in nume_prof varchar(20),in prenume_prof varchar(20), in nume_curs varchar(45))
begin
SET @idstudent1 = (SELECT student.idstudent
               from student
               where student.nume = nume_stud 
               and student.prenume = prenume_stud
               and  student.cnp = CNP_stud);
SET @idprofesor1 = (SELECT profesor.idprofesor
               from cursuri,informatii_profesor,profesor
               where profesor.nume = nume_prof
               and profesor.prenume = prenume_prof
               and cursuri.idcurs = informatii_profesor.idcurs
			   and informatii_profesor.idprofesor = profesor.idprofesor
               and cursuri.nume_curs = nume_curs);
set @idcurs1 = ( SELECT cursuri.idcurs
                from cursuri,informatii_profesor,profesor
                where cursuri.nume_curs = nume_curs
                and cursuri.idcurs = informatii_profesor.idcurs
                and informatii_profesor.idprofesor = profesor.idprofesor
                and profesor.nume = nume_prof
                and profesor.prenume = prenume_prof );
set @data_inscriere1 = curdate();
set @ID=( SELECT MAX(orar_student.idorar_student) FROM orar_student) + 1;
if @ID IS NULL then
set @ID=1;
end if;

set @ID1=( SELECT MAX(inscriere.idinscriere) FROM inscriere) + 1;
if @ID1 IS NULL then
set @ID1=1;
end if;

SET @nrStudActual =(select nr_actual_studenti from cursuri where cursuri.idcurs=@idcurs1);
SET @nrStudMax =(select nr_max_studenti from cursuri where cursuri.idcurs=@idcurs1);

set @ziualab= (select zi_lab from activitati  where activitati.idcurs=@idcurs1);
set @ziuacurs= (select zi_curs from activitati where activitati.idcurs=@idcurs1);
set @ziuasem= (select zi_seminar from activitati where activitati.idcurs=@idcurs1);

set @oracurs= (select ora_curs from activitati where activitati.idcurs=@idcurs1);
set @oralab= (select ora_lab from activitati where activitati.idcurs=@idcurs1);
set @orasem= (select ora_sem from activitati where activitati.idcurs=@idcurs1);

-- set @ziualab1= (select zi_lab from orar_student  where   orar_student.idstudent=idstudent1 and @ziualab=orar_student.zi_lab and @oralab<>orar_student.ora_lab);
-- set @ziuacurs1= (select zi_curs from orar_student where      orar_student.idstudent=idstudent1 and @ziuacurs=orar_student.zi_curs and @oracurs<>orar_student.ora_curs);
-- set @ziuasem1= (select zi_sem from orar_student where   orar_student.idstudent=idstudent1 and  @ziuasem=orar_student.zi_sem and @orasem<>orar_student.ora_sem);
 set @oracurs1= NULL;
set @oralab1= NULL;
set @orasem1= NULL;

set @idstud =  (select count(orar_student.idstudent) from orar_student where orar_student.idstudent= @idstudent1);
(select orar_student.idstudent from orar_student where orar_student.idstudent= @idstudent1);
if(@idstud >0 ) then
if(@ziuacurs="Luni")then
set @oracurs1= (select ora_curs from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Luni" and @oracurs=orar_student.ora_curs) or (orar_student.zi_sem="Luni" and  @oracurs=orar_student.ora_sem) or (orar_student.zi_lab="Luni" and  @oracurs=orar_student.ora_lab)));
end if;
-- (select ora_curs from orar_student where  orar_student.idstudent=idstudent1 and (orar_student.zi_curs="Luni" and @oracurs=orar_student.ora_curs) or (orar_student.zi_sem="Luni" and @orasem=orar_student.ora_curs) or (orar_student.zi_lab="Luni" and @oralab=orar_student.ora_curs));
 
 if(@ziuacurs="Marti")then
set @oracurs1= (select ora_curs from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Marti" and @oracurs=orar_student.ora_curs) or (orar_student.zi_sem="Marti" and @oracurs=orar_student.ora_sem) or (orar_student.zi_lab="Marti" and @oracurs=orar_student.ora_lab)));
end if;
(select ora_curs from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Marti" and @oracurs=orar_student.ora_curs) or (orar_student.zi_sem="Marti" and @orasem=orar_student.ora_sem) or (orar_student.zi_lab="Marti" and @oralab=orar_student.ora_lab)));
if(@ziuacurs="Miercuri")then
set @oracurs1= (select ora_curs from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Miercuri" and @oracurs=orar_student.ora_curs) or (orar_student.zi_sem="Miercuri" and  @oracurs=orar_student.ora_sem) or (orar_student.zi_lab="Miercuri" and @oralab=orar_student.ora_lab)));
end if;

if(@ziuacurs="Joi")then
set @oracurs1= (select ora_curs from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Joi" and @oracurs=orar_student.ora_curs) or (orar_student.zi_sem="Joi" and  @oracurs=orar_student.ora_sem) or (orar_student.zi_lab="Joi" and  @oracurs=orar_student.ora_lab)));
end if;

if(@ziuacurs="Vineri")then
set @oracurs1= (select ora_curs from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Vineri" and @oracurs=orar_student.ora_curs) or (orar_student.zi_sem="Vineri" and  @oracurs=orar_student.ora_sem) or (orar_student.zi_lab="Vineri" and  @oracurs=orar_student.ora_lab)));
end if;

--
if(@ziualab="Luni")then
set @oralab1= (select ora_lab from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Luni" and @oralab=orar_student.ora_curs) or (orar_student.zi_sem="Luni" and @oralab=orar_student.ora_sem) or (orar_student.zi_lab="Luni" and @oralab=orar_student.ora_lab)));
end if;
 
 if(@ziualab="Marti")then
set @oralab1= (select ora_lab from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Marti" and @oralab=orar_student.ora_curs) or (orar_student.zi_sem="Marti" and @oralab=orar_student.ora_sem) or (orar_student.zi_lab="Marti" and @oralab=orar_student.ora_lab)));
end if;

if(@ziualab="Miercuri")then
set @oralab1= (select ora_lab from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Miercuri" and @oralabs=orar_student.ora_curs) or (orar_student.zi_sem="Miercuri" and @oralab=orar_student.ora_sem) or (orar_student.zi_lab="Miercuri" and @oralab=orar_student.ora_lab)));
end if;

if(@ziualab="Joi")then
set @oralab1= (select ora_lab  from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Joi" and @oralab=orar_student.ora_curs) or (orar_student.zi_sem="Joi" and @oralab=orar_student.ora_sem) or (orar_student.zi_lab="Joi" and @oralab=orar_student.ora_lab)));
end if;

if(@ziualab="Vineri")then
set @oralab1= (select ora_lab  from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Vineri" and @oralab=orar_student.ora_curs) or (orar_student.zi_sem="Vineri" and @oralab=orar_student.ora_sem) or (orar_student.zi_lab="Vineri" and @oralab=orar_student.ora_lab)));
end if;
-- (select ora_lab  from orar_student where  orar_student.idstudent=idstudent1 and (orar_student.zi_curs="Vineri" and @oracurs=orar_student.ora_lab) or (orar_student.zi_sem="Vineri" and @orasem=orar_student.ora_lab) or (orar_student.zi_lab="Vineri" and @oralab=orar_student.ora_lab));


if(@ziuasem="Luni")then
set @orasem1= (select ora_sem from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Luni" and @orasem=orar_student.ora_curs) or (orar_student.zi_sem="Luni" and @orasem=orar_student.ora_sem) or (orar_student.zi_lab="Luni" and @orasem=orar_student.ora_lab)));
end if;
 
 if(@ziuasem="Marti")then
set @orasem1= (select ora_sem from orar_student where orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Marti" and @orasem=orar_student.ora_curs) or (orar_student.zi_sem="Marti" and @orasem=orar_student.ora_sem) or (orar_student.zi_lab="Marti" and @orasem=orar_student.ora_lab)));
end if;
-- select ora_sem from orar_student where  orar_student.idstudent=idstudent1 and (orar_student.zi_sem="Marti" and @orasem=orar_student.ora_sem);
if(@ziuasem="Miercuri")then
set @orasem1= (select ora_sem from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Miercuri" and @orasem=orar_student.ora_curs) or (orar_student.zi_sem="Miercuri" and @orasem=orar_student.ora_sem) or (orar_student.zi_lab="Miercuri" and @oralab=orar_student.ora_lab)));
end if;

if(@ziuasem="Joi")then
set @orasem1= (select ora_sem  from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Joi" and @orasem=orar_student.ora_curs) or (orar_student.zi_sem="Joi" and @orasem=orar_student.ora_sem) or (orar_student.zi_lab="Joi" and @orasem=orar_student.ora_lab)));
end if;

if(@ziuasem="Vineri")then
set @orasem1= (select ora_sem  from orar_student where  orar_student.idstudent=@idstudent1 and ((orar_student.zi_curs="Vineri" and @orasem=orar_student.ora_curs) or (orar_student.zi_sem="Vineri" and @orasem=orar_student.ora_sem) or (orar_student.zi_lab="Vineri" and @orasem=orar_student.ora_lab)));
end if;
--
end if;

 
if((@oracurs1 is null) and (@orasem1 is  null) and (@oralab1 is null)) then
  if(@nrStudActual<@nrStudMax) then
insert into proiect.inscriere (idinscriere, idstudent, idprofesor,idcurs,data_inscriere) 
values  (@ID1, @idstudent1, @idprofesor1,@idcurs1,@data_inscriere1);
update cursuri set cursuri.nr_actual_studenti= cursuri.nr_actual_studenti+1 where cursuri.idcurs= @idcurs1;
insert into proiect.orar_student values(@ID, @idstudent1, @idcurs1, @ziuacurs, @ziualab, @ziuasem, @oracurs, @oralab, @orasem);
end if;
end if;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `studentToAGroupActivity` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `studentToAGroupActivity`(in nume_stud varchar(20), in prenume_stud varchar(20),in CNP varchar(15), in nume_grup varchar(20), in nume_activ varchar(20))
begin
set @ID=( SELECT MAX(studenti_actvititate.idstudenti_actvititate) FROM studenti_actvititate) + 1;
if @ID IS NULL then
set @ID=1;
end if;
SET @idstud = (SELECT student.idstudent
from student
where student.nume = nume_stud
and student.prenume = prenume_stud
and student.cnp = CNP);
set @idgrup =(select idgrup_studiu from grup_studiu where grup_studiu.nume=nume_grup);
set @idstud1 = (select relatii_grup.idstudent from relatii_grup where relatii_grup.idstudent=@idstud and relatii_grup.idgrup=@idgrup);
set @idactiv= (select idactiv_grup from activ_grup where nume_activ=activ_grup.nume_activ and activ_grup.id_grup_studiu = @idgrup);

if(@idstud1 is not null)then
update activ_grup set activ_grup.nr_actual = activ_grup.nr_actual+1 where nume_activ=activ_grup.nume_activ and activ_grup.id_grup_studiu = @idgrup;
insert into proiect.studenti_actvititate values(@ID, @idstud, @idactiv, @idgrup);
end if;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateactivitati` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateactivitati`( in nume varchar(45),in curspr int, in seminarpr int, in labpr int,in a varchar(10),in a1 varchar(10),in a2 varchar(10),in a3 varchar(10),in a4 varchar(10),in a5 varchar(10))
begin
set @id =(select activitati.idcurs from activitati, cursuri where cursuri.nume_curs=nume and cursuri.idcurs=activitati.idcurs);
update activitati set activitati.laboratorpr=labpr where activitati.idcurs=@id;
update activitati set activitati.seminarpr=seminarpr where activitati.idcurs=@id;
update activitati set activitati.curspr=curspr where activitati.idcurs=@id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateactivitatiproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateactivitatiproc`( in nume varchar(45),in curspr int, in seminarpr int, in labpr int)
begin
set @id =(select activitati.idcurs from activitati, cursuri where cursuri.nume_curs=nume and cursuri.idcurs=activitati.idcurs);
update activitati set activitati.laboratorpr=labpr where activitati.idcurs=@id;
update activitati set activitati.seminarpr=seminarpr where activitati.idcurs=@id;
update activitati set activitati.curspr=curspr where activitati.idcurs=@id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateadministrator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateadministrator`( in cnp varchar(13), in nume varchar(20), in prenume varchar(20), in adresa varchar(10), in telefon varchar(10), in email varchar(20), in iban varchar (16), in nr_contact varchar(10))
BEGIN

if nume IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE administrator 
SET administrator.nume = nume
where administrator.CNP = cnp; 
end if;

if prenume is not null then
SET SQL_SAFE_UPDATES=0;
UPDATE administrator 
SET administrator.prenume = prenume
where administrator.CNP = cnp; 
end if;

if(adresa IS NOT null) then
SET SQL_SAFE_UPDATES=0;
UPDATE administrator
SET administrator.adresa = adresa
where administrator.CNP = cnp; 
end if;

if(telefon is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE administrator
SET administrator.telefon = telefon
where administrator.CNP = cnp; 
end if;

if(email is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE administrator
SET administrator.email = email
where administrator.CNP = cnp; 
end if;

if(iban is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE administrator 
SET administrator.IBAN = iban
where administrator.CNP = cnp; 
end if;

if(nr_contact is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE administrator 
SET administrator.nr_contract = nr_contact
where administrator.CNP = cnp; 
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updatecursuri` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatecursuri`(in nume_prof varchar(20), in prenume_prof varchar(20), in numeinit varchar(45),in nume varchar(45),in descriere varchar(45), in nr_max_stud int,in curspr int, in seminarpr int, in labpr int)
begin

set @idcurs = ( SELECT cursuri.idcurs
                from cursuri,informatii_profesor,profesor
                where cursuri.nume_curs = numeinit
                and cursuri.idcurs = informatii_profesor.idcurs
                and informatii_profesor.idprofesor = profesor.idprofesor
                and profesor.nume = nume_prof
                and profesor.prenume = prenume_prof );

if nume IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE cursuri 
SET cursuri.nume_curs = nume
where cursuri.idcurs = @idcurs; 
end if;

if descriere IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE cursuri 
SET cursuri.descriere = descriere
where cursuri.idcurs = @idcurs; 
end if;

if nr_max_stud IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE cursuri 
SET cursuri.nr_max_studenti = nr_max_stud
where cursuri.idcurs = @idcurs; 
end if;

if curspr IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE activitati 
SET activitati.curspr = curspr
where activitati.idcurs = @idcurs; 
end if;

if seminarpr IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE activitati 
SET activitati.seminarpr = seminarpr
where activitati.idcurs = @idcurs; 
end if;

if labpr IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE activitati 
SET activitati.laborator.pr = labpr
where activitati.idcurs = @idcurs; 
end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updategrup_studiu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updategrup_studiu`( in nume_init varchar(20), in nume varchar(20), in nr_max_studenti int ,in a varchar(10),in a1 varchar(10),in a2 varchar(10),in a3 varchar(10),in a4 varchar(10),in a5 varchar(10),in a6 varchar(10))
begin
SET @idgrup_studiu = (SELECT grup_studiu.idgrup_studiu
                      from grup_studiu
                      where grup_studiu.nume = nume_init);
if nume IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE grup_studiu 
SET grup_studiu.nume = nume
where grup_studiu.idgrup_studiu = @idgrup_studiu; 
end if;

if nr_max_studenti IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE grup_studiu 
SET grup_studiu.nr_max_studenti = nr_max_studenti
where grup_studiu.idgrup_studiu = @idgrup_studiu; 
end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateprofesor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateprofesor`( in cnp varchar(13), in nume varchar(20), in prenume varchar(20), in adresa varchar(10), in telefon varchar(10), in email varchar(20), in iban varchar (16), in nr_contact varchar(10))
BEGIN
SET @idprof = (SELECT profesor.idprofesor
               from profesor where profesor.cnp = cnp);

if nume IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE profesor 
SET profesor.nume = nume
where profesor.idprofesor = @idprof; 
end if;

if prenume is not null then
SET SQL_SAFE_UPDATES=0;
UPDATE profesor 
SET profesor.prenume = prenume
where profesor.idprofesor = @idprof; 
end if;

if(adresa IS NOT null) then
SET SQL_SAFE_UPDATES=0;
UPDATE profesor
SET profesor.adresa = adresa
where profesor.idprofesor = @idprof; 
end if;

if(telefon is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE profesor
SET profesor.telefon = telefon
where profesor.idprofesor = @idprof; 
end if;

if(email is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE profesor
SET profesor.email = email
where profesor.idprofesor = @idprof; 
end if;

if(iban is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE profesor 
SET profesor.IBAN = iban
where profesor.idprofesor = @idprof; 
end if;

if(nr_contact is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE profesor 
SET profesor.nr_contract = nr_contact
where profesor.idprofesor = @idprof; 
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updatestudent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatestudent`(in cnp varchar(13), in nume varchar(20), in prenume varchar(20), in adresa varchar(10), in telefon varchar(10), in email varchar(20), in iban varchar (16), in nr_contact varchar(10))
BEGIN
SET @idstud = (SELECT student.idstudent
               from student
               where  student.cnp = cnp);
if cnp IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE student 
SET student.cnp = cnp
where student.idstudent = @idstud; 
end if;

if nume IS NOT NULL then
SET SQL_SAFE_UPDATES=0;
UPDATE student 
SET student.nume = nume
where student.idstudent = @idstud; 
end if;

if prenume is not null then
SET SQL_SAFE_UPDATES=0;
UPDATE student 
SET student.prenume = prenume
where student.idstudent = @idstud; 
end if;

if(adresa IS NOT null) then
SET SQL_SAFE_UPDATES=0;
UPDATE student 
SET student.adresa = adresa
where student.idstudent = @idstud; 
end if;

if(telefon is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE student
SET student.telefon = telefon
where student.idstudent = @idstud; 
end if;

if(email is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE student
SET student.email = email
where student.idstudent = @idstud; 
end if;

if(iban is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE student 
SET student.IBAN = iban
where student.idstudent = @idstud; 
end if;

if(nr_contact is not null) then
SET SQL_SAFE_UPDATES=0;
UPDATE student 
SET student.nr_contract = nr_contact
where student.idstudent = @idstud; 
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `veziActiv` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `veziActiv`(in CNP1 varchar(20))
BEGIN
	set @ids = (select student.idstudent from student where student.CNP = CNP1);
    select nume_activ,data_desf from activ_grup, relatii_grup where relatii_grup.idstudent = @ids and activ_grup.id_grup_studiu = relatii_grup.idgrup;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-18 15:03:52
