
/* Category Table */
INSERT into Category(id,name)
  values(1,'Country');
INSERT into Category(id,name)
  values(2,'Animal');

/* Team Table */
INSERT into Team(id,name)
  values(1,'Red Team');
INSERT into Team(id,name)
  values(2,'Blue Team');

  /* Round Table */
INSERT into Round(id,name)
  values(1,'Round One');
INSERT into Round(id,name)
  values(2,'Round Two');
INSERT into Round(id,name)
  values(3,'Round Three');
INSERT into Round(id,name)
  values(4,'Round Four');
  INSERT into Round(id,name)
  values(5,'Round Five');

/* Word Table */
INSERT into Word(id,name,category_id)
  values(1,'France',1);
INSERT into Word(id,name,category_id)
  values(2,'Cat',2);
  INSERT into Word(id,name,category_id)
  values(3,'Germany',1);
INSERT into Word(id,name,category_id)
  values(4,'Lizard',2);
  INSERT into Word(id,name,category_id)
  values(5,'Eritrea',1);
INSERT into Word(id,name,category_id)
  values(6,'Lion',2);

/* Member Table */
INSERT into Member(id,name,team_id)
  values(1,'Tapas',1);
INSERT into Member(id,name,team_id)
  values(2,'Anamika',2);
