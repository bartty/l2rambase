-- For further information on the usage of this table, please refer to the
-- documentation comments in the access_levels.sql file

CREATE TABLE IF NOT EXISTS `admin_command_access_rights` (
  `adminCommand` varchar(255) NOT NULL default 'admin_',
  `accessLevels` varchar(255) NOT NULL,
  PRIMARY KEY (`adminCommand`)
);

INSERT IGNORE INTO `admin_command_access_rights` VALUES

-- ADMIN
('admin_admin','7'),
('admin_admin1','7'),
('admin_admin2','7'),
('admin_admin3','7'),
('admin_admin4','7'),
('admin_admin5','7'),
('admin_admin6','7'),
('admin_admin7','7'),
('admin_admin8','7'),
('admin_gmliston','100'),
('admin_gmlistoff','100'),
('admin_silence','7'),
('admin_diet','7'),
('admin_tradeoff','100'),
('admin_reload','127'),
('admin_set','127'),
('admin_set_mod','127'),
('admin_saveolymp','127'),
('admin_manualhero','127'),
('admin_sethero','127'),
('admin_endolympiad','127'),
('admin_setconfig','127'),
('admin_config_server','127'),

-- ANNOUNCEMENTS
('admin_list_announcements','100'),
('admin_reload_announcements','100'),
('admin_announce_announcements','100'),
('admin_add_announcement','100'),
('admin_del_announcement','100'),
('admin_announce','7'),
('admin_announce_menu','7'),
('admin_list_autoann', '100'),
('admin_reload_autoann','100'),
('admin_add_autoann', '100'),
('admin_del_autoann', '100'),

-- BAN
('admin_ban','7'),
('admin_ban_acc','7'),
('admin_ban_char','7'),
('admin_ban_chat','7'),
('admin_unban','127'),
('admin_unban_acc','127'),
('admin_unban_char','127'),
('admin_unban_chat','7'),
('admin_jail','7'),
('admin_unjail','7'),
('admin_penalty','7'),

-- BBS
('admin_bbs','7'),

-- BUFF
('admin_getbuffs','7'),
('admin_stopbuff','7'),
('admin_stopallbuffs','7'),
('admin_areacancel','100'),

-- CACHE
('admin_cache_htm_rebuild','127'),
('admin_cache_htm_reload','127'),
('admin_cache_reload_path','127'),
('admin_cache_reload_file','127'),
('admin_cache_crest_rebuild','127'),
('admin_cache_crest_reload','127'),
('admin_cache_crest_fix','127'),
 
-- CAMERA 
('admin_camera','127'), 

-- CHANGE ACCESS LEVEL
('admin_changelvl','127'),

-- CREATE ITEM
('admin_itemcreate','127'),
('admin_create_item','100'),
('admin_create_coin','127'), 
('admin_give_item_target','127'),
('admin_give_item_to_all','127'),
('admin_clear_inventory','100'),

-- CURSED WEPONS
('admin_cw_info','7'),
('admin_cw_remove','100'),
('admin_cw_goto','7'),
('admin_cw_reload','127'),
('admin_cw_add','127'),
('admin_cw_info_menu','7'),

-- DELETE
('admin_delete','127'),

-- DISCONNECT
('admin_character_disconnect','127'),

-- DOOR CONTROL
('admin_open','7'),
('admin_close','7'),
('admin_openall','127'),
('admin_closeall','127'),

-- EDIT CHAR
('admin_edit_character','127'),
('admin_setkarma','100'),
('admin_setfame','100'),
('admin_charquestmenu','100'),
('admin_setcharquest','100'),
('admin_character_list','7'),
('admin_character_info','7'),
('admin_debug','127'),
('admin_show_characters','7'),
('admin_find_character','7'),
('admin_current_player','7'),
('admin_find_ip','7'),
('admin_find_account','7'),
('admin_find_dualbox','7'),
('admin_save_modifications','127'),
('admin_rec','7'),
('admin_settitle','7'),
('admin_changename','127'),
('admin_setsex','7'),
('admin_setcolor','7'),
('admin_settcolor','7'), 
('admin_setclass','127'),
('admin_setpvp','127'), 
('admin_setpk','127'),
('admin_fullfood','100'),
('admin_remove_clan_penalty','127'),
('admin_nokarma','100'),
('admin_summon_info','7'),
('admin_summon_setlvl','127'),
('admin_unsummon','7'),
('admin_show_pet_inv','7'),
('admin_partyinfo','100'),

