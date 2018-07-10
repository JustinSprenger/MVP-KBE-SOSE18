CREATE TABLE IF NOT EXISTS `Address` ( 
    `id` INT NOT NULL AUTO_INCREMENT,
    `street` VARCHAR(100) NOT NULL , 
    `city` VARCHAR(100) NOT NULL ,
    `zip` VARCHAR(100) NOT NULL ,
    `country` VARCHAR(100) NOT NULL , 
    `contact_id` INT NOT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY (contact_id) REFERENCES Contact(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `Contact` ( 
	`id` INT NOT NULL AUTO_INCREMENT,
	`firstName` VARCHAR(100) NOT NULL , 
	`lastName` VARCHAR(100) ,
	`mobile` VARCHAR(100) ,
	`emailAddress` VARCHAR(100) , 
	 PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
