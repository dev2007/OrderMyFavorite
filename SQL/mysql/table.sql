###################################################
#1.custom TABLE

CREATE TABLE `orderdb`.`custom` (
  `idCustom` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(200) NULL,
  `age` INT NULL,
  `sex` INT NULL DEFAULT 1,
  `phoneNumber` VARCHAR(100) NULL,
  PRIMARY KEY (`idCustom`));
  
###################################################
#2.sex TABLE
#1 is male
#0 is female

CREATE TABLE `orderdb`.`sex` (
  `idsex` INT NOT NULL,
  `fullname` VARCHAR(45) NULL,
  PRIMARY KEY (`idsex`))
ENGINE = InnoDB;

INSERT INTO sex(idsex,fullname)
VALUES(1,'ÄÐ'),(0,'Å®')

###################################################
#3.orderstate TABLE

CREATE TABLE `orderdb`.`orderstate` (
  `idorderstate` INT NOT NULL,
  `fullname` VARCHAR(100) NULL,
  PRIMARY KEY (`idorderstate`));

INSERT INTO orderstate(idorderstate,fullname)
VALUES(1,'Ô¤¶¨'),(2,'¿ªÌ¨'),(3,'¹ýÆÚ'),(4,'¸ÄÆÚ')
###################################################
#4.tablestate TABLE

CREATE TABLE `orderdb`.`tablestate` (
  `idtablestate` INT NOT NULL,
  `fullname` VARCHAR(100) NULL,
  PRIMARY KEY (`idtablestate`));

INSERT INTO tablestate(idtablestate,fullname)
VALUES(1,'¿Õ×À'),(2,'Ô¤¶¨'),(3,'¾Í²Í'),(4,'Æ´×À¾Í²Í'),(5,'Í£ÓÃ')

###################################################
#5.ordertable TABLE

CREATE TABLE `orderdb`.`ordertable` (
  `idordertable` INT NOT NULL AUTO_INCREMENT,
  `customid` INT NULL,
  `tableid` INT NULL,
  `orderdatetime` DATETIME NULL,
  `orderstateid` INT NULL,
  PRIMARY KEY (`idordertable`));
  
###################################################
#6.waiter TABLE

CREATE TABLE `orderdb`.`waiter` (
  `idwaiter` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(45) NULL,
  `age` INT NULL,
  `sex` INT NULL,
  `phonenumber` VARCHAR(100) NULL,
  PRIMARY KEY (`idwaiter`));

###################################################
#7.goodsbase TABLE

CREATE TABLE `orderdb`.`goodsbase` (
  `idgoodsbase` INT NOT NULL AUTO_INCREMENT,
  `unitprice` BLOB NULL,
  `fullname` VARCHAR(200) NULL,
  `imageurl` VARCHAR(2000) NULL,
  `description` VARCHAR(2000) NULL,
  PRIMARY KEY (`idgoodsbase`));

###################################################
#8.dish TABLE

CREATE TABLE `orderdb`.`dish` (
  `iddish` INT NOT NULL AUTO_INCREMENT,
  `baseid` INT NOT NULL,
  `cando` INT NULL,
  `dishtypeid` INT NOT NULL,
  PRIMARY KEY (`iddish`));

###################################################
#9.dishtype TABLE

CREATE TABLE `orderdb`.`dishtype` (
  `iddishtype` INT NOT NULL,
  `fullname` VARCHAR(200) NULL,
  PRIMARY KEY (`iddishtype`));

INSERT INTO dishtype(iddishtype,fullname)
VALUES	(1,'Á¹²Ë'),
		(2,'ÉÕ²Ë'),
		(3,'Õô²Ë'),
		(4,'ìÀ²Ë'),
		(5,'³´²Ë'),
		(6,'»ð¹ø'),
		(7,'ÉÕ¿¾'),
		(8,'Ð¡³Ô')
		
###################################################
#10.winewater TABLE

CREATE TABLE `orderdb`.`winewater` (
  `idwinewater` INT NOT NULL AUTO_INCREMENT,
  `baseid` INT NULL,
  `storenumber` INT NULL,
  `brand` VARCHAR(200) NULL,
  PRIMARY KEY (`idwinewater`));


###################################################
#11.dinningtable TABLE

CREATE TABLE `orderdb`.`dinningtable` (
  `idtable` INT NOT NULL AUTO_INCREMENT,
  `tableno` VARCHAR(100) NULL,
  `servicecount` INT NULL,
  `tablestateid` INT NULL,
  PRIMARY KEY (`idtable`));


###################################################
#12.flowrecord TABLE 

CREATE TABLE `orderdb`.`flowrecord` (
  `idflowrecord` INT NOT NULL AUTO_INCREMENT,
  `tableid` INT NULL,
  `businessdate` DATETIME NULL,
  PRIMARY KEY (`idflowrecord`));
  
###################################################
#13.flowattach TABLE

CREATE TABLE `orderdb`.`flowattach` (
  `idflowattach` INT NOT NULL AUTO_INCREMENT,
  `flowrecordid` INT NULL,
  `startdatetime` DATETIME NULL,
  `enddatetime` DATETIME NULL,
  `waiterid` INT NULL,
  `dinnerstateid` INT NULL,
  PRIMARY KEY (`idflowattach`));
  
###################################################
#14.ordermenuindex TABLE

CREATE TABLE `orderdb`.`ordermenuindex` (
  `idorderindex` INT NOT NULL AUTO_INCREMENT,
  `flowrecordid` INT NULL,
  `totaldish` INT NULL,
  `totalcash` DECIMAL NULL,
  `paycash` DECIMAL NULL,
  PRIMARY KEY (`idorderindex`));
 
###################################################
#15. ordermenudetails TABLE

CREATE TABLE `orderdb`.`ordermenudetails` (
  `idorderdetails` INT NOT NULL AUTO_INCREMENT,
  `orderindexid` INT NULL,
  `goodsbaseid` INT NULL,
  `currentprice` DECIMAL NULL,
  PRIMARY KEY (`idorderdetails`));
  
###################################################
#16.operator TABLE

CREATE TABLE `orderdb`.`operator` (
  `idoperator` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(200) NULL,
  `username` VARCHAR(100) NULL,
  `password` VARCHAR(200) NULL,
  `roleid` INT NULL,
  PRIMARY KEY (`idoperator`));

###################################################
#17.ROLE TABLE

CREATE TABLE `orderdb`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(200) NULL,
  PRIMARY KEY (`idrole`));

###################################################
#18.rolemenu TABLE

CREATE TABLE `orderdb`.`rolemenu` (
  `idrolemenu` INT NOT NULL AUTO_INCREMENT,
  `roleid` INT NULL,
  `menuid` INT NULL,
  PRIMARY KEY (`idrolemenu`));

###################################################
#19.menu TABLE

CREATE TABLE `orderdb`.`menu` (
  `idmenu` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(200) NULL,
  PRIMARY KEY (`idmenu`));

###################################################
#20.operatelog TABLE

CREATE TABLE `orderdb`.`operatelog` (
  `idoperatelog` INT NOT NULL AUTO_INCREMENT,
  `operatorid` INT NULL,
  `operatedatetime` DATETIME NULL,
  `action` VARCHAR(2000) NULL,
  PRIMARY KEY (`idoperatelog`));