-- EDIT NPC
('admin_edit_npc','127'),
('admin_save_npc','127'),
('admin_show_droplist','7'),
('admin_edit_drop','127'),
('admin_add_drop','127'),
('admin_del_drop','127'),
('admin_showShop','100'),
('admin_showShopList','100'),
('admin_addShopItem','127'),
('admin_delShopItem','127'),
('admin_editShopItem','127'),
('admin_close_window','100'),
('admin_show_skilllist_npc','100'), 
('admin_add_skill_npc','127'), 
('admin_edit_skill_npc','127'), 
('admin_del_skill_npc','127'), 

-- EFFECTS
('admin_invis','7'),
('admin_invisible','7'),
('admin_vis','7'),
('admin_visible','7'),
('admin_invis_menu','7'),
('admin_earthquake','7'),
('admin_earthquake_menu','7'),
('admin_bighead','7'),
('admin_shrinkhead','7'),
('admin_gmspeed','7'),
('admin_gmspeed_menu','7'),
('admin_unpara_all','7'),
('admin_para_all','7'),
('admin_unpara','7'),
('admin_para','7'),
('admin_unpara_all_menu','7'),
('admin_para_all_menu','7'),
('admin_unpara_menu','7'),
('admin_para_menu','7'),
('admin_polyself','7'),
('admin_unpolyself','7'),
('admin_polyself_menu','7'),
('admin_unpolyself_menu','7'),
('admin_clearteams','7'),
('admin_setteam_close','7'),
('admin_setteam','7'),
('admin_social','100'),
('admin_effect','100'),
('admin_social_menu','100'),
('admin_special','100'),
('admin_special_menu','100'),
('admin_effect_menu','100'),
('admin_abnormal','100'),
('admin_abnormal_menu','100'),
('admin_play_sounds','100'),
('admin_play_sound','100'),
('admin_atmosphere','127'),
('admin_atmosphere_menu','127'),

-- ELEMENT
('admin_setlh','127'),
('admin_setlc','127'),
('admin_setll','127'),
('admin_setlg','127'),
('admin_setlb','127'),
('admin_setlw','127'),
('admin_setls','127'),

-- ENCHANT
('admin_seteh','127'),
('admin_setec','127'),
('admin_seteg','127'),
('admin_setel','127'),
('admin_seteb','127'),
('admin_setew','127'),
('admin_setes','127'),
('admin_setle','127'),
('admin_setre','127'),
('admin_setlf','127'),
('admin_setrf','127'),
('admin_seten','127'),
('admin_setun','127'),
('admin_setba','127'),
('admin_setbe','127'),
('admin_enchant','127'),

-- EVENT ENGINE
('admin_event','100'),
('admin_event_new','100'),
('admin_event_choose','100'),
('admin_event_store','100'),
('admin_event_set','100'),
('admin_event_change_teams_number','100'),
('admin_event_announce','100'),
('admin_event_panel','100'),
('admin_event_control_begin','100'),
('admin_event_control_teleport','100'),
('admin_add','100'),
('admin_event_see','100'),
('admin_event_del','100'),
('admin_delete_buffer','100'),
('admin_event_control_sit','100'),
('admin_event_name','100'),
('admin_event_control_kill','100'),
('admin_event_control_res','100'),
('admin_event_control_poly','100'),
('admin_event_control_unpoly','100'),
('admin_event_control_prize','100'),
('admin_event_control_chatban','100'),
('admin_event_control_finish','100'),
('admin_pvp_start','100'),
('admin_tvt_start','100'),

-- EX & SP
('admin_add_exp_sp_to_character','127'),
('admin_add_exp_sp','127'),
('admin_remove_exp_sp','127'),

-- FIGHT CALCULATOR
('admin_fight_calculator','100'),
('admin_fight_calculator_show','100'),
('admin_fcs','100'),

