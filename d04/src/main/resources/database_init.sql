-- database initialization script

START TRANSACTION;

-- tables must be dropped in the reversed order of their FK relationships

DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Service;
DROP TABLE IF EXISTS Section;
DROP TABLE IF EXISTS Store;
DROP TABLE IF EXISTS StoreType;
DROP TABLE IF EXISTS Person;
DROP PROCEDURE IF EXISTS getProductsCount;

CREATE TABLE StoreType (
  id   INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL
);

CREATE TABLE Store (
  id            INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(30) NOT NULL,
  location      VARCHAR(20) NOT NULL,
  storeTypeId   INTEGER,
  parentStoreId INTEGER              DEFAULT NULL,
  email         VARCHAR(200),
  phoneNumber   CHAR(10),

  FOREIGN KEY fk_parentStore(parentStoreId) REFERENCES Store (id)
    ON DELETE CASCADE,
  FOREIGN KEY fk_storeType(storeTypeId) REFERENCES StoreType (id)
    ON DELETE CASCADE
);

CREATE TABLE Section (
  id      INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(50) NOT NULL,
  storeId INTEGER     NOT NULL,

  FOREIGN KEY fk_store(storeId) REFERENCES Store (id)
    ON DELETE CASCADE
);

CREATE TABLE Person (
  id   INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE Product (
  id        INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name      VARCHAR(50)            NOT NULL,
  sectionId INTEGER                NOT NULL,

  FOREIGN KEY fk_store(sectionId) REFERENCES Section (id)
    ON DELETE CASCADE
);

CREATE TABLE Service (
  id        INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(50) NOT NULL,
  sectionId INTEGER     NOT NULL,
  FOREIGN KEY fk_section(sectionId) REFERENCES Section (id)
    ON DELETE CASCADE
);

CREATE PROCEDURE getProductsCount(OUT count INT)
  BEGIN
    SELECT COUNT(*)
    INTO count
    FROM Product;
  END;

ALTER TABLE Store
  ADD COLUMN manager INTEGER;
ALTER TABLE Store
  ADD FOREIGN KEY fk_store (manager) REFERENCES Person (id);
ALTER TABLE Product
  ADD COLUMN stock INTEGER;

INSERT INTO Person (id, name) VALUES (1, 'Sef1');
INSERT INTO Person (id, name) VALUES (2, 'Sef2');
INSERT INTO Person (id, name) VALUES (3, 'Sef3');

INSERT INTO StoreType (id, name) VALUES (1, 'Electronics');
INSERT INTO StoreType (id, name) VALUES (2, 'Clothes');

INSERT INTO Store (id, name, location, storeTypeId, parentStoreId, manager, email, phoneNumber)
VALUES (1, 'Mall', 'TM', NULL, NULL, 1, 'a@b.c', '1234567890');
INSERT INTO Store (id, name, location, storeTypeId, parentStoreId, manager, email, phoneNumber)
VALUES (2, 'Media Galaxy', '1st floor', 1, 1, 1, 'x@y.z', '5839295593');
INSERT INTO Store (id, name, location, storeTypeId, parentStoreId, manager, email, phoneNumber)
VALUES (3, 'Zara', '2nd floor', 2, 2, 3, 'm@n.p', '7483961285');

INSERT INTO Section (id, name, storeId) VALUES (1, 'Laptops', 2);
INSERT INTO Section (id, name, storeId) VALUES (2, 'Monitors', 2);
INSERT INTO Section (id, name, storeId) VALUES (3, 'T-shirts', 3);
INSERT INTO Section (id, name, storeId) VALUES (4, 'Blouses', 3);
INSERT INTO Section (id, name, storeId) VALUES (5, 'Services', 3);

INSERT INTO Product (id, name, sectionId, stock) VALUES (1, 'Dell XPS 9360', 5, 1);
INSERT INTO Product (id, name, sectionId, stock) VALUES (2, 'Asus UX530', 1, 5);
INSERT INTO Product (id, name, sectionId, stock) VALUES (3, 'Samsung CF791', 5, 10);
INSERT INTO Product (id, name, sectionId, stock) VALUES (4, 'Dell P4317Q', 2, 0);
INSERT INTO Product (id, name, sectionId, stock) VALUES (5, 'White', 3, 3);
INSERT INTO Product (id, name, sectionId, stock) VALUES (6, 'Red', 3, 2);

INSERT INTO Service (id, name, sectionId) VALUES (1, 'Nails', 5);
INSERT INTO Service (id, name, sectionId) VALUES (2, 'Hair', 5);
INSERT INTO Service (id, name, sectionId) VALUES (3, 'Cosmetics', 5);

COMMIT;