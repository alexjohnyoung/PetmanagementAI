-- Create the household table
CREATE TABLE household (
    eircode VARCHAR(7) NOT NULL PRIMARY KEY,
    number_of_occupants INTEGER NOT NULL CHECK (number_of_occupants > 0),
    max_number_of_occupants INTEGER NOT NULL CHECK (max_number_of_occupants > 0),
    owner_occupied BIT NOT NULL DEFAULT FALSE,
    CONSTRAINT chk_occupants CHECK (number_of_occupants <= max_number_of_occupants)
);

-- Create the pet table
CREATE TABLE pet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    breed VARCHAR(20) NOT NULL,
    type VARCHAR(20) NOT NULL,
    age INTEGER NOT NULL,
    household_eircode VARCHAR(7),
    CONSTRAINT fk_household FOREIGN KEY (household_eircode)
     REFERENCES household (eircode)
     ON DELETE CASCADE
);
