CREATE DATABASE IF NOT EXISTS talkative;

use talkative;

-- Inserting six users
INSERT INTO users (first_name, last_name, email, password, is_verified, created_at)
VALUES
    ('Rachel', 'Green', 'rachel.green@friends.com', '$2a$12$xqpn0xAce6dMcYM3vrh.QODeKzC5t4P5Z09hE1Nz2nerkIl00quka', true, NOW()),
    ('Monica', 'Geller', 'monica.geller@friends.com', '$2a$12$xqpn0xAce6dMcYM3vrh.QODeKzC5t4P5Z09hE1Nz2nerkIl00quka', true, NOW()),
    ('Phoebe', 'Buffay', 'phoebe.buffay@friends.com', '$2a$12$xqpn0xAce6dMcYM3vrh.QODeKzC5t4P5Z09hE1Nz2nerkIl00quka', true, NOW()),
    ('Chandler', 'Bing', 'chandler.bing@friends.com', '$2a$12$xqpn0xAce6dMcYM3vrh.QODeKzC5t4P5Z09hE1Nz2nerkIl00quka', true, NOW()),
    ('Joey', 'Tribbiani', 'joey.tribbiani@friends.com', '$2a$12$xqpn0xAce6dMcYM3vrh.QODeKzC5t4P5Z09hE1Nz2nerkIl00quka', true, NOW()),
    ('Ross', 'Geller', 'ross.geller@friends.com', '$2a$12$xqpn0xAce6dMcYM3vrh.QODeKzC5t4P5Z09hE1Nz2nerkIl00quka', true, NOW());
