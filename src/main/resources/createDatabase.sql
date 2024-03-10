CREATE TABLE IF NOT EXISTS public.bonuses
(
    id serial,
    athletics smallint NOT NULL,
    acrobatics smallint NOT NULL,
    sleight_of_hand smallint NOT NULL,
    stealth smallint NOT NULL,
    perception smallint NOT NULL,
    survival smallint NOT NULL,
    medicine smallint NOT NULL,
    insight smallint NOT NULL,
    animal_care smallint NOT NULL,
    analysis smallint NOT NULL,
    history smallint NOT NULL,
    magic smallint NOT NULL,
    nature smallint NOT NULL,
    religion smallint NOT NULL,
    performance smallint NOT NULL,
    intimidation smallint NOT NULL,
    fraud smallint NOT NULL,
    conviction smallint NOT NULL,
    CONSTRAINT bonuses_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.characters
(
    id serial,
    user_id integer,
    name text COLLATE pg_catalog."default" NOT NULL,
    class text COLLATE pg_catalog."default" NOT NULL,
    race text COLLATE pg_catalog."default" NOT NULL,
    image_link text COLLATE pg_catalog."default",
    lvl smallint NOT NULL,
    experience integer NOT NULL,
    health smallint NOT NULL,
    strength smallint NOT NULL,
    physique smallint NOT NULL,
    dexterity smallint NOT NULL,
    wisdom smallint NOT NULL,
    intelligence smallint NOT NULL,
    charisma smallint NOT NULL,
    notes text COLLATE pg_catalog."default",
    CONSTRAINT characters_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.modifiers
(
    id serial,
    athletics smallint NOT NULL,
    acrobatics smallint NOT NULL,
    sleight_of_hand smallint NOT NULL,
    stealth smallint NOT NULL,
    perception smallint NOT NULL,
    survival smallint NOT NULL,
    medicine smallint NOT NULL,
    insight smallint NOT NULL,
    animal_care smallint NOT NULL,
    analysis smallint NOT NULL,
    history smallint NOT NULL,
    magic smallint NOT NULL,
    nature smallint NOT NULL,
    religion smallint NOT NULL,
    performance smallint NOT NULL,
    intimidation smallint NOT NULL,
    fraud smallint NOT NULL,
    conviction smallint NOT NULL,
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


ALTER TABLE IF EXISTS public.modifiers
    ADD CONSTRAINT bonuses_id FOREIGN KEY (id)
        REFERENCES public.bonuses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
CREATE INDEX IF NOT EXISTS modifiers_pkey
    ON public.modifiers(id);

END;