-- FORT SIEGE
('admin_fortsiege','127'),
('admin_add_fortattacker','127'),
('admin_list_fortsiege_clans','100'),
('admin_clear_fortsiege_list','127'),
('admin_spawn_fortdoors','127'),
('admin_endfortsiege','127'),
('admin_startfortsiege','127'),
('admin_setfort','127'),
('admin_removefort','127'),

-- GEODATA
('admin_geo_z','100'),
('admin_geo_type','100'),
('admin_geo_nswe','100'),
('admin_geo_los','100'),
('admin_geo_position','100'),
('admin_geo_bug','100'),
('admin_geo_load','100'),
('admin_geo_unload','100'),

-- GEO EDITOR
('admin_ge_status','100'),
('admin_ge_mode','100'),
('admin_ge_join','100'),
('admin_ge_leave','100'),

-- GM
('admin_gm','127'),

-- GM CHAT
('admin_gmchat','100'),
('admin_snoop','100'),
('admin_gmchat_menu','100'),

-- HEAL
('admin_heal','100'),

-- HELP PAGE
('admin_help','100'),

-- INSTANCE
('admin_setinstance','127'),
('admin_ghoston','127'),
('admin_ghostoff','127'),
('admin_createinstance','127'),
('admin_destroyinstance','127'),
('admin_listinstances','127'),

-- INSTANCE ZONE
('admin_instancezone','127'),
('admin_instancezone_clear','127'),

-- INVUL
('admin_invul','7'),
('admin_setinvul','7'),

-- KICK
('admin_kick','7'),
('admin_kick_non_gm','127'),

-- KILL
('admin_kill','100'),
('admin_kill_monster','100'),

-- LEVEL
('admin_add_level','127'),
('admin_set_level','127'),

-- LOGIN
('admin_server_gm_only','127'),
('admin_server_all','127'),
('admin_server_max_player','127'),
('admin_server_list_clock','127'),
('admin_server_login','127'),

-- MAMMON
('admin_mammon_find','7'),
('admin_mammon_respawn','127'),
('admin_list_spawns','100'),
('admin_msg','100'),

-- MANOR
('admin_manor','100'),
('admin_manor_approve','127'),
('admin_manor_setnext','127'),
('admin_manor_reset','127'),
('admin_manor_setmaintenance','127'),
('admin_manor_save','127'),
('admin_manor_disable','127'),

-- MENU
('admin_char_manage','7'),
('admin_teleport_character_to_menu','7'),
('admin_recall_char_menu','7'),
('admin_recall_party_menu','7'),
('admin_recall_clan_menu','100'),
('admin_goto_char_menu','100'),
('admin_kick_menu','7'),
('admin_kill_menu','7'),
('admin_ban_menu','7'),
('admin_unban_menu','127'),

-- MOB GROUP
('admin_mobmenu','100'),
('admin_mobgroup_list','100'),
('admin_mobgroup_create','100'),
('admin_mobgroup_remove','100'),
('admin_mobgroup_delete','100'),
('admin_mobgroup_spawn','100'),
('admin_mobgroup_unspawn','100'),
('admin_mobgroup_kill','100'),
('admin_mobgroup_idle','100'),
('admin_mobgroup_attack','100'),
('admin_mobgroup_rnd','100'),
('admin_mobgroup_return','100'),
('admin_mobgroup_follow','100'),
('admin_mobgroup_casting','100'),
('admin_mobgroup_nomove','100'),
('admin_mobgroup_attackgrp','100'),
('admin_mobgroup_invul','100'),

-- MONSTER RACE
('admin_mons','127'),

-- PATHNODE
('admin_pn_info','100'),
('admin_show_path','100'),
('admin_path_debug','100'),
('admin_show_pn','100'),
('admin_find_path','100'),

-- PETITION
('admin_view_petitions','7'),
('admin_view_petition','7'),
('admin_accept_petition','7'),
('admin_reject_petition','7'),
('admin_reset_petitions','7'),
('admin_force_peti','7'),

-- PFORGE
('admin_forge','127'),
('admin_forge2','127'),
('admin_forge3','127'),

-- PLEDGE
('admin_pledge','127'),

