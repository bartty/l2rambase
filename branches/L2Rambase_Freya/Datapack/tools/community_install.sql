-- These queries are meant to delete any communityserver table you may have from
-- previous L2m installations.
--
-- Queries that create these tables and/or populate them apropiately are
-- available in the ../cb_sql/ folder. 

DROP TABLE IF EXISTS clan_introductions;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS forums;
DROP TABLE IF EXISTS registered_gameservers;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS topics;