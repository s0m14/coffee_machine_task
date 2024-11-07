insert into ingredients(id,coffee_beans,water,milk) values (1000,500,1000,500)

insert into coffee(coffee_id,name,coffee_beans,milk,water,sugar,sold_quantity) values(1,'Espresso',18,0,30,false,0)
insert into coffee(coffee_id,name,coffee_beans,milk,water,sugar,sold_quantity) values(2,'Americano',18,0,120,false,0)
insert into coffee(coffee_id,name,coffee_beans,milk,water,sugar,sold_quantity) values(3,'Cappucino',18,120,30,false,0)

insert into steps(coffee_id,instruction) values(1,'1.Grind the coffee beans to a fine consistency.')
insert into steps(coffee_id,instruction) values(1,'2.Add 30 ml of water to the machine and set it to high pressure.')
insert into steps(coffee_id,instruction) values(1,'3.Brew the espresso shot, which should take around 25–30 seconds.')
insert into steps(coffee_id,instruction) values(1,'4.Add sugar after brewing, if desired.')

insert into steps(coffee_id,instruction) values(2,'1.Grind the coffee beans to a fine consistency.')
insert into steps(coffee_id,instruction) values(2,'2.Add 30 ml of water to the machine and brew an espresso shot.')
insert into steps(coffee_id,instruction) values(2,'3.Add an additional 90 ml of hot water to the brewed espresso.')
insert into steps(coffee_id,instruction) values(2,'4.Add sugar to taste, if desired.')

insert into steps(coffee_id,instruction) values(3,'1.Grind the coffee beans to a fine consistency.')
insert into steps(coffee_id,instruction) values(3,'2.Add 30 ml of water to the machine and brew an espresso shot.')
insert into steps(coffee_id,instruction) values(3,'3.Heat 120 ml of milk until it’s hot, then froth it to create foam.')
insert into steps(coffee_id,instruction) values(3,'4.Pour the steamed milk over the espresso and top with milk foam.')
insert into steps(coffee_id,instruction) values(3,'5.Add sugar to taste, if desired.')