-- POLYMORPH
('admin_polymorph','100'),
('admin_unpolymorph','100'),
('admin_polymorph_menu','100'),
('admin_unpolymorph_menu','100'),
('admin_transform','7'),
('admin_untransform','7'),
('admin_transform_menu','7'),
('admin_untransform_menu','7'),

-- QUEST
('admin_quest_reload','127'),
('admin_script_load','127'),

-- REPAIR CHAR
('admin_restore','127'),
('admin_repair','127'),

-- RES
('admin_res','7'),
('admin_res_monster','127'),

-- RIDE
('admin_ride_horse','7'),
('admin_ride_bike','7'), 
('admin_ride_wyvern','100'),
('admin_ride_strider','7'),
('admin_unride_wyvern','100'),
('admin_unride_strider','7'),
('admin_unride','7'),
('admin_ride_wolf','7'),
('admin_unride_wolf','7'),

-- SHOP
('admin_buy','100'),
('admin_gmshop','100'),

-- SHUTDOWN
('admin_server_shutdown','127'),
('admin_server_restart','7'),
('admin_server_abort','7'),

-- SIEGE
('admin_siege','127'),
('admin_add_attacker','127'),
('admin_add_defender','127'),
('admin_add_guard','127'),
('admin_list_siege_clans','100'),
('admin_clear_siege_list','127'),
('admin_move_defenders','127'),
('admin_spawn_doors','127'),
('admin_endsiege','127'),
('admin_startsiege','127'),
('admin_setsiegetime','127'),
('admin_setcastle','127'),
('admin_removecastle','127'),
('admin_clanhall','100'),
('admin_clanhallset','127'),
('admin_clanhalldel','127'),
('admin_clanhallopendoors','127'),
('admin_clanhallclosedoors','127'),
('admin_clanhallteleportself','7'),

-- SKILL
('admin_show_skills','100'),
('admin_remove_skills','127'),
('admin_skill_list','100'),
('admin_skill_index','100'),
('admin_add_skill','127'),
('admin_remove_skill','127'),
('admin_get_skills','127'),
('admin_reset_skills','127'),
('admin_give_all_skills','127'),
('admin_remove_all_skills','127'),
('admin_add_clan_skill','127'),

-- SPAWN
('admin_show_spawns','100'),
('admin_spawn','127'),
('admin_spawn_monster','127'),
('admin_spawn_index','127'),
('admin_unspawnall','127'),
('admin_respawnall','127'),
('admin_spawn_reload','127'),
('admin_npc_index','127'),
('admin_spawn_once','127'),
('admin_show_npcs','127'),
('admin_teleport_reload','127'),
('admin_spawnnight','127'),
('admin_spawnday','127'),

-- SUMMON
('admin_summon','127'),

-- TARGET
('admin_target','127'),

-- TELEPORT
('admin_show_moves','127'),
('admin_show_moves_other','127'),
('admin_show_teleport','127'),
('admin_teleport_to_character','7'),
('admin_teleportto','7'),
('admin_move_to','7'),
('admin_teleport_character','100'),
('admin_recall','7'),
('admin_walk','127'),
('admin_explore','127'),
('teleportto','7'),
('recall','7'),
('admin_recall_npc','127'),
('admin_gonorth','100'),
('admin_gosouth','100'),
('admin_goeast','100'),
('admin_gowest','100'),
('admin_goup','100'),
('admin_godown','100'),
('admin_tele','100'),
('admin_teleto','100'),
('admin_instant_move','100'),

-- TEST
('admin_test','100'),
('admin_stats','100'),
('admin_skill_test','100'),
('admin_st','100'),
('admin_mp','100'),
('admin_known','100'),

-- UNBLOCK IP
('admin_unblockip','127'),

-- VITALITY
('admin_set_vitality','100'),
('admin_set_vitality_level','100'),
('admin_full_vitality','100'),
('admin_empty_vitality','100'),
('admin_get_vitality','100'),

-- ZONE
('admin_zone_check','127'),
('admin_zone_reload','127'),


-- TERITORY WAR
('admin_territory_war','127'),
('admin_territory_war_end','127'),
('admin_territory_war_start','127'),
('admin_territory_war_time','127');