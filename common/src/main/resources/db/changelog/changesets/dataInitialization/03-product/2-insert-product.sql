-- liquibase formatted sql
-- changeset rashmita.subedi:1

-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO product (version, name, details, species, status_product,code,image,recorded_date,is_present)
VALUES (0, 'fresh salmon',
        'Fresh salmon is a vibrant, pink-orange fish with a firm, moist texture and rich flavor. Fresh salmon is packed with omega-3 fatty acids, making it a popular choice for both flavor and health.',
        'Northwest King,Alaskan King,Alaskan Sockeye', 1, uuid(), 'image', '2024-04-14',true),
       (0, 'Smoked Salmon',
        'Smoked salmon is a delicacy made from fresh salmon that has been cured and then smoked, typically using cold or hot smoking methods.Smoked salmon is commonly enjoyed on bagels with cream cheese, in salads, or as a garnish for various dishes.',
        'Alderwood smoked salmon,Garlic-Pepper Smoked Salmon,Fresh Smoked Belly Strip Smoked Salmon Jerky ', '1',
        uuid(), 'image', '2024-04-14',true),
       (0, 'Wild Halibut',
        'Wild halibut is a large, flat fish found primarily in the cold waters of the North Pacific and North Atlantic oceans. It has a mild, slightly sweet flavor with firm, flaky white flesh, making it popular in a variety of dishes',
        'FRESH Alaskan Halibut Fillet!!,Whole Halibut,FRESH Halibut Steaks!', '1', uuid(), 'image', '2024-04-14',true),
       (0, 'Jumbo Crab',
        'The jumbo crab is a large species of crab known for its sizable, meaty claws and sweet, tender flesh. Their meat is used in various dishes, from crab cakes to stews, prized for its rich flavor and versatility.',
        'Jumbo! Whole Dungeness Crab,Fresh Dungeness Crab Meat,Jumbo Red King Crab Legs ONLY!', '1', uuid(), 'image', '2024-04-14',true),
       (0, 'Shellfish',
        'shellfish are commonly found in both saltwater and freshwater environments and are a popular food source worldwide due to their rich flavor and high nutritional value. Shellfish are often valued for their protein content, minerals, and omega-3 fatty acids.',
        'Jumbo Colossal Lobster Tails,Whole Maine Lobster,callops', '1', uuid(), 'image', '2024-04-14',true);