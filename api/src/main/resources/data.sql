INSERT INTO users (email, password, role)
VALUES ('alice', '$2a$10$rSeitEtQO0Towy/2NorpQuJW95YHKBrBi9B6CZT7/hheb19T/0lK2','ROLE_GUEST'),
       ('bob', '$2a$10$4Lwx5ZiovAo76ZHP23.WCOUMF39sqow66gYdvHBPpswotglyVdWci','ROLE_GUEST'),
       ('carol', '$2a$10$rGp/NGTGBfdl6GDNyIRe6uGcknI5KwDK28Kg/78mTJsM4Dwcq3AKS','ROLE_GUEST'),
       ('dave', '$2a$10$wINSs/KIR/eU8M4BNB3j7OnHjUQrt2oD8swiSFQVPiVo8yrqr3vRO','ROLE_GUEST'),
       ('eve', '$2a$10$vIDvrPzIqeaD49SZe6s.auLKvkGWU9l7eO9HW5HmlyBiKhGKPHHmK','ROLE_ADMIN'),
       ('root', '$2a$10$hzFWobG5aBIVuEuh.HitOe9AswoftjFbzNIUf7gLqlU3YiozGc5Ge','ROLE_ADMIN');

# "email" + @ = password
# root = root