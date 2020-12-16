select 
  feed.arno,
  feed.tno,
  feed.name,
  feed.cdt
from (
	select
	  a.arno,
	  at.tno,
	  t.name
	from
	  article a 
	  inner join follow f on a.writer = f.fwing_no
	  inner join arc_tag at on at.arno = a.arno
	  inner join tag t on at.tno = t.tno
	  inner join user u on a.writer = u.uno
	where 
	  fwer_no = 11
	  
	union
	
	select
	  at.arno,
	  at.tno,
	  t.name
	from 
	  arc_tag at
	  inner join usr_tag ut on at.tno = ut.tno
	  inner join tag t on at.tno = t.tno
	  inner join article a on at.arno = a.arno
	  inner join user u on a.writer = u.uno
	where 
	  ut.uno = 11
) feed
order by feed.arno desc