CREATE TABLE IF NOT EXISTS public.characters
(
    id serial,
    user_id integer,
    name text COLLATE pg_catalog."default" NOT NULL,
    class text COLLATE pg_catalog."default" NOT NULL,
    race text COLLATE pg_catalog."default" NOT NULL,
    image_link text COLLATE pg_catalog."default",
    lvl integer NOT NULL,
    experience integer NOT NULL,
    health integer NOT NULL,
    strength integer NOT NULL,
    physique integer NOT NULL,
    dexterity integer NOT NULL,
    wisdom integer NOT NULL,
    intelligence integer NOT NULL,
    charisma integer NOT NULL,
    notes text COLLATE pg_catalog."default",
    CONSTRAINT characters_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.modifiers
(
    id serial,
    athletics integer NOT NULL,
    acrobatics integer NOT NULL,
    sleight_of_hand integer NOT NULL,
    stealth integer NOT NULL,
    perception integer NOT NULL,
    survival integer NOT NULL,
    medicine integer NOT NULL,
    insight integer NOT NULL,
    animal_care integer NOT NULL,
    analysis integer NOT NULL,
    history integer NOT NULL,
    magic integer NOT NULL,
    nature integer NOT NULL,
    religion integer NOT NULL,
    performance integer NOT NULL,
    intimidation integer NOT NULL,
    fraud integer NOT NULL,
    conviction integer NOT NULL,
    CONSTRAINT modifiers_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id serial,
    username text COLLATE pg_catalog."default",
    password text COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.characters
    ADD CONSTRAINT modifiers_id FOREIGN KEY (id)
    REFERENCES public.modifiers (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
              NOT VALID;
CREATE INDEX IF NOT EXISTS characters_pkey
    ON public.characters(id);


ALTER TABLE IF EXISTS public.characters
    ADD CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
              NOT VALID;