# --- !Ups

create table "USER" (
  "ID"          VARCHAR NOT NULL PRIMARY KEY,
  "USERNAME"    VARCHAR NOT NULL,
  "EMAIL"       VARCHAR NOT NULL,
  "START_DATE"  TIMESTAMP(0) DEFAULT NOW() NOT NULL,
  "END_DATE"    TIMESTAMP(0) DEFAULT NULL  NULL
);

INSERT INTO "USER" ("ID", "USERNAME", "EMAIL", "START_DATE", "END_DATE") VALUES ('1', 'luiscocr', 'anthonydicortes@gmail.com', '2021-01-30 12:00:00', NULL);
INSERT INTO "USER" ("ID", "USERNAME", "EMAIL", "START_DATE", "END_DATE") VALUES ('2', 'carlvare', 'carlvare@gmail.com', NOW(), NULL);

# --- !Downs

DROP TABLE "USER" if EXISTS;