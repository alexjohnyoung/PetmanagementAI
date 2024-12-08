INSERT INTO household (eircode, number_of_occupants, max_number_of_occupants, owner_occupied)
VALUES ('D01AB12', 3, 5, FALSE);

INSERT INTO pet (name, breed, type, age, household_eircode)
VALUES
    ('Buddy', 'Golden Retriever', 'Dog', 3, 'D01AB12'),
    ('Kitty', 'Siamese', 'Cat', 2, 'D01AB12');
