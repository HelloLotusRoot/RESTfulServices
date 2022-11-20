insert into users values(90001, NOW(), 'User1', 'test1111', '701010-1111111');
insert into users values(90002, NOW(), 'User2', 'test2222', '801010-2222222');
insert into users values(90003, NOW(), 'User3', 'test3333', '901010-3333333');

insert into boards values(10001, 'hello first', NOW(), 'My first post', 90001);
insert into boards values(10002, 'hello second', NOW(), 'My second post', 90001);