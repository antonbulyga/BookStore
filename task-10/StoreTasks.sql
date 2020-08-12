-- Задание 1
select model, speed, hd from pc where price < 500; 

-- Задание 2
select maker from Product where type = 'Printer' group by maker;
 
-- Задание 3
select model, hd, screen from laptop where price > 1000; 

-- Задание 4
select * from printer where color = 'y';

-- Задание 5
select model, cd, hd from pc where cd = '12x' or cd = '24x' and price < 600; 

-- Задание 6
select distinct product.maker, laptop.speed from laptop join product on laptop.model = product.model where hd >= 100;
 
-- Задание 7
select distinct product.model, pc.price from pc join product on product.model = pc.model where maker = 'B' 
union 
select distinct product.model, laptop.price from laptop join product on product.model = laptop.model where maker = 'B'  
union
select distinct product.model,printer.price from printer join product on product.model = printer.model where maker = 'B';

-- Задание 8
 select distinct maker from product where type = 'pc' and maker not in							   
 (select distinct maker formproduct where type = 'laptop');
 
 -- Задание 9
select distinct product.maker from product join pc on product.model = pc.model where speed >= 450;  

-- Задание 10
select model, price from printer
where price = (select max(price) from printer);       
                                     
-- Задание 11
select AVG(speed) from pc;   
                                                              
-- Задание 12
select AVG(speed) from laptop where price > 1000;       
                                   
-- Задание 13
select AVG(speed) from pc join product on pc.model = product.model                         
where maker = 'A'; 

-- Задание 14
select speed, AVG(price) from pc group by speed;     
                                      
-- Задание 15
select hd from pc group by hd having count(model) >=2;                                     

-- Задание 16
select distinct p1.model, p2.model, p1.speed, p1.ram                                                
from pc p1, pc p2
where p1.speed = p2.speed and p1.ram = p2.ram and p1.model > p2.model;

-- Задание 17
select distinct product.type, product.model, laptop.speed from laptop join product on laptop.model = product.model   
where laptop.speed < (select min(speed) from pc);

-- Задание 18
select distinct product.maker, printer.price from printer join product on printer.model = product.model              
where printer.color = 'y' and printer.price = (select min(printer.price) from printer where color = 'y');

-- Задание 19
select product.maker, AVG(laptop.screen) from laptop
join product on product.model = laptop.model group by product.maker;                                    

 -- Задание 20
select maker, count(*) from product where type = 'pc' group by maker                                   
having count(*) >= 3;

-- Задание 21
select product.maker, max(pc.price) as Max_Price from product join pc on product.model = pc.model       
group by product.maker;

-- Задание 22
select speed, AVG (pc.price) from pc where pc.speed > 600                                               
group by pc.speed;

 -- Задание 23
select distinct maker 
from product join pc on product.model=pc.model                                                          
where speed>=750 and maker in
( select maker
from product join laptop on product.model=laptop.model
where speed>=750 );

-- Задание 24
select model from( 
select distinct model, price from laptop where laptop.price = (select MAX(price) from laptop)             
union 
select distinct model, price from pc where pc.price = (select MAX(price) from pc)  
union 
select distinct model, price from printer where printer.price = (select MAX(price) from printer)  
) as t 
WHERE t.price=(select MAX(price) from ( 
select distinct price from laptop where laptop.price = (select MAX(price) from laptop)  
union 
select distinct price from pc where pc.price = (select MAX(price) from pc)  
union 
select distinct price FROM printer where printer.price = (select MAX(price) FROM printer)  
) as t1 );

-- Задание 25
SELECT DISTINCT maker                                                                                     
FROM product
WHERE model IN (
SELECT model
FROM pc
WHERE ram = (
  SELECT MIN(ram)
  FROM pc
  )
AND speed = (
  SELECT MAX(speed)
  FROM pc
  WHERE ram = (
   SELECT MIN(ram)
   FROM pc
   )
  )
)
AND
maker IN (
SELECT maker
FROM product
WHERE type='printer'
);

  







