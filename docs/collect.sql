/* 태그 게시글수 카운트 */
(select count(*)
from article a
	left outer join arc_tag at on a.arno = at.arno
	left outer join tag t on at.tno = t.tno
	left outer join user u on a.writer = u.uno
where 
	t.tno = 1 and u.uno = 11
) as arc_count;

/* 태그 댓글수 카운트 */
(select count(*)
from comment c
	left outer join arc_tag at on c.arno = at.arno
	left outer join tag t on at.tno = t.tno
	left outer join user u on c.uno = u.uno
where 
	t.tno = 1 and u.uno = 11
) as cmt_count;

/* 가입일수 카운트 */
select datediff(now(), cdt) from user; 

/* 팔로워수 카운트 */
(select count(*) 
from follow f 
inner join user u2 
on f.fwer_no=u2.uno 
where u.uno = f.fwing_no 
and u2.state=1) as fwer_cnt