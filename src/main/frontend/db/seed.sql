INSERT INTO pet_types
    (type, description, img_url_random_animal)
VALUES
    ('Two-legged', 'animals who stand with two legs', 'https://images.newscientist.com/wp-content/uploads/2014/07/dn25829-2_800.jpg');

INSERT INTO pet_types
    (type, description, img_url_random_animal)
VALUES
    ('Four-legged', 'animals who stand on four legs', 'https://www.ucdavis.edu/sites/default/files/home-site/blogs/one-health/blog-posts/2018/cow-field-one-health-uc-davis.jpg');

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Clifford', 'https://images2.minutemediacdn.com/image/upload/c_crop,h_1181,w_2100,x_0,y_328/f_auto,q_auto,w_1100/v1554923476/shape/mentalfloss/24523-pbs_scholastic.jpg', 5, true, 'The big red dog, he loves dinosaur bones', 'null', 2);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Scooby Doo', 'https://i.ebayimg.com/images/i/131484627379-0-1/s-l1000.jpg', 4, true, 'Retired Crime Solving pooch, loves scooby snacks', 'null', 2);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Courage', 'https://1.bp.blogspot.com/-fSbsOaTu73Y/T3wz22zl-xI/AAAAAAAAEyM/9TJXzGPT_iE/s1600/coragem+1.jpg', 4, true, 'Courage the cowardly dog, is pink and has a hole in his teeth,', 'null', 2);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Snoopy', 'https://4.bp.blogspot.com/-5I3DW0YllQ0/WiDS_A8GcHI/AAAAAAAAJJo/ZlUjubpGPiotafVMg2VUPcvGRt2BI-LoACLcBGAs/s1600/snoopy.png', 6, true, 'He loves to dance and his best friend is a yellow bird', 'null', 2);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Odie Garfield', 'https://4.bp.blogspot.com/-qPRrHfld2vA/Us4j2ZCsdqI/AAAAAAAADYg/SlvDwfnOSZ4/s1600/odie_bd.jpg', 2, true, 'loving goofy doggo who licks everything!', 'null', 2);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Pluto', 'http://images6.fanpop.com/image/photos/38600000/Pluto-classic-disney-38684898-1600-900.jpg', 4, true, 'Ran away from Mickey', 'null', 2);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Spike', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fhobbydb-production.s3.amazonaws.com%2Fprocessed_uploads%2Fsubject_photo%2Fsubject_photo%2Fimage%2F34894%2F1513307715-29010-4102%2FSpike_20Bulldog.jpg&f=1&nofb=1', 4, true, 'He loves a juicy steak', 'null', 2);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Tweety', 'https://i.pinimg.com/736x/84/25/eb/8425eb206150472144136b4132e6f719.jpg', 4, true, 'Aka Tweety Bird or Tweety Pie, is a male yellow canary. He likes to foil cats attempts to catch him, sometimes quite aggressively. Tweety Pie is perhaps the cutest cartoon animal of all time.', 'null', 1);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Donald Duck', 'https://www.wikihow.com/images/f/fe/Final-Colored-Intro-2.jpg', 4, true, 'The one and only Donald, his face is on orange juice containers', 'null', 1);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Bugs Bunny', 'https://1.bp.blogspot.com/-0c1rJ3UPQUU/UI65xo49QHI/AAAAAAAAIdc/BKLbVcSm2DE/s1600/Bugs+Bunny+Gets+the+Boid+%289%29.jpg', 7, true, 'His favorite activity is digging and munching on a carrot', 'null', 1);

INSERT INTO adoptable_pets
    (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id)
VALUES
    ('Po the Giant Panda.', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fae01.alicdn.com%2Fkf%2FHTB15x5aRVXXXXbkXVXXq6xXFXXXj%2F2017-New-Hot-Kung-fu-Panda-Po-3-Sitting-Plush-Toys-Large-Dolls-Furnishing-Articles-Figures.jpg_640x640.jpg&f=1&nofb=1', 12, true, 'Po is famously known for being a kung fu master', 'null', 1);

INSERT INTO adoption_applications
    (name, phone_number, email, home_status, application_status, pet_id)
VALUES
    ('grandma', '5551234567', 'grandma@looneytunes.com', 'own', 'pending', '8');