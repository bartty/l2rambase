package lineage2.gameserver.network.serverpackets;

import org.apache.commons.lang3.StringUtils;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.instances.DoorInstance;
import lineage2.gameserver.model.items.ItemInstance;
import lineage2.gameserver.network.serverpackets.components.SystemMsg;
import lineage2.gameserver.utils.Location;

import java.util.ArrayList;
import java.util.List;

public class SystemMessage extends L2GameServerPacket {
    // d d (d S/d d/d dd)
    // |--------------> 0 - String 1-number 2-textref npcname (1000000-1002655)
    // 3-textref itemname 4-textref skills 5-??
    private static final int TYPE_DAMAGE = 16;
    private static final int TYPE_CLASS_NAME = 15;
    private static final int TYPE_SYSTEM_STRING = 13;
    private static final int TYPE_PLAYER_NAME = 12;
    private static final int TYPE_DOOR_NAME = 11;
    private static final int TYPE_INSTANCE_NAME = 10;
    private static final int TYPE_ELEMENT_NAME = 9;
    private static final int TYPE_UNKNOWN_8 = 8;
    private static final int TYPE_ZONE_NAME = 7;
    private static final int TYPE_LONG = 6;
    private static final int TYPE_CASTLE_NAME = 5;
    private static final int TYPE_SKILL_NAME = 4;
    private static final int TYPE_ITEM_NAME = 3;
    private static final int TYPE_NPC_NAME = 2;
    private static final int TYPE_NUMBER = 1;
    private static final int TYPE_TEXT = 0;
    private int _messageId;
    private List<Arg> args = new ArrayList<Arg>();
    public static final int YOU_HAVE_BEEN_DISCONNECTED_FROM_THE_SERVER = 0;
    public static final int THE_SERVER_WILL_BE_COMING_DOWN_IN_S1_SECONDS__PLEASE_FIND_A_SAFE_PLACE_TO_LOG_OUT = 1;
    public static final int S1_DOES_NOT_EXIST = 2;
    public static final int S1_IS_NOT_CURRENTLY_LOGGED_IN = 3;
    public static final int YOU_CANNOT_ASK_YOURSELF_TO_APPLY_TO_A_CLAN = 4;
    public static final int S1_ALREADY_EXISTS = 5;
    public static final int S1_DOES_NOT_EXIST_2 = 6;
    public static final int YOU_ARE_ALREADY_A_MEMBER_OF_S1 = 7;
    public static final int YOU_ARE_WORKING_WITH_ANOTHER_CLAN = 8;
    public static final int S1_IS_NOT_A_CLAN_LEADER = 9;
    public static final int S1_IS_WORKING_WITH_ANOTHER_CLAN = 10;
    public static final int THERE_ARE_NO_APPLICANTS_FOR_THIS_CLAN = 11;
    public static final int APPLICANT_INFORMATION_IS_INCORRECT = 12;
    public static final int UNABLE_TO_DISPERSE_YOUR_CLAN_HAS_REQUESTED_TO_PARTICIPATE_IN_A_CASTLE_SIEGE = 13;
    public static final int UNABLE_TO_DISPERSE_YOUR_CLAN_OWNS_ONE_OR_MORE_CASTLES_OR_HIDEOUTS = 14;
    public static final int YOU_ARE_IN_SIEGE = 15;
    public static final int YOU_ARE_NOT_IN_SIEGE = 16;
    public static final int CASTLE_SIEGE_HAS_BEGUN = 17;
    public static final int THE_CASTLE_SIEGE_HAS_ENDED = 18;
    public static final int THERE_IS_A_NEW_LORD_OF_THE_CASTLE = 19;
    public static final int THE_GATE_IS_BEING_OPENED = 20;
    public static final int THE_GATE_IS_BEING_DESTROYED = 21;
    public static final int YOUR_TARGET_IS_OUT_OF_RANGE = 22;
    public static final int NOT_ENOUGH_HP = 23;
    public static final int NOT_ENOUGH_MP = 24;
    public static final int REJUVENATING_HP = 25;
    public static final int REJUVENATING_MP = 26;
    public static final int YOUR_CASTING_HAS_BEEN_INTERRUPTED = 27;
    public static final int YOU_HAVE_OBTAINED_S1_ADENA = 28;
    public static final int YOU_HAVE_OBTAINED_S2_S1 = 29;
    public static final int YOU_HAVE_OBTAINED_S1 = 30;
    public static final int YOU_CANNOT_MOVE_WHILE_SITTING = 31;
    public static final int YOU_ARE_NOT_CAPABLE_OF_COMBAT_MOVE_TO_THE_NEAREST_RESTART_POINT = 32;
    public static final int YOU_CANNOT_MOVE_WHILE_CASTING = 33; 
    public static final int WELCOME_TO_THE_WORLD_OF_LINEAGE_II = 34; 
    @Deprecated
    public static final int YOU_HIT_FOR_S1_DAMAGE = 35; 
    @Deprecated
    public static final int C1_HIT_YOU_FOR_S2_DAMAGE = 36;
    public static final int C1_HIT_YOU_FOR_S2_DAMAGE_2 = 37;
    public static final int THE_TGS2002_EVENT_BEGINS = 38;
    public static final int THE_TGS2002_EVENT_IS_OVER_THANK_YOU_VERY_MUCH = 39;
    public static final int THIS_IS_THE_TGS_DEMO_THE_CHARACTER_WILL_IMMEDIATELY_BE_RESTORED = 40;
    public static final int YOU_CAREFULLY_NOCK_AN_ARROW = 41;
    @Deprecated
    public static final int YOU_HAVE_AVOIDED_C1S_ATTACK = 42;
    @Deprecated
    public static final int YOU_HAVE_MISSED = 43;
    @Deprecated
    public static final int CRITICAL_HIT = 44;
    public static final int YOU_HAVE_EARNED_S1_EXPERIENCE = 45;
    public static final int YOU_USE_S1 = 46;
    public static final int YOU_BEGIN_TO_USE_AN_S1 = 47;
    @Deprecated
    public static final int S1_IS_NOT_AVAILABLE_AT_THIS_TIME_BEING_PREPARED_FOR_REUSE = 48; 
    public static final int YOU_HAVE_EQUIPPED_YOUR_S1 = 49;
    public static final int YOUR_TARGET_CANNOT_BE_FOUND = 50;
    public static final int YOU_CANNOT_USE_THIS_ON_YOURSELF = 51;
    public static final int YOU_HAVE_EARNED_S1_ADENA = 52;
    public static final int YOU_HAVE_EARNED_S2_S1S = 53;
    public static final int YOU_HAVE_EARNED_S1 = 54;
    public static final int YOU_HAVE_FAILED_TO_PICK_UP_S1_ADENA = 55;
    public static final int YOU_HAVE_FAILED_TO_PICK_UP_S1 = 56;
    public static final int YOU_HAVE_FAILED_TO_PICK_UP_S2_S1S = 57;
    public static final int YOU_HAVE_FAILED_TO_EARN_S1_ADENA = 58;
    public static final int YOU_HAVE_FAILED_TO_EARN_S1 = 59;
    public static final int YOU_HAVE_FAILED_TO_EARN_S2_S1S = 60;
    public static final int NOTHING_HAPPENED = 61;
    public static final int S1_HAS_BEEN_SUCCESSFULLY_ENCHANTED = 62;
    public static final int _S1_S2_HAS_BEEN_SUCCESSFULLY_ENCHANTED = 63;
    public static final int THE_ENCHANTMENT_HAS_FAILED_YOUR_S1_HAS_BEEN_CRYSTALLIZED = 64;
    public static final int THE_ENCHANTMENT_HAS_FAILED_YOUR__S1_S2_HAS_BEEN_CRYSTALLIZED = 65;
    public static final int C1_IS_INVITING_YOU_TO_JOIN_A_PARTY_DO_YOU_ACCEPT = 66;
    public static final int S1_HAS_INVITED_YOU_TO_THE_JOIN_THE_CLAN_S2_DO_YOU_WISH_TO_JOIN = 67;
    public static final int WOULD_YOU_LIKE_TO_WITHDRAW_FROM_THE_S1_CLAN_IF_YOU_LEAVE_YOU_WILL_HAVE_TO_WAIT_AT_LEAST_A_DAY_BEFORE_JOINING_ANOTHER_CLAN = 68;
    public static final int WOULD_YOU_LIKE_TO_DISMISS_S1_FROM_THE_CLAN_IF_YOU_DO_SO_YOU_WILL_HAVE_TO_WAIT_AT_LEAST_A_DAY_BEFORE_ACCEPTING_A_NEW_MEMBER = 69;
    public static final int DO_YOU_WISH_TO_DISPERSE_THE_CLAN_S1 = 70;
    public static final int HOW_MANY_S1_S_DO_YOU_WANT_TO_DISCARD = 71;
    public static final int HOW_MANY_S1_S_DO_YOU_WANT_TO_MOVE = 72;
    public static final int HOW_MANY_S1_S_DO_YOU_WANT_TO_DESTROY = 73;
    public static final int DO_YOU_WISH_TO_DESTROY_YOUR_S1 = 74;
    public static final int ID_DOES_NOT_EXIST = 75;
    public static final int INCORRECT_PASSWORD = 76;
    public static final int YOU_CANNOT_CREATE_ANOTHER_CHARACTER_PLEASE_DELETE_THE_EXISTING_CHARACTER_AND_TRY_AGAIN = 77;
    public static final int DO_YOU_WISH_TO_DELETE_S1 = 78;
    public static final int THIS_NAME_ALREADY_EXISTS = 79;
    public static final int YOUR_TITLE_CANNOT_EXCEED_16_CHARACTERS_IN_LENGTH_PLEASE_TRY_AGAIN = 80;
    public static final int PLEASE_SELECT_YOUR_RACE = 81;
    public static final int PLEASE_SELECT_YOUR_OCCUPATION = 82;
    public static final int PLEASE_SELECT_YOUR_GENDER = 83;
    public static final int YOU_MAY_NOT_ATTACK_IN_A_PEACEFUL_ZONE = 84;
    public static final int YOU_MAY_NOT_ATTACK_THIS_TARGET_IN_A_PEACEFUL_ZONE = 85;
    public static final int PLEASE_ENTER_YOUR_ID = 86;
    public static final int PLEASE_ENTER_YOUR_PASSWORD = 87;
    public static final int YOUR_PROTOCOL_VERSION_IS_DIFFERENT_PLEASE_RESTART_YOUR_CLIENT_AND_RUN_A_FULL_CHECK = 88;
    public static final int YOUR_PROTOCOL_VERSION_IS_DIFFERENT_PLEASE_CONTINUE = 89;
    public static final int YOU_ARE_UNABLE_TO_CONNECT_TO_THE_SERVER = 90;
    public static final int PLEASE_SELECT_YOUR_HAIRSTYLE = 91; 
    public static final int S1_HAS_WORN_OFF = 92; // Ã�â€”Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½ Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š: $s1.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_SP_FOR_THIS = 93; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // SP.
    public static final int COPYRIGHT_NCSOFT_CORPORATION_ALL_RIGHTS_RESERVED = 94; // 2008
    // Copyright
    // NCsoft
    // Corporation.
    // All
    // Rights
    // Reserved.
    public static final int YOU_HAVE_EARNED_S1_EXPERIENCE_AND_S2_SP = 95; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾:
    // $s1
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°
    // Ã�Â¸
    // $s2
    // SP.
    public static final int YOUR_LEVEL_HAS_INCREASED = 96; 
    public static final int THIS_ITEM_CANNOT_BE_MOVED = 97; 
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int THIS_ITEM_CANNOT_BE_DISCARDED = 98; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int THIS_ITEM_CANNOT_BE_TRADED_OR_SOLD = 99; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’/Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int S1_REQUESTS_A_TRADE_DO_YOU_WANT_TO_TRADE = 100; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã�Â³Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ‘Æ’.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’?
    public static final int YOU_CANNOT_EXIT_WHILE_IN_COMBAT = 101; // Ã�â€™Ã�Â¾ Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã‘ï¿½ Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸ Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int YOU_CANNOT_RESTART_WHILE_IN_COMBAT = 102; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int THIS_ID_IS_CURRENTLY_LOGGED_IN = 103; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â¾Ã�Âº Ã‘ï¿½
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â¼ Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int YOU_MAY_NOT_EQUIP_ITEMS_WHILE_CASTING_OR_PERFORMING_A_SKILL = 104; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int YOU_HAVE_INVITED_C1_TO_JOIN_YOUR_PARTY = 105; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.
    public static final int YOU_HAVE_JOINED_S1S_PARTY = 106; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’ Ã�Âº
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.
    public static final int S1_HAS_JOINED_THE_PARTY = 107; // $c1 Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Âº Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.
    public static final int S1_HAS_LEFT_THE_PARTY = 108; // $c1 Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â¸Ã�Â·
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int INVALID_TARGET = 109; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½ Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’.
    public static final int S1_S2S_EFFECT_CAN_BE_FELT = 110; // Ã�â€™Ã‘â€¹ Ã�Â¾Ã‘â€°Ã‘Æ’Ã‘â€°Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š: $s1.
    public static final int YOUR_SHIELD_DEFENSE_HAS_SUCCEEDED = 111; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â´Ã�Â°Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬.
    public static final int YOU_HAVE_RUN_OUT_OF_ARROWS = 112; // Ã�Â¡Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â»Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’.
    public static final int S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS = 113; // $s1:
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’.
    public static final int YOU_HAVE_ENTERED_THE_SHADOW_OF_THE_MOTHER_TREE = 114; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â¢Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�â€�Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â°
    // Ã�â€“Ã�Â¸Ã�Â·Ã�Â½Ã�Â¸.
    public static final int YOU_HAVE_LEFT_THE_SHADOW_OF_THE_MOTHER_TREE = 115; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â¢Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�â€�Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â°
    // Ã�â€“Ã�Â¸Ã�Â·Ã�Â½Ã�Â¸.
    public static final int YOU_HAVE_ENTERED_A_PEACEFUL_ZONE = 116; // Ã�â€™Ã‘â€¹ Ã�Â·Ã�Â°Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â² Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’.
    public static final int YOU_HAVE_LEFT_THE_PEACEFUL_ZONE = 117; // Ã�â€™Ã‘â€¹ Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â· Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘â€¹.
    public static final int YOU_HAVE_REQUESTED_A_TRADE_WITH_C1 = 118; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // $c1.
    public static final int C1_HAS_DENIED_YOUR_REQUEST_TO_TRADE = 119; // $c1
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ�Â¸.
    public static final int YOU_BEGIN_TRADING_WITH_C1 = 120; // $c1: Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    public static final int C1_HAS_CONFIRMED_THE_TRADE = 121; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ‘Æ’.
    public static final int YOU_MAY_NO_LONGER_ADJUST_ITEMS_IN_THE_TRADE_BECAUSE_THE_TRADE_HAS_BEEN_CONFIRMED = 122; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ�Â°
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â´Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOUR_TRADE_IS_SUCCESSFUL = 123; // Ã�Å¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½ Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int C1_HAS_CANCELLED_THE_TRADE = 124; // $c1 Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ‘Æ’.
    public static final int DO_YOU_WISH_TO_EXIT_THE_GAME = 125; // Ã�â€”Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’?
    public static final int DO_YOU_WISH_TO_EXIT_TO_THE_CHARACTER_SELECT_SCREEN = 126; // Ã�â€™Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°?
    public static final int YOU_HAVE_BEEN_DISCONNECTED_FROM_THE_SERVER_PLEASE_LOGIN_AGAIN = 127; // Ã�Â¡Ã�Â²Ã‘ï¿½Ã�Â·Ã‘Å’
    // Ã‘ï¿½
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â¾Ã�Â¼
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â·Ã�Â°Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int YOUR_CHARACTER_CREATION_HAS_FAILED = 128; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int YOUR_INVENTORY_IS_FULL = 129; // Ã�â€™Ã‘ï¿½Ã�Âµ Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â¹Ã�ÂºÃ�Â¸ Ã�Â²
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã�Âµ Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â€¹.
    public static final int YOUR_WAREHOUSE_IS_FULL = 130; // Ã�â€™Ã‘ï¿½Ã�Âµ Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â¹Ã�ÂºÃ�Â¸ Ã�Â²
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â€¹.
    public static final int S1_HAS_LOGGED_IN = 131; // $s1 Ã�Â·Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int S1_HAS_BEEN_ADDED_TO_YOUR_FRIEND_LIST = 132; // $s1
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int S1_HAS_BEEN_REMOVED_FROM_YOUR_FRIEND_LIST = 133; // $s1
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int PLEASE_CHECK_YOUR_FRIEND_LIST_AGAIN = 134; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int S1_DID_NOT_REPLY_TO_YOUR_INVITATION_PARTY_INVITATION_HAS_BEEN_CANCELLED = 135; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€¡Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int YOU_HAVE_NOT_REPLIED_TO_C1S_INVITATION_THE_OFFER_HAS_BEEN_CANCELLED = 136; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â¸Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1.
    public static final int THERE_ARE_NO_MORE_ITEMS_IN_THE_SHORTCUT = 137; // Ã�â€˜Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â²Ã‘ï¿½Ã�Â·Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Âº
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ‘Æ’.
    public static final int DESIGNATE_SHORTCUT = 138; // Ã�ï¿½Ã�Âµ Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’ Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº.
    public static final int C1_HAS_RESISTED_YOUR_S2 = 139; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ $s2 Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾ Ã�Â½Ã�Â°
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’ $c1.
    public static final int YOUR_SKILL_WAS_REMOVED_DUE_TO_A_LACK_OF_MP = 140; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // MP,
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�ÂºÃ‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int ONCE_THE_TRADE_IS_CONFIRMED_THE_ITEM_CANNOT_BE_MOVED_AGAIN = 141; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ�Â°
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â´Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_ARE_ALREADY_TRADING_WITH_SOMEONE = 142; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã‘ï¿½
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼.
    public static final int C1_IS_ALREADY_TRADING_WITH_ANOTHER_PERSON_PLEASE_TRY_AGAIN_LATER = 143; // $c1
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼.
    public static final int THAT_IS_THE_INCORRECT_TARGET = 144; // Ã�ï¿½Ã�ÂµÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã‘Å’Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’.
    public static final int THAT_PLAYER_IS_NOT_ONLINE = 145; // Ã�Â­Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾ Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â½Ã�ÂµÃ‘â€š Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int CHATTING_IS_NOW_PERMITTED = 146; // Ã�â€˜Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â° Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â°.
    public static final int CHATTING_IS_CURRENTLY_PROHIBITED = 147; // Ã�Â§Ã�Â°Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOU_CANNOT_USE_QUEST_ITEMS = 148; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int YOU_CANNOT_PICK_UP_OR_USE_ITEMS_WHILE_TRADING = 149; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã‘â€šÃ‘Å’/Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int YOU_CANNOT_DISCARD_OR_DESTROY_AN_ITEM_WHILE_TRADING_AT_A_PRIVATE_STORE = 150; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’/Ã‘Æ’Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ�Â¸.
    public static final int THAT_IS_TOO_FAR_FROM_YOU_TO_DISCARD = 151; // Ã�Â¡Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�ÂºÃ�Â¾,
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int YOU_HAVE_INVITED_WRONG_TARGET = 152; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’.
    public static final int S1_IS_BUSY_PLEASE_TRY_AGAIN_LATER = 153; // $c1
    // Ã�Â·Ã�Â°Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ�Â¼-Ã‘â€šÃ�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼.
    public static final int ONLY_THE_LEADER_CAN_GIVE_OUT_INVITATIONS = 154; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂµÃ�Âµ
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬.
    public static final int PARTY_IS_FULL = 155; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â° Ã�Â·Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DRAIN_WAS_ONLY_HALF_SUCCESSFUL = 156; // Ã�Å¸Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾ Ã�Â½Ã�Â°
    // 50%.
    @Deprecated
    public static final int YOU_RESISTED_S1S_DRAIN = 157; // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // C1_RESISTED_C2S_DRAIN
    // = 2267
    @Deprecated
    public static final int ATTACK_FAILED = 158; // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾ C1S_ATTACK_FAILED =
    // 2268
    @Deprecated
    public static final int RESISTED_AGAINST_S1S_MAGIC = 159; // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // C1_RESISTED_C2S_MAGIC
    // = 2269
    public static final int S1_IS_A_MEMBER_OF_ANOTHER_PARTY_AND_CANNOT_BE_INVITED = 160; // $c1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.
    public static final int THAT_PLAYER_IS_NOT_CURRENTLY_ONLINE = 161; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â½Ã�ÂµÃ‘â€š Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int WAREHOUSE_IS_TOO_FAR = 162; // Ã�Â¥Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼ Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�ÂºÃ�Â¾.
    public static final int YOU_CANNOT_DESTROY_IT_BECAUSE_THE_NUMBER_IS_INCORRECT = 163; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�Â·Ã�Â°Ã�Â´Ã�Â°Ã�Â½Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾.
    public static final int WAITING_FOR_ANOTHER_REPLY = 164; // Ã�Å¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIEND_LIST = 165; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â±Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int FRIEND_LIST_IS_NOT_READY_YET_PLEASE_REGISTER_AGAIN_LATER = 166; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int S1_IS_ALREADY_ON_YOUR_FRIEND_LIST = 167; // $c1 Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â² Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int S1_HAS_REQUESTED_TO_BECOME_FRIENDS = 168; // $c1
    // Ã‘â€¦Ã�Â¾Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int ACCEPT_FRIENDSHIP_0_1__1_TO_ACCEPT_0_TO_DENY = 169; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’?
    // 0/1
    // (1
    // -
    // Ã�Â´Ã�Â°,
    // 0
    // -
    // Ã�Â½Ã�ÂµÃ‘â€š)
    public static final int THE_USER_WHO_REQUESTED_TO_BECOME_FRIENDS_IS_NOT_FOUND_IN_THE_GAME = 170; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã‘Å’Ã‘ï¿½,
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int S1_IS_NOT_ON_YOUR_FRIEND_LIST = 171; // $c1 Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â² Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int YOU_LACK_THE_FUNDS_NEEDED_TO_PAY_FOR_THIS_TRANSACTION = 172; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Â°.
    public static final int YOU_LACK_THE_FUNDS_NEEDED_TO_PAY_FOR_THIS_TRANSACTION_2 = 173; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸.
    public static final int THE_PERSONS_INVENTORY_IS_FULL = 174; // Ã�Â£ Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â² Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã�Âµ
    // Ã�Â½Ã�ÂµÃ‘â€š Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°.
    public static final int HP_WAS_FULLY_RECOVERED_AND_SKILL_WAS_REMOVED = 175; // HP
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’,
    // Ã�Â¸
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int THE_PERSON_IS_IN_A_MESSAGE_REFUSAL_MODE = 176; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â¾Ã�Âº
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int MESSAGE_REFUSAL_MODE = 177; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾.
    public static final int MESSAGE_ACCEPTANCE_MODE = 178; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_DISCARD_THOSE_ITEMS_HERE = 179; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int YOU_HAVE_S1_DAY_S_LEFT_UNTIL_DELETION_DO_YOU_WANT_TO_CANCEL_DELETION = 180; // Ã�â€�Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�Â´Ã�Â¾
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½:
    // $s1.
    // Ã�Å¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ?
    public static final int CANNOT_SEE_TARGET = 181; // Ã�Â¦Ã�ÂµÃ�Â»Ã�Â¸ Ã�Â½Ã�Âµ Ã�Â²Ã�Â¸Ã�Â´Ã�Â½Ã�Â¾.
    public static final int DO_YOU_WANT_TO_QUIT_THE_CURRENT_QUEST = 182; // Ã�Å¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€š?
    public static final int THERE_ARE_TOO_MANY_USERS_ON_THE_SERVER_PLEASE_TRY_AGAIN_LATER = 183; // Ã�ï¿½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Âµ
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int PLEASE_TRY_AGAIN_LATER = 184; // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ Ã�Â·Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int SELECT_USER_TO_INVITE_TO_YOUR_PARTY = 185; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.
    public static final int SELECT_USER_TO_INVITE_TO_YOUR_CLAN = 186; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â² Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int SELECT_USER_TO_EXPEL = 187; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾ Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int CREATE_CLAN_NAME = 188; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int CLAN_HAS_BEEN_CREATED = 189; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½ Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½.
    public static final int YOU_HAVE_FAILED_TO_CREATE_A_CLAN = 190; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int CLAN_MEMBER_S1_HAS_BEEN_EXPELLED = 191; // $s1
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â· Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_HAVE_FAILED_TO_EXPEL_S1_FROM_THE_CLAN = 192; // $s1:
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int CLAN_HAS_DISPERSED = 193; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½ Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOU_HAVE_FAILED_TO_DISPERSE_THE_CLAN = 194; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int ENTERED_THE_CLAN = 195; // Ã�â€™Ã‘â€¹ Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»Ã�Â¸ Ã�Â² Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int S1_REFUSED_TO_JOIN_THE_CLAN = 196; // $s1
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’ Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int WITHDRAWN_FROM_THE_CLAN = 197; // Ã�â€™Ã‘â€¹ Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã�Â»Ã�Â¸ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int YOU_HAVE_FAILED_TO_WITHDRAW_FROM_THE_S1_CLAN = 198; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // $s1.
    public static final int YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN_YOU_ARE_NOT_ALLOWED_TO_JOIN_ANOTHER_CLAN_FOR_24_HOURS = 199; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 24
    // Ã‘â€¡.
    public static final int YOU_HAVE_WITHDRAWN_FROM_THE_PARTY = 200; // Ã�â€™Ã‘â€¹ Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int S1_WAS_EXPELLED_FROM_THE_PARTY = 201; // $c1
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â· Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_HAVE_BEEN_EXPELLED_FROM_THE_PARTY = 202; // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int THE_PARTY_HAS_DISPERSED = 203; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int INCORRECT_NAME_PLEASE_TRY_AGAIN = 204;
    public static final int INCORRECT_CHARACTER_NAME_PLEASE_ASK_THE_GM = 205;
    public static final int ENTER_NAME_OF_CLAN_TO_DECLARE_WAR_ON = 206;
    public static final int S2_OF_THE_S1_CLAN_REQUESTS_DECLARATION_OF_WAR_DO_YOU_ACCEPT = 207;
    public static final int PLEASE_INCLUDE_FILE_TYPE_WHEN_ENTERING_FILE_PATH = 208;
    public static final int THE_SIZE_OF_THE_IMAGE_FILE_IS_DIFFERENT_PLEASE_ADJUST_TO_16_12 = 209;
    public static final int CANNOT_FIND_FILE_PLEASE_ENTER_PRECISE_PATH = 210;
    public static final int CAN_ONLY_REGISTER_16_12_SIZED_BMP_FILES_OF_256_COLORS = 211;
    public static final int YOU_ARE_NOT_A_CLAN_MEMBER = 212;
    public static final int NOT_WORKING_PLEASE_TRY_AGAIN_LATER = 213;
    public static final int TITLE_HAS_CHANGED = 214; // Ã�Â¢Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â» Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int WAR_WITH_THE_S1_CLAN_HAS_BEGUN = 215; // Ã�ï¿½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Â° Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼ $s1.
    public static final int WAR_WITH_THE_S1_CLAN_HAS_ENDED = 216; // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â° Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼ $s1
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN = 217; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1!
    public static final int YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN = 218; // Ã�â€™Ã‘â€¹
    // Ã�ÂºÃ�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1.
    public static final int YOUR_CLAN_LEADER_HAS_DIEDYOU_HAVE_BEEN_DEFEATED_BY_THE_S1_CLAN = 219; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã‘Æ’Ã�Â±Ã�Â¸Ã‘â€š.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1.
    public static final int YOU_HAVE_S1_MINUTES_LEFT_UNTIL_THE_CLAN_WAR_ENDS = 220; // Ã�â€�Ã�Â¾
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_TIME_LIMIT_FOR_THE_CLAN_WAR_IS_UPWAR_WITH_THE_S1_CLAN_IS_OVER = 221; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�ÂºÃ�Â»Ã�Â¾.
    // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int S1_HAS_JOINED_THE_CLAN = 222; // $s1 Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â°Ã�ÂµÃ‘â€š Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int S1_HAS_WITHDRAWN_FROM_THE_CLAN = 223; // $s1 Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Â¸Ã�Â· Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int S1_DID_NOT_RESPOND_INVITATION_TO_THE_CLAN_HAS_BEEN_CANCELLED = 224; // $s1
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€¡Ã�Â°Ã�ÂµÃ‘â€š.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int YOU_DIDNT_RESPOND_TO_S1S_INVITATION_JOINING_HAS_BEEN_CANCELLED = 225; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // $s1.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int THE_S1_CLAN_DID_NOT_RESPOND_WAR_PROCLAMATION_HAS_BEEN_REFUSED = 226; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â¸Ã�Â».
    // Ã�Å¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int CLAN_WAR_HAS_BEEN_REFUSED_BECAUSE_YOU_DID_NOT_RESPOND_TO_S1_CLANS_WAR_PROCLAMATION = 227; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’
    // $s1.
    // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°.
    public static final int REQUEST_TO_END_WAR_HAS_BEEN_DENIED = 228; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int YOU_ARE_NOT_QUALIFIED_TO_CREATE_A_CLAN = 229; // Ã�Å¸Ã�Â¾Ã�ÂºÃ�Â°
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int YOU_MUST_WAIT_10_DAYS_BEFORE_CREATING_A_NEW_CLAN = 230; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 10
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾.
    public static final int AFTER_A_CLAN_MEMBER_IS_DISMISSED_FROM_A_CLAN_THE_CLAN_MUST_WAIT_AT_LEAST_A_DAY_BEFORE_ACCEPTING_A_NEW_MEMBER = 231; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã‘Æ’Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // 24
    // Ã‘â€¡,
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã‘â€šÃ�ÂµÃ�Â¼
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int AFTER_LEAVING_OR_HAVING_BEEN_DISMISSED_FROM_A_CLAN_YOU_MUST_WAIT_AT_LEAST_A_DAY_BEFORE_JOINING_ANOTHER_CLAN = 232; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 24
    // Ã‘â€¡
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â¸Ã�Â·
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾.
    public static final int THE_ACADEMY_ROYAL_GUARD_ORDER_OF_KNIGHTS_IS_FULL_AND_CANNOT_ACCEPT_NEW_MEMBERS_AT_THIS_TIME = 233; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    public static final int THE_TARGET_MUST_BE_A_CLAN_MEMBER = 234; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â² Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ.
    public static final int YOU_CANNOT_TRANSFER_YOUR_RIGHTS = 235;
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã�Â¶Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°Ã�Â¼Ã�Â¸.
    public static final int ONLY_THE_CLAN_LEADER_IS_ENABLED = 236; // Ã�Â­Ã‘â€šÃ�Â¾ Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int CANNOT_FIND_CLAN_LEADER = 237; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾ Ã�Â½Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã‘Æ’ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int NOT_JOINED_IN_ANY_CLAN = 238; // Ã�ï¿½Ã�Âµ Ã�Â² Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ.
    public static final int THE_CLAN_LEADER_CANNOT_WITHDRAW = 239; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â° Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int CURRENTLY_INVOLVED_IN_CLAN_WAR = 240; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½ Ã�Â¸Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    public static final int LEADER_OF_THE_S1_CLAN_IS_NOT_LOGGED_IN = 241; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã‘â€¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int SELECT_TARGET = 242; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’.
    public static final int CANNOT_PROCLAIM_WAR_ON_ALLIED_CLANS = 243; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã‘ï¿½Ã�Â¾Ã‘Å½Ã�Â·Ã�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’.
    public static final int UNQUALIFIED_TO_REQUEST_DECLARATION_OF_CLAN_WAR = 244; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    public static final int _5_DAYS_HAS_NOT_PASSED_SINCE_YOU_WERE_REFUSED_WAR_DO_YOU_WANT_TO_CONTINUE = 245; // Ã�Â¡
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°
    // Ã�Â¾Ã‘â€š
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // 5
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_OTHER_CLAN_IS_CURRENTLY_AT_WAR = 246; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â²Ã�Â¾Ã‘Å½Ã�ÂµÃ‘â€š.
    public static final int YOU_HAVE_ALREADY_BEEN_AT_WAR_WITH_THE_S1_CLAN_5_DAYS_MUST_PASS_BEFORE_YOU_CAN_PROCLAIM_WAR_AGAIN = 247; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // 5
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹.
    public static final int YOU_CANNOT_PROCLAIM_WAR_THE_S1_CLAN_DOES_NOT_HAVE_ENOUGH_MEMBERS = 248; // Ã�â€™
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ
    // $s1
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�Â°Ã�Â»Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹.
    // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    public static final int DO_YOU_WISH_TO_SURRENDER_TO_THE_S1_CLAN = 249; // Ã�Å¡Ã�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1?
    public static final int YOU_HAVE_PERSONALLY_SURRENDERED_TO_THE_S1_CLAN_YOU_ARE_LEAVING_THE_CLAN_WAR = 250; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’
    // $s1.
    // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_CANNOT_PROCLAIM_WAR_YOU_ARE_AT_WAR_WITH_ANOTHER_CLAN = 251; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’.
    public static final int ENTER_THE_NAME_OF_CLAN_TO_SURRENDER_TO = 252; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int ENTER_THE_NAME_OF_CLAN_TO_REQUEST_END_OF_WAR = 253; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã�Â³Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¼Ã�Â¸Ã‘â‚¬.
    public static final int CLAN_LEADER_CANNOT_SURRENDER_PERSONALLY = 254; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int THE_S1_CLAN_HAS_REQUESTED_TO_END_WAR_DO_YOU_AGREE = 255; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã�Â³Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�Â¸Ã‘â‚¬.
    // Ã�Â¡Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½?
    public static final int ENTER_NAME = 256; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Æ’Ã�Â».
    public static final int DO_YOU_PROPOSE_TO_THE_S1_CLAN_TO_END_THE_WAR = 257; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¼Ã�Â¸Ã‘â‚¬
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’
    // $s1?
    public static final int NOT_INVOLVED_IN_CLAN_WAR = 258;
    public static final int SELECT_CLAN_MEMBERS_FROM_LIST = 259;
    public static final int FAME_LEVEL_HAS_DECREASED_5_DAYS_HAVE_NOT_PASSED_SINCE_YOU_WERE_REFUSED_WAR = 260;
    public static final int CLAN_NAME_IS_INCORRECT = 261; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int CLAN_NAMES_LENGTH_IS_INCORRECT = 262; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int DISPERSION_HAS_ALREADY_BEEN_REQUESTED = 263; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_DISSOLVE_A_CLAN_WHILE_ENGAGED_IN_A_WAR = 264; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    public static final int YOU_CANNOT_DISSOLVE_A_CLAN_DURING_A_SIEGE_OR_WHILE_PROTECTING_A_CASTLE = 265; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹.
    public static final int YOU_CANNOT_DISSOLVE_A_CLAN_WHILE_OWNING_A_CLAN_HALL_OR_CASTLE = 266; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â¼/Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â¾Ã�Â¼.
    public static final int NO_REQUESTS_FOR_DISPERSION = 267; // Ã�ï¿½Ã�ÂµÃ‘â€š Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â¸ Ã�Â½Ã�Â°
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int PLAYER_ALREADY_BELONGS_TO_A_CLAN = 268; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ.
    public static final int YOU_CANNOT_EXPEL_YOURSELF = 269;
    public static final int YOU_HAVE_ALREADY_SURRENDERED = 270; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�ÂºÃ�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸.
    public static final int TITLE_ENDOWMENT_IS_ONLY_POSSIBLE_WHEN_CLANS_SKILL_LEVELS_ARE_ABOVE_3 = 271; // Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã�Â¾Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // 3
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ.
    public static final int CLAN_CREST_REGISTRATION_IS_ONLY_POSSIBLE_WHEN_CLANS_SKILL_LEVELS_ARE_ABOVE_3 = 272; // Ã�Â£Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // 3
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ.
    public static final int PROCLAMATION_OF_CLAN_WAR_IS_ONLY_POSSIBLE_WHEN_CLANS_SKILL_LEVELS_ARE_ABOVE_3 = 273; // Ã�Å¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // 3
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ.
    public static final int CLANS_SKILL_LEVEL_HAS_INCREASED = 274; // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½.
    public static final int CLAN_HAS_FAILED_TO_INCREASE_SKILL_LEVEL = 275; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_ITEMS_TO_LEARN_SKILLS = 276; // Ã�ï¿½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�Â°Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â¸Ã�Â°Ã�Â»Ã�Â¾Ã�Â²,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int YOU_HAVE_EARNED_S1_2 = 277; // Ã�â€™Ã‘â€¹Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾ Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ: $s1.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_SP_TO_LEARN_SKILLS = 278; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // SP
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_ADENA = 279; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int YOU_DO_NOT_HAVE_ANY_ITEMS_TO_SELL = 280; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã‘Æ’.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_CUSTODY_FEES = 281; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Â°.
    public static final int YOU_HAVE_NOT_DEPOSITED_ANY_ITEMS_IN_YOUR_WAREHOUSE = 282; // Ã�â€™
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â².
    public static final int YOU_HAVE_ENTERED_A_COMBAT_ZONE = 283; // Ã�â€™Ã‘â€¹ Ã�Â·Ã�Â°Ã‘Ë†Ã�Â»Ã�Â¸ Ã�Â²
    // Ã�Â±Ã�Â¾Ã�ÂµÃ�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’.
    public static final int YOU_HAVE_LEFT_A_COMBAT_ZONE = 284; // Ã�â€™Ã‘â€¹ Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸ Ã�Â¸Ã�Â·
    // Ã�Â±Ã�Â¾Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¹ Ã�Â·Ã�Â¾Ã�Â½Ã‘â€¹.
    public static final int CLAN_S1_HAS_SUCCEEDED_IN_ENGRAVING_THE_RULER = 285; // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // $s1!
    public static final int YOUR_BASE_IS_BEING_ATTACKED = 286; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â° Ã�Â±Ã�Â°Ã�Â·Ã�Â°
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int THE_OPPONENT_CLAN_HAS_BEGUN_TO_ENGRAVE_THE_RULER = 287; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã‘Ë†Ã‘Æ’
    // Ã�Â±Ã�Â°Ã�Â·Ã‘Æ’.
    public static final int THE_CASTLE_GATE_HAS_BEEN_BROKEN_DOWN = 288; // Ã�â€™Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int SINCE_A_HEADQUARTERS_ALREADY_EXISTS_YOU_CANNOT_BUILD_ANOTHER_ONE = 289; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã‘Æ’
    // Ã�Â±Ã�Â°Ã�Â·Ã‘Æ’.
    public static final int YOU_CANNOT_SET_UP_A_BASE_HERE = 290; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â°Ã�Â·Ã‘Æ’ Ã�Â·Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’.
    public static final int CLAN_S1_IS_VICTORIOUS_OVER_S2S_CASTLE_SIEGE = 291; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»
    // $s2!
    public static final int S1_HAS_ANNOUNCED_THE_CASTLE_SIEGE_TIME = 292; // $s1
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int THE_REGISTRATION_TERM_FOR_S1_HAS_ENDED = 293; // Ã�Â Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘Æ’
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_CANNOT_SUMMON_A_BASE_BECAUSE_YOU_ARE_NOT_IN_BATTLE = 294; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ.
    // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â°Ã�Â·Ã‘Æ’.
    public static final int S1S_SIEGE_WAS_CANCELED_BECAUSE_THERE_WERE_NO_CLANS_THAT_PARTICIPATED = 295; // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // $s1
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�Â¾Ã‘â€šÃ‘ï¿½Ã‘Æ’Ã‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Âº.
    public static final int YOU_RECEIVED_S1_DAMAGE_FROM_TAKING_A_HIGH_FALL = 296; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�Â¿Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ‘â€¹.
    public static final int YOU_RECEIVED_S1_DAMAGE_BECAUSE_YOU_WERE_UNABLE_TO_BREATHE = 297; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘â€š
    // Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘Ë†Ã‘Å’Ã‘ï¿½.
    public static final int YOU_HAVE_DROPPED_S1 = 298; // Ã�â€™Ã‘â€¹ Ã�Â²Ã‘â€¹Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸: $s1.
    public static final int S1_HAS_OBTAINED_S3_S2 = 299; // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š: $s2
    // ($s3 Ã‘Ë†Ã‘â€š.)
    public static final int S1_HAS_OBTAINED_S2 = 300; // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š: $s2.
    public static final int S2_S1_HAS_DISAPPEARED = 301; // Ã�ËœÃ‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â»Ã�Â¾: $s1 ($s2
    // Ã‘Ë†Ã‘â€š.)
    public static final int S1_HAS_DISAPPEARED = 302; // Ã�ËœÃ‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â»Ã�Â¾: $s1.
    public static final int SELECT_ITEM_TO_ENCHANT = 303; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â»Ã‘ï¿½ Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int CLAN_MEMBER_S1_HAS_LOGGED_INTO_GAME = 304; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã�Â·Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int THE_PLAYER_DECLINED_TO_JOIN_YOUR_PARTY = 305; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.
    public static final int YOU_HAVE_FAILED_TO_DELETE_THE_CHARACTER = 306; // Ã�Â£Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int YOU_HAVE_FAILED_TO_TRADE_WITH_THE_WAREHOUSE = 307; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾
    // Ã�Â¡Ã�Â¼Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¼
    // Ã�Â¡Ã�ÂºÃ�Â»Ã�Â°Ã�Â´Ã�Â°.
    public static final int FAILED_TO_JOIN_THE_CLAN = 308; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶ Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½ Ã�Âº
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’.
    public static final int SUCCEEDED_IN_EXPELLING_A_CLAN_MEMBER = 309; // Ã�ËœÃ‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾.
    public static final int FAILED_TO_EXPEL_A_CLAN_MEMBER = 310; // Ã�ËœÃ‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â° Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Âµ Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int CLAN_WAR_HAS_BEEN_ACCEPTED = 311; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹ Ã�Â¼Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾.
    public static final int CLAN_WAR_HAS_BEEN_REFUSED = 312; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹ Ã�Â¼Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int THE_CEASE_WAR_REQUEST_HAS_BEEN_ACCEPTED = 313; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾.
    public static final int FAILED_TO_SURRENDER = 314; // Ã�Å¡Ã�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã‘ï¿½Ã‘â€ Ã�Â¸Ã‘ï¿½ Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int FAILED_TO_PERSONALLY_SURRENDER = 315; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸ Ã�Â¸Ã�Â·
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    public static final int FAILED_TO_WITHDRAW_FROM_THE_PARTY = 316; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸ Ã�Â¸Ã�Â·
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int FAILED_TO_EXPEL_A_PARTY_MEMBER = 317; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â¸Ã�Â· Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int FAILED_TO_DISPERSE_THE_PARTY = 318; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.
    public static final int YOU_ARE_UNABLE_TO_UNLOCK_THE_DOOR = 319; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’.
    public static final int YOU_HAVE_FAILED_TO_UNLOCK_THE_DOOR = 320; // Ã�â€™Ã�Â°Ã�Â¼ Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’.
    public static final int IT_IS_NOT_LOCKED = 321; // Ã�ï¿½Ã�Âµ Ã�Â·Ã�Â°Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¾.
    public static final int PLEASE_DECIDE_ON_THE_SALES_PRICE = 322; // Ã�Â£Ã�ÂºÃ�Â°Ã�Â¶Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â¸.
    public static final int YOUR_FORCE_HAS_INCREASED_TO_S1_LEVEL = 323; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â´Ã�Â¾
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int YOUR_FORCE_HAS_REACHED_MAXIMUM_CAPACITY_ = 324; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â»Ã�Â°
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int THE_CORPSE_HAS_ALREADY_DISAPPEARED = 325; // Ã�Â¢Ã‘â‚¬Ã‘Æ’Ã�Â¿
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·.
    public static final int SELECT_TARGET_FROM_LIST = 326; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’ Ã�Â¸Ã�Â·
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°.
    public static final int YOU_CANNOT_EXCEED_80_CHARACTERS = 327; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ 80
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int PLEASE_INPUT_TITLE_USING_LESS_THAN_128_CHARACTERS = 328; // Ã�Å“Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // -
    // 128
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int PLEASE_INPUT_CONTENTS_USING_LESS_THAN_3000_CHARACTERS = 329; // Ã�Å“Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�Â¼
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘ï¿½Ã‘â€šÃ�Â°
    // -
    // 3000
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int A_ONE_LINE_RESPONSE_MAY_NOT_EXCEED_128_CHARACTERS = 330; // Ã�Å“Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // -
    // 128
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int YOU_HAVE_ACQUIRED_S1_SP = 331; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾: $s1 SP.
    public static final int DO_YOU_WANT_TO_BE_RESTORED = 332; // Ã�Â¥Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â·Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½?
    public static final int YOU_HAVE_RECEIVED_S1_DAMAGE_BY_CORES_BARRIER = 333; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘â€š
    // Ã�Â±Ã�Â°Ã‘â‚¬Ã‘Å’Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�Â¯Ã�Â´Ã‘â‚¬Ã�Â°.
    public static final int PLEASE_ENTER_STORE_MESSAGE = 334; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int S1_IS_ABORTED = 335; // $s1: Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int S1_IS_CRYSTALLIZED_DO_YOU_WANT_TO_CONTINUE = 336; // $s1:
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â¸Ã�Â·Ã�Â°Ã‘â€ Ã�Â¸Ã‘Å½?
    public static final int SOULSHOT_DOES_NOT_MATCH_WEAPON_GRADE = 337; // Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´
    // Ã�â€�Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â³Ã‘Æ’
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã‘ï¿½.
    public static final int NOT_ENOUGH_SOULSHOTS = 338; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾ Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â¾Ã�Â²
    // Ã�â€�Ã‘Æ’Ã‘Ë†Ã�Â¸.
    public static final int CANNOT_USE_SOULSHOTS = 339; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½ Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´ Ã�â€�Ã‘Æ’Ã‘Ë†Ã�Â¸.
    public static final int PRIVATE_STORE_UNDER_WAY = 340; // Ã�â€ºÃ�Â¸Ã‘â€¡Ã�Â½Ã�Â°Ã‘ï¿½ Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°.
    public static final int NOT_ENOUGH_MATERIALS = 341; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¼Ã�Â°Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â¸Ã�Â°Ã�Â»Ã�Â¾Ã�Â².
    public static final int POWER_OF_THE_SPIRITS_ENABLED = 342; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¾Ã�Â¹.
    public static final int SWEEPER_FAILED_TARGET_NOT_SPOILED = 343; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’ Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€ Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int POWER_OF_THE_SPIRITS_DISABLED = 344; // Ã�Â¡Ã�Â¸Ã�Â»Ã�Â° Ã�Â´Ã‘Æ’Ã‘â€¦Ã�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°.
    public static final int CHAT_ENABLED = 345; // Ã�Â§Ã�Â°Ã‘â€š Ã�Â²Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½.
    public static final int CHAT_DISABLED = 346; // Ã�Â§Ã�Â°Ã‘â€š Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½.
    // 347 Ã�ÂºÃ�Â°Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾-Ã‘â€šÃ�Â¾ Ã‘â€¦Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â° Ã�Â½Ã�Âµ Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â¶Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½ Ã�Â² Ã�ÂºÃ�Â»Ã�Â¸Ã�ÂµÃ�Â½Ã‘â€šÃ�Âµ, 351 Ã�Â²Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾ Ã�Â½Ã�ÂµÃ�Â³Ã�Â¾
    @Deprecated
    public static final int INCORRECT_ITEM_COUNT_0 = 347; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â².
    public static final int INCORRECT_ITEM_PRICE = 348; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½ Ã‘â€ Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int PRIVATE_STORE_ALREADY_CLOSED = 349; // Ã�â€ºÃ�Â¸Ã‘â€¡Ã�Â½Ã�Â°Ã‘ï¿½ Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°.
    public static final int ITEM_OUT_OF_STOCK = 350; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â½.
    public static final int INCORRECT_ITEM_COUNT = 351; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int INCORRECT_ITEM = 352; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int CANNOT_PURCHASE = 353; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½ Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int CANCEL_ENCHANT = 354; // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int INAPPROPRIATE_ENCHANT_CONDITIONS = 355; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â² Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½Ã‘â€¦.
    public static final int REJECT_RESURRECTION = 356; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int ALREADY_SPOILED = 357; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’ Ã‘Æ’Ã�Â¶Ã�Âµ Ã�Â¾Ã‘â€ Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int S1_HOUR_S_UNTIL_CASTLE_SIEGE_CONCLUSION = 358; // Ã�â€�Ã�Â¾
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°:
    // $s1
    // Ã‘â€¡.
    public static final int S1_MINUTE_S_UNTIL_CASTLE_SIEGE_CONCLUSION = 359; // Ã�â€�Ã�Â¾
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int CASTLE_SIEGE_S1_SECOND_S_LEFT = 360; // Ã�â€�Ã�Â¾ Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹ Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°:
    // $s1 Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int OVER_HIT = 361; // Ã�Â¡Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬!
    public static final int ACQUIRED_S1_BONUS_EXPERIENCE_THROUGH_OVER_HIT = 362; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // $s1
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°
    // Ã�Â·Ã�Â°
    // Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬.
    public static final int CHAT_AVAILABLE_TIME_S1_MINUTE = 363; // Ã�â€�Ã�Â¾
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°: $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int ENTER_USERS_NAME_TO_SEARCH = 364; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°.
    public static final int ARE_YOU_SURE = 365; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã�Â²Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â½Ã‘â€¹?
    public static final int SELECT_HAIR_COLOR = 366; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€š Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½.
    public static final int CANNOT_REMOVE_CLAN_CHARACTER = 367; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾ Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ.
    public static final int EQUIPPED__S1_S2 = 368; // +$s1 $s2: Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ‘â€šÃ�Â¾.
    public static final int YOU_HAVE_OBTAINED__S1S2 = 369; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾: +$s1
    // $s2.
    @Deprecated
    public static final int FAILED_TO_PICK_UP_S1 = 370; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½ Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’: +$s1
    // $s2.
    public static final int ACQUIRED__S1_S2 = 371; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾: +$s1 $s2.
    @Deprecated
    public static final int FAILED_TO_EARN_S1 = 372; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½ Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’: +$s1
    // $s2.
    public static final int DESTROY__S1_S2_DO_YOU_WISH_TO_CONTINUE = 373; // +$s1
    // $s2
    // -
    // Ã‘Æ’Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int CRYSTALLIZE__S1_S2_DO_YOU_WISH_TO_CONTINUE = 374; // +$s1
    // $s2
    // -
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â¸Ã�Â·Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’?
    public static final int DROPPED__S1_S2 = 375; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸: +$s1 $s2.
    public static final int S1_HAS_OBTAINED__S2S3 = 376; // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š: +$s2
    // $s3.
    public static final int _S1_S2_DISAPPEARED = 377; // +$s1 $s2 Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int S1_PURCHASED_S2 = 378; // $c1 Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â°Ã�ÂµÃ‘â€š: $s2.
    public static final int S1_PURCHASED__S2_S3 = 379; // $c1 Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â°Ã�ÂµÃ‘â€š: +$s2
    // $s3.
    public static final int S1_PURCHASED_S3_S2_S = 380; // $c1 Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â°Ã�ÂµÃ‘â€š: $s2
    // ($s3 Ã‘Ë†Ã‘â€š.).
    public static final int CANNOT_CONNECT_TO_PETITION_SERVER = 381; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    public static final int CURRENTLY_THERE_ARE_NO_USERS_THAT_HAVE_CHECKED_OUT_A_GM_ID = 382;
    public static final int REQUEST_CONFIRMED_TO_END_CONSULTATION_AT_PETITION_SERVER = 383;
    public static final int THE_CLIENT_IS_NOT_LOGGED_ONTO_THE_GAME_SERVER = 384;
    public static final int REQUEST_CONFIRMED_TO_BEGIN_CONSULTATION_AT_PETITION_SERVER = 385;
    public static final int PETITION_REQUESTS_MUST_BE_OVER_FIVE_CHARACTERS = 386;
    public static final int ENDING_PETITION_CONSULTATION = 387;
    public static final int NOT_UNDER_PETITION_CONSULTATION = 388;
    public static final int PETITION_APPLICATION_ACCEPTED_RECEIPT_NO_IS_S1 = 389;
    public static final int ALREADY_APPLIED_FOR_PETITION = 390;
    public static final int RECEIPT_NO_S1_PETITION_CANCELLED = 391;
    public static final int UNDER_PETITION_ADVICE = 392;
    public static final int FAILED_TO_CANCEL_PETITION_PLEASE_TRY_AGAIN_LATER = 393;
    public static final int PETITION_CONSULTATION_WITH_S1_UNDER_WAY = 394;
    public static final int ENDING_PETITION_CONSULTATION_WITH_S1 = 395;
    public static final int PLEASE_LOGIN_AFTER_CHANGING_YOUR_TEMPORARY_PASSWORD = 396;
    public static final int NOT_A_PAID_ACCOUNT = 397;
    public static final int YOU_HAVE_NO_MORE_TIME_LEFT_ON_YOUR_ACCOUNT = 398;
    public static final int SYSTEM_ERROR = 399;
    public static final int DISCARD_S1_DO_YOU_WISH_TO_CONTINUE = 400;
    public static final int TOO_MANY_QUESTS_IN_PROGRESS = 401; 
    public static final int YOU_MAY_NOT_GET_ON_BOARD_WITHOUT_A_PASS = 402;
    public static final int YOU_HAVE_EXCEEDED_YOUR_POCKET_MONEY_LIMIT = 403;
    public static final int CREATE_ITEM_LEVEL_IS_TOO_LOW_TO_REGISTER_THIS_RECIPE = 404;
    public static final int THE_TOTAL_PRICE_OF_THE_PRODUCT_IS_TOO_HIGH = 405;
    public static final int PETITION_APPLICATION_ACCEPTED = 406;
    public static final int PETITION_UNDER_PROCESS = 407;
    public static final int SET_PERIOD = 408;
    public static final int SET_TIME_S1_S2_S3 = 409;
    public static final int REGISTRATION_PERIOD = 410; 
    public static final int REGISTRATION_TIME_S1_S2_S3 = 411;
    public static final int BATTLE_BEGINS_IN_S1_S2_S4 = 412;
    public static final int BATTLE_ENDS_IN_S1_S2_S5 = 413;
    public static final int STANDBY = 414;
    public static final int UNDER_SIEGE = 415;
    public static final int CANNOT_BE_EXCHANGED = 416;
    public static final int S1__HAS_BEEN_DISARMED = 417;
    public static final int THERE_IS_A_SIGNIFICANT_DIFFERENCE_BETWEEN_THE_ITEMS_PRICE_AND_ITS_STANDARD_PRICE_PLEASE_CHECK_AGAIN = 418;
    public static final int S1_MINUTE_S_OF_DESIGNATED_USAGE_TIME_LEFT = 419;
    public static final int TIME_EXPIRED = 420;
    public static final int ANOTHER_PERSON_HAS_LOGGED_IN_WITH_THE_SAME_ACCOUNT = 421;
    public static final int YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT = 422;
    public static final int THE_SCROLL_OF_ENCHANT_HAS_BEEN_CANCELED = 423;
    public static final int DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL = 424;
    public static final int YOUR_CREATE_ITEM_LEVEL_IS_TOO_LOW = 425;
    public static final int YOUR_ACCOUNT_HAS_BEEN_REPORTED_FOR_INTENTIONALLY_NOT_PAYING_THE_CYBER_CAFE_FEES = 426;
    public static final int PLEASE_CONTACT_US = 427;
    public static final int IN_ACCORDANCE_WITH_COMPANY_POLICY_YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_DUE_TO_SUSPICION_OF_ILLEGAL = 428;
    public static final int IN_ACCORDANCE_WITH_COMPANY_POLICY_YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_DUE_TO_FALSELY_REPORTING_A = 429;
    public static final int __DOESNT_NEED_TO_TRANSLATE = 430;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_DUE_TO_VIOLATING_THE_EULA_ROC_AND_OR_USER_AGREEMENT_CHAPTER_4 = 431;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_7_DAYS_RETROACTIVE_TO_THE_DAY_OF_DISCLOSURE_UNDER_CHAPTER_3 = 432;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE = 433;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_1 = 434;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_2 = 435;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_3 = 436;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_4 = 437;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_3_SECTION_14_OF_THE_LINEAGE_II_SERVICE_USE_5 = 438;
    public static final int IN_ACCORDANCE_WITH_THE_COMPANYS_USER_AGREEMENT_AND_OPERATIONAL_POLICY_THIS_ACCOUNT_HAS_BEEN = 439;
    public static final int BECAUSE_YOU_ARE_REGISTERED_AS_A_MINOR_YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_AT_THE_REQUEST_OF_YOUR = 440;
    public static final int PER_OUR_COMPANYS_USER_AGREEMENT_THE_USE_OF_THIS_ACCOUNT_HAS_BEEN_SUSPENDED_IF_YOU_HAVE_ANY = 441;
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_UNDER_CHAPTER_2_SECTION_7_OF_THE_LINEAGE_II_SERVICE_USE = 442;
    public static final int THE_IDENTITY_OF_THIS_ACCOUNT_HAS_NOT_BEEN_VEEN_VERIFIED_THEREFORE_LINEAGE_II_SERVICE_FOR_THIS = 443;
    public static final int SINCE_WE_HAVE_RECEIVED_A_WITHDRAWAL_REQUEST_FROM_THE_HOLDER_OF_THIS_ACCOUNT_ACCESS_TO_ALL = 444;
    public static final int REFERENCE_NUMBER_REGARDING_MEMBERSHIP_WITHDRAWAL_REQUEST__S1 = 445;
    public static final int FOR_MORE_INFORMATION_PLEASE_VISIT_THE_SUPPORT_CENTER_ON_THE_PLAYNC_WEBSITE_HTTP___WWWPLAYNCCOM = 446;
    public static final int SYSMSG_ID447 = 447; // .
    public static final int SYSTEM_ERROR_PLEASE_LOG_IN_AGAIN_LATER = 448;
    public static final int PASSWORD_DOES_NOT_MATCH_THIS_ACCOUNT = 449;
    public static final int CONFIRM_YOUR_ACCOUNT_INFORMATION_AND_LOG_IN_AGAIN_LATER = 450;
    public static final int THE_PASSWORD_YOU_HAVE_ENTERED_IS_INCORRECT = 451;
    public static final int PLEASE_CONFIRM_YOUR_ACCOUNT_INFORMATION_AND_TRY_LOGGING_IN_AGAIN = 452;
    public static final int YOUR_ACCOUNT_INFORMATION_IS_INCORRECT = 453;
    public static final int FOR_MORE_DETAILS_PLEASE_CONTACT_OUR_CUSTOMER_SERVICE_CENTER_AT_HTTP__SUPPORTPLAYNCCOM = 454;
    public static final int THE_ACCOUNT_IS_ALREADY_IN_USE_ACCESS_DENIED = 455;
    public static final int LINEAGE_II_GAME_SERVICES_MAY_BE_USED_BY_INDIVIDUALS_15_YEARS_OF_AGE_OR_OLDER_EXCEPT_FOR_PVP_SERVERS = 456;
    public static final int SERVER_UNDER_MAINTENANCE_PLEASE_TRY_AGAIN_LATER = 457;
    public static final int YOUR_USAGE_TERM_HAS_EXPIRED = 458;
    public static final int PLEASE_VISIT_THE_OFFICIAL_LINEAGE_II_WEBSITE_AT_HTTP__WWWLINEAGE2COM = 459;
    public static final int TO_REACTIVATE_YOUR_ACCOUNT = 460;
    public static final int ACCESS_FAILED = 461;
    @Deprecated
    public static final int PLEASE_TRY_AGAIN_LATER_1 = 462;
    public static final int SYSMSG_ID463 = 463; // .
    public static final int FEATURE_AVAILABLE_TO_ALLIANCE_LEADERS_ONLY = 464;
    public static final int YOU_ARE_NOT_CURRENTLY_ALLIED_WITH_ANY_CLANS = 465;
    public static final int YOU_HAVE_EXCEEDED_THE_LIMIT = 466;
    public static final int YOU_MAY_NOT_ACCEPT_ANY_CLAN_WITHIN_A_DAY_AFTER_EXPELLING_ANOTHER_CLAN = 467;
    public static final int A_CLAN_THAT_HAS_WITHDRAWN_OR_BEEN_EXPELLED_CANNOT_ENTER_INTO_AN_ALLIANCE_WITHIN_ONE_DAY_OF_WITHDRAWAL_OR_EXPULSION = 468;
    public static final int YOU_MAY_NOT_ALLY_WITH_A_CLAN_YOU_ARE_AT_BATTLE_WITH = 469;
    public static final int ONLY_THE_CLAN_LEADER_MAY_APPLY_FOR_WITHDRAWAL_FROM_THE_ALLIANCE = 470;
    public static final int ALLIANCE_LEADERS_CANNOT_WITHDRAW = 471;
    public static final int YOU_CANNOT_EXPEL_YOURSELF_FROM_THE_CLAN = 472;
    public static final int DIFFERENT_ALLIANCE = 473;
    public static final int THE_FOLLOWING_CLAN_DOES_NOT_EXIST = 474;
    public static final int DIFFERENT_ALLIANCE_1 = 475;
    public static final int INCORRECT_IMAGE_SIZE_PLEASE_ADJUST_TO_8X12 = 476;
    public static final int NO_RESPONSE_INVITATION_TO_JOIN_AN_ALLIANCE_HAS_BEEN_CANCELLED = 477; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int NO_RESPONSE_YOUR_ENTRANCE_TO_THE_ALLIANCE_HAS_BEEN_CANCELLED = 478; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int S1_HAS_JOINED_AS_A_FRIEND = 479; // $s1 Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â² Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int PLEASE_CHECK_YOUR_FRIENDS_LIST = 480; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int S1__HAS_BEEN_DELETED_FROM_YOUR_FRIENDS_LIST = 481; // $s1
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    @Deprecated
    public static final int YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIEND_LIST_1 = 482; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â±Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int FRIEND_LIST_IS_NOT_READY_YET_PLEASE_TRY_AGAIN_LATER = 483; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int ALREADY_REGISTERED_ON_THE_FRIENDS_LIST = 484; // Ã�Â£Ã�Â¶Ã�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int NO_NEW_FRIEND_INVITATIONS_FROM_OTHER_USERS = 485; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int THE_FOLLOWING_USER_IS_NOT_IN_YOUR_FRIENDS_LIST = 486; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â¸Ã‘â€¦
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int _FRIENDS_LIST_ = 487; // ======<FRIENDS_LIST>======
    public static final int S1_CURRENTLY_ONLINE = 488; // $s1 (Ã�â€™ Ã‘ï¿½Ã�ÂµÃ‘â€šÃ�Â¸)
    public static final int S1_CURRENTLY_OFFLINE = 489; // $s1 (Ã�ï¿½Ã�Âµ Ã�Â² Ã‘ï¿½Ã�ÂµÃ‘â€šÃ�Â¸)
    public static final int __EQUALS__ = 490; // ========================
    public static final int _ALLIANCE_INFORMATION_ = 491; // =======<ALLIANCE_INFORMATION>=======
    public static final int ALLIANCE_NAME_S1 = 492; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â° : $s1
    public static final int CONNECTION_S1_TOTAL_S2 = 493; // Ã�â€™ Ã‘ï¿½Ã�ÂµÃ‘â€šÃ�Â¸: $s1/Ã�â€™Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â¾:
    // $s2
    public static final int ALLIANCE_LEADER_S2_OF_S1 = 494; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â° Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°:
    // $s2, Ã�ÂºÃ�Â»Ã�Â°Ã�Â½ $s1
    public static final int AFFILIATED_CLANS_TOTAL_S1_CLAN_S = 495; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â² Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Âµ:
    // $s1
    public static final int _CLAN_INFORMATION_ = 496; // =====<CLAN_INFORMATION>=====
    public static final int CLAN_NAME_S1 = 497; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°: $s1
    public static final int CLAN_LEADER_S1 = 498; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â° Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°: $s1
    public static final int CLAN_LEVEL_S1 = 499; // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°: $s1
    public static final int __DASHES__ = 500; // ------------------------
    public static final int SYSMSG_ID501 = 501; // ========================
    public static final int YOU_ALREADY_BELONG_TO_ANOTHER_ALLIANCE = 502; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Âµ.
    public static final int S1_FRIEND_HAS_LOGGED_IN = 503; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³ $s1 Ã�Â²Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int ONLY_CLAN_LEADERS_MAY_CREATE_ALLIANCES = 504; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã‘â€¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½.
    public static final int YOU_CANNOT_CREATE_A_NEW_ALLIANCE_WITHIN_1_DAY_AFTER_DISSOLUTION = 505; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 24
    // Ã‘â€¡
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾.
    public static final int INCORRECT_ALLIANCE_NAME = 506; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int INCORRECT_LENGTH_FOR_AN_ALLIANCE_NAME = 507; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int THIS_ALLIANCE_NAME_ALREADY_EXISTS = 508; // Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½ Ã‘ï¿½
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸Ã�Â¼
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š.
    public static final int CANNOT_ACCEPT_CLAN_ALLY_IS_REGISTERED_AS_AN_ENEMY_DURING_SIEGE_BATTLE = 509; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int YOU_HAVE_INVITED_SOMEONE_TO_YOUR_ALLIANCE = 510; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½.
    public static final int SELECT_USER_TO_INVITE = 511; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ, Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int DO_YOU_REALLY_WISH_TO_WITHDRAW_FROM_THE_ALLIANCE = 512; // Ã�â€™Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°?
    // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 24
    // Ã‘â€¡.
    public static final int ENTER_THE_NAME_OF_THE_CLAN_YOU_WISH_TO_EXPEL = 513; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int DO_YOU_REALLY_WISH_TO_DISSOLVE_THE_ALLIANCE = 514; // Ã�Â Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½?
    // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 24
    // Ã‘â€¡.
    public static final int ENTER_FILE_NAME_FOR_THE_ALLIANCE_CREST = 515; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int S1_HAS_INVITED_YOU_AS_A_FRIEND = 516; // $s1 Ã‘â€¦Ã�Â¾Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã‘Å’Ã‘ï¿½.
    public static final int YOU_HAVE_ACCEPTED_THE_ALLIANCE = 517; // Ã�â€™Ã‘â€¹ Ã�Â²Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¸ Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int YOU_HAVE_FAILED_TO_INVITE_A_CLAN_INTO_THE_ALLIANCE = 518; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½.
    public static final int YOU_HAVE_WITHDRAWN_FROM_THE_ALLIANCE = 519; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int YOU_HAVE_FAILED_TO_WITHDRAW_FROM_THE_ALLIANCE = 520; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int YOU_HAVE_SUCCEEDED_IN_EXPELLING_A_CLAN = 521; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int YOU_HAVE_FAILED_TO_EXPEL_A_CLAN = 522; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½ Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int THE_ALLIANCE_HAS_BEEN_DISSOLVED = 523; // Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½.
    public static final int YOU_HAVE_FAILED_TO_DISSOLVE_THE_ALLIANCE = 524; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½.
    public static final int YOU_HAVE_SUCCEEDED_IN_INVITING_A_FRIEND = 525; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â°.
    public static final int YOU_HAVE_FAILED_TO_INVITE_A_FRIEND = 526;
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â°.
    public static final int S2_THE_LEADER_OF_S1_HAS_REQUESTED_AN_ALLIANCE = 527; // $s2,
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã�Â³Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½.
    public static final int FILE_NOT_FOUND = 528; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾ Ã�Â½Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸ Ã‘â€žÃ�Â°Ã�Â¹Ã�Â».
    public static final int YOU_MAY_ONLY_REGISTER_8X12_BMP_FILES_WITH_256_COLORS = 529; // Ã�Â¤Ã�Â°Ã�Â¹Ã�Â»:
    // Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€š
    // .bmp,
    // 256
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²,
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘â‚¬
    // 8*12.
    public static final int SPIRITSHOT_DOES_NOT_MATCH_WEAPON_GRADE = 530; // Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´
    // Ã�â€�Ã‘Æ’Ã‘â€¦Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â³Ã‘Æ’
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã‘Å½.
    public static final int NOT_ENOUGH_SPIRITSHOTS = 531; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â¾Ã�Â² Ã�â€�Ã‘Æ’Ã‘â€¦Ã�Â°.
    public static final int CANNOT_USE_SPIRITSHOTS = 532; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½ Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´ Ã�â€�Ã‘Æ’Ã‘â€¦Ã�Â°.
    public static final int POWER_OF_MANA_ENABLED = 533; // Ã�Å“Ã�Â°Ã�Â³Ã�Â¸Ã‘ï¿½ Ã�Â½Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã‘ï¿½Ã�ÂµÃ‘â€š Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ.
    public static final int POWER_OF_MANA_DISABLED = 534; // Ã�â€�Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â°
    // Ã�â€�Ã‘Æ’Ã‘â€¦Ã�Â° Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int NAME_PET = 535; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¸Ã�Â¼Ã‘ï¿½ Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int HOW_MUCH_ADENA_DO_YOU_WISH_TO_TRANSFER_TO_YOUR_INVENTORY = 536; // Ã�Â¡Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’?
    public static final int HOW_MUCH_WILL_YOU_TRANSFER = 537; // Ã�Â¡Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’?
    public static final int SP_HAS_DECREASED_BY_S1 = 538; // SP Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã‘â€¹ Ã�Â½Ã�Â° $s1.
    public static final int EXPERIENCE_HAS_DECREASED_BY_S1 = 539; // Ã�Å¾Ã�Â¿Ã‘â€¹Ã‘â€š Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â° $s1.
    public static final int CLAN_LEADERS_CANNOT_BE_DELETED_DISSOLVE_THE_CLAN_AND_TRY_AGAIN = 540; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    // Ã�Â Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.
    public static final int YOU_CANNOT_DELETE_A_CLAN_MEMBER_WITHDRAW_FROM_THE_CLAN_AND_TRY_AGAIN = 541; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    // Ã�â€™Ã‘â€¹Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.
    public static final int NPC_SERVER_NOT_OPERATING_PETS_CANNOT_BE_SUMMONED = 542; // Ã�Â¡Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // NPC
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½.
    // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int YOU_ALREADY_HAVE_A_PET = 543; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½ Ã‘Æ’Ã�Â¶Ã�Âµ Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ .
    public static final int ITEM_NOT_AVAILABLE_FOR_PETS = 544; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int DUE_TO_THE_VOLUME_LIMIT_OF_THE_PETS_INVENTORY_NO_MORE_ITEMS_CAN_BE_PLACED_THERE = 545; // Ã�ËœÃ�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    // Ã�Â£Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€¡Ã‘â€šÃ�Â¾-Ã�Â½Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã‘Å’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int EXCEEDED_PET_INVENTORYS_WEIGHT_LIMIT = 546; // Ã�ËœÃ�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    public static final int SUMMON_A_PET = 547; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â² Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°Ã¢â‚¬Â¦
    public static final int YOUR_PETS_NAME_CAN_BE_UP_TO_8_CHARACTERS = 548; // Ã�Å“Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // -
    // 8
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int TO_CREATE_AN_ALLIANCE_YOUR_CLAN_MUST_BE_LEVEL_5_OR_HIGHER = 549; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // 5-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½.
    public static final int YOU_CANNOT_CREATE_AN_ALLIANCE_DURING_THE_TERM_OF_DISSOLUTION_POSTPONEMENT = 550; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã‘ï¿½Ã‘â‚¬Ã�Â°Ã�Â·Ã‘Æ’
    // Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾.
    public static final int YOU_CANNOT_RAISE_YOUR_CLAN_LEVEL_DURING_THE_TERM_OF_DISPERSION_POSTPONEMENT = 551; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Âµ.
    public static final int DURING_THE_GRACE_PERIOD_FOR_DISSOLVING_A_CLAN_REGISTRATION_OR_DELETION_OF_A_CLANS_CREST_IS_NOT_ALLOWED = 552; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’/Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int THE_OPPOSING_CLAN_HAS_APPLIED_FOR_DISPERSION = 553; // Ã�â€™Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°.
    public static final int YOU_CANNOT_DISPERSE_THE_CLANS_IN_YOUR_ALLIANCE = 554; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½,
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Âµ.
    public static final int YOU_CANNOT_MOVE_YOUR_ITEM_WEIGHT_IS_TOO_GREAT = 555; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â²Ã�Â¸Ã�Â³Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int YOU_CANNOT_MOVE_IN_THIS_STATE = 556;
    public static final int THE_PET_HAS_BEEN_SUMMONED_AND_CANNOT_BE_DELETED = 557; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½.
    public static final int THE_PET_HAS_BEEN_SUMMONED_AND_CANNOT_BE_LET_GO = 558; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½.
    public static final int PURCHASED_S2_FROM_S1 = 559; // Ã�â€™Ã‘â€¹ Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»Ã�Â¸ Ã‘Æ’ Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1: $s2.
    public static final int PURCHASED_S2_S3_FROM_S1 = 560; // Ã�â€™Ã‘â€¹ Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»Ã�Â¸ Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1:
    // +$s2 $s3.
    public static final int PURCHASED_S3_S2_S_FROM_S1_ = 561; // Ã�â€™Ã‘â€¹ Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»Ã�Â¸ Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1:
    // $s2 ($s3 Ã‘Ë†Ã‘â€š.)
    public static final int CANNOT_CRYSTALLIZE_CRYSTALLIZATION_SKILL_LEVEL_TOO_LOW = 562; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â±Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã‘â€¹
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â½Ã�Â¸Ã�Â·Ã�Â¾Ã�Âº.
    public static final int FAILED_TO_DISABLE_ATTACK_TARGET = 563; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸.
    public static final int FAILED_TO_CHANGE_ATTACK_TARGET = 564; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’ Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸.
    public static final int NOT_ENOUGH_LUCK = 565; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â½Ã�Âµ Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š Ã‘Æ’Ã�Â´Ã�Â°Ã‘â€¡Ã�Â¸.
    public static final int CONFUSION_FAILED = 566; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã�Â¸Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾.
    public static final int FEAR_FAILED = 567; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã�Â¸Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€¦Ã�Â° Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾.
    public static final int CUBIC_SUMMONING_FAILED = 568; // Ã�ï¿½Ã�Âµ Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’ Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Å¡Ã‘Æ’Ã�Â±.
    public static final int CAUTION_THE_ITEM_PRICE_GREATLY_DIFFERS_FROM_THE_SHOPS_STANDARD_PRICE_DO_YOU_WISH_TO_CONTINUE = 569; // Ã�â€™Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // -
    // Ã‘â€ Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â°Ã�Â·Ã�Â¸Ã�Â½Ã�Âµ.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int HOW_MANY__S1__S_DO_YOU_WISH_TO_PURCHASE = 570; // $s1:
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int HOW_MANY__S1__S_DO_YOU_WANT_TO_PURCHASE = 571; // $s1:
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¾Ã�Âº?
    public static final int DO_YOU_WISH_TO_JOIN_S1S_PARTY_ITEM_DISTRIBUTION_FINDERS_KEEPERS = 572; // Ã�â€™Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1?
    // (Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�ï¿½Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â´Ã‘Ë†Ã�ÂµÃ�Â¼Ã‘Æ’)
    public static final int DO_YOU_WISH_TO_JOIN_S1S_PARTY_ITEM_DISTRIBUTION_RANDOM = 573; // Ã�â€™Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1?
    // (Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Â¹Ã�Â½Ã�Â¾)
    public static final int PETS_AND_SERVITORS_ARE_NOT_AVAILABLE_AT_THIS_TIME = 574; // Ã�â€™
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¸
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    public static final int HOW_MUCH_ADENA_DO_YOU_WISH_TO_TRANSFER_TO_YOUR_PET = 575; // Ã�Â¡Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã‘Æ’?
    public static final int HOW_MUCH_DO_YOU_WISH_TO_TRANSFER = 576; // Ã�Â¡Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_CANNOT_SUMMON_DURING_A_TRADE_OR_WHILE_USING_THE_PRIVATE_SHOPS = 577; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã�Â¸.
    public static final int YOU_CANNOT_SUMMON_DURING_COMBAT = 578; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾ Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹.
    public static final int A_PET_CANNOT_BE_SENT_BACK_DURING_BATTLE = 579; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹.
    public static final int YOU_MAY_NOT_USE_MULTIPLE_PETS_OR_SERVITORS_AT_THE_SAME_TIME = 580; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â·Ã�Â°
    // Ã‘â‚¬Ã�Â°Ã�Â·.
    public static final int THERE_IS_A_SPACE_IN_THE_NAME = 581; // Ã�â€™ Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸ Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã�ÂµÃ�Â».
    public static final int INAPPROPRIATE_CHARACTER_NAME = 582; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int NAME_INCLUDES_FORBIDDEN_WORDS = 583; // Ã�â€™ Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¾.
    public static final int ALREADY_IN_USE_BY_ANOTHER_PET = 584; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã‘ï¿½
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸Ã�Â¼ Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â¼
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š.
    public static final int PLEASE_DECIDE_ON_THE_PRICE = 585; // Ã�Â£Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ�Âµ Ã‘â€ Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â¸.
    public static final int PET_ITEMS_CANNOT_BE_REGISTERED_AS_SHORTCUTS = 586; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â¹Ã�ÂºÃ�Â¸
    // Ã�Â±Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â°.
    public static final int IRREGULAR_SYSTEM_SPEED = 587; // Ã�Â¡Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’ Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¹
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã‘â€¹ Ã�Â½Ã�Â¸Ã�Â·Ã�ÂºÃ�Â°.
    public static final int PET_INVENTORY_IS_FULL = 588; // Ã�ËœÃ�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’ Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â½.
    public static final int A_DEAD_PET_CANNOT_BE_SENT_BACK = 589; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int CANNOT_GIVE_ITEMS_TO_A_DEAD_PET = 590; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã‘Æ’.
    public static final int AN_INVALID_CHARACTER_IS_INCLUDED_IN_THE_PETS_NAME = 591; // Ã�ËœÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â».
    public static final int DO_YOU_WISH_TO_DISMISS_YOUR_PET_DISMISSING_YOUR_PET_WILL_CAUSE_THE_PET_NECKLACE_TO_DISAPPEAR = 592; // Ã�Â£Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°?
    // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¿Ã�Â°Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â¹Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int YOUR_PET_HAS_LEFT_DUE_TO_UNBEARABLE_HUNGER = 593; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã‘ï¿½Ã�Â±Ã�ÂµÃ�Â¶Ã�Â°Ã�Â»,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â¾Ã‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�ÂµÃ�Â´Ã‘â€¹.
    public static final int YOU_CANNOT_RESTORE_HUNGRY_PETS = 594; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int YOUR_PET_IS_VERY_HUNGRY = 595; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¾Ã‘â€¡Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â´Ã�ÂµÃ�Â½.
    public static final int YOUR_PET_ATE_A_LITTLE_BUT_IS_STILL_HUNGRY = 596; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�ÂµÃ�Â»,
    // Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â½Ã�Â¾
    // Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â´Ã�ÂµÃ�Â½.
    public static final int YOUR_PET_IS_VERY_HUNGRY_PLEASE_BE_CAREFUL = 597; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¾Ã‘â€¡Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â´Ã�ÂµÃ�Â½,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â½Ã‘â€¹.
    public static final int YOU_CANNOT_CHAT_WHILE_YOU_ARE_INVISIBLE = 598; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¸Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int THE_GM_HAS_AN_IMPORTANT_NOTICE_CHAT_IS_TEMPORARILY_ABORTED = 599; // Ã�â€™Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ!
    // Ã�â€™Ã�Â°Ã�Â¶Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸!
    // Ã�ï¿½Ã�ÂµÃ�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â€¡Ã�Â°Ã‘â€š
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_EQUIP_A_PET_ITEM = 600; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½ Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int THERE_ARE_S1_PETITIONS_PENDING = 601; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â¼Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹:
    // $S1.
    public static final int THE_PETITION_SYSTEM_IS_CURRENTLY_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER = 602; // Ã�Â¡Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�ÂµÃ‘Å½
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int THAT_ITEM_CANNOT_BE_DISCARDED_OR_EXCHANGED = 603; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int YOU_MAY_NOT_CALL_FORTH_A_PET_OR_SUMMONED_CREATURE_FROM_THIS_LOCATION = 604; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ.
    public static final int YOU_MAY_REGISTER_UP_TO_64_PEOPLE_ON_YOUR_LIST = 605; // Ã�â€™
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â´Ã�Â¾
    // 64
    // Ã‘â€¡Ã�ÂµÃ�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Âº.
    public static final int YOU_CANNOT_BE_REGISTERED_BECAUSE_THE_OTHER_PERSON_HAS_ALREADY_REGISTERED_64_PEOPLE_ON_HIS_HER_LIST = 606; // Ã�Â¡Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    public static final int YOU_DO_NOT_HAVE_ANY_FURTHER_SKILLS_TO_LEARN__COME_BACK_WHEN_YOU_HAVE_REACHED_LEVEL_S1 = 607; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â·Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â½Ã�ÂµÃ‘â€šÃ�Âµ
    // $s1-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int S1_HAS_OBTAINED_3_S2_S_BY_USING_SWEEPER = 608; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š:
    // $s2
    // ($s3
    // Ã‘Ë†Ã‘â€š.)
    // Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‚Â«Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã‘â€šÃ‘Å’Ã‚Â».
    public static final int S1_HAS_OBTAINED_S2_BY_USING_SWEEPER = 609; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š:
    // $s2 Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‚Â«Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã‘â€šÃ‘Å’Ã‚Â».
    public static final int YOUR_SKILL_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_HP = 610; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // HP.
    // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int YOU_HAVE_SUCCEEDED_IN_CONFUSING_THE_ENEMY = 611; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ‘Ë†Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾.
    public static final int THE_SPOIL_CONDITION_HAS_BEEN_ACTIVATED = 612; // Ã�ï¿½Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‚Â«SpoilÃ‚Â».
    public static final int _IGNORE_LIST_ = 613; // ======<IGNORE_LIST>======
    public static final int C1___C2 = 614; // $c1 : $c2
    public static final int S1 = 1983; // $s1
    public static final int YOU_HAVE_FAILED_TO_REGISTER_THE_USER_TO_YOUR_IGNORE_LIST = 615; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã�Â½Ã�Â¾Ã‘â‚¬-Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š.
    public static final int YOU_HAVE_FAILED_TO_DELETE_THE_CHARACTER_FROM_IGNORE_LIST = 616; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã�Â½Ã�Â¾Ã‘â‚¬-Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°.
    public static final int S1_HAS_BEEN_ADDED_TO_YOUR_IGNORE_LIST = 617; // $s1
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã�Â½Ã�Â¾Ã‘â‚¬-Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š.
    public static final int S1_HAS_BEEN_REMOVED_FROM_YOUR_IGNORE_LIST = 618; // $s1
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã�Â½Ã�Â¾Ã‘â‚¬-Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°.
    public static final int S1__HAS_PLACED_YOU_ON_HIS_HER_IGNORE_LIST = 619; // $s1
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã�Â½Ã�Â¾Ã‘â‚¬-Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š.
    public static final int S1__HAS_PLACED_YOU_ON_HIS_HER_IGNORE_LIST_1 = 620; // $s1
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã�Â½Ã�Â¾Ã‘â‚¬-Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š.
    public static final int THIS_SERVER_IS_RESERVED_FOR_PLAYERS_IN_KOREA__TO_USE_LINEAGE_II_GAME_SERVICES_PLEASE_CONNECT_TO_THE_SERVER_IN_YOUR_REGION = 621; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // IP.
    public static final int YOU_MAY_NOT_MAKE_A_DECLARATION_OF_WAR_DURING_AN_ALLIANCE_BATTLE = 622; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã�Â±
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â¼Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°Ã�Â¼Ã�Â¸.
    public static final int YOUR_OPPONENT_HAS_EXCEEDED_THE_NUMBER_OF_SIMULTANEOUS_ALLIANCE_BATTLES_ALLOWED = 623; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â²Ã�Â¾Ã‘Å½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â´Ã�Â¾Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    public static final int S1_CLAN_LEADER_IS_NOT_CURRENTLY_CONNECTED_TO_THE_GAME_SERVER = 624; // Ã�â€ºÃ�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int YOUR_REQUEST_FOR_ALLIANCE_BATTLE_TRUCE_HAS_BEEN_DENIED = 625; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·
    // Ã�Â¾Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â¼Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°Ã�Â¼Ã�Â¸.
    public static final int THE_S1_CLAN_DID_NOT_RESPOND__WAR_PROCLAMATION_HAS_BEEN_REFUSED = 626; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â¸Ã�Â»
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    public static final int CLAN_BATTLE_HAS_BEEN_REFUSED_BECAUSE_YOU_DID_NOT_RESPOND_TO_S1_CLANS_WAR_PROCLAMATION = 627; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’
    // $s1
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã�Â±
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    public static final int YOU_HAVE_ALREADY_BEEN_AT_WAR_WITH_THE_S1_CLAN_5_DAYS_MUST_PASS_BEFORE_YOU_CAN_DECLARE_WAR_AGAIN = 628; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1.
    // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â¹.
    public static final int YOUR_OPPONENT_HAS_EXCEEDED_THE_NUMBER_OF_SIMULTANEOUS_ALLIANCE_BATTLES_ALLOWED_1 = 629; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã‘ï¿½
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    public static final int WAR_WITH_THE_S1_CLAN_HAS_BEGUN_1 = 630; // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â° Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int WAR_WITH_THE_S1_CLAN_IS_OVER = 631; // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â° Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼ $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN_1 = 632; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1.
    public static final int YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN_1 = 633; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1.
    public static final int YOUR_ALLIANCE_LEADER_HAS_BEEN_SLAIN_YOU_HAVE_BEEN_DEFEATED_BY_THE_S1_CLAN = 634; // Ã�â€ºÃ�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â¸Ã�Â±.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’
    // $s1.
    public static final int THE_TIME_LIMIT_FOR_THE_CLAN_WAR_HAS_BEEN_EXCEEDED_WAR_WITH_THE_S1_CLAN_IS_OVER = 635; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�ÂºÃ�Â»Ã�Â¾.
    // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â°
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_ARE_NOT_INVOLVED_IN_A_CLAN_WAR_1 = 636;
    // Ã�Â²Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    public static final int A_CLAN_ALLY_HAS_REGISTERED_ITSELF_TO_THE_OPPONENT = 637; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½,
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°,
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â¸Ã�Â¼
    // Ã�Â¾Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã‘â€šÃ�Â¾Ã�Â¼.
    public static final int YOU_HAVE_ALREADY_REQUESTED_A_SIEGE_BATTLE = 638; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int YOUR_APPLICATION_HAS_BEEN_DENIED_BECAUSE_YOU_HAVE_ALREADY_SUBMITTED_A_REQUEST_FOR_ANOTHER_SIEGE_BATTLE = 639; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’.
    public static final int YOU_HAVE_FAILED_TO_REFUSE_CASTLE_DEFENSE_AID = 640; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ‘â€¹
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int YOU_HAVE_FAILED_TO_APPROVE_CASTLE_DEFENSE_AID = 641; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int YOU_ARE_ALREADY_REGISTERED_TO_THE_ATTACKER_SIDE_AND_MUST_CANCEL_YOUR_REGISTRATION_BEFORE_SUBMITTING_YOUR_REQUEST = 642; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    // Ã�ï¿½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Âµ
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int YOU_HAVE_ALREADY_REGISTERED_TO_THE_DEFENDER_SIDE_AND_MUST_CANCEL_YOUR_REGISTRATION_BEFORE_SUBMITTING_YOUR_REQUEST = 643; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    // Ã�ï¿½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Âµ
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int YOU_ARE_NOT_YET_REGISTERED_FOR_THE_CASTLE_SIEGE = 644; // Ã�â€™Ã‘â€¹
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int ONLY_CLANS_WITH_LEVEL_4_AND_HIGHER_MAY_REGISTER_FOR_A_CASTLE_SIEGE = 645; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘â€¹,
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã‘Ë†Ã�Â¸Ã�Âµ
    // 5-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_MODIFY_THE_CASTLE_DEFENDER_LIST = 646; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã�Â²Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_MODIFY_THE_SIEGE_TIME = 647; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’.
    public static final int NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_ATTACKER_SIDE = 648; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_DEFENDER_SIDE = 649; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int YOU_MAY_NOT_SUMMON_FROM_YOUR_CURRENT_LOCATION = 650; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ.
    public static final int PLACE_S1_IN_THE_CURRENT_LOCATION_AND_DIRECTION_DO_YOU_WISH_TO_CONTINUE = 651; // Ã�Å¸Ã�Â¾Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s1
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ?
    public static final int THE_TARGET_OF_THE_SUMMONED_MONSTER_IS_WRONG = 652; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°.
    public static final int YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_POSITION_MERCENARIES = 653; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_CANCEL_MERCENARY_POSITIONING = 654; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int MERCENARIES_CANNOT_BE_POSITIONED_HERE = 655; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ.
    public static final int THIS_MERCENARY_CANNOT_BE_POSITIONED_ANYMORE = 656; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°.
    public static final int POSITIONING_CANNOT_BE_DONE_HERE_BECAUSE_THE_DISTANCE_BETWEEN_MERCENARIES_IS_TOO_SHORT = 657; // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¼Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã�Â¼Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�Â°Ã�Â»Ã�Â¾.
    public static final int THIS_IS_NOT_A_MERCENARY_OF_A_CASTLE_THAT_YOU_OWN_AND_SO_YOU_CANNOT_CANCEL_ITS_POSITIONING = 658; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â°Ã�Â´Ã�Â»Ã�ÂµÃ�Â¶Ã�Â¸Ã‘â€š
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int THIS_IS_NOT_THE_TIME_FOR_SIEGE_REGISTRATION_AND_SO_REGISTRATIONS_CANNOT_BE_ACCEPTED_OR_REJECTED = 659; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¾Ã�Â´Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int THIS_IS_NOT_THE_TIME_FOR_SIEGE_REGISTRATION_AND_SO_REGISTRATION_AND_CANCELLATION_CANNOT_BE_DONE = 660; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int IT_IS_A_CHARACTER_THAT_CANNOT_BE_SPOILED = 661; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€ Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int THE_OTHER_PLAYER_IS_REJECTING_FRIEND_INVITATIONS = 662; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â¸Ã�Â¼
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼.
    public static final int THE_SIEGE_TIME_HAS_BEEN_DECLARED_FOR_S_IT_IS_NOT_POSSIBLE_TO_CHANGE_THE_TIME_AFTER_A_SIEGE_TIME_HAS_BEEN_DECLARED_DO_YOU_WANT_TO_CONTINUE = 663; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹:
    // $s2.
    // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�ÂºÃ�Â°Ã�ÂºÃ�Â¸Ã�Âµ-Ã�Â»Ã�Â¸Ã�Â±Ã�Â¾
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int PLEASE_CHOOSE_A_PERSON_TO_RECEIVE = 664; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½.
    public static final int S2_OF_S1_ALLIANCE_IS_APPLYING_FOR_ALLIANCE_WAR_DO_YOU_WANT_TO_ACCEPT_THE_CHALLENGE = 665; // $s2,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // $s1,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã�Â³Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    // Ã�Â¡Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½?
    public static final int A_REQUEST_FOR_CEASEFIRE_HAS_BEEN_RECEIVED_FROM_S1_ALLIANCE_DO_YOU_AGREE = 666; // Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // $s1
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â½Ã‘â€¹?
    public static final int YOU_ARE_REGISTERING_ON_THE_ATTACKING_SIDE_OF_THE_S1_SIEGE_DO_YOU_WANT_TO_CONTINUE = 667; // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€š
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // $s1.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_ARE_REGISTERING_ON_THE_DEFENDING_SIDE_OF_THE_S1_SIEGE_DO_YOU_WANT_TO_CONTINUE = 668; // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€š
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // $s1.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_ARE_CANCELING_YOUR_APPLICATION_TO_PARTICIPATE_IN_THE_S1_SIEGE_BATTLE_DO_YOU_WANT_TO_CONTINUE = 669; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // $s1
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_ARE_REFUSING_THE_REGISTRATION_OF_S1_CLAN_ON_THE_DEFENDING_SIDE_DO_YOU_WANT_TO_CONTINUE = 670; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_ARE_AGREEING_TO_THE_REGISTRATION_OF_S1_CLAN_ON_THE_DEFENDING_SIDE_DO_YOU_WANT_TO_CONTINUE = 671; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â´Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int S1_ADENA_DISAPPEARED = 672; // Ã�ËœÃ‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â»Ã�Â¾: $s1 Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int YOU_ARE_MOVING_TO_ANOTHER_VILLAGE_DO_YOU_WANT_TO_CONTINUE = 682; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THERE_ARE_NO_PRIORITY_RIGHTS_ON_A_SWEEPER = 683; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int YOU_CANNOT_POSITION_MERCENARIES_DURING_A_SIEGE = 684; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_APPLY_FOR_CLAN_WAR_WITH_A_CLAN_THAT_BELONGS_TO_THE_SAME_ALLIANCE = 685; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’,
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â²
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½.
    public static final int YOU_HAVE_RECEIVED_S1_DAMAGE_FROM_THE_FIRE_OF_MAGIC = 686; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘â€š
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã�Â¸.
    public static final int YOU_CANNOT_MOVE_IN_A_FROZEN_STATE_PLEASE_WAIT_A_MOMENT = 687; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â²Ã�Â¸Ã�Â³Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�Â·Ã‘Ë†Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸.
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int THE_CLAN_THAT_OWNS_THE_CASTLE_IS_AUTOMATICALLY_REGISTERED_ON_THE_DEFENDING_SIDE = 688; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â¾Ã�Â¼,
    // Ã�Â°Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // Ã�Â·Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int A_CLAN_THAT_OWNS_A_CASTLE_CANNOT_PARTICIPATE_IN_ANOTHER_SIEGE = 689; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â¾Ã�Â¼,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã‘â€¦
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°Ã‘â€¦.
    public static final int YOU_CANNOT_REGISTER_ON_THE_ATTACKING_SIDE_BECAUSE_YOU_ARE_PART_OF_AN_ALLIANCE_WITH_THE_CLAN_THAT_OWNS_THE_CASTLE = 690; // Ã�Â¢Ã�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Âµ
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â¸Ã�Â¼
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’Ã‘Å½,
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â¶Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â¾Ã�Âº.
    public static final int S1_CLAN_IS_ALREADY_A_MEMBER_OF_S2_ALLIANCE = 691; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Âµ
    // $s2.
    public static final int THE_OTHER_PARTY_IS_FROZEN_PLEASE_WAIT_A_MOMENT = 692; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â½Ã�Â¸Ã�Âº
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int THE_PACKAGE_THAT_ARRIVED_IS_IN_ANOTHER_WAREHOUSE = 693; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ.
    public static final int NO_PACKAGES_HAVE_ARRIVED = 694; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�Â¾Ã�Âº Ã�Â½Ã�Âµ Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾.
    public static final int YOU_CANNOT_SET_THE_NAME_OF_THE_PET = 695;
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã‘Æ’.
    public static final int YOUR_ACCOUNT_IS_RESTRICTED_FOR_NOT_PAYING_YOUR_PC_ROOM_USAGE_FEES = 696; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°
    // Ã�Â½Ã�ÂµÃ‘Æ’Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘Æ’.
    public static final int THE_ITEM_ENCHANT_VALUE_IS_STRANGE = 697; // Ã�Â§Ã�Â¸Ã‘ï¿½Ã�Â»Ã�Â¾
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾.
    public static final int THE_PRICE_IS_DIFFERENT_THAN_THE_SAME_ITEM_ON_THE_SALES_LIST = 698; // Ã�Â¦Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶.
    public static final int CURRENTLY_NOT_PURCHASING = 699; // Ã�â€™ Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹ Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â° Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_PURCHASE_IS_COMPLETE = 700; // Ã�Å¸Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_REQUIRED_ITEMS = 701; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘â€¹Ã‘â€¦
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â².
    public static final int THERE_ARE_NOT_ANY_GMS_THAT_ARE_PROVIDING_CUSTOMER_SERVICE_CURRENTLY = 702; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â°.
    public static final int _GM_LIST_ = 703; // ======<GM_LIST>======
    public static final int GM_S1 = 704;
    public static final int YOU_CANNOT_EXCLUDE_YOURSELF = 705;
    public static final int YOU_CAN_ONLY_REGISTER_UP_TO_64_NAMES_ON_YOUR_EXCLUDE_LIST = 706;
    public static final int YOU_CANNOT_TELEPORT_TO_A_VILLAGE_THAT_IS_IN_A_SIEGE = 707;
    public static final int YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_THE_CASTLE_WAREHOUSE = 708; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_THE_CLAN_WAREHOUSE = 709; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int ONLY_CLANS_OF_CLAN_LEVEL_1_OR_HIGHER_CAN_USE_A_CLAN_WAREHOUSE = 710; // Ã�Â¥Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½,
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€š
    // 1.
    public static final int THE_SIEGE_OF_S1_HAS_STARTED = 711; // $s1: Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int THE_SIEGE_OF_S1_HAS_FINISHED = 712; // $s1: Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int S1_S2_S3_S4S5 = 713; // $s1/$s2/$s3 $s4:$s5
    public static final int A_TRAP_DEVICE_HAS_TRIPPED = 714; // Ã�â€ºÃ�Â¾Ã�Â²Ã‘Æ’Ã‘Ë†Ã�ÂºÃ�Â°
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int THE_TRAP_DEVICE_HAS_STOPPED = 715; // Ã�â€�Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã�Â»Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Ë†Ã�ÂºÃ�Â¸ Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int IF_A_BASE_CAMP_DOES_NOT_EXIST_RESURRECTION_IS_NOT_POSSIBLE = 716; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ‘ï¿½Ã‘Æ’Ã‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â°.
    public static final int THE_GUARDIAN_TOWER_HAS_BEEN_DESTROYED_AND_RESURRECTION_IS_NOT_POSSIBLE = 717; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â±Ã�Â°Ã‘Ë†Ã�Â½Ã‘ï¿½
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_CASTLE_GATES_CANNOT_BE_OPENED_AND_CLOSED_DURING_A_SIEGE = 718; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int YOU_FAILED_AT_ITEM_MIXING = 719; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘Æ’Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int THE_PURCHASE_PRICE_IS_HIGHER_THAN_THE_AMOUNT_OF_MONEY_THAT_YOU_HAVE_AND_SO_YOU_CANNOT_OPEN_A_PERSONAL_STORE = 720; // Ã�Â¦Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â°Ã�Â»,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã‘Å½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_CREATE_AN_ALLIANCE_WHILE_PARTICIPATING_IN_A_SIEGE = 721; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int YOU_CANNOT_DISSOLVE_AN_ALLIANCE_WHILE_AN_AFFILIATED_CLAN_IS_PARTICIPATING_IN_A_SIEGE_BATTLE = 722; // Ã�Å¾Ã�Â´Ã�Â¸Ã�Â½
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²,
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½,
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int THE_OPPOSING_CLAN_IS_PARTICIPATING_IN_A_SIEGE_BATTLE = 723; // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int YOU_CANNOT_LEAVE_WHILE_PARTICIPATING_IN_A_SIEGE_BATTLE = 724; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int YOU_CANNOT_BANISH_A_CLAN_FROM_AN_ALLIANCE_WHILE_THE_CLAN_IS_PARTICIPATING_IN_A_SIEGE = 725; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â½Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½,
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int THE_FROZEN_CONDITION_HAS_STARTED_PLEASE_WAIT_A_MOMENT = 726; // Ã�Â¡Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â·Ã�ÂºÃ�Â¸.
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int THE_FROZEN_CONDITION_WAS_REMOVED = 727; // Ã�â€”Ã�Â°Ã�Â¼Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int YOU_CANNOT_APPLY_FOR_DISSOLUTION_AGAIN_WITHIN_SEVEN_DAYS_AFTER_A_PREVIOUS_APPLICATION_FOR_DISSOLUTION = 728; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�Âº
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 7
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â¹.
    public static final int THAT_ITEM_CANNOT_BE_DISCARDED = 729; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int YOU_HAVE_SUBMITTED_S1_PETITIONS_YOU_MAY_SUBMIT_S2_MORE_PETITIONS_TODAY = 730; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹:
    // $s1.
    // \\n
    // -
    // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹:
    // $s2.
    public static final int A_PETITION_HAS_BEEN_RECEIVED_BY_THE_GM_ON_BEHALF_OF_S1_IT_IS_PETITION_S2 = 731; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // $c1.
    // Ã�Å¸Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘ï¿½
    // $s2.
    public static final int S1_HAS_RECEIVED_A_REQUEST_FOR_A_CONSULTATION_WITH_THE_GM = 732; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�ÂµÃ‘ï¿½Ã�ÂµÃ�Â´Ã�Âµ
    // $c1.
    public static final int WE_HAVE_RECEIVED_S1_PETITIONS_FROM_YOU_TODAY_AND_THAT_IS_THE_MAXIMUM_THAT_YOU_CAN_SUBMIT_IN_ONE_DAY_YOU_CANNOT_SUBMIT_ANY_MORE_PETITIONS = 733; // Ã�Â¡Ã�ÂµÃ�Â³Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½
    // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    // Ã�â€˜Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int YOU_FAILED_AT_SUBMITTING_A_PETITION_ON_BEHALF_OF_SOMEONE_ELSE_S1_ALREADY_SUBMITTED_A_PETITION = 734; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â·Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾-Ã‘â€šÃ�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾.
    // $c1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã�Â»
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘Å½.
    public static final int YOU_FAILED_AT_SUBMITTING_A_PETITION_ON_BEHALF_OF_S1_THE_ERROR_IS_S2 = 735; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°
    // $c1.
    // Ã�ï¿½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸:
    // $s2.
    public static final int THE_PETITION_WAS_CANCELED_YOU_MAY_SUBMIT_S1_MORE_PETITIONS_TODAY = 736; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // $s1
    // Ã‘â‚¬Ã�Â°Ã�Â·.
    public static final int YOU_FAILED_AT_SUBMITTING_A_PETITION_ON_BEHALF_OF_S1 = 737; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€¡Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°
    // $c1.
    public static final int YOU_HAVE_NOT_SUBMITTED_A_PETITION = 738;
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘Å½.
    public static final int YOU_FAILED_AT_CANCELING_A_PETITION_ON_BEHALF_OF_S1_THE_ERROR_CODE_IS_S2 = 739; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€¡Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°
    // $c1.
    // Ã�Å¡Ã�Â¾Ã�Â´
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸:
    // $s2.
    public static final int S1_PARTICIPATED_IN_A_PETITION_CHAT_AT_THE_REQUEST_OF_THE_GM = 740; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘Å’Ã�Â±Ã�Âµ
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int YOU_FAILED_AT_ADDING_S1_TO_THE_PETITION_CHAT_A_PETITION_HAS_ALREADY_BEEN_SUBMITTED = 741; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°
    // $c1
    // Ã�Â²
    // Ã‘â€¡Ã�Â°Ã‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    // Ã�Å¸Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â½Ã�Â°
    public static final int YOU_FAILED_AT_ADDING_S1_TO_THE_PETITION_CHAT_THE_ERROR_CODE_IS_S2 = 742; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°
    // $c1
    // Ã�Â²
    // Ã‘â€¡Ã�Â°Ã‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    // Ã�Å¡Ã�Â¾Ã�Â´
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸:
    // $s2.
    public static final int S1_LEFT_THE_PETITION_CHAT = 743; // $c1 Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â¸Ã�Â·
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â° Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    public static final int YOU_FAILED_AT_REMOVING_S1_FROM_THE_PETITION_CHAT_THE_ERROR_CODE_IS_S2 = 744; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°
    // $s1
    // Ã�Â¸Ã�Â·
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    // Ã�Å¡Ã�Â¾Ã�Â´
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸:
    // $s2.
    public static final int YOU_ARE_CURRENTLY_NOT_IN_A_PETITION_CHAT = 745; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    public static final int IT_IS_NOT_CURRENTLY_A_PETITION = 746; // Ã�Â­Ã‘â€šÃ�Â¾ Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘ï¿½.
    public static final int IF_YOU_NEED_HELP_PLEASE_USE_11_INQUIRY_ON_THE_OFFICIAL_WEB_SITE = 747; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’,
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€šÃ�Âµ.
    public static final int THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED = 748; // Ã�â€�Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã�ÂºÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã�Â¸Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int THE_EFFECT_OF_S1_HAS_BEEN_REMOVED = 749; // $s1:
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int THERE_ARE_NO_OTHER_SKILLS_TO_LEARN = 750; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
   
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â·Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int AS_THERE_IS_A_CONFLICT_IN_THE_SIEGE_RELATIONSHIP_WITH_A_CLAN_IN_THE_ALLIANCE_YOU_CANNOT_INVITE_THAT_CLAN_TO_THE_ALLIANCE = 751; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â¶Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ.
    // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int THAT_NAME_CANNOT_BE_USED = 752; // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Âµ Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int YOU_CANNOT_POSITION_MERCENARIES_HERE = 753; // Ã�Â£Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THERE_ARE_S1_HOURS_AND_S2_MINUTES_LEFT_IN_THIS_WEEKS_USAGE_TIME = 754; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s1
    // Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�Âµ.
    public static final int THERE_ARE_S1_MINUTES_LEFT_IN_THIS_WEEKS_USAGE_TIME = 755; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�Âµ.
    public static final int THIS_WEEKS_USAGE_TIME_HAS_FINISHED = 756; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Â° Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�Âµ
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int THERE_ARE_S1_HOURS_AND_S2_MINUTES_LEFT_IN_THE_FIXED_USE_TIME = 757; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THERE_ARE_S1_MINUTES_LEFT_IN_THIS_WEEKS_PLAY_TIME = 758; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // $s1
    // Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â´Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€ Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�Â¸.
    public static final int THERE_ARE_S1_MINUTES_LEFT_IN_THIS_WEEKS_PLAY_TIME_1 = 759; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â´Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€ Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�Â¸.
    public static final int S1_CANNOT_JOIN_THE_CLAN_BECAUSE_ONE_DAY_HAS_NOT_YET_PASSED_SINCE_HE_SHE_LEFT_ANOTHER_CLAN = 760; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // 24
    // Ã‘â€¡
    // Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â¸Ã�Â·
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾.
    public static final int S1_CLAN_CANNOT_JOIN_THE_ALLIANCE_BECAUSE_ONE_DAY_HAS_NOT_YET_PASSED_SINCE_IT_LEFT_ANOTHER_ALLIANCE = 761; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â¸Ã�Â·
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Âµ
    // 24
    // Ã‘â€¡
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â°Ã�Â´
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Â²Ã�Â½Ã�Â¾Ã�Â²Ã‘Å’.
    public static final int S1_ROLLED_S2_AND_S3S_EYE_CAME_OUT = 762; // $c1
    // Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â°Ã�ÂµÃ‘â€š
    // $s2.
    // Ã�â€™Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š:
    // $s3.
    public static final int YOU_FAILED_AT_SENDING_THE_PACKAGE_BECAUSE_YOU_ARE_TOO_FAR_FROM_THE_WAREHOUSE = 763; // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â¾
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Â°
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã�ÂºÃ�Â¾.
    // Ã�Å¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_HAVE_BEEN_PLAYING_FOR_AN_EXTENDED_PERIOD_OF_TIME_PLEASE_CONSIDER_TAKING_A_BREAK = 764; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â³Ã�Â¾.
    // Ã�Â¡Ã�Â¾Ã�Â²Ã�ÂµÃ‘â€šÃ‘Æ’Ã�ÂµÃ�Â¼
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â¾Ã‘â€šÃ�Â´Ã�Â¾Ã‘â€¦Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’.
    public static final int GAMEGUARD_IS_ALREADY_RUNNING_PLEASE_TRY_RUNNING_IT_AGAIN_AFTER_REBOOTING = 765; // Ã�ï¿½Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã�Â°
    // GameGuard.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int THERE_IS_A_GAMEGUARD_INITIALIZATION_ERROR_PLEASE_TRY_RUNNING_IT_AGAIN_AFTER_REBOOTING = 766; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘â€¹
    // GameGuard.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¿Ã‘Å’Ã‘Å½Ã‘â€šÃ�ÂµÃ‘â‚¬
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int THE_GAMEGUARD_FILE_IS_DAMAGED__PLEASE_REINSTALL_GAMEGUARD = 767; // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â´Ã�ÂµÃ�Â½
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘â€¹
    // GameGuard.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ‘â€¹.
    public static final int A_WINDOWS_SYSTEM_FILE_IS_DAMAGED_PLEASE_REINSTALL_INTERNET_EXPLORER = 768; // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â´Ã�ÂµÃ�Â½
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Windows.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ�Âµ
    // Internet
    // Explorer.
    public static final int A_HACKING_TOOL_HAS_BEEN_DISCOVERED_PLEASE_TRY_PLAYING_AGAIN_AFTER_CLOSING_UNNECESSARY_PROGRAMS = 769; // Ã�Å¾Ã�Â±Ã�Â½Ã�Â°Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½
    // Ã�Â²Ã�Â·Ã�Â»Ã�Â¾Ã�Â¼.
    // Ã�â€™Ã‘â€¹Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â½Ã‘Æ’Ã�Â¶Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘â€¹
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int THE_GAMEGUARD_UPDATE_WAS_CANCELED_PLEASE_CHECK_YOUR_NETWORK_CONNECTION_STATUS_OR_FIREWALL = 770; // Ã�Å¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘â€¹
    // GameGuard
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�ÂµÃ‘â€šÃ�Â¸
    // Ã�Â¸
    // Firewall.
    public static final int THE_GAMEGUARD_UPDATE_WAS_CANCELED_PLEASE_TRY_RUNNING_IT_AGAIN_AFTER_DOING_A_VIRUS_SCAN_OR_CHANGING_THE_SETTINGS_IN_YOUR_PC_MANAGEMENT_PROGRAM = 771; // Ã�Å¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘â€¹
    // GameGuard
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¿Ã‘Å’Ã‘Å½Ã‘â€šÃ�ÂµÃ‘â‚¬
    // Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã‘ï¿½Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int THERE_WAS_A_PROBLEM_WHEN_RUNNING_GAMEGUARD = 772; // Ã�Å¾Ã�Â±Ã�Â½Ã�Â°Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // GameGuard.
    public static final int THE_GAME_OR_GAMEGUARD_FILES_ARE_DAMAGED = 773; // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â´Ã�ÂµÃ�Â½
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â¸Ã�Â»Ã�Â¸
    // GameGuard.
    public static final int SINCE_THIS_IS_A_PEACE_ZONE_PLAY_TIME_DOES_NOT_GET_EXPENDED_HERE = 774; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int FROM_HERE_ON_PLAY_TIME_WILL_BE_EXPENDED = 775; // Ã�Â¡
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int YOU_MAY_NOT_LOG_OUT_FROM_THIS_LOCATION = 778; // Ã�â€”Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int YOU_MAY_NOT_RESTART_IN_THIS_LOCATION = 779; // Ã�â€”Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int OBSERVATION_IS_ONLY_POSSIBLE_DURING_A_SIEGE = 780; // Ã�Â Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹.
    public static final int OBSERVERS_CANNOT_PARTICIPATE = 781; // Ã�Â­Ã‘â€šÃ�Â° Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â° Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½.
    public static final int YOU_MAY_NOT_OBSERVE_A_SUMMONED_CREATURE = 782; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾,
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int LOTTERY_TICKET_SALES_HAVE_BEEN_TEMPORARILY_SUSPENDED = 783; // Ã�â€™
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¹Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°.
    public static final int TICKETS_FOR_THE_CURRENT_LOTTERY_ARE_NO_LONGER_AVAILABLE = 784; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¹Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_RESULTS_OF_LOTTERY_NUMBER_S1_HAVE_NOT_YET_BEEN_PUBLISHED = 785; // Ã�â€¢Ã‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã‘â‚¬Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¸
    // Ã¢â€žâ€“$s1.
    public static final int INCORRECT_SYNTAX = 786; // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾ Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¾.
    public static final int THE_TRYOUTS_ARE_FINISHED = 787; // Ã�Å¾Ã‘â€šÃ�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã‘â€¹Ã�Â¹ Ã‘â€šÃ‘Æ’Ã‘â‚¬
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int THE_FINALS_ARE_FINISHED = 788; // Ã�Â¤Ã�Â¸Ã�Â½Ã�Â°Ã�Â» Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int THE_TRYOUTS_HAVE_BEGUN = 789; // Ã�Å¾Ã‘â€šÃ�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã‘â€¹Ã�Â¹ Ã‘â€šÃ‘Æ’Ã‘â‚¬
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    public static final int THE_FINALS_HAVE_BEGUN = 790; // Ã�Â¤Ã�Â¸Ã�Â½Ã�Â°Ã�Â» Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    public static final int THE_FINAL_MATCH_IS_ABOUT_TO_BEGIN_LINE_UP = 791; // Ã�Â¤Ã�Â¸Ã�Â½Ã�Â°Ã�Â»
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_SIEGE_OF_THE_CLAN_HALL_IS_FINISHED = 792; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_SIEGE_OF_THE_CLAN_HALL_HAS_BEGUN = 793; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT = 794; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾.
    public static final int ONLY_CLAN_LEADERS_ARE_AUTHORIZED_TO_SET_RIGHTS = 795; // Ã�Å¸Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã‘ï¿½Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOUR_REMAINING_OBSERVATION_TIME_IS_S1_MINUTES = 796; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_MAY_CREATE_UP_TO_48_MACROS = 797; // Ã�Å“Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’ 48
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â².
    public static final int ITEM_REGISTRATION_IS_IRREVERSIBLE_DO_YOU_WISH_TO_CONTINUE = 798; // Ã�Â Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_OBSERVATION_TIME_HAS_EXPIRED = 799; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Â°
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�ÂºÃ�Â»Ã�Â¾.
    public static final int YOU_ARE_TOO_LATE_THE_REGISTRATION_PERIOD_IS_OVER = 800; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€¡Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�ÂºÃ�Â»Ã�Â¾,
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int REGISTRATION_FOR_THE_CLAN_HALL_SIEGE_IS_CLOSED = 801; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int PETITIONS_ARE_NOT_BEING_ACCEPTED_AT_THIS_TIME_YOU_MAY_SUBMIT_YOUR_PETITION_AFTER_S1_AM_PM = 802; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½.
    // Ã�â€”Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â¾Ã�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int ENTER_THE_SPECIFICS_OF_YOUR_PETITION = 803; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¸.
    public static final int SELECT_A_TYPE = 804; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ Ã‘â€šÃ�Â¸Ã�Â¿.
    public static final int PETITIONS_ARE_NOT_BEING_ACCEPTED_AT_THIS_TIME_YOU_MAY_SUBMIT_YOUR_PETITION_AFTER_S1_AM_PM_1 = 805; // Ã�ï¿½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â°.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘â€¡.
    public static final int IF_YOU_ARE_TRAPPED_TRY_TYPING__UNSTUCK = 806; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã‘ï¿½Ã�Â½Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘ï¿½Ã‘â€šÃ‘Æ’Ã‘â‚¬Ã�Â°Ã‘â€¦,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�ÂºÃ‘Æ’
    // Ã�Â²Ã�Â²Ã�Â¾Ã�Â´Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘Æ’
    // "/unstuck"
    public static final int THIS_TERRAIN_IS_UNNAVIGABLE_PREPARE_FOR_TRANSPORT_TO_THE_NEAREST_VILLAGE = 807; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾,
    // Ã�Â¼Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�Â±Ã�Â»Ã�Â¸Ã�Â¶Ã�Â°Ã�Â¹Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â³Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â´.
    public static final int YOU_ARE_STUCK_YOU_MAY_SUBMIT_A_PETITION_BY_TYPING__GM = 808; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã‘ï¿½Ã�Â»Ã�Â¸.
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // "/gm".
    public static final int YOU_ARE_STUCK_YOU_WILL_BE_TRANSPORTED_TO_THE_NEAREST_VILLAGE_IN_FIVE_MINUTES = 809; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã‘ï¿½Ã�Â»Ã�Â¸.
    // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â±Ã�Â»Ã�Â¸Ã�Â¶Ã�Â°Ã�Â¹Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â³Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â´.
    public static final int INVALID_MACRO_REFER_TO_THE_HELP_FILE_FOR_INSTRUCTIONS = 810; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½.
    // Ã�Å¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Âº
    // Ã‘â‚¬Ã‘Æ’Ã�ÂºÃ�Â¾Ã�Â²Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’
    // Ã�Â¿Ã�Â¾
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â¼.
    public static final int YOU_WILL_BE_MOVED_TO_S1_DO_YOU_WISH_TO_CONTINUE = 811; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�Â°Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘Å½
    // ($s1).
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_SECRET_TRAP_HAS_INFLICTED_S1_DAMAGE_ON_YOU = 812; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â»Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Ë†Ã�ÂºÃ‘Æ’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°.
    public static final int YOU_HAVE_BEEN_POISONED_BY_A_SECRET_TRAP = 813; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â»Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Ë†Ã�ÂºÃ‘Æ’
    // Ã�Â¸
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int YOUR_SPEED_HAS_BEEN_DECREASED_BY_A_SECRET_TRAP = 814; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â»Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Ë†Ã�ÂºÃ‘Æ’,
    // Ã�Â¸
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_TRYOUTS_ARE_ABOUT_TO_BEGIN_LINE_UP = 815; // Ã�Å¾Ã‘â€šÃ�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€šÃ‘Æ’Ã‘â‚¬
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã‘Å’Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’.
    public static final int TICKETS_ARE_NOW_AVAILABLE_FOR_THE_S1TH_MONSTER_RACE = 816; // Ã�Â¡Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Â°
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1.
    // Ã�Å¡Ã‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int WE_ARE_NOW_SELLING_TICKETS_FOR_THE_S1TH_MONSTER_RACE = 817; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1.
    public static final int TICKET_SALES_FOR_THE_MONSTER_RACE_WILL_CEASE_IN_S1_MINUTE_S = 818; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int TICKETS_SALES_ARE_CLOSED_FOR_THE_S1TH_MONSTER_RACE_ODDS_ARE_POSTED = 819; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¼Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘Æ’Ã�Â¼Ã�Â¼Ã‘Æ’
    // Ã�Â²Ã‘â€¹Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹Ã‘Ë†Ã�Â°.
    public static final int THE_S2TH_MONSTER_RACE_WILL_BEGIN_IN_S1_MINUTES = 820; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Â°
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s2.
    public static final int THE_S1TH_MONSTER_RACE_WILL_BEGIN_IN_30_SECONDS = 821; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 30
    // Ã‘ï¿½Ã�ÂµÃ�Âº
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Â°
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s2.
    public static final int THE_S1TH_MONSTER_RACE_IS_ABOUT_TO_BEGIN_COUNTDOWN_IN_FIVE_SECONDS = 822; // Ã�â€œÃ�Â¾Ã�Â½Ã�ÂºÃ�Â°
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã‘ï¿½Ã�ÂµÃ�Âº
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š.
    public static final int THE_RACE_WILL_BEGIN_IN_S1_SECONDS = 823; // Ã�â€�Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // $s1 Ã‘ï¿½Ã�ÂµÃ�Âº!
    public static final int THEYRE_OFF = 824; // Ã�Â¡Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘â€š! Ã�â€œÃ�Â¾Ã�Â½Ã�ÂºÃ�Â° Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’!
    public static final int MONSTER_RACE_S1_IS_FINISHED = 825; // Ã�â€œÃ�Â¾Ã�Â½Ã�ÂºÃ�Â° Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1 Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int FIRST_PRIZE_GOES_TO_THE_PLAYER_IN_LANE_S1_SECOND_PRIZE_GOES_TO_THE_PLAYER_IN_LANE_S2 = 826; // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�ÂºÃ�Âµ
    // $s1!
    // II
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾
    // -
    // Ã�Â·Ã�Â°
    // Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�ÂºÃ�Â¾Ã�Â¹
    // $s2.
    public static final int YOU_MAY_NOT_IMPOSE_A_BLOCK_ON_A_GM = 827; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // GM.
    public static final int ARE_YOU_SURE_YOU_WISH_TO_DELETE_THE_S1_MACRO = 828; // Ã�Å“Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½
    // $s1
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int S1_HAS_ROLLED_S2 = 834; // Ã�ï¿½Ã�Â° Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦ Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼
    // $c1 Ã�ÂºÃ‘Æ’Ã�Â±Ã�Â¸Ã�ÂºÃ�Â°Ã‘â€¦ Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â»Ã�Â¾ $s2.
    public static final int YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIMETRY_AGAIN_LATER = 835; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ‘Æ’Ã�Â±Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int THE_INVENTORY_IS_FULL_NO_FURTHER_QUEST_ITEMS_MAY_BE_DEPOSITED_AT_THIS_TIME = 836; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â·Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    public static final int MACRO_DESCRIPTIONS_MAY_CONTAIN_UP_TO_32_CHARACTERS = 837; // Ã�Å¾Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // 32
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â°.
    public static final int ENTER_THE_NAME_OF_THE_MACRO = 838; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â°.
    public static final int THAT_NAME_IS_ALREADY_ASSIGNED_TO_ANOTHER_MACRO = 839; // Ã�Å“Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã‘ï¿½
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸Ã�Â¼
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â¼
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š.
    public static final int THAT_RECIPE_IS_ALREADY_REGISTERED = 840; // Ã�Â¢Ã�Â°Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€š
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š.
    public static final int NO_FURTHER_RECIPES_MAY_BE_REGISTERED = 841; // Ã�â€”Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int YOU_ARE_NOT_AUTHORIZED_TO_REGISTER_A_RECIPE = 842; // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â½Ã�Â¸Ã�Â·Ã�Â¾Ã�Âº,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€š.
    public static final int THE_SIEGE_OF_S1_IS_FINISHED = 843; // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â° $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_SIEGE_TO_CONQUER_S1_HAS_BEGUN = 844; // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // $s1
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int THE_DEADLINE_TO_REGISTER_FOR_THE_SIEGE_OF_S1_HAS_PASSED = 845; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // $s1
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�ÂºÃ�Â»Ã�Â¾.
    public static final int THE_SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST = 846; // Ã�â€“Ã�ÂµÃ�Â»Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ‘â€š,
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // $s1
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int A_CLAN_THAT_OWNS_A_CLAN_HALL_MAY_NOT_PARTICIPATE_IN_A_CLAN_HALL_SIEGE = 847; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½,
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â¼,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int S1_HAS_BEEN_DELETED = 848; // $s1 Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½.
    public static final int S1_CANNOT_BE_FOUND = 849; // $s1 Ã�Â½Ã�Âµ Ã�Â½Ã�Â°Ã�Â¹Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S1_ALREADY_EXISTS_1 = 850; // $s1 Ã‘Æ’Ã�Â¶Ã�Âµ Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š.
    public static final int S1_HAS_BEEN_ADDED = 851; // $s1 Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½.
    public static final int THE_RECIPE_IS_INCORRECT = 852; // Ã�Â¡Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€šÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾.
    public static final int YOU_MAY_NOT_ALTER_YOUR_RECIPE_BOOK_WHILE_ENGAGED_IN_MANUFACTURING = 853; // Ã�â€™
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â½Ã�Â¸Ã�Â³Ã�Â¾Ã�Â¹
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€šÃ�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_LACK_S2_OF_S1 = 854; // Ã�ï¿½Ã�Âµ Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â° $s1
    // ($s2 Ã‘Ë†Ã‘â€š.)
    public static final int S1_CLAN_HAS_DEFEATED_S2 = 855; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½ $s1 Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â» Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ Ã�Â·Ã�Â° Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â° $s2.
    public static final int THE_SIEGE_OF_S1_HAS_ENDED_IN_A_DRAW = 856; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â½Ã�Â¸Ã‘â€¡Ã‘Å’Ã‘Å½.
    public static final int S1_CLAN_HAS_WON_IN_THE_PRELIMINARY_MATCH_OF_S2 = 857; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»
    // Ã�Â²
    // Ã�Â¾Ã‘â€šÃ�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘â€šÃ‘Æ’Ã‘â‚¬Ã�Âµ
    // $s2.
    public static final int THE_PRELIMINARY_MATCH_OF_S1_HAS_ENDED_IN_A_DRAW = 858; // Ã�Å¾Ã‘â€šÃ�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€šÃ‘Æ’Ã‘â‚¬
    // $s1
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â½Ã�Â¸Ã‘â€¡Ã‘Å’Ã‘Å½.
    public static final int PLEASE_REGISTER_A_RECIPE = 859; // Ã�â€”Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€š.
    public static final int YOU_MAY_NOT_BUILD_YOUR_HEADQUARTERS_IN_CLOSE_PROXIMITY_TO_ANOTHER_HEADQUARTERS = 860; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Ë†Ã‘â€šÃ�Â°Ã�Â±
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ,
    // Ã�Â´Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘Ë†Ã‘â€šÃ�Â°Ã�Â±Ã�Â°
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�Â°Ã�Â»Ã�Â°.
    public static final int YOU_HAVE_EXCEEDED_THE_MAXIMUM_NUMBER_OF_MEMOS = 861; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã�Â°Ã�Â¼Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Âº.
    public static final int ODDS_ARE_NOT_POSTED_UNTIL_TICKET_SALES_HAVE_CLOSED = 862; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¼Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘Æ’Ã�Â¼Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â°,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_FEEL_THE_ENERGY_OF_FIRE = 863; // Ã�â€™Ã‘â€¹ Ã‘â€¡Ã‘Æ’Ã�Â²Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’ Ã�Å¾Ã�Â³Ã�Â½Ã‘ï¿½.
    public static final int YOU_FEEL_THE_ENERGY_OF_WATER = 864; // Ã�â€™Ã‘â€¹ Ã‘â€¡Ã‘Æ’Ã�Â²Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’ Ã�â€™Ã�Â¾Ã�Â´Ã‘â€¹.
    public static final int YOU_FEEL_THE_ENERGY_OF_WIND = 865; // Ã�â€™Ã‘â€¹ Ã‘â€¡Ã‘Æ’Ã�Â²Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’ Ã�â€™Ã�ÂµÃ‘â€šÃ‘â‚¬Ã�Â°.
    public static final int YOU_MAY_NO_LONGER_GATHER_ENERGY = 866; // Ã�â€™Ã‘â€¹ Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’.
    public static final int THE_ENERGY_IS_DEPLETED = 867; // Ã�Â¡Ã�Â¸Ã�Â»Ã�Â° Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¿Ã�Â°Ã�Â»Ã�Â°.
    public static final int THE_ENERGY_OF_FIRE_HAS_BEEN_DELIVERED = 868; // Ã�â€˜Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã�Å¾Ã�Â³Ã�Â½Ã‘ï¿½.
    public static final int THE_ENERGY_OF_WATER_HAS_BEEN_DELIVERED = 869; // Ã�â€˜Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã�â€™Ã�Â¾Ã�Â´Ã‘â€¹.
    public static final int THE_ENERGY_OF_WIND_HAS_BEEN_DELIVERED = 870; // Ã�â€˜Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã�â€™Ã�ÂµÃ‘â€šÃ‘â‚¬Ã�Â°.
    public static final int THE_SEED_HAS_BEEN_SOWN = 871; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘ï¿½ Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾ Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂµÃ‘ï¿½Ã�Â½Ã�Â¾.
    public static final int THIS_SEED_MAY_NOT_BE_SOWN_HERE = 872; // Ã�Â­Ã‘â€šÃ�Â¾ Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’.
    public static final int THAT_CHARACTER_DOES_NOT_EXIST = 873; // Ã�Â¢Ã�Â°Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š.
    public static final int THE_CAPACITY_OF_THE_WAREHOUSE_HAS_BEEN_EXCEEDED = 874; // Ã�Â¥Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int TRANSPORT_OF_CARGO_HAS_BEEN_CANCELED = 875; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int CARGO_WAS_NOT_DELIVERED = 876; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â° Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int THE_SYMBOL_HAS_BEEN_ADDED = 877; // Ã�Â¡Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â» Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½.
    public static final int THE_SYMBOL_HAS_BEEN_DELETED = 878; // Ã�Â¡Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â» Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½.
    public static final int THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE = 879; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€žÃ�Â¸Ã�Â»Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�ÂºÃ�Â°
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã‘â€¹
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int THE_TRANSACTION_IS_COMPLETE = 880; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€¡Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THERE_IS_A_DISCREPANCY_ON_THE_INVOICE = 881; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Âµ.
    public static final int SEED_QUANTITY_IS_INCORRECT = 882; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½Ã�Â½.
    public static final int SEED_INFORMATION_IS_INCORRECT = 883; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½ Ã�Â¾
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸.
    public static final int THE_MANOR_INFORMATION_HAS_BEEN_UPDATED = 884; // Ã�ËœÃ�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¾
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_NUMBER_OF_CROPS_IS_INCORRECT = 885; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾.
    public static final int THE_CROPS_ARE_PRICED_INCORRECTLY = 886; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¾ Ã‘â€ Ã�ÂµÃ�Â½Ã�Âµ
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â°Ã‘ï¿½.
    public static final int THE_TYPE_IS_INCORRECT = 887; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹ Ã‘â€šÃ�Â¸Ã�Â¿.
    public static final int NO_CROPS_CAN_BE_PURCHASED_AT_THIS_TIME = 888; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â°Ã�Â¹.
    public static final int THE_SEED_WAS_SUCCESSFULLY_SOWN = 889; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â².
    public static final int THE_SEED_WAS_NOT_SOWN = 890;
    public static final int YOU_ARE_NOT_AUTHORIZED_TO_HARVEST = 891;
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â°Ã�Â¹.
    public static final int THE_HARVEST_HAS_FAILED = 892;
    public static final int THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN = 893; // Ã�Â¡Ã�Â±Ã�Â¾Ã‘â‚¬
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â´Ã�ÂµÃ�Â½,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â²Ã�Â°.
    public static final int UP_TO_S1_RECIPES_CAN_BE_REGISTERED = 894; // Ã�Å“Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€šÃ�Â¾Ã�Â²:
    // $s1.
    public static final int NO_RECIPES_HAVE_BEEN_REGISTERED = 895; // Ã�â€”Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€šÃ�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ‘â€š.
    public static final int QUEST_RECIPES_CAN_NOT_BE_REGISTERED = 896;
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€š.
    public static final int THE_FEE_TO_CREATE_THE_ITEM_IS_INCORRECT = 897; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¸Ã‘ï¿½Ã‘ï¿½Ã�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int THE_SYMBOL_CANNOT_BE_DRAWN = 899;
    public static final int NO_SLOT_EXISTS_TO_DRAW_THE_SYMBOL = 900; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â¹Ã�ÂºÃ�Â¸
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int THE_SYMBOL_INFORMATION_CANNOT_BE_FOUND = 901;
    public static final int THE_NUMBER_OF_ITEMS_IS_INCORRECT = 902; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾.
    public static final int YOU_MAY_NOT_SUBMIT_A_PETITION_WHILE_FROZEN_BE_PATIENT = 903; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â²
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸.
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int ITEMS_CANNOT_BE_DISCARDED_WHILE_IN_PRIVATE_STORE_STATUS = 904; // Ã�â€™
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THE_CURRENT_SCORE_FOR_THE_HUMAN_RACE_IS_S1 = 905; // Ã�Â Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€š
    // Ã�â€ºÃ‘Å½Ã�Â´Ã�ÂµÃ�Â¹
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // -
    // $s1.
    public static final int THE_CURRENT_SCORE_FOR_THE_ELVEN_RACE_IS_S1 = 906; // Ã�Â Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€š
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // -
    // $s1.
    public static final int THE_CURRENT_SCORE_FOR_THE_DARK_ELVEN_RACE_IS_S1 = 907; // Ã�Â Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€š
    // Ã�Â¢Ã�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // -
    // $s1.
    public static final int THE_CURRENT_SCORE_FOR_THE_ORC_RACE_IS_S1 = 908; // Ã�Â Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€š
    // Ã�Å¾Ã‘â‚¬Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // -
    // $s1.
    public static final int THE_CURRENT_SCORE_FOR_THE_DWARVEN_RACE_IS_S1 = 909; // Ã�Â Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€š
    // Ã�â€œÃ�Â½Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // -
    // $s1.
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_TALKING_ISLAND_VILLAGE = 910; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_GLUDIN_VILLAGE = 911; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_GLUDIO_CASTLE_TOWN = 912; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â¾)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_THE_NEUTRAL_ZONE = 913; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½::
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�ï¿½Ã�ÂµÃ�Â¹Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�â€”Ã�Â¾Ã�Â½Ã‘â€¹)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_ELVEN_VILLAGE = 914; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¾Ã�Â²)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_DARK_ELVEN_VILLAGE = 915; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸
    // Ã�Â¢Ã�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¾Ã�Â²)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_DION_CASTLE_TOWN = 916; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�Â¸Ã�Â¾Ã�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_FLORAN_VILLAGE = 917; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�Â¤Ã�Â»Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_GIRAN_CASTLE_TOWN = 918; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_GIRAN_HARBOR = 919; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€œÃ�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_ORC_VILLAGE = 920; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸
    // Ã�Å¾Ã‘â‚¬Ã�ÂºÃ�Â¾Ã�Â²)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_DWARVEN_VILLAGE = 921; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â½Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â²)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_THE_TOWN_OF_OREN = 922; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�Å¾Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_HUNTERS_VILLAGE = 923; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸
    // Ã�Å¾Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_ADEN_CASTLE_TOWN = 924; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�ï¿½Ã�Â´Ã�ÂµÃ�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_THE_COLISEUM = 925; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã�Â·Ã�ÂµÃ‘ï¿½)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_HEINE = 926; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�Â¥Ã�ÂµÃ�Â¹Ã�Â½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_THE_TOWN_OF_SCHUTTGART = 1714; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â¾Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¨Ã‘Æ’Ã‘â€šÃ�Â³Ã�Â°Ã‘â‚¬Ã‘â€šÃ�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_KAMAEL_VILLAGE = 2189; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�â€™Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸
    // Ã�Å¡Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½Ã�Â»Ã‘Å’)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_PRIMEVAL_ISLE = 1924; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°).
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_SOUTH_OF_WASTELANDS_CAP = 2190; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�â€™Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�â€ºÃ�Â°Ã�Â³Ã�ÂµÃ‘â‚¬Ã‘ï¿½
    // Ã�Å¸Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘Ë†Ã�Â¸)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_FANTASY_ISLE = 2259; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â¾Ã�Â·Ã�Â»Ã�Âµ
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�â€œÃ‘â‚¬Ã�ÂµÃ�Â·)
    public static final int THE_CURRENT_TIME_IS_S1S2_IN_THE_DAY = 927; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½:
    // $s1 Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â´Ã�Â½Ã‘ï¿½.
    public static final int THE_CURRENT_TIME_IS_S1S2_IN_THE_NIGHT = 928; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½:
    // $s1
    // Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â½Ã�Â¾Ã‘â€¡Ã�Â¸.
    public static final int NO_COMPENSATION_WAS_GIVEN_FOR_THE_FARM_PRODUCTS = 929; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â·Ã�Â°
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â°Ã�Â¹
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š.
    public static final int LOTTERY_TICKETS_ARE_NOT_CURRENTLY_BEING_SOLD = 930; // Ã�â€™
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¹Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_WINNING_LOTTERY_TICKET_NUMBER_HAS_NOT_YET_BEEN_ANNOUNCED = 931; // Ã�â€™Ã‘â€¹Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹Ã‘Ë†Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¹Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_CHAT_LOCALLY_WHILE_OBSERVING = 932; // Ã�â€™
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THE_SEED_PRICING_GREATLY_DIFFERS_FROM_STANDARD_SEED_PRICES = 933; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¾
    // Ã‘â€ Ã�ÂµÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½Ã�Â½.
    public static final int IT_IS_A_DELETED_RECIPE = 934; // Ã�Â£Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹ Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€š.
    public static final int THE_AMOUNT_IS_NOT_SUFFICIENT_AND_SO_THE_MANOR_IS_NOT_IN_OPERATION = 935; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int USE_S1 = 936; // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½: $s1.
    public static final int CURRENTLY_PREPARING_FOR_PRIVATE_WORKSHOP = 937; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â¾Ã‘â€¡Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°.
    public static final int THE_COMMUNITY_SERVER_IS_CURRENTLY_OFFLINE = 938; // Ã�Â¡Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€žÃ�Â¸Ã�Â»Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�ÂºÃ�Âµ.
    public static final int YOU_CANNOT_EXCHANGE_WHILE_BLOCKING_EVERYTHING = 939; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int S1_IS_BLOCKING_EVERYTHING = 940; // $s1 Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½ Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int RESTART_AT_TALKING_ISLAND_VILLAGE = 941; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    public static final int RESTART_AT_GLUDIN_VILLAGE = 942; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½
    public static final int RESTART_AT_GLUDIN_CASTLE_TOWN = 943; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â² Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â¾
    public static final int RESTART_AT_THE_NEUTRAL_ZONE = 944; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�ï¿½Ã�ÂµÃ�Â¹Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�â€”Ã�Â¾Ã�Â½Ã‘Æ’
    public static final int RESTART_AT_ELVEN_VILLAGE = 945; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¸Ã�Â¹Ã‘ï¿½Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    public static final int RESTART_AT_DARK_ELVEN_VILLAGE = 946; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â² Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�Â¢Ã�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¾Ã�Â²
    public static final int RESTART_AT_DION_CASTLE_TOWN = 947; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�â€�Ã�Â¸Ã�Â¾Ã�Â½
    public static final int RESTART_AT_FLORAN_VILLAGE = 948; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²Ã�Â¾
    // Ã�Â¤Ã�Â»Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â½
    public static final int RESTART_AT_GIRAN_CASTLE_TOWN = 949; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½
    public static final int RESTART_AT_GIRAN_HARBOR = 950; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�â€œÃ�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’ Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    public static final int RESTART_AT_ORC_VILLAGE = 951; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½ Ã�Å¾Ã‘â‚¬Ã�ÂºÃ�Â¾Ã�Â²
    public static final int RESTART_AT_DWARVEN_VILLAGE = 952; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½ Ã�â€œÃ�Â½Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â²
    public static final int RESTART_AT_THE_TOWN_OF_OREN = 953; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�Å¾Ã‘â‚¬Ã�ÂµÃ�Â½
    public static final int RESTART_AT_HUNTERS_VILLAGE = 954; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�Å¾Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    public static final int RESTART_AT_ADEN_CASTLE_TOWN = 955; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�ï¿½Ã�Â´Ã�ÂµÃ�Â½
    public static final int RESTART_AT_THE_COLISEUM = 956; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â²
    // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã�Â·Ã�ÂµÃ�Â¹
    public static final int RESTART_AT_HEINE = 957; // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â² Ã�Â¥Ã�ÂµÃ�Â¹Ã�Â½
    public static final int ITEMS_CANNOT_BE_DISCARDED_OR_DESTROYED_WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP = 958; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int S1_S2_MANUFACTURING_SUCCESS = 959; // $s1 (*$s2):
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â´Ã�Â°Ã‘â€¡Ã�Â½Ã�Â¾.
    public static final int S1_MANUFACTURING_FAILURE = 960; // $s1: Ã�Â½Ã�ÂµÃ‘Æ’Ã�Â´Ã�Â°Ã‘â€¡Ã�Â°.
    public static final int YOU_ARE_NOW_BLOCKING_EVERYTHING = 961; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â² Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int YOU_ARE_NO_LONGER_BLOCKING_EVERYTHING = 962; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int PLEASE_DETERMINE_THE_MANUFACTURING_PRICE = 963; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int CHATTING_IS_PROHIBITED_FOR_ABOUT_ONE_MINUTE = 964; // Ã�Â§Ã�Â°Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â½Ã�Â°
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_CHATTING_PROHIBITION_HAS_BEEN_REMOVED = 965; // Ã�â€˜Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â°.
    public static final int CHATTING_IS_CURRENTLY_PROHIBITED_IF_YOU_TRY_TO_CHAT_BEFORE_THE_PROHIBITION_IS_REMOVED_THE_PROHIBITION_TIME_WILL_BECOME_EVEN_LONGER = 966; // Ã�â€˜Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    // Ã�â€™
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â¸
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int DO_YOU_ACCEPT_THE_PARTY_INVITATION_FROM_S1_ITEM_DISTRIBUTION_RANDOM_INCLUDING_SPOIL = 967; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â½Ã‘â€¹
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã‘ï¿½
    // $c1?
    // (Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Â¹Ã�Â½Ã�Â¾+Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã‘â€šÃ‘Å’)
    public static final int DO_YOU_ACCEPT_THE_PARTY_INVITATION_FROM_S1_ITEM_DISTRIBUTION_BY_TURN = 968; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â½Ã‘â€¹
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã‘ï¿½
    // $c1?
    // (Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Å¸Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â¸)
    public static final int DO_YOU_ACCEPT_THE_PARTY_INVITATION_FROM_S1_ITEM_DISTRIBUTION_BY_TURN_INCLUDING_SPOIL = 969; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â½Ã‘â€¹
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã‘ï¿½
    // $c1?
    // (Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Å¸Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â¸+Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã‘â€šÃ‘Å’)
    public static final int S2S_MP_HAS_BEEN_DRAINED_BY_S1 = 970; // $s2 MP Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼
    // $c1.
    public static final int PETITIONS_CANNOT_EXCEED_255_CHARACTERS = 971; // Ã�Å¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // 255
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int PETS_CANNOT_USE_THIS_ITEM = 972; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â½Ã�Âµ Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â¼.
    public static final int PLEASE_INPUT_NO_MORE_THAN_THE_NUMBER_YOU_HAVE = 973; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾,
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã‘â€šÃ�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’.
    public static final int THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL = 974; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã‘Æ’.
    public static final int THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_A_SOUL = 975; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã‘Æ’.
    public static final int THE_SOUL_CRYSTAL_BROKE_BECAUSE_IT_WAS_NOT_ABLE_TO_ENDURE_THE_SOUL_ENERGY = 976; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€¡Ã�ÂµÃ�Â½,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â¸Ã�Â¼
    // Ã�Â´Ã‘Æ’Ã‘Ë†.
    public static final int THE_SOUL_CRYSTALS_CAUSED_RESONATION_AND_FAILED_AT_ABSORBING_A_SOUL = 977; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã‘Æ’,
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã‘â€¹
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã�ÂµÃ�Â·Ã�Â¾Ã�Â½Ã�Â°Ã�Â½Ã‘ï¿½.
    public static final int THE_SOUL_CRYSTAL_IS_REFUSING_TO_ABSORB_A_SOUL = 978; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸.
    public static final int ARRIVED_AT_TALKING_ISLAND_HARBOR = 979; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°.
    public static final int WILL_LEAVE_FOR_GLUDIN_HARBOR_AFTER_ANCHORING_FOR_TEN_MINUTES = 980; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int WILL_LEAVE_FOR_GLUDIN_HARBOR_IN_FIVE_MINUTES = 981; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int WILL_LEAVE_FOR_GLUDIN_HARBOR_IN_ONE_MINUTE = 982; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THOSE_WISHING_TO_RIDE_SHOULD_MAKE_HASTE_TO_GET_ON = 983; // Ã�â€“Ã�ÂµÃ�Â»Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�ÂµÃ‘â€¦Ã�Â°Ã‘â€šÃ‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¿Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’.
    public static final int LEAVING_SOON_FOR_GLUDIN_HARBOR = 984; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â² Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°.
    public static final int LEAVING_FOR_GLUDIN_HARBOR = 985; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½ Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’ Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°.
    public static final int ARRIVED_AT_GLUDIN_HARBOR = 986; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’ Ã�Â²Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â» Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’ Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°.
    public static final int WILL_LEAVE_FOR_TALKING_ISLAND_HARBOR_AFTER_ANCHORING_FOR_TEN_MINUTES = 987; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int WILL_LEAVE_FOR_TALKING_ISLAND_HARBOR_IN_FIVE_MINUTES = 988; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int WILL_LEAVE_FOR_TALKING_ISLAND_HARBOR_IN_ONE_MINUTE = 989; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int LEAVING_SOON_FOR_TALKING_ISLAND_HARBOR = 990; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Âº
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã‘Æ’.
    public static final int LEAVING_FOR_TALKING_ISLAND_HARBOR = 991; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã‘Æ’.
    public static final int ARRIVED_AT_GIRAN_HARBOR = 992; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’ Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã�Â» Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’ Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°.
    public static final int WILL_LEAVE_FOR_GIRAN_HARBOR_AFTER_ANCHORING_FOR_TEN_MINUTES = 993; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€š
    // Ã�Â¿Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�Âº
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int WILL_LEAVE_FOR_GIRAN_HARBOR_IN_FIVE_MINUTES = 994; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int WILL_LEAVE_FOR_GIRAN_HARBOR_IN_ONE_MINUTE = 995; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int LEAVING_SOON_FOR_GIRAN_HARBOR = 996; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Âº Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°.
    public static final int LEAVING_FOR_GIRAN_HARBOR = 997; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½ Ã�Âº
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸ Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°.
    public static final int THE_INNADRIL_PLEASURE_BOAT_HAS_ARRIVED_IT_WILL_ANCHOR_FOR_TEN_MINUTES = 998; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�ËœÃ�Â½Ã�Â½Ã�Â°Ã�Â´Ã‘â‚¬Ã�Â¸Ã�Â»Ã�Â°.
    // Ã�Â¡Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�ÂºÃ�Â°
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_INNADRIL_PLEASURE_BOAT_WILL_LEAVE_IN_FIVE_MINUTES = 999; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â²
    // Ã�ËœÃ�Â½Ã�Â½Ã�Â°Ã�Â´Ã‘â‚¬Ã�Â¸Ã�Â»
    // Ã�Â¾Ã‘â€šÃ�Â±Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_INNADRIL_PLEASURE_BOAT_WILL_LEAVE_IN_ONE_MINUTE = 1000; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â²
    // Ã�ËœÃ�Â½Ã�Â½Ã�Â°Ã�Â´Ã‘â‚¬Ã�Â¸Ã�Â»
    // Ã�Â¾Ã‘â€šÃ�Â±Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int INNADRIL_PLEASURE_BOAT_IS_LEAVING_SOON = 1001; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â²
    // Ã�ËœÃ�Â½Ã�Â½Ã�Â°Ã�Â´Ã‘â‚¬Ã�Â¸Ã�Â»
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â±Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int INNADRIL_PLEASURE_BOAT_IS_LEAVING = 1002; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â²
    // Ã�ËœÃ�Â½Ã�Â½Ã�Â°Ã�Â´Ã‘â‚¬Ã�Â¸Ã�Â»
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int CANNOT_PROCESS_A_MONSTER_RACE_TICKET = 1003; // Ã�Å¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â¾Ã‘â€šÃ�Â°Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int THE_PRELIMINARY_MATCH_REGISTRATION_OF_S1_HAS_FINISHED = 1007; // Ã�Â Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘â€šÃ�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘â€šÃ‘Æ’Ã‘â‚¬Ã�Âµ
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int A_HUNGRY_STRIDER_CANNOT_BE_MOUNTED_OR_DISMOUNTED = 1008; // Ã�Å¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int A_STRIDER_CANNOT_BE_RIDDEN_WHEN_DEAD = 1009; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²Ã‘â€¹.
    public static final int A_DEAD_PET_CANNOT_BE_RIDDEN = 1010;
    public static final int A_STRIDER_IN_BATTLE_CANNOT_BE_RIDDEN = 1011;
    public static final int A_STRIDER_CANNOT_BE_RIDDEN_WHILE_IN_BATTLE = 1012;
    public static final int A_STRIDER_CAN_BE_RIDDEN_ONLY_WHEN_STANDING = 1013;
    public static final int THE_PET_ACQUIRED_EXPERIENCE_POINTS_OF_S1 = 1014;
    public static final int THE_PET_GAVE_DAMAGE_OF_S1 = 1015;
    public static final int THE_PET_RECEIVED_DAMAGE_OF_S2_CAUSED_BY_S1 = 1016; // $c1
    // Ã�Â½Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã‘Æ’
    // $s2
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°.
    public static final int PETS_CRITICAL_HIT = 1017; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã�Â¹ Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°!
    public static final int THE_PET_USES_S1 = 1018; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€š:
    // $s1.
    public static final int YOUR_PET_USES_S1 = 1019; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€š:
    // $s1.
    public static final int THE_PET_GAVE_S1 = 1020; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¿Ã�Â¾Ã�Â´Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»:
    // $s1.
    public static final int THE_PET_GAVE_S2_S1_S = 1021; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¿Ã�Â¾Ã�Â´Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š: $s1 ($s2
    // Ã‘Ë†Ã‘â€š.).
    public static final int THE_PET_GAVE__S1_S2 = 1022; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¿Ã�Â¾Ã�Â´Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»:
    // +$s1 $s2.
    public static final int THE_PET_GAVE_S1_ADENA = 1023; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»: $s1 Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_PET_PUT_ON_S1 = 1024; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ�Â»: $s1.
    public static final int THE_PET_TOOK_OFF_S1 = 1025; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â»:
    // $s1.
    public static final int THE_SUMMONED_MONSTER_GAVE_DAMAGE_OF_S1 = 1026; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â½Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°.
    public static final int THE_SUMMONED_MONSTER_RECEIVED_DAMAGE_OF_S2_CAUSED_BY_S1 = 1027; // $c1
    // Ã�Â½Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Âµ
    // $s2
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°.
    public static final int SUMMONED_MONSTERS_CRITICAL_HIT = 1028; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã�Â¹
    // Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°!
    public static final int A_SUMMONED_MONSTER_USES_S1 = 1029; // Ã�â€™Ã�Â°Ã‘Ë† Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€š:
    // $s1.
    public static final int _PARTY_INFORMATION_ = 1030; // <Ã�ËœÃ�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½ Ã�Â¾
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.>
    public static final int LOOTING_METHOD_FINDERS_KEEPERS = 1031; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â±Ã�Â¾Ã‘â‚¬
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�ï¿½Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â´Ã‘Ë†Ã�ÂµÃ�Â¼Ã‘Æ’
    public static final int LOOTING_METHOD_RANDOM = 1032; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â±Ã�Â¾Ã‘â‚¬ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Â¹Ã�Â½Ã�Â¾
    public static final int LOOTING_METHOD_RANDOM_INCLUDING_SPOIL = 1033; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â±Ã�Â¾Ã‘â‚¬
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Â¹Ã�Â½Ã�Â¾+Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int LOOTING_METHOD_BY_TURN = 1034; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â±Ã�Â¾Ã‘â‚¬ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Å¸Ã�Â¾ Ã�Â¾Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â¸
    public static final int LOOTING_METHOD_BY_TURN_INCLUDING_SPOIL = 1035; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â±Ã�Â¾Ã‘â‚¬
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²:
    // Ã�Å¸Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â¸+Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED = 1036; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾.
    public static final int S1_MANUFACTURED_S2 = 1037; // $c1 Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š: $s2.
    public static final int S1_MANUFACTURED_S3_S2_S = 1038; // $c1 Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š: $s2
    // ($s3 Ã‘Ë†Ã‘â€š.).
    public static final int ITEMS_LEFT_AT_THE_CLAN_HALL_WAREHOUSE_CAN_ONLY_BE_RETRIEVED_BY_THE_CLAN_LEADER_DO_YOU_WANT_TO_CONTINUE = 1039; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int PACKAGES_SENT_CAN_ONLY_BE_RETRIEVED_AT_THIS_WAREHOUSE_DO_YOU_WANT_TO_CONTINUE = 1040; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½
    // Ã�Â»Ã‘Å½Ã�Â±Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘ï¿½Ã�ÂºÃ�Â»Ã�Â°Ã�Â´Ã�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_NEXT_SEED_PURCHASE_PRICE_IS_S1_ADENA = 1041; // Ã�Â¦Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½Ã�Â½
    // Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã‘â€šÃ‘â‚¬Ã�Â°:
    // $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_NEXT_FARM_GOODS_PURCHASE_PRICE_IS_S1_ADENA = 1042; // Ã�Â¦Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã�Â»Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã‘â€šÃ‘â‚¬Ã�Â°:
    // $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int AT_THE_CURRENT_TIME_THE__UNSTUCK_COMMAND_CANNOT_BE_USED_PLEASE_SEND_IN_A_PETITION = 1043; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘Æ’
    // "/unstuck".
    // Ã�Å¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int MONSTER_RACE_PAYOUT_INFORMATION_IS_NOT_AVAILABLE_WHILE_TICKETS_ARE_BEING_SOLD = 1044; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â¸
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã‘Æ’Ã�Â·Ã�Â½Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘Æ’Ã�Â¼Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¾Ã�Âº
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int NOT_CURRENTLY_PREPARING_FOR_A_MONSTER_RACE = 1045; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int MONSTER_RACE_TICKETS_ARE_NO_LONGER_AVAILABLE = 1046; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â°
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int WE_DID_NOT_SUCCEED_IN_PRODUCING_S1_ITEM = 1047; // Ã�ËœÃ�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s1
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int WHISPERING_IS_NOT_POSSIBLE_IN_STATE_OF_OVERALL_BLOCKING = 1048; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int IT_IS_NOT_POSSIBLE_TO_MAKE_INVITATIONS_FOR_ORGANIZING_PARTIES_IN_STATE_OF_OVERALL_BLOCKING = 1049; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int THERE_ARE_NO_COMMUNITIES_IN_MY_CLAN_CLAN_COMMUNITIES_ARE_ALLOWED_FOR_CLANS_WITH_SKILL_LEVELS_OF_2_AND_HIGHER = 1050; // Ã�â€™
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²
    // 2-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ.
    public static final int PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW = 1051; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’Ã�Â³Ã�Â¸
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã�Â¼
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘Æ’Ã�Â¼Ã�Â¼Ã‘Æ’
    // Ã�Â²
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â¾
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘Ë†Ã�Â½Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â´Ã�Â½Ã‘ï¿½.
    public static final int THE_CLAN_HALL_FEE_IS_ONE_WEEK_OVERDUE_THEREFORE_THE_CLAN_HALL_OWNERSHIP_HAS_BEEN_REVOKED = 1052; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½
    // Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â´Ã�Â¾Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã‘Å½,
    // Ã�Â²Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã‘â€¡Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â¼
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int IT_IS_IMPOSSIBLE_TO_BE_RESSURECTED_IN_BATTLEFIELDS_WHERE_SIEGE_WARS_ARE_IN_PROCESS = 1053; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_HAVE_ENTERED_A_LAND_WITH_MYSTERIOUS_POWERS = 1054; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â¼Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾.
    public static final int YOU_HAVE_LEFT_THE_LAND_WHICH_HAS_MYSTERIOUS_POWERS = 1055; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Â¼Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°.
    public static final int YOU_HAVE_EXCEEDED_THE_CASTLES_STORAGE_LIMIT_OF_ADENA = 1056; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int THIS_COMMAND_CAN_ONLY_BE_USED_IN_THE_RELAX_SERVER = 1057; // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Âµ
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â´Ã‘â€¹Ã‘â€¦Ã�Â°.
    public static final int THE_SALES_AMOUNT_OF_SEEDS_IS_S1_ADENA = 1058; // Ã�Â¦Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸:
    // $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_REMAINING_PURCHASING_AMOUNT_IS_S1_ADENA = 1059; // Ã�ï¿½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ‘Æ’
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_REMAINDER_AFTER_SELLING_THE_SEEDS_IS_S1 = 1060; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â¸
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½Ã�Â½
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_RECIPE_CANNOT_BE_REGISTERED__YOU_DO_NOT_HAVE_THE_ABILITY_TO_CREATE_ITEMS = 1061; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int WRITING_SOMETHING_NEW_IS_POSSIBLE_AFTER_LEVEL_10 = 1062; // Ã�ï¿½Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾,
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â½Ã‘Æ’Ã�Â²
    // 10-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int PETITION_SERVICE_IS_NOT_AVAILABEL_FOR_S1_TO_S2_IN_CASE_OF_BEING_TRAPPED_IN_TERRITORY_WHERE_YOU_ARE_UNABLE_TO_MOVE_PLEASE_USE_THE__UNSTUCK_COMMAND = 1063; // Ã�â€™
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�â€™
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Âµ,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘ï¿½Ã‘â€šÃ‘Æ’Ã‘â‚¬Ã�Â°Ã‘â€¦,
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â¾Ã�Â¹
    // "/unstuck".
    public static final int EQUIPMENT_OF__S1_S2_HAS_BEEN_REMOVED = 1064; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â½Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // +$s1
    // $s2.
    public static final int WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM = 1065; // Ã�â€™
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int S1_HPS_HAVE_BEEN_RESTORED = 1066; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // $s1 Ã�ï¿½Ã�Â .
    public static final int XS2S_HP_HAS_BEEN_RESTORED_BY_S1 = 1067; // $c1
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã�Â¼ $s2
    // Ã�ï¿½Ã�Â .
    public static final int S1_MPS_HAVE_BEEN_RESTORED = 1068; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // $s1 MP.
    public static final int XS2S_MP_HAS_BEEN_RESTORED_BY_S1 = 1069; // $c1
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã�Â¼ $s2
    // MP.
    public static final int XYOU_DO_NOT_HAVE_XREADX_PERMISSION = 1070; // Ã�â€™Ã�Â°Ã�Â¼ Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // "Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â¸Ã‘â€šÃ�Â°Ã‘â€šÃ‘Å’".
    public static final int XYOU_DO_NOT_HAVE_XWRITEX_PERMISSION = 1071; // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // "Ã�Â½Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â°Ã‘â€šÃ‘Å’".
    public static final int YOU_HAVE_OBTAINED_A_TICKET_FOR_THE_MONSTER_RACE_S1__SINGLE = 1072; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1.
    public static final int YOU_HAVE_OBTAINED_A_TICKET_FOR_THE_MONSTER_RACE_S1__DOUBLE = 1073; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // $s1.
    public static final int YOU_DO_NOT_MEET_THE_AGE_REQUIREMENT_TO_PURCHASE_A_MONSTER_RACE_TICKET = 1074; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â±Ã�Â¸Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘Æ’.
    public static final int THE_GAME_CANNOT_BE_TERMINATED = 1076; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸ Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int A_GAMEGUARD_EXECUTION_ERROR_HAS_OCCURRED_PLEASE_SEND_THE_ERL_FILE_S_LOCATED_IN_THE_GAMEGUARD_FOLDER_TO_GAME = 1077; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // GameGuard.
    // Ã�Å¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€šÃ�Âµ.
    public static final int WHEN_A_USERS_KEYBOARD_INPUT_EXCEEDS_A_CERTAIN_CUMULATIVE_SCORE_A_CHAT_BAN_WILL_BE_APPLIED_THIS_IS_DONE_TO_DISCOURAGE_SPAMMING_PLEASE_AVOID_POSTING_THE_SAME_MESSAGE_MULTIPLE_TIMES_DURING_A_SHORT_PERIOD = 1078; // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾Ã�ÂºÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘â€žÃ‘â‚¬Ã�Â°Ã�Â·
    // Ã�Â²Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    // Ã�â€˜Ã‘Æ’Ã�Â´Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾Ã�ÂºÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â¶Ã�Âµ
    // Ã‘â€žÃ‘â‚¬Ã�Â°Ã�Â·Ã‘â€¹.
    public static final int THE_TARGET_IS_CURRENTLY_BANNED_FROM_CHATTING = 1079; // Ã�Â£
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�ÂµÃ�Â´Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int DO_YOU_WISH_TO_USE_THE_FACELIFTING_POTION_X_TYPE_A_IT_IS_PERMANENT = 1080; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�ï¿½?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int DO_YOU_WISH_TO_USE_THE_DYE_POTION_X_TYPE_A_IT_IS_PERMANENT = 1081; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�ï¿½?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int DO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_X_TYPE_A_IT_IS_PERMANENT = 1082; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�ï¿½?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int THE_FACELIFTING_POTION__TYPE_A_IS_BEING_USED = 1083; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�ï¿½.
    public static final int THE_DYE_POTION__TYPE_A_IS_BEING_USED = 1084; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�ï¿½.
    public static final int THE_HAIR_STYLE_CHANGE_POTION__TYPE_A_IS_BEING_USED = 1085; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�ï¿½.
    public static final int YOUR_FACIAL_APPEARANCE_HAS_BEEN_CHANGED = 1086; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�Â»Ã�Â¸Ã‘â€ Ã�Â¾
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int YOUR_HAIR_COLOR_HAS_BEEN_CHANGED = 1087; // Ã�Â¦Ã�Â²Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int YOUR_HAIR_STYLE_HAS_BEEN_CHANGED = 1088; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int S1_HAS_OBTAINED_A_FIRST_ANNIVERSARY_COMMEMORATIVE_ITEM = 1089; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Âº
    // Ã�Â¿Ã�Â¾
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘Å½
    // I
    // Ã�Â³Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²Ã‘â€°Ã�Â¸Ã�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int DO_YOU_WISH_TO_USE_THE_FACELIFTING_POTION_X_TYPE_B_IT_IS_PERMANENT = 1090; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�â€™?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_FACELIFTING_POTION_X_TYPE_C_IT_IS_PERMANENT = 1091; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�Â¡?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_DYE_POTION_X_TYPE_B_IT_IS_PERMANENT = 1092; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�â€™?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_DYE_POTION_X_TYPE_C_IT_IS_PERMANENT = 1093; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // Ã�Â¡?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_DYE_POTION_X_TYPE_D_IT_IS_PERMANENT = 1094; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // D?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_X_TYPE_B_IT_IS_PERMANENT = 1095; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // B?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_X_TYPE_C_IT_IS_PERMANENT = 1096; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // C?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_X_TYPE_D_IT_IS_PERMANENT = 1097; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // D?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_X_TYPE_E_IT_IS_PERMANENT = 1098; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // E?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_X_TYPE_F_IT_IS_PERMANENT = 1099; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // F?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int XDO_YOU_WISH_TO_USE_THE_HAIR_STYLE_CHANGE_POTION_X_TYPE_G_IT_IS_PERMANENT = 1100; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // G?
    // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°.
    public static final int THE_FACELIFTING_POTION__TYPE_B_IS_BEING_USED = 1101; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // B.
    public static final int THE_FACELIFTING_POTION__TYPE_C_IS_BEING_USED = 1102; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // C.
    public static final int THE_DYE_POTION__TYPE_B_IS_BEING_USED = 1103; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // B.
    public static final int THE_DYE_POTION__TYPE_C_IS_BEING_USED = 1104; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // C.
    public static final int THE_DYE_POTION__TYPE_D_IS_BEING_USED = 1105; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // D.
    public static final int THE_HAIR_STYLE_CHANGE_POTION__TYPE_B_IS_BEING_USED = 1106; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // B.
    public static final int THE_HAIR_STYLE_CHANGE_POTION__TYPE_C_IS_BEING_USED = 1107; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // C.
    public static final int THE_HAIR_STYLE_CHANGE_POTION__TYPE_D_IS_BEING_USED = 1108; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // D.
    public static final int THE_HAIR_STYLE_CHANGE_POTION__TYPE_E_IS_BEING_USED = 1109; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // E.
    public static final int THE_HAIR_STYLE_CHANGE_POTION__TYPE_F_IS_BEING_USED = 1110; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // F.
    public static final int THE_HAIR_STYLE_CHANGE_POTION__TYPE_G_IS_BEING_USED = 1111; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â·Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // -
    // Ã‘â€šÃ�Â¸Ã�Â¿
    // G.
    public static final int THE_PRIZE_AMOUNT_FOR_THE_WINNER_OF_LOTTERY__S1__IS_S2_ADENA_WE_HAVE_S3_FIRST_PRIZE_WINNERS = 1112; // Ã�â€�Ã�Â¶Ã�ÂµÃ�ÂºÃ�Â¿Ã�Â¾Ã‘â€š
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¸
    // $s1
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã�Â»
    // $s2
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹:
    // $s3
    // Ã‘â€¡Ã�ÂµÃ�Â».
    public static final int THE_PRIZE_AMOUNT_FOR_LUCKY_LOTTERY__S1__IS_S2_ADENA_THERE_WAS_NO_FIRST_PRIZE_WINNER_IN_THIS_DRAWING_THEREFORE_THE_JACKPOT_WILL_BE_ADDED_TO_THE_NEXT_DRAWING = 1113; // Ã�â€�Ã�Â¶Ã�ÂµÃ�ÂºÃ�Â¿Ã�Â¾Ã‘â€š
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¸
    // $s1
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã�Â»
    // $s2
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã�Â»
    // Ã�Â½Ã�Â¸Ã�ÂºÃ‘â€šÃ�Â¾.
    // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â´Ã�Â¶Ã�ÂµÃ�ÂºÃ�Â¿Ã�Â¾Ã‘â€š
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â€¹Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã�Â»Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ�Âµ.
    public static final int YOUR_CLAN_MAY_NOT_REGISTER_TO_PARTICIPATE_IN_A_SIEGE_WHILE_UNDER_A_GRACE_PERIOD_OF_THE_CLANS_DISSOLUTION = 1114; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int INDIVIDUALS_MAY_NOT_SURRENDER_DURING_COMBAT = 1115; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¸Ã�Â½Ã�Â´Ã�Â¸Ã�Â²Ã�Â¸Ã�Â´Ã‘Æ’Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int ONE_CANNOT_LEAVE_ONES_CLAN_DURING_COMBAT = 1116; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int A_CLAN_MEMBER_MAY_NOT_BE_DISMISSED_DURING_COMBAT = 1117; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â½Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int PROGRESS_IN_A_QUEST_IS_POSSIBLE_ONLY_WHEN_YOUR_INVENTORYS_WEIGHT_AND_VOLUME_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY = 1118; // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€š,
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’
    // (Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Âµ
    // 80%).
    public static final int QUEST_WAS_AUTOMATICALLY_CANCELED_WHEN_YOU_ATTEMPTED_TO_SETTLE_THE_ACCOUNTS_OF_YOUR_QUEST_WHILE_YOUR_INVENTORY_EXCEEDED_80_PERCENT_OF_CAPACITY = 1119; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int YOU_ARE_STILL_IN_THE_CLAN = 1120; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â° Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int YOU_DO_NOT_HAVE_THE_RIGHT_TO_VOTE = 1121; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â²Ã‘â€¹Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â°Ã‘â€¦.
    public static final int THERE_IS_NO_CANDIDATE = 1122; // Ã�ï¿½Ã�ÂµÃ‘â€š Ã�ÂºÃ�Â°Ã�Â½Ã�Â´Ã�Â¸Ã�Â´Ã�Â°Ã‘â€šÃ�Â¾Ã�Â².
    public static final int WEIGHT_AND_VOLUME_LIMIT_HAS_BEEN_EXCEEDED_THAT_SKILL_IS_CURRENTLY_UNAVAILABLE = 1123; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â³Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�Â°.
    public static final int A_RECIPE_BOOK_MAY_NOT_BE_USED_WHILE_USING_A_SKILL = 1124; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â½Ã�Â¸Ã�Â³Ã‘Æ’
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€šÃ�Â¾Ã�Â²
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼.
    public static final int AN_ITEM_MAY_NOT_BE_CREATED_WHILE_ENGAGED_IN_TRADING = 1125; // Ã�â€™
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°.
    public static final int YOU_MAY_NOT_ENTER_A_NEGATIVE_NUMBER = 1126; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â€ Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘â€¡Ã�Â¸Ã‘ï¿½Ã�Â»Ã�Â¾.
    public static final int THE_REWARD_MUST_BE_LESS_THAN_10_TIMES_THE_STANDARD_PRICE = 1127; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â´Ã‘Æ’,
    // Ã�Â²
    // 10
    // Ã‘â‚¬Ã�Â°Ã�Â·
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã‘Å½Ã‘â€°Ã‘Æ’Ã‘Å½
    // Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘Æ’.
    public static final int A_PRIVATE_STORE_MAY_NOT_BE_OPENED_WHILE_USING_A_SKILL = 1128; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã�Â¸
    // Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹.
    public static final int THIS_IS_NOT_ALLOWED_WHILE_USING_A_FERRY = 1129; // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â»Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_HAVE_GIVEN_S1_DAMAGE_TO_YOUR_TARGET_AND_S2_DAMAGE_TO_THE_SERVITOR = 1130; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã�Â½Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // $s1
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â³Ã‘Æ’
    // Ã�Â¸
    // $s2
    // -
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Âµ.
    public static final int IT_IS_NOW_MIDNIGHT_AND_THE_EFFECT_OF_S1_CAN_BE_FELT = 1131; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã‘â€¡Ã‘Å’,
    // Ã�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€°Ã‘Æ’Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // $s1.
    public static final int IT_IS_DAWN_AND_THE_EFFECT_OF_S1_WILL_NOW_DISAPPEAR = 1132; // Ã�ï¿½Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾
    // Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¾,
    // Ã�Â¸
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // $s1
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¿Ã�Â°Ã�Â».
    public static final int SINCE_HP_HAS_DECREASED_THE_EFFECT_OF_S1_CAN_BE_FELT = 1133; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¼Ã�Â°Ã�Â»Ã�Â¾
    // HP.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€°Ã‘Æ’Ã‘â€°Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1.
    public static final int SINCE_HP_HAS_INCREASED_THE_EFFECT_OF_S1_WILL_DISAPPEAR = 1134; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // HP
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    // Ã�â€�Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�ÂºÃ‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int WHILE_YOU_ARE_ENGAGED_IN_COMBAT_YOU_CANNOT_OPERATE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP = 1135; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½Ã�Â¼Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int SINCE_THERE_WAS_AN_ACCOUNT_THAT_USED_THIS_IP_AND_ATTEMPTED_TO_LOG_IN_ILLEGALLY_THIS_ACCOUNT_IS_NOT_ALLOWED_TO_CONNECT_TO_THE_GAME_SERVER_FOR_S1_MINUTES_PLEASE_USE_ANOTHER_GAME_SERVER = 1136; // Ã�â€˜Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // IP,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬.
    public static final int S1_HARVESTED_S3_S2_S = 1137; // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š: $s2
    // $s3 Ã‘Ë†Ã‘â€šÃ‘Æ’Ã�Âº.
    public static final int S1_HARVESTED_S2_S = 1138; // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š: $s2.
    public static final int THE_WEIGHT_AND_VOLUME_LIMIT_OF_INVENTORY_MUST_NOT_BE_EXCEEDED = 1139; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â²Ã�ÂµÃ‘ï¿½Ã�Â°.
    public static final int WOULD_YOU_LIKE_TO_OPEN_THE_GATE = 1140; // Ã�â€™Ã‘â€¹ Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°?
    public static final int WOULD_YOU_LIKE_TO_CLOSE_THE_GATE = 1141; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°?
    public static final int SINCE_S1_ALREADY_EXISTS_NEARBY_YOU_CANNOT_SUMMON_IT_AGAIN = 1142; // $s1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â¾Ã�Â¼
    // Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã�Â¼Ã�Â¸,
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.
    public static final int SINCE_YOU_DO_NOT_HAVE_ENOUGH_ITEMS_TO_MAINTAIN_THE_SERVITORS_STAY_THE_SERVITOR_WILL_DISAPPEAR = 1143; // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â½Ã�ÂµÃ‘â€š,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int CURRENTLY_YOU_DONT_HAVE_ANYBODY_TO_CHAT_WITH_IN_THE_GAME = 1144; // Ã�ï¿½Ã�Â¸
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã�Â·
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â¸Ã‘â€¦
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int S2_HAS_BEEN_CREATED_FOR_S1_AFTER_THE_PAYMENT_OF_S3_ADENA_IS_RECEIVED = 1145; // Ã�ËœÃ�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1:
    // $s2
    // Ã�Â·Ã�Â°
    // $s3
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S1_CREATED_S2_AFTER_RECEIVING_S3_ADENA = 1146; // $c1
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2
    // Ã�Â·Ã�Â°
    // $s3
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S2_S3_HAVE_BEEN_CREATED_FOR_S1_AT_THE_PRICE_OF_S4_ADENA = 1147; // Ã�ËœÃ�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1:
    // $s2
    // ($s3
    // Ã‘Ë†Ã‘â€š.)
    // Ã�Â·Ã�Â°
    // $s4
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S1_CREATED_S2_S3_AT_THE_PRICE_OF_S4_ADENA = 1148; // $c1
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2
    // ($s3
    // Ã‘Ë†Ã‘â€š.)
    // Ã�Â·Ã�Â°
    // $s4
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_ATTEMPT_TO_CREATE_S2_FOR_S1_AT_THE_PRICE_OF_S3_ADENA_HAS_FAILED = 1149; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1:
    // $s2
    // Ã�Â·Ã�Â°
    // $s3
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S1_HAS_FAILED_TO_CREATE_S2_AT_THE_PRICE_OF_S3_ADENA = 1150; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2
    // Ã�Â·Ã�Â°
    // $s3
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S2_IS_SOLD_TO_S1_AT_THE_PRICE_OF_S3_ADENA = 1151; // $c1
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2
    // Ã�Â·Ã�Â°
    // $s3
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S2_S3_HAVE_BEEN_SOLD_TO_S1_FOR_S4_ADENA = 1152; // $c1
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2
    // ($s3
    // Ã‘Ë†Ã‘â€š.)
    // Ã�Â·Ã�Â°
    // $s4
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S2_HAS_BEEN_PURCHASED_FROM_S1_AT_THE_PRICE_OF_S3_ADENA = 1153; // Ã�Å¡Ã‘Æ’Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1:
    // $s2
    // Ã�Â·Ã�Â°
    // $s3
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S3_S2_HAS_BEEN_PURCHASED_FROM_S1_FOR_S4_ADENA = 1154; // Ã�Å¡Ã‘Æ’Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1:
    // $s2
    // ($s3
    // Ã‘Ë†Ã‘â€š.)
    // Ã�Â·Ã�Â°
    // $s4
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int _S2S3_HAS_BEEN_SOLD_TO_S1_AT_THE_PRICE_OF_S4_ADENA = 1155; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // $c1:
    // +$s2
    // $s3
    // Ã�Â·Ã�Â°
    // $s4
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int _S2S3_HAS_BEEN_PURCHASED_FROM_S1_AT_THE_PRICE_OF_S4_ADENA = 1156; // Ã�Å¡Ã‘Æ’Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1:
    // +$s2
    // $s3
    // Ã�Â·Ã�Â°
    // $s4
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int TRYING_ON_STATE_LASTS_FOR_ONLY_5_SECONDS_WHEN_A_CHARACTERS_STATE_CHANGES_IT_CAN_BE_CANCELLED = 1157; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�ÂºÃ�Â°
    // Ã‘ï¿½Ã�Â½Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // 10
    // Ã‘ï¿½Ã�ÂµÃ�ÂºÃ‘Æ’Ã�Â½Ã�Â´.
    // Ã�â€™
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�ÂºÃ�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_GET_DOWN_FROM_A_PLACE_THAT_IS_TOO_HIGH = 1158; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ‘â€¹.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_ARRIVE_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_10_MINUTES = 1159; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_5_MINUTES = 1160; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_1_MINUTE = 1161; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_15_MINUTES = 1162; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 15
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_10_MINUTES = 1163; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_5_MINUTES = 1164; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_GIRAN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_1_MINUTE = 1165; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_20_MINUTES = 1166; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 20
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_15_MINUTES = 1167; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 15
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_10_MINUTES = 1168; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_5_MINUTES = 1169; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_WILL_BE_ARRIVING_AT_GIRAN_HARBOR_IN_APPROXIMATELY_1_MINUTE = 1170; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_20_MINUTES = 1171; // Ã�ï¿½Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¿Ã‘Æ’Ã�Â½Ã�ÂºÃ‘â€š
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 20
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_15_MINUTES = 1172; // Ã�ï¿½Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¿Ã‘Æ’Ã�Â½Ã�ÂºÃ‘â€š
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 15
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_10_MINUTES = 1173; // Ã�ï¿½Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¿Ã‘Æ’Ã�Â½Ã�ÂºÃ‘â€š
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_5_MINUTES = 1174; // Ã�ï¿½Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¿Ã‘Æ’Ã�Â½Ã�ÂºÃ‘â€š
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_INNADRIL_PLEASURE_BOAT_WILL_ARRIVE_IN_APPROXIMATELY_1_MINUTE = 1175; // Ã�ï¿½Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¿Ã‘Æ’Ã�Â½Ã�ÂºÃ‘â€š
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THIS_IS_A_QUEST_EVENT_PERIOD = 1176; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°.
    public static final int THIS_IS_THE_SEAL_VALIDATION_PERIOD = 1177; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸.
    public static final int THIS_SEAL_PERMITS_THE_GROUP_THAT_HOLDS_IT_TO_EXCLUSIVELY_ENTER_THE_DUNGEON_OPENED_BY_THE_SEAL_OF_AVARICE_DURING_THE_SEAL_VALIDATION_PERIOD__IT_ALSO_PERMITS_TRADING_WITH_THE_MERCHANT_OF_MAMMON_WHO_APPEARS_IN_SPECIAL_DUNGEONS_AND_PERMITS_MEETINGS_WITH_ANAKIM_OR_LILITH_IN_THE_DISCIPLES_NECROPOLIS = 1178; // Ã�Â­Ã‘â€šÃ�Â°
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã�ÂµÃ‘Å½,
    // Ã‘ï¿½Ã�ÂºÃ‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã�Â·Ã�Â¸Ã�Â²Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´
    // Ã�Â²
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â·Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ,
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�ï¿½Ã�Â»Ã‘â€¡Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹.
    // Ã�Â¢Ã�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â´Ã�ÂµÃ�Â»Ã�Â°
    // Ã‘ï¿½
    // Ã�Â¢Ã�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã‘â€ Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Å“Ã�Â°Ã�Â¼Ã�Â¼Ã�Â¾Ã�Â½Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€¦
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â·Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½Ã‘â€¦.
    // Ã�Â¢Ã�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�ï¿½Ã�Â½Ã�Â°Ã�ÂºÃ�Â¸Ã�Â¼Ã�Â¾Ã�Â¼
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�â€ºÃ�Â¸Ã�Â»Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã�ï¿½Ã�ÂµÃ�ÂºÃ‘â‚¬Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Âµ
    // Ã�ï¿½Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int THIS_SEAL_PERMITS_THE_GROUP_THAT_HOLDS_IT_TO_ENTER_THE_DUNGEON_OPENED_BY_THE_SEAL_OF_GNOSIS_USE_THE_TELEPORTATION_SERVICE_OFFERED_BY_THE_PRIEST_IN_THE_VILLAGE_AND_DO_BUSINESS_WITH_THE_MERCHANT_OF_MAMMON_THE_ORATOR_OF_REVELATIONS_APPEARS_AND_CASTS_GOOD_MAGIC_ON_THE_WINNERS_AND_THE_PREACHER_OF_DOOM_APPEARS_AND_CASTS_BAD_MAGIC_ON_THE_LOSERS = 1179; // Ã�Â­Ã‘â€šÃ�Â°
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã�ÂµÃ‘Å½,
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â¸Ã�ÂºÃ�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â·Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ,
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°Ã�Â¼Ã�Â¸
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¶Ã‘â‚¬Ã�ÂµÃ‘â€ 
    // Ã�Â²
    // Ã�Â´Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Âµ,
    // Ã�Â¸
    // Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â´Ã�ÂµÃ�Â»Ã�Â°
    // Ã‘ï¿½
    // Ã�Å¡Ã‘Æ’Ã�Â·Ã�Â½Ã�ÂµÃ‘â€ Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Å“Ã�Â°Ã�Â¼Ã�Â¼Ã�Â¾Ã�Â½Ã�Â°.
    // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Å¾Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¾Ã‘â‚¬
    // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã�Â¸Ã�Â½Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¼Ã�Â¸
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€šÃ�Â°Ã�Â¼Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â²Ã�ÂµÃ�Â´Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â¡Ã‘Æ’Ã�Â´Ã‘Å’Ã�Â±Ã‘â€¹
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã�Â¸Ã�Â½Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â€ Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¼Ã�Â¸
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€šÃ�Â°Ã�Â¼Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã‘â€¦.
    public static final int DURING_THE_SEAL_VALIDATION_PERIOD_THE_COSTS_OF_CASTLE_DEFENSE_MERCENARIES_AND_RENOVATIONS_BASIC_P_DEF_OF_CASTLE_GATES_AND_CASTLE_WALLS_AND_MAXIMUM_TAX_RATES_WILL_ALL_CHANGE_TO_FAVOR_THE_GROUP_OF_FIGHTERS_THAT_POSSESSES_THIS_SEAL = 1180; // Ã�â€™
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã‘Æ’Ã�Â¼
    // CP
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    // Ã�Å¡Ã‘â‚¬Ã�Â¾Ã�Â¼Ã�Âµ
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�ÂºÃ�Â¸Ã�Â´Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°,
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â¸
    // Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â½
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â¤.
    // Ã�â€”Ã�Â°Ã‘â€°.
    // Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â½
    // Ã�Â¸
    // Ã�Â²Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â¸
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â½Ã�Â°Ã�Â»Ã�Â¾Ã�Â³Ã�Â¾Ã�Â².
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â¹
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°,
    // Ã‘â€šÃ�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â¾Ã�Â¼,
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ‘Æ’.
    public static final int DO_YOU_REALLY_WISH_TO_CHANGE_THE_TITLE = 1181; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â²Ã�Â¾Ã�Âº?
    public static final int DO_YOU_REALLY_WISH_TO_DELETE_THE_CLAN_CREST = 1182; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°?
    public static final int THIS_IS_THE_INITIAL_PERIOD = 1183; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�ÂºÃ�Â°.
    public static final int THIS_IS_A_PERIOD_OF_CALCULATIING_STATISTICS_IN_THE_SERVER = 1184; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â±Ã�Â¾Ã‘â‚¬
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¾
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Âµ.
    public static final int DAYS_LEFT_UNTIL_DELETION = 1185; // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹ Ã�Â´Ã�Â¾
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int TO_CREATE_A_NEW_ACCOUNT_PLEASE_VISIT_THE_PLAYNC_WEBSITE_HTTP___WWWPLAYNCCOM_US_SUPPORT = 1186; // Ã�ï¿½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€šÃ�Âµ.
    public static final int IF_YOU_HAVE_LOST_YOUR_ACCOUNT_INFORMATION_PLEASE_VISIT_THE_OFFICIAL_LINEAGE_II_SUPPORT_WEBSITE_AT_HTTP__SUPPORTPLAYNCCOM = 1187; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â¾Ã�Â³Ã�Â¸Ã�Â½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€šÃ�Âµ.
    public static final int THE_TEMPORARY_ALLIANCE_OF_THE_CASTLE_ATTACKER_TEAM_IS_IN_EFFECT_IT_WILL_BE_DISSOLVED_WHEN_THE_CASTLE_LORD_IS_REPLACED = 1189; // Ã�Â¡Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â¾Ã�Â·Ã‘ï¿½Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½.
    public static final int THE_TEMPORARY_ALLIANCE_OF_THE_CASTLE_ATTACKER_TEAM_HAS_BEEN_DISSOLVED = 1190; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½,
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°,
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_10_MINUTES = 1191; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â¾
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_5_MINUTES = 1192; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â¾
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_TALKING_ISLAND_IN_APPROXIMATELY_1_MINUTE = 1193; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â¾
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int A_MERCENARY_CAN_BE_ASSIGNED_TO_A_POSITION_FROM_THE_BEGINNING_OF_THE_SEAL_VALIDATION_PERIOD_UNTIL_THE_TIME_WHEN_A_SIEGE_STARTS = 1194; // Ã�ï¿½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â´Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int THIS_MERCENARY_CANNOT_BE_ASSIGNED_TO_A_POSITION_BY_USING_THE_SEAL_OF_STRIFE = 1195; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â Ã�Â°Ã�Â·Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int YOUR_FORCE_HAS_REACHED_MAXIMUM_CAPACITY = 1196; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã‘Å½
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’
    // Ã�Â´Ã�Â¾
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�Â°.
    public static final int SUMMONING_A_SERVITOR_COSTS_S2_S1 = 1197; // Ã�â€™
    // Ã�ÂºÃ�Â°Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘â€¹ Ã�Â·Ã�Â°
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â²Ã�Â·Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // $s1 Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ
    // $s2 Ã‘Ë†Ã‘â€š.
    public static final int THE_ITEM_HAS_BEEN_SUCCESSFULLY_CRYSTALLIZED = 1198; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â¸Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int _CLAN_WAR_TARGET_ = 1199; // =======<CLAN_WAR_TARGET>=======
    public static final int S1_S2_ALLIANCE = 1200; // = $s1 (Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½ $s2)
    public static final int PLEASE_SELECT_THE_QUEST_YOU_WISH_TO_QUIT = 1201; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€š,
    // Ã�Â¾Ã‘â€š
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int S1_NO_ALLIANCE_EXISTS = 1202; // = $s1 (Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½ $s2)
    public static final int THERE_IS_NO_CLAN_WAR_IN_PROGRESS = 1203; // Ã�â€™ Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â²Ã‘â€¹ Ã�Â½Ã�Âµ
    // Ã�Â²Ã�Â¾Ã‘Å½Ã�ÂµÃ‘â€šÃ�Âµ Ã‘ï¿½
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°Ã�Â¼Ã�Â¸.
    public static final int THE_SCREENSHOT_HAS_BEEN_SAVED_S1_S2XS3 = 1204; // Ã�Â¡Ã�ÂºÃ‘â‚¬Ã�Â¸Ã�Â½Ã‘Ë†Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½.
    // ($s1
    // $s2x$s3)
    public static final int MAILBOX_IS_FULL100_MESSAGE_MAXIMUM = 1205; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¿Ã�Â¾Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Âº
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â² Ã�Â½Ã�ÂµÃ�Â¼
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 100
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int MEMO_BOX_IS_FULL_100_MEMO_MAXIMUM = 1206; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â½.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â² Ã�Â½Ã�ÂµÃ�Â¼
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 100
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂµÃ�Â¹.
    public static final int PLEASE_MAKE_AN_ENTRY_IN_THE_FIELD = 1207; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ.
    public static final int S1_DIED_AND_DROPPED_S3_S2 = 1208; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶ $c1
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â¸Ã�Â± Ã�Â¸ Ã�Â¿Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â»
    // $s2 Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ $s3
    // Ã‘Ë†Ã‘â€š.
    public static final int CONGRATULATIONS_YOUR_RAID_WAS_SUCCESSFUL = 1209; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼
    // Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã‘â‚¬Ã�ÂµÃ�Â¹Ã�Â´Ã�Â¾Ã�Â¼!
    public static final int SEVEN_SIGNS_THE_QUEST_EVENT_PERIOD_HAS_BEGUN_VISIT_A_PRIEST_OF_DAWN_OR_DUSK_TO_PARTICIPATE_IN_THE_EVENT = 1210; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å¡Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â¼,
    // Ã�Â¿Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã‘ï¿½Ã�Â¾
    // Ã�â€“Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�Â¾Ã�Â¼
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�â€“Ã‘â‚¬Ã�Â¸Ã‘â€ Ã�ÂµÃ�Â¹
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°.
    public static final int SEVEN_SIGNS_THE_QUEST_EVENT_PERIOD_HAS_ENDED_THE_NEXT_QUEST_EVENT_WILL_START_IN_ONE_WEEK = 1211; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å¡Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    // Ã�Â¡Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¹Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã‘Å½.
    public static final int SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_AVARICE = 1212; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�â€ºÃ�Â¾Ã‘â‚¬Ã�Â´Ã‘â€¹
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â»Ã‘â€¡Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_GNOSIS = 1213; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�â€ºÃ�Â¾Ã‘â‚¬Ã�Â´Ã‘â€¹
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_OBTAINED_THE_SEAL_OF_STRIFE = 1214; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�â€ºÃ�Â¾Ã‘â‚¬Ã�Â´Ã‘â€¹
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â Ã�Â°Ã�Â·Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â°.
    public static final int SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_AVARICE = 1215; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â»Ã‘â€¡Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_GNOSIS = 1216; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_OBTAINED_THE_SEAL_OF_STRIFE = 1217; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â Ã�Â°Ã�Â·Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â°.
    public static final int SEVEN_SIGNS_THE_SEAL_VALIDATION_PERIOD_HAS_BEGUN = 1218; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int SEVEN_SIGNS_THE_SEAL_VALIDATION_PERIOD_HAS_ENDED = 1219; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int ARE_YOU_SURE_YOU_WISH_TO_SUMMON_IT = 1220; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾?
    public static final int DO_YOU_REALLY_WISH_TO_RETURN_IT = 1221; // Ã�â€™Ã‘â€¹ Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾?
    public static final int CURRENT_LOCATION_S1_S2_S3_GM_CONSULTATION_SERVICE = 1222; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ:
    // $s1,
    // $s2,
    // $s3
    // (Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸)
    public static final int WE_DEPART_FOR_TALKING_ISLAND_IN_FIVE_MINUTES = 1223; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â´Ã�Â¾
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int WE_DEPART_FOR_TALKING_ISLAND_IN_ONE_MINUTE = 1224; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â´Ã�Â¾
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int ALL_ABOARD_FOR_TALKING_ISLAND = 1225; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’ Ã�Â´Ã�Â¾
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½!
    // Ã�â€™Ã‘ï¿½Ã�Âµ Ã�Â½Ã�Â°
    // Ã�Â±Ã�Â¾Ã‘â‚¬Ã‘â€š!
    public static final int WE_ARE_NOW_LEAVING_FOR_TALKING_ISLAND = 1226; // Ã�Å“Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã‘Æ’.
    public static final int YOU_HAVE_S1_UNREAD_MESSAGES = 1227; // Ã�ï¿½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½:
    // $c1.
    public static final int S1_HAS_BLOCKED_YOU_YOU_CANNOT_SEND_MAIL_TO_S1_ = 1228; // $c1
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½.
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’.
    public static final int NO_MORE_MESSAGES_MAY_BE_SENT_AT_THIS_TIME_EACH_ACCOUNT_IS_ALLOWED_10_MESSAGES_PER_DAY = 1229; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾.
    // Ã�â€™
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 10
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂµÃ�Â¼.
    public static final int YOU_ARE_LIMITED_TO_FIVE_RECIPIENTS_AT_A_TIME = 1230; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã‘Æ’Ã�Â¼
    // 5
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°Ã�Â¼.
    public static final int YOUVE_SENT_MAIL = 1231; // Ã�Å¸Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾ Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int THE_MESSAGE_WAS_NOT_SENT = 1232; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â° Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â°.
    public static final int YOUVE_GOT_MAIL = 1233; // Ã�Å¸Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾ Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int THE_MAIL_HAS_BEEN_STORED_IN_YOUR_TEMPORARY_MAILBOX = 1234; // Ã�Å¸Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã�ÂºÃ�Â°Ã‘â€¦.
    public static final int DO_YOU_WISH_TO_DELETE_ALL_YOUR_FRIENDS = 1235; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹?
    public static final int PLEASE_ENTER_SECURITY_CARD_NUMBER = 1236; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ‘â€¹
    // Ã�Â±Ã�ÂµÃ�Â·Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int PLEASE_ENTER_THE_CARD_NUMBER_FOR_NUMBER_S1 = 1237; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ‘â€¹
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // $s1.
    public static final int YOUR_TEMPORARY_MAILBOX_IS_FULL_NO_MORE_MAIL_CAN_BE_STORED_10_MESSAGE_LIMIT = 1238; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â½.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã�ÂºÃ�Â°Ã‘â€¦
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 10
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int LOADING_OF_THE_KEYBOARD_SECURITY_MODULE_HAS_FAILED_PLEASE_EXIT_THE_GAME_AND_RELOAD = 1239; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂºÃ�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â´Ã‘Æ’Ã�Â»Ã‘ï¿½
    // Ã�Â±Ã�ÂµÃ�Â·Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â²Ã�Â²Ã�Â¾Ã�Â´Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â²Ã�Â¸Ã�Â°Ã‘â€šÃ‘Æ’Ã‘â‚¬Ã‘Æ’.
    // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int SEVEN_SIGNS_THE_REVOLUTIONARIES_OF_DUSK_HAVE_WON = 1240; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°.
    public static final int SEVEN_SIGNS_THE_LORDS_OF_DAWN_HAVE_WON = 1241; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�â€ºÃ�Â¾Ã‘â‚¬Ã�Â´Ã‘â€¹
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int USERS_WHO_HAVE_NOT_VERIFIED_THEIR_AGE_CANNOT_LOG_IN_BETWEEN_1000_PM_AND_600_AM = 1242; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�Â¸,
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â´Ã�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 18
    // Ã�Â»Ã�ÂµÃ‘â€š,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã‘ï¿½
    // 22:00
    // Ã�Â´Ã�Â¾
    // 6:00.
    public static final int THE_SECURITY_CARD_NUMBER_IS_INVALID = 1243; // Ã�ï¿½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ‘â€¹
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â½.
    public static final int USERS_WHO_HAVE_NOT_VERIFIED_THEIR_AGE_CANNOT_LOG_IN_BETWEEN_1000_PM_AND_600_AM_LOGGING_OFF = 1244; // Ã�Å¸Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹,
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â´Ã�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã‘â€¦
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 18
    // Ã�Â»Ã�ÂµÃ‘â€š,
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½
    // 22:00
    // Ã�Â´Ã�Â¾
    // 6:00.
    // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_WILL_BE_LOGGED_OUT_IN_S1_MINUTES = 1245; // Ã�â€�Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int S1_DIED_AND_HAS_DROPPED_S2_ADENA = 1246; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â¸Ã�Â±Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸ Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂµÃ‘â€š
    // $s2
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_CORPSE_IS_TOO_OLD_THE_SKILL_CANNOT_BE_USED = 1247; // Ã�Å“Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â¸Ã�Â±
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â´Ã�Â°Ã�Â²Ã�Â½Ã�Â¾,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼.
    public static final int YOU_ARE_OUT_OF_FEED_MOUNT_STATUS_CANCELED = 1248; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¼,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â°
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã�Â¾Ã�Â¼
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�ÂºÃ‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_MAY_ONLY_RIDE_A_WYVERN_WHILE_YOURE_RIDING_A_STRIDER = 1249; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¸Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�ÂµÃ�Â·Ã�Â´Ã‘â€¹
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘â‚¬Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã�Âµ.
    public static final int DO_YOU_REALLY_WANT_TO_SURRENDER_IF_YOU_SURRENDER_DURING_AN_ALLIANCE_WAR_YOUR_EXP_WILL_DROP_AS_MUCH_AS_WHEN_YOUR_CHARACTER_DIES_ONCE = 1250; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½?
    // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�ÂºÃ�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã‘ï¿½Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°,
    // Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸.
    public static final int ARE_YOU_SURE_YOU_WANT_TO_DISMISS_THE_ALLIANCE_IF_YOU_USE_THE__ALLYDISMISS_COMMAND_YOU_WILL_NOT_BE_ABLE_TO_ACCEPT_ANOTHER_CLAN_TO_YOUR_ALLIANCE_FOR_ONE_DAY = 1251; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½?
    // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 1
    // Ã�Â´Ã�Â½Ã‘ï¿½
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼.
    public static final int ARE_YOU_SURE_YOU_WANT_TO_SURRENDER_EXP_PENALTY_WILL_BE_THE_SAME_AS_DEATH = 1252; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½?
    // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�ÂºÃ�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã‘ï¿½Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°,
    // Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸.
    public static final int ARE_YOU_SURE_YOU_WANT_TO_SURRENDER_EXP_PENALTY_WILL_BE_THE_SAME_AS_DEATH_AND_YOU_WILL_NOT_BE_ALLOWED_TO_PARTICIPATE_IN_CLAN_WAR = 1253; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½?
    // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�ÂºÃ�Â°Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Æ’Ã�Â»Ã‘ï¿½Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°,
    // Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸,
    // Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    public static final int THANK_YOU_FOR_SUBMITTING_FEEDBACK = 1254; // Ã�Â¡Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â¸Ã�Â±Ã�Â¾
    // Ã�Â·Ã�Â° Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€š.
    public static final int GM_CONSULTATION_HAS_BEGUN = 1255; // Ã�ï¿½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘ï¿½Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½ Ã‘ï¿½Ã�Â¾
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã�Â¾Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int PLEASE_WRITE_THE_NAME_AFTER_THE_COMMAND = 1256; // Ã�ï¿½Ã�Â°Ã�Â¿Ã�Â¸Ã‘Ë†Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int THE_SPECIAL_SKILL_OF_A_SERVITOR_OR_PET_CANNOT_BE_REGISTERED_AS_A_MACRO = 1257; // Ã�Å¾Ã‘ï¿½Ã�Â¾Ã�Â±Ã‘â€¹Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�ÂµÃ�Â²
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€¹.
    public static final int S1_HAS_BEEN_CRYSTALLIZED = 1258; // $s1:
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â¸Ã�Â·Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â° Ã‘Æ’Ã�Â´Ã�Â°Ã‘â€¡Ã�Â½Ã�Â¾.
    public static final int _ALLIANCE_TARGET_ = 1259; // =======<ALLIANCE_TARGET>=======
    public static final int SEVEN_SIGNS_PREPARATIONS_HAVE_BEGUN_FOR_THE_NEXT_QUEST_EVENT = 1260; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Âº
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂºÃ‘â‚¬Ã‘Æ’Ã�Â³Ã‘Æ’.
    public static final int SEVEN_SIGNS_THE_QUEST_EVENT_PERIOD_HAS_BEGUN_SPEAK_WITH_A_PRIEST_OF_DAWN_OR_DUSK_PRIESTESS_IF_YOU_WISH_TO_PARTICIPATE_IN_THE_EVENT = 1261; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�ËœÃ�Â²Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾
    // Ã�â€“Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�Â¾Ã�Â¼
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�â€“Ã‘â‚¬Ã�Â¸Ã‘â€ Ã�ÂµÃ�Â¹
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°.
    public static final int SEVEN_SIGNS_QUEST_EVENT_HAS_ENDED_RESULTS_ARE_BEING_TALLIED = 1262; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�ËœÃ�Â²Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    // Ã�Â¡Ã�Â¾Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¸Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�ÂºÃ�Â¸.
    public static final int SEVEN_SIGNS_THIS_IS_THE_SEAL_VALIDATION_PERIOD_A_NEW_QUEST_EVENT_PERIOD_BEGINS_NEXT_MONDAY = 1263; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘Å’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹:
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸.
    // Ã�Â¡Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â´Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¸Ã�Âº.
    public static final int THIS_SOUL_STONE_CANNOT_CURRENTLY_ABSORB_SOULS_ABSORPTION_HAS_FAILED = 1264; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã‘Æ’.
    public static final int YOU_CANT_ABSORB_SOULS_WITHOUT_A_SOUL_STONE = 1265; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã‘Æ’,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸.
    public static final int THE_EXCHANGE_HAS_ENDED = 1266; // Ã�Å¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½ Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int YOUR_CONTRIBUTION_SCORE_IS_INCREASED_BY_S1 = 1267; // Ã�Â Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�Â²Ã�ÂºÃ�Â»Ã�Â°Ã�Â´Ã�Â°
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // $s1.
    public static final int DO_YOU_WISH_TO_ADD_S1_CLASS_AS_YOUR_SUB_CLASS = 1268; // $s1:
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½?
    public static final int THE_NEW_SUB_CLASS_HAS_BEEN_ADDED = 1269; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½.
    public static final int THE_TRANSFER_OF_SUB_CLASS_HAS_BEEN_COMPLETED = 1270; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½.
    public static final int DO_YOU_WISH_TO_PARTICIPATE_UNTIL_THE_NEXT_SEAL_VALIDATION_PERIOD_YOU_ARE_A_MEMBER_OF_THE_LORDS_OF_DAWN = 1271; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’?
    // Ã�â€�Ã�Â¾
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€¦
    // Ã�â€ºÃ�Â¾Ã‘â‚¬Ã�Â´Ã�Â¾Ã�Â²
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int DO_YOU_WISH_TO_PARTICIPATE_UNTIL_THE_NEXT_SEAL_VALIDATION_PERIOD_YOU_ARE_A_MEMBER_OF_THE_REVOLUTIONARIES_OF_DUSK = 1272; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’?
    // Ã�â€�Ã�Â¾
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€¦
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°.
    public static final int YOU_WILL_PARTICIPATE_IN_THE_SEVEN_SIGNS_AS_A_MEMBER_OF_THE_LORDS_OF_DAWN = 1273; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘ï¿½Ã‘â€¦
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�â€ºÃ�Â¾Ã‘â‚¬Ã�Â´Ã�Â¾Ã�Â²
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int YOU_WILL_PARTICIPATE_IN_THE_SEVEN_SIGNS_AS_A_MEMBER_OF_THE_REVOLUTIONARIES_OF_DUSK = 1274; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘ï¿½Ã‘â€¦
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°.
    public static final int YOUVE_CHOSEN_TO_FIGHT_FOR_THE_SEAL_OF_AVARICE_DURING_THIS_QUEST_EVENT_PERIOD = 1275; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â»Ã‘â€¡Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int YOUVE_CHOSEN_TO_FIGHT_FOR_THE_SEAL_OF_GNOSIS_DURING_THIS_QUEST_EVENT_PERIOD = 1276; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOUVE_CHOSEN_TO_FIGHT_FOR_THE_SEAL_OF_STRIFE_DURING_THIS_QUEST_EVENT_PERIOD = 1277; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â Ã�Â°Ã�Â·Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â°.
    public static final int THE_NPC_SERVER_IS_NOT_OPERATING = 1278; // Ã�Â¡Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // NPC
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½.
    public static final int CONTRIBUTION_LEVEL_HAS_EXCEEDED_THE_LIMIT_YOU_MAY_NOT_CONTINUE = 1279; // Ã�â€˜Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�ÂµÃ�Â½
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â²Ã�ÂºÃ�Â»Ã�Â°Ã�Â´Ã�Â°.
    public static final int MAGIC_CRITICAL_HIT = 1280; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã�Â¹ Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã�ÂµÃ�Â¹!
    public static final int YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS = 1281; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â¸Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼.
    public static final int YOUR_KARMA_HAS_BEEN_CHANGED_TO_S1 = 1282; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã�ÂºÃ�Â°Ã‘â‚¬Ã�Â¼Ã�Â°
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â½Ã�Â° $s1.
    public static final int THE_MINIMUM_FRAME_OPTION_HAS_BEEN_ACTIVATED = 1283; // Ã�â€˜Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â°
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â´Ã�ÂµÃ‘â€šÃ�Â°Ã�Â»Ã�Â¸Ã�Â·Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    public static final int THE_MINIMUM_FRAME_OPTION_HAS_BEEN_DEACTIVATED = 1284; // Ã�ï¿½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â°
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â´Ã�ÂµÃ‘â€šÃ�Â°Ã�Â»Ã�Â¸Ã�Â·Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int NO_INVENTORY_EXISTS_YOU_CANNOT_PURCHASE_AN_ITEM = 1285; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int UNTIL_NEXT_MONDAY_AT_120_AM = 1286; // (Ã�â€�Ã�Â¾ 18:00
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°)
    public static final int UNTIL_TODAY_AT_120_AM = 1287; // (Ã�â€�Ã�Â¾ 18:00)
    public static final int IF_TRENDS_CONTINUE_S1_WILL_WIN_AND_THE_SEAL_WILL_BELONG_TO = 1288; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â·Ã�Â°
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€š
    // $s1,
    // Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€š:
    public static final int SINCE_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_10_PERCENT_OR_MORE_PEOPLE_HAVE_VOTED = 1289; // Ã�Â¢Ã�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 10%
    // Ã�Â»Ã‘Å½Ã�Â´Ã�ÂµÃ�Â¹
    public static final int ALTHOUGH_THE_SEAL_WAS_NOT_OWNED_SINCE_35_PERCENT_OR_MORE_PEOPLE_HAVE_VOTED = 1290; // Ã�Â¥Ã�Â¾Ã‘â€šÃ‘ï¿½
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã‘â€¹Ã�Â¹
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 35%
    // Ã�Â»Ã‘Å½Ã�Â´Ã�ÂµÃ�Â¹
    public static final int ALTHOUGH_THE_SEAL_WAS_OWNED_DURING_THE_PREVIOUS_PERIOD_BECAUSE_LESS_THAN_10_PERCENT_OF_PEOPLE_HAVE_VOTED = 1291; // Ã�Â¢Ã�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Âµ
    // 10%
    // Ã�Â»Ã‘Å½Ã�Â´Ã�ÂµÃ�Â¹
    public static final int SINCE_THE_SEAL_WAS_NOT_OWNED_DURING_THE_PREVIOUS_PERIOD_AND_SINCE_LESS_THAN_35_PERCENT_OF_PEOPLE_HAVE_VOTED = 1292; // Ã�Â¥Ã�Â¾Ã‘â€šÃ‘ï¿½
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã‘â€¹Ã�Â¹
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Âµ
    // 35%
    // Ã�Â»Ã‘Å½Ã�Â´Ã�ÂµÃ�Â¹
    public static final int IF_CURRENT_TRENDS_CONTINUE_IT_WILL_END_IN_A_TIE = 1293; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â·Ã�Â°
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â¸Ã‘â€¡Ã‘Å’Ã‘ï¿½.
    public static final int SINCE_THE_COMPETITION_HAS_ENDED_IN_A_TIE_THE_SEAL_WILL_NOT_BE_AWARDED = 1294; // Ã�Â¡Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â½Ã�Â¸Ã‘â€¡Ã‘Å’Ã‘Å½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€š
    // Ã�Â½Ã�Â¸Ã�ÂºÃ‘â€šÃ�Â¾.
    public static final int SUB_CLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE = 1295; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int A_PRIVATE_STORE_MAY_NOT_BE_OPENED_IN_THIS_AREA = 1296; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int A_PRIVATE_WORKSHOP_MAY_NOT_BE_OPENED_IN_THIS_AREA = 1297; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int EXITING_THE_MONSTER_RACE_TRACK = 1298; // Ã�Å¸Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¶Ã�ÂµÃ�Â»Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Â¸ Ã‘ï¿½
    // Ã�Â¸Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â´Ã‘â‚¬Ã�Â¾Ã�Â¼Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â².
    public static final int S1S_CASTING_HAS_BEEN_INTERRUPTED = 1299; // Ã�Â§Ã‘â€šÃ�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã�Â¸Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // $c1 Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â°Ã�Â½Ã�Â¾.
    public static final int TRYING_ON_MODE_CANCELED = 1300; // Ã�Â Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼ Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�ÂºÃ�Â¸
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int CAN_BE_USED_ONLY_BY_THE_LORDS_OF_DAWN = 1301; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Âº
    // Ã�â€ºÃ�Â¾Ã‘â‚¬Ã�Â´Ã�Â°Ã�Â¼
    // Ã�Â Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int CAN_BE_USED_ONLY_BY_THE_REVOLUTIONARIES_OF_DUSK = 1302; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Âº
    // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã�Â¼
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°.
    public static final int USED_ONLY_DURING_A_QUEST_EVENT_PERIOD = 1303; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°.
    public static final int DUE_TO_THE_INFLUENCE_OF_THE_SEAL_OF_STRIFE_ALL_DEFENSIVE_REGISTRATION_HAS_BEEN_CANCELED_EXCEPT_BY_ALLIANCES_OF_CASTLE_OWNING_CLANS = 1304; // Ã�Å¸Ã�Â¾Ã�Â´
    // Ã�Â²Ã�Â»Ã�Â¸Ã‘ï¿½Ã�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â Ã�Â°Ã�Â·Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²-Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸.
    public static final int YOU_MAY_GIVE_SOMEONE_ELSE_A_SEAL_STONE_FOR_SAFEKEEPING_ONLY_DURING_A_QUEST_EVENT_PERIOD = 1305; // Ã�Å¡Ã�Â°Ã�Â¼Ã�Â½Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã�Â½Ã‘â€¹
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°.
    public static final int TRYING_ON_MODE_HAS_ENDED = 1306; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ACCOUNTS_MAY_ONLY_BE_SETTLED_DURING_THE_SEAL_VALIDATION_PERIOD = 1307; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¸Ã�Â¼
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸.
    public static final int CONGRATULATIONS_YOU_HAVE_TRANSFERRED_TO_A_NEW_CLASS = 1308; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼!
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½.
    public static final int THIS_OPTION_REQUIRES_THAT_THE_LATEST_VERSION_OF_MSN_MESSENGER_CLIENT_BE_INSTALLED_ON_YOUR_COMPUTER = 1309; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â°Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã�Â½Ã‘Å½Ã‘Å½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¸Ã‘Å½
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // MSN.
    public static final int FOR_FULL_FUNCTIONALITY_THE_LATEST_VERSION_OF_MSN_MESSENGER_CLIENT_MUST_BE_INSTALLED_ON_THE_USERS_COMPUTER = 1310; // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½Ã�Â¼Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // MSN
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ,
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã�Â½Ã‘Å½Ã‘Å½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¸Ã‘Å½
    // MSN
    // Messenger.
    public static final int PREVIOUS_VERSIONS_OF_MSN_MESSENGER_ONLY_PROVIDE_THE_BASIC_FEATURES_TO_CHAT_IN_THE_GAME_ADD_DELETE_CONTACTS_AND_OTHER_OPTIONS_ARENT_AVAILABLE = 1311; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¸Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // MSN,
    // Ã‘â€šÃ�Â¾
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�ÂµÃ‘ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’,
    // Ã�Â½Ã�Â¾
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’/Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�ÂµÃ�Â´Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ.
    public static final int THE_LATEST_VERSION_OF_MSN_MESSENGER_MAY_BE_OBTAINED_FROM_THE_MSN_WEB_SITE_ = 1312; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¸Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // MSN
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€š
    // http://messenger.msn.com.
    public static final int S1_TO_BETTER_SERVE_OUR_CUSTOMERS_ALL_CHAT_HISTORIES_ARE_STORED_AND_MAINTAINED_BY_NCSOFT_IF_YOU_DO_NOT_AGREE_TO_HAVE_YOUR_CHAT_RECORDS_STORED_CLOSE_THE_CHAT_WINDOW_NOW_FOR_MORE_INFORMATION_REGARDING_THIS_ISSUE_PLEASE_VISIT_OUR_HOME_PAGE_AT_WWWNCSOFTCOM = 1313; // $s1,
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘â€¹Ã‘â€¦
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³
    // NCSoft
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¸
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â¸Ã‘â€¦
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â²
    // Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼
    // $s2.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â¸Ã�Â°Ã�Â»Ã�Â¾Ã�Â³Ã�Â¾Ã�Â²,
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â‚¬Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€š
    // Ã�Â½Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¿Ã�Â°Ã�Â½Ã�Â¸Ã�Â¸.
    public static final int PLEASE_ENTER_THE_PASSPORT_ID_OF_THE_PERSON_YOU_WISH_TO_ADD_TO_YOUR_CONTACT_LIST = 1314; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�ÂºÃ�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã‘Å’Ã‘ï¿½.
    public static final int DELETING_A_CONTACT_WILL_REMOVE_THAT_CONTACT_FROM_MSN_MESSENGER_AS_WELL_THE_CONTACT_CAN_STILL_CHECK_YOUR_ONLINE_STATUS_AND_WILL_NOT_BE_BLOCKED_FROM_SENDING_YOU_A_MESSAGE = 1315; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�ÂµÃ�Â´Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°,
    // Ã�Â¾Ã�Â½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â¸Ã�Â·
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // MSN.
    // Ã�ï¿½Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿
    // Ã�Âº
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¾
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Æ’Ã‘ï¿½Ã�Âµ,
    // Ã�Â¸
    // Ã�Â¾Ã�Â½
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THE_CONTACT_WILL_BE_DELETED_AND_BLOCKED_FROM_YOUR_CONTACT_LIST = 1316; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€š
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int WOULD_YOU_LIKE_TO_DELETE_THIS_CONTACT = 1317; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€š?
    public static final int PLEASE_SELECT_THE_CONTACT_YOU_WANT_TO_BLOCK_OR_UNBLOCK = 1318; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’/Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’.
    public static final int PLEASE_SELECT_THE_NAME_OF_THE_CONTACT_YOU_WISH_TO_CHANGE_TO_ANOTHER_GROUP = 1319; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â°,
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int AFTER_SELECTING_THE_GROUP_YOU_WISH_TO_MOVE_YOUR_CONTACT_TO_PRESS_THE_OK_BUTTON = 1320; // Ã�â€™Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’,
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘Æ’Ã‘Å½
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€š,
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¼Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â½Ã�Â¾Ã�Â¿Ã�ÂºÃ‘Æ’
    // "Ã�Å¾Ã�Å¡".
    public static final int ENTER_THE_NAME_OF_THE_GROUP_YOU_WISH_TO_ADD = 1321; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘Æ’Ã‘Å½
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int SELECT_THE_GROUP_AND_ENTER_THE_NEW_NAME = 1322; // Ã�â€™Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘Æ’Ã‘Å½
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ.
    public static final int SELECT_THE_GROUP_YOU_WISH_TO_DELETE_AND_CLICK_THE_OK_BUTTON = 1323; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘Æ’Ã‘Å½
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’,
    // Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¼Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â½Ã�Â¾Ã�Â¿Ã�ÂºÃ‘Æ’
    // "Ã�Å¾Ã�Å¡".
    public static final int SIGNING_IN = 1324; // Ã�â€™Ã‘â€¦Ã�Â¾Ã�Â´ Ã�Â² Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã‘Æ’Ã¢â‚¬Â¦
    public static final int YOUVE_LOGGED_INTO_ANOTHER_COMPUTER_AND_BEEN_LOGGED_OUT_OF_THE_NET_MESSENGER_SERVICE_ON_THIS_COMPUTER = 1325; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¿Ã‘Å’Ã‘Å½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Âµ,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´
    // Ã�Â¸Ã�Â·
    // .NET
    // Messenger
    // Service.
    public static final int S1_ = 1326; // $s1:
    public static final int THE_FOLLOWING_MESSAGE_COULD_NOT_BE_DELIVERED = 1327; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾:
    public static final int MEMBERS_OF_THE_REVOLUTIONARIES_OF_DUSK_WILL_NOT_BE_RESURRECTED = 1328; // Ã�Å“Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¸
    // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â²Ã�Â¾Ã�Â·Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int YOU_ARE_CURRENTLY_BANNED_FROM_ACTIVITIES_RELATED_TO_THE_PRIVATE_STORE_AND_PRIVATE_WORKSHOP = 1329; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½Ã�Â¼Ã�Â¸
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int NO_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_MAY_BE_OPENED_FOR_S1_MINUTES = 1330; // Ã�â€ºÃ�Â¸Ã‘â€¡Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã‘â€¹
    // Ã�Â½Ã�Â°
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int ACTIVITIES_RELATED_TO_THE_PRIVATE_STORE_AND_PRIVATE_WORKSHOP_ARE_NOW_PERMITTED = 1331; // Ã�â€˜Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â°.
    public static final int ITEMS_MAY_NOT_BE_USED_AFTER_YOUR_CHARACTER_OR_PET_DIES = 1332; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²Ã‘â€¹.
    public static final int REPLAY_FILE_ISNT_ACCESSIBLE_VERIFY_THAT_REPLAYINI_FILE_EXISTS = 1333; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã‘â€¡Ã�Â¸Ã‘â€šÃ�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Replay.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Replay.ini.
    public static final int THE_NEW_CAMERA_DATA_HAS_BEEN_STORED = 1334; // Ã�Â¡Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â°Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€¹.
    public static final int THE_ATTEMPT_TO_STORE_THE_NEW_CAMERA_DATA_HAS_FAILED = 1335; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â°Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€¹.
    public static final int THE_REPLAY_FILE_HAS_BEEN_CORRUPTED_PLEASE_CHECK_THE_S1S2_FILE = 1336; // Ã�Â¤Ã�Â°Ã�Â¹Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â°,
    // $s1.$s2,
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â´Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã�ÂµÃ�Â³Ã�Â¾.
    public static final int REPLAY_MODE_WILL_BE_TERMINATED_DO_YOU_WISH_TO_CONTINUE = 1337; // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_TRANSFERRED_AT_ONE_TIME = 1338; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½
    // Ã‘â‚¬Ã�Â°Ã�Â·.
    public static final int ONCE_A_MACRO_IS_ASSIGNED_TO_A_SHORTCUT_IT_CANNOT_BE_RUN_AS_A_MACRO_AGAIN = 1339; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â²Ã�Â¸Ã‘Ë†Ã�Â°
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²,
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â¼
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THIS_SERVER_CANNOT_BE_ACCESSED_BY_THE_COUPON_YOU_ARE_USING = 1340; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¾Ã�Â½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THE_NAME_OR_E_MAIL_ADDRESS_YOU_ENTERED_IS_INCORRECT = 1341; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�ÂºÃ‘â€šÃ‘â‚¬Ã�Â¾Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â°Ã�Â´Ã‘â‚¬Ã�ÂµÃ‘ï¿½.
    public static final int YOU_ARE_ALREADY_LOGGED_IN = 1342; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã�Â¶Ã�Âµ Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int THE_PASSWORD_OR_E_MAIL_ADDRESS_YOU_ENTERED_IS_INCORRECT__YOUR_ATTEMPT_TO_LOG_INTO_NET_MESSENGER_SERVICE_HAS_FAILED = 1343; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�ÂºÃ‘â€šÃ‘â‚¬Ã�Â¾Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â°Ã�Â´Ã‘â‚¬Ã�ÂµÃ‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // .NET
    // Messenger
    // Service
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THE_SERVICE_YOU_REQUESTED_COULD_NOT_BE_LOCATED_AND_THEREFORE_YOUR_ATTEMPT_TO_LOG_INTO_THE_NET_MESSENGER_SERVICE_HAS_FAILED_PLEASE_VERIFY_THAT_YOU_ARE_CURRENTLY_CONNECTED_TO_THE_INTERNET = 1344; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã‘Æ’
    // .NET
    // Messenger
    // Service.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½
    // Ã�Â¸Ã�Â½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â½Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â¼.
    public static final int AFTER_SELECTING_A_CONTACT_NAME_CLICK_ON_THE_OK_BUTTON = 1345; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�ÂµÃ�Â´Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¼Ã�Â¸Ã‘â€šÃ�Âµ
    // "Ã�Å¾Ã�Å¡".
    public static final int YOU_ARE_CURRENTLY_ENTERING_A_CHAT_MESSAGE = 1346; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘â€¡Ã�Â°Ã‘â€š
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int THE_LINEAGE_II_MESSENGER_COULD_NOT_CARRY_OUT_THE_TASK_YOU_REQUESTED = 1347; // Lineage
    // II
    // Messenger
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½.
    public static final int S1_HAS_ENTERED_THE_CHAT_ROOM = 1348; // $s1 Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â²
    // Ã‘â€¡Ã�Â°Ã‘â€š.
    public static final int S1_HAS_LEFT_THE_CHAT_ROOM = 1349; // $s1 Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â¸Ã�Â·
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int THE_STATUS_WILL_BE_CHANGED_TO_INDICATE__OFF_LINE__ALL_THE_CHAT_WINDOWS_CURRENTLY_OPENED_WILL_BE = 1350; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Æ’Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // "Ã�Å¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½".
    // Ã�â€™Ã‘ï¿½Ã�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â°
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘â€¹.
    public static final int AFTER_SELECTING_THE_CONTACT_YOU_WANT_TO_DELETE_CLICK_THE_DELETE_BUTTON = 1351; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€š
    // Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¼Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â½Ã�Â¾Ã�Â¿Ã�ÂºÃ‘Æ’
    // "Ã�Â£Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’".
    public static final int YOU_HAVE_BEEN_ADDED_TO_THE_CONTACT_LIST_OF_S1_S2 = 1352; // $s1
    // ($s2)
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¾Ã�Â².
    public static final int YOU_CAN_SET_THE_OPTION_TO_SHOW_YOUR_STATUS_AS_ALWAYS_BEING_OFF_LINE_TO_ALL_OF_YOUR_CONTACTS = 1353; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Æ’Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // "Ã�Å¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½"
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ.
    public static final int YOU_ARE_NOT_ALLOWED_TO_CHAT_WITH_YOUR_CONTACT_WHILE_YOU_ARE_BLOCKED_FROM_CHATTING = 1354; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â±Ã‘â€°Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int THE_CONTACT_YOU_CHOSE_TO_CHAT_WITH_IS_CURRENTLY_BLOCKED_FROM_CHATTING = 1355; // Ã�Â£
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int THE_CONTACT_YOU_CHOSE_TO_CHAT_WITH_IS_NOT_CURRENTLY_LOGGED_IN = 1356; // Ã�â€™Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½.
    public static final int YOU_HAVE_BEEN_BLOCKED_FROM_THE_CONTACT_YOU_SELECTED = 1357; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°
    // Ã‘ï¿½
    // Ã�Â½Ã�Â¸Ã�Â¼.
    public static final int YOU_ARE_BEING_LOGGED_OUT = 1358; // Ã�â€™Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´ Ã�Â¸Ã�Â·
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã‘â€¹Ã¢â‚¬Â¦
    public static final int S1_HAS_LOGGED_IN_1 = 1359; // $s1 Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int YOU_HAVE_RECEIVED_A_MESSAGE_FROM_S1 = 1360; // $s1:
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int DUE_TO_A_SYSTEM_ERROR_YOU_HAVE_BEEN_LOGGED_OUT_OF_THE_NET_MESSENGER_SERVICE = 1361; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´
    // Ã�Â¸Ã�Â·
    // .NET
    // Messenger
    // Service.
    public static final int PLEASE_SELECT_THE_CONTACT_YOU_WISH_TO_DELETE__IF_YOU_WOULD_LIKE_TO_DELETE_A_GROUP_CLICK_THE_BUTTON_NEXT_TO_MY_STATUS_AND_THEN_USE_THE_OPTIONS_MENU = 1362; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€š,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’,
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¼Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â½Ã�Â¾Ã�Â¿Ã�ÂºÃ‘Æ’
    // Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â¾Ã�Â¼
    // Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â¸Ã�Â¼
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Æ’Ã‘ï¿½Ã�Â¾Ã�Â¼
    // Ã�Â¸
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å½.
    public static final int YOUR_REQUEST_TO_PARTICIPATE_IN_THE_ALLIANCE_WAR_HAS_BEEN_DENIED = 1363; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â¾Ã�Â².
    public static final int THE_REQUEST_FOR_AN_ALLIANCE_WAR_HAS_BEEN_REJECTED = 1364; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â¾Ã�Â².
    public static final int S2_OF_S1_CLAN_HAS_SURRENDERED_AS_AN_INDIVIDUAL = 1365; // $s2
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã‘ï¿½Ã�Â´Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int YOU_CAN_DELETE_A_GROUP_ONLY_WHEN_YOU_DO_NOT_HAVE_ANY_CONTACT_IN_THAT_GROUP__IN_ORDER_TO_DELETE_A_GROUP_FIRST_TRANSFER_YOUR_CONTACT_S_IN_THAT_GROUP_TO_ANOTHER_GROUP = 1366; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ‘ï¿½Ã‘Æ’Ã‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¾Ã�Â².
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â¸Ã�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ�Â°Ã�ÂºÃ‘â€šÃ‘â€¹
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã‘Æ’Ã‘Å½.
    public static final int ONLY_MEMBERS_OF_THE_GROUP_ARE_ALLOWED_TO_ADD_RECORDS = 1367; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¸.
    public static final int THOSE_ITEMS_MAY_NOT_BE_TRIED_ON_SIMULTANEOUSLY = 1368; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾.
    public static final int YOUVE_EXCEEDED_THE_MAXIMUM = 1369; // Ã�â€™Ã‘â€¹ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘Æ’Ã�Â¼Ã�Â¼Ã‘Æ’.
    public static final int YOU_CANNOT_SEND_MAIL_TO_A_GM_SUCH_AS_S1 = 1370; // $c1
    // -
    // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬.
    // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾.
    public static final int IT_HAS_BEEN_DETERMINED_THAT_YOURE_NOT_ENGAGED_IN_NORMAL_GAMEPLAY_AND_A_RESTRICTION_HAS_BEEN_IMPOSED_UPON_YOU_YOU_MAY_NOT_MOVE_FOR_S1_MINUTES = 1371; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â¾Ã�Â·Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¹.
    // Ã�â€™
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOUR_PUNISHMENT_WILL_CONTINUE_FOR_S1_MINUTES = 1372; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â²Ã�Â¸Ã�Â³Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    // Ã�Å¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int S1_HAS_PICKED_UP_S2_THAT_WAS_DROPPED_BY_A_RAID_BOSS = 1373; // $s1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2,
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�â€˜Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã�Â°
    // Ã‘â‚¬Ã�ÂµÃ�Â¹Ã�Â´Ã�Â°.
    public static final int S1_HAS_PICKED_UP_S3_S2_S_THAT_WAS_DROPPED_BY_A_RAID_BOSS = 1374; // $s1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2
    // ($s3
    // Ã‘Ë†Ã‘â€š.),
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�â€˜Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã�Â°
    // Ã‘â‚¬Ã�ÂµÃ�Â¹Ã�Â´Ã�Â°.
    public static final int S1_HAS_PICKED_UP__S2_ADENA_THAT_WAS_DROPPED_BY_A_RAID_BOSS = 1375; // $s1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€š
    // $s2
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½,
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Âµ
    // Ã�Â¸Ã�Â·
    // Ã�â€˜Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã�Â°
    // Ã‘â‚¬Ã�ÂµÃ�Â¹Ã�Â´Ã�Â°.
    public static final int S1_HAS_PICKED_UP_S2_THAT_WAS_DROPPED_BY_ANOTHER_CHARACTER = 1376; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2,
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int S1_HAS_PICKED_UP_S3_S2_S_THAT_WAS_DROPPED_BY_ANOTHER_CHARACTER = 1377; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s2
    // ($s3Ã‘Ë†Ã‘â€š.),
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int S1_HAS_PICKED_UP__S3S2_THAT_WAS_DROPPED_BY_ANOTHER_CHARACTER = 1378; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // +$s3
    // $s2,
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int S1_HAS_OBTAINED_S2_ADENA = 1379; // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š $s2
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int YOU_CANT_SUMMON_A_S1_WHILE_ON_THE_BATTLEGROUND = 1380; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $s1
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Âµ
    // Ã�Â±Ã�Â¾Ã‘ï¿½.
    public static final int THE_PARTY_LEADER_HAS_OBTAINED_S2_OF_S1 = 1381; // Ã�â€ºÃ�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š:
    // $s1
    // ($s2
    // Ã‘Ë†Ã‘â€š.)
    public static final int ARE_YOU_SURE_YOU_WANT_TO_CHOOSE_THIS_WEAPON_TO_FULFILL_THE_QUEST_YOU_MUST_BRING_THE_CHOSEN_WEAPON = 1382; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ?
    // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€š,
    // Ã�Â²Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ.
    public static final int ARE_YOU_SURE_YOU_WANT_TO_EXCHANGE = 1383; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½?
    public static final int S1_HAS_BECOME_A_PARTY_LEADER = 1384; // $c1 Ã‘â€šÃ�ÂµÃ�Â¿Ã�ÂµÃ‘â‚¬Ã‘Å’
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_ARE_NOT_ALLOWED_TO_DISMOUNT_AT_THIS_LOCATION = 1385; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ.
    public static final int HOLD_STATE_HAS_BEEN_LIFTED = 1386; // Ã�Å¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã�Â½Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾.
    public static final int PLEASE_SELECT_THE_ITEM_YOU_WOULD_LIKE_TO_TRY_ON = 1387; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int A_PARTY_ROOM_HAS_BEEN_CREATED = 1388; // Ã�Â¡Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int THE_PARTY_ROOMS_INFORMATION_HAS_BEEN_REVISED = 1389; // Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Âµ
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_ARE_NOT_ALLOWED_TO_ENTER_THE_PARTY_ROOM = 1390; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_HAVE_EXITED_FROM_THE_PARTY_ROOM = 1391; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int S1_HAS_LEFT_THE_PARTY_ROOM = 1392; // $c1 Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_HAVE_BEEN_OUSTED_FROM_THE_PARTY_ROOM = 1393; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â½Ã�Â°Ã�Â½Ã‘â€¹
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int S1_HAS_BEEN_OUSTED_FROM_THE_PARTY_ROOM = 1394; // $c1
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã�Â½Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int THE_PARTY_ROOM_HAS_BEEN_DISBANDED = 1395; // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°.
    public static final int THE_LIST_OF_PARTY_ROOMS_CAN_BE_VIEWED_BY_A_PERSON_WHO_HAS_NOT_JOINED_A_PARTY_OR_WHO_IS_A_PARTY_LEADER = 1396; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’,
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¾Ã�Â¼
    // Ã�Âº
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ‘Æ’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂµÃ�Âµ
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬.
    public static final int THE_LEADER_OF_THE_PARTY_ROOM_HAS_CHANGED = 1397; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int WE_ARE_RECRUITING_PARTY_MEMBERS = 1398; // Ã�Å“Ã‘â€¹ Ã�Â¸Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int ONLY_A_PARTY_LEADER_CAN_TRANSFER_ONES_RIGHTS_TO_ANOTHER_PLAYER = 1399; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â¼
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’.
    public static final int PLEASE_SELECT_THE_PERSON_YOU_WISH_TO_MAKE_THE_PARTY_LEADER = 1400; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_CANNOT_TRANSFER_RIGHTS_TO_YOURSELF = 1401; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�ÂµÃ�Â±Ã�Âµ.
    public static final int YOU_CAN_TRANSFER_RIGHTS_ONLY_TO_ANOTHER_PARTY_MEMBER = 1402; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_HAVE_FAILED_TO_TRANSFER_THE_PARTY_LEADER_RIGHTS = 1403; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€¡Ã�Âµ
    // Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int THE_OWNER_OF_THE_PRIVATE_MANUFACTURING_STORE_HAS_CHANGED_THE_PRICE_FOR_CREATING_THIS_ITEM__PLEASE_CHECK_THE_NEW_PRICE_BEFORE_TRYING_AGAIN = 1404; // Ã�Â¥Ã�Â¾Ã�Â·Ã‘ï¿½Ã�Â¸Ã�Â½
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�Â¸Ã�Â·Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int S1_CPS_WILL_BE_RESTORED = 1405; // $s1: CP
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int S1_WILL_RESTORE_S2S_CP = 1406; // $s2: CP
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // ($c1).
    public static final int YOU_ARE_USING_A_COMPUTER_THAT_DOES_NOT_ALLOW_YOU_TO_LOG_IN_WITH_TWO_ACCOUNTS_AT_THE_SAME_TIME = 1407; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¿Ã‘Å’Ã‘Å½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â¾Ã�Â¼,
    // Ã�Â´Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½.
    public static final int YOUR_PREPAID_REMAINING_USAGE_TIME_IS_S1_HOURS_AND_S2_MINUTES__YOU_HAVE_S3_PAID_RESERVATIONS_LEFT = 1408; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½:
    // $s1Ã‘â€¡
    // $s2Ã�Â¼Ã�Â¸Ã�Â½.
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘â€¡Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â²:
    // $s3.
    public static final int YOUR_PREPAID_USAGE_TIME_HAS_EXPIRED_YOUR_NEW_PREPAID_RESERVATION_WILL_BE_USED_THE_REMAINING_USAGE_TIME_IS_S1_HOURS_AND_S2_MINUTES = 1409; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’,
    // Ã�Â¸
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â¸Ã�Â½Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘â€¡Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã‘Ë†Ã�ÂµÃ�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½:
    // $s1
    // Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOUR_PREPAID_USAGE_TIME_HAS_EXPIRED_YOU_DO_NOT_HAVE_ANY_MORE_PREPAID_RESERVATIONS_LEFT = 1410; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘â€¡Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int THE_NUMBER_OF_YOUR_PREPAID_RESERVATIONS_HAS_CHANGED = 1411; // Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘â€¡Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â².
    public static final int YOUR_PREPAID_USAGE_TIME_HAS_S1_MINUTES_LEFT = 1412; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int SINCE_YOU_DO_NOT_MEET_THE_REQUIREMENTS_YOU_ARE_NOT_ALLOWED_TO_ENTER_THE_PARTY_ROOM = 1413; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int THE_WIDTH_AND_LENGTH_SHOULD_BE_100_OR_MORE_GRIDS_AND_LESS_THAN_5000_GRIDS_RESPECTIVELY = 1414; // Ã�Â¨Ã�Â¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â¸
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 100,
    // Ã�Â½Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Âµ
    // 5000.
    public static final int THE_COMMAND_FILE_IS_NOT_SET = 1415; // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â» Ã�Â½Ã�Âµ Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�ÂµÃ�Â½.
    public static final int THE_PARTY_REPRESENTATIVE_OF_TEAM_1_HAS_NOT_BEEN_SELECTED = 1416; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // 1-Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½.
    public static final int THE_PARTY_REPRESENTATIVE_OF_TEAM_2_HAS_NOT_BEEN_SELECTED = 1417; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // 2-Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½.
    public static final int THE_NAME_OF_TEAM_1_HAS_NOT_YET_BEEN_CHOSEN = 1418; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // 1-Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¾.
    public static final int THE_NAME_OF_TEAM_2_HAS_NOT_YET_BEEN_CHOSEN = 1419; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // 2-Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¾.
    public static final int THE_NAME_OF_TEAM_1_AND_THE_NAME_OF_TEAM_2_ARE_IDENTICAL = 1420; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // 1-Ã�Â¹
    // Ã�Â¸
    // 2-Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã‘â€¹.
    public static final int THE_RACE_SETUP_FILE_HAS_NOT_BEEN_DESIGNATED = 1421; // Ã�ï¿½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int RACE_SETUP_FILE_ERROR__BUFFCNT_IS_NOT_SPECIFIED = 1422; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // BuffCnt.
    public static final int RACE_SETUP_FILE_ERROR__BUFFIDS1_IS_NOT_SPECIFIED = 1423; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // BuffID$s1.
    public static final int RACE_SETUP_FILE_ERROR__BUFFLVS1_IS_NOT_SPECIFIED = 1424; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // BuffLv$s1.
    public static final int RACE_SETUP_FILE_ERROR__DEFAULTALLOW_IS_NOT_SPECIFIED = 1425; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // DefaultAllow.
    public static final int RACE_SETUP_FILE_ERROR__EXPSKILLCNT_IS_NOT_SPECIFIED = 1426; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // ExpSkillCnt.
    public static final int RACE_SETUP_FILE_ERROR__EXPSKILLIDS1_IS_NOT_SPECIFIED = 1427; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // ExpSkillID$s1.
    public static final int RACE_SETUP_FILE_ERROR__EXPITEMCNT_IS_NOT_SPECIFIED = 1428; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // ExpItemCnt.
    public static final int RACE_SETUP_FILE_ERROR__EXPITEMIDS1_IS_NOT_SPECIFIED = 1429; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // ExpItemId$s1.
    public static final int RACE_SETUP_FILE_ERROR__TELEPORTDELAY_IS_NOT_SPECIFIED = 1430; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // -
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // TeleportDelay.
    public static final int THE_RACE_WILL_BE_STOPPED_TEMPORARILY = 1431; // Ã�Â¡Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int YOUR_OPPONENT_IS_CURRENTLY_IN_A_PETRIFIED_STATE = 1432; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â¾Ã�ÂºÃ�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â».
    public static final int THE_USE_OF_S1_WILL_NOW_BE_AUTOMATED = 1433; // $s1
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â°Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    public static final int THE_AUTOMATIC_USE_OF_S1_WILL_NOW_BE_CANCELLED = 1434; // $s1:
    // Ã�Â°Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int DUE_TO_INSUFFICIENT_S1_THE_AUTOMATIC_USE_FUNCTION_HAS_BEEN_CANCELLED = 1435; // $s1:
    // Ã�Â½Ã�ÂµÃ‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    // Ã�ï¿½Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int DUE_TO_INSUFFICIENT_S1_THE_AUTOMATIC_USE_FUNCTION_CANNOT_BE_ACTIVATED = 1436; // $s1:
    // Ã�Â½Ã�ÂµÃ‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    // Ã�ï¿½Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int PLAYERS_ARE_NO_LONGER_ALLOWED_TO_PLACE_DICE_DICE_CANNOT_BE_PURCHASED_FROM_A_VILLAGE_STORE_ANY_MORE_HOWEVER_YOU_CAN_STILL_SELL_THEM_TO_A_STORE_IN_A_VILLAGE = 1437; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸,
    // Ã�Â°
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘â€šÃ�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘â€¦
    // Ã�Â²
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â°Ã�Â·Ã�Â¸Ã�Â½Ã�Âµ
    // Ã�Â´Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¸.
    // Ã�ï¿½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â²
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â°Ã�Â·Ã�Â¸Ã�Â½
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.

    public static final int THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT = 1438; // Ã�Å¾Ã‘â€šÃ‘ï¿½Ã‘Æ’Ã‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ,
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int ITEMS_REQUIRED_FOR_SKILL_ENCHANT_ARE_INSUFFICIENT = 1439; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²,
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘â€¹Ã‘â€¦
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int SUCCEEDED_IN_ENCHANTING_SKILL_S1 = 1440; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // $s1 Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int FAILED_IN_ENCHANTING_SKILL_S1 = 1441; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â° Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â°Ã�ÂµÃ�Â¼Ã�Â¾Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int SP_REQUIRED_FOR_SKILL_ENCHANT_IS_INSUFFICIENT = 1443; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // SP
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int EXP_REQUIRED_FOR_SKILL_ENCHANT_IS_INSUFFICIENT = 1444; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_DO_NOT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_UNTRAIN_THE_ENCHANT_SKILL = 2068; // Ã�Â£
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²,
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘â€¹Ã‘â€¦
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.

    public static final int Untrain_of_enchant_skill_was_successful_Current_level_of_enchant_skill_S1_has_been_decreased_by_1 = 2069; // Ã�Â Ã�Â°Ã�Â·Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¾.
    // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â½Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // 1.
    public static final int Untrain_of_enchant_skill_was_successful_Current_level_of_enchant_skill_S1_became_0_and_enchant_skill_will_be_initialized = 2070; // Ã�Â¡Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    // $s1:
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // -
    // 0,
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â²
    // Ã�Â¸Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Âµ.
    public static final int You_do_not_have_all_of_the_items_needed_to_enchant_skill_route_change = 2071; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘â€¹Ã‘â€¦
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int Enchant_skill_route_change_was_successful_Lv_of_enchant_skill_S1_has_been_decreased_by_S2 = 2072; // Ã�Å¸Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â½Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // $s2.
    public static final int Enchant_skill_route_change_was_successful_Lv_of_enchant_skill_S1_will_remain = 2073; // Ã�Å¸Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½.
    public static final int Skill_enchant_failed_Current_level_of_enchant_skill_S1_will_remain_unchanged = 2074; // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã�ÂµÃ�Â·
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.

    public static final int REMAINING_TIME_S1_SECOND = 1442; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’: $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int YOUR_PREVIOUS_SUB_CLASS_WILL_BE_DELETED_AND_YOUR_NEW_SUB_CLASS_WILL_START_AT_LEVEL_40__DO_YOU_WISH_TO_PROCEED = 1445; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¼
    // Ã�Â¿Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // 40-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_FERRY_FROM_S1_TO_S2_HAS_BEEN_DELAYED = 1446; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â±Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°
    // $s1
    // Ã�Â²
    // Ã�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€š
    // $s2,
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int OTHER_SKILLS_ARE_NOT_AVAILABLE_WHILE_FISHING = 1447; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int ONLY_FISHING_SKILLS_ARE_AVAILABLE = 1448; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int SUCCEEDED_IN_GETTING_A_BITE = 1449; // Ã�Å¡Ã�Â»Ã‘Å½Ã�ÂµÃ‘â€š!
    public static final int TIME_IS_UP_SO_THAT_FISH_GOT_AWAY = 1450; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾,
    // Ã�Â¸ Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã‘Æ’.
    public static final int THE_FISH_GOT_AWAY = 1451; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â»Ã�Â¸ Ã‘â‚¬Ã‘â€¹Ã�Â±Ã‘Æ’.
    public static final int BAITS_HAVE_BEEN_LOST_BECAUSE_THE_FISH_GOT_AWAY = 1452; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã‘Æ’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¸Ã�Â²Ã�ÂºÃ‘Æ’.
    public static final int FISHING_POLES_ARE_NOT_INSTALLED = 1453;
    public static final int BAITS_ARE_NOT_PUT_ON_A_HOOK = 1454;
    public static final int YOU_CANT_FISH_IN_WATER = 1455;
    public static final int YOU_CANT_FISH_WHILE_YOU_ARE_ON_BOARD = 1456;
    public static final int YOU_CANT_FISH_HERE = 1457; // Ã�â€”Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’ Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’ Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int CANCELS_FISHING = 1458; // Ã�Â Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â° Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int NOT_ENOUGH_BAIT = 1459; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â½Ã�Âµ Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š Ã�Â½Ã�Â°Ã�Â¶Ã�Â¸Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int ENDS_FISHING = 1460; // Ã�Â Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â° Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int STARTS_FISHING = 1461; // Ã�Â Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â° Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int PUMPING_SKILL_IS_AVAILABLE_ONLY_WHILE_FISHING = 1462; // Ã�Å¸Ã�Â¾Ã�Â´Ã‘â€šÃ‘ï¿½Ã�Â³Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int REELING_SKILL_IS_AVAILABLE_ONLY_WHILE_FISHING = 1463; // Ã�Å¸Ã�Â¾Ã�Â´Ã‘ï¿½Ã�ÂµÃ�ÂºÃ�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int FISH_HAS_RESISTED = 1464; // Ã�Â Ã‘â€¹Ã�Â±Ã�Â° Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�Â²Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’ Ã‘ï¿½
    // Ã�ÂºÃ‘â‚¬Ã‘Å½Ã‘â€¡Ã�ÂºÃ�Â°, Ã�Â¸ Ã�Â²Ã‘â€¹ Ã�Â½Ã�Âµ Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã�ÂµÃ�Âµ Ã�Â²Ã‘â€¹Ã‘â€šÃ�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int PUMPING_IS_SUCCESSFUL_DAMAGE_S1 = 1465; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ‘ï¿½Ã�Â½Ã‘Æ’Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã‘Æ’ Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â½Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�ÂµÃ�Â¹ Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½
    // $s1 HP
    public static final int PUMPING_FAILED_DAMAGE_S1 = 1466;
    public static final int REELING_IS_SUCCESSFUL_DAMAGE_S1 = 1467; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘ï¿½Ã�ÂµÃ�ÂºÃ�Â»Ã�Â¸
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã‘Æ’ Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â½Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�ÂµÃ�Â¹ Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½
    // $s1 HP
    public static final int REELING_FAILED_DAMAGE_S1 = 1468;
    public static final int SUCCEEDED_IN_FISHING = 1469; 
    public static final int YOU_CANNOT_DO_THAT_WHILE_FISHING = 1470;
    public static final int YOU_CANNOT_DO_ANYTHING_ELSE_WHILE_FISHING = 1471;
    public static final int YOU_CANT_MAKE_AN_ATTACK_WITH_A_FISHING_POLE = 1472;
    public static final int S1_IS_NOT_SUFFICIENT = 1473;
    public static final int S1_IS_NOT_AVAILABLE = 1474;
    public static final int PET_HAS_DROPPED_S1 = 1475;
    public static final int PET_HAS_DROPPED__S1S2 = 1476;
    // +$s1 $s2.
    public static final int PET_HAS_DROPPED_S2_OF_S1 = 1477;
    // $s1 ($s2 Ã‘Ë†Ã‘â€š.)
    public static final int YOU_CAN_REGISTER_ONLY_256_COLOR_BMP_FILES_WITH_A_SIZE_OF_64X64 = 1478; // Ã�Â¤Ã�Â°Ã�Â¹Ã�Â»
    // -
    // Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€š
    // bmp,
    // 256
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²,
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘â‚¬
    // 64*64.
    public static final int THIS_FISHING_SHOT_IS_NOT_FIT_FOR_THE_FISHING_POLE_CRYSTAL = 1479; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â³
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â¾Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã�Â´Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸.
    public static final int DO_YOU_WANT_TO_CANCEL_YOUR_APPLICATION_FOR_JOINING_THE_GRAND_OLYMPIAD = 1480; // Ã�Å¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ?
    public static final int YOU_HAVE_BEEN_SELECTED_FOR_NO_CLASS_GAME_DO_YOU_WANT_TO_JOIN = 1481; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ‘ï¿½Ã‘Æ’Ã‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â°Ã�Â¼.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_HAVE_BEEN_SELECTED_FOR_CLASSIFIED_GAME_DO_YOU_WANT_TO_JOIN = 1482; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã‘ï¿½
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Â¿Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â°Ã�Â¼.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int DO_YOU_WANT_TO_BECOME_A_HERO_NOW = 1483; // Ã�â€™Ã‘â€¹
    // Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Å’
    // Ã�Â³Ã�ÂµÃ‘â‚¬Ã�Â¾Ã�ÂµÃ�Â¼?
    public static final int DO_YOU_WANT_TO_USE_THE_HEROES_WEAPON_THAT_YOU_CHOSE = 1484; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�â€œÃ�ÂµÃ‘â‚¬Ã�Â¾Ã‘ï¿½?
    // Ã�â€�Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â¼
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â°Ã�Â¼,
    // Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�Â¼Ã�Âµ
    // Ã�Å¡Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â¹.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_TO_GLUDIN_HARBOR_HAS_BEEN_DELAYED = 1485; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½,
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_TO_TALKING_ISLAND_HAS_BEEN_DELAYED = 1486; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²,
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_FERRY_FROM_GIRAN_HARBOR_TO_TALKING_ISLAND_HAS_BEEN_DELAYED = 1487; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½
    // Ã�Â½Ã�Â°
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²,
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_FERRY_FROM_TALKING_ISLAND_TO_GIRAN_HARBOR_HAS_BEEN_DELAYED = 1488; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½,
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int INNADRIL_CRUISE_SERVICE_HAS_BEEN_DELAYED = 1489; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // Ã�Â²
    // Ã�ËœÃ�Â½Ã�Â½Ã�Â°Ã�Â´Ã‘â‚¬Ã�Â¸Ã�Â»
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int TRADED_S2_OF_CROP_S1 = 1490; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â½Ã�Â¾ Ã�Â¿Ã�Â»Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²: $s1
    // ($s2 Ã‘Ë†Ã‘â€š.)
    public static final int FAILED_IN_TRADING_S2_OF_CROP_S1 = 1491; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â»Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²:
    // $s1 ($s2
    // Ã‘Ë†Ã‘â€š.)
    public static final int YOU_WILL_ENTER_THE_OLYMPIAD_STADIUM_IN_S1_SECOND_S = 1492; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â¹Ã‘ï¿½Ã�ÂºÃ�Â¸Ã�Â¹
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â´Ã�Â¸Ã�Â¾Ã�Â½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_ENDS_THE_GAME = 1493; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹,
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã‘â€šÃ�ÂµÃ�Â¼
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã‘â€¹Ã�Â¼
    // Ã�Â¾Ã‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¸.
    public static final int THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_JOINING_THE_GAME = 1494; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã�Â»
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½Ã�Â¼
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¸,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int THE_GAME_WILL_START_IN_S1_SECOND_S = 1495; // Ã�Â¡Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int STARTS_THE_GAME = 1496; // Ã�Â¡Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½ Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’.
    public static final int S1_HAS_WON_THE_GAME = 1497; // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¹ - $c1.
    public static final int THE_GAME_ENDED_IN_A_TIE = 1498; // Ã�ï¿½Ã�Â¸Ã‘â€¡Ã‘Å’Ã‘ï¿½.
    public static final int YOU_WILL_GO_BACK_TO_THE_VILLAGE_IN_S1_SECOND_S = 1499; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â³Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â´.
    public static final int YOU_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_JOB_CHARACTER = 1500; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼
    // Ã‘ï¿½
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â¾Ã�Â¼.
    public static final int ONLY_NOBLESS_CAN_PARTICIPATE_IN_THE_OLYMPIAD = 1501; // Ã�â€™
    // Ã�Â¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â´Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã�Â½Ã�Âµ.
    public static final int YOU_HAVE_ALREADY_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_AN_EVENT = 1502; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_CLASSIFIED_GAMES = 1503; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦.
    public static final int YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_NO_CLASS_GAMES = 1504; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã�Â½Ã�ÂµÃ�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦.
    public static final int YOU_HAVE_BEEN_DELETED_FROM_THE_WAITING_LIST_OF_A_GAME = 1505; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã‘Å½
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¸Ã�Â·
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¹.
    public static final int YOU_HAVE_NOT_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_A_GAME = 1506; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THIS_ITEM_CANT_BE_EQUIPPED_FOR_THE_OLYMPIAD_EVENT = 1507; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT = 1508; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int THIS_SKILL_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT = 1509; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int S1_IS_MAKING_AN_ATTEMPT_AT_RESURRECTION_WITH_$S2_EXPERIENCE_POINTS_DO_YOU_WANT_TO_CONTINUE_WITH_THIS_RESURRECTION = 1510; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $s2.
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â½Ã‘â€¹?
    public static final int WHILE_A_PET_IS_ATTEMPTING_TO_RESURRECT_IT_CANNOT_HELP_IN_RESURRECTING_ITS_MASTER = 1511; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€¡Ã‘Å’
    // Ã‘â€¦Ã�Â¾Ã�Â·Ã‘ï¿½Ã�Â¸Ã�Â½Ã‘Æ’.
    public static final int WHILE_A_PETS_MASTER_IS_ATTEMPTING_TO_RESURRECT_THE_PET_CANNOT_BE_RESURRECTED_AT_THE_SAME_TIME = 1512; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€¦Ã�Â¾Ã�Â·Ã‘ï¿½Ã�Â¸Ã�Â½
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€¡Ã‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã‘Æ’.
    public static final int BETTER_RESURRECTION_HAS_BEEN_ALREADY_PROPOSED = 1513; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘Ë†Ã�Â»Ã�Â¾.
    public static final int SINCE_THE_PET_WAS_IN_THE_PROCESS_OF_BEING_RESURRECTED_THE_ATTEMPT_TO_RESURRECT_ITS_MASTER_HAS_BEEN_CANCELLED = 1514; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€¦Ã�Â¾Ã�Â·Ã‘ï¿½Ã�Â¸Ã�Â½Ã�Â°,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ .
    public static final int SINCE_THE_MASTER_WAS_IN_THE_PROCESS_OF_BEING_RESURRECTED_THE_ATTEMPT_TO_RESURRECT_THE_PET_HAS_BEEN_CANCELLED = 1515; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¦Ã�Â¾Ã�Â·Ã‘ï¿½Ã�Â¸Ã�Â½.
    public static final int THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING = 1516; // Ã�ï¿½Ã�Â°
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½.
    public static final int FAILED_IN_BLESSED_ENCHANT_THE_ENCHANT_VALUE_OF_THE_ITEM_BECAME_0 = 1517; // Ã�â€˜Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    // Ã�â€”Ã�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â°
    // 0.
    public static final int YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM = 1518; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�Â»Ã‘Å½Ã�Â´Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int THE_PET_HAS_BEEN_KILLED_IF_YOU_DO_NOT_RESURRECT_IT_WITHIN_24_HOURS_THE_PETS_BODY_WILL_DISAPPEAR_ALONG_WITH_ALL_THE_PETS_ITEMS = 1519; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ‘â‚¬.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â·Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 24
    // Ã‘â€¡,
    // Ã‘â€šÃ�Â¾
    // Ã‘â€šÃ‘â‚¬Ã‘Æ’Ã�Â¿
    // Ã�Â¸
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int SERVITOR_PASSED_AWAY = 1520; // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â³Ã�Â° Ã‘Æ’Ã�Â¼Ã�ÂµÃ‘â‚¬.
    public static final int SERVITOR_DISAPPEASR_BECAUSE_THE_SUMMONING_TIME_IS_OVER = 1521; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾.
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â½Ã‘Æ’Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int THE_CORPSE_DISAPPEARED_BECAUSE_MUCH_TIME_PASSED_AFTER_PET_DIED = 1522; // Ã�Å¸Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã‘â€šÃ‘â‚¬Ã‘Æ’Ã�Â¿
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int BECAUSE_PET_OR_SERVITOR_MAY_BE_DROWNED_WHILE_THE_BOAT_MOVES_PLEASE_RELEASE_THE_SUMMON_BEFORE_DEPARTURE = 1523; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã�Â¿Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°
    // Ã�Â±Ã�Â¾Ã‘â‚¬Ã‘â€š
    // Ã�Â¸
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘â€šÃ‘Å’.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã‘Æ’Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�Â¸Ã‘â€¦
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�ÂµÃ�Â².
    public static final int PET_OF_S1_GAINED_S2 = 1524; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»: $s2.
    public static final int PET_OF_S1_GAINED_S3_OF_S2 = 1525; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»: $s2
    // ($s3 Ã‘Ë†Ã‘â€š.)
    public static final int PET_OF_S1_GAINED__S2S3 = 1526; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»: +$s2
    // $s3.
    public static final int PET_TOOK_S1_BECAUSE_HE_WAS_HUNGRY = 1527; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸ Ã‘ï¿½Ã‘Å Ã�ÂµÃ�Â»:
    // $s1.
    public static final int A_FORCIBLE_PETITION_FROM_GM_HAS_BEEN_RECEIVED = 1528; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã�Â»Ã�Â¸
    // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘Æ’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�Â±Ã�ÂµÃ‘ï¿½Ã�ÂµÃ�Â´Ã‘Æ’.
    public static final int S1_HAS_INVITED_YOU_TO_THE_COMMAND_CHANNEL_DO_YOU_WANT_TO_JOIN = 1529; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    // Ã�Â¡Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½?
    public static final int SELECT_A_TARGET_OR_ENTER_THE_NAME = 1530; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½.
    public static final int ENTER_THE_NAME_OF_CLAN_AGAINST_WHICH_YOU_WANT_TO_MAKE_AN_ATTACK = 1531; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¼
    // Ã�Â²Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    public static final int ENTER_THE_NAME_OF_CLAN_AGAINST_WHICH_YOU_WANT_TO_STOP_THE_WAR = 1532; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¼
    // Ã�Â²Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�ÂºÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    public static final int ATTENTION_S1_PICKED_UP_S2 = 1533; // $c1 Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š:
    // $s2.
    public static final int ATTENTION_S1_PICKED_UP__S2_S3 = 1534; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š:
    // +$s2 $s3.
    public static final int ATTENTION_S1_PET_PICKED_UP_S2 = 1535; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1 Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã�Â»:
    // $s2.
    public static final int ATTENTION_S1_PET_PICKED_UP__S2_S3 = 1536; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã�Â»:
    // +$s2
    // $s3.
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_RUNE_VILLAGE = 1537; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â¾Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹)
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_GODDARD_CASTLE_TOWN = 1538; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â¾Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�â€œÃ�Â¾Ã�Â´Ã�Â´Ã�Â°Ã‘â‚¬Ã�Â´Ã�Â°)
    public static final int CARGO_HAS_ARRIVED_AT_TALKING_ISLAND_VILLAGE = 1539; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�â€œÃ�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°.
    public static final int CARGO_HAS_ARRIVED_AT_DARK_ELVEN_VILLAGE = 1540; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�Â¢Ã�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¾Ã�Â².
    public static final int CARGO_HAS_ARRIVED_AT_ELVEN_VILLAGE = 1541; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�Â­Ã�Â»Ã‘Å’Ã‘â€žÃ�Â¾Ã�Â².
    public static final int CARGO_HAS_ARRIVED_AT_ORC_VILLAGE = 1542; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�Å¾Ã‘â‚¬Ã�ÂºÃ�Â¾Ã�Â².
    public static final int CARGO_HAS_ARRIVED_AT_DWARVEN_VILLAGE = 1543; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�â€œÃ�Â½Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â².
    public static final int CARGO_HAS_ARRIVED_AT_ADEN_CASTLE_TOWN = 1544; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�ï¿½Ã�Â´Ã�ÂµÃ�Â½.
    public static final int CARGO_HAS_ARRIVED_AT_OREN_CASTLE_TOWN = 1545; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�Å¾Ã‘â‚¬Ã�ÂµÃ�Â½.
    public static final int CARGO_HAS_ARRIVED_AT_HUNTERS_VILLAGE = 1546; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€�Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã‘Å½
    // Ã�Å¾Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int CARGO_HAS_ARRIVED_AT_DION_CASTLE_TOWN = 1547; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€�Ã�Â¸Ã�Â¾Ã�Â½.
    public static final int CARGO_HAS_ARRIVED_AT_FLORAN_VILLAGE = 1548; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�Â¤Ã�Â»Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â½.
    public static final int CARGO_HAS_ARRIVED_AT_GLUDIN_VILLAGE = 1549; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½.
    public static final int CARGO_HAS_ARRIVED_AT_GLUDIO_CASTLE_TOWN = 1550; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â¾.
    public static final int CARGO_HAS_ARRIVED_AT_GIRAN_CASTLE_TOWN = 1551; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½.
    public static final int CARGO_HAS_ARRIVED_AT_HEINE = 1552; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â· Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â² Ã�Â¥Ã�ÂµÃ�Â¹Ã�Â½.
    public static final int CARGO_HAS_ARRIVED_AT_RUNE_VILLAGE = 1553; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â² Ã�Â Ã‘Æ’Ã�Â½Ã‘Æ’.
    public static final int CARGO_HAS_ARRIVED_AT_GODDARD_CASTLE_TOWN = 1554; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â·
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�â€œÃ�Â¾Ã�Â´Ã�Â´Ã�Â°Ã‘â‚¬Ã�Â´.
    public static final int DO_YOU_WANT_TO_CANCEL_CHARACTER_DELETION = 1555; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°?
    public static final int NOTICE_HAS_BEEN_SAVED = 1556; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int SEED_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2 = 1557; // Ã�Â¦Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸:
    // $s1
    // -
    // $s2.
    public static final int THE_QUANTITY_OF_SEED_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2 = 1558; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½Ã�Â½:
    // $s1
    // -
    // $s2.
    public static final int CROP_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2 = 1559; // Ã�Â¦Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã�Â»Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²:
    // $s1
    // -
    // $s2.
    public static final int THE_QUANTITY_OF_CROP_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2_ = 1560; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã�Â»Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²:
    // $s1
    // -
    // $s2.
    public static final int S1_CLAN_HAS_DECLARED_CLAN_WAR = 1561; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½ $s1
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã�Â»
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    public static final int CLAN_WAR_HAS_BEEN_DECLARED_AGAINST_S1_CLAN_IF_YOU_ARE_KILLED_DURING_THE_CLAN_WAR_BY_MEMBERS_OF_THE_OPPOSING_CLAN_THE_EXPERIENCE_PENALTY_WILL_BE_REDUCED_TO_1_4_OF_NORMAL = 1562; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’
    // $s1.
    // Ã�Â¡
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸
    // Ã�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¸Ã�Â·
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ�Â¼Ã‘â€¹Ã�Â¹
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€š
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€š
    // 1/4.
    public static final int S1_CLAN_CANT_MAKE_A_DECLARATION_OF_CLAN_WAR_SINCE_IT_HASNT_REACHED_THE_CLAN_LEVEL_OR_DOESNT_HAVE_ENOUGH_CLAN_MEMBERS = 1563; // Ã�Â£
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1
    // Ã�Â½Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â¼Ã�Â°Ã�Â»Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘â€¡Ã�ÂµÃ�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Âº
    // Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ.
    // Ã�Å¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int A_CLAN_WAR_CAN_BE_DECLARED_ONLY_IF_THE_CLAN_IS_LEVEL_THREE_OR_ABOVE_AND_THE_NUMBER_OF_CLAN_MEMBERS_IS_FIFTEEN_OR_GREATER = 1564; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²
    // Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Âµ,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // 3
    // Ã�Â¸
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 15
    // Ã‘â€¡Ã�ÂµÃ�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Âº.
    public static final int THE_DECLARATION_OF_WAR_CANT_BE_MADE_BECAUSE_THE_CLAN_DOES_NOT_EXIST_OR_ACT_FOR_A_LONG_PERIOD = 1565; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’.
    // Ã�Â­Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�Â½
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â³Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ.
    public static final int S1_CLAN_HAS_STOPPED_THE_WAR = 1566; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½ $s1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã�Â»
    // Ã‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    public static final int THE_WAR_AGAINST_S1_CLAN_HAS_BEEN_STOPPED = 1567; // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Â°
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼
    // $s1
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_TARGET_FOR_DECLARATION_IS_WRONG = 1568; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int A_DECLARATION_OF_CLAN_WAR_AGAINST_AN_ALLIED_CLAN_CANT_BE_MADE = 1569; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’,
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã‘ï¿½
    // Ã�Â²Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Â²
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Âµ.
    public static final int A_DECLARATION_OF_WAR_AGAINST_MORE_THAN_30_CLANS_CANT_BE_MADE_AT_THE_SAME_TIME = 1570; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // Ã‘â€¡Ã�ÂµÃ�Â¼
    // 30
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°Ã�Â¼
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾.
    public static final int _ATTACK_LIST_ = 1571; // =======<ATTACK_LIST>=======
    public static final int _UNDER_ATTACK_LIST_ = 1572; // ======<UNDER_ATTACK_LIST>======
    public static final int THERE_IS_NO_ATTACK_CLAN = 1573; // Ã�ï¿½Ã�ÂµÃ‘â€š Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¼ Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸ Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    public static final int THERE_IS_NO_UNDER_ATTACK_CLAN = 1574; // Ã�ï¿½Ã�ÂµÃ‘â€š Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’ Ã�â€™Ã�Â°Ã�Â¼.
    public static final int COMMAND_CHANNELS_CAN_ONLY_BE_FORMED_BY_A_PARTY_LEADER_WHO_IS_ALSO_THE_LEADER_OF_A_LEVEL_5_CLAN = 1575; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¾Ã�Â¼
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã�ÂµÃ�Â¼
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Âµ
    // 5.
    public static final int PET_USES_THE_POWER_OF_SPIRIT = 1576; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’ Ã�Â´Ã‘Æ’Ã‘â€¦Ã�Â¾Ã�Â².
    public static final int SERVITOR_USES_THE_POWER_OF_SPIRIT = 1577; // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’
    // Ã�Â´Ã‘Æ’Ã‘â€¦Ã�Â¾Ã�Â².
    public static final int ITEMS_ARE_NOT_AVAILABLE_FOR_A_PRIVATE_STORE_OR_PRIVATE_MANUFACTURE = 1578; // Ã�â€™
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int S1_PET_GAINED_S2_ADENA = 1579; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â» $s2
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THE_COMMAND_CHANNEL_HAS_BEEN_FORMED = 1580; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½.
    public static final int THE_COMMAND_CHANNEL_HAS_BEEN_DISBANDED = 1581; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOU_HAVE_PARTICIPATED_IN_THE_COMMAND_CHANNEL = 1582; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã‘Æ’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int YOU_WERE_DISMISSED_FROM_THE_COMMAND_CHANNEL = 1583; // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int S1_PARTY_HAS_BEEN_DISMISSED_FROM_THE_COMMAND_CHANNEL = 1584; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int THE_COMMAND_CHANNEL_HAS_BEEN_DEACTIVATED = 1585; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOU_HAVE_QUIT_THE_COMMAND_CHANNEL = 1586; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int S1_PARTY_HAS_LEFT_THE_COMMAND_CHANNEL = 1587; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int THE_COMMAND_CHANNEL_IS_ACTIVATED_ONLY_IF_AT_LEAST_FIVE_PARTIES_PARTICIPATE_IN = 1588; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²
    // Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Âµ,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â¼
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 5
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿.
    public static final int COMMAND_CHANNEL_AUTHORITY_HAS_BEEN_TRANSFERRED_TO_S1 = 1589; // Ã�Å¸Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€¡Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¾
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã‘Æ’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�Â»Ã�Â¸
    // Ã�Âº
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // $c1.
    public static final int _COMMAND_CHANNEL_INFO_TOTAL_PARTIES_S1_ = 1590; // ===<COMMAND_CHANNEL_INFO(TOTAL_PARTIES_S1)>===
    public static final int NO_USER_HAS_BEEN_INVITED_TO_THE_COMMAND_CHANNEL = 1591; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int YOU_CANT_OPEN_COMMAND_CHANNELS_ANY_MORE = 1592; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int YOU_DO_NOT_HAVE_AUTHORITY_TO_INVITE_SOMEONE_TO_THE_COMMAND_CHANNEL = 1593; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int S1_PARTY_IS_ALREADY_A_MEMBER_OF_THE_COMMAND_CHANNEL = 1594; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int S1_HAS_SUCCEEDED = 1595; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾ Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ $1s.
    public static final int HIT_BY_S1 = 1596; // Ã�ï¿½Ã�Â° Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â²Ã�Â¾Ã�Â·Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾ Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // $s1.
    public static final int S1_HAS_FAILED = 1597; // Ã�â€™Ã�Â°Ã�Â¼ Ã�Â½Ã�Âµ Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’ Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ $s1.
    public static final int WHEN_PET_OR_SERVITOR_IS_DEAD_SOULSHOTS_OR_SPIRITSHOTS_FOR_PET_OR_SERVITOR_ARE_NOT_AVAILABLE = 1598; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ /Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ‘â‚¬,
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€”Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã‘â€¹
    // Ã�â€�Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â¸
    // Ã�â€�Ã‘Æ’Ã‘â€¦Ã�Â°.
    public static final int WATCHING_IS_IMPOSSIBLE_DURING_COMBAT = 1599; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼
    // Ã�Â·Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã‘ï¿½.
    public static final int TOMORROWS_ITEMS_WILL_ALL_BE_SET_TO_0__DO_YOU_WISH_TO_CONTINUE = 1600; // Ã�â€”Ã�Â°Ã�Â²Ã‘â€šÃ‘â‚¬Ã�Â°
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â½Ã�Â°
    // 0.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int TOMORROWS_ITEMS_WILL_ALL_BE_SET_TO_THE_SAME_VALUE_AS_TODAYS_ITEMS__DO_YOU_WISH_TO_CONTINUE = 1601; // Ã�â€”Ã�Â°Ã�Â²Ã‘â€šÃ‘â‚¬Ã�Â°
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â½Ã‘â€¹
    // Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘Ë†Ã�Â½Ã�Â¸Ã�Â¼.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int ONLY_A_PARTY_LEADER_CAN_ACCESS_THE_COMMAND_CHANNEL = 1602; // Ã�Å¾Ã�Â±Ã‘â€°Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿.
    public static final int ONLY_CHANNEL_OPENER_CAN_GIVE_ALL_COMMAND = 1603; // Ã�â€™Ã‘ï¿½Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°.
    public static final int WHILE_DRESSED_IN_FORMAL_WEAR_YOU_CANT_USE_ITEMS_THAT_REQUIRE_ALL_SKILLS_AND_CASTING_OPERATIONS = 1604; // Ã�â€™
    // Ã‘ï¿½Ã�Â²Ã�Â°Ã�Â´Ã�ÂµÃ�Â±Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â½Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â°Ã‘â€¦
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã‘Å½Ã‘â€š
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã‘Å½.
    public static final int _HERE_YOU_CAN_BUY_ONLY_SEEDS_OF_S1_MANOR = 1605; // *Ã�â€”Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1.
    public static final int YOU_HAVE_COMPLETED_THE_QUEST_FOR_3RD_OCCUPATION_CHANGE_AND_MOVED_TO_ANOTHER_CLASS_CONGRATULATIONS = 1606; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼!
    // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€š
    // Ã�Â½Ã�Â°
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ‘â€šÃ‘Å’Ã‘Å½
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€žÃ�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¸Ã�Â¸.
    public static final int S1_ADENA_HAS_BEEN_PAID_FOR_PURCHASING_FEES = 1607; // Ã�ï¿½Ã�Â°Ã�Â»Ã�Â¾Ã�Â³
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½
    // Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â¸:
    // $s1.
    public static final int YOU_CANT_BUY_ANOTHER_CASTLE_SINCE_ADENA_IS_NOT_SUFFICIENT = 1608; // Ã�ï¿½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â¾Ã�Âº.
    public static final int THE_DECLARATION_OF_WAR_HAS_BEEN_ALREADY_MADE_TO_THE_CLAN = 1609; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹.
    public static final int FOOL_YOU_CANNOT_DECLARE_WAR_AGAINST_YOUR_OWN_CLAN = 1610; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â¶Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’.
    public static final int PARTY_LEADER_S1 = 1611; // Ã�â€ºÃ�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬ Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹: $c1
    public static final int _WAR_LIST_ = 1612; // =====<WAR_LIST>=====
    public static final int THERE_IS_NO_CLAN_LISTED_ON_WAR_LIST = 1613; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²,
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½.
    public static final int YOU_ARE_PARTICIPATING_IN_THE_CHANNEL_WHICH_HAS_BEEN_ALREADY_OPENED = 1614; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã‘Æ’.
    public static final int THE_NUMBER_OF_REMAINING_PARTIES_IS_S1_UNTIL_A_CHANNEL_IS_ACTIVATED = 1615; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿
    // Ã�Â´Ã�Â¾
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°:
    // $s1.
    public static final int THE_COMMAND_CHANNEL_HAS_BEEN_ACTIVATED = 1616; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOU_DO_NOT_HAVE_AUTHORITY_TO_USE_THE_COMMAND_CHANNEL = 1617; // Ã�Â£
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int THE_FERRY_FROM_RUNE_HARBOR_TO_GLUDIN_HARBOR_HAS_BEEN_DELAYED = 1618; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°,
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_TO_RUNE_HARBOR_HAS_BEEN_DELAYED = 1619; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int ARRIVED_AT_RUNE_HARBOR = 1620; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’ Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã�Â» Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’ Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹.
    public static final int DEPARTURE_FOR_GLUDIN_HARBOR_WILL_TAKE_PLACE_IN_FIVE_MINUTES = 1621; // Ã�Å¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘Æ’
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int DEPARTURE_FOR_GLUDIN_HARBOR_WILL_TAKE_PLACE_IN_ONE_MINUTE = 1622; // Ã�Å¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘Æ’
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Æ’.
    public static final int MAKE_HASTE__WE_WILL_BE_DEPARTING_FOR_GLUDIN_HARBOR_SHORTLY = 1623; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int WE_ARE_NOW_DEPARTING_FOR_GLUDIN_HARBOR__HOLD_ON_AND_ENJOY_THE_RIDE = 1624; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int WILL_LEAVE_FOR_RUNE_HARBOR_AFTER_ANCHORING_FOR_TEN_MINUTES = 1625; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹.
    public static final int WILL_LEAVE_FOR_RUNE_HARBOR_IN_FIVE_MINUTES = 1626; // Ã�Å¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int WILL_LEAVE_FOR_RUNE_HARBOR_IN_ONE_MINUTE = 1627; // Ã�Å¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Æ’.
    public static final int LEAVING_SOON_FOR_RUNE_HARBOR = 1628; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’ Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸ Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Â² Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int LEAVING_FOR_RUNE_HARBOR = 1629; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’ Ã�Â¸Ã�Â· Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹ Ã�Â² Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_15_MINUTES = 1630; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 15
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_10_MINUTES = 1631; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_5_MINUTES = 1632; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int THE_FERRY_FROM_RUNE_HARBOR_WILL_BE_ARRIVING_AT_GLUDIN_HARBOR_IN_APPROXIMATELY_1_MINUTE = 1633; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Æ’.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_15_MINUTES = 1634; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 15
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_10_MINUTES = 1635; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_5_MINUTES = 1636; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int THE_FERRY_FROM_GLUDIN_HARBOR_WILL_BE_ARRIVING_AT_RUNE_HARBOR_IN_APPROXIMATELY_1_MINUTE = 1637; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�â€œÃ�Â»Ã‘Æ’Ã�Â´Ã�Â¸Ã�Â½Ã�Â°
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Æ’.
    public static final int YOU_CANNOT_FISH_WHILE_USING_A_RECIPE_BOOK_PRIVATE_MANUFACTURE_OR_PRIVATE_STORE = 1638; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Â°Ã‘â€¦
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸,
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¸
    // Ã�ÂºÃ�Â½Ã�Â¸Ã�Â³Ã�Â¸
    // Ã‘â‚¬Ã�ÂµÃ‘â€ Ã�ÂµÃ�Â¿Ã‘â€šÃ�Â¾Ã�Â².
    public static final int OLYMPIAD_PERIOD_S1_HAS_STARTED = 1639; // $s1 Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    public static final int OLYMPIAD_PERIOD_S1_HAS_ENDED = 1640; // $s1 Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    public static final int THE_OLYMPIAD_GAME_HAS_STARTED = 1641; // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int THE_OLYMPIAD_GAME_HAS_ENDED = 1642; // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int CURRENT_LOCATION_S1_S2_S3_DIMENSION_GAP = 1643; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â Ã�Â°Ã�Â·Ã�Â»Ã�Â¾Ã�Â¼
    // Ã�Å“Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�Å“Ã�Â¸Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¸)
    public static final int NONE_1644 = 1644; // Ã�â€™Ã‘â€¹ Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ: $s1 Ã‘â€¡ $s2 Ã�Â¼Ã�Â¸Ã�Â½.
    // Ã�ï¿½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾ Ã�Â¾Ã‘â€šÃ�Â´Ã�Â¾Ã‘â€¦Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’: $s3 Ã‘â€¡ $s4
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int NONE_1645 = 1645; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸ Ã�â€™Ã‘â€¹ Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’ Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ 3 Ã‘â€¡, Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°Ã�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ, Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’ Ã�Â²Ã‘â€¹Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹ Ã�Â¸ Ã�Â¾Ã‘â€šÃ�Â´Ã�Â¾Ã‘â€¦Ã�Â½Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int NONE_1646 = 1646; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸ Ã�â€™Ã‘â€¹ Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ 3 Ã‘â€¡,
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ�Â¼Ã‘â€¹Ã�Â¹ Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€š Ã�Â¸ Ã‘Ë†Ã�Â°Ã�Â½Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â² Ã‘ï¿½Ã�Â¾Ã�ÂºÃ‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â´Ã�Â²Ã�Â¾Ã�Âµ, Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’ Ã�Â²Ã‘â€¹Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¸Ã�Â· Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â¸ Ã�Â¾Ã‘â€šÃ�Â´Ã�Â¾Ã‘â€¦Ã�Â½Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int NONE_1647 = 1647; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸ Ã�â€™Ã‘â€¹ Ã�Â² Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ 5 Ã‘â€¡, Ã‘â€šÃ�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€šÃ�Âµ Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’ Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€š Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹, Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’ Ã�Â²Ã‘â€¹Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹ Ã�Â¸ Ã�Â¾Ã‘â€šÃ�Â´Ã�Â¾Ã‘â€¦Ã�Â½Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int NONE_1648 = 1648; // Ã�Å¸Ã‘â‚¬Ã�Â¸ Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â¶Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸ Ã�Â² Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹ Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½ Ã�Â²Ã‘ï¿½Ã�Âµ Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â½Ã�Â¾ Ã�Â¸Ã�Â´Ã�ÂµÃ‘â€š.
    public static final int PLAY_TIME_IS_NOW_ACCUMULATING = 1649; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸.
    public static final int DUE_TO_A_LARGE_NUMBER_OF_USERS_CURRENTLY_ACCESSING_OUR_SERVER_YOUR_LOGIN_ATTEMPT_HAS_FAILED_PLEASE_WAIT_A_LITTLE_WHILE_AND_ATTEMPT_TO_LOG_IN_AGAIN = 1650; // Ã�Â¡Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int THE_OLYMPIAD_GAME_IS_NOT_CURRENTLY_IN_PROGRESS = 1651; // Ã�ï¿½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã�ÂµÃ�Â´Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_VIDEO_RECORDING_OF_THE_REPLAY_WILL_NOW_BEGIN = 1652; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int THE_REPLAY_FILE_HAS_BEEN_STORED_SUCCESSFULLY_S1 = 1653; // Ã�â€”Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½
    // ($s1).
    public static final int THE_ATTEMPT_TO_RECORD_THE_REPLAY_FILE_HAS_FAILED = 1654; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int YOU_HAVE_CAUGHT_A_MONSTER = 1655; // Ã�â€™Ã‘â€¹ Ã�Â¿Ã�Â¾Ã�Â¹Ã�Â¼Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°!
    public static final int YOU_HAVE_SUCCESSFULLY_TRADED_THE_ITEM_WITH_THE_NPC = 1656; // Ã�Å¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½
    // Ã‘ï¿½
    // NPC
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int C1_HAS_EARNED_S2_POINTS_IN_THE_GRAND_OLYMPIAD_GAMES = 1657; // $c1
    // -
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â»Ã�Â»Ã�Â¾Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘â€¹:
    // $s2.
    public static final int C1_HAS_LOST_S2_POINTS_IN_THE_GRAND_OLYMPIAD_GAMES = 1658; // $c1
    // -
    // Ã�Â¿Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â»Ã�Â»Ã�Â¾Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘â€¹:
    // $s2.
    public static final int CURRENT_LOCATION_S1_S2_S3_CEMETERY_OF_THE_EMPIRE = 1659; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Å¡Ã�Â»Ã�Â°Ã�Â´Ã�Â±Ã�Â¸Ã‘â€°Ã�Âµ
    // Ã�ËœÃ�Â¼Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¸)
    public static final int THE_CHANNEL_WAS_OPENED_BY_S1 = 1660; // Ã�Â¡Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°: $c1.
    public static final int S1_HAS_OBTAINED_S3_S2S = 1661; // $c1 Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š $s2
    // ($s3 Ã‘Ë†Ã‘â€š.)
    public static final int IF_YOU_FISH_IN_ONE_SPOT_FOR_A_LONG_TIME_THE_SUCCESS_RATE_OF_A_FISH_TAKING_THE_BAIT_BECOMES_LOWER__PLEASE_MOVE_TO_ANOTHER_PLACE_AND_CONTINUE_YOUR_FISHING_THERE = 1662; // Ã�Â¨Ã�Â°Ã�Â½Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¹Ã�Â¼Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã‘Æ’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ.
    public static final int THE_CLANS_EMBLEM_WAS_SUCCESSFULLY_REGISTERED__ONLY_A_CLAN_THAT_OWNS_A_CLAN_HALL_OR_A_CASTLE_CAN_GET_THEIR_EMBLEM_DISPLAYED_ON_CLAN_RELATED_ITEMS = 1663; // Ã�Â­Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int BECAUSE_THE_FISH_IS_RESISTING_THE_FLOAT_IS_BOBBING_UP_AND_DOWN_A_LOT = 1664; // Ã�Â Ã‘â€¹Ã�Â±Ã�Â°
    // Ã‘ï¿½Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½,
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã�Â²Ã�Â¾Ã�Âº
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int SINCE_THE_FISH_IS_EXHAUSTED_THE_FLOAT_IS_MOVING_ONLY_SLIGHTLY = 1665; // Ã�Â Ã‘â€¹Ã�Â±Ã�Â°
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â°
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã‘â€¡Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int YOU_HAVE_OBTAINED__S1_S2 = 1666; // Ã�â€™Ã‘â€¹ Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸ +$s1
    // $s2.
    public static final int LETHAL_STRIKE = 1667; // Ã�Â¡Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¹ Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬!
    public static final int YOUR_LETHAL_STRIKE_WAS_SUCCESSFUL = 1668; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾!
    public static final int THERE_WAS_NOTHING_FOUND_INSIDE_OF_THAT = 1669; // Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int SINCE_THE_SKILL_LEVEL_OF_REELING_PUMPING_IS_HIGHER_THAN_THE_LEVEL_OF_YOUR_FISHING_MASTERY_A_PENALTY_OF_S1_WILL_BE_APPLIED = 1670; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‚Â«Ã�Å¸Ã�Â¾Ã�Â´Ã‘ï¿½Ã�ÂµÃ‘â€¡Ã‘Å’Ã‚Â»
    // (Ã‚Â«Ã�Å¸Ã�Â¾Ã�Â´Ã‘â€šÃ‘ï¿½Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’Ã‚Â»)
    // Ã�Â½Ã�Â°
    // 3
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â¾Ã�Â²Ã�Â»Ã�Â¸.
    public static final int YOUR_REELING_WAS_SUCCESSFUL_MASTERY_PENALTYS1_ = 1671; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘ï¿½Ã�ÂµÃ�ÂºÃ�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾!
    // (Ã‘Ë†Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ž:
    // $
    // s1)
    public static final int YOUR_PUMPING_WAS_SUCCESSFUL_MASTERY_PENALTYS1_ = 1672; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ‘ï¿½Ã�Â³Ã�Â¸Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾!
    // (Ã‘Ë†Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ž:
    // $
    // s1)
    public static final int THE_CURRENT_FOR_THIS_OLYMPIAD_IS_S1_WINS_S2_DEFEATS_S3_YOU_HAVE_EARNED_S4_OLYMPIAD_POINTS = 1673; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘â‚¬Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€š
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ:
    // Ã�Â¼Ã�Â°Ã‘â€šÃ‘â€¡Ã�ÂµÃ�Â¹
    // -
    // $s1,
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´
    // -
    // $s2,
    // Ã�Â¿Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // -
    // $s3.
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â»Ã�Â»Ã�Â¾Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘â€¹
    // -
    // $s4.
    public static final int THIS_COMMAND_CAN_ONLY_BE_USED_BY_A_NOBLESSE = 1674; // Ã�Â­Ã‘â€šÃ‘Æ’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘Æ’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â´Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â½.
    public static final int A_MANOR_CANNOT_BE_SET_UP_BETWEEN_6_AM_AND_8_PM = 1675; // Ã�â€™Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // c
    // 6:00
    // Ã�Â´Ã�Â¾
    // 20:00.
    public static final int SINCE_A_SERVITOR_OR_A_PET_DOES_NOT_EXIST_AUTOMATIC_USE_IS_NOT_APPLICABLE = 1676; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    // Ã�ï¿½Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã�Âµ
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹.
    public static final int A_CEASE_FIRE_DURING_A_CLAN_WAR_CAN_NOT_BE_CALLED_WHILE_MEMBERS_OF_YOUR_CLAN_ARE_ENGAGED_IN_BATTLE = 1677; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘Æ’.
    public static final int YOU_HAVE_NOT_DECLARED_A_CLAN_WAR_TO_S1_CLAN = 1678; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘Æ’
    // $s1.
    public static final int ONLY_THE_CREATOR_OF_A_CHANNEL_CAN_ISSUE_A_GLOBAL_COMMAND = 1679; // Ã�â€™Ã‘ï¿½Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°.
    public static final int S1_HAS_DECLINED_THE_CHANNEL_INVITATION = 1680; // $c1
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â».
    public static final int SINCE_S1_DID_NOT_RESPOND_YOUR_CHANNEL_INVITATION_HAS_FAILED = 1681; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€¡Ã�Â°Ã�ÂµÃ‘â€š.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int ONLY_THE_CREATOR_OF_A_CHANNEL_CAN_USE_THE_CHANNEL_DISMISS_COMMAND = 1682; // Ã�ËœÃ‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°.
    public static final int ONLY_A_PARTY_LEADER_CAN_CHOOSE_THE_OPTION_TO_LEAVE_A_CHANNEL = 1683; // Ã�Å¸Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int WHILE_A_CLAN_IS_BEING_DISSOLVED_IT_IS_IMPOSSIBLE_TO_DECLARE_A_CLAN_WAR_AGAINST_IT = 1684; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â´Ã�Â¸Ã�Â¸
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int IF_YOUR_PK_COUNT_IS_1_OR_MORE_YOU_ARE_NOT_ALLOWED_TO_WEAR_THIS_ITEM = 1685; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // PK
    // >
    // 0.
    public static final int THE_CASTLE_WALL_HAS_SUSTAINED_DAMAGE = 1686; // Ã�Â¡Ã‘â€šÃ�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int THIS_AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_ATOP_OF_A_WYVERN_YOU_WILL_BE_DISMOUNTED_FROM_YOUR_WYVERN_IF_YOU_DO_NOT_LEAVE = 1687; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¸Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Âµ.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã�Â¸,
    // Ã�Â²Ã�Â°Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½
    // Ã�Â²Ã�Â¸Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹.
    public static final int YOU_CANNOT_PRACTICE_ENCHANTING_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_MANUFACTURING_WORKSHOP = 1688; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã�Â¸.
    public static final int YOU_ARE_ALREADY_ON_THE_WAITING_LIST_TO_PARTICIPATE_IN_THE_GAME_FOR_YOUR_CLASS = 1689; // $c1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½Ã�Â¼Ã�Â¸
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€žÃ�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¸Ã�Â¸.
    public static final int YOU_ARE_ALREADY_ON_THE_WAITING_LIST_FOR_ALL_CLASSES_WAITING_TO_PARTICIPATE_IN_THE_GAME = 1690; // $c1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Â²Ã�Â½Ã�ÂµÃ�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int SINCE_80_PERCENT_OR_MORE_OF_YOUR_INVENTORY_SLOTS_ARE_FULL_YOU_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD = 1691; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â¹Ã�ÂºÃ�Â¸
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â½Ã�Â°
    // 80%.
    public static final int SINCE_YOU_HAVE_CHANGED_YOUR_CLASS_INTO_A_SUB_JOB_YOU_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD = 1692; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½.
    public static final int WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME = 1693; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â±Ã�Â»Ã‘Å½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Â¾Ã�Â¹,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â²Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int ONLY_A_CLAN_LEADER_THAT_IS_A_NOBLESSE_CAN_VIEW_THE_SIEGE_WAR_STATUS_WINDOW_DURING_A_SIEGE_WAR = 1694; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�Â½
    // Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â½Ã�Â¾Ã�Â¼.
    public static final int IT_CAN_BE_USED_ONLY_WHILE_A_SIEGE_WAR_IS_TAKING_PLACE = 1695; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int IF_THE_ACCUMULATED_ONLINE_ACCESS_TIME_IS_S1_OR_MORE_A_PENALTY_WILL_BE_IMPOSED__PLEASE_TERMINATE_YOUR_SESSION_AND_TAKE_A_BREAK = 1696; // none
    public static final int SINCE_YOUR_CUMULATIVE_ACCESS_TIME_HAS_EXCEEDED_S1_YOUR_EXP_AND_ITEM_DROP_RATE_WERE_REDUCED_BY_HALF_PLEASE_TERMINATE_YOUR_SESSION_AND_TAKE_A_BREAK = 1697; // none
    public static final int SINCE_YOUR_CUMULATIVE_ACCESS_TIME_HAS_EXCEEDED_S1_YOU_NO_LONGER_HAVE_EXP_OR_ITEM_DROP_PRIVILEGE__PLEASE_TERMINATE_YOUR_SESSION_AND_TAKE_A_BREAK = 1698; // none
    public static final int YOU_CANNOT_DISMISS_A_PARTY_MEMBER_BY_FORCE = 1699; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int YOU_DONT_HAVE_ENOUGH_SPIRITSHOTS_NEEDED_FOR_A_PET_SERVITOR = 1700; // Ã�ï¿½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â¾Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘â€¦Ã�Â°
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°/Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸.
    public static final int YOU_DONT_HAVE_ENOUGH_SOULSHOTS_NEEDED_FOR_A_PET_SERVITOR = 1701; // Ã�ï¿½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�Â¾Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â¸
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°/Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸.
    public static final int THE_USER_WHO_CONDUCTED_A_SEARCH_A_MOMENT_AGO_HAS_BEEN_CONFIRMED_TO_BE_A_BOT_USER = 1702; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $s1
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â½
    // Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â½Ã�Â°Ã�Â½
    // Ã�Â±Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â¼.
    public static final int THE_USER_WHO_CONDUCTED_A_SEARCH_A_MOMENT_AGO_HAS_BEEN_CONFIRMED_TO_BE_A_NONBOT_USER = 1703; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $s1
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â½
    // -
    // Ã�Â±Ã�Â¾Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½.
    public static final int PLEASE_CLOSE_THE_SETUP_WINDOW_FOR_A_PRIVATE_MANUFACTURING_STORE_OR_THE_SETUP_WINDOW_FOR_A_PRIVATE_STORE_AND_TRY_AGAIN = 1704; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�ÂµÃ�Âº
    // Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸/Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    // Pc Bang Points
    public static final int PC_BANG_POINTS_ACQUISITION_PERIOD_PONTS_ACQUISITION_PERIOD_LEFT_S1_HOUR = 1705; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // PC
    // Bang.
    // Ã�Å¾Ã�Â½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // $s1
    // Ã‘â€¡.
    public static final int PC_BANG_POINTS_USE_PERIOD_POINTS_USE_PERIOD_LEFT_S1_HOUR = 1706; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // PC
    // Bang.
    // Ã�Å¾Ã�Â½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // $s1
    // Ã‘â€¡.
    public static final int YOU_ACQUIRED_S1_PC_BANG_POINT = 1707; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â² PC
    // Bang: $s1.
    public static final int DOUBLE_POINTS_YOU_AQUIRED_S1_PC_BANG_POINT = 1708; // Ã�Å¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã‘Æ’Ã�Â´Ã�Â²Ã�Â°Ã�Â¸Ã�Â²Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½!
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // PC
    // Bang:
    // $s1.
    public static final int YOU_ARE_USING_S1_POINT = 1709; // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²: $s1.
    public static final int YOU_ARE_SHORT_OF_ACCUMULATED_POINTS = 1710; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â².
    public static final int PC_BANG_POINTS_USE_PERIOD_HAS_EXPIRED = 1711; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // PC
    // Bang
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Âº.
    public static final int THE_PC_BANG_POINTS_ACCUMULATION_PERIOD_HAS_EXPIRED = 1712; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // PC
    // Bang
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Âº.
    public static final int THE_MATCH_MAY_BE_DELAYED_DUE_TO_NOT_ENOUGH_COMBATANTS = 1713; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã�Â½Ã�ÂµÃ‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â´Ã�Â½Ã�ÂµÃ�Âµ.
    public static final int THIS_IS_A_PEACEFUL_ZONE__N__PVP_IS_NOT_ALLOWED_IN_THIS_AREA = 1715; // Ã�Å“Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�â€”Ã�Â¾Ã�Â½Ã�Â°
    // \\n-
    // PvP
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int ALTERED_ZONE = 1716; // Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½ Ã�â€”Ã�Â¾Ã�Â½Ã�Â°
    public static final int SIEGE_WAR_ZONE___N__A_SIEGE_IS_CURRENTLY_IN_PROGRESS_IN_THIS_AREA____N_IF_A_CHARACTER_DIES_IN = 1717; // Ã�â€”Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�â€”Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // \\n-
    // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    // \\n-
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int GENERAL_FIELD = 1718; // Ã�Å¾Ã�Â±Ã‘â€¹Ã‘â€¡Ã�Â½Ã�Â°Ã‘ï¿½ Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘ï¿½
    public static final int SEVEN_SIGNS_ZONE___N__ALTHOUGH_A_CHARACTER_S_LEVEL_MAY_INCREASE_WHILE_IN_THIS_AREA_HP_AND_MP___N = 1719; // Ã�â€”Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¹\\n-
    // Ã�â€™Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’,
    // Ã�Â½Ã�Â¾
    // HP
    // Ã�Â¸
    // MP\\n
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int ___ = 1720; // ---
    public static final int COMBAT_ZONE = 1721; // Ã�â€˜Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã‘ï¿½ Ã�â€”Ã�Â¾Ã�Â½Ã�Â°
    public static final int PLEASE_ENTER_THE_NAME_OF_THE_ITEM_YOU_WISH_TO_SEARCH_FOR = 1722; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã�Â²Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸.
    public static final int PLEASE_TAKE_A_MOMENT_TO_PROVIDE_FEEDBACK_ABOUT_THE_PETITION_SERVICE = 1723; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€šÃ�Â¸Ã‘â‚¬Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â°Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã�Â±Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹.
    public static final int A_SERVITOR_WHOM_IS_ENGAGED_IN_BATTLE_CANNOT_BE_DE_ACTIVATED = 1724; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â²Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘Æ’,
    // Ã‘â€šÃ�Â¾
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’.
    public static final int YOU_HAVE_EARNED_S1_RAID_POINTS = 1725; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘â‚¬Ã�ÂµÃ�Â¹Ã�Â´Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²:
    // $s1.
    public static final int S1_HAS_DISAPPEARED_BECAUSE_ITS_TIME_PERIOD_HAS_EXPIRED = 1726; // $s1
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½.
    public static final int C1_HAS_INVITED_YOU_TO_A_PARTY_ROOM_DO_YOU_ACCEPT = 1727; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’?
    public static final int THE_RECIPIENT_OF_YOUR_INVITATION_DID_NOT_ACCEPT_THE_PARTY_MATCHING_INVITATION = 1728; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ�Â°.
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int YOU_CANNOT_JOIN_A_COMMAND_CHANNEL_WHILE_TELEPORTING = 1729; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã‘Æ’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    public static final int TO_ESTABLISH_A_CLAN_ACADEMY_YOUR_CLAN_MUST_BE_LEVEL_5_OR_HIGHER = 1730; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â°Ã�ÂºÃ�Â°Ã�Â´Ã�ÂµÃ�Â¼Ã�Â¸Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã‘Æ’Ã�Â¶Ã�Â½Ã�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�Âµ
    // 5.
    public static final int ONLY_THE_CLAN_LEADER_CAN_CREATE_A_CLAN_ACADEMY = 1731; // Ã�ï¿½Ã�ÂºÃ�Â°Ã�Â´Ã�ÂµÃ�Â¼Ã�Â¸Ã‘Å½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int TO_CREATE_A_CLAN_ACADEMY_A_BLOOD_MARK_IS_NEEDED = 1732; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â°Ã�ÂºÃ�Â°Ã�Â´Ã�ÂµÃ�Â¼Ã�Â¸Ã�Â¸
    // Ã�Â½Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Å“Ã�ÂµÃ‘â€šÃ�ÂºÃ�Â°
    // Ã�Å¡Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¸.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_ADENA_TO_CREATE_A_CLAN_ACADEMY = 1733; // Ã�ï¿½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â°Ã�ÂºÃ�Â°Ã�Â´Ã�ÂµÃ�Â¼Ã�Â¸Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int TO_JOIN_A_CLAN_ACADEMY_CHARACTERS_MUST_BE_LEVEL_76_OR_BELOW_NOT_BELONG_ANOTHER_CLAN_AND_NOT_YET_COMPLETED_THEIR_4TH_CLASS_TRANSFER = 1734; 
    public static final int S1_DOES_NOT_MEET_THE_REQUIREMENTS_TO_JOIN_A_CLAN_ACADEMY = 1735; // $s1
    public static final int THE_CLAN_ACADEMY_HAS_REACHED_ITS_MAXIMUM_ENROLLMENT = 1736; // Ã�Å“Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾

    public static final int YOUR_CLAN_HAS_NOT_ESTABLISHED_A_CLAN_ACADEMY_BUT_IS_ELIGIBLE_TO_DO_SO = 1737; // Ã�â€™

    public static final int YOUR_CLAN_HAS_ALREADY_ESTABLISHED_A_CLAN_ACADEMY = 1738; // Ã�â€™

    public static final int WOULD_YOU_LIKE_TO_CREATE_A_CLAN_ACADEMY = 1739; // Ã�â€™Ã‘â€¹

    public static final int PLEASE_ENTER_THE_NAME_OF_THE_CLAN_ACADEMY = 1740; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ

    public static final int CONGRATULATIONS_THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED = 1741; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼!

    public static final int A_MESSAGE_INVITING_S1_TO_JOIN_THE_CLAN_ACADEMY_IS_BEING_SENT = 1742; // $s1:

    public static final int TO_OPEN_A_CLAN_ACADEMY_THE_LEADER_OF_A_LEVEL_5_CLAN_OR_ABOVE_MUST_PAY_XX_PROOFS_OF_BLOOD_OR_A_CERTAIN_AMOUNT_OF_ADENA = 1743; // Ã�â€�Ã�Â»Ã‘ï¿½

    public static final int THERE_WAS_NO_RESPONSE_TO_YOUR_INVITATION_TO_JOIN_THE_CLAN_ACADEMY_SO_THE_INVITATION_HAS_BEEN_RESCINDED = 1744;
    public static final int THE_RECIPIENT_OF_YOUR_INVITATION_TO_JOIN_THE_CLAN_ACADEMY_HAS_DECLINED = 1745;
    public static final int YOU_HAVE_ALREADY_JOINED_A_CLAN_ACADEMY = 1746;
    public static final int S1_HAS_SENT_YOU_AN_INVITATION_TO_JOIN_THE_CLAN_ACADEMY_BELONGING_TO_THE_S2_CLAN_DO_YOU_ACCEPT = 1747;
    public static final int CLAN_ACADEMY_MEMBER_S1_HAS_SUCCESSFULLY_COMPLETED_THE_4TH_CLASS_TRANSFER_AND_OBTAINED_S2_CLAN_REPUTATION_POINTS = 1748;
    public static final int CONGRATULATIONS_YOU_WILL_NOW_GRADUATE_FROM_THE_CLAN_ACADEMY_AND_LEAVE_YOUR_CURRENT_CLAN_AS_A_GRADUATE_OF_THE_ACADEMY_YOU_CAN_IMMEDIATELY_JOIN_A_CLAN_AS_A_REGULAR_MEMBER_WITHOUT_BEING_SUBJECT_TO_ANY_PENALTIES = 1749;
    public static final int C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_THE_OWNER_OF_S2_CANNOT_PARTICIPATE_IN_THE = 1750;
    public static final int THE_GRAND_MASTER_HAS_GIVEN_YOU_A_COMMEMORATIVE_ITEM = 1751;
    public static final int SINCE_THE_CLAN_HAS_RECEIVED_A_GRADUATE_OF_THE_CLAN_ACADEMY_IT_HAS_EARNED_S1_POINTS_TOWARD_ITS_REPUTATION_SCORE = 1752;
    public static final int THE_CLAN_LEADER_HAS_DECREED_THAT_THAT_PARTICULAR_PRIVILEGE_CANNOT_BE_GRANTED_TO_A_CLAN_ACADEMY = 1753; 
    public static final int THAT_PRIVILEGE_CANNOT_BE_GRANTED_TO_A_CLAN_ACADEMY_MEMBER = 1754;
    public static final int S2_HAS_BEEN_DESIGNATED_AS_THE_APPRENTICE_OF_CLAN_MEMBER_S1 = 1755;
    public static final int S1_YOUR_CLAN_ACADEMYS_APPRENTICE_HAS_LOGGED_IN = 1756; 
    public static final int S1_YOUR_CLAN_ACADEMYS_APPRENTICE_HAS_LOGGED_OUT = 1757;
    public static final int S1_YOUR_CLAN_ACADEMYS_SPONSOR_HAS_LOGGED_IN = 1758;
    public static final int S1_YOUR_CLAN_ACADEMYS_SPONSOR_HAS_LOGGED_OUT = 1759;
    public static final int CLAN_MEMBER_S1S_TITLE_HAS_BEEN_CHANGED_TO_S2 = 1760;
    public static final int CLAN_MEMBER_S1S_PRIVILEGE_LEVEL_HAS_BEEN_CHANGED_TO_S2 = 1761; 
    public static final int YOU_DO_NOT_HAVE_THE_RIGHT_TO_DISMISS_AN_APPRENTICE = 1762; 
    public static final int S2_CLAN_MEMBER_S1S_APPRENTICE_HAS_BEEN_REMOVED = 1763;
    public static final int THIS_ITEM_CAN_ONLY_BE_WORN_BY_A_MEMBER_OF_THE_CLAN_ACADEMY = 1764; 
    public static final int AS_A_GRADUATE_OF_THE_CLAN_ACADEMY_YOU_CAN_NO_LONGER_WEAR_THIS_ITEM = 1765; 
    public static final int AN_APPLICATION_TO_JOIN_THE_CLAN_HAS_BEEN_SENT_TO_S1_IN_S2 = 1766; 
    public static final int AN_APPLICATION_TO_JOIN_THE_CLAN_ACADEMY_HAS_BEEN_SENT_TO_S1 = 1767;
    public static final int C1_HAS_INVITED_YOU_TO_JOIN_THE_CLAN_ACADEMY_OF_S2_CLAN_WOULD_YOU_LIKE_TO_JOIN = 1768;
    public static final int C1_HAS_SENT_YOU_AN_INVITATION_TO_JOIN_THE_S3_ORDER_OF_KNIGHTS_UNDER_THE_S2_CLAN_WOULD_YOU_LIKE = 1769; // $c1
    public static final int THE_CLAN_REPUTATION_SCORE_HAS_DROPPED_BELOW_0_THE_CLAN_MAY_FACE_CERTAIN_PENALTIES_AS_A_RESULT = 1770; // Ã�Å¸Ã‘â‚¬Ã�Â¸
    public static final int NOW_THAT_YOUR_CLAN_LEVEL_IS_ABOVE_LEVEL_5_IT_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS = 1771; // Ã�Å¾Ã‘â€¡Ã�ÂºÃ�Â¸
    public static final int SINCE_YOUR_CLAN_WAS_DEFEATED_IN_A_SIEGE_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE_AND_GIVEN_TO_THE_OPPOSING_CLAN = 1772; 
    public static final int SINCE_YOUR_CLAN_EMERGED_VICTORIOUS_FROM_THE_SIEGE_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_REPUTATION_SCORE = 1773; 
    public static final int YOUR_CLAN_NEWLY_ACQUIRED_CONTESTED_CLAN_HALL_HAS_ADDED_S1_POINTS_TO_YOUR_CLAN_REPUTATION_SCORE = 1774; 
    public static final int CLAN_MEMBER_S1_WAS_AN_ACTIVE_MEMBER_OF_THE_HIGHEST_RANKED_PARTY_IN_THE_FESTIVAL_OF_DARKNESS_S2_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_REPUTATION_SCORE = 1775; 
    public static final int CLAN_MEMBER_S1_WAS_NAMED_A_HERO_2S_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_REPUTATION_SCORE = 1776; 
    public static final int YOU_HAVE_SUCCESSFULLY_COMPLETED_A_CLAN_QUEST_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLAN_REPUTATION_SCORE = 1777; // Ã�â€™Ã‘â€¹

    public static final int AN_OPPOSING_CLAN_HAS_CAPTURED_YOUR_CLAN_CONTESTED_CLAN_HALL_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE = 1778; // Ã�â€™Ã‘â‚¬Ã�Â°Ã�Â¶Ã�Â´Ã�ÂµÃ�Â±Ã�Â½Ã‘â€¹Ã�Â¹
    public static final int AFTER_LOSING_THE_CONTESTED_CLAN_HALL_300_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE = 1779; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ

    public static final int YOUR_CLAN_HAS_CAPTURED_YOUR_OPPONENT_CONTESTED_CLAN_HALL_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_OPPONENT_CLAN_REPUTATION_SCORE = 1780; // Ã�â€™Ã�Â°Ã‘Ë†

    public static final int YOUR_CLAN_HAS_ADDED_1S_POINTS_TO_ITS_CLAN_REPUTATION_SCORE = 1781; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾

    public static final int YOUR_CLAN_MEMBER_S1_WAS_KILLED_S2_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE_AND_ADDED_TO_YOUR_OPPONENT_CLAN_REPUTATION_SCORE = 1782; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½
    public static final int FOR_KILLING_AN_OPPOSING_CLAN_MEMBER_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_OPPONENTS_CLAN_REPUTATION_SCORE = 1783; // Ã�â€”Ã�Â°
    public static final int YOUR_CLAN_HAS_FAILED_TO_DEFEND_THE_CASTLE_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE_AND_ADDED_TO_YOUR_OPPONENTS = 1784; // Ã�â€™Ã�Â°Ã‘Ë†
    public static final int THE_CLAN_YOU_BELONG_TO_HAS_BEEN_INITIALIZED_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE = 1785; // Ã�â€™Ã�Â°Ã‘Ë†
    public static final int YOUR_CLAN_HAS_FAILED_TO_DEFEND_THE_CASTLE_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOUR_CLAN_REPUTATION_SCORE = 1786; // Ã�â€™Ã�Â°Ã‘Ë†
    public static final int S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_THE_CLAN_REPUTATION_SCORE = 1787; // Ã�Å¸Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â½Ã�Â¾
    public static final int THE_CLAN_SKILL_S1_HAS_BEEN_ADDED = 1788; // Ã�â€�Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    public static final int SINCE_THE_CLAN_REPUTATION_SCORE_HAS_DROPPED_TO_0_OR_LOWER_YOUR_CLAN_SKILLS_WILL_BE_DE_ACTIVATED = 1789; // Ã�Â¡
    public static final int THE_CONDITIONS_NECESSARY_TO_INCREASE_THE_CLAN_LEVEL_HAVE_NOT_BEEN_MET = 1790; // Ã�Â£Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½,
    public static final int THE_CONDITIONS_NECESSARY_TO_CREATE_A_MILITARY_UNIT_HAVE_NOT_BEEN_MET = 1791; // Ã�Â£Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½,
    public static final int PLEASE_ASSIGN_A_MANAGER_FOR_YOUR_NEW_ORDER_OF_KNIGHTS = 1792; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    public static final int S1_HAS_BEEN_SELECTED_AS_THE_CAPTAIN_OF_S2 = 1793; // $c1
    public static final int THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED = 1794; 
    public static final int THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED = 1795; 
    public static final int FOR_KOREA_ONLY = 1796; 
    public static final int C1_HAS_BEEN_PROMOTED_TO_S2 = 1797; 
    public static final int CLAN_LORD_PRIVILEGES_HAVE_BEEN_TRANSFERRED_TO_C1 = 1798; // $c1
    public static final int CURRENTLY_UNDER_INVESTIGATION_PLEASE_WAIT = 1799; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    public static final int THE_USER_NAME_S1_HAS_A_HISTORY_OF_USING_THIRD_PARTY_PROGRAMS = 1800; // $c1
    public static final int THE_ATTEMPT_TO_SELL_HAS_FAILED = 1801; 
    public static final int THE_ATTEMPT_TO_TRADE_HAS_FAILED = 1802;
    public static final int YOU_CANNOT_REGISTER_FOR_A_MATCH = 1803; 
    public static final int THIS_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_7_DAYS = 1804; 
    public static final int THIS_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_30_DAYS_1 = 1805;
    public static final int THIS_ACCOUNT_HAS_BEEN_PERMANENTLY_BANNED_1 = 1806;
    public static final int THIS_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_30_DAYS_2 = 1807; 
    public static final int THIS_ACCOUNT_HAS_BEEN_PERMANENTLY_BANNED_2 = 1808; 
    public static final int ACCOUNT_OWNER_MUST_BE_VERIFIED_IN_ORDER_TO_USE_THIS_ACCOUNT_AGAIN = 1809;
    public static final int THE_REFUSE_INVITATION_STATE_HAS_BEEN_ACTIVATED = 1810; 
    public static final int THE_REFUSE_INVITATION_STATE_HAS_BEEN_REMOVED = 1811;
    public static final int SINCE_THE_REFUSE_INVITATION_STATE_IS_CURRENTLY_ACTIVATED_NO_INVITATION_CAN_BE_MADE = 1812; // Ã�Â¡
    public static final int THERE_IS_S1_HOUR_AND_S2_MINUTE_LEFT_OF_THE_FIXED_USAGE_TIME = 1813; // $s1:
    public static final int S2_MINUTE_OF_USAGE_TIME_ARE_LEFT_FOR_S1 = 1814;
    public static final int S2_WAS_DROPPED_IN_THE_S1_REGION = 1815;
    public static final int THE_OWNER_OF_S2_HAS_APPEARED_IN_THE_S1_REGION = 1816; // Ã�Å¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    public static final int S2_OWNER_HAS_LOGGED_INTO_THE_S1_REGION = 1817; // Ã�Å¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // $s2
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘â‚¬Ã�Â°Ã�Â¹Ã�Â¾Ã�Â½Ã�Âµ
    // $s1.
    public static final int S1_HAS_DISAPPEARED_CW = 1818; // $s1 Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·.
    public static final int AN_EVIL_IS_PULSATING_FROM_S2_IN_S1 = 1819; // Ã�â€”Ã�Â»Ã�Â¾
    // Ã�Â¿Ã‘Æ’Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â· $s2
    // Ã�Â² $s1.
    public static final int S1_IS_CURRENTLY_ASLEEP = 1820; // $s1 Ã�Â² Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½ Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘â€š.
    public static final int S2_S_EVIL_PRESENCE_IS_FELT_IN_S1 = 1821; // Ã�ï¿½Ã‘Æ’Ã‘â‚¬Ã�Â° Ã�Â·Ã�Â»Ã�Â°
    // $s2
    // Ã‘â€¡Ã‘Æ’Ã�Â²Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â² $s1.
    public static final int S1_HAS_BEEN_SEALED = 1822; // $s1 Ã�Â·Ã�Â°Ã�Â¿Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â°Ã�Â½.
    public static final int THE_REGISTRATION_PERIOD_FOR_A_CLAN_HALL_WAR_HAS_ENDED = 1823; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½.
    public static final int YOU_HAVE_BEEN_REGISTERED_FOR_A_CLAN_HALL_WAR__PLEASE_MOVE_TO_THE_LEFT_SIDE_OF_THE_CLAN_HALL_S = 1824; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â»Ã�ÂµÃ�Â²Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ‘â‚¬Ã�Â°Ã�Â¹
    // Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã‘Å’Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’.
    public static final int YOU_HAVE_FAILED_IN_YOUR_ATTEMPT_TO_REGISTER_FOR_THE_CLAN_HALL_WAR_PLEASE_TRY_AGAIN = 1825; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.
    public static final int IN_S1_MINUTES_THE_GAME_WILL_BEGIN_ALL_PLAYERS_MUST_HURRY_AND_MOVE_TO_THE_LEFT_SIDE_OF_THE_CLAN = 1826; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // Ã�â€™Ã‘ï¿½Ã�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â¸
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â»Ã�ÂµÃ�Â²Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ‘â‚¬Ã�Â°Ã�Â¹
    // Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int IN_S1_MINUTES_THE_GAME_WILL_BEGIN_ALL_PLAYERS_PLEASE_ENTER_THE_ARENA_NOW = 1827; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â¸,
    // Ã�Â¿Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â½Ã‘Æ’.
    public static final int IN_S1_SECONDS_THE_GAME_WILL_BEGIN = 1828; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THE_COMMAND_CHANNEL_IS_FULL = 1829; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â» Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    public static final int C1_IS_NOT_ALLOWED_TO_USE_THE_PARTY_ROOM_INVITE_COMMAND_PLEASE_UPDATE_THE_WAITING_LIST = 1830; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int C1_DOES_NOT_MEET_THE_CONDITIONS_OF_THE_PARTY_ROOM_PLEASE_UPDATE_THE_WAITING_LIST = 1831; // $c1
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½Ã�Â¼
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int ONLY_A_ROOM_LEADER_MAY_INVITE_OTHERS_TO_A_PARTY_ROOM = 1832; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘â€¦Ã�Â¾Ã�Â·Ã‘ï¿½Ã�Â¸Ã�Â½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int ALL_OF_S1_WILL_BE_DROPPED_WOULD_YOU_LIKE_TO_CONTINUE = 1833; // Ã�â€™Ã‘ï¿½Ã�Âµ
    // $s1
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã‘ï¿½Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_PARTY_ROOM_IS_FULL_NO_MORE_CHARACTERS_CAN_BE_INVITED_IN = 1834; // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�â€˜Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int S1_IS_FULL_AND_CANNOT_ACCEPT_ADDITIONAL_CLAN_MEMBERS_AT_THIS_TIME = 1835; // $s
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾Ã�Â².
    public static final int YOU_CANNOT_JOIN_A_CLAN_ACADEMY_BECAUSE_YOU_HAVE_SUCCESSFULLY_COMPLETED_YOUR_4TH_CLASS_TRANSFER = 1836;
    public static final int C1_HAS_SENT_YOU_AN_INVITATION_TO_JOIN_THE_S3_ROYAL_GUARD_UNDER_THE_S2_CLAN_WOULD_YOU_LIKE_TO = 1837;
    public static final int _1_THE_COUPON_CAN_BE_USED_ONCE_PER_CHARACTER = 1838;
    public static final int _2_A_USED_SERIAL_NUMBER_MAY_NOT_BE_USED_AGAIN = 1839;
    public static final int _3_IF_YOU_ENTER_THE_INCORRECT_SERIAL_NUMBER_MORE_THAN_5_TIMES__N___YOU_MAY_USE_IT_AGAIN_AFTER_A = 1840;
    public static final int THIS_CLAN_HALL_WAR_HAS_BEEN_CANCELLED__NOT_ENOUGH_CLANS_HAVE_REGISTERED = 1841;
    public static final int S1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT = 1842;
    public static final int S1_IS_ENGAGED_IN_COMBAT_AND_CANNOT_BE_SUMMONED = 1843;
    public static final int S1_IS_DEAD_AT_THE_MOMENT_AND_CANNOT_BE_SUMMONED = 1844;
    public static final int HERO_WEAPONS_CANNOT_BE_DESTROYED = 1845;
    public static final int YOU_ARE_TOO_FAR_AWAY_FROM_THE_FENRIR_TO_MOUNT_IT = 1846;
    public static final int YOU_CAUGHT_A_FISH_S1_IN_LENGTH = 1847;
    public static final int BECAUSE_OF_THE_SIZE_OF_FISH_CAUGHT_YOU_WILL_BE_REGISTERED_IN_THE_RANKING = 1848;
    public static final int ALL_OF_S1_WILL_BE_DISCARDED_WOULD_YOU_LIKE_TO_CONTINUE = 1849;
    public static final int THE_CAPTAIN_OF_THE_ORDER_OF_KNIGHTS_CANNOT_BE_APPOINTED = 1850;
    public static final int THE_CAPTAIN_OF_THE_ROYAL_GUARD_CANNOT_BE_APPOINTED = 1851;
    public static final int THE_ATTEMPT_TO_ACQUIRE_THE_SKILL_HAS_FAILED_BECAUSE_OF_AN_INSUFFICIENT_CLAN_REPUTATION_SCORE = 1852;
    public static final int QUANTITY_ITEMS_OF_THE_SAME_TYPE_CANNOT_BE_EXCHANGED_AT_THE_SAME_TIME = 1853;
    public static final int ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME_PLEASE_ENTER_A_DIFFERENT_NAME = 1855;
    public static final int SINCE_YOUR_OPPONENT_IS_NOW_THE_OWNER_OF_S1_THE_OLYMPIAD_HAS_BEEN_CANCELLED = 1856;
    public static final int SINCE_YOU_NOW_OWN_S1_YOU_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD = 1857;
    public static final int YOU_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD_WHILE_DEAD = 1858;
    public static final int YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_MOVED_AT_ONE_TIME = 1859;
    public static final int THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW = 1860;
    public static final int THE_CLANS_CREST_HAS_BEEN_DELETED = 1861;
    public static final int THE_CLAN_SKILL_WILL_BE_ACTIVATED_BECAUSE_THE_CLANS_REPUTATION_SCORE_HAS_REACHED_TO_0_OR_HIGHER = 1862;
    public static final int S1_PURCHASED_A_CLAN_ITEM_REDUCING_THE_CLAN_REPUTATION_BY_S2_POINTS = 1863;
    public static final int THE_PET_SERVITOR_IS_UNRESPONSIVE_AND_WILL_NOT_OBEY_ANY_ORDERS = 1864;
    public static final int THE_PET_SERVITOR_IS_CURRENTLY_IN_A_STATE_OF_DISTRESS = 1865;
    public static final int MP_WAS_REDUCED_BY_S1 = 1866;
    public static final int YOUR_OPPONENTS_MP_WAS_REDUCED_BY_S1 = 1867;
    public static final int YOU_CANNOT_EXCHANGE_AN_ITEM_WHILE_IT_IS_BEING_USED = 1868;
    public static final int S1_HAS_GRANTED_THE_CHANNELS_MASTER_PARTY_THE_PRIVILEGE_OF_ITEM_LOOTING = 1869;
    public static final int A_COMMAND_CHANNEL_WITH_THE_ITEM_LOOTING_PRIVILEGE_ALREADY_EXISTS = 1870;
    public static final int DO_YOU_WANT_TO_DISMISS_S1_FROM_THE_CLAN = 1871; // $c1
    // -
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°?
    public static final int YOU_HAVE_S1_HOURS_AND_S2_MINUTES_LEFT = 1872; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THERE_ARE_S1_HOURS_AND_S2_MINUTES_LEFT_IN_THE_FIXED_USE_TIME_FOR_THIS_PC_CARD = 1873; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘â€žÃ�Â¸Ã�ÂºÃ‘ï¿½Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â»Ã‘Æ’Ã�Â±Ã�Âµ:
    // $s1Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THERE_ARE_S1_MINUTES_LEFT_FOR_THIS_INDIVIDUAL_USER = 1874; // Ã�Â£
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THERE_ARE_S1_MINUTES_LEFT_IN_THE_FIXED_USE_TIME_FOR_THIS_PC_CARD = 1875; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã‘â€žÃ�Â¸Ã�ÂºÃ‘ï¿½Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â»Ã‘Æ’Ã�Â±Ã�Âµ:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int DO_YOU_WANT_TO_LEAVE_S1_CLAN = 1876; // Ã�Å¸Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½ $s1?
    public static final int THE_GAME_WILL_END_IN_S1_MINUTES = 1877; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â· $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_GAME_WILL_END_IN_S1_SECONDS = 1878; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â· $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int IN_S1_MINUTES_YOU_WILL_BE_TELEPORTED_OUTSIDE_OF_THE_GAME_ARENA = 1879; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int IN_S1_SECONDS_YOU_WILL_BE_TELEPORTED_OUTSIDE_OF_THE_GAME_ARENA = 1880; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¸Ã�Â·
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int THE_PRELIMINARY_MATCH_WILL_BEGIN_IN_S1_SECONDS_PREPARE_YOURSELF = 1881; // Ã�Å¾Ã‘â€šÃ�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€šÃ‘Æ’Ã‘â‚¬
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã‘Å’Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’.
    public static final int CHARACTERS_CANNOT_BE_CREATED_FROM_THIS_SERVER = 1882; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã‘â€¹
    // Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â°.
    public static final int THERE_ARE_NO_OFFERINGS_I_OWN_OR_I_MADE_A_BID_FOR = 1883; // Ã�Å“Ã�Â½Ã�Âµ
    // Ã�Â½Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int ENTER_THE_PC_ROOM_COUPON_SERIAL_NUMBER = 1884; // Ã�â€™Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¹Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¾Ã�Â½Ã�Â°:
    public static final int THIS_SERIAL_NUMBER_CANNOT_BE_ENTERED_PLEASE_TRY_AGAIN_IN_S1_MINUTES = 1885; // Ã�Â¡Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¹Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THIS_SERIAL_NUMBER_HAS_ALREADY_BEEN_USED = 1886; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¹Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int INVALID_SERIAL_NUMBER_YOUR_ATTEMPT_TO_ENTER_THE_NUMBER_HAS_FAILED_S1_TIMES_YOU_WILL_BE_ALLOWED_TO_MAKE_S2_MORE_ATTEMPTS = 1887; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¹Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬.
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â²Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’
    // $s1
    // Ã‘â‚¬Ã�Â°Ã�Â·(-Ã�Â°).
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // $s2
    // Ã‘â‚¬Ã�Â°Ã�Â·(-Ã�Â°).
    public static final int INVALID_SERIAL_NUMBER_YOUR_ATTEMPT_TO_ENTER_THE_NUMBER_HAS_FAILED_5_TIMES_PLEASE_TRY_AGAIN_IN_4_HOURS = 1888; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¹Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬.
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â²Ã�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’
    // 5
    // Ã‘â‚¬Ã�Â°Ã�Â·.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 4
    // Ã‘â€¡Ã�Â°Ã‘ï¿½Ã�Â°.
    public static final int CONGRATULATIONS_YOU_HAVE_RECEIVED_S1 = 1889; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼!
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸:
    // $s1.
    public static final int SINCE_YOU_HAVE_ALREADY_USED_THIS_COUPON_YOU_MAY_NOT_USE_THIS_SERIAL_NUMBER = 1890; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¾Ã�Â½
    // Ã�Â¸
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â½Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬.
    public static final int YOU_MAY_NOT_USE_ITEMS_IN_A_PRIVATE_STORE_OR_PRIVATE_WORK_SHOP = 1891; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int THE_REPLAY_FILE_FOR_THE_PREVIOUS_VERSION_CANNOT_BE_PLAYED = 1892; // Ã�Â¤Ã�Â°Ã�Â¹Ã�Â»
    // Replay
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½.
    public static final int THIS_FILE_CANNOT_BE_REPLAYED = 1893; // Ã�Â­Ã‘â€šÃ�Â¾ Ã‘â€žÃ�Â°Ã�Â¹Ã�Â» Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â²Ã�ÂµÃ�Â´Ã�ÂµÃ�Â½.
    public static final int A_SUB_CLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT = 1894; // Ã�Å¸Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â²Ã�ÂµÃ‘ï¿½Ã�Â°.
    public static final int S1_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING = 1895; // $c1
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°.
    public static final int S1_HAS_ALREADY_BEEN_SUMMONED = 1896; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶ $c1
    // Ã‘Æ’Ã�Â¶Ã�Âµ Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â½.
    public static final int S1_IS_REQUIRED_FOR_SUMMONING = 1897; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã‘Æ’Ã�ÂµÃ‘â€š:
    // $s1.
    public static final int S1_IS_CURRENTLY_TRADING_OR_OPERATING_A_PRIVATE_STORE_AND_CANNOT_BE_SUMMONED = 1898; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $c1
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING = 1899; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int S1_HAS_ENTERED_THE_PARTY_ROOM = 1900; // $c1 Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Â² Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int S1_HAS_INVITED_YOU_TO_ENTER_THE_PARTY_ROOM = 1901; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int INCOMPATIBLE_ITEM_GRADE_THIS_ITEM_CANNOT_BE_USED = 1902; // Ã�ï¿½Ã�ÂµÃ‘ï¿½Ã�Â¾Ã�Â²Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int REQUESTED_NCOTP = 1903; // Ã�Â¢Ã�Âµ Ã�Â¸Ã�Â· Ã�â€™Ã�Â°Ã‘ï¿½, Ã�ÂºÃ�Â¾Ã�Â¼Ã‘Æ’ Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // NCOTP, Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š Ã�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â³Ã�Â¾,\\n Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã‘ï¿½ Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ‘â€žÃ�Â¾Ã�Â½, Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹ Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // NCOTP
    public static final int A_SUB_CLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED = 1904; // Ã�Å¸Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ .
    public static final int S2_OF_S1_WILL_BE_REPLACED_WITH_S4_OF_S3 = 1905; // $s2
    // $s1
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // $c4
    // $s3.
    public static final int SELECT_THE_COMBAT_UNIT_YOU_WISH_TO_TRANSFER_TO = 1906; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â±Ã�Â¾Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¹
    // Ã�Â¾Ã‘â€šÃ‘â‚¬Ã‘ï¿½Ã�Â´\\n
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€žÃ�ÂµÃ‘â‚¬Ã�Â°.
    public static final int SELECT_THE_THE_CHARACTER_WHO_WILL_REPLACE_THE_CURRENT_CHARACTER = 1907; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // \\n
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€š
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int S1_IS_IN_A_STATE_WHICH_PREVENTS_SUMMONING = 1908; // $c1
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Æ’Ã‘ï¿½,
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â².
    public static final int LIST_OF_CLAN_ACADEMY_GRADUATES_DURING_THE_PAST_WEEK = 1909; // ==
    // <Ã�Â¡Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã�Â½Ã‘Å½Ã‘Å½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã‘Å½>
    // ==
    public static final int GRADUATES = 1910; // Ã�â€™Ã‘â€¹Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¸: $c1.
    public static final int YOU_CANNOT_SUMMON_PLAYERS_WHO_ARE_CURRENTLY_PARTICIPATING_IN_THE_GRAND_OLYMPIAD = 1911; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹,
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Â²
    // Ã�â€™Ã�ÂµÃ�Â»Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int ONLY_THOSE_REQUESTING_NCOTP_SHOULD_MAKE_AN_ENTRY_INTO_THIS_FIELD = 1912; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘â€šÃ�Âµ,
    // Ã�ÂºÃ‘â€šÃ�Â¾
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â°Ã‘Ë†Ã�Â¸Ã�Â²Ã�Â°Ã�Â»
    // NCOTP,
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã‘ï¿½Ã‘Å½Ã�Â´Ã�Â°.
    public static final int THE_REMAINING_RECYCLE_TIME_FOR_S1_IS_S2_MINUTES = 1913; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â°
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // $s1:
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_REMAINING_RECYCLE_TIME_FOR_S1_IS_S2_SECONDS = 1914; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â°
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // $s1:
    // $s2
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THE_GAME_WILL_END_IN_S1_SECONDS_2 = 1915; // Ã�ËœÃ�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THE_LEVEL_S1_SHILENS_BREATH_WILL_BE_ASSESSED = 1916; // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Ë†Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€žÃ�Â°
    // Ã�Â·Ã�Â°
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ‘Å’:
    // $s1.
    public static final int THE_SHILENS_BREATH_HAS_BEEN_LIFTED = 1917; // Ã�Â¨Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ž
    // Ã�Â·Ã�Â°
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int THE_PET_IS_TOO_HIGH_LEVEL_TO_CONTROL = 1918; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½,
    // Ã�Â²Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â»Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    public static final int THE_GRAND_OLYMPIAD_REGISTRATION_PERIOD_HAS_ENDED = 1919; // Ã�Â Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�â€™Ã�ÂµÃ�Â»Ã�Â¸Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘Æ’
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOUR_ACCOUNT_IS_CURRENTLY_INACTIVE_BECAUSE_YOU_HAVE_NOT_LOGGED_INTO_THE_GAME_FOR_SOME_TIME_YOU = 1920; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�ÂµÃ�Â½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â²Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â³Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂµÃ‘â€°Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    // Ã�Å“Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€šÃ�Âµ.
    public static final int S2_HOURS_AND_S3_MINUTES_HAVE_PASSED_SINCE_S1_HAS_KILLED = 1921; // Ã�Â¡
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã‘Æ’Ã�Â±Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // $s1
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // $s2
    // Ã‘â€¡
    // $s3
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int BECAUSE_S1_FAILED_TO_KILL_FOR_ONE_FULL_DAY_IT_HAS_EXPIRED = 1922; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // $s1
    // Ã�Â±Ã‘â€¹Ã�Â»
    public static final int COURT_MAGICIAN__THE_PORTAL_HAS_BEEN_CREATED = 1923; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â´Ã�Â²Ã�Â¾Ã‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Å“Ã�Â°Ã�Â³:
    // Ã�Å¸Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°Ã�Â»
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½!
    public static final int DUE_TO_THE_AFFECTS_OF_THE_SEAL_OF_STRIFE_IT_IS_NOT_POSSIBLE_TO_SUMMON_AT_THIS_TIME = 1925; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€šÃ�Â°
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â Ã�Â°Ã�Â·Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    public static final int C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL = 1932; // $c1
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸.
    public static final int C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_PARTY_DUEL = 1935; // $c1
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã�Â¼Ã�Â¸.
    public static final int THIS_IS_NOT_A_SUITABLE_PLACE_TO_CHALLENGE_ANYONE_OR_PARTY_TO_A_DUEL = 1941; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int THE_OPPOSING_PARTY_IS_CURRENTLY_NOT_IN_A_SUITABLE_LOCATION_FOR_A_DUEL = 1943; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // .
    public static final int C1_HAS_CHALLENGED_YOU_TO_A_DUEL_WILL_YOU_ACCEPT = 1946; // $c1
    // Ã�Â²Ã‘â€¹Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’?
    public static final int C1_S_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL_WILL_YOU_ACCEPT = 1947; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â²Ã‘â€¹Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘Ë†Ã‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’?
    public static final int THE_DUEL_WILL_BEGIN_IN_S1_SECONDS_1 = 1948; // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int SINCE_C1_WAS_DISQUALIFIED_S2_HAS_WON = 1953; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã�Â´Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â²Ã�Â°Ã�Â»Ã�Â¸Ã‘â€žÃ�Â¸Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // $s2.
    public static final int SINCE_C1_S_PARTY_WAS_DISQUALIFIED_S2_S_PARTY_HAS_WON = 1954; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã�Â´Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â²Ã�Â°Ã�Â»Ã�Â¸Ã‘â€žÃ�Â¸Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $s2.
    public static final int ONLY_THE_CLAN_LEADER_MAY_ISSUE_COMMANDS = 1966; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’.
    public static final int THE_GATE_IS_FIRMLY_LOCKED_PLEASE_TRY_AGAIN_LATER = 1967; // Ã�â€™Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Â·Ã�Â°Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘â€šÃ‘â€¹.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int S1_S_OWNER = 1968; // Ã�â€™Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ‘â€  $s1.
    public static final int AREA_WHERE_S1_APPEARS = 1969; // Ã�Å“Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾ Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // $s1.
    public static final int THE_LEVEL_OF_THE_HARDENER_IS_TOO_HIGH_TO_BE_USED = 1971; // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�Âº.
    public static final int YOU_CANNOT_AUGMENT_ITEMS_WHILE_FROZEN = 1973; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¼.
    public static final int YOU_CANNOT_AUGMENT_ITEMS_WHILE_ENGAGED_IN_TRADE_ACTIVITIES = 1975; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã�Â¸.
    public static final int S1_S_DROP_AREA_S2 = 1985; // Ã�â€”Ã�Â¾Ã�Â½Ã�Â° Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½ Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // $s1
    public static final int S1_S_OWNER_S2 = 1986; // Ã�â€™Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ‘â€  Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â° $s1 ($s2)
    public static final int S1_1 = 1987; // $s1
    public static final int THE_FERRY_HAS_ARRIVED_AT_PRIMEVAL_ISLE = 1988; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â½Ã�Â°
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â½Ã‘â€¹Ã�Â¹
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â².
    public static final int THE_FERRY_WILL_LEAVE_FOR_RUNE_HARBOR_AFTER_ANCHORING_FOR_THREE_MINUTES = 1989; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 3
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â€¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â¸Ã‘ï¿½.
    public static final int THE_FERRY_IS_NOW_DEPARTING_PRIMEVAL_ISLE_FOR_RUNE_HARBOR = 1990; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€š
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Âº
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã‘Æ’
    // Ã�Â¾Ã‘â€šÃ‘â€¡Ã�Â°Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int THE_FERRY_WILL_LEAVE_FOR_PRIMEVAL_ISLE_AFTER_ANCHORING_FOR_THREE_MINUTES = 1991; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 3
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â€¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â¸Ã‘ï¿½.
    public static final int THE_FERRY_IS_NOW_DEPARTING_RUNE_HARBOR_FOR_PRIMEVAL_ISLE = 1992; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¾Ã‘â€š
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Âº
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ‘â€¡Ã�Â°Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int THE_FERRY_FROM_PRIMEVAL_ISLE_TO_RUNE_HARBOR_HAS_BEEN_DELAYED = 1993; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Âº
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_FERRY_FROM_RUNE_HARBOR_TO_PRIMEVAL_ISLE_HAS_BEEN_DELAYED = 1994; // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¸Ã�Â·
    // Ã�Â³Ã�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã‘Æ’Ã�Â½Ã‘â€¹
    // Ã�Âº
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â²Ã‘Æ’
    // Ã�Â·Ã�Â°Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int S1_CHANNEL_FILTERING_OPTION = 1995; // Ã�Â¤Ã�Â¸Ã�Â»Ã‘Å’Ã‘â€šÃ‘â‚¬
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°: $s1.
    public static final int THE_ATTACK_HAS_BEEN_BLOCKED = 1996; // Ã�ï¿½Ã‘â€šÃ�Â°Ã�ÂºÃ�Â° Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_HAVE_AVOIDED_C1_S_ATTACK = 2000; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â¾Ã‘â€š Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸
    // Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸ $c1.
    public static final int TRAP_FAILED = 2002; // Ã�â€”Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€š Ã�Â½Ã�Âµ Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    public static final int YOU_OBTAINED_AN_ORDINARY_MATERIAL = 2003; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€¡Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â¸Ã�Â°Ã�Â».
    public static final int YOU_OBTAINED_A_RARE_MATERIAL = 2004; // Ã�â€™Ã‘â€¹ Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂºÃ�Â¸Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â¸Ã�Â°Ã�Â».
    public static final int YOU_OBTAINED_A_UNIQUE_MATERIAL = 2005; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â¸Ã�Â°Ã�Â».
    public static final int YOU_OBTAINED_THE_ONLY_MATERIAL_OF_THIS_KIND = 2006; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â²Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â²
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�ÂµÃ�Â¼
    // Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Âµ
    // Ã�Â¼Ã�Â°Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â¸Ã�Â°Ã�Â».
    public static final int PLEASE_ENTER_THE_RECIPIENT_S_NAME = 2007; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â°Ã�Â´Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã�Â°Ã‘â€šÃ�Â°.
    public static final int PLEASE_ENTER_THE_TEXT = 2008; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°, Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘ï¿½Ã‘â€š.
    public static final int YOU_CANNOT_EXCEED_1500_CHARACTERS = 2009; // Ã�ï¿½Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // 1500
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int S2_S1 = 2010; // $s2 $s1
    public static final int THE_AUGMENTED_ITEM_CANNOT_BE_DISCARDED = 2011; // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int S1_HAS_BEEN_ACTIVATED = 2012; // $s1 Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOUR_SEED_OR_REMAINING_PURCHASE_AMOUNT_IS_INADEQUATE = 2013; // Ã�Â¢Ã�Â¸Ã�Â¿
    // Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘ï¿½Ã�Â½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã�Âº
    // Ã‘ï¿½Ã‘Æ’Ã�Â¼Ã�Â¼Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘Æ’.
    public static final int YOU_CANNOT_PROCEED_BECAUSE_THE_MANOR_CANNOT_ACCEPT_ANY_MORE_CROPS__ALL_CROPS_HAVE_BEEN_RETURNED = 2014; // Ã�ï¿½Ã�ÂµÃ�Â¾Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¼Ã�Â¾.
    // Ã�â€™Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�ÂºÃ�Â¾Ã�Â².
    // Ã�â€™Ã‘ï¿½Ã�Âµ
    // Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�ÂºÃ�Â¸
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¸
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â·Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int A_SKILL_IS_READY_TO_BE_USED_AGAIN = 2015; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¾
    // Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½.
    public static final int A_SKILL_IS_READY_TO_BE_USED_AGAIN_BUT_ITS_RE_USE_COUNTER_TIME_HAS_INCREASED = 2016; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¾
    // Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½,
    // Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â·Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾.
    public static final int C1_IS_CURRENTLY_TELEPORTING_AND_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD = 2029; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    public static final int YOU_ARE_CURRENTLY_LOGGING_IN = 2030; // Ã�â€™Ã‘â€¹ Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int PLEASE_WAIT_A_MOMENT = 2031; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ.
    public static final int IT_IS_NOT_THE_RIGHT_TIME_FOR_PURCHASING_THE_ITEM = 2032; // Ã�ï¿½Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¾Ã�Âº.
    public static final int A_SUB_CLASS_CANNOT_BE_CREATED_OR_CHANGED_BECAUSE_YOU_HAVE_EXCEEDED_YOUR_INVENTORY_LIMIT = 2033; // Ã�Å¸Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â°Ã‘Ë†
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½.
    public static final int THERE_ARE_S1_HOURSS_AND_S2_MINUTES_REMAINING_UNTIL_THE_TIME_WHEN_THE_ITEM_CAN_BE_PURCHASED = 2034; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘â€¡
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THERE_ARE_S1_MINUTES_REMAINING_UNTIL_THE_TIME_WHEN_THE_ITEM_CAN_BE_PURCHASED = 2035; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int UNABLE_TO_INVITE_BECAUSE_THE_PARTY_IS_LOCKED = 2036; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°.
    public static final int UNABLE_TO_CREATE_CHARACTER_YOU_ARE_UNABLE_TO_CREATE_A_NEW_CHARACTER_ON_THE_SELECTED_SERVER_A = 2037; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Âµ.
    // Ã�â€�Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ,
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½Ã�Â¼
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹
    // Ã�Â½Ã�Â°
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â°Ã‘â€¦,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â´Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬.
    public static final int SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_ARENT_ALLOWED_TO_DROP = 2038; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â±Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int THIS_ACCOUNT_CANOT_TRADE_ITEMS = 2039; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int CANNOT_TRADE_ITEMS_WITH_THE_TARGETED_USER = 2040; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¼.
    public static final int SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_ARENT_ALLOWED_TO = 2041; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’.
    public static final int THIS_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_NON_PAYMENT_BASED_ON_THE_CELL_PHONE_PAYMENT_AGREEMENT__N = 2042; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°
    // Ã�Â½Ã�ÂµÃ‘Æ’Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Â¾Ã�Â±
    // Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ�Âµ
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ‘â€žÃ�Â¾Ã�Â½.
    // \\n
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘Ë†Ã�Â»Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â²Ã‘ï¿½Ã�Â¶Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã‘ï¿½
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¼
    // Ã�Â¾Ã�Â±Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â¸Ã�ÂµÃ�Â½Ã‘â€šÃ�Â¾Ã�Â².
    public static final int YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_MAY_NOT_TAKE_THIS_QUEST_ITEM_PLEASE_MAKE_ROOM = 2043; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�Â¼Ã�Â°
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â·Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    // Ã�Å¾Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â±Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â²
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã�Âµ
    // Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’.
    public static final int SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_ARENT_ALLOWED_TO_SET = 2044; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ‘Æ’Ã‘Å½.
    public static final int SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_ARENT_ALLOWED_TO_USE = 2045; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ‘Æ’Ã‘Å½.
    public static final int THIS_ACCOUNT_CANOT_USE_PRIVATE_STORES = 2046; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’.
    public static final int SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_ARENT_ALLOWED_TO_ = 2047; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Âµ
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ.
    public static final int THE_SHORTCUT_IN_USE_CONFLICTS_WITH_S1_DO_YOU_WISH_TO_RESET_THE_CONFLICTING_SHORTCUTS_AND_USE_THE = 2048; // Ã�Â¯Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€žÃ�Â»Ã�Â¸Ã�ÂºÃ‘â€šÃ‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘ï¿½
    // $s1.
    // Ã�Â¥Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€žÃ�Â»Ã�Â¸Ã�ÂºÃ‘â€šÃ‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Â¸
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹?
    public static final int THE_SHORTCUT_WILL_BE_APPLIED_AND_SAVED_IN_THE_SERVER_WILL_YOU_CONTINUE = 2049; // Ã�Â¯Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€š
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Âµ.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int S1_CLAN_IS_TRYING_TO_DISPLAY_A_FLAG = 2050; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³.
    public static final int YOU_MUST_ACCEPT_THE_USER_AGREEMENT_BEFORE_THIS_ACCOUNT_CAN_ACCESS_LINEAGE_II__N_PLEASE_TRY_AGAIN = 2051; // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿
    // Ã�Â²
    // Lineage
    // II,
    // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    // \\n
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·.
    public static final int A_GUARDIAN_S_CONSENT_IS_REQUIRED_BEFORE_THIS_ACCOUNT_CAN_BE_USED_TO_PLAY_LINEAGE_II__NPLEASE_TRY = 2052; // Ã�ï¿½Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â¾Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½
    // Ã�Âº
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ
    // Ã�Â²
    // Lineage
    // II
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¾
    // Ã�Â±Ã�ÂµÃ�Â·Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    // \\n
    // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THIS_ACCOUNT_HAS_DECLINED_THE_USER_AGREEMENT_OR_IS_PENDING_A_WITHDRAWL_REQUEST___NPLEASE_TRY = 2053; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã�Â¸Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã�Â»
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.\\n
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â°.
    public static final int THIS_ACCOUNT_HAS_BEEN_SUSPENDED___NFOR_MORE_INFORMATION_PLEASE_CALL_THE_CUSTOMER_S_CENTER_TEL = 2054; // Ã�â€�Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾.
    // \\n
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES__NFOR_MORE_INFORMATION_PLEASE_VISIT_THE = 2055; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â¾Ã‘â€š
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³.
    // \\n
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    public static final int YOUR_ACCOUNT_HAS_BEEN_CONVERTED_TO_AN_INTEGRATED_ACCOUNT_AND_IS_UNABLE_TO_BE_ACCESSED___NPLEASE = 2056; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â²
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½.
    // \\n
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â¾Ã�Â¼.
    public static final int YOU_HAVE_BLOCKED_C1 = 2057; // Ã�â€™Ã‘â€¹ Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸ $c1.
    public static final int THAT_ITEM_CANNOT_BE_TAKEN_OFF = 2065; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int THAT_WEAPON_CANNOT_PERFORM_ANY_ATTACKS = 2066; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�ÂµÃ�Â¼.
    public static final int THAT_WEAPON_CANNOT_USE_ANY_OTHER_SKILL_EXCEPT_THE_WEAPON_S_SKILL = 2067; // Ã�Å¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½,
    // Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�Â¼Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã‘ï¿½.
    public static final int THERE_ARE_NO_FUNDS_PRESENTLY_DUE_TO_YOU = 2081; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�ÂµÃ�Â·Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â°.
    public static final int YOU_HAVE_EXCEEDED_THE_TOTAL_AMOUT_OF_ADENA_ALLOWED_IN_INVENTORY = 2082; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â´Ã�Â¾Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼Ã‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘Æ’Ã�Â¼Ã�Â¼Ã‘Æ’
    // Ã�Â²
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã�Âµ.
    public static final int SEARCH_ON_USER_C2_FOR_THIRD_PARTY_PROGRAM_USE_WILL_BE_COMPLETED_IN_S1_MINUTES = 2086; // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // $c2,
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘â€¹,
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOUR_ACCOUNT_CAN_ONLY_BE_USED_AFTER_CHANGING_YOUR_PASSWORD_AND_QUIZ___N_SERVICES_WILL_BE = 2091; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â»Ã‘ï¿½
    // Ã�Â¸
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â°.
    public static final int YOU_CANNOT_BID_DUE_TO_A_PASSED_IN_PRICE = 2092; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã‘â€ Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_BID_AMOUNT_WAS_S1_ADENA_WOULD_YOU_LIKE_TO_RETRIEVE_THE_BID_AMOUNT = 2093; // Ã�Â¡Ã‘Æ’Ã�Â¼Ã�Â¼Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â¸
    // -
    // $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    // Ã�â€“Ã�ÂµÃ�Â»Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã‘Ë†Ã‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’?
    public static final int ANOTHER_USER_IS_PURCHASING_PLEASE_TRY_AGAIN_LATER = 2094; // Ã�Å¸Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_TRIAL_ACCOUNTS_HAVE_LIMITED_CHATTING = 2095; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã�Âº.
    public static final int YOU_CANNOT_ENTER_BECAUSE_YOU_ARE_NOT_ASSOCIATED_WITH_THE_CURRENT_COMMAND_CHANNEL = 2103; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Âµ
    // Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°.
    public static final int THE_MAXIMUM_NUMBER_OF_INSTANCE_ZONES_HAS_BEEN_EXCEEDED_YOU_CANNOT_ENTER = 2104; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â·Ã�Â¾Ã�Â½.
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_10_DAYS_FOR_USE_OF_ILLEGAL_SOFTWARE_AND_MAY_BE_PERMANENTLY = 2108; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€ Ã�ÂµÃ�Â½Ã�Â·Ã�Â¸Ã�Â¾Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�Â¿Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â±Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã�Â¾.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int THE_SERVER_HAS_BEEN_INTEGRATED_AND_YOUR_CHARACTER_S1_HAS_BEEN_OVERLAPPED_WITH_ANOTHER_NAME = 2109; // Ã�Â¡Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½
    // Ã�Â¸
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int THIS_CHARACTER_NAME_ALREADY_EXISTS_OR_IS_AN_INVALID_NAME_PLEASE_ENTER_A_NEW_NAME = 2110; // Ã�ËœÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â»Ã�Â¸Ã�Â±Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�ÂµÃ�ÂºÃ�Â¾Ã‘â‚¬Ã‘â‚¬Ã�ÂµÃ�ÂºÃ‘â€šÃ�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½.
    public static final int ENTER_A_SHORTCUT_TO_ASSIGN = 2111; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº.
    public static final int SUB_KEY_CAN_BE_CTRL_ALT_SHIFT_AND_YOU_MAY_ENTER_TWO_SUB_KEYS_AT_A_TIME___N_EXAMPLE__CTRL___ALT__ = 2112; // Ã�â€™Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â³Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¼Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â²Ã�Â¸Ã‘Ë†Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // CTRL,
    // ALT,
    // SHIFT.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¸Ã�Â¼Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â²Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾.
    // Ã�ï¿½Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ‘â‚¬,
    // "CTRL + ALT + A"
    public static final int CTRL_ALT_SHIFT_KEYS_MAY_BE_USED_AS_SUB_KEY_IN_EXPANDED_SUB_KEY_MODE_AND_ONLY_ALT_MAY_BE_USED_AS = 2113; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â²Ã�Â¸Ã‘Ë†Ã�Â¸
    // CTRL,
    // ALT,
    // SHIFT
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã‘â€¹
    // Ã�Â²
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â²Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â³Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â²Ã�Â¸Ã‘Ë†,
    // Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // ALT
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â²Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â³Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â²Ã�Â¸Ã‘Ë†Ã�Â°
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â´Ã�Â°Ã‘â‚¬Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ.
    public static final int FORCED_ATTACK_AND_STAND_IN_PLACE_ATTACKS_ASSIGNED_PREVIOUSLY_TO_CTRL_AND_SHIFT_WILL_BE_CHANGED = 2114; // Ã�Â¡Ã�Â¸Ã�Â»Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â´Ã�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ctrl
    // Ã�Â¸
    // Shift,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�ÂºÃ�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Â°
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â½Ã�Â°
    // Alt
    // +
    // Q
    // Ã�Â¸
    // Alt
    // +
    // E.
    // Ctrl
    // Ã�Â¸
    // Shift
    // Ã�Â¾Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â±Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã‘â€¦
    // Ã�Â±Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã‘â€¹Ã‘â€¦
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¾Ã�Â².
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_ABUSING_A_BUG_RELATED_TO_THE_NCCOIN_FOR_MORE_INFORMATION = 2115; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸,
    // Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Nccoin.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¦Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_ABUSING_A_FREE_NCCOIN_FOR_MORE_INFORMATION_PLEASE_VISIT_THE = 2116; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�ÂµÃ‘â€šÃ�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â±Ã�ÂµÃ‘ï¿½Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ�Â½Ã�Â¾
    // jigeupdoen
    // Ã�Â¼Ã�Â¾Ã�Â½Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾,
    // Ã�Â¸
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // akyonghan
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã�Â¸.
    // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸,
    // Ã�ÂºÃ�Â»Ã�Â¸Ã�ÂµÃ�Â½Ã‘â€šÃ�Â¾Ã�Â²
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬Ã�Â°
    // (Ã‘â€šÃ�ÂµÃ�Â».
    // 1600-0020)
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â°,
    // Ã�Â¿Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_USING_ANOTHER_PERSON_S_IDENTIFICATION_IF_YOU_WERE_NOT = 2117; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�ÂµÃ‘â€šÃ�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�ÂºÃ‘â‚¬Ã�Â°Ã�Â¶Ã�Â¸
    // Ã�Â¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â³Ã�Â¾Ã‘ï¿½Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â¼.
    // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸,
    // PlayNC
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€šÃ�Âµ
    // (www.plaync.co.kr)
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // Ã�ÂºÃ�Â»Ã�Â¸Ã�ÂµÃ�Â½Ã‘â€š-Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ,
    // Ã�Â¿Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_MISAPPROPRIATING_PAYMENT_UNDER_ANOTHER_PLAYER_S_ACCOUNT_FOR = 2118; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘ï¿½Ã�Â²Ã�Â¾Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¶Ã�ÂµÃ�Â¹
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â°.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_AFTER_BEING_DETECTED_WITH_DEALING_AN = 2119; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â¾Ã‘â€š
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³
    // Ã�Â·Ã�Â°
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã‘Å½
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸.
    // \\n
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_10_DAYS_FOR_USING_ILLEGAL_SOFTWARE_YOUR_ACCOUNT_MAY_BE = 2120; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�Â¿Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�Å¾Ã�Â½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â±Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã�Â½Ã�Â¾,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_FOR_USE_OF_ILLEGAL_SOFTWARE_FOR_MORE = 2121; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â¾Ã‘â€š
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�Â¿Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // \\n
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_FOR_USE_OF_ILLEGAL_SOFTWARE_FOR_MORE_ = 2122; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â¾Ã‘â€š
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¾Ã�Â±Ã�ÂµÃ‘ï¿½Ã�Â¿Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // \\n
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_AT_YOUR_OWN_REQUEST_FOR_MORE_INFORMATION_PLEASE_VISIT_THE = 2123; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â¿Ã�Â¾
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¹.
    public static final int THE_SERVER_HAS_BEEN_INTEGRATED_AND_YOUR_CLAN_NAME_S1_HAS_BEEN_OVERLAPPED_WITH_ANOTHER_NAME = 2124; // Ã�Â¡Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�ÂµÃ�Â½,
    // Ã�Â¸
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // $s1,
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â¼.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int THE_NAME_ALREADY_EXISTS_OR_IS_AN_INVALID_NAME_PLEASE_ENTER_THE_CLAN_NAME_TO_BE_CHANGED = 2125; // Ã�ËœÃ�Â¼Ã‘ï¿½
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â»Ã�Â¸Ã�Â±Ã�Â¾
    // Ã�Â¾Ã�Â½Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�ÂºÃ�Â¾Ã‘â‚¬Ã‘â‚¬Ã�ÂµÃ�ÂºÃ‘â€šÃ�Â½Ã�Â¾.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã‘ï¿½.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FOR_REGULARLY_POSTING_ILLEGAL_MESSAGES_FOR_MORE_INFORMATION = 2126; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¦Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_AFTER_BEING_DETECTED_WITH_AN_ILLEGAL_MESSAGE_FOR_MORE = 2127; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â°Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¦Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int YOUR_ACCOUNT_HAS_BEEN_SUSPENDED_FROM_ALL_GAME_SERVICES_FOR_USING_THE_GAME_FOR_COMMERCIAL = 2128; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�Â¾Ã‘â€š
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã‘â€¦
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘ï¿½Ã‘â€¦.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€šÃ‘â‚¬
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int THE_AUGMENTED_ITEM_CANNOT_BE_CONVERTED_PLEASE_CONVERT_AFTER_THE_AUGMENTATION_HAS_BEEN_REMOVED = 2129; // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â·Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾.
    public static final int YOU_CANNOT_CONVERT_THIS_ITEM = 2130;
    public static final int YOU_HAVE_BID_THE_HIGHEST_PRICE_AND_HAVE_WON_THE_ITEM_THE_ITEM_CAN_BE_FOUND_IN_YOUR_PERSONAL = 2131; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã‘Æ’Ã‘Å½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¸
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    // Ã�Å¾Ã�Â½
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¹
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int YOU_HAVE_ENTERED_A_COMMON_SEVER = 2132; // Ã�â€™Ã‘â€¹ Ã�Â·Ã�Â°Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â° Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬.
    public static final int YOU_HAVE_ENTERED_AN_ADULTS_ONLY_SEVER = 2133; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â·Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â»Ã‘â€¹Ã‘â€¦.
    public static final int YOU_HAVE_ENTERED_A_SERVER_FOR_JUVENILES = 2134; // Ã�â€™Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�ÂºÃ�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬.
    public static final int BECAUSE_OF_YOUR_FATIGUE_LEVEL_THIS_IS_NOT_ALLOWED = 2135; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã‘Æ’Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹,
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â¾.
    public static final int A_CLAN_NAME_CHANGE_APPLICATION_HAS_BEEN_SUBMITTED = 2136; // Ã�â€”Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_ARE_ABOUT_TO_BID_S1_ITEM_WITH_S2_ADENA_WILL_YOU_CONTINUE = 2137; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s1
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â²
    // $s2
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int PLEASE_ENTER_A_BID_PRICE = 2138; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ Ã‘ï¿½Ã�Â²Ã�Â¾Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’.
    public static final int C1_S_PET = 2139; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€  Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1.
    public static final int C1_S_SERVITOR = 2140; // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â³Ã�Â° Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1.
    public static final int YOU_SLIGHTLY_RESISTED_C1_S_MAGIC = 2141; // Ã�â€™Ã‘â€¹
    // Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â·Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã‘Å½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸
    // $c1.
    public static final int YOU_CANNOT_EXPEL_C1_BECAUSE_C1_IS_NOT_A_PARTY_MEMBER = 2142; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¾Ã�Â½
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.
    public static final int YOUR_OPPONENT_HAS_RESISTANCE_TO_MAGIC_THE_DAMAGE_WAS_DECREASED = 2151; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Âº
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã�Â¸,
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int THE_ASSIGNED_SHORTCUT_WILL_BE_DELETED_AND_THE_INITIAL_SHORTCUT_SETTING_RESTORED_WILL_YOU = 2152; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹,
    // Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_ARE_CURRENTLY_LOGGED_INTO_10_OF_YOUR_ACCOUNTS_AND_CAN_NO_LONGER_ACCESS_YOUR_OTHER_ACCOUNTS = 2153; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // 10
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â¾Ã�Â²
    // Ã�Â¸
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ‘â€¹.
    public static final int THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED = 2154; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Ë†Ã‘â€šÃ�Â¾Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â¸
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘â€š.
    public static final int A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED = 2155; // Ã�Â¤Ã�Â»Ã�Â°Ã�Â³
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘â€š.
    // Ã�â€�Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THERE_ARE_NOT_ENOUGH_NECESSARY_ITEMS_TO_USE_THE_SKILL = 2156; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²,
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘â€¹Ã‘â€¦
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â½Ã�Â°Ã�Â²Ã‘â€¹Ã�ÂºÃ�Â°.
    public static final int BID_WILL_BE_ATTEMPTED_WITH_S1_ADENA = 2157; // Ã�Â£Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘â€ Ã�ÂµÃ�Â½Ã�Â°:
    // $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int FORCE_ATTACK_IS_IMPOSSIBLE_AGAINST_A_TEMPORARY_ALLIED_MEMBER_DURING_A_SIEGE = 2158; // Ã�Â¡Ã�Â¸Ã�Â»Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã�Â¾Ã‘Å½Ã�Â·Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â¶Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã‘â€¹.
    public static final int THE_BARRACKS_HAVE_BEEN_SEIZED = 2164; // Ã�Å¡Ã�Â°Ã�Â·Ã�Â°Ã‘â‚¬Ã�Â¼Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int THE_BARRACKS_FUNCTION_HAS_BEEN_RESTORED = 2165; // Ã�Â¤Ã‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // Ã�ÂºÃ�Â°Ã�Â·Ã�Â°Ã‘â‚¬Ã�Â¼
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int ALL_BARRACKS_ARE_OCCUPIED = 2166; // Ã�Å¡Ã�Â°Ã�Â·Ã�Â°Ã‘â‚¬Ã�Â¼Ã‘â€¹ Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�ÂºÃ�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã‘â€¹.
    public static final int C1_HAS_ACQUIRED_THE_FLAG = 2168; // $c1 Ã�Â·Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³.
    public static final int A_MALICIOUS_SKILL_CANNOT_BE_USED_WHEN_AN_OPPONENT_IS_IN_THE_PEACE_ZONE = 2170; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int THIS_ITEM_CANNOT_BE_CRYSTALIZED = 2171; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Âµ Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â¸Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int S1_S_ELEMENTAL_POWER_HAS_BEEN_REMOVED = 2176; // $s1:
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¸
    // Ã�Â´Ã�ÂµÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int _S1S2_S_ELEMENTAL_POWER_HAS_BEEN_REMOVED = 2177; // +$s1
    // $s2:
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¸
    // Ã�Â´Ã�ÂµÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_FAILED_TO_REMOVE_THE_ELEMENTAL_POWER = 2178; // Ã�Â£Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int YOU_HAVE_THE_HIGHEST_BID_SUBMITTED_IN_A_GIRAN_CASTLE_AUCTION = 2179; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã‘Æ’Ã‘Å½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�â€œÃ�Â¸Ã‘â‚¬Ã�Â°Ã�Â½.
    public static final int YOU_HAVE_THE_HIGHEST_BID_SUBMITTED_IN_AN_ADEN_CASTLE_AUCTION = 2180; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã‘Æ’Ã‘Å½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�ï¿½Ã�Â´Ã�ÂµÃ�Â½.
    public static final int YOU_HAVE_HIGHEST_THE_BID_SUBMITTED_IN_A_RUNE_CASTLE_AUCTION = 2181; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã‘Æ’Ã‘Å½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â Ã‘Æ’Ã�Â½Ã�Â°.
    public static final int THE_TARGET_IS_LOCATED_WHERE_YOU_CANNOT_CHARGE = 2187; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’.
    public static final int ANOTHER_ENCHANTMENT_IS_IN_PROGRESS_PLEASE_COMPLETE_PREVIOUS_TASK_AND_TRY_AGAIN = 2188; // Ã�â€�Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Âµ
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€ Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Âµ.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â°Ã�Â´Ã�Â°Ã‘â€¡Ã‘Æ’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.
    public static final int TO_APPLY_SELECTED_OPTIONS_THE_GAME_NEEDS_TO_BE_RELOADED_IF_YOU_DON_T_APPLY_NOW_IT_WILL_BE = 2191; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�ÂµÃ�Âº
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½,
    // Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã‘ï¿½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Âµ
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½?
    public static final int YOU_HAVE_BID_ON_AN_ITEM_AUCTION = 2192; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’ Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â².
    public static final int NO_OWNED_CLAN = 2196; // Ã�ï¿½Ã�ÂµÃ‘â€š Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int OWNED_BY_CLAN_S1 = 2197; // Ã�â€¢Ã‘ï¿½Ã‘â€šÃ‘Å’ Ã�ÂºÃ�Â»Ã�Â°Ã�Â½ $s1.
    public static final int YOU_HAVE_THE_HIGHEST_BID_IN_AN_ITEM_AUCTION = 2198; // Ã�Â£
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â².
    public static final int YOU_CANNOT_ENTER_THIS_INSTANCE_ZONE_WHILE_THE_NPC_SERVER_IS_UNAVAILABLE = 2199; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // NPC
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°.
    public static final int THIS_INSTANCE_ZONE_WILL_BE_TERMINATED_BECAUSE_THE_NPC_SERVER_IS_UNAVAILABLE_YOU_WILL_BE_FORCIBLY = 2200; // Ã�Â­Ã‘â€šÃ�Â°
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬
    // NPC
    // Ã�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â¾Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š.
    // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â²Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘Æ’Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¸Ã�Â·
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â·Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½.
    public static final int S1YEARS_S2MONTHS_S3DAYS = 2201; // $s1 Ã�Â³. $s2 Ã�Â¼Ã�ÂµÃ‘ï¿½.
    // $s3 Ã�Â´Ã�Â½.
    public static final int S1HOURS_S2MINUTES_S3_SECONDS = 2202; // $s1 Ã‘â€¡ $s2
    // Ã�Â¼Ã�Â¸Ã�Â½ $s3 Ã‘ï¿½Ã�ÂµÃ�Âº
    public static final int S1_M_S2_D = 2203; // $s1 Ã�Â¼Ã�ÂµÃ‘ï¿½. $s2 Ã�Â´Ã�Â½.
    public static final int S1HOURS = 2204; // $s1 Ã‘â€¡
    public static final int YOU_HAVE_ENTERED_AN_AREA_WHERE_THE_MINI_MAP_CANNOT_BE_USED_THE_MINI_MAP_WILL_BE_CLOSED = 2205; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    // Ã�Å“Ã�Â¸Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ�Â°
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°.
    public static final int YOU_HAVE_ENTERED_AN_AREA_WHERE_THE_MINI_MAP_CAN_BE_USED = 2206; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ�Â°.
    public static final int THIS_IS_AN_AREA_WHERE_YOU_CANNOT_USE_THE_MINI_MAP_THE_MINI_MAP_WILL_NOT_BE_OPENED = 2207; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int YOU_DO_NOT_MEET_THE_SKILL_LEVEL_REQUIREMENTS = 2208; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼.
    public static final int THIS_IS_AN_AREA_WHERE_RADAR_CANNOT_BE_USED = 2209; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â´Ã�Â°Ã‘â‚¬
    // Ã�Â½Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int IT_WILL_RETURN_TO_AN_UNENCHANTED_CONDITION = 2210; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Âµ.
    public static final int YOU_MUST_LEARN_A_GOOD_DEED_SKILL_BEFORE_YOU_CAN_ACQUIRE_NEW_SKILLS = 2211; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘â€šÃ�Â²Ã�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â´Ã�Âµ
    // Ã‘â€¡Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_HAVE_NOT_COMPLETED_THE_NECESSARY_QUEST_FOR_SKILL_ACQUISITION = 2212; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â¾,
    // Ã�Â²Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘â€¹Ã�Â¹
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€š.
    public static final int A_NEW_CHARACTER_WILL_BE_CREATED_WITH_THE_CURRENT_SETTINGS_CONTINUE = 2214; // Ã�ï¿½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½
    // Ã‘ï¿½
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â¸Ã�Â¼Ã�Â¸
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â°Ã�Â¼Ã�Â¸.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int S1P_DEF = 2215; // Ã�Â¤Ã�Â¸Ã�Â·. Ã�â€”Ã�Â°Ã‘â€°.: $s1
    public static final int THE_CPU_DRIVER_IS_NOT_UP_TO_DATE_PLEASE_INSTALL_AN_UP_TO_DATE_CPU_DRIVER = 2216; // Ã�ï¿½Ã�Âµ
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // CPU
    // Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â¹Ã�Â²Ã�ÂµÃ‘â‚¬.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int THE_BALLISTA_HAS_BEEN_SUCCESSFULLY_DESTROYED_AND_THE_CLAN_S_REPUTATION_WILL_BE_INCREASED = 2217; // Ã�â€˜Ã�Â°Ã�Â»Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã‘â‚¬Ã�ÂµÃ�Â¿Ã‘Æ’Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â·Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°.
    public static final int THIS_IS_A_MAIN_CLASS_SKILL_ONLY = 2218; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â°.
    public static final int THIS_LOWER_CLAN_SKILL_HAS_ALREADY_BEEN_ACQUIRED = 2219; // Ã�ï¿½Ã�Â¸Ã�Â·Ã‘Ë†Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹.
    public static final int THE_PREVIOUS_LEVEL_SKILL_HAS_NOT_BEEN_LEARNED = 2220; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int WILL_YOU_ACTIVATE_THE_SELECTED_FUNCTIONS = 2221; // Ã�Â¥Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸?
    public static final int IT_WILL_COST_150000_ADENA_TO_PLACE_SCOUTS_WILL_YOU_PLACE_THEM = 2222; // Ã�Â Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â²Ã�ÂµÃ�Â´Ã‘â€¡Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // 150
    // 000
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    // Ã�Â Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’?
    public static final int IT_WILL_COST_200000_ADENA_FOR_A_FORTRESS_GATE_ENHANCEMENT_WILL_YOU_ENHANCE_IT = 2223; // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€š
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ‘Å’
    // 200
    // 000
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int CROSSBOW_IS_PREPARING_TO_FIRE = 2224; // Ã�ï¿½Ã‘â‚¬Ã�Â±Ã�Â°Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½ Ã�Âº
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â»Ã‘Å’Ã�Â±Ã�Âµ.
    public static final int THERE_ARE_NO_OTHER_SKILLS_TO_LEARN_PLEASE_COME_BACK_AFTER_S1ND_CLASS_CHANGE = 2225; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã‘â€¦
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // $s1-Ã�Â¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â°.
    public static final int IT_IS_NOT_POSSIBLE_TO_REGISTER_FOR_THE_CASTLE_SIEGE_SIDE_OR_CASTLE_SIEGE_OF_A_HIGHER_CASTLE_IN = 2227; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘Æ’
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘Æ’
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�ÂºÃ‘â€šÃ�Âµ.
    public static final int THE_SUPPLY_ITEMS_HAVE_NOT_NOT_BEEN_PROVIDED_BECAUSE_THE_HIGHER_CASTLE_IN_CONTRACT_DOESN_T_HAVE = 2231; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã‘Æ’
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�ÂºÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¿Ã‘Æ’Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    public static final int S1_WILL_BE_CRYSTALIZED_BEFORE_DESTRUCTION_WILL_YOU_CONTINUE = 2232; // $s1
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â¸Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int SIEGE_REGISTRATION_IS_NOT_POSSIBLE_DUE_TO_A_CONTRACT_WITH_A_HIGHER_CASTLE = 2233; // Ã�Â Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�ÂºÃ‘â€šÃ�Â°
    // Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã�Â¼
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â¾Ã�Â¼.
    public static final int WILL_YOU_USE_THE_SELECTED_KAMAEL_RACE_ONLY_HERO_WEAPON = 2234; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�â€œÃ�ÂµÃ‘â‚¬Ã�Â¾Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Âµ
    // Ã�Å¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ,
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Âµ
    // Ã�Å¡Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½Ã�Â»Ã‘Å’?
    public static final int THE_INSTANCE_ZONE_IN_USE_HAS_BEEN_DELETED_AND_CANNOT_BE_ACCESSED = 2235; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â°.
    public static final int S1_MINUTES_LEFT_FOR_WYVERN_RIDING = 2236; // Ã�â€�Ã�Â¾
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¸Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘â€¦
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int S1_SECONDS_LEFT_FOR_WYVERN_RIDING = 2237; // Ã�â€�Ã�Â¾
    // Ã�Â³Ã�Â¾Ã�Â½Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¸Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘â€¦
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_PARTICIPATED_IN_THE_SIEGE_OF_S1_THIS_SIEGE_WILL_CONTINUE_FOR_2_HOURS = 2238; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // $s1.
    // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // 2
    // Ã‘â€¡Ã�Â°Ã‘ï¿½Ã�Â°.
    public static final int THE_SIEGE_OF_S1_IN_WHICH_YOU_ARE_PARTICIPATING_HAS_FINISHED = 2239; // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // $s1,
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¹
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ,
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_CANNOT_REGISTER_FOR_THE_TEAM_BATTLE_CLAN_HALL_WAR_WHEN_YOUR_CLAN_LORD_IS_ON_THE_WAITING_LIST = 2240; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘Æ’
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã�Â·Ã�Â°Ã�ÂºÃ‘â€ Ã�Â¸Ã‘Å½.
    public static final int YOU_CANNOT_APPLY_FOR_A_CLAN_LORD_TRANSACTION_IF_YOUR_CLAN_HAS_REGISTERED_FOR_THE_TEAM_BATTLE = 2241; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘Æ’
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã‘â€šÃ�Â¾
    // Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã�Â·Ã�Â°Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã‘â€¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°.
    public static final int CLAN_MEMBERS_CANNOT_LEAVE_OR_BE_EXPELLED_WHEN_THEY_ARE_REGISTERED_FOR_THE_TEAM_BATTLE_CLAN_HALL = 2242; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘Æ’
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int WHEN_A_CLAN_LORD_OCCUPYING_THE_BANDIT_STRONGHOLD_OR_WILD_BEAST_RESERVE_CLAN_HALL_IS_IN_DANGER = 2243; // Ã�Å¡Ã�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¾Ã�ÂºÃ�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¢Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â´Ã‘â€¹Ã�Â½Ã‘Å½
    // Ã�Â Ã�Â°Ã�Â·Ã�Â±Ã�Â¾Ã�Â¹Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â²
    // Ã�â€”Ã�Â°Ã�Â³Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�â€�Ã�Â¸Ã�ÂºÃ�Â¸Ã‘â€¦
    // Ã�â€”Ã�Â²Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¹
    // Ã�Â²
    // Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int S1_MINUTES_REMAINING = 2244; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’: $s1 Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int S1_SECONDS_REMAINING = 2245; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’: $s1 Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THE_CONTEST_WILL_BEGIN_IN_S1_MINUTES = 2246; // Ã�Â¡Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_TRANSFORMED = 2247; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Âµ.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_PETRIFIED = 2248; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã�ÂºÃ�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â»Ã�Â¸,
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_DEAD = 2249; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²Ã‘â€¹Ã�Â¼.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_FISHING = 2250; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_BATTLE = 2251; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_A_DUEL = 2252; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SITTING = 2253; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SKILL_CASTING = 2254; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â°
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_CURSED_WEAPON_IS_EQUPPED = 2255; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�ÂºÃ�Â¸Ã�Â¿Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â»Ã‘ï¿½Ã‘â€šÃ‘â€¹Ã�Â¼
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�ÂµÃ�Â¼.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_HOLDING_A_FLAG = 2256; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³.
    public static final int YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_PET_OR_A_SERVITOR_IS_SUMMONED = 2257; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â½
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ .
    public static final int YOU_HAVE_ALREADY_BOARDED_ANOTHER_AIRSHIP = 2258; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.

    public static final int THE_PET_CAN_RUN_AWAY_IF_THE_HUNGER_GAUGE_IS_BELOW_10 = 2260; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã�Â±Ã�ÂµÃ�Â¶Ã�Â°Ã‘â€šÃ‘Å’,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘Ë†Ã�ÂºÃ�Â°Ã�Â»Ã�Â°
    // Ã�Â³Ã�Â¾Ã�Â»Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â¾Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â¸Ã�Â¶Ã�Âµ
    // 10%.
    public static final int THE_KEY_YOU_ASSIGNED_AS_A_SHORTCUT_KEY_IS_NOT_AVAILABLE_IN_THE_REGULAR_CHATTING_MODE = 2272; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â²Ã�Â¸Ã‘Ë†Ã�Â°,
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â²Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº,
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°
    // Ã�Â²
    // Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int THIS_SKILL_CANNOT_BE_LEARNED_WHILE_IN_THE_SUB_CLASS_STATE_PLEASE_TRY_AGAIN_AFTER_CHANGING_TO_THE = 2273; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â¾Ã�Â¼.
    // Ã�â€™Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Âº
    // Ã�Â¾Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã‘Æ’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int YOU_ENTERED_AN_AREA_WHERE_YOU_CANNOT_THROW_AWAY_ITEMS = 2274; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int YOU_ARE_IN_AN_AREA_WHERE_YOU_CANNOT_CANCEL_PET_SUMMONING = 2275; // Ã�â€™Ã‘â€¹
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã‘â€¹Ã�Â²
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int PARTY_OF_S1 = 2277; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â° Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $s1
    public static final int REMAINING_TIME_S1_S2 = 2278; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’ Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸:
    // $s1:$s2
    public static final int YOU_CAN_NO_LONGER_ADD_A_QUEST_TO_THE_QUEST_ALERTS = 2279; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘â€¹
    // Ã�Â²
    // Ã�Â¶Ã‘Æ’Ã‘â‚¬Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â².
    public static final int C1_HIT_YOU_FOR_S3_DAMAGE_AND_HIT_YOUR_SERVITOR_FOR_S4 = 2281; // $c1
    // Ã�Â½Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½
    // $s3
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â³Ã‘Æ’
    // $c2,
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã�Â½Ã�Â°Ã�Â½Ã�ÂµÃ‘ï¿½
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½
    // $s4
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�ÂºÃ‘â€šÃ‘Æ’.
    public static final int LEAVE_FANTASY_ISLE = 2282; // Ã�Å¸Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’ Ã�Å¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â² Ã�â€œÃ‘â‚¬Ã�ÂµÃ�Â·?
    public static final int YOU_HAVE_OBTAINED_ALL_THE_POINTS_YOU_CAN_GET_TODAY_IN_A_PLACE_OTHER_THAN_INTERNET_CAF = 2284; // Ã�Â¡Ã�ÂµÃ�Â³Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½
    // Ã�Â²Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã�Â²
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Âµ,
    // Ã�Â¾Ã‘â€šÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¾Ã‘â€š
    // Ã�Â¸Ã�Â½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�ÂºÃ�Â°Ã‘â€žÃ�Âµ.
    public static final int THIS_SKILL_CANNOT_REMOVE_THIS_TRAP = 2285; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â±Ã�ÂµÃ�Â·Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ‘Æ’
    // Ã�Â»Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Ë†Ã�ÂºÃ‘Æ’.
    public static final int YOU_CANNOT_WEAR_S1_BECAUSE_YOU_ARE_NOT_WEARING_THE_BRACELET = 2286; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s1,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â±Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â»Ã�ÂµÃ‘â€šÃ‘â€¹.
    public static final int THERE_IS_NO_SPACE_TO_WEAR_S1 = 2287; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½ Ã�Â¾Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š $s1,
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â±Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°.
    public static final int RESURRECTION_WILL_OCCUR_IN_S1_SECONDS = 2288; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â¾Ã�Â¹Ã�Â´Ã�ÂµÃ‘â€š
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THE_MATCH_BETWEEN_THE_PARTIES_IS_NOT_AVAILABLE_BECAUSE_ONE_OF_THE_PARTY_MEMBERS_IS_BEING = 2289; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â²Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½
    // Ã�Â¸Ã�Â·
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOU_CANNOT_ASSIGN_SHORTCUT_KEYS_BEFORE_YOU_LOG_IN = 2290; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¸
    // Ã�Â´Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int YOU_CAN_OPERATE_THE_MACHINE_WHEN_YOU_PARTICIPATE_IN_THE_PARTY = 2291; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼
    // Ã�Â¼Ã�ÂµÃ‘â€¦Ã�Â°Ã�Â½Ã�Â¸Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¼,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.
    public static final int CURRENT_LOCATION__S1_S2_S3_INSIDE_THE_STEEL_CITADEL = 2293; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Â¡Ã‘â€šÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¦Ã�Â¸Ã‘â€šÃ�Â°Ã�Â´Ã�ÂµÃ�Â»Ã�Â¸)
    public static final int THE_WIDTH_OF_THE_UPLOADED_BADGE_OR_INSIGNIA_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS = 2294; // Ã�Â¨Ã�Â¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂºÃ�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â´Ã�Â°Ã‘â‚¬Ã‘â€šÃ�Â°Ã�Â¼.
    public static final int THE_LENGTH_OF_THE_UPLOADED_BADGE_OR_INSIGNIA_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS = 2295; // Ã�â€�Ã�Â»Ã�Â¸Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂºÃ�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â´Ã�Â°Ã‘â‚¬Ã‘â€šÃ�Â°Ã�Â¼.
    public static final int ROUND_S1 = 2297; // Ã�Â Ã�Â°Ã‘Æ’Ã�Â½Ã�Â´ $s1
    public static final int THE_COLOR_OF_THE_BADGE_OR_INSIGNIA_THAT_YOU_WANT_TO_REGISTER_DOES_NOT_MEET_THE_STANDARD = 2298; // Ã�Â¦Ã�Â²Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂºÃ�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘â€¹,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘Æ’Ã‘Å½
    // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’,
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â´Ã�Â°Ã‘â‚¬Ã‘â€šÃ�Â°Ã�Â¼.
    public static final int THE_FILE_FORMAT_OF_THE_BADGE_OR_INSIGNIA_THAT_YOU_WANT_TO_REGISTER_DOES_NOT_MEET_THE_STANDARD = 2299; // Ã�Â¤Ã�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€š
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Â°
    // Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂºÃ�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¼Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼Ã‘â€¹,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘Æ’Ã‘Å½
    // Ã�Â²Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’,
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼.
    public static final int FAILED_TO_LOAD_KEYBOARD_SECURITY_MODULE_FOR_EFFECTIVE_GAMING_FUNCTIONALITY_WHEN_THE_GAME_IS_OVER = 2300; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¼Ã�Â¾Ã�Â´Ã‘Æ’Ã�Â»Ã‘Å’
    // Ã�Â·Ã�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ‘â€¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â²Ã�Â¸Ã�Â°Ã‘â€šÃ‘Æ’Ã‘â‚¬Ã‘â€¹.
    // Ã�Å¡Ã�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã‘â€¹
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â°Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Lineage
    // II.
    public static final int CURRENT_LOCATION__STEEL_CITADEL_RESISTANCE = 2301; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Â¡Ã‘â€šÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¦Ã�Â¸Ã‘â€šÃ�Â°Ã�Â´Ã�ÂµÃ�Â»Ã�Â¸.
    public static final int YOUR_VITAMIN_ITEM_HAS_ARRIVED_VISIT_THE_VITAMIN_MANAGER_IN_ANY_VILLAGE_TO_OBTAIN_IT = 2302; // Ã�â€™Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½.
    // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â³Ã�Â¾,
    // Ã�Â½Ã�Â°Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â´Ã�Â¶Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�Â²Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â´Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Âµ.
    public static final int RESURRECTION_IS_POSSIBLE_BECAUSE_OF_THE_COURAGE_CHARM_S_EFFECT_WOULD_YOU_LIKE_TO_RESURRECT_NOW = 2306; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€šÃ‘Æ’
    // Ã�Å¾Ã‘â€šÃ�Â²Ã�Â°Ã�Â³Ã�Â°.
    // Ã�Â¥Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’?
    public static final int THE_TARGET_IS_RECEIVING_THE_COURAGE_CHARM_S_EFFECT = 2307; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Å¾Ã‘â€šÃ�Â²Ã�Â°Ã�Â³Ã�Â°.
    public static final int REMAINING_TIME__S1_DAYS = 2308; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’ Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸:
    // $s1 Ã�Â´Ã�Â½.
    public static final int REMAINING_TIME__S1_HOURS = 2309; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸: $s1 Ã‘â€¡.
    public static final int REMAINING_TIME__S1_MINUTES = 2310; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸: $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_DO_NOT_HAVE_A_SERVITOR = 2311; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸.
    public static final int YOU_DO_NOT_HAVE_A_PET = 2312; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â½Ã�ÂµÃ‘â€š Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int THE_VITAMIN_ITEM_HAS_ARRIVED = 2313; // Ã�â€™Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½.
    public static final int ONLY_AN_ENHANCED_SKILL_CAN_BE_CANCELLED = 2318; // Ã�Â¢Ã�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int MASTERWORK_POSSIBLE = 2320; // Ã�Å“Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾ Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’ Ã‘Ë†Ã�ÂµÃ�Â´Ã�ÂµÃ�Â²Ã‘â‚¬.
    public static final int CURRENT_LOCATION__INSIDE_KAMALOKA = 2321; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Å¡Ã�Â°Ã�Â¼Ã�Â°Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸
    public static final int CURRENT_LOCATION__INSIDE_NIA_KAMALOKA = 2322; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Å¡Ã�Â°Ã�Â¼Ã�Â°Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸
    public static final int CURRENT_LOCATION__INSIDE_RIM_KAMALOKA = 2323; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Å¾Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¹
    // Ã�Å¡Ã�Â°Ã�Â¼Ã�Â°Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸
    public static final int C1_YOU_CANNOT_ENTER_BECAUSE_YOU_HAVE_INSUFFICIENT_PC_CAFE_POINTS = 2324; // $c1,
    // Ã�Â²Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸,
    // Ã‘Æ’
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã‘Æ’Ã�Â±Ã�Â°.
    public static final int ANOTHER_TELEPORT_IS_TAKING_PLACE_PLEASE_TRY_AGAIN_ONCE_THE_TELEPORT_IN_PROCESS_ENDS = 2325; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â¸Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€ Ã�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int CLANS_OF_LEVEL_4_OR_ABOVE_CAN_REGISTER_FOR_HIDEAWAY_WARS_FOR_DEVASTATED_CASTLE_AND_FORTRESS_OF = 2328; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã‘â€¹
    // 4
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã‘Æ’Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â²
    // Ã�Â Ã�Â°Ã�Â·Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�â€”Ã�Â°Ã�Â¼Ã�ÂºÃ�Âµ
    // Ã�Â¸
    // Ã�Å¡Ã‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�ï¿½Ã�ÂµÃ‘Æ’Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¾Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦.
    public static final int VITALITY_LEVEL_S1_S2 = 2329; // Ã�Â£Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’ Ã‘ï¿½Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â¸Ã�Â¸ $s1
    // $s2
    public static final int __EXPERIENCE_POINTS_BOOSTED_BY_S1 = 2330; // -
    // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ�Â¼Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // $s1% Ã�Â¾Ã‘â€š
    // Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int RARE_S1 = 2331; // <Ã�Â Ã�ÂµÃ�Â´Ã�ÂºÃ�Â¸Ã�Â¹> $s1
    public static final int SUPPLY_S1 = 2332; // <Ã�Â Ã�ÂµÃ‘ï¿½Ã‘Æ’Ã‘â‚¬Ã‘ï¿½> $s1
    public static final int YOU_CANNOT_RECEIVE_THE_VITAMIN_ITEM_BECAUSE_YOU_HAVE_EXCEED_YOUR_INVENTORY_WEIGHT_QUANTITY_LIMIT = 2333; // Ã�ï¿½Ã�Â°Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â²
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã�Âµ
    // Ã�Â½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â±Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°.
    public static final int SCORE_THAT_SHOWS_A_PLAYER_S_INDIVIDUAL_FAME_FAME_CAN_BE_OBTAINED_BY_PARTICIPATING_IN_A_TERRITORY = 2334; // Ã�Å¾Ã‘â€¡Ã�ÂºÃ�Â¸,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Âµ
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â‚¬Ã�ÂµÃ�Â¿Ã‘Æ’Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã‘Å½.
    // Ã�Å“Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ‘â€¹
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã�Â¸,
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°,
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸,
    // Ã‘Æ’Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½,
    // Ã�Â²
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â·Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã�Â·Ã�ÂµÃ�Âµ,
    // Ã�Â½Ã�Â°
    // Ã�Â¤Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã�Â²Ã�Â°Ã�Â»Ã�Âµ
    // Ã�Â¢Ã‘Å’Ã�Â¼Ã‘â€¹
    // Ã�Â¸
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int THERE_ARE_NO_MORE_VITAMIN_ITEMS_TO_BE_FOUND = 2335; // Ã�â€™Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¾Ã�Â²
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â¹Ã�Â´Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int IF_IT_S_A_DRAW_THE_PLAYER_WHO_FIRST_ENTERED_IS_FIRST = 2338; // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â¾Ã�Â¼
    // Ã‘â‚¬Ã�ÂµÃ�Â·Ã‘Æ’Ã�Â»Ã‘Å’Ã‘â€šÃ�Â°Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Âº
    public static final int PLEASE_PLACE_THE_ITEM_TO_BE_ENCHANTED = 2339; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘â€šÃ�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’.
    public static final int PLEASE_PLACE_THE_ITEM_FOR_RATE_INCREASE = 2340; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘â€šÃ�Â°Ã‘â€°Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã‘Ë†Ã�Â°Ã�Â½Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’.
    public static final int THE_ENCHANT_WILL_BEGIN_ONCE_YOU_PRESS_THE_START_BUTTON_BELOW = 2341; // Ã�ï¿½Ã�Â°Ã�Â¶Ã�Â¼Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â½Ã�Â¾Ã�Â¿Ã�ÂºÃ‘Æ’
    // "Ã�ï¿½Ã�Â°Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’",
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int SUCCESS_THE_ITEM_IS_NOW_A_S1 = 2342; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼.
    // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½ Ã�Â¸
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â» $s1.
    public static final int FAILED_YOU_HAVE_OBTAINED_S2_OF_S1 = 2343; // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // $s1
    // $s2Ã�ÂµÃ�Â´.
    public static final int YOU_HAVE_BEEN_KILLED_BY_AN_ATTACK_FROM_C1 = 2344; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â°Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼
    // $c1
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â¸Ã�Â±Ã�Â»Ã�Â¸.
    public static final int YOU_HAVE_ATTACKED_AND_KILLED_C1 = 2345; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã�Â±Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1.
    public static final int YOUR_ACCOUNT_IS_TEMPORARILY_LIMITED_BECAUSE_YOUR_GAME_USE_GOAL_IS_PRESUMED_TO_BE_EMBEZZLEMENT_OF = 2346; // Ã�â€™Ã‘â€¹Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â°
    // Ã‘ï¿½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¼Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°Ã�Â¼Ã�Â¸
    // Ã�Â·Ã�Â°
    // Ã‘â‚¬Ã�ÂµÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’Ã�Â³Ã�Â¸.
    // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â‚¬Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int S1_SECONDS_TO_GAME_END = 2347; // Ã�â€�Ã�Â¾ Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½ Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // $s1 Ã‘ï¿½Ã�ÂµÃ�Âº!
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_BATTLE = 2348; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¾Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº
    // Ã�Â½Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_A_LARGE_SCALE_BATTLE_SUCH_AS_A_CASTLE_SIEGE = 2349; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘Ë†Ã‘â€šÃ�Â°Ã�Â±Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â°Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // -
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´
    // Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¹,
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â¾Ã�Â²,
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // -
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº
    // Ã�Â½Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_DUEL = 2350; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº
    // Ã�Â½Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_WHILE_FLYING = 2351; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€š
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_IN_AN_OLYMPIAD_MATCH = 2352; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€š
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_IN_A_FLINT_OR_PARALYZED_STATE = 2353; // Ã�â€™
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�ÂºÃ�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€š
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_DEAD = 2354; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ‘â‚¬,
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_IN_THIS_AREA = 2355; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸,
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¹
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€š
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_UNDERWATER = 2356; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â´Ã�Âµ.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_IN_AN_INSTANT_ZONE = 2357; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘ï¿½Ã‘Å’
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int YOU_HAVE_NO_SPACE_TO_SAVE_THE_TELEPORT_LOCATION = 2358; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº
    // Ã�Â½Ã�ÂµÃ�Â¼Ã‘Æ’.
    public static final int YOU_CANNOT_TELEPORT_BECAUSE_YOU_DO_NOT_HAVE_A_TELEPORT_ITEM = 2359; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’
    // Ã�Â±Ã�ÂµÃ�Â·
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int MY_TELEPORTS_SPELLBK__S1 = 2360; // Ã�Â¡Ã�Â²Ã�Â¸Ã‘â€šÃ�Â¾Ã�Âº Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº Ã�Â¤Ã�Â»Ã�Â°Ã�Â³Ã‘Æ’: $s1Ã�ÂµÃ�Â´.
    public static final int CURRENT_LOCATION__S1 = 2361; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â¸Ã�Âµ Ã�ÂºÃ�Â¾Ã�Â¾Ã‘â‚¬Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹:
    // $s1
    public static final int THE_SAVED_TELEPORT_LOCATION_WILL_BE_DELETED_DO_YOU_WISH_TO_CONTINUE = 2362; // Ã�Â£Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOUR_ACCOUNT_HAS_BEEN_CONFIRMED_AS_USING_ANOTHER_PERSON_S_NAME_ALL_GAME_SERVICES_HAVE_BEEN = 2363; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¾Ã�Â½
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â¿Ã�Â¾Ã�Â´
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Â¼
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â¼.
    // Ã�â€”Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�ÂµÃ�Â¹
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int THE_ITEM_HAS_EXPIRED_AFTER_ITS_S1_PERIOD = 2364; // $s1:
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Âº.
    // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·.
    public static final int THE_DESIGNATED_ITEM_HAS_EXPIRED_AFTER_ITS_S1_PERIOD = 2365; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Âº,
    // Ã�Â¸
    // Ã�Â¾Ã�Â½
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·.
    public static final int S1_S_BLESSING_HAS_RECOVERED_HP_BY_S2 = 2367; // HP
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // $s2
    // (Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $s1).
    public static final int S1_S_BLESSING_HAS_RECOVERED_MP_BY_S2 = 2368; // MP
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // $s2
    // (Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $s1).
    public static final int S1_S_BLESSING_HAS_FULLY_RECOVERED_HP_AND_MP = 2369; // HP
    // Ã�Â¸
    // MP
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // (Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $s1).
    public static final int RESURRECTION_WILL_TAKE_PLACE_IN_THE_WAITING_ROOM_AFTER_S1_SECONDS = 2370; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int C1_WAS_REPORTED_AS_A_BOT = 2371; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â°Ã�Â½Ã�Â° Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±Ã�Â°,
    // Ã‘â€¡Ã‘â€šÃ�Â¾ $c1
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€š Ã�Â±Ã�Â¾Ã‘â€šÃ�Â°.
    public static final int THERE_IS_NOT_MUCH_TIME_REMAINING_UNTIL_THE_HUNTING_HELPER_PET_LEAVES = 2372; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã‘ï¿½Ã�Â±Ã�ÂµÃ�Â¶Ã�Â¸Ã‘â€š.
    public static final int THE_HUNTING_HELPER_PET_IS_NOW_LEAVING = 2373; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ 
    // Ã‘ï¿½Ã�Â±Ã�ÂµÃ�Â¶Ã�Â°Ã�Â»
    // Ã�Â¾Ã‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½.
    public static final int END_MATCH = 2374; // Ã�Â¡Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾!
    public static final int THE_HUNTING_HELPER_PET_CANNOT_BE_RETURNED_BECAUSE_THERE_IS_NOT_MUCH_TIME_REMAINING_UNTIL_IT = 2375; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int YOU_CANNOT_RECEIVE_A_VITAMIN_ITEM_DURING_AN_EXCHANGE = 2376; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_CANNOT_REPORT_A_CHARACTER_WHO_IS_IN_A_PEACE_ZONE_OR_A_BATTLEFIELD = 2377; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±Ã‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Âµ
    // Ã�Â±Ã�Â¾Ã‘ï¿½.
    public static final int YOU_CANNOT_REPORT_WHEN_A_CLAN_WAR_HAS_BEEN_DECLARED = 2378; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±Ã‘Æ’,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â¸Ã�Â»
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’.
    public static final int YOU_CANNOT_REPORT_A_CHARACTER_WHO_HAS_NOT_ACQUIRED_ANY_EXP_AFTER_CONNECTING = 2379; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±Ã‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€š
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’.
    public static final int YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME___CHARACTER = 2380; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±
    // Ã‘ï¿½
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME___ACCOUNT = 2381; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±
    // Ã‘ï¿½
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â°.
    public static final int YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME___CLAN = 2382; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±
    // Ã‘ï¿½
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_REPORT_THIS_PERSON_AGAIN_AT_THIS_TIME___IP = 2383; // Ã�ï¿½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¶Ã�Â°Ã�Â»Ã�Â¾Ã�Â±
    // Ã‘ï¿½
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // IP.
    public static final int THIS_ITEM_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_THE_ENHANCEMENT_SPELLBOOK = 2384; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Â¿Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â°Ã�Â¼
    // Ã�Â¡Ã�Â²Ã�Â¸Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THIS_IS_AN_INCORRECT_SUPPORT_ENHANCEMENT_SPELLBOOK = 2385; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¡Ã�Â²Ã�Â¸Ã‘â€šÃ�Â¾Ã�Âº
    // Ã�â€�Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THIS_ITEM_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_THE_SUPPORT_ENHANCEMENT_SPELLBOOK = 2386; // Ã�Â­Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Â¿Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â°Ã�Â¼
    // Ã�Â¡Ã�Â²Ã�Â¸Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�â€�Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int REGISTRATION_OF_THE_SUPPORT_ENHANCEMENT_SPELLBOOK_HAS_FAILED = 2387; // Ã�ï¿½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¡Ã�Â²Ã�Â¸Ã‘â€šÃ�Â¾Ã�Âº
    // Ã�â€�Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THE_MAXIMUM_ACCUMULATION_ALLOWED_OF_PC_CAFE_POINTS_HAS_BEEN_EXCEEDED_YOU_CAN_NO_LONGER_ACQUIRE = 2389; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ËœÃ�Â½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�Â½Ã�ÂµÃ‘â€š-Ã�ÂºÃ�Â°Ã‘â€žÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¾
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š.
    // Ã�â€˜Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸.
    public static final int YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT = 2390; // Ã�â€�Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�ÂµÃ�Âº
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¤Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã�Â².
    // Ã�Â£Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_HAVE_USED_THE_FEATHER_OF_BLESSING_TO_RESURRECT = 2391; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã‘ï¿½
    // Ã�â€˜Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘Æ’.
    public static final int THE_VITAMIN_ITEM_CANNOT_BE_LOCATED_BECAUSE_OF_A_TEMPORARY_CONNECTION_ERROR = 2392; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â°Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�â€™Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¾Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_HAVE_ACQUIRED_S1_PC_CAFE_POINTS = 2393; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂµÃ�Â¶Ã�ÂµÃ�Â´Ã�Â½Ã�ÂµÃ�Â²Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â Ã�Â¡-Ã�ÂºÃ�Â»Ã‘Æ’Ã�Â±Ã�Â°
    // Ã�Â²
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�Âµ
    // $s1
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â².
    public static final int THAT_SKILL_CANNOT_BE_USED_BECAUSE_YOUR_PET_SERVITOR_LACKS_SUFFICIENT_MP = 2394; // Ã�Â£
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°/Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Å“Ã�Â 
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THAT_SKILL_CANNOT_BE_USED_BECAUSE_YOUR_PET_SERVITOR_LACKS_SUFFICIENT_HP = 2395; // Ã�Â£
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°/Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�ï¿½Ã�Â 
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THAT_PET_SERVITOR_SKILL_CANNOT_BE_USED_BECAUSE_IT_IS_RECHARGING = 2396; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ /Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ.
    public static final int PLEASE_USE_A_MY_TELEPORT_SCROLL = 2397; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Å¡Ã�Â½Ã�Â¸Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�â€™Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº Ã�Â¤Ã�Â»Ã�Â°Ã�Â³Ã‘Æ’.
    public static final int YOU_HAVE_NO_OPEN_MY_TELEPORTS_SLOTS = 2398; // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â¹Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int S1_S_OWNERSHIP_EXPIRES_IN_S2_MINUTES = 2399; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int INSTANT_ZONE_CURRENTLY_IN_USE__S1 = 2400; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Â°:
    // $s1
    public static final int CLAN_LORD_C2_WHO_LEADS_CLAN_S1_HAS_BEEN_DECLARED_THE_LORD_OF_THE_S3_TERRITORY = 2401; // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1,
    // $c2,
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¸Ã�Â·Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½
    // Ã�Å¸Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¼
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â»Ã‘Å’
    // $s3Ã�Â°.
    public static final int THE_TERRITORY_WAR_REQUEST_PERIOD_HAS_ENDED = 2402; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€¡Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Âº
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�ÂºÃ�Â»Ã�Â¾.
    public static final int TERRITORY_WAR_BEGINS_IN_10_MINUTES = 2403; // Ã�â€�Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // 10Ã�Â¼Ã�Â¸Ã�Â½!
    public static final int TERRITORY_WAR_BEGINS_IN_5_MINUTES = 2404; // Ã�â€�Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // 5Ã�Â¼Ã�Â¸Ã�Â½!
    public static final int TERRITORY_WAR_BEGINS_IN_1_MINUTE = 2405; // Ã�â€�Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹ Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // 1Ã�Â¼Ã�Â¸Ã�Â½!
    public static final int S1_S_TERRITORY_WAR_HAS_BEGUN = 2406; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â° Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸ $s1
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int S1_S_TERRITORY_WAR_HAS_ENDED = 2407; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â° Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸ $s1
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_HAVE_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_NON_CLASS_LIMITED_TEAM_MATCH_EVENT = 2408; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â±Ã�ÂµÃ�Â·
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â°Ã�Â¼.
    public static final int THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED = 2409; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�ÂµÃ�Âº
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA = 2410; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Âº
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int C1_HAS_ISSUED_A_PARTY_INVITATION_WHICH_YOU_AUTOMATICALLY_REJECTED_TO_RECEIVE_PARTY_INVITATIONS = 2411; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â¾Ã‘â€š
    // $c1,
    // Ã�Â½Ã�Â¾
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.
    // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ,
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸.
    public static final int THE_BIRTHDAY_GIFT_HAS_BEEN_DELIVERED_VISIT_THE_VITAMIN_MANAGER_IN_ANY_VILLAGE_TO_OBTAIN_IT = 2412; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Âº
    // Ã�Â½Ã�Â°
    // Ã�â€�Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â Ã�Â¾Ã�Â¶Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘Æ’
    // Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â´Ã�Â¶Ã�ÂµÃ‘â‚¬Ã�Â°
    // Ã�Â²Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¾Ã�Â².
    public static final int YOU_ARE_REGISTERING_AS_A_RESERVE_ON_THE_AERIAL_CLEFT_RED_TEAM_S_BATTLEFIELD_DO_YOU_WISH_TO = 2413; // Ã�Â Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘Æ’
    // Ã�Â·Ã�Â°
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�ÂºÃ‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_ARE_REGISTERING_AS_A_RESERVE_ON_THE_AERIAL_CLEFT_BLUE_TEAM_S_BATTLEFIELD_DO_YOU_WISH_TO = 2414; // Ã�Â Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘Æ’
    // Ã�Â·Ã�Â°
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¸Ã�Â½Ã�ÂµÃ�Â¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_HAVE_REGISTERED_AS_A_RESERVE_ON_THE_AERIAL_CLEFT_RED_TEAM_S_BATTLEFIELD_WHEN_IN_BATTLE_THE = 2415; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�ÂºÃ‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘Æ’.
    // Ã�â€™
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã�Â°Ã�Â»Ã�Â°Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int YOU_HAVE_REGISTERED_AS_A_RESERVE_ON_THE_AERIAL_CLEFT_BLUE_TEAM_S_BATTLEFIELD_WHEN_IN_BATTLE_THE = 2416; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¸Ã�Â½Ã‘Å½Ã‘Å½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘Æ’.
    // Ã�â€™
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã�Â°Ã�Â»Ã�Â°Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int YOU_ARE_CANCELING_THE_AERIAL_CLEFT_BATTLEFIELD_REGISTRATION_DO_YOU_WISH_TO_CONTINUE = 2417; // Ã�Å¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_AERIAL_CLEFT_BATTLEFIELD_REGISTRATION_HAS_BEEN_CANCELED = 2418; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â¾Ã‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ.
    public static final int THE_AERIAL_CLEFT_BATTLEFIELD_HAS_BEEN_ACTIVATED_FLIGHT_TRANSFORMATION_WILL_BE_POSSIBLE_IN = 2419; // Ã�ï¿½Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Âµ
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ.
    // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 40
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾.
    public static final int THE_BATTLEFIELD_CLOSES_IN_1_MINUTE = 2420; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int THE_BATTLEFIELD_CLOSES_IN_10_SECONDS = 2421; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 10Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int EP_OR_ENERGY_POINTS_REFERS_TO_FUEL = 2422; // EP
    // Ã�Â¾Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â¸Ã�Â¸
    // -
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â°Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â»Ã�Â¸Ã�Â²Ã�Â°.
    public static final int EP_CAN_BE_REFILLED_BY_USING_A_S1_WHILE_SAILING_ON_AN_AIRSHIP = 2423;
    public static final int THE_COLLECTION_HAS_FAILED = 2424;
    public static final int THE_AERIAL_CLEFT_BATTLEFIELD_HAS_BEEN_CLOSED = 2425;
    public static final int C1_HAS_BEEN_EXPELLED_FROM_THE_TEAM = 2426;
    public static final int THE_RED_TEAM_IS_VICTORIOUS = 2427;
    public static final int THE_BLUE_TEAM_IS_VICTORIOUS = 2428;
    public static final int C1_HAS_BEEN_DESIGNATED_AS_THE_TARGET = 2429;
    public static final int C1_HAS_FALLEN_THE_RED_TEAM_S_POINTS_HAVE_INCREASED = 2430;
    public static final int C2_HAS_FALLEN_THE_BLUE_TEAM_S_POINTS_HAVE_INCREASED = 2431;
    public static final int THE_CENTRAL_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED = 2432;
    public static final int THE_FIRST_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED = 2433; // 1-Ã�Â¹
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int THE_SECOND_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED = 2434; // 2-Ã�Â¹
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int THE_THIRD_STRONGHOLD_S_COMPRESSOR_HAS_BEEN_DESTROYED = 2435; // 3-Ã�Â¹
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½.
    public static final int THE_CENTRAL_STRONGHOLD_S_COMPRESSOR_IS_WORKING = 2436; // Ã�Â¡Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½.
    public static final int THE_FIRST_STRONGHOLD_S_COMPRESSOR_IS_WORKING = 2437; // 1-Ã�Â¹
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½
    public static final int THE_SECOND_STRONGHOLD_S_COMPRESSOR_IS_WORKING = 2438; // 2-Ã�Â¹
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½
    public static final int THE_THIRD_STRONGHOLD_S_COMPRESSOR_IS_WORKING = 2439; // 3-Ã�Â¹
    // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½
    public static final int C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_NON_CLASS_LIMITED_MATCH_EVENT = 2440; // $c1
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Âµ
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ
    // Ã�Â±Ã�ÂµÃ�Â·
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â°Ã�Â¼.
    public static final int ONLY_A_PARTY_LEADER_CAN_REQUEST_A_TEAM_MATCH = 2441; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�â€ºÃ�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬
    // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int THE_REQUEST_CANNOT_BE_MADE_BECAUSE_THE_REQUIREMENTS_HAVE_NOT_BEEN_MET_TO_PARTICIPATE_IN_A_TEAM = 2442; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¹.
    // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // 3
    // Ã‘â€¡Ã�ÂµÃ�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�ÂºÃ�Â°.
    public static final int FLAMES_FILLED_WITH_THE_WRATH_OF_VALAKAS_ARE_ENGULFING_YOU = 2443; // Ã�â€œÃ�Â½Ã�ÂµÃ�Â²
    // Ã�â€™Ã�Â°Ã�Â»Ã�Â°Ã�ÂºÃ�Â°Ã‘ï¿½Ã�Â°
    // Ã�Â½Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // Ã�â€™Ã�Â°Ã‘ï¿½.
    public static final int FLAMES_FILLED_WITH_THE_AUTHORITY_OF_VALAKAS_ARE_BINDING_YOUR_MIND = 2444; // Ã�Å¸Ã�Â»Ã�Â°Ã�Â¼Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã�Â»Ã�Â°Ã�ÂºÃ�Â°Ã‘ï¿½Ã�Â°
    // Ã‘ï¿½Ã�Â¾Ã�ÂºÃ‘â‚¬Ã‘Æ’Ã‘Ë†Ã�Â¸Ã�Â»Ã�Â¾
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ.
    public static final int THE_BATTLEFIELD_CHANNEL_HAS_BEEN_ACTIVATED = 2445; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int THE_BATTLEFIELD_CHANNEL_HAS_BEEN_DEACTIVATED = 2446; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â´Ã�ÂµÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int THE_CLOAK_EQUIP_HAS_BEEN_REMOVED_BECAUSE_THE_ARMOR_SET_EQUIP_HAS_BEEN_REMOVED = 2451; // Ã�Â¢Ã�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â¿Ã�Â»Ã�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘â€¦Ã�Â¾Ã�Â²,
    // Ã�Â¿Ã�Â»Ã�Â°Ã‘â€°
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€š.
    public static final int THE_INVENTORY_IS_FULL_SO_IT_CANNOT_BE_EQUIPPED_OR_REMOVED_ON_THE_BELT = 2452; // Ã�â€�Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â¹Ã�ÂºÃ�Â¸
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã‘â€¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’/Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int THE_CLOAK_CANNOT_BE_EQUIPPED_BECAUSE_A_NECESSARY_ITEM_IS_NOT_EQUIPPED = 2453; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ�Â»Ã�Â¸
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã‘â€¹Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�Â»Ã�Â°Ã‘â€°
    // Ã�Â½Ã�Â°Ã�Â´Ã�ÂµÃ‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int KRESNIK_CLASS_AIRSHIP = 2454; // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹ Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã‘â€šÃ�Â¸Ã�Â¿Ã�Â° Ã�Å¡Ã‘â‚¬Ã�ÂµÃ�Â¹Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â°
    public static final int THE_AIRSHIP_MUST_BE_SUMMONED_IN_ORDER_FOR_YOU_TO_BOARD = 2455; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â¼.
    public static final int IN_ORDER_TO_ACQUIRE_AN_AIRSHIP_THE_CLAN_S_LEVEL_MUST_BE_LEVEL_5_OR_HIGHER = 2456; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�ÂµÃ�Â½
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // 5-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int AN_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_EITHER_YOU_HAVE_NOT_REGISTERED_YOUR_AIRSHIP_LICENSE_OR_THE = 2457; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½,
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â½Ã�Âµ
    // Ã�Â¾Ã�Â±Ã�Â»Ã�Â°Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â¼.
    public static final int THE_AIRSHIP_OWNED_BY_THE_CLAN_IS_ALREADY_BEING_USED_BY_ANOTHER_CLAN_MEMBER = 2458; // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_AIRSHIP_SUMMON_LICENSE_HAS_ALREADY_BEEN_ACQUIRED = 2459; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½.
    public static final int THE_CLAN_OWNED_AIRSHIP_ALREADY_EXISTS = 2460; // Ã�Â£
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’.
    public static final int THE_AIRSHIP_OWNED_BY_THE_CLAN_CAN_ONLY_BE_PURCHASED_BY_THE_CLAN_LORD = 2461; // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â½
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int THE_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_YOU_DON_T_HAVE_ENOUGH_S1 = 2462; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // $s1.
    public static final int THE_AIRSHIP_S_FUEL_EP_WILL_SOON_RUN_OUT = 2463; // Ã�Â¢Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¸Ã�Â²Ã�Â¾
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // (EP)
    // Ã�Â½Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã�Âµ.
    public static final int THE_AIRSHIP_S_FUEL_EP_HAS_RUN_OUT_THE_AIRSHIP_S_SPEED_WILL_BE_GREATLY_DECREASED_IN_THIS = 2464; // Ã�â€”Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¢Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¸Ã�Â²Ã�Â¾
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // (EP).
    // Ã�Â¡Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹.
    public static final int YOU_HAVE_SELECTED_A_NON_CLASS_LIMITED_TEAM_MATCH_DO_YOU_WISH_TO_PARTICIPATE = 2465; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã�Â±Ã�ÂµÃ�Â·
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã�Â°Ã�Â¼.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â´Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â¹
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ?
    public static final int A_PET_ON_AUXILIARY_MODE_CANNOT_USE_SKILLS = 2466; // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ ,
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int DO_YOU_WISH_TO_BEGIN_THE_GAME_NOW = 2467; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã‘ï¿½Ã�Â¼Ã�Â¾
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½?
    public static final int YOU_HAVE_USED_A_REPORT_POINT_ON_C1_YOU_HAVE_S2_POINTS_REMAINING_ON_THIS_ACCOUNT = 2468; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¾
    // $c1.
    // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s2
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â².
    public static final int YOU_HAVE_USED_ALL_AVAILABLE_POINTS_POINTS_ARE_RESET_EVERYDAY_AT_NOON = 2469; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â²Ã‘Ë†Ã�Â¸Ã�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸.
    // Ã�Å¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã�Â´Ã�Â½Ã‘ï¿½.
    public static final int THIS_CHARACTER_CANNOT_MAKE_A_REPORT_YOU_CANNOT_MAKE_A_REPORT_WHILE_LOCATED_INSIDE_A_PEACE_ZONE = 2470; // Ã�Â¡Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¾
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�Å¾Ã�Â½
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Å“Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�â€”Ã�Â¾Ã�Â½Ã�Âµ,
    // Ã�Â½Ã�Â°
    // Ã�Å¸Ã�Â¾Ã�Â»Ã�Âµ
    // Ã�â€˜Ã�Â¾Ã‘ï¿½
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�â€™Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int THIS_CHARACTER_CANNOT_MAKE_A_REPORT_THE_TARGET_HAS_ALREADY_BEEN_REPORTED_BY_EITHER_YOUR_CLAN_OR = 2471; // Ã�Â¡Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¾
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�Å¾
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Âµ
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã�Â»
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�Â°Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°,
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â½Ã�Â¾
    // Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // IP.
    public static final int THIS_CHARACTER_CANNOT_MAKE_A_REPORT_BECAUSE_ANOTHER_CHARACTER_FROM_THIS_ACCOUNT_HAS_ALREADY_DONE = 2472; // Ã�Â¡Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¾
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�Â£Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â¼
    // Ã‘ï¿½
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â°.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_CHATTING_WILL_BE_BLOCKED_FOR_10 = 2473; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€¡Ã�Â°Ã‘â€š
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 10-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_PARTY_PARTICIPATION_WILL_BE_BLOCKED_60 = 2474; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 60-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_PARTY_PARTICIPATION_WILL_BE_BLOCKED_120 = 2475; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 120-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_PARTY_PARTICIPATION_WILL_BE_BLOCKED_180 = 2476; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 180-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_ACTIONS_WILL_BE_RESTRICTED_FOR_120 = 2477; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 120-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_ACTIONS_WILL_BE_RESTRICTED_FOR_180 = 2478; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 180-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_ACTIONS_WILL_BE_RESTRICTED_FOR_180_1 = 2479; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 180-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_MOVING_WILL_BE_BLOCKED_FOR_120_MINUTES = 2480; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 120-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int C1_HAS_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_HAS_BEEN_INVESTIGATED = 2481; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $c1
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã�Â»
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â´Ã‘Æ’.
    public static final int C1_HAS_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_CANNOT_JOIN_A_PARTY = 2482; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $c1
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã�Â»
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_CHATTING_IS_NOT_ALLOWED = 2483; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘â€¡Ã�Â°Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_PARTICIPATING_IN_A_PARTY_IS_NOT_ALLOWED = 2484; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_ACTIVITIES_ARE_ONLY_ALLOWED_WITHIN = 2485; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦.
    public static final int YOU_HAVE_BEEN_BLOCKED_DUE_TO_VERIFICATION_THAT_YOU_ARE_USING_A_THIRD_PARTY_PROGRAM_SUBSEQUENT = 2486; // Ã�â€�Ã�Â¾
    // Ã‘ï¿½Ã�Â¸Ã‘â€¦
    // Ã�Â¿Ã�Â¾Ã‘â‚¬
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘Ë†Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€žÃ‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â¼
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦Ã‘ï¿½Ã‘ï¿½
    // Ã‘Æ’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Â¡Ã�Â¾
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â°
    // Ã‘Ë†Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ž
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘Æ’Ã‘ï¿½Ã�Â¼Ã�Â°Ã‘â€šÃ‘â‚¬Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Âµ
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã‘Ë†Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€žÃ‘â€¹,
    // Ã�Â½Ã�Â¾
    // Ã�Â¸
    // Ã�Â½Ã�ÂµÃ‘Æ’Ã�Â´Ã�Â¾Ã�Â±Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â²
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€šÃ�Â°.
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_YOUR_CONNECTION_HAS_BEEN_ENDED_PLEASE = 2487; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¸Ã�Â´Ã�ÂµÃ�Â½Ã‘â€šÃ�Â¸Ã‘â€žÃ�Â¸Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    public static final int YOU_CANNOT_ENTER_AERIAL_CLEFT_BECAUSE_YOU_ARE_NOT_AT_THE_RIGHT_LEVEL_ENTRY_IS_POSSIBLE_ONLY = 2488; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼.
    // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ,
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¡Ã‘Å’
    // 75-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int YOU_MUST_TARGET_A_CONTROL_DEVICE_IN_ORDER_TO_PERFORM_THIS_ACTION = 2489; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¼
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_PERFORM_THIS_ACTION_BECAUSE_YOU_ARE_TOO_FAR_AWAY_FROM_THE_CONTROL_DEVICE = 2490; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�ÂºÃ�Â¾
    // Ã�Â¾Ã‘â€š
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â»Ã‘Å’
    // Ã�Â½Ã�Â°Ã�Â´
    // Ã�Â½Ã�Â¸Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    public static final int YOUR_SHIP_CANNOT_TELEPORT_BECAUSE_IT_DOES_NOT_HAVE_ENOUGH_FUEL_FOR_THE_TRIP = 2491; // Ã�ï¿½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â»Ã�Â¸Ã�Â²Ã�Â°
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int THE_AIRSHIP_HAS_BEEN_SUMMONED_IT_WILL_AUTOMATICALLY_DEPART_IN_S_MINUTES = 2492; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’.
    // Ã�Å¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // %s.
    public static final int ENTER_CHAT_MODE_IS_AUTOMATICALLY_ENABLED_WHEN_YOU_ARE_IN_A_FLYING_TRANSFORMATION_STATE = 2493; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾,
    // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int ENTER_CHAT_MODE_IS_AUTOMATICALLY_ENABLED_WHEN_YOU_ARE_IN_AIRSHIP_CONTROL_STATE = 2494; // Ã�ï¿½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â²
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼,
    // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int W_GO_FORWARD_S_STOP_A_TURN_LEFT_D_TURN_RIGHT_E_INCREASE_ALTITUDE_AND_Q_DECREASE_ALTITUDE = 2495; // W(Ã�â€™Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´),
    // S(Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½),
    // A(Ã�â€™Ã�Â»Ã�ÂµÃ�Â²Ã�Â¾),
    // D(Ã�â€™Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾),
    // E(Ã�ï¿½Ã�Â°Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€™Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ‘Æ’),
    // Q(Ã�Â¡Ã�Â½Ã�Â¸Ã�Â·Ã�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½).
    public static final int IF_YOU_CLICK_ON_A_SKILL_DESIGNATED_ON_YOUR_SHORTCUT_BAR_THAT_SLOT_IS_ACTIVATED_ONCE_ACTIVATED = 2496; // Ã�ï¿½Ã�Â°Ã�Â¶Ã�Â°Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â²Ã�Â¸Ã‘Ë†Ã‘Æ’,
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã‘Å½Ã‘â€°Ã‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ‘Æ’,
    // Ã�â€™Ã‘â€¹
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â°Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²,
    // Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â°Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã�ÂµÃ�Â»,
    // Ã�â€™Ã‘â€¹
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº.
    public static final int TO_CLOSE_THE_CURRENTLY_OPEN_TIP_PLEASE_CANCEL_THE_CHECKED_BOX__SYSTEM_TUTORIAL__IN_OPTIONS = 2497; // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘ï¿½Ã�ÂºÃ�Â°Ã�Â·Ã�ÂºÃ�Â¸,
    // Ã�Â²Ã‘â€¹Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ‘â€šÃ�ÂºÃ‘Æ’
    // "Ã�Â¡Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Âµ Ã�Å¾Ã�Â±Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ"
    // Ã�Â²
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â°Ã‘â€¦
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int DURING_THE_AIRSHIP_CONTROL_STATE_YOU_CAN_ALSO_CHANGE_ALTITUDE_USING_THE_BUTTON_AT_THE_CENTER_OF = 2498; // Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¶Ã�Âµ
    // Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â½Ã�Â¾Ã�Âº
    // Ã�Â¿Ã�Â°Ã�Â½Ã�ÂµÃ�Â»Ã�Â¸
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_CANNOT_COLLECT_BECAUSE_SOMEONE_ELSE_IS_ALREADY_COLLECTING = 2499; // Ã�Å¡Ã‘â€šÃ�Â¾-Ã‘â€šÃ�Â¾
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�Â¸Ã‘â‚¬Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã‘Æ’Ã�Â´Ã‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã‘Æ’Ã�Â´Ã‘Æ’
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½.
    public static final int THE_COLLECTION_HAS_SUCCEEDED = 2500; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â´Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’ Ã‘â‚¬Ã‘Æ’Ã�Â´Ã‘Æ’.
    public static final int YOU_WILL_BE_MOVED_TO_THE_PREVIOUS_CHATTING_CHANNEL_TAB = 2501; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã‘Æ’Ã‘Å½
    // Ã‘â€šÃ�Â°Ã�Â±Ã�Â»Ã�Â¸Ã‘â€ Ã‘Æ’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int YOU_WILL_BE_MOVED_TO_THE_NEXT_CHATTING_CHANNEL_TAB = 2502; // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã‘Æ’Ã‘Å½
    // Ã‘â€šÃ�Â°Ã�Â±Ã�Â»Ã�Â¸Ã‘â€ Ã‘Æ’
    // Ã�ÂºÃ�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int THE_CURRENTLY_SELECTED_TARGET_WILL_BE_CANCELLED = 2503; // Ã�â€™Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int FOCUS_WILL_BE_MOVED_TO_CHAT_WINDOW = 2504; // Ã�ï¿½Ã�ÂºÃ‘â€ Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    public static final int OPENS_OR_CLOSES_THE_INVENTORY_WINDOW = 2505; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½.
    public static final int TEMPORARILY_HIDES_ALL_OPEN_WINDOWS = 2506; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘â€¹Ã�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â°.
    public static final int CLOSES_ALL_OPEN_WINDOWS = 2507; // Ã�â€”Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â°.
    public static final int OPENS_THE_GM_MANAGER_WINDOW = 2508; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // GM.
    public static final int OPENS_THE_GM_PETITION_WINDOW = 2509; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾ Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹
    // GM.
    public static final int THE_BUFF_IN_THE_PARTY_WINDOW_IS_TOGGLED_BUFF_FOR_ONE_INPUT_DEBUFF_FOR_TWO_INPUTS_A_SONG_AND = 2510; // Ã�Å¸Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã�Â²Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã�Â²Ã�Â¾Ã�Â´Ã�Â°,
    // Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â€ Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â´Ã�Â²Ã‘Æ’Ã‘â€¦,
    // Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€ 
    // Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ‘â€¦
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â€šÃ‘â€¹Ã‘â‚¬Ã�ÂµÃ‘â€¦.
    public static final int ACTIVATES_OR_DEACTIVATES_MINIMUM_FRAME_FUNCTION = 2511; // Ã�ï¿½Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â´Ã�ÂµÃ�Â·Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã‘Æ’Ã�Â¼
    // Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã‘â€¡Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¹.
    public static final int RUNS_OR_CLOSES_THE_MSN_MESSENGER_WINDOW = 2512; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹
    // MSN.

    public static final int ASSIGN_1ST_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2513; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 1-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_2ND_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2514; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 2-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_3RD_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2515; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 3-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_4TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2516; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 4-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_5TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2517; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 5-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_6TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2518; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 6-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_7TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2519; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 7-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_8TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2520; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 8-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_9TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2521; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 9-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_10TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2522; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 10-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_11TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2523; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 11-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_12TH_SLOT_SHORTCUT_IN_THE_SHORTCUT_BASE_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT_CANNOT_BE = 2524; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 12-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_1ST_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2525; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 1-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_2ND_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2526; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 2-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_3RD_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2527; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 3-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_4TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2528; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 4-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_5TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2529; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 5-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_6TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2530; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 6-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_7TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2531; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 7-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_8TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2532; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 8-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_9TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2533; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 9-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_10TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2534; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 10-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_11TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2535; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 11-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_12TH_SLOT_SHORTCUT_IN_THE_1ST_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2536; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 12-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 1-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_1ST_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2537; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 1-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_2ND_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2538; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 2-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_3RD_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2539; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 3-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_4TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2540; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 4-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_5TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2541; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 5-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_6TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2542; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 6-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_7TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2543; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 7-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_8TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2544; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 8-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_9TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2545; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 9-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_10TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2546; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 10-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_11TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2547; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 11-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ASSIGN_12TH_SLOT_SHORTCUT_IN_THE_2ND_SHORTCUT_EXPANDED_WINDOW_COMBINATION_OF_CTRL_AND_SHIFT = 2548; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 12-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // 2-Ã�Â¼
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.

    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_1 = 2549; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 1.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_2 = 2550; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 2.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_3 = 2551; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 3.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_4 = 2552; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 4.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_5 = 2553; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 5.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_6 = 2554; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 6.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_7 = 2555; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 7.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_8 = 2556; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 8.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_9 = 2557; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 9.
    public static final int MOVE_THE_SHORTCUT_PAGE_IN_THE_SHORTCUT_BASE_WINDOW_TO_PAGE_10 = 2558; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â±Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ‘â‚¬.
    // 10.

    public static final int OPENS_AND_CLOSES_THE_ACTION_WINDOW_EXECUTING_CHARACTER_ACTIONS_AND_GAME_COMMANDS = 2559; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¹,
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â¸
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int OPENS_AND_CLOSES_THE_GAME_BULLETIN_BOARD = 2560; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘Æ’
    // Ã�Â¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int OPENS_AND_CLOSES_THE_CALCULATOR = 2561; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�ÂºÃ�Â°Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’Ã�Â»Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬.
    public static final int HIDES_OR_SHOWS_THE_CHAT_WINDOW_THE_WINDOW_ALWAYS_SHOWS_BY_DEFAULT = 2562; // Ã�Å¸Ã‘â‚¬Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Â°.
    // Ã�Å¸Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�Â¾Ã�Â»Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½
    // Ã�Â¾Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â´Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int OPENS_AND_CLOSES_THE_CLAN_WINDOW_CONFIRMING_INFORMATION_OF_THE_INCLUDED_CLAN_AND_PERFORMS_THE = 2563; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Âµ
    // Ã�Â¸
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�ÂºÃ�Â°Ã‘ï¿½Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int OPENS_AND_CLOSES_THE_STATUS_WINDOW_SHOWING_THE_DETAILED_STATUS_OF_A_CHARACTER_THAT_YOU_CREATED = 2564; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Æ’Ã‘ï¿½Ã�Â°,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â´Ã�ÂµÃ‘â€šÃ�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int OPENS_AND_CLOSES_THE_HELP_WINDOW = 2565; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã�Â¸.
    public static final int OPENS_OR_CLOSES_THE_INVENTORY_WINDOW_ = 2566; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½.
    public static final int OPENS_AND_CLOSES_THE_MACRO_WINDOW_FOR_MACRO_SETTINGS = 2567; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�ÂµÃ�Âº
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â².
    public static final int OPENS_AND_CLOSES_THE_SKILL_WINDOW_DISPLAYING_THE_LIST_OF_SKILLS_THAT_YOU_CAN_USE = 2568; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹,
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â¶Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Âº
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int HIDES_OR_SHOWS_THE_MENU_WINDOW_THE_WINDOW_SHOWS_BY_DEFAULT = 2569; // Ã�Å¸Ã‘â‚¬Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å½.
    // Ã�Å¸Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�Â¾Ã�Â»Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â½Ã�Â¾.
    public static final int OPENS_AND_CLOSES_THE_MINI_MAP_SHOWING_DETAILED_INFORMATION_ABOUT_THE_GAME_WORLD = 2570; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ‘Æ’,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã�ÂµÃ‘â€šÃ�Â°Ã�Â»Ã�Â¸Ã�Â·Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â¾Ã�Â±
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Âµ.
    public static final int OPENS_AND_CLOSES_THE_OPTION_WINDOW = 2571; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¾Ã�Â¿Ã‘â€ Ã�Â¸Ã�Â¹.
    public static final int OPEN_AND_CLOSE_THE_PARTY_MATCHING_WINDOW_USEFUL_IN_ORGANIZING_A_PARTY_BY_HELPING_TO_EASILY_FIND = 2572; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹,
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã�Â³Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã‘â€¦
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹,
    // Ã�Â¶Ã�ÂµÃ�Â»Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.
    public static final int OPEN_AND_CLOSE_THE_QUEST_JOURNAL_DISPLAYING_THE_PROGRESS_OF_QUESTS = 2573; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¶Ã‘Æ’Ã‘â‚¬Ã�Â½Ã�Â°Ã�Â»
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â²,
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â¶Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€¦Ã�Â¾Ã�Â¶Ã�Â´Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â².
    public static final int HIDES_OR_RE_OPENS_THE_RADAR_MAP_WHICH_ALWAYS_APPEARS_BY_DEFAULT = 2574; // Ã�Å¸Ã‘â‚¬Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�Â°Ã�Â´Ã�Â°Ã‘â‚¬.
    // Ã�Å¸Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�Â¾Ã�Â»Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int HIDE_OR_SHOW_THE_STATUS_WINDOW_THE_WINDOW_WILL_SHOW_BY_DEFAULT = 2575; // Ã�Å¸Ã‘â‚¬Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Æ’Ã‘ï¿½Ã�Â°.
    // Ã�Å¸Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�Â¾Ã�Â»Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int OPENS_AND_CLOSES_THE_SYSTEM_MENU_WINDOW_ENABLES_DETAILED_MENU_SELECTION = 2576; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¸
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â¾Ã�Â¿Ã‘â€ Ã�Â¸Ã�Â¹,
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã�ÂµÃ‘Ë†Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â´Ã�ÂµÃ‘â€šÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å½.
    public static final int DO_NOT_SHOW_DROP_ITEMS_DROPPED_IN_THE_WORLD_GAME_PERFORMANCE_SPEED_CAN_BE_ENHANCED_BY_USING_THIS = 2577; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹.
    // Ã�Â­Ã‘â€šÃ�Â°
    // Ã�Â¾Ã�Â¿Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int A_KEY_TO_AUTOMATICALLY_SEND_WHISPERS_TO_A_TARGETED_CHARACTER = 2578; // Ã�ï¿½Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ�Â½Ã�Â¾Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’.
    public static final int TURNS_OFF_ALL_GAME_SOUNDS = 2579; // Ã�â€™Ã‘â€¹Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘â€¹Ã�Âµ Ã�Â·Ã�Â²Ã‘Æ’Ã�ÂºÃ�Â¸.
    public static final int EXPANDS_EACH_SHORTCUT_WINDOW = 2580; // Ã�Â£Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    public static final int INITIALIZE_USER_INTERFACE_LOCATION_TO_A_DEFAULT_LOCATION = 2581; // Ã�Â£Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¸Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¸Ã�Â½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘â€žÃ�ÂµÃ�Â¹Ã‘ï¿½Ã�Â°
    // Ã�Â¿Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�Â¾Ã�Â»Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½.
    public static final int SPIN_MY_CHARACTER_OR_MOUNTABLE_TO_THE_LEFT = 2582; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â»Ã�ÂµÃ�Â²Ã�Â¾.
    public static final int SPIN_MY_CHARACTER_OR_MOUNTABLE_TO_THE_RIGHT = 2583; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾.
    public static final int SPIN_MY_CHARACTER_OR_MOUNTABLE_FORWARD = 2584; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â³Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´.
    public static final int SPIN_MY_CHARACTER_OR_MOUNTABLE_TO_THE_REAR = 2585; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â³Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â°Ã�Â´.
    public static final int CONTINUE_MOVING_IN_THE_PRESENT_DIRECTION = 2586; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â³Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã�Â°Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸.
    public static final int REDUCE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE = 2587; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¸Ã�Â´
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int ENLARGE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE = 2588; // Ã�Â£Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¸Ã�Â´
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int QUICKLY_SPIN_IN_ALL_DIRECTIONS_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE = 2589; // Ã�Å“Ã�Â³Ã�Â½Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â²Ã�Â¾Ã‘â‚¬Ã�Â°Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¸Ã�Â´
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â½Ã�Â°
    // 180%.
    public static final int OPENS_THE_GM_MANAGER_WINDOW_ = 2590; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // GM.
    public static final int OPENS_THE_GM_PETITION_WINDOW_ = 2591; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã�Â¹ GM.
    public static final int QUICKLY_SWITCH_THE_CONTENT_OF_THE_EXPANDED_SHORTCUT_WINDOW = 2592; // Ã�â€˜Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â¼Ã�Â¾Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    // Ã�Â­Ã‘â€šÃ�Â°
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°.
    public static final int ADVANCE_BY_A_SET_DISTANCE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE = 2593; // Ã�Â¡Ã�Â¼Ã�ÂµÃ‘â€°Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¸Ã�Â´
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â²Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã‘â€ Ã�Â¸Ã‘Å½.
    public static final int RETREAT_BY_A_SET_DISTANCE_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE = 2594; // Ã�Â¡Ã�Â¼Ã�ÂµÃ‘â€°Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¸Ã�Â´
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â°Ã�Â´
    // Ã�Â½Ã�Â°
    // Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã‘â€ Ã�Â¸Ã‘Å½.
    public static final int RESET_THE_VIEWING_POINT_OF_MY_CHARACTER_OR_MOUNTABLE = 2595; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¸Ã�Â´
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â¿Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�Â¾Ã�Â»Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘Å½.
    public static final int NO_TRANSLATION_REQUIRED_2596 = 2596; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2597 = 2597; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2598 = 2598; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2599 = 2599; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int THE_MATCH_IS_BEING_PREPARED_PLEASE_TRY_AGAIN_LATER = 2701; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Âº
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼.
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¶Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int YOU_WERE_EXCLUDED_FROM_THE_MATCH_BECAUSE_THE_REGISTRATION_COUNT_WAS_NOT_CORRECT = 2702; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¾
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¸Ã�Â·
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹.
    public static final int THE_TEAM_WAS_ADJUSTED_BECAUSE_THE_POPULATION_RATIO_WAS_NOT_CORRECT = 2703; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â±Ã�Â°Ã�Â»Ã�Â°Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½.
    public static final int YOU_CANNOT_REGISTER_BECAUSE_CAPACITY_HAS_BEEN_EXCEEDED = 2704; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¾
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¸.
    public static final int THE_MATCH_WAITING_TIME_WAS_EXTENDED_BY_1_MINUTE = 2705; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾
    // Ã�Â½Ã�Â°
    // 1Ã�Â¼Ã�Â¸Ã�Â½.
    public static final int YOU_CANNOT_ENTER_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS = 2706; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸.
    public static final int YOU_CANNOT_MAKE_ANOTHER_REQUEST_FOR_10_SECONDS_AFTER_CANCELLING_A_MATCH_REGISTRATION = 2707; // Ã�â€™
    // Ã‘â€šÃ�ÂµÃ‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // 10-Ã‘â€šÃ�Â¸
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int YOU_CANNOT_REGISTER_WHILE_POSSESSING_A_CURSED_WEAPON = 2708; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â»Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Âµ
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ.
    public static final int APPLICANTS_FOR_THE_OLYMPIAD_UNDERGROUND_COLISEUM_OR_KRATEI_S_CUBE_MATCHES_CANNOT_REGISTER = 2709; // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â°Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ,
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â·Ã�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�â€”Ã�Â°Ã�Â»
    // Ã�â€˜Ã�Â¾Ã�ÂµÃ�Â²
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Å¡Ã‘Æ’Ã�Â±
    // Ã�Å¡Ã‘â‚¬Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int CURRENT_LOCATION__S1_S2_S3_NEAR_THE_KEUCEREUS_CLAN_ASSOCIATION_LOCATION = 2710; // Ã�Å¡Ã�Â¾Ã�Â¾Ã‘â‚¬Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹:
    // $s1,
    // $s2,
    // $s3
    // (Ã�Å¾Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�â€˜Ã�Â°Ã�Â·Ã‘â€¹
    // Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Å¡Ã�ÂµÃ‘â€ Ã�ÂµÃ‘â‚¬Ã‘Æ’Ã‘ï¿½Ã�Â°)
    public static final int CURRENT_LOCATION__S1_S2_S3_INSIDE_THE_SEED_OF_INFINITY = 2711; // Ã�Å¡Ã�Â¾Ã�Â¾Ã‘â‚¬Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹:
    // $s1,
    // $s2,
    // $s3
    // (Ã�â€™Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�â€˜Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸Ã‘ï¿½)
    public static final int CURRENT_LOCATION__S1_S2_S3_OUTSIDE_THE_SEED_OF_INFINITY = 2712; // Ã�Å¡Ã�Â¾Ã�Â¾Ã‘â‚¬Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹:
    // $s1,
    // $s2,
    // $s3
    // (Ã�â€™Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½)
    public static final int ______________________________________________________ = 2713; // ------------------------------------------------------
    public static final int ______________________________________________________________________ = 2714; // ----------------------------------------------------------------------
    public static final int AIRSHIPS_CANNOT_BE_BOARDED_IN_THE_CURRENT_AREA = 2715; // Ã�â€™
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼.
    public static final int CURRENT_LOCATION__S1_S2_S3_INSIDE_AERIAL_CLEFT = 2716; // Ã�Å¡Ã�Â¾Ã�Â¾Ã‘â‚¬Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹:
    // $s1,
    // $s2,
    // $s3
    // (Ã�â€™Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½)
    public static final int THE_AIRSHIP_WILL_LAND_AT_THE_WHARF_SHORTLY = 2717; // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â±Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�â€™Ã�Â¾Ã�Â·Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�â€œÃ�Â°Ã�Â²Ã�Â°Ã�Â½Ã‘Å’.
    public static final int THE_SKILL_CANNOT_BE_USED_BECAUSE_THE_TARGET_S_LOCATION_IS_TOO_HIGH_OR_LOW = 2718; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ�Â¾
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â¸Ã�Â·Ã�ÂºÃ�Â¾,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼.
    public static final int ONLY_NON_COMPRESSED_256_COLOR_BMP_BITMAP_FILES_CAN_BE_REGISTERED = 2719; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¶Ã�Â°Ã‘â€šÃ‘â€¹Ã�Â¹
    // bmp
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»
    // Ã‘ï¿½
    // Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â³Ã�Â°Ã�Â¼Ã�Â¼Ã�Â¾Ã�Â¹
    // 256.
    public static final int INSTANT_ZONE_FROM_HERE__S1_S_ENTRY_HAS_BEEN_RESTRICTED_YOU_CAN_CHECK_THE_NEXT_ENTRY_POSSIBLE = 2720; // Ã�â€™Ã‘â€¦Ã�Â¾Ã�Â´
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’
    // $s1
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€š.
    // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã‘Æ’Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // "/Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½ Ã�â€”Ã�Â¾Ã�Â½Ã�Â°".
    public static final int BOARDING_OR_CANCELLATION_OF_BOARDING_ON_AIRSHIPS_IS_NOT_ALLOWED_IN_THE_CURRENT_AREA = 2721; // Ã�â€™
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â·Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’/Ã‘ï¿½Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã‘ï¿½
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½.
    public static final int ANOTHER_AIRSHIP_HAS_ALREADY_BEEN_SUMMONED_AT_THE_WHARF_PLEASE_TRY_AGAIN_LATER = 2722; // Ã�â€™
    // Ã�â€™Ã�Â¾Ã�Â·Ã�Â´Ã‘Æ’Ã‘Ë†Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�â€œÃ�Â°Ã�Â²Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¹
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’.
    // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int THE_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_YOU_DONT_HAVE_ENOUGH_S1 = 2723; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // $s1.
    public static final int THE_AIRSHIP_CANNOT_BE_PURCHASED_BECAUSE_YOU_DONT_HAVE_ENOUGH_S1 = 2724; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // $s1.
    public static final int YOU_CANNOT_SUMMON_THE_AIRSHIP_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS = 2725; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼.
    public static final int YOU_CANNOT_PURCHASE_THE_AIRSHIP_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS = 2726; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼.
    public static final int YOU_CANNOT_MOUNT_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS = 2727; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã�Â¼.
    public static final int THIS_ACTION_IS_PROHIBITED_WHILE_MOUNTED = 2728; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�Â°
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�Âµ.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_TRANSFORMED = 2729; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_YOU_ARE_PETRIFIED = 2730; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â»Ã�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHEN_YOU_ARE_DEAD = 2731; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²Ã‘â€¹Ã�Â¼.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_FISHING = 2732; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_IN_A_BATTLE = 2733; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_IN_A_DUEL = 2734; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_IN_A_SITTING_POSITION = 2735; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã‘ï¿½Ã�Â¸Ã�Â´Ã‘ï¿½.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_USING_A_SKILL = 2736; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã‘â€šÃ�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã�Â¸Ã�Â½Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_A_CURSED_WEAPON_IS_EQUIPPED = 2737; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½,
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â»Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Âµ
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_HOLDING_A_FLAG = 2738; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½,
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã�Â²
    // Ã‘â€žÃ�Â»Ã�Â°Ã�Â³.
    public static final int YOU_CANNOT_CONTROL_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS = 2739; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½.
    public static final int THIS_ACTION_IS_PROHIBITED_WHILE_CONTROLLING = 2740; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½.
    public static final int YOU_CAN_CONTROL_THE_AIRSHIP_BY_TARGETING_THE_AIRSHIP_S_CONTROL_KEY_AND_PRESSING_THE__CONTROL_ = 2741; // Ã�Â£Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸
    // Ã�Å¸Ã�Â°Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â£Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // "Ã�Â£Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’"
    public static final int ANY_CHARACTER_RIDING_THE_AIRSHIP_CAN_CONTROL_IT = 2742; // Ã�â€ºÃ‘Å½Ã�Â±Ã�Â¾Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�Âµ,
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�ÂµÃ�Â±Ã‘ï¿½.
    public static final int IF_YOU_RESTART_WHILE_ON_AN_AIRSHIP_YOU_WILL_RETURN_TO_THE_DEPARTURE_LOCATION = 2743; // Ã�â€™Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã�Â²
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�Âº
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�Âµ,
    // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã�ÂºÃ�Â°Ã�Â¶Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘â€šÃ�Â¾Ã‘â€¡Ã�ÂºÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½.
    public static final int IF_YOU_PRESS_THE__CONTROL_CANCEL__ACTION_BUTTON_YOU_CAN_EXIT_THE_CONTROL_STATE_AT_ANY_TIME = 2744; // Ã�Â¡
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // "Ã�â€™Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸"
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼.
    public static final int THE__MOUNT_CANCEL__ACTION_BUTTON_ALLOWS_YOU_TO_DISMOUNT_BEFORE_THE_AIRSHIP_DEPARTS = 2745; // Ã�Â¡
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // "Ã�Â¡Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸"
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã‘ï¿½
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½.
    public static final int USE_THE__DEPART__ACTION_TO_MAKE_THE_AIRSHIP_DEPART = 2746; // Ã�Â¡
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // "Ã�â€™Ã�Â·Ã�Â»Ã�ÂµÃ‘â€š"
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�Âµ.
    public static final int AIRSHIP_TELEPORT_IS_POSSIBLE_THROUGH_THE__DEPART__ACTION_AND_IN_THAT_CASE_FUEL_EP_IS_CONSUMED = 2747; // Ã�Â¡
    // Ã�Â¿Ã�Â¾Ã�Â¼Ã�Â¾Ã‘â€°Ã‘Å’Ã‘Å½
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // "Ã�â€™Ã�Â·Ã�Â»Ã�ÂµÃ‘â€š"
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â²
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�Âµ,
    // Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼
    // Ã�Â·Ã�Â°Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â»Ã�Â¸Ã�Â²Ã�Â¾(EP).
    public static final int YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_CANNOT_REPORT_OTHER_USERS = 2748; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â¼,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�ÂµÃ‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã�ÂµÃ�Â³Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â¼Ã�Â¾Ã�Â¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¾
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Âµ.
    public static final int YOU_HAVE_REACHED_YOUR_CRYSTALLIZATION_LIMIT_AND_CANNOT_CRYSTALLIZE_ANY_MORE = 2749; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘â€¡Ã�Â¸Ã‘ï¿½Ã�Â»Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â»Ã�Â¸Ã�Â·Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¹.
    public static final int THE_S1_WARD_HAS_BEEN_DESTROYED_C2_NOW_HAS_THE_TERRITORY_WARD = 2750; // Ã�â€”Ã�Â½Ã�Â°Ã�Â¼Ã‘ï¿½
    // $s1
    // Ã�Â¿Ã�Â¾Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â½Ã�Â¾!
    // $c2
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â¸Ã�Â»
    // Ã�â€”Ã�Â½Ã�Â°Ã�Â¼Ã‘ï¿½.
    public static final int THE_CHARACTER_THAT_ACQUIRED_S1_WARD_HAS_BEEN_KILLED = 2751; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶,
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã�â€”Ã�Â½Ã�Â°Ã�Â¼Ã‘ï¿½,
    // $s1
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â¸Ã�Â±.
    public static final int THE_WAR_FOR_S1_HAS_BEEN_DECLARED = 2752; // Ã�Å¾Ã�Â±Ã‘Å Ã‘ï¿½Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Â° Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // $s1.
    public static final int A_POWERFUL_ATTACK_IS_PROHIBITED_WHEN_ALLIED_TROOPS_ARE_THE_TARGET = 2753; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â°.
    public static final int PVP_MATCHES_SUCH_AS_OLYMPIAD_UNDERGROUND_COLISEUM_AERIAL_CLEFT_KRATEI_S_CUBE_AND_HANDY_S_BLOCK = 2754; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¸Ã‘â€¦
    // PVP
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦
    // (Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Â°,
    // Ã�Å¸Ã�Â¾Ã�Â´Ã�Â·Ã�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�â€”Ã�Â°Ã�Â»
    // Ã�â€˜Ã�Â¾Ã�ÂµÃ�Â²,
    // Ã�Â£Ã‘â€°Ã�ÂµÃ�Â»Ã‘Å’Ã�Âµ,
    // Ã�Å¡Ã‘Æ’Ã�Â±
    // Ã�Å¡Ã‘â‚¬Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¸
    // Ã�Â¸
    // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°).
    public static final int C1_HAS_BEEN_DESIGNATED_AS_CAT = 2755; // $c1 Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»
    // CAT.
    public static final int ANOTHER_PLAYER_IS_PROBABLY_CONTROLLING_THE_TARGET = 2756; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â´
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â»Ã�ÂµÃ�Â¼
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â°.
    public static final int THE_TARGET_IS_MOVING_SO_YOU_HAVE_FAILED_TO_MOUNT = 2757; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int YOU_CANNOT_CONTROL_THE_TARGET_WHILE_A_PET_OR_SERVITOR_IS_SUMMONED = 2758; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â³Ã‘Æ’.
    public static final int WHEN_ACTIONS_ARE_PROHIBITED_YOU_CANNOT_MOUNT_A_MOUNTABLE = 2759; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘ï¿½Ã�ÂµÃ�Â´Ã�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½Ã‘â€¦.
    public static final int WHEN_ACTIONS_ARE_PROHIBITED_YOU_CANNOT_CONTROL_THE_TARGET = 2760; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½Ã‘â€¦.
    public static final int YOU_MUST_TARGET_THE_ONE_YOU_WISH_TO_CONTROL = 2761; // Ã�Â¡Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’.
    public static final int YOU_CANNOT_CONTROL_BECAUSE_YOU_ARE_TOO_FAR = 2762; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�ÂºÃ�Â¾.
    public static final int YOU_CANNOT_ENTER_THE_BATTLEFIELD_WHILE_IN_A_PARTY_STATE = 2763; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Å¸Ã�Â¾Ã�Â»Ã�Âµ
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹,
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ.
    public static final int YOU_CANNOT_ENTER_BECAUSE_THE_CORRESPONDING_ALLIANCE_CHANNEL_S_MAXIMUM_NUMBER_OF_ENTRANTS_HAS = 2764; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â»Ã�Â¾
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã‘Æ’Ã�Â¼Ã�Â°,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½.
    public static final int ONLY_THE_ALLIANCE_CHANNEL_LEADER_CAN_ATTEMPT_ENTRY = 2765; // Ã�â€™Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�â€œÃ�Â»Ã�Â°Ã�Â²Ã�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»Ã�Â°.
    public static final int SEED_OF_INFINITY_STAGE_1_ATTACK_IN_PROGRESS = 2766; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // 1-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�â€˜Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸Ã‘ï¿½
    public static final int SEED_OF_INFINITY_STAGE_2_ATTACK_IN_PROGRESS = 2767; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // 2-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�â€˜Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸Ã‘ï¿½
    public static final int SEED_OF_INFINITY_CONQUEST_COMPLETE = 2768; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�â€˜Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    public static final int SEED_OF_INFINITY_STAGE_1_DEFENSE_IN_PROGRESS = 2769; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Å¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // 1-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�â€˜Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸Ã‘ï¿½
    public static final int SEED_OF_INFINITY_STAGE_2_DEFENSE_IN_PROGRESS = 2770; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Å¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // 2-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�â€˜Ã�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â¸Ã‘ï¿½
    public static final int SEED_OF_DESTRUCTION_ATTACK_IN_PROGRESS = 2771; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Å¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Â°
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    public static final int SEED_OF_DESTRUCTION_CONQUEST_COMPLETE = 2772; // Ã�Â¡Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    public static final int SEED_OF_DESTRUCTION_DEFENSE_IN_PROGRESS = 2773; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Å¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â Ã�Â°Ã�Â·Ã‘â‚¬Ã‘Æ’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    public static final int YOU_CAN_MAKE_ANOTHER_REPORT_IN_S1_MINUTES_YOU_HAVE_S2_POINTS_REMAINING_ON_THIS_ACCOUNT = 2774; // Ã�Â§Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘Å½.
    // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // $s2
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â².
    public static final int THE_MATCH_CANNOT_TAKE_PLACE_BECAUSE_A_PARTY_MEMBER_IS_IN_THE_PROCESS_OF_BOARDING = 2775; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã�Â¾Ã�Â¼
    // Ã�Â½Ã�Â°
    // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Âµ
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã�Â¼Ã�ÂµÃ�Â¶Ã�Â´Ã‘Æ’
    // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int THE_EFFECT_OF_TERRITORY_WARD_IS_DISAPPEARING = 2776; // Ã�Â­Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€š
    // Ã�â€”Ã�Â½Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¿Ã�Â°Ã�Â´Ã�Â°Ã�ÂµÃ‘â€š.
    public static final int THE_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_ENTERED_YOUR_CLAN_CAN_NOW_SUMMON_THE_AIRSHIP = 2777; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½.
    // Ã�Â¢Ã�ÂµÃ�Â¿Ã�ÂµÃ‘â‚¬Ã‘Å’
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’.
    public static final int YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD = 2778; // Ã�Â¡Ã�Â¾
    // Ã�â€”Ã�Â½Ã�Â°Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â¼
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€š
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    public static final int FURTHER_INCREASE_IN_ALTITUDE_IS_NOT_ALLOWED = 2779; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ‘â€¹.
    public static final int FURTHER_DECREASE_IN_ALTITUDE_IS_NOT_ALLOWED = 2780; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ�Âµ.
    public static final int NUMBER_OF_UNITS__S1 = 2781; // $s1 Ã�ÂµÃ�Â´.
    public static final int NUMBER_OF_PEOPLE__S1 = 2782; // $s1 Ã‘â€¡Ã�ÂµÃ�Â».
    public static final int NO_ONE_IS_LEFT_FROM_THE_OPPOSING_TEAM_THUS_VICTORY_IS_YOURS = 2783; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã�Â»
    // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã‘Æ’.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»Ã�Â¸.
    public static final int THE_BATTLEFIELD_HAS_BEEN_CLOSED_THE_MATCH_HAS_ENDED_IN_A_TIE_BECAUSE_THE_MATCH_LASTED_FOR_S1 = 2784; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�Âº
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã�Â»
    // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã‘Æ’.
    // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â¾
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // $s2
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â½Ã‘Æ’Ã�Â²
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã‘â€¦
    // 15-Ã‘â€šÃ�Â¸
    // Ã�Â¼Ã�Â¸Ã�Â½.,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â¸Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â½Ã�Â¸Ã‘â€¡Ã‘Å’Ã‘ï¿½.
    public static final int IT_S_A_LARGE_SCALED_AIRSHIP_FOR_TRANSPORTATIONS_AND_BATTLES_AND_CAN_BE_OWNED_BY_THE_UNIT_OF_CLAN = 2785; // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â°Ã�Â´Ã�Â»Ã�ÂµÃ�Â¶Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°Ã�Â¼,
    // Ã�Â¾Ã�Â½
    // Ã�Â¾Ã‘â€šÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€š
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¾Ã�Âº
    // Ã�Â¸
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â².
    public static final int START_ACTION_IS_AVAILABLE_ONLY_WHEN_CONTROLLING_THE_AIRSHIP = 2786; // Ã�â€™Ã�Â·Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã‘Æ’Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�ÂµÃ�Â¼.
    public static final int C1_HAS_DRAINED_YOU_OF_S2_HP = 2787; // $c1 Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â»
    // Ã‘Æ’ Ã�â€™Ã�Â°Ã‘ï¿½ $s2 HP.
    public static final int MERCENARY_PARTICIPATION_IS_REQUESTED_IN_S1_TERRITORY = 2788; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // $s1
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int MERCENARY_PARTICIPATION_REQUEST_IS_CANCELLED_IN_S1_TERRITORY = 2789; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // $s1
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int CLAN_PARTICIPATION_IS_REQUESTED_IN_S1_TERRITORY = 2790; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // $s1
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int CLAN_PARTICIPATION_REQUEST_IS_CANCELLED_IN_S1_TERRITORY = 2791; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // $s1
    // Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int _50_CLAN_REPUTATION_POINTS_WILL_BE_AWARDED_DO_YOU_WISH_TO_CONTINUE = 2792; // Ã�Â£Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â‚¬Ã�ÂµÃ�Â¿Ã‘Æ’Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°
    // 50
    // Ã�Â·Ã�Â°
    // Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�ÂµÃ�Â¹
    // Ã‘â‚¬Ã�ÂµÃ�Â¿Ã‘Æ’Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int THE_MINIMUM_NUMBER_S1_OF_PEOPLE_TO_ENTER_INSTANT_ZONE_IS_NOT_MET_AND_ENTRY_IS_NOT_AVAILABLE = 2793; // Ã�Å“Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²,
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾Ã�Âµ
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’($s1
    // Ã‘â€¡Ã�ÂµÃ�Â».)
    // Ã�Â½Ã�Âµ
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â½Ã‘Æ’Ã‘â€šÃ�Â¾,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    public static final int THE_TERRITORY_WAR_CHANNEL_AND_FUNCTIONS_WILL_NOW_BE_DEACTIVATED = 2794; // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â¸
    // Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â´Ã�ÂµÃ�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã‘â€¹.
    public static final int YOU_VE_ALREADY_REQUESTED_A_TERRITORY_WAR_IN_ANOTHER_TERRITORY_ELSEWHERE = 2795; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¸Ã�Âµ
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸.
    public static final int THE_CLAN_WHO_OWNS_THE_TERRITORY_CANNOT_PARTICIPATE_IN_THE_TERRITORY_WAR_AS_MERCENARIES = 2796; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã‘ï¿½Ã�Â¼Ã�Â¸,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â½Ã�Â°Ã�ÂµÃ�Â¼Ã�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â².
    public static final int IT_IS_NOT_A_TERRITORY_WAR_REGISTRATION_PERIOD_SO_A_REQUEST_CANNOT_BE_MADE_AT_THIS_TIME = 2797; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã‘â‚¬Ã�ÂµÃ�Â³Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½.
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ.
    public static final int THE_TERRITORY_WAR_WILL_END_IN_S1_HOURS = 2798; // Ã�â€�Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s1
    // Ã‘â€¡!
    public static final int THE_TERRITORY_WAR_WILL_END_IN_S1_MINUTES = 2799; // Ã�â€�Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½!

    public static final int NO_TRANSLATION_REQUIRED_2800 = 2800; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2801 = 2801; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2802 = 2802; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2803 = 2803; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2804 = 2804; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2805 = 2805; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2806 = 2806; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2807 = 2807; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2808 = 2808; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2809 = 2809; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2810 = 2810; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2811 = 2811; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2812 = 2812; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2813 = 2813; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2814 = 2814; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2815 = 2815; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’

    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_1 = 2816; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 1-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_2 = 2817; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 2-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_3 = 2818; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 3-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_4 = 2819; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 4-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_5 = 2820; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 5-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_6 = 2821; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 6-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_7 = 2822; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 7-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_8 = 2823; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 8-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_9 = 2824; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 9-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_10 = 2825; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 10-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_11 = 2826; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 11-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_FLYING_TRANSFORMED_OBJECT_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_12 = 2827; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 12-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â»Ã�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_1_SLOT_THE_CTRL = 2828; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 1-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_2_SLOT_THE_CTRL = 2829; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 2-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_3_SLOT_THE_CTRL = 2830; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 3-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_4_SLOT_THE_CTRL = 2831; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 4-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_5_SLOT_THE_CTRL = 2832; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 5-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_6_SLOT_THE_CTRL = 2833; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 6-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_7_SLOT_THE_CTRL = 2834; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 7-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_8_SLOT_THE_CTRL = 2835; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 8-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_9_SLOT_THE_CTRL = 2836; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 9-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_10_SLOT_THE_CTRL = 2837; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 10-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_11_SLOT_THE_CTRL = 2838; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 11-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int DESIGNATE_A_SHORTCUT_KEY_FOR_THE_MOUNTABLE_EXCLUSIVE_USE_SHORTCUT_WINDOW_S_NO_12_SLOT_THE_CTRL = 2839; // Ã�ï¿½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�Âº
    // Ã�Âº
    // 12-Ã�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â»Ã�Â¾Ã‘â€šÃ‘Æ’
    // Ã�Â²
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â±Ã�Â¸Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ctrl+Shift
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.

    public static final int EXECUTE_THE_DESIGNATED_SHORTCUT_S_ACTION_SKILL_MACRO = 2840; // Ã�â€”Ã�Â°Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�ÂºÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â²Ã‘ï¿½,
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€¹,
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â².
    public static final int RAISE_MY_CHARACTER_TO_THE_TOP = 2841; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // Ã�Â²Ã�Â·Ã�Â»Ã�ÂµÃ‘â€šÃ�ÂµÃ‘â€šÃ‘Å’
    // Ã�Â²Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦.
    public static final int LOWER_MY_CHARACTER_TO_THE_BOTTOM = 2842; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�Â¸Ã�Â·.
    public static final int RAISE_THE_CONTROLLED_MOUNTABLE_TO_THE_TOP = 2843; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â²Ã�Â·Ã�Â»Ã�ÂµÃ‘â€šÃ�ÂµÃ‘â€šÃ‘Å’
    // Ã�Â²Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦
    // Ã�Â½Ã�Â°
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼.
    public static final int LOWER_THE_CONTROLLED_MOUNTABLE_TO_THE_BOTTOM = 2844; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã�Â¸Ã�Â·
    // Ã�Â½Ã�Â°
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼.
    public static final int AUTOMATICALLY_SEND_FORWARD_MY_CHARACTER_OR_MOUNTABLE = 2845; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â²Ã�Â¾Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â´Ã�Â²Ã�Â¸Ã�Â³Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã�Â°Ã�Â²Ã‘â€šÃ�Â¾Ã�Â¼Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸.
    public static final int NO_TRANSLATION_REQUIRED_2846 = 2846; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2847 = 2847; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2848 = 2848; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2849 = 2849; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int NO_TRANSLATION_REQUIRED_2850 = 2850; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘Å’
    public static final int STOP_ALL_ACTIONS_OF_MY_CHARACTER = 2851; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int STOP_ALL_ACTIONS_OF_MY_CONTROLLED_MOUNTABLE = 2852; // Ã�Å¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â²Ã�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¶Ã�Â¸Ã�Â²Ã�Â¾Ã‘â€šÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾.
    public static final int IF_YOU_JOIN_THE_CLAN_ACADEMY_YOU_CAN_BECOME_A_CLAN_MEMBER_AND_LEARN_THE_GAME_SYSTEM_UNTIL_YOU = 2853; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã�Â²
    // Ã�Â²
    // Ã�Â°Ã�ÂºÃ�Â°Ã�Â´Ã�ÂµÃ�Â¼Ã�Â¸Ã‘Å½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾Ã�Â¼
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â¸
    // Ã�Â¸Ã�Â·Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã‘Æ’
    // Ã�Â´Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // 40-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€š
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â»Ã‘Å’Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½,
    // Ã‘â€šÃ�Â¾
    // Ã�Â¼Ã‘â€¹
    // Ã‘â‚¬Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã�Â´Ã‘Æ’Ã�ÂµÃ�Â¼
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â°Ã�ÂºÃ�Â°Ã�Â´Ã�ÂµÃ�Â¼Ã�Â¸Ã‘Å½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int IF_YOU_BECOME_LEVEL_40_THE_SECOND_CLASS_CHANGE_IS_AVAILABLE_IF_YOU_COMPLETE_THE_SECOND_CLASS = 2854; // Ã�Å¸Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // 40-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€žÃ�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¸Ã�Â¸.
    // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€žÃ�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¸Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â²Ã�Â¾Ã�Â·Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã‘â€š.
    public static final int YOU_CAN_SEE_THE_GAME_HELP = 2855; // You can see the
    // game help.
    public static final int YOU_CAN_ASK_A_QUESTION_ABOUT_YOUR_GAME_PROGRESS_TO_A_GM = 2856; // You
    // can
    // ask
    // a
    // question
    // about
    // your
    // game
    // progress
    // to
    // a
    // GM.
    public static final int YOU_CAN_SELECT_SEVERAL_OPTIONS_RELATED_TO_THE_GAME_INCLUDING_GRAPHIC_SETTINGS_AND_SOUND_SETTINGS = 2857; // You
    // can
    // select
    // several
    // options
    // related
    // to
    // the
    // game,
    // including
    // graphic
    // settings
    // and
    // sound
    // settings.
    public static final int YOU_ARE_RESTARTING_THE_GAME_AS_ANOTHER_CHARACTER = 2858; // You
    // are
    // restarting
    // the
    // game
    // as
    // another
    // character.
    public static final int YOU_ARE_QUITTING_THE_GAME_CLIENT_AND_LOGGING_OUT_FROM_THE_SERVER = 2859; // You
    // are
    // quitting
    // the
    // game
    // client
    // and
    // logging
    // out
    // from
    // the
    // server.
    public static final int THIS_DISPLAYS_A_LIST_OF_MY_CHARACTER_S_SKILL_AND_MAGIC_ABILITIES = 2860; // This
    // displays
    // a
    // list
    // of
    // my
    // character's
    // skill
    // and
    // magic
    // abilities.
    public static final int THIS_CONFIRMS_MY_CHARACTER_S_CLAN_INFORMATION_AND_MANAGES_THE_CLAN = 2861; // This
    // confirms
    // my
    // character's
    // clan
    // information
    // and
    // manages
    // the
    // clan.
    public static final int THIS_DISPLAYS_ALL_THE_ACTIONS_THAT_MY_CHARACTER_CAN_TAKE = 2862; // This
    // displays
    // all
    // the
    // actions
    // that
    // my
    // character
    // can
    // take.
    public static final int THIS_DISPLAYS_THE_LIST_OF_ALL_THE_QUESTS_THAT_MY_CHARACTER_IS_UNDERTAKING_THE_QUEST_PROGRESS = 2863; // This
    // displays
    // the
    // list
    // of
    // all
    // the
    // quests
    // that
    // my
    // character
    // is
    // undertaking.
    // The
    // quest
    // progress
    // status
    // can
    // be
    // easily
    // managed.
    public static final int THIS_DISPLAYS_MY_CHARACTER_S_DETAILED_STATUS_INFORMATION_I_CAN_EASILY_CONFIRM_WHEN_AN_ITEM_IS = 2864; // This
    // displays
    // my
    // character's
    // detailed
    // status
    // information.
    // I
    // can
    // easily
    // confirm
    // when
    // an
    // item
    // is
    // equipped,
    // when
    // a
    // buff
    // is
    // received,
    // and
    // how
    // much
    // stronger
    // my
    // character
    // has
    // become.
    public static final int THIS_OPENS_AN_INVENTORY_WINDOW_WHERE_I_CAN_CHECK_THE_LIST_OF_ALL_MY_CHARACTER_S_ITEMS = 2865; // This
    // opens
    // an
    // inventory
    // window
    // where
    // I
    // can
    // check
    // the
    // list
    // of
    // all
    // my
    // character's
    // items.
    public static final int I_CAN_SEE_INFORMATION_ABOUT_MY_LOCATION_BY_OPENING_A_MINI_MAP_WINDOW_AND_I_CAN_CHECK_CURRENT = 2866; // I
    // can
    // see
    // information
    // about
    // my
    // location
    // by
    // opening
    // a
    // mini-map
    // window,
    // and
    // I
    // can
    // check
    // current
    // information
    // about
    // the
    // entire
    // game
    // world.
    public static final int CLICK_HERE_TO_SEE_A_GAME_SYSTEM_MENU_THAT_CONTAINS_VARIOUS_FUNCTIONS_OF_THE_GAME_YOU_CAN_CHECK = 2867; // Click
    // here
    // to
    // see
    // a
    // game
    // system
    // menu
    // that
    // contains
    // various
    // functions
    // of
    // the
    // game.
    // You
    // can
    // check
    // information
    // about
    // the
    // game
    // bulletin,
    // macro,
    // help,
    // GM
    // suppression
    // button,
    // game
    // option
    // button,
    // game
    // restart
    // button
    // and
    // the
    // game
    // quit
    // button.
    public static final int IF_YOU_CLICK_THE_CHAT_TAB_YOU_CAN_SELECT_AND_VIEW_THE_MESSAGES_OF_YOUR_DESIRED_GROUP_THE_TABS = 2868; // If
    // you
    // click
    // the
    // Chat
    // tab,
    // you
    // can
    // select
    // and
    // view
    // the
    // messages
    // of
    // your
    // desired
    // group.
    // The
    // tabs
    // can
    // be
    // separated
    // by
    // using
    // drag-and-drop.
    public static final int YOU_CAN_CLOSE_OR_OPEN_SPECIFIC_MESSAGES_FROM_THE_CHAT_SCREEN_YOU_CAN_CLOSE_OR_OPEN_THE_SYSTEM = 2869; // You
    // can
    // close
    // or
    // open
    // specific
    // messages
    // from
    // the
    // chat
    // screen.
    // You
    // can
    // close
    // or
    // open
    // the
    // system
    // message
    // exclusive
    // window.
    public static final int YOU_CAN_LOGIN_ONTO_MSN_MESSENGER_YOU_CAN_ALSO_CHAT_WITH_REGISTERED_FRIENDS_WITHIN_THE_GAME = 2870; // You
    // can
    // login
    // onto
    // MSN
    // Messenger.
    // You
    // can
    // also
    // chat
    // with
    // registered
    // friends
    // within
    // the
    // game.
    public static final int YOU_CAN_USE_THE_PARTY_MATCHING_FUNCTION_THAT_ALLOWS_YOU_TO_EASILY_FORM_PARTIES_WITH_OTHER = 2871; // You
    // can
    // use
    // the
    // party
    // matching
    // function
    // that
    // allows
    // you
    // to
    // easily
    // form
    // parties
    // with
    // other
    // players.
    public static final int YOU_CAN_INSTALL_VARIOUS_RAID_OPTIONS_SUCH_AS_MONSTER_OR_PARTY_MEMBER_MARK_AND_RAID_FIX = 2872; // You
    // can
    // install
    // various
    // raid
    // options
    // such
    // as
    // monster
    // or
    // party
    // member
    // mark
    // and
    // raid
    // fix.
    public static final int THIS_ENLARGES_THE_RAIDER_S_VIEW_AND_MARKS_A_MORE_DETAILED_TOPOGRAPHY = 2873; // This
    // enlarges
    // the
    // raider's
    // view
    // and
    // marks
    // a
    // more
    // detailed
    // topography.
    public static final int THIS_DIMINISHES_THE_RAIDER_S_VIEW_AND_MARKS_A_WIDER_TOPOGRAPHY = 2874; // This
    // diminishes
    // the
    // raider's
    // view
    // and
    // marks
    // a
    // wider
    // topography.
    public static final int S1_SECONDS_TO_THE_END_OF_TERRITORY_WAR = 2900; // Ã�â€�Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº!
    public static final int YOU_CANNOT_FORCE_ATTACK_A_MEMBER_OF_THE_SAME_TERRITORY = 2901; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã‘ï¿½
    // Ã‘â€šÃ�ÂµÃ‘â€¦
    // Ã�Â¶Ã�Âµ
    // Ã�Â·Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â»Ã‘Å’,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�Â¸
    // Ã�â€™Ã‘â€¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int YOU_VE_ACQUIRED_THE_WARD_MOVE_QUICKLY_TO_YOUR_FORCES__OUTPOST = 2902; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â´Ã�Â¾Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�â€”Ã�Â½Ã�Â°Ã�Â¼Ã‘ï¿½.
    // Ã�Â¡Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�ÂµÃ�Âµ
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â³Ã�ÂµÃ‘â‚¬Ã‘Å’.
    public static final int TERRITORY_WAR_HAS_BEGUN = 2903; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â° Ã�Â·Ã�Â° Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int TERRITORY_WAR_HAS_ENDED = 2904; // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Â° Ã�Â·Ã�Â° Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int ALTITUDE_CANNOT_BE_DECREASED_ANY_FURTHER = 2905; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ�Âµ.
    public static final int ALTITUDE_CANNOT_BE_INCREASED_ANY_FURTHER = 2906; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã‘â€šÃ‘â€¹.
    public static final int YOU_HAVE_ENTERED_A_POTENTIALLY_HOSTILE_ENVIRONMENT_SO_THE_AIRSHIP_S_SPEED_HAS_BEEN_GREATLY = 2907; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â².
    // Ã�Â¡Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â·Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int AS_YOU_ARE_LEAVING_THE_HOSTILE_ENVIRONMENT_THE_AIRSHIP_S_SPEED_HAS_BEEN_RETURNED_TO_NORMAL = 2908; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â².
    // Ã�Â¡Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°.
    public static final int A_SERVITOR_OR_PET_CANNOT_BE_SUMMONED_WHILE_ON_AN_AIRSHIP = 2909; // Ã�ï¿½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘ï¿½Ã‘Å’
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã�Â¾Ã�Â¼
    // Ã�Â½Ã�Â°
    // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Âµ,
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¡Ã�Â»Ã‘Æ’Ã�Â³Ã‘Æ’.
    public static final int YOU_HAVE_ENTERED_AN_INCORRECT_COMMAND = 2910; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â°.
    public static final int YOU_VE_REQUESTED_C1_TO_BE_ON_YOUR_FRIENDS_LIST = 2911; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // $c1
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â·Ã�ÂµÃ�Â¹.
    public static final int YOU_VE_INVITED_C1_TO_JOIN_YOUR_CLAN = 2912; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â»Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’
    // $c1
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â² Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int CLAN_S1_HAS_SUCCEEDED_IN_CAPTURING_S2_S_TERRITORY_WARD = 2913; // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½
    // $s1
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³
    // Ã�Â·Ã�Â°Ã�Â²Ã�Â¾Ã�ÂµÃ�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â½Ã�Â°Ã�Â¼Ã‘ï¿½
    // $s2.
    public static final int THE_TERRITORY_WAR_WILL_BEGIN_IN_20_MINUTES_TERRITORY_RELATED_FUNCTIONS_IE__BATTLEFIELD_CHANNEL = 2914; // Ã�â€�Ã�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // 20Ã�Â¼Ã�Â¸Ã�Â½!
    // Ã�ï¿½Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THIS_CLAN_MEMBER_CANNOT_WITHDRAW_OR_BE_EXPELLED_WHILE_PARTICIPATING_IN_A_TERRITORY_WAR = 2915; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã‘Å½,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½.
    public static final int PARTICIPATING_IN_S1_TERRITORY_WAR = 2916; // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â² Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // $s1
    public static final int NOT_PARTICIPATING_IN_A_TERRITORY_WAR = 2917; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    public static final int ONLY_CHARACTERS_WHO_ARE_LEVEL_76_OR_ABOVE_WHO_HAVE_COMPLETED_THEIR_FOURTH_CLASS_TRANSFER_CAN = 2918; // Ã�â€™
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â¸,
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â³Ã‘Ë†Ã�Â¸Ã�Âµ
    // 40-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã�Âµ
    // 2-Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€žÃ�ÂµÃ‘ï¿½Ã‘ï¿½Ã�Â¸Ã‘Å½.
    public static final int WHILE_DISGUISED_YOU_CANNOT_OPERATE_A_PRIVATE_OR_MANUFACTURE_STORE = 2919; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ‘Æ’Ã‘Å½.
    public static final int NO_MORE_AIRSHIPS_CAN_BE_SUMMONED_AS_THE_MAXIMUM_AIRSHIP_LIMIT_HAS_BEEN_MET = 2920; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�ÂµÃ�Â¹
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½.
    public static final int YOU_CANNOT_BOARD_THE_AIRSHIP_BECAUSE_THE_MAXIMUM_NUMBER_FOR_OCCUPANTS_IS_MET = 2921; // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½,
    // Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â½Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘Å’.
    public static final int BLOCK_CHECKER_WILL_END_IN_5_SECONDS = 2922; // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 5Ã‘ï¿½Ã�ÂµÃ�Âº!!!!
    public static final int BLOCK_CHECKER_WILL_END_IN_4_SECONDS = 2923; // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 4Ã‘ï¿½Ã�ÂµÃ�Âº!!!!
    public static final int YOU_CANNOT_ENTER_A_SEED_WHILE_IN_A_FLYING_TRANSFORMATION_STATE = 2924; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã‘Å’
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸,
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�â€ºÃ�ÂµÃ‘â€šÃ�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¡Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾.
    public static final int BLOCK_CHECKER_WILL_END_IN_3_SECONDS = 2925; // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 3Ã‘ï¿½Ã�ÂµÃ�Âº!!!!
    public static final int BLOCK_CHECKER_WILL_END_IN_2_SECONDS = 2926; // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 2Ã‘ï¿½Ã�ÂµÃ�Âº!!!!
    public static final int BLOCK_CHECKER_WILL_END_IN_1_SECOND = 2927; // Ã�ï¿½Ã‘â‚¬Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1Ã‘ï¿½Ã�ÂµÃ�Âº!!!!
    public static final int THE_C1_TEAM_HAS_WON = 2928; // Ã�â€™ Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½Ã‘â€¦
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»Ã�Â° Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â° $c1.
    public static final int YOUR_REQUEST_CANNOT_BE_PROCESSED_BECAUSE_THERE_S_NO_ENOUGH_AVAILABLE_MEMORY_ON_YOUR_GRAPHIC_CARD = 2929; // Ã�Å¸Ã�Â°Ã�Â¼Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â¸Ã�Â´Ã�ÂµÃ�Â¾Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ‘â€¹
    // Ã�Â¼Ã�Â°Ã�ÂºÃ‘ï¿½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½Ã�Â°,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã�Âº
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // Ã�Â¡Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’.
    public static final int A_GRAPHIC_CARD_INTERNAL_ERROR_HAS_OCCURRED_PLEASE_INSTALL_THE_LATEST_VERSION_OF_THE_GRAPHIC_CARD = 2930; // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â°
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â½Ã�Â½Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â²Ã�Â¸Ã�Â´Ã�ÂµÃ�Â¾Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ‘â€¹.
    // Ã�Å¸Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã�Â½Ã‘Å½Ã‘Å½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¸Ã‘Å½
    // Ã�Â´Ã‘â‚¬Ã�Â°Ã�Â¹Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â¾Ã�Â²
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â¸Ã�Â´Ã�ÂµÃ�Â¾Ã�ÂºÃ�Â°Ã‘â‚¬Ã‘â€šÃ‘â€¹.
    public static final int THE_SYSTEM_FILE_MAY_HAVE_BEEN_DAMAGED_AFTER_ENDING_THE_GAME_PLEASE_CHECK_THE_FILE_USING_THE = 2931; // Ã�â€¢Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â´Ã�ÂµÃ�Â½
    // Ã‘ï¿½Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�ÂµÃ�Â¼Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â».
    // Ã�â€”Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â³Ã‘Æ’
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã�ÂºÃ‘Æ’
    // Ã‘â€žÃ�Â°Ã�Â¹Ã�Â»Ã�Â¾Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    public static final int S1_ADENA = 2932; // $s1 Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int THOMAS_D_TURKEY_HAS_APPEARED_PLEASE_SAVE_SANTA = 2933; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â²Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�â€˜Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹Ã�Â¹
    // Ã�ËœÃ�Â½Ã�Â´Ã‘Å½Ã�Âº.
    // Ã�Â¡Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�â€�Ã�ÂµÃ�Â´Ã�Â°
    // Ã�Å“Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â·Ã�Â°!
    public static final int YOU_HAVE_DEFEATED_THOMAS_D_TURKEY_AND_RESCUED_SANTA = 2934; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�ÂµÃ�Â´Ã�Â¸Ã�Â½Ã�Â¾Ã�Âº
    // Ã‘ï¿½
    // Ã�â€˜Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹Ã�Â¼
    // Ã�ËœÃ�Â½Ã�Â´Ã‘Å½Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€�Ã�ÂµÃ�Â´Ã�Â°
    // Ã�Å“Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â·Ã�Â°!
    public static final int YOU_FAILED_TO_RESCUE_SANTA_AND_THOMAS_D_TURKEY_HAS_DISAPPEARED = 2935; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¿Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�â€�Ã�ÂµÃ�Â´Ã�Â°
    // Ã�Å“Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â·Ã�Â°,
    // Ã�Â¸
    // Ã�â€˜Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹Ã�Â¹
    // Ã�ËœÃ�Â½Ã�Â´Ã‘Å½Ã�Âº
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·.
    public static final int THE_DISGUISE_SCROLL_CANNOT_BE_USED_BECAUSE_IT_IS_MEANT_FOR_USE_IN_A_DIFFERENT_TERRITORY = 2936; // Ã�Â¡Ã�Â²Ã�Â¸Ã‘â€šÃ�Â¾Ã�Âº
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Âº
    // Ã�â€”Ã�Â¼Ã�ÂµÃ�Â»Ã‘ï¿½Ã�Â¼,
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã‘â€¦
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€šÃ�Âµ,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int A_TERRITORY_OWNING_CLAN_MEMBER_CANNOT_USE_A_DISGUISE_SCROLL = 2937; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°,
    // Ã‘â€¡Ã�ÂµÃ�Â¹
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�ÂµÃ‘â€š
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã‘ï¿½Ã�Â¼Ã�Â¸,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int THE_DISGUISE_SCROLL_CANNOT_BE_USED_WHILE_YOU_ARE_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE = 2938; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ‘Æ’Ã‘Å½
    public static final int A_DISGUISE_CANNOT_BE_USED_WHEN_YOU_ARE_IN_A_CHAOTIC_STATE = 2939; // Ã�Â¥Ã�Â°Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½.
    public static final int YOU_CAN_INCREASE_THE_CHANCE_TO_ENCHANT_THE_ITEM_PRESS_THE_START_BUTTON_BELOW_TO_BEGIN = 2940; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã‘Ë†Ã�Â°Ã�Â½Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â½Ã�Â¾Ã�Â¿Ã�ÂºÃ‘Æ’
    // 'Ã�ï¿½Ã�Â°Ã‘â€¡Ã�Â°Ã‘â€šÃ‘Å’'.
    public static final int THE_REQUEST_CANNOT_BE_COMPLETED_BECAUSE_THE_REQUIREMENTS_ARE_NOT_MET_IN_ORDER_TO_PARTICIPATE_IN = 2941; // Ã�ï¿½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã‘â€šÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘ï¿½Ã�Â²Ã�ÂºÃ‘Æ’.
    // Ã�Â§Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â²Ã�Â½Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Â¸,
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã‘Æ’
    // Ã�Â²Ã‘ï¿½Ã�ÂµÃ‘â€¦
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã‘â€¹
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // 1.
    public static final int THE_FIRST_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_HOURS_S2_MINUTES_S3_SECONDS__IF_YOU_RESUMMON_THE = 2942; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã‘â€¡.
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // $s3
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ.)
    public static final int THE_FIRST_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_MINUTES_S2_SECONDS_IF_YOU_RESUMMON_THE_AGATHION = 2943; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // $s2
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ.)
    public static final int THE_FIRST_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_SECONDS__IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT = 2944; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ.)
    public static final int THE_SECOND_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_HOURS_S2_MINUTES_S3_SECONDS__IF_YOU_RESUMMON_THE = 2945; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã‘â€¡.
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // $s3
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // 1
    // Ã‘â€¡.
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ.)
    public static final int THE_SECOND_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_MINUTES_S2_SECONDS_IF_YOU_RESUMMON_THE_AGATHION = 2946; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // $s2
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // 1
    // Ã‘â€¡.
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ.)
    public static final int THE_SECOND_GIFT_S_REMAINING_RESUPPLY_TIME_IS_S1_SECONDS_IF_YOU_RESUMMON_THE_AGATHION_AT_THE_GIFT = 2947; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’:
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·,
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // 1
    // Ã‘â€¡.
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ.)
    public static final int THE_TERRITORY_WAR_EXCLUSIVE_DISGUISE_AND_TRANSFORMATION_CAN_BE_USED_20_MINUTES_BEFORE_THE_START = 2955; // Ã�Å“Ã�Â°Ã‘ï¿½Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â¸
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã‘â€¹Ã�Âµ
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â·Ã�Â°
    // Ã�â€”Ã�ÂµÃ�Â¼Ã�Â»Ã�Â¸,
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â·Ã�Â°
    // 20
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // Ã�Â´Ã�Â¾
    // Ã�â€˜Ã�Â¸Ã‘â€šÃ�Â²Ã‘â€¹
    // Ã�Â¸
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã�ÂµÃ�Âµ
    // Ã�Â¾Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int A_USER_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_WITNESS_THE_BATTLE = 2956; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â½Ã�Â°Ã�Â±Ã�Â»Ã‘Å½Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¼,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int CHARACTERS_WITH_A_FEBRUARY_29_CREATION_DATE_WILL_RECEIVE_A_GIFT_ON_FEBRUARY_28 = 2957; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â¸,
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Âµ
    // 29-Ã�Â³Ã�Â¾
    // Ã‘â€žÃ�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã�Â»Ã‘ï¿½,
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Âº
    // 28-Ã�Â³Ã�Â¾
    // Ã‘â€žÃ�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã�Â»Ã‘ï¿½.
    public static final int AN_AGATHION_HAS_ALREADY_BEEN_SUMMONED = 2958; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°.
    public static final int YOUR_ACCOUNT_HAS_BEEN_TEMPORARILY_RESTRICTED_BECAUSE_IT_IS_SUSPECTED_OF_BEING_USED_ABNORMALLY = 2959; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã�Â·Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¾Ã�Â¼Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Â²Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹.
    // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸Ã‘â€¦
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¹,
    // Ã�Â²Ã�Â°Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã�Â·Ã�Â°Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã�ÂµÃ�Â±-Ã‘ï¿½Ã�Â°Ã�Â¹Ã‘â€š
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã‘â€¹
    // Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â¸Ã�Â´Ã�ÂµÃ�Â½Ã‘â€šÃ�Â¸Ã‘â€žÃ�Â¸Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸.
    // Ã�â€˜Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â‚¬Ã�Â¾Ã�Â±Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘Å½
    // Ã�Â²Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’,
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â¶Ã�Â±Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�ÂºÃ�Â¸.
    public static final int THE_ITEM_S1_IS_REQUIRED = 2960; // Ã�â€™Ã�Â°Ã�Â¼ Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š $s1%
    public static final int _2_UNITS_OF_THE_ITEM_S1_IS_REQUIRED = 2961; // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s1%
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ
    // $2
    // Ã‘Ë†Ã‘â€š.
    public static final int THE_ITEM_HAS_BEEN_SUCCESSFULLY_PURCHASED = 6001; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â½.
    public static final int THE_ITEM_HAS_FAILED_TO_BE_PURCHASED = 6002; // Ã�Å¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int THE_ITEM_YOU_SELECTED_CANNOT_BE_PURCHASED_UNFORTUNATELY_THE_SALE_PERIOD_ENDED = 6003; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int ENCHANT_FAILED_THE_ENCHANT_SKILL_FOR_THE_CORRESPONDING_ITEM_WILL_BE_EXACTLY_RETAINED = 6004; // Ã�Â£Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int GAME_POINTS_ARE_NOT_ENOUGH = 6005; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š Ã�Â¼Ã�Â¾Ã�Â½Ã�ÂµÃ‘â€š
    // 4Game.
    public static final int THE_ITEM_CANNOT_BE_RECEIVED_BECAUSE_THE_INVENTORY_WEIGHT_QUANTITY_LIMIT_HAS_BEEN_EXCEEDED = 6006; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½.
    public static final int PRODUCT_PURCHASE_ERROR___THE_USER_STATE_IS_NOT_RIGHT = 6007; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int PRODUCT_PURCHASE_ERROR___THE_PRODUCT_IS_NOT_RIGHT = 6008; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int PRODUCT_PURCHASE_ERROR___THE_ITEM_WITHIN_THE_PRODUCT_IS_NOT_RIGHT = 6009; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸
    // Ã‘Æ’Ã�Â¿Ã�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â².
    public static final int THE_MASTER_ACCOUNT_OF_YOUR_ACCOUNT_HAS_BEEN_RESTRICTED = 6010; // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬-Ã�Â°Ã�ÂºÃ�ÂºÃ�Â°Ã‘Æ’Ã�Â½Ã‘â€š
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½.
    public static final int YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG = 6501; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ,
    // Ã‘â€šÃ�Â°Ã�Âº
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�Â¾Ã‘â€šÃ‘ï¿½Ã‘Æ’Ã‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¤Ã�Â»Ã�Â°Ã�Â³.
    public static final int MY_TELEPORT_FLAG__S1 = 6502; // Ã�Â¤Ã�Â»Ã�Â°Ã�Â³: $s1 Ã�ÂµÃ�Â´.
    public static final int THE_EVIL_THOMAS_D_TURKEY_HAS_APPEARED_PLEASE_SAVE_SANTA = 6503; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã�Â²Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�â€˜Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã‘â€¹Ã�Â¹
    // Ã�ËœÃ�Â½Ã�Â´Ã‘Å½Ã�Âº.
    // Ã�Â¡Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¡Ã�Â°Ã�Â½Ã‘â€šÃ�Â°
    // Ã�Å¡Ã�Â»Ã�Â°Ã‘Æ’Ã‘ï¿½Ã�Â°.
    public static final int YOU_WON_THE_BATTLE_AGAINST_THOMAS_D_TURKEY_SANTA_HAS_BEEN_RESCUED = 6504; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�â€˜Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�ËœÃ�Â½Ã�Â´Ã‘Å½Ã�ÂºÃ�Â°
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¡Ã�Â°Ã�Â½Ã‘â€šÃ�Â°
    // Ã�Å¡Ã�Â»Ã�Â°Ã‘Æ’Ã‘ï¿½Ã�Â°.
    public static final int YOU_DID_NOT_RESCUE_SANTA_AND_THOMAS_D_TURKEY_HAS_DISAPPEARED = 6505; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â¿Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�Â¡Ã�Â°Ã�Â½Ã‘â€šÃ�Â°
    // Ã�Å¡Ã�Â»Ã�Â°Ã‘Æ’Ã‘ï¿½Ã�Â°,
    // Ã�Â¸
    // Ã�ËœÃ�Â½Ã�Â´Ã‘Å½Ã�Âº
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·.
    public static final int ALTHOUGH_YOU_CAN_T_BE_CERTAIN_THE_AIR_SEEMS_LADEN_WITH_THE_SCENT_OF_FRESHLY_BAKED_BREAD = 6506; // Ã�Å¾Ã‘â€šÃ�ÂºÃ‘Æ’Ã�Â´Ã�Â°-Ã‘â€šÃ�Â¾
    // Ã�Â¸Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â°Ã‘â€¦
    // Ã‘â€¦Ã�Â»Ã�ÂµÃ�Â±Ã�Â°.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´
    // Ã�Â³Ã�Â»Ã�Â°Ã�Â·Ã�Â°Ã�Â¼Ã�Â¸
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¿Ã�Â»Ã‘â€¹Ã�Â²Ã�ÂµÃ‘â€š.
    public static final int YOU_FEEL_REFRESHED_EVERYTHING_APPEARS_CLEAR = 6507; // Ã�ï¿½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    // Ã�Â¡Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Âµ
    // Ã�Â²Ã�Â¸Ã�Â´Ã�Â½Ã�Â¾.

    // Recommedations
    public static final int YOU_CANNOT_RECOMMEND_YOURSELF = 829;
    public static final int YOU_HAVE_BEEN_RECOMMENDED_BY_C1 = 831;
    public static final int YOU_HAVE_RECOMMENDED_C1_YOU_HAVE_S2_RECOMMENDATIONS_LEFT = 830;
    public static final int THAT_CHARACTER_HAS_ALREADY_BEEN_RECOMMENDED = 832;
    public static final int YOU_ARE_NOT_AUTHORIZED_TO_MAKE_FURTHER_RECOMMENDATIONS_AT_THIS_TIME_YOU_WILL_RECEIVE_MORE_RECOMMENDATION_CREDITS_EACH_DAY_AT_1_PM = 833; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã‘â‚¬Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã�Â´Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘â‚¬Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã�Â´Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¹
    // Ã�Â¾Ã�Â±Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�ÂºÃ�Â°Ã�Â¶Ã�Â´Ã‘â€¹Ã�Â¹
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â²
    // Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â½Ã‘ï¿½.
    public static final int ONLY_CHARACTERS_OF_LEVEL_10_OR_ABOVE_ARE_AUTHORIZED_TO_MAKE_RECOMMENDATIONS = 898; // Ã�â€™Ã‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â´Ã�Â°Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â‚¬Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã�Â´Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¸
    // 10-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½.
    public static final int YOUR_SELECTED_TARGET_CAN_NO_LONGER_RECEIVE_A_RECOMMENDATION = 1188; // Ã�Â¦Ã�ÂµÃ�Â»Ã‘Å’
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã‘â‚¬Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã�Â´Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸.

    // Duelling
    public static final int THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL = 1926; // Ã�ï¿½Ã�Â¸
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½
    // Ã�Â¾Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â½Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã�Â»
    // Ã�Â²Ã�Â°Ã‘Ë†
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int S1_HAS_BEEN_CHALLENGED_TO_A_DUEL = 1927; // $c1
    // Ã�Â²Ã‘â€¹Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int S1S_PARTY_HAS_BEEN_CHALLENGED_TO_A_DUEL = 1928; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = 1929; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int YOU_HAVE_ACCEPTED_S1S_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = 1930; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼
    // $c1.
    // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int S1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL = 1931; // $c1
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã�Â¼Ã�Â¸.
    public static final int YOU_HAVE_ACCEPTED_S1S_CHALLENGE_TO_A_PARTY_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = 1933; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¼
    // $c1.
    // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_DUEL_AGAINST_THEIR_PARTY_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = 1934; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL = 1936; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â»Ã�Â°
    // Ã�â€™Ã�Â°Ã‘Ë†
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int SINCE_THE_PERSON_YOU_CHALLENGED_IS_NOT_CURRENTLY_IN_A_PARTY_THEY_CANNOT_DUEL_AGAINST_YOUR_PARTY = 1937; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶,
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¸Ã‘â€š
    // Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Âµ
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã‘â‚¬Ã�Â°Ã�Â¶Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¹
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int S1_HAS_CHALLENGED_YOU_TO_A_DUEL = 1938; // $c1
    // Ã�Â²Ã‘â€¹Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�â€™Ã�Â°Ã‘ï¿½ Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int S1S_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL = 1939; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â°
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â²Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME = 1940; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int THE_OPPOSING_PARTY_IS_CURRENTLY_UNABLE_TO_ACCEPT_A_CHALLENGE_TO_A_DUEL = 1942; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã�Â°Ã‘Ë†
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int IN_A_MOMENT_YOU_WILL_BE_TRANSPORTED_TO_THE_SITE_WHERE_THE_DUEL_WILL_TAKE_PLACE = 1944; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â²Ã‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â½Ã�Â°
    // Ã�Â¼Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â¾Ã�Â¹Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    public static final int THE_DUEL_WILL_BEGIN_IN_S1_SECONDS = 1945; // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // $s1
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int LET_THE_DUEL_BEGIN = 1949; // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’ Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’!
    public static final int S1_HAS_WON_THE_DUEL = 1950; // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’ Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸ -
    // $c1.
    public static final int S1S_PARTY_HAS_WON_THE_DUEL = 1951; // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸ - Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1.
    public static final int THE_DUEL_HAS_ENDED_IN_A_TIE = 1952; // Ã�â€�Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’ Ã�Â²
    // Ã�Â½Ã�Â¸Ã‘â€¡Ã‘Å’Ã‘Å½.
    public static final int SINCE_S1_WITHDREW_FROM_THE_DUEL_S2_HAS_WON = 1955; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $c1
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // -
    // $s2.
    public static final int SINCE_S1S_PARTY_WITHDREW_FROM_THE_DUEL_S1S_PARTY_HAS_WON = 1956; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $c1
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’.
    // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // -
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // $s2.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE = 2017; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_CURRENTLY_FISHING = 2018; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã‘â€¡Ã�Â¸Ã‘â€š.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1S_HP_OR_MP_IS_BELOW_50_PERCENT = 2019; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // HP
    // Ã�Â¸Ã�Â»Ã�Â¸
    // MP
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�Âµ
    // 50%.
    public static final int S1_CANNOT_MAKE_A_CHALLANGE_TO_A_DUEL_BECAUSE_S1_IS_CURRENTLY_IN_A_DUEL_PROHIBITED_AREA = 2020; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ,
    // Ã�Â³Ã�Â´Ã�Âµ
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�ÂµÃ‘â€°Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_CURRENTLY_ENGAGED_IN_BATTLE = 2021; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â±Ã�Â¾Ã‘Å½.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_ALREADY_ENGAGED_IN_A_DUEL = 2022; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_IN_A_CHAOTIC_STATE = 2023; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¥Ã�Â°Ã�Â¾Ã‘ï¿½Ã�Â°.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_PARTICIPATING_IN_THE_OLYMPIAD = 2024; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_PARTICIPATING_IN_A_CLAN_HALL_WAR = 2025; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_PARTICIPATING_IN_A_SIEGE_WAR = 2026; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ.
    public static final int S1_CANNOT_DUEL_BECAUSE_S1_IS_CURRENTLY_RIDING_A_BOAT_WYVERN_OR_STRIDER = 2027; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int S1_CANNOT_RECEIVE_A_DUEL_CHALLENGE_BECAUSE_S1_IS_TOO_FAR_AWAY = 2028; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â¾Ã�Â²
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’,
    // Ã�Â¿Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�ÂºÃ�Â¾.
    public static final int YOU_CANNOT_ADD_ELEMENTAL_POWER_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP = 2143; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¹,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int PLEASE_SELECT_ITEM_TO_ADD_ELEMENTAL_POWER = 2144; // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�Âº
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â²Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¹.
    public static final int ELEMENTAL_POWER_ENCHANCER_USAGE_HAS_BEEN_CANCELLED = 2145; // Ã�ËœÃ‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¹
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int ELEMENTAL_POWER_ENCHANCER_USAGE_REQUIREMENT_IS_NOT_SUFFICIENT = 2146; // Ã�Â£Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã‘Æ’Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�Â»Ã‘Å½Ã�Â´Ã�ÂµÃ�Â½Ã‘â€¹.
    public static final int S2_ELEMENTAL_POWER_HAS_BEEN_ADDED_SUCCESSFULLY_TO_S1 = 2147; // $s1:
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¸
    // $s2
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int S3_ELEMENTAL_POWER_HAS_BEEN_ADDED_SUCCESSFULLY_TO__S1S2 = 2148; // +$s1
    // $s2:
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¸
    // $s3
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_HAVE_FAILED_TO_ADD_ELEMENTAL_POWER = 2149; // Ã�â€�Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘â€¹
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¹
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int ANOTHER_ELEMENTAL_POWER_HAS_ALREADY_BEEN_ADDED_THIS_ELEMENTAL_POWER_CANNOT_BE_ADDED = 2150; // Ã�â€�Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¸
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°.
    // Ã�Â­Ã‘â€šÃ�Â°
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¦Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°.
    public static final int A_PARTY_CANNOT_BE_FORMED_IN_THIS_AREA = 2388; // Ã�â€”Ã�Â´Ã�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘Æ’.

    public static final int A_MALICIOUS_SKILL_CANNOT_BE_USED_IN_A_PEACE_ZONE = 2167; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾
    // Ã�Â²
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_POLYMORPHED = 2174; // $c1
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¸,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ‘Æ’
    // Ã�Â¾Ã�Â½
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»
    // Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã‘Æ’.
    public static final int PARTY_DUEL_CANNOT_BE_INITIATED_DUEL_TO_A_POLYMORPHED_PARTY_MEMBER = 2175; // Ã�â€œÃ‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã‘Æ’Ã‘ï¿½Ã�Â»Ã‘Å’
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â¸Ã�Â·-Ã�Â·Ã�Â°
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã‘â€¹
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã�Â·
    // Ã‘â€¡Ã�Â»Ã�ÂµÃ�Â½Ã�Â¾Ã�Â²
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹.
    public static final int HALF_KILL = 2336; // Ã�â€™Ã�Â°Ã‘Ë† Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â³ Ã�Â½Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã�Â½Ã‘Æ’ Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²!
    public static final int CP_DISAPPEARS_WHEN_HIT_WITH_A_HALF_KILL_SKILL = 2337; // CP
    // Ã‘Æ’Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã‘ï¿½Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�ÂµÃ�Â¼,
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â½Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã�Â½Ã‘Æ’.

    public static final int NOT_ENOUGH_BOLTS = 2226; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾ Ã�Â±Ã�Â¾Ã�Â»Ã‘â€šÃ�Â¾Ã�Â².

    // ClanHall Auction messages
    public static final int ONLY_A_CLAN_LEADER_WHOSE_CLAN_IS_OF_LEVEL_2_OR_HIGHER_IS_ALLOWED_TO_PARTICIPATE_IN_A_CLAN_HALL_AUCTION = 673; // Ã�Â£Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â²
    // Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’Ã‘â€š
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã‘â€¹
    // 2-Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â½Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ.
    public static final int IT_HAS_NOT_YET_BEEN_SEVEN_DAYS_SINCE_CANCELING_AN_AUCTION = 674; // Ã�ï¿½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // 7
    // Ã�Â´Ã�Â½Ã�ÂµÃ�Â¹
    // Ã‘ï¿½Ã�Â¾
    // Ã�Â´Ã�Â½Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Â°.
    public static final int THERE_ARE_NO_CLAN_HALLS_UP_FOR_AUCTION = 675; // Ã�â€�Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int SINCE_YOU_HAVE_ALREADY_SUBMITTED_A_BID_YOU_ARE_NOT_ALLOWED_TO_PARTICIPATE_IN_ANOTHER_AUCTION_AT_THIS_TIME = 676; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â²Ã�Â½Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Âµ
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Â¼
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOUR_BID_PRICE_MUST_BE_HIGHER_THAN_THE_MINIMUM_PRICE_THAT_CAN_BE_BID = 677; // Ã�Â¡Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¼Ã�Â¸Ã�Â½Ã�Â¸Ã�Â¼Ã�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¼Ã�ÂµÃ‘â‚¬
    // Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ�ÂµÃ�Â¶Ã�Â°.
    public static final int YOU_HAVE_SUBMITTED_A_BID_IN_THE_AUCTION_OF_S1 = 678; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â²
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // $s1.
    public static final int YOU_HAVE_CANCELED_YOUR_BID = 679; // Ã�Â¡Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_CANNOT_PARTICIPATE_IN_AN_AUCTION = 680;
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â²
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int THE_CLAN_DOES_NOT_OWN_A_CLAN_HALL = 681; // Ã�â€™Ã�Â°Ã‘Ë† Ã�ÂºÃ�Â»Ã�Â°Ã�Â½
    // Ã�Â½Ã�Âµ
    // Ã�Â²Ã�Â»Ã�Â°Ã�Â´Ã�ÂµÃ�ÂµÃ‘â€š
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â¼.
    public static final int THE_CLAN_HALL_WHICH_WAS_PUT_UP_FOR_AUCTION_HAS_BEEN_AWARDED_TO_S1_CLAN = 776; // Ã�Â¥Ã�Â¾Ã�Â»Ã�Â»,
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ,
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã‘ï¿½Ã‘â€šÃ�Â²Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°
    // $s1.
    public static final int THE_CLAN_HALL_WHICH_HAD_BEEN_PUT_UP_FOR_AUCTION_WAS_NOT_SOLD_AND_THEREFORE_HAS_BEEN_RELISTED = 777; // Ã�Â¥Ã�Â¾Ã�Â»Ã�Â»,
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â½Ã�Â°
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ,
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â½
    // Ã�Â¸
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â²,
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã‘Æ’.
    public static final int YOU_HAVE_REGISTERED_FOR_A_CLAN_HALL_AUCTION = 1004; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â±Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�Â¾Ã�Âº
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â½Ã�Â¸Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â²
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    public static final int THERE_IS_NOT_ENOUGH_ADENA_IN_THE_CLAN_HALL_WAREHOUSE = 1005; // Ã�ï¿½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½
    // Ã�Â²
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã�Â»Ã�Â¸Ã‘â€°Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int YOU_HAVE_BID_IN_A_CLAN_HALL_AUCTION = 1006; // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â²
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»Ã�Â¾Ã�Â²
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    public static final int THE_SECOND_BID_AMOUNT_MUST_BE_HIGHER_THAN_THE_ORIGINAL = 1075; // Ã�Â¡Ã‘Æ’Ã�Â¼Ã�Â¼Ã�Â°
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ‘Æ’.
    public static final int IT_IS_NOT_AN_AUCTION_PERIOD = 2075; // Ã�ï¿½Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½ Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    public static final int YOUR_BID_CANNOT_EXCEED_100_BILLION = 2076; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // 2,1
    // Ã�Â¼Ã�Â¸Ã�Â»Ã�Â»Ã�Â¸Ã�Â°Ã‘â‚¬Ã�Â´Ã�Â°.
    public static final int YOUR_BID_MUST_BE_HIGHER_THAN_THE_CURRENT_HIGHEST_BID = 2077; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â°
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // Ã‘â€šÃ�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int YOU_DO_NOT_HAVE_ENOUGH_ADENA_FOR_THIS_BID = 2078; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int YOU_CURRENTLY_HAVE_THE_HIGHEST_BID = 2079; // Ã�Â£ Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã�Â°Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¾Ã�ÂºÃ�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°.
    public static final int YOU_HAVE_BEEN_OUTBID = 2080; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Â° Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â° Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â±Ã�Â¸Ã‘â€šÃ�Â°.
    public static final int THE_AUCTION_HAS_BEGUN = 2083; // Ã�ï¿½Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½ Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã‘ï¿½Ã‘ï¿½.
    public static final int BIDDER_EXISTS__THE_AUCTION_TIME_HAS_BEEN_EXTENDED_BY_5_MINUTES = 2159; // Ã�ï¿½Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°!
    // Ã�ï¿½Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // 5
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€š.
    public static final int BIDDER_EXISTS__AUCTION_TIME_HAS_BEEN_EXTENDED_BY_3_MINUTES = 2160; // Ã�ï¿½Ã�Â¾Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â°!
    // Ã�ï¿½Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â½Ã�Â°
    // 3
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â€¹.
    public static final int TRADE_S1_S2_S_AUCTION_HAS_ENDED = 2172; // +$s1 $s2:
    // Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½.
    public static final int S1_S_AUCTION_HAS_ENDED = 2173; // $s1: Ã�Â°Ã‘Æ’Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¾Ã�Â½
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�ÂµÃ�Â½.

    // Combat messages
    public static final int C1S_IS_PERFORMING_A_COUNTERATTACK = 1997; // $c1
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ‘Æ’.
    public static final int YOU_COUNTERED_C1S_ATTACK = 1998; // Ã�â€™Ã‘â€¹
    // Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’ $c1.
    public static final int C1_DODGES_THE_ATTACK = 1999; // $c1 Ã‘Æ’Ã�Â²Ã�Â¾Ã‘â‚¬Ã�Â°Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½ Ã�Â¾Ã‘â€š
    // Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸.
    public static final int C1_HAS_GIVEN_C2_DAMAGE_OF_S3 = 2261; // $c1 Ã�Â½Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸ $c2 $s3
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°.
    public static final int C1_HAS_RECEIVED_DAMAGE_OF_S3_FROM_C2 = 2262; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã‘â€š
    // Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸
    // $c2
    // $s3
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°.
    public static final int C1_HAS_RECEIVED_DAMAGE_OF_S3_THROUGH_C2 = 2263; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š
    // $s3
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°
    // ($c2).
    public static final int C1_HAS_EVADED_C2S_ATTACK = 2264; // $c1 Ã‘Æ’Ã�ÂºÃ�Â»Ã�Â¾Ã�Â½Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€š Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¸ Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸
    // $c2.
    public static final int C1S_ATTACK_WENT_ASTRAY = 2265; // $c1 Ã�Â½Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬
    // Ã�Â¼Ã�Â¸Ã�Â¼Ã�Â¾ Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸.
    public static final int C1_HAD_A_CRITICAL_HIT = 2266; // $c1 Ã�Â½Ã�Â°Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€š
    // Ã�ÂºÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¸Ã�Â¹ Ã‘Æ’Ã�Â´Ã�Â°Ã‘â‚¬!
    public static final int C1_RESISTED_C2S_DRAIN = 2267; // $c1 Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â¶Ã�Â°Ã�ÂµÃ‘â€š
    // Ã�Å¸Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸
    // $c2.
    public static final int C1S_ATTACK_FAILED = 2268; // $c1: Ã�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â° Ã�Â½Ã�Âµ Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    public static final int C1_RESISTED_C2S_MAGIC = 2269; // $c1 Ã�Â¾Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â¶Ã�Â°Ã�ÂµÃ‘â€š Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã‘Å½
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â³Ã�Â° $c2.
    public static final int C1_HAS_RECEIVED_DAMAGE_FROM_S2_THROUGH_THE_FIRE_OF_MAGIC = 2270; // $c1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½
    // Ã�Â¾Ã‘â€š
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã�Â¸
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â³Ã�Â°
    // $s2.
    public static final int C1_WEAKLY_RESISTED_C2S_MAGIC = 2271; // $c1 Ã‘ï¿½Ã�Â»Ã�Â°Ã�Â±Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã�Â¸ Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â³Ã�Â°
    // $c2.
    public static final int DAMAGE_IS_DECREASED_BECAUSE_C1_RESISTED_AGAINST_C2S_MAGIC = 2280; // $c1
    // Ã‘ï¿½Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¼Ã�Â°Ã�Â³Ã�Â¸Ã�Â¸
    // Ã�Â²Ã‘â‚¬Ã�Â°Ã�Â³Ã�Â°
    // $c2,
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â½
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�ÂµÃ�Â½.

    public static final int THERE_ARE_S2_SECONDS_REMAINING_IN_S1S_REUSE_TIME = 2303; // $s1:
    // Ã�Â´Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // $s2
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THERE_ARE_S2_MINUTES_S3_SECONDS_REMAINING_IN_S1S_REUSE_TIME = 2304; // $s1:
    // Ã�Â´Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // $s2
    // Ã�Â¼Ã�Â¸Ã�Â½
    // $s3
    // Ã‘ï¿½Ã�ÂµÃ�Âº.
    public static final int THERE_ARE_S2_HOURS_S3_MINUTES_AND_S4_SECONDS_REMAINING_IN_S1S_REUSE_TIME = 2305; // $s1:
    // Ã�Â´Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // $s2
    // Ã‘â€¡
    // $s3
    // Ã�Â¼Ã�Â¸Ã�Â½
    // $s4
    // Ã‘ï¿½Ã�ÂµÃ�Âº.

    // Augmentation
    public static final int SELECT_THE_ITEM_TO_BE_AUGMENTED = 1957; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int SELECT_THE_CATALYST_FOR_AUGMENTATION = 1958; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°Ã�Â»Ã�Â¸Ã�Â·Ã�Â°Ã‘â€šÃ�Â¾Ã‘â‚¬
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int REQUIRES_S1_S2 = 1959; // Ã�Â¢Ã‘â‚¬Ã�ÂµÃ�Â±Ã‘Æ’Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½: $s2 $s1.
    public static final int THIS_IS_NOT_A_SUITABLE_ITEM = 1960; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int GEMSTONE_QUANTITY_IS_INCORRECT = 1961; // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â°Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â‚¬Ã‘â€¹
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã�Â¾Ã‘â€ Ã�Â²Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€š.
    public static final int THE_ITEM_WAS_SUCCESSFULLY_AUGMENTED = 1962; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½!
    public static final int SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION = 1963; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã‘ï¿½
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ.
    public static final int AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM = 1964; // Ã�Â¡Ã�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°.
    public static final int AUGMENTATION_HAS_BEEN_SUCCESSFULLY_REMOVED_FROM_YOUR_S1 = 1965; // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // $s1.
    public static final int ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN = 1970; // Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.

    public static final int YOU_CANNOT_AUGMENT_ITEMS_WHILE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_IS_IN_OPERATION = 1972; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�ÂµÃ�Â¶Ã�Â¸Ã�Â¼
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int YOU_CANNOT_AUGMENT_ITEMS_WHILE_DEAD = 1974;
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â²Ã‘â€¹Ã�Â¼.
    public static final int YOU_CANNOT_AUGMENT_ITEMS_WHILE_PARALYZED = 1976; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€¡Ã�Â¸
    // Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¸Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¼.
    public static final int YOU_CANNOT_AUGMENT_ITEMS_WHILE_FISHING = 1977; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã�Â²Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã�Â°Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int YOU_CANNOT_AUGMENT_ITEMS_WHILE_SITTING_DOWN = 1978; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹
    // Ã‘ï¿½Ã�Â¸Ã�Â´Ã‘ï¿½.

    public static final int PRESS_THE_AUGMENT_BUTTON_TO_BEGIN = 1984; // Ã�â€�Ã�Â»Ã‘ï¿½
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â½Ã�Â°Ã�Â¶Ã�Â¼Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â½Ã�Â¾Ã�Â¿Ã�ÂºÃ‘Æ’
    // "Ã�â€”Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’".
    public static final int AUGMENTATION_FAILED_DUE_TO_INAPPROPRIATE_CONDITIONS = 2001; // Ã�Â£Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â±Ã�Â»Ã‘Å½Ã�Â´Ã�ÂµÃ�Â½Ã‘â€¹,
    // Ã�Â·Ã�Â°Ã‘â€¡Ã�Â°Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.

    // Shadow items
    public static final int S1S_REMAINING_MANA_IS_NOW_10 = 1979; // $s1:
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’ 10
    // Ã�Â¼Ã�Â°Ã�Â½Ã‘â€¹.
    public static final int S1S_REMAINING_MANA_IS_NOW_5 = 1980; // $s1: Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // 5 Ã�Â¼Ã�Â°Ã�Â½Ã‘â€¹.
    public static final int S1S_REMAINING_MANA_IS_NOW_1_IT_WILL_DISAPPEAR_SOON = 1981; // $s1:
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’
    // 1
    // Ã�Â¼Ã�Â°Ã�Â½Ã�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·Ã�Â½Ã�ÂµÃ‘â€š.
    public static final int S1S_REMAINING_MANA_IS_NOW_0_AND_THE_ITEM_HAS_DISAPPEARED = 1982; // $s1:
    // Ã�Â¼Ã�Â°Ã�Â½Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’.
    // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã‘â€¡Ã�ÂµÃ�Â·.

    // Limited-items
    public static final int THE_LIMITED_TIME_ITEM_HAS_BEEN_DELETED = 2366; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã‘ï¿½
    // Ã�Â¾Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¼
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â¼
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.

    // Ã�Â¢Ã‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    public static final int YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN = 2058; // Ã�â€™Ã‘â€¹
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.
    public static final int THE_NEARBLY_AREA_IS_TOO_NARROW_FOR_YOU_TO_POLYMORPH_PLEASE_MOVE_TO_ANOTHER_AREA_AND_TRY_TO_POLYMORPH_AGAIN = 2059; // Ã�Å¾Ã�ÂºÃ‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â°Ã‘Å½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã‘ï¿½Ã�Â»Ã�Â¸Ã‘Ë†Ã�ÂºÃ�Â¾Ã�Â¼
    // Ã‘â€šÃ�ÂµÃ‘ï¿½Ã�Â½Ã�Â¾
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â²Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â¹Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã‘Æ’Ã‘Å½
    // Ã�Â·Ã�Â¾Ã�Â½Ã‘Æ’
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â±Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�Â½Ã�Â¾Ã�Â²Ã�Â°.
    public static final int YOU_CANNOT_POLYMORPH_INTO_THE_DESIRED_FORM_IN_WATER = 2060; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã�Â´Ã�Âµ.
    public static final int YOU_ARE_STILL_UNDER_TRANSFORM_PENALTY_AND_CANNOT_BE_POLYMORPHED = 2061; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã‘Ë†Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€žÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã‘Æ’.
    public static final int YOU_CANNOT_POLYMORPH_WHEN_YOU_HAVE_SUMMONED_A_SERVITOR_PET = 2062; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â½
    // Ã‘ï¿½Ã�Â»Ã‘Æ’Ã�Â³Ã�Â°/Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã�ÂµÃ‘â€ .
    public static final int YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_PET = 2063; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã�Â¾Ã�Â¼
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Âµ.
    public static final int YOU_CANNOT_POLYMORPH_WHILE_UNDER_THE_EFFECT_OF_A_SPECIAL_SKILL = 2064; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½,
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘ï¿½Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â´
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�ÂµÃ�Â¼
    // Ã�Â¾Ã‘ï¿½Ã�Â¾Ã�Â±Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_BOAT = 2182; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€°Ã�Â°Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�Âµ.
    public static final int YOU_CANNOT_BOARD_A_SHIP_WHILE_YOU_ARE_POLYMORPHED = 2213; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â²Ã�Â·Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸
    // Ã�Â½Ã�Â°
    // Ã�Â±Ã�Â¾Ã‘â‚¬Ã‘â€š
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¼.
    public static final int CURRENT_POLYMORPH_FORM_CANNOT_BE_APPLIED_WITH_CORRESPONDING_EFFECTS = 2194; // Ã�Å¸Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â¼Ã�Â¸
    // Ã‘ï¿½Ã‘â€žÃ‘â€žÃ�ÂµÃ�ÂºÃ‘â€šÃ�Â°Ã�Â¼Ã�Â¸.
    public static final int SHOUT_AND_TRADE_CHATING_CANNOT_BE_USED_SHILE_POSSESSING_A_CURSED_WEAPON = 2085; // Ã�Å¡Ã‘â‚¬Ã�Â¸Ã�Âº
    // Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â»Ã‘ï¿½
    // Ã�Â²
    // Ã‘â€¡Ã�Â°Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã‘â€¹,
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ�Â°
    // Ã‘Æ’
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�ÂºÃ�Â»Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Âµ
    // Ã�Â¾Ã‘â‚¬Ã‘Æ’Ã�Â¶Ã�Â¸Ã�Âµ.
    public static final int YOU_CANNOT_TRANSFORM_WHILE_SITTING = 2283;
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’Ã‘ï¿½Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¸Ã�Â´Ã‘ï¿½.

    public static final int THERE_IS_NOT_ENOUGH_SPACE_TO_MOVE_THE_SKILL_CANNOT_BE_USED = 2161; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�Â±Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°,
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â¾.

    // Ã�ï¿½Ã�Â±Ã‘ï¿½Ã�Â¾Ã‘â‚¬Ã�Â±Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½ Ã�Â´Ã‘Æ’Ã‘Ë†
    public static final int YOUR_SOUL_HAS_INCREASED_BY_S1_SO_IT_IS_NOW_AT_S2 = 2162; // Ã�â€™Ã�Â°Ã‘Ë†Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â´Ã‘Æ’Ã‘Ë†
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â½Ã�Â°
    // $s1
    // Ã�Â¸
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ‘â€š
    // $s2.
    public static final int SOUL_CANNOT_BE_INCREASED_ANY_MORE = 2163; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â´Ã‘Æ’Ã‘Ë†
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â±Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€¹Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int SOUL_CANNOT_BE_ABSORBED_ANY_MORE = 2186; // Ã�â€˜Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â´Ã‘Æ’Ã‘Ë†
    // Ã�Â¿Ã�Â¾Ã�Â³Ã�Â»Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int THERE_IS_NOT_ENOUGHT_SOUL = 2195; // Ã�ï¿½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â´Ã‘Æ’Ã‘Ë†.

    public static final int AGATHION_SKILLS_CAN_BE_USED_ONLY_WHEN_AGATHION_IS_SUMMONED = 2292; // Ã�Â£Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Âµ
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾,
    // Ã�ÂºÃ�Â°Ã�Âº
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã�Â½.

    public static final int YOU_HAVE_GAINED_VITALITY_POINTS = 2296; // Ã�Â¢Ã‘â‚¬Ã�ÂµÃ�Â±Ã‘Æ’Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â¸Ã�Â¸.
    public static final int YOUR_VITALITY_IS_AT_MAXIMUM = 2314; // Ã�Â­Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â°.
    public static final int VITALITY_HAS_INCREASED = 2315; // Ã�Â­Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â¸Ã‘ï¿½ Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int VITALITY_HAS_DECREASED = 2316; // Ã�Â­Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â¸Ã‘ï¿½ Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int VITALITY_IS_FULLY_EXHAUSTED = 2317; // Ã�â€™Ã‘ï¿½Ã‘ï¿½ Ã‘ï¿½Ã�Â½Ã�ÂµÃ‘â‚¬Ã�Â³Ã�Â¸Ã‘ï¿½
    // Ã�Â¸Ã�Â·Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â°.

    public static final int ACQUIRED_50_CLAN_FAME_POINTS = 2326; // Ã�â€™Ã‘â€¹ Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸

    public static final int NOT_ENOUGH_FAME_POINTS = 2327; // Ã�Â£ Ã�Â²Ã�Â°Ã‘ï¿½ Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã‘â€šÃ�Â¾Ã‘â€¡Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â² Ã‘â‚¬Ã�ÂµÃ�Â¿Ã‘Æ’Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    public static final int YOU_HAVE_ACQUIRED_S1_REPUTATION_SCORE = 2319; // Ã�â€™Ã‘â€¹

    public static final int C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED = 2096; // $c1

    public static final int C1S_LEVEL_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED = 2097; // $c1

    public static final int C1S_QUEST_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED = 2098; // $c1

    public static final int C1S_ITEM_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED = 2099; // $c1
    public static final int C1_MAY_NOT_RE_ENTER_YET = 2100; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½ Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â° Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â° $c1 Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã�Â½Ã�Âµ Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘Ë†Ã�Â»Ã�Â¾, $c1 Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š Ã�Â²Ã�Â¾Ã�Â¹Ã‘â€šÃ�Â¸.
    public static final int YOU_ARE_NOT_CURRENTLY_IN_A_PARTY_SO_YOU_CANNOT_ENTER = 2101; // Ã�â€™Ã‘â€¹
    public static final int YOU_CANNOT_ENTER_DUE_TO_THE_PARTY_HAVING_EXCEEDED_THE_LIMIT = 2102; // Ã�â€™Ã‘â€¹
    public static final int YOU_CANNOT_ENTER_BECAUSE_YOU_ARE_NOT_IN_A_CURRENT_COMMAND_CHANNEL = 2103; // Ã�â€™Ã‘â€¹
    public static final int YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON = 2105; // Ã�â€™Ã‘â€¹
    public static final int THIS_DUNGEON_WILL_EXPIRE_IN_S1_MINUTES = 2106; 
    public static final int THIS_INSTANCE_ZONE_WILL_BE_TERMINATED_IN_S1_MINUTES_YOU_WILL_BE_FORCED_OUT_OF_THE_DANGEON_THEN_TIME_EXPIRES = 2107; 
    public static final int ONLY_A_PARTY_LEADER_CAN_TRY_TO_ENTER = 2185;
    public static final int ITS_TOO_FAR_FROM_THE_NPC_TO_WORK = 2193; 
    public static final int INSTANCE_ZONE_TIME_LIMIT = 2228;
    public static final int THERE_IS_NO_INSTANCE_ZONE_UNDER_A_TIME_LIMIT = 2229; 
    public static final int S1_WILL_BE_AVAILABLE_FOR_RE_USE_AFTER_S2_HOURS_S3_MINUTES = 2230;
    public static final int ENEMY_BLOOD_PLEDGES_HAVE_INTRUDED_INTO_THE_FORTRESS = 2084; 
    public static final int A_FORTRESS_IS_UNDER_ATTACK = 2087;
    public static final int S1_MINUTE_UNTIL_THE_FORTRESS_BATTLE_STARTS = 2088; 
    public static final int S1_SECOND_UNTIL_THE_FORTRESS_BATTLE_STARTS = 2089; 
    public static final int THE_FORTRESS_BATTLE_S1_HAS_BEGAN = 2090;
    public static final int YOUR_CLAN_HAS_BEEN_REGISTERED_TO_S1_FORTRESS_BATTLE = 2169;
    public static final int THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED = 2183;
    public static final int S1_CLAN_IS_VICTORIOUS_IN_THE_FORTRESS_BATLE_OF_S2 = 2184;
    public static final int THE_REBEL_ARMY_RECAPTURED_THE_FORTRESS = 2276;
    public static final int FIVE_YEARS_HAVE_PASSED_SINCE_THIS_CHARACTERS_CREATION = 2447;
    public static final int YOUR_BIRTHDAY_GIFT_HAS_ARRIVED_YOU_CAN_OBTAIN_IT_FROM_THE_GATEKEEPER_IN_ANY_VILLAGE = 2448;
    public static final int THERE_ARE_S1_DAYS_UNTIL_YOUR_CHARACTERS_BIRTHDAY_ON_THAT_DAY_YOU_CAN_OBTAIN_A_SPECIAL_GIFT_FROM_THE_GATEKEEPER_IN_ANY_VILLAGE = 2449;
    public static final int C1S_CHARACTER_BIRTHDAY_IS_S3S4S2 = 2450;
    public static final int _IF_YOU_JOIN_THE_CLAN_ACADEMY_YOU_CAN_BECOME_A_CLAN_MEMBER_AND_LEARN_THE_GAME_SYSTEM_UNTIL_YOU = 2875;
    public static final int _IF_YOU_BECOME_LEVEL_40_THE_SECOND_CLASS_CHANGE_IS_AVAILABLE_IF_YOU_COMPLETE_THE_SECOND_CLASS = 2876;
    public static final int THIS_ITEM_CANNOT_BE_USED_IN_THE_CURRENT_TRANSFORMATION_STATTE = 2962;
    public static final int THE_OPPONENT_HAS_NOT_EQUIPPED_S1_SO_S2_CANNOT_BE_USED = 2963;
    public static final int BEING_APPOINTED_AS_A_NOBLESSE_WILL_CANCEL_ALL_RELATED_QUESTS_DO_YOU_WISH_TO_CONTINUE = 2964; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�ÂµÃ‘â€šÃ�Âµ
    // Ã�â€�Ã�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â½Ã�Â¾Ã�Â¼,
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘â€¹Ã�Âµ
    // Ã�ÂºÃ�Â²Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘â€¹
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’Ã‘â€š
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã‘â€¹.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_CANNOT_PURCHASE_AND_RE_PURCHASE_THE_SAME_TYPE_OF_ITEM_AT_THE_SAME_TIME = 2965; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â¾Ã�Â²
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã‘â€šÃ�Â¸Ã�Â¿Ã�Â°
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int IT_S_A_PAYMENT_REQUEST_TRANSACTION_PLEASE_ATTACH_THE_ITEM = 2966; // Ã�â€˜Ã�ÂµÃ�Â·Ã�Â¾Ã�Â¿Ã�Â°Ã‘ï¿½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ�Â°.
    // Ã�Å¸Ã‘â‚¬Ã�Â¸Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š.
    public static final int YOU_ARE_ATTEMPTING_TO_SEND_MAIL_DO_YOU_WISH_TO_PROCEED = 2967; // Ã�â€™Ã‘â€¹
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’?
    public static final int THE_MAIL_LIMIT_240_HAS_BEEN_EXCEEDED_AND_THIS_CANNOT_BE_FORWARDED = 2968; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã�Â¿Ã�Â¾Ã‘â€¡Ã‘â€šÃ‘â€¹
    // (240
    // Ã‘Ë†Ã‘â€š.),
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int THE_PREVIOUS_MAIL_WAS_FORWARDED_LESS_THAN_1_MINUTE_AGO_AND_THIS_CANNOT_BE_FORWARDED = 2969; // Ã�Â¡
    // Ã�Â¼Ã�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã‘â€¹Ã�Â´Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â°
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // Ã�Â¾Ã�Â´Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¼Ã�Â¸Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â€¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_FORWARD_IN_A_NON_PEACE_ZONE_LOCATION = 2970; // Ã�Å¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¸Ã�Â·
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â·Ã�Â¾Ã�Â½.
    public static final int YOU_CANNOT_FORWARD_DURING_AN_EXCHANGE = 2971; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_FORWARD_BECAUSE_THE_PRIVATE_SHOP_OR_WORKSHOP_IS_IN_PROGRESS = 2972; // Ã�Å¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int YOU_CANNOT_FORWARD_DURING_AN_ITEM_ENHANCEMENT_OR_ATTRIBUTE_ENHANCEMENT = 2973; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int THE_ITEM_THAT_YOU_RE_TRYING_TO_SEND_CANNOT_BE_FORWARDED_BECAUSE_IT_ISN_T_PROPER = 2974; // Ã�Å¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€š.
    public static final int YOU_CANNOT_FORWARD_BECAUSE_YOU_DON_T_HAVE_ENOUGH_ADENA = 2975; // Ã�Â£
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â¸.
    public static final int YOU_CANNOT_RECEIVE_IN_A_NON_PEACE_ZONE_LOCATION = 2976; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int YOU_CANNOT_RECEIVE_DURING_AN_EXCHANGE = 2977; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂµÃ�Â¼
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_RECEIVE_BECAUSE_THE_PRIVATE_SHOP_OR_WORKSHOP_IS_IN_PROGRESS = 2978; // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_RECEIVE_DURING_AN_ITEM_ENHANCEMENT_OR_ATTRIBUTE_ENHANCEMENT = 2979; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_RECEIVE_BECAUSE_YOU_DON_T_HAVE_ENOUGH_ADENA = 2980; // Ã�Â£
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int YOU_COULD_NOT_RECEIVE_BECAUSE_YOUR_INVENTORY_IS_FULL = 2981; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘ï¿½
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ‘Æ’.
    public static final int YOU_CANNOT_CANCEL_IN_A_NON_PEACE_ZONE_LOCATION = 2982; // Ã�Å¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â°
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int YOU_CANNOT_CANCEL_DURING_AN_EXCHANGE = 2983; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_CANCEL_BECAUSE_THE_PRIVATE_SHOP_OR_WORKSHOP_IS_IN_PROGRESS = 2984; // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_CANCEL_DURING_AN_ITEM_ENHANCEMENT_OR_ATTRIBUTE_ENHANCEMENT = 2985; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int PLEASE_SET_THE_AMOUNT_OF_ADENA_TO_SEND = 2986; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã‘ï¿½Ã�ÂµÃ�Â¼Ã‘â€¹Ã‘â€¦
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int PLEASE_SET_THE_AMOUNT_OF_ADENA_TO_RECEIVE = 2987; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ�Â¼Ã‘â€¹Ã‘â€¦
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int YOU_COULD_NOT_CANCEL_RECEIPT_BECAUSE_YOUR_INVENTORY_IS_FULL = 2988; // Ã�ËœÃ�Â·-Ã�Â·Ã�Â°
    // Ã�Â¾Ã‘Ë†Ã�Â¸Ã�Â±Ã�ÂºÃ�Â¸
    // Ã�Â²
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int VITAMIN_ITEM_S1_IS_BEING_USED = 2989; // Ã�â€™Ã‘â€¹
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã�â€™Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½
    // $s1%.
    public static final int _2_UNITS_OF_VITAMIN_ITEM_S1_WAS_CONSUMED = 2990; // Ã�ËœÃ‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½
    // Ã�â€™Ã�Â¸Ã‘â€šÃ�Â°Ã�Â¼Ã�Â¸Ã�Â½
    // $s1%
    // Ã�Â²
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Âµ
    // $2
    // Ã�ÂµÃ�Â´.
    public static final int TRUE_INPUT_MUST_BE_ENTERED_BY_SOMEONE_OVER_15_YEARS_OLD = 2991; // Ã�Å¸Ã�ÂµÃ‘â€šÃ�Â¸Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã�Â°
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Âµ
    // Ã�Â±Ã�Â¾Ã�Â»Ã�ÂµÃ�Âµ
    // 15
    // Ã‘ï¿½Ã�Â¸Ã�Â¼Ã�Â²Ã�Â¾Ã�Â»Ã�Â¾Ã�Â².
    public static final int PLEASE_CHOOSE_THE_2ND_STAGE_TYPE = 2992; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€šÃ�Â¸Ã�Â¿ 2-Ã�Â³Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â¿Ã�Â°.
    public static final int WHEN_AN_COMMAND_CHANNEL_LEADER_GOES_OUT_OF_THE_COMMAND_CHANNEL_MATCHING_ROOM_THE_CURRENTLY_OPEN = 2993; // Ã�Å¸Ã‘â‚¬Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘â€¦Ã�Â¾Ã�Â´Ã�Âµ
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â°Ã�Â½Ã�Â´Ã�Â¸Ã‘â‚¬Ã�Â°
    // Ã�ï¿½Ã�Â»Ã‘Å’Ã‘ï¿½Ã�Â½Ã‘ï¿½Ã�Â°
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã�Â¾Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½.
    // Ã�Â¥Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â²Ã‘â€¹Ã�Â¹Ã‘â€šÃ�Â¸?
    public static final int THE_COMMAND_CHANNEL_MATCHING_ROOM_WAS_CANCELLED = 2994; // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°.
    public static final int THIS_COMMAND_CHANNEL_MATCHING_ROOM_IS_ALREADY_CANCELLED = 2995; // Ã�Â­Ã‘â€šÃ�Â°
    // Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Â°
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã�Â·Ã�Â°Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ�Â°.
    public static final int YOU_CANNOT_ENTER_THE_COMMAND_CHANNEL_MATCHING_ROOM_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS = 2996; // Ã�â€™Ã‘â€¹
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°.
    public static final int YOU_EXITED_FROM_THE_COMMAND_CHANNEL_MATCHING_ROOM = 2997; // Ã�â€™Ã‘â€¹
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°.
    public static final int YOU_WERE_EXPELLED_FROM_THE_COMMAND_CHANNEL_MATCHING_ROOM = 2998; // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â²Ã‘â€¹Ã�Â³Ã�Â½Ã�Â°Ã�Â»Ã�Â¸
    // Ã�Â¸Ã�Â·
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°.
    public static final int THE_COMMAND_CHANNEL_AFFILIATED_PARTY_S_PARTY_MEMBER_CANNOT_USE_THE_MATCHING_SCREEN = 2999; // Ã�Â§Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â³Ã‘â‚¬Ã‘Æ’Ã�Â¿Ã�Â¿Ã‘â€¹,
    // Ã�Â²Ã‘â€¦Ã�Â¾Ã�Â´Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Â¹
    // Ã�Â²
    // Ã�Å¡Ã�Â°Ã�Â½Ã�Â°Ã�Â»
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°,
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¾Ã�ÂºÃ�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°.
    public static final int THE_COMMAND_CHANNEL_MATCHING_ROOM_WAS_CREATED = 3000; // Ã�Â¡Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â½Ã�Â°
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ�Â°
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°.
    public static final int THE_COMMAND_CHANNEL_MATCHING_ROOM_INFORMATION_WAS_EDITED = 3001; // Ã�ËœÃ�Â·Ã�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°.
    public static final int WHEN_THE_RECIPIENT_DOESN_T_EXIST_OR_THE_CHARACTER_HAS_BEEN_DELETED_SENDING_MAIL_IS_NOT_POSSIBLE = 3002; // Ã�ï¿½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾,
    // Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã‘Æ’Ã‘â€°Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½.
    public static final int C1_ENTERED_THE_COMMAND_CHANNEL_MATCHING_ROOM = 3003; // $c1
    // Ã�Â²Ã�Â¾Ã‘Ë†Ã�ÂµÃ�Â»
    // Ã�Â²
    // Ã�Å¡Ã�Â¾Ã�Â¼Ã�Â½Ã�Â°Ã‘â€šÃ‘Æ’
    // Ã�Å¸Ã�Â¾Ã�Â¸Ã‘ï¿½Ã�ÂºÃ�Â°
    // Ã�Â¡Ã�Â¾Ã‘Å½Ã�Â·Ã�Â°.
    public static final int I_M_SORRY_TO_GIVE_YOU_A_SATISFACTORY_RESPONSE__N__NIF_YOU_SEND_YOUR_COMMENTS_REGARDING_THE = 3004; // Ã�ËœÃ�Â·Ã�Â²Ã�Â¸Ã�Â½Ã�Â¸Ã‘â€šÃ�Âµ,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã�Â»Ã�Â¸
    // Ã�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘Æ’Ã�Â´Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ‘â€šÃ�Â²Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã‘Å½Ã‘â€°Ã�Â¸Ã�Â¹
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€š.\\n\\nÃ�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�ÂºÃ�Â°Ã�Â¶Ã�ÂµÃ‘â€šÃ�Âµ,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã�Â»Ã�Â¾
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã�Â²Ã�Â¾Ã�Â»Ã‘Å’Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾,
    // Ã�Â¼Ã‘â€¹
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�Â°Ã�ÂµÃ�Â¼
    // Ã�Â²Ã‘ï¿½Ã�Âµ,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â±Ã�Â¾Ã�Â»Ã‘Å’Ã‘Ë†Ã�Âµ
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.\\n\\nÃ�â€˜Ã‘Æ’Ã�Â´Ã�ÂµÃ�Â¼
    // Ã‘â‚¬Ã�Â°Ã�Â´Ã‘â€¹
    // Ã�â€™Ã�Â°Ã‘Ë†Ã�ÂµÃ�Â¼Ã‘Æ’
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã�Â±Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘Å½.
    public static final int THIS_SKILL_CANNOT_BE_ENHANCED = 3005; // Ã�Â­Ã‘â€šÃ�Â¾ Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int NEWLY_USED_PC_CAFE_S1_POINTS_WERE_WITHDRAWN = 3006; // Ã�ï¿½Ã�ÂµÃ�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¸
    // Ã�Â Ã�Â¡,
    // $s1,
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’.
    public static final int SHYEED_S_ROAR_FILLED_WITH_WRATH_RINGS_THROUGHOUT_THE_STAKATO_NEST = 3007; // Ã�â€œÃ�Â½Ã�ÂµÃ�Â·Ã�Â´Ã�Â¾
    // Ã�Â¡Ã‘â€šÃ�Â°Ã�ÂºÃ�Â°Ã‘â€šÃ�Â¾
    // Ã�Â½Ã�Â°Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã‘ï¿½Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã�Â»Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â¼
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â³Ã�Â½Ã�ÂµÃ�Â²Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¨Ã�Â¸Ã�Â¸Ã�Â´.
    public static final int THE_MAIL_HAS_ARRIVED = 3008; // Ã�â€�Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â° Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â°.
    public static final int MAIL_SUCCESSFULLY_SENT = 3009; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸ Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ‘Æ’.
    public static final int MAIL_SUCCESSFULLY_RETURNED = 3010; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â° Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â½Ã�Â¾.
    public static final int MAIL_SUCCESSFULLY_CANCELLED = 3011; // Ã�â€™Ã‘â€¹ Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’.
    public static final int MAIL_SUCCESSFULLY_RECEIVED = 3012; // Ã�Å¸Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â°
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°.
    public static final int C1_HAS_SUCCESSFULY_ENCHANTED_A__S2_S3 = 3013; // $c1
    // Ã‘Æ’Ã‘ï¿½Ã�Â¿Ã�ÂµÃ‘Ë†Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â¸Ã�Â»
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â´Ã�Â¾
    // +$s2$s3.
    public static final int DO_YOU_WISH_TO_ERASE_THE_SELECTED_MAIL = 3014; // Ã�â€™Ã‘â€¹
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â€¹Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Âµ
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â°?
    public static final int PLEASE_SELECT_THE_MAIL_TO_BE_DELETED = 3015; // Ã�â€™Ã‘â€¹Ã�Â±Ã�ÂµÃ‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â°,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Âµ
    // Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã�ÂµÃ‘â€šÃ‘Å’.
    public static final int ITEM_SELECTION_IS_POSSIBLE_UP_TO_8 = 3016;
    public static final int YOU_CANNOT_USE_ANY_SKILL_ENHANCING_SYSTEM_UNDER_YOUR_STATUS_CHECK_OUT_THE_PC_S_CURRENT_STATUS = 3017;
    public static final int YOU_CANNOT_USE_SKILL_ENHANCING_SYSTEM_FUNCTIONS_FOR_THE_SKILLS_CURRENTLY_NOT_ACQUIRED = 3018;
    public static final int YOU_CANNOT_SEND_A_MAIL_TO_YOURSELF = 3019;
    public static final int WHEN_NOT_ENTERING_THE_AMOUNT_FOR_THE_PAYMENT_REQUEST_YOU_CANNOT_SEND_ANY_MAIL = 3020;
    public static final int STAND_BY_FOR_THE_GAME_TO_BEGIN = 3021;
    public static final int THE_KASHA_S_EYE_GIVES_YOU_A_STRANGE_FEELING = 3022;
    public static final int I_CAN_FEEL_THAT_THE_ENERGY_BEING_FLOWN_IN_THE_KASHA_S_EYE_IS_GETTING_STRONGER_RAPIDLY = 3023;
    public static final int KASHA_S_EYE_PITCHES_AND_TOSSES_LIKE_IT_S_ABOUT_TO_EXPLODE = 3024;
    public static final int S2_HAS_MADE_A_PAYMENT_OF_S1_ADENA_PER_YOUR_PAYMENT_REQUEST_MAIL = 3025;
    public static final int YOU_CANNOT_USE_THE_SKILL_ENHANCING_FUNCTION_ON_THIS_LEVEL_YOU_CAN_USE_THE_CORRESPONDING_FUNCTION = 3026;
    public static final int YOU_CANNOT_USE_THE_SKILL_ENHANCING_FUNCTION_IN_THIS_CLASS_YOU_CAN_USE_CORRESPONDING_FUNCTION = 3027;
    public static final int YOU_CANNOT_USE_THE_SKILL_ENHANCING_FUNCTION_IN_THIS_CLASS_YOU_CAN_USE_THE_SKILL_ENHANCING = 3028;
    public static final int S1_RETURNED_THE_MAIL = 3029;
    public static final int YOU_CANNOT_CANCEL_SENT_MAIL_SINCE_THE_RECIPIENT_RECEIVED_IT = 3030;
    public static final int BY_USING_THE_SKILL_OF_EINHASAD_S_HOLY_SWORD_DEFEAT_THE_EVIL_LILIMS = 3031;
    public static final int IN_ORDER_TO_HELP_ANAKIM_ACTIVATE_THE_SEALING_DEVICE_OF_THE_EMPEROR_WHO_IS_POSSESED_BY_THE_EVIL = 3032;
    public static final int BY_USING_THE_INVISIBLE_SKILL_SNEAK_INTO_THE_DAWN_S_DOCUMENT_STORAGE = 3033;
    public static final int THE_DOOR_IN_FRONT_OF_US_IS_THE_ENTRANCE_TO_THE_DAWN_S_DOCUMENT_STORAGE_APPROACH_TO_THE_CODE = 3034;
    public static final int MY_POWER_S_WEAKENING_PLEASE_ACTIVATE_THE_SEALING_DEVICE_POSSESSED_BY_LILITH_S_MAGICAL_CURSE = 3035;
    public static final int YOU_SUCH_A_FOOL_THE_VICTORY_OVER_THIS_WAR_BELONGS_TO_SHILIEN = 3036;
    public static final int MALE_GUARDS_CAN_DETECT_THE_CONCEALMENT_BUT_THE_FEMALE_GUARDS_CANNOT = 3037;
    public static final int FEMALE_GUARDS_NOTICE_THE_DISGUISES_FROM_FAR_AWAY_BETTER_THAN_THE_MALE_GUARDS_DO_SO_BEWARE = 3038;
    public static final int BY_USING_THE_HOLY_WATER_OF_EINHASAD_OPEN_THE_DOOR_POSSESSED_BY_THE_CURSE_OF_FLAMES = 3039;
    public static final int BY_USING_THE_COURT_MAGICIAN_S_MAGIC_STAFF_OPEN_THE_DOOR_ON_WHICH_THE_MAGICIAN_S_BARRIER_IS = 3040; // Ã�â€™Ã�Â¾Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã‘Æ’Ã�Â¹Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¡Ã�ÂºÃ�Â¸Ã�Â¿Ã�ÂµÃ‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¼
    // Ã�Å¡Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â»Ã�ÂµÃ�Â²Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�â€™Ã�Â¾Ã�Â»Ã‘Ë†Ã�ÂµÃ�Â±Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã‘â€šÃ‘Å’
    // Ã�â€œÃ‘â‚¬Ã�Â°Ã�Â½Ã‘Å’
    // Ã�Â Ã�ÂµÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    // Ã�â€™Ã�Â¾Ã�Â»Ã‘Ë†Ã�ÂµÃ�Â±Ã�Â½Ã�Â¸Ã�ÂºÃ�Â°.
    public static final int AROUND_FIFTEEN_HUNDRED_YEARS_AGO_THE_LANDS_WERE_RIDDLED_WITH_HERETICS = 3041; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹
    // Ã‘â€šÃ‘â€¹Ã‘ï¿½Ã‘ï¿½Ã‘â€¡Ã�Â¸
    // Ã�Â»Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â°Ã�Â´
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½,
    // Ã�ÂºÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â´Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã�Â¸\\nÃ�Â±Ã�Â¾Ã�Â³Ã�Â¸Ã�Â½Ã�Â¸
    // Ã�Â¨Ã�Â¸Ã�Â»Ã�ÂµÃ�Â½
    public static final int WORSHIPPERS_OF_SHILEN_THE_GODDESS_OF_DEATH = 3042; // Ã�Â½Ã�Â°Ã�Â·Ã‘â€¹Ã�Â²Ã�Â°Ã�Â»Ã�Â¸
    // Ã‘ï¿½Ã�ÂµÃ�Â±Ã‘ï¿½
    // Ã�â€�Ã�ÂµÃ‘â€šÃ‘Å’Ã�Â¼Ã�Â¸
    // Ã�Â¨Ã�Â¸Ã�Â»Ã�ÂµÃ�Â½.
    // Ã�ËœÃ‘â€¦
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â°
    // Ã�Â±Ã‘â€¹Ã�Â»Ã�Â°
    // Ã‘â€¡Ã‘â‚¬Ã�ÂµÃ�Â·Ã�Â²Ã‘â€¹Ã‘â€¡Ã�Â°Ã�Â¹Ã�Â½Ã�Â¾\\nÃ�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã�ÂºÃ�Â°.
    public static final int BUT_A_MIRACLE_HAPPENED_AT_THE_ENTHRONEMENT_OF_SHUNAIMAN_THE_FIRST_EMPEROR = 3043; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã‘ï¿½Ã�Â¼Ã‘Æ’Ã‘â€šÃ�Â½Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â»Ã�Â°
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¸Ã�Â¼Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â°\\nÃ�Â­Ã�Â»Ã‘Å’Ã�Â¼Ã�Â¾Ã‘â‚¬Ã�ÂµÃ�Â´Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¨Ã‘Æ’Ã�Â½Ã�Â°Ã�Â¹Ã�Â¼Ã�Â°Ã�Â½Ã�Â°.
    public static final int ANAKIM_AN_ANGEL_OF_EINHASAD_CAME_DOWN_FROM_THE_SKIES = 3044; // Ã�â€™Ã�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¹
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â½Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â¸Ã�Â·Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾
    // Ã‘â€¡Ã‘Æ’Ã�Â´Ã�Â¾.
    // Ã�Â¡
    // Ã�Â½Ã�ÂµÃ�Â±Ã�Â°
    // Ã‘ï¿½Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã�Â»Ã�Â°Ã‘ï¿½Ã‘Å’
    public static final int SURROUNDED_BY_SACRED_FLAMES_AND_THREE_PAIRS_OF_WINGS = 3045; // Ã‘Ë†Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¸Ã�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â»Ã�Â°Ã‘ï¿½
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â°Ã�Â½Ã�Â½Ã�Â¸Ã‘â€ Ã�Â°
    // Ã�Â­Ã�Â¹Ã�Â½Ã‘â€¦Ã�Â°Ã‘ï¿½Ã�Â°Ã�Â´,
    // Ã�ï¿½Ã�Â½Ã�Â°Ã�ÂºÃ�Â¸Ã�Â¼.
    public static final int THUS_EMPOWERED_THE_EMPEROR_LAUNCHED_A_WAR_AGAINST__SHILEN_S_PEOPLE_ = 3046; // Ã�ËœÃ�Â¼Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â¾Ã‘â‚¬
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ�Â»
    // Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã�ÂºÃ‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘Æ’
    // Ã�Â¸
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»
    // Ã‘ï¿½Ã�Â²Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã‘Å½
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã‘Æ’
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€šÃ�Â¸Ã�Â²\\nÃ�â€�Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â¹
    // Ã�Â¨Ã�Â¸Ã�Â»Ã�ÂµÃ�Â½.
    public static final int THE_EMPEROR_S_ARMY_LED_BY_ANAKIM_ATTACKED__SHILEN_S_PEOPLE__RELENTLESSLY = 3047; // Ã�Â¥Ã�Â¾Ã‘â€šÃ‘ï¿½
    // Ã�Â°Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘ï¿½
    // Ã�Â¸Ã�Â¼Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â°,
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â½Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã‘Æ’Ã‘â€¦Ã�Â¾Ã�Â¼
    // Ã�ï¿½Ã�Â½Ã�Â°Ã�ÂºÃ�Â¸Ã�Â¼Ã�Â°,\\nÃ‘ï¿½
    // Ã�Â»Ã�ÂµÃ�Â³Ã�ÂºÃ�Â¾Ã‘ï¿½Ã‘â€šÃ‘Å’Ã‘Å½
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»Ã�Â°
    // Ã�â€�Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â¹
    // Ã�Â¨Ã�Â¸Ã�Â»Ã�ÂµÃ�Â½,
    public static final int BUT_IN_THE_END_SOME_SURVIVORS_MANAGED_TO_HIDE_IN_UNDERGROUND_CATACOMBS = 3048; // Ã�Â¼Ã�Â½Ã�Â¾Ã�Â³Ã�Â¸Ã�Âµ
    // Ã�Â¸Ã�Â·
    // Ã�Â½Ã�Â¸Ã‘â€¦
    // Ã�Â²Ã‘â€¹Ã�Â¶Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â¿Ã‘â‚¬Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â²
    // Ã�ÂºÃ�Â°Ã‘â€šÃ�Â°Ã�ÂºÃ�Â¾Ã�Â¼Ã�Â±Ã�Â°Ã‘â€¦
    // Ã‘â€¦Ã‘â‚¬Ã�Â°Ã�Â¼Ã�Â°.
    public static final int A_NEW_LEADER_EMERGED_LILITH_WHO_SEEKED_TO_SUMMON_SHILEN_FROM_THE_AFTERLIFE = 3049; // Ã�â€™Ã‘â€¹Ã�Â¶Ã�Â¸Ã�Â²Ã‘Ë†Ã�Â¸Ã�Âµ
    // Ã�â€�Ã�ÂµÃ‘â€šÃ�Â¸
    // Ã�Â¨Ã�Â¸Ã�Â»Ã�ÂµÃ�Â½
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�ÂµÃ�Â»Ã�Â¸
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã�ÂµÃ�Â³Ã�Â¾
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â»Ã�Â¸Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â°,
    // Ã�â€ºÃ�Â¸Ã�Â»Ã�Â¸Ã‘â€š,\\nÃ�Â²Ã�Â¾Ã�Â·Ã�Â³Ã�Â»Ã�Â°Ã�Â²Ã�Â¸Ã�Â²Ã‘Ë†Ã‘Æ’Ã‘Å½
    // Ã�Â¸Ã‘â€¦
    // Ã‘ï¿½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’Ã‘Å½
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã�ÂºÃ‘â‚¬Ã�ÂµÃ‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¨Ã�Â¸Ã�Â»Ã�ÂµÃ�Â½
    public static final int AND_TO_REBUILD_THE_LILIM_ARMY_WITHIN_THE_EIGHT_NECROPOLISES = 3050; // Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã�Â·Ã�Â´Ã�Â°Ã�Â²Ã‘Ë†Ã‘Æ’Ã‘Å½
    // Ã‘ï¿½Ã�Â²Ã�Â¾Ã‘Å½
    // Ã�Â°Ã‘â‚¬Ã�Â¼Ã�Â¸Ã‘Å½
    // Ã�Â²
    // Ã�Â²Ã�Â¾Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�ÂºÃ‘â‚¬Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã‘ï¿½Ã‘â€¦.
    public static final int NOW_IN_THE_MIDST_OF_IMPENDING_WAR_THE_MERCHANT_OF_MAMMON_STRUCK_A_DEAL = 3051; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¢Ã�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã‘â€ Ã‘â€¹
    // Ã�Å“Ã�Â°Ã�Â¼Ã�Â¼Ã�Â¾Ã�Â½Ã�Â°,
    // Ã‘â‚¬Ã�Â°Ã�Â´Ã�Â¸
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³
    // Ã�Â³Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Âµ
    // Ã�Â½Ã�Â°
    // Ã�Â²Ã‘ï¿½Ã�Âµ,
    public static final int HE_SUPPLIES_SHUNAIMAN_WITH_WAR_FUNDS_IN_EXCHANGE_FOR_PROTECTION = 3052; // Ã�Â·Ã�Â°Ã�ÂºÃ�Â»Ã‘Å½Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘ï¿½
    // Ã�Â¸Ã�Â¼Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â°Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�ÂµÃ�ÂºÃ‘â‚¬Ã�ÂµÃ‘â€šÃ�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â´Ã�Â¾Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬.
    public static final int AND_RIGHT_NOW_THE_DOCUMENT_WE_RE_LOOKING_FOR_IS_THAT_CONTRACT = 3053; // Ã�Ëœ
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â´Ã�Â¾Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã‘â‚¬
    // Ã�Â¼Ã‘â€¹
    // Ã�Â¸
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â½Ã‘â€¹
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â€¹Ã‘ï¿½Ã�ÂºÃ�Â°Ã‘â€šÃ‘Å’.
    public static final int FINALLY_YOU_RE_HERE_I_M_ANAKIM_I_NEED_YOUR_HELP = 3054; // Ã�ï¿½Ã�Â°Ã�ÂºÃ�Â¾Ã�Â½Ã�ÂµÃ‘â€ -Ã‘â€šÃ�Â¾
    // Ã�Â²Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã‘Ë†Ã�Â»Ã�Â¸!
    // Ã�Â¯
    // Ã�Â¶Ã�Â´Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â²Ã�Â°Ã‘ï¿½!
    public static final int IT_S_THE_SEAL_DEVICES_I_NEED_YOU_TO_DESTROY_THEM_WHILE_I_DISTRACT_LILITH = 3055; // Ã�Å¸Ã�Â¾Ã�ÂºÃ�Â°
    // Ã‘ï¿½
    // Ã�Â±Ã‘Æ’Ã�Â´Ã‘Æ’
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�â€ºÃ�Â¸Ã�Â»Ã�Â¸Ã‘â€š,
    // Ã�Â°Ã�ÂºÃ‘â€šÃ�Â¸Ã�Â²Ã�Â¸Ã�Â·Ã�Â¸Ã‘â‚¬Ã‘Æ’Ã�Â¹Ã‘â€šÃ�Âµ
    // Ã�Â¼Ã�ÂµÃ‘â€¦Ã�Â°Ã�Â½Ã�Â¸Ã�Â·Ã�Â¼\\nÃ�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸!
    public static final int PLEASE_HURRY_I_DON_T_HAVE_MUCH_TIME_LEFT = 3056; // Ã�Â¯
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¼Ã�Â¾Ã�Â³Ã‘Æ’
    // Ã�Â´Ã�Â¾Ã�Â»Ã�Â³Ã�Â¾
    // Ã�ÂµÃ�Âµ
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â¸Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’.
    // Ã�Â¢Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â¿Ã�Â¸Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’!
    public static final int FOR_EINHASAD = 3057; // Ã�â€”Ã�Â° Ã�Â­Ã�Â¹Ã�Â½Ã‘â€¦Ã�Â°Ã‘ï¿½Ã�Â°Ã�Â´!
    public static final int EMBRYO = 3058; // Ã�Â­Ã�Â¼Ã�Â±... Ã‘â‚¬Ã�Â¸Ã�Â¾Ã¢â‚¬Â¦
    public static final int S1_DID_NOT_RECEIVE_IT_DURING_THE_WAITING_TIME_SO_IT_WAS_RETURNED_AUTOMATICALLY_TNTLS = 3059; // $s1
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ‘Æ’
    // Ã�Â²
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¾Ã�Â½Ã�Â°
    // Ã�Â²Ã�Â¾Ã�Â·Ã�Â²Ã‘â‚¬Ã�Â°Ã‘â€°Ã�ÂµÃ�Â½Ã�Â°.
    public static final int THE_SEALING_DEVICE_GLITTERS_AND_MOVES_ACTIVATION_COMPLETE_NORMALLY = 3060; // Ã�Â£Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Å¸Ã�ÂµÃ‘â€¡Ã�Â°Ã‘â€šÃ�Â¸
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¾.
    // Ã�Å“Ã�ÂµÃ‘â€¦Ã�Â°Ã�Â½Ã�Â¸Ã�Â·Ã�Â¼
    // Ã�Â²
    // Ã�Â¿Ã�Â¾Ã‘â‚¬Ã‘ï¿½Ã�Â´Ã�ÂºÃ�Âµ!
    public static final int THERE_COMES_A_SOUND_OF_OPENING_THE_HEAVY_DOOR_FROM_SOMEWHERE = 3061; // Ã�â€�Ã�Â¾
    // Ã�â€™Ã�Â°Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã�Â½Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â·Ã�Â²Ã‘Æ’Ã�Âº
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ‘â‚¬Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€°Ã�Â¸Ã‘â€¦Ã‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â²Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¹.
    public static final int DO_YOU_WANT_TO_PAY_S1_ADENA = 3062; // Ã�â€”Ã�Â°Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’ $s1
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½?
    public static final int DO_YOU_REALLY_WANT_TO_FORWARD = 3063; // Ã�â€™Ã�ÂµÃ‘â‚¬Ã�Â½Ã‘Æ’Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ‘Æ’?
    public static final int THERE_IS_AN_UNREAD_MAIL = 3064; // Ã�Â£ Ã�Â²Ã�Â°Ã‘ï¿½ Ã�ÂµÃ‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â¿Ã‘â‚¬Ã�Â¾Ã‘â€¡Ã‘â€šÃ�ÂµÃ�Â½Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã�Â¾.
    public static final int CURRENT_LOCATION__INSIDE_THE_CHAMBER_OF_DELUSION = 3065; // Ã�Å¡Ã�Â¾Ã�Â¾Ã‘â‚¬Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã‘â€šÃ‘â€¹:
    // Ã�â€™Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�â€œÃ‘â‚¬Ã�Â°Ã�Â½Ã�Â¸
    // Ã�Â Ã�ÂµÃ�Â°Ã�Â»Ã‘Å’Ã�Â½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸
    public static final int YOU_CANNOT_USE_THE_MAIL_FUNCTION_OUTSIDE_THE_PEACE_ZONE = 3066; // Ã�Â¤Ã‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã‘â€¡Ã‘â€šÃ‘â€¹
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¸Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã‘Å’Ã�Â·Ã�Â¾Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â²
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â·Ã�Â¾Ã�Â½Ã�Âµ.
    public static final int S1_CANCELED_THE_SENT_MAIL = 3067; // $s1 Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â»
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â¸.
    public static final int THE_MAIL_WAS_RETURNED_DUE_TO_THE_EXCEEDED_WAITING_TIME = 3068; // Ã�â€™Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â¾Ã�Â¶Ã�Â¸Ã�Â´Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â¾,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ�Â°
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã‘â€šÃ�Â½Ã�Â¾.
    public static final int DO_YOU_REALLY_WANT_TO_CANCEL_THE_TRANSACTION = 3069; // Ã�Å¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ‘Æ’?
    public static final int SKILL_NOT_AVAILABLE_TO_BE_ENHANCED_CHECK_SKILL_S_LV_AND_CURRENT_PC_STATUS = 3070; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘Æ’Ã�Â»Ã‘Æ’Ã‘â€¡Ã‘Ë†Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Å’Ã‘â€šÃ�Âµ
    // Ã‘Æ’Ã‘â‚¬Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã‘Å’
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¸
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°.
    public static final int DO_YOU_REALLY_WANT_TO_RESET_1000000010_MILLION_ADENA_WILL_BE_CONSUMED = 3071; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·Ã�Â°Ã�Â¿Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ‘Å’?
    // Ã�â€˜Ã‘Æ’Ã�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¸Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // 10,000,000
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½.
    public static final int S1_ACQUIRED_THE_ATTACHED_ITEM_TO_YOUR_MAIL = 3072; // $s1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š,
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�ÂºÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Âº
    // Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘Å’Ã�Â¼Ã‘Æ’.
    public static final int YOU_HAVE_ACQUIRED_S2_S1 = 3073; // Ã�â€™Ã‘â€¹ Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸ $s1,
    // $s2 Ã�ÂµÃ�Â´.
    public static final int THE_ALLOWED_LENGTH_FOR_RECIPIENT_EXCEEDED = 3074; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã‘Æ’
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½.
    public static final int THE_ALLOWED_LENGTH_FOR_A_TITLE_EXCEEDED = 3075; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘Æ’Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â½Ã‘Æ’Ã‘Å½
    // Ã�Â´Ã�Â»Ã�Â¸Ã�Â½Ã‘Æ’
    // Ã�Â½Ã�Â°Ã�Â·Ã�Â²Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int _THE_ALLOWED_LENGTH_FOR_A_TITLE_EXCEEDED = 3076; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã‘â‚¬Ã�ÂµÃ‘Ë†Ã�ÂµÃ�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¾Ã�Â±Ã‘Å Ã�ÂµÃ�Â¼
    // Ã‘ï¿½Ã�Â¾Ã�Â´Ã�ÂµÃ‘â‚¬Ã�Â¶Ã�Â°Ã�Â½Ã�Â¸Ã‘ï¿½.
    public static final int THE_MAIL_LIMIT_240_OF_THE_OPPONENT_S_CHARACTER_HAS_BEEN_EXCEEDED_AND_THIS_CANNOT_BE_FORWARDED = 3077; // Ã�Â£
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘â€šÃ�ÂµÃ�Â»Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½
    // Ã�Â¿Ã�Â¾Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â²Ã‘â€¹Ã�Â¹
    // Ã‘ï¿½Ã‘â€°Ã�Â¸Ã�Âº
    // (240
    // Ã�ÂµÃ�Â´.),
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�ÂºÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int YOU_RE_MAKING_A_REQUEST_FOR_PAYMENT_DO_YOU_WANT_TO_PROCEED = 3078; // Ã�Å¾Ã�Â¿Ã�Â»Ã�Â°Ã‘â€šÃ�Â°.
    // Ã�Å¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’Ã�Â³Ã�Â¸?
    public static final int THERE_ARE_ITEMS_IN_THE_PET_INVENTORY_SO_YOU_CANNOT_REGISTER_AS_AN_INDIVIDUAL_STORE_OR_DROP_ITEMS = 3079; // Ã�â€™
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã�Âµ
    // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¸Ã�Â¼Ã�ÂµÃ‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ‘â€¹,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�Â¾Ã�Â±Ã�Â¼Ã�ÂµÃ�Â½Ã‘ï¿½Ã‘â€šÃ‘Å’
    // Ã�ÂµÃ�Â³Ã�Â¾,
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â°Ã�Â¶Ã‘Æ’
    // Ã�Â²
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â³Ã�Â¾Ã�Â²Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Âµ
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â³Ã�Â½Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    // Ã�Â¡Ã�Â½Ã�Â°Ã‘â€¡Ã�Â°Ã�Â»Ã�Â°
    // Ã�Â¾Ã‘â€¡Ã�Â¸Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¸Ã�Â½Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°Ã‘â‚¬Ã‘Å’
    // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°.
    public static final int YOU_CANNOT_RESET_THE_SKILL_LINK_BECAUSE_THERE_IS_NOT_ENOUGH_ADENA = 3080; // Ã�Â£
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Âµ
    // Ã‘â€¦Ã�Â²Ã�Â°Ã‘â€šÃ�Â°Ã�ÂµÃ‘â€š
    // Ã�Â°Ã�Â´Ã�ÂµÃ�Â½,
    // Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â±Ã‘â€¹
    // Ã‘ï¿½Ã�Â±Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â¾Ã�Â¹Ã�ÂºÃ�Â¸
    // Ã‘ï¿½Ã‘â‚¬Ã�Â»Ã‘â€¹Ã�ÂºÃ�Â¾Ã�Â²
    // Ã‘Æ’Ã�Â¼Ã�ÂµÃ�Â½Ã�Â¸Ã�Â¹.
    public static final int YOU_CANNOT_RECEIVE_IT_BECAUSE_YOU_ARE_UNDER_THE_CONDITION_THAT_THE_OPPONENT_CANNOT_ACQUIRE_ANY = 3081; // Ã�Å¸Ã�Â°Ã‘â‚¬Ã‘â€šÃ�Â½Ã�ÂµÃ‘â‚¬
    // Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’Ã�Â³Ã�Â¸,
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â¼Ã‘Æ’
    // Ã�ÂºÃ‘Æ’Ã�Â¿Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int YOU_CANNOT_SEND_MAILS_TO_ANY_CHARACTER_THAT_HAS_BLOCKED_YOU = 3082; // Ã�Å¾Ã‘â€šÃ�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â¾Ã‘ï¿½Ã‘â€¹Ã�Â»Ã�ÂºÃ‘Æ’
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã‘Æ’,
    // Ã�ÂºÃ�Â¾Ã‘â€šÃ�Â¾Ã‘â‚¬Ã‘â€¹Ã�Â¹
    // Ã�Â²Ã�Â°Ã‘ï¿½
    // Ã�Â·Ã�Â°Ã�Â±Ã�Â»Ã�Â¾Ã�ÂºÃ�Â¸Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â°Ã�Â»,
    // Ã�Â½Ã�ÂµÃ�Â»Ã‘Å’Ã�Â·Ã‘ï¿½.
    public static final int IN_THE_PROCESS_OF_WORKING_ON_THE_PREVIOUS_CLAN_DECLARATION_RETREAT_PLEASE_TRY_AGAIN_LATER = 3083; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â¸Ã�Â½Ã‘â€žÃ�Â¾Ã‘â‚¬Ã�Â¼Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¸
    // Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘Ë†Ã�Â»Ã�Â¾Ã�Â¹
    // Ã�Â²Ã�Â¾Ã�Â¹Ã�Â½Ã�Âµ
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â¾Ã�Â².
    // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int CURRENTLY_WE_ARE_IN_THE_PROCESS_OF_CHOOSING_A_HERO_PLEASE_TRY_AGAIN_LATER = 3084; // Ã�ËœÃ�Â´Ã�ÂµÃ‘â€š
    // Ã�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°
    // Ã�Â¾
    // Ã�Â²Ã‘â€¹Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Âµ
    // Ã�Â³Ã�ÂµÃ‘â‚¬Ã�Â¾Ã‘ï¿½.
    // Ã�Å¸Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�ÂºÃ‘Æ’
    // Ã�Â¿Ã�Â¾Ã�Â·Ã�Â¶Ã�Âµ.
    public static final int YOU_CAN_SUMMON_THE_PET_YOU_ARE_TRYING_TO_SUMMON_NOW_ONLY_WHEN_YOU_OWN_AN_AGIT = 3085; // Ã�â€™Ã‘â€¹Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã�Â³Ã�Â¾
    // Ã�Å¸Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Â°
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã‘â€šÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸
    // Ã�Â½Ã�Â°Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â¸
    // Ã�Â¥Ã�Â¾Ã�Â»Ã�Â»Ã�Â°
    // Ã�Å¡Ã�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int WOULD_YOU_LIKE_TO_GIVE_S2_S1 = 3086; // Ã�â€™Ã‘â€¹ Ã‘â€¦Ã�Â¾Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â´Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ‘Æ’ $s1
    // $s2?
    public static final int THIS_MAIL_IS_BEING_SENT_WITH_A_PAYMENT_REQUEST_WOULD_YOU_LIKE_TO_CONTINUE = 3087; // Ã�Â­Ã‘â€šÃ�Â¾
    // Ã‘â€žÃ‘Æ’Ã�Â½Ã�ÂºÃ‘â€ Ã�Â¸Ã‘ï¿½
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â½Ã�Â°Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â°
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ‘â€¡Ã�Â¸Ã‘ï¿½Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â´Ã�ÂµÃ�Â½Ã�ÂµÃ�Â³.
    // Ã�â€™Ã‘â€¹
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â½Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â¾Ã�Â»Ã�Â¶Ã�Â¸Ã‘â€šÃ‘Å’?
    public static final int YOU_HAVE_S1_HOURS_S2_MINUTES_AND_S3_SECONDS_LEFT_IN_THE_PROOF_OF_SPACE_AND_TIME_IF_AGATHION_IS = 3088; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¡Ã�Â²Ã�Â¸Ã�Â´Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s1Ã‘â€¡Ã�Â°Ã‘ï¿½.
    // $s2Ã�Â¼Ã�Â¸Ã�Â½.
    // $s3Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°,
    // Ã‘â€šÃ�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.)
    public static final int YOU_HAVE_S1_MINUTES_AND_S2_SECONDS_LEFT_IN_THE_PROOF_OF_SPACE_AND_TIME_IF_AGATHION_IS_SUMMONED = 3089; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¡Ã�Â²Ã�Â¸Ã�Â´Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s1Ã�Â¼Ã�Â¸Ã�Â½.
    // $s2Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°,
    // Ã‘â€šÃ�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.)
    public static final int YOU_HAVE_S1_SECONDS_LEFT_IN_THE_PROOF_OF_SPACE_AND_TIME_IF_AGATHION_IS_SUMMONED_WITHIN_THIS_TIME = 3090; // Ã�â€�Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½
    // Ã�Â¡Ã�Â²Ã�Â¸Ã�Â´Ã�ÂµÃ‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Å¸Ã‘â‚¬Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘â‚¬Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â°
    // Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’
    // $s1Ã‘ï¿½Ã�ÂµÃ�Âº.
    // (Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â²
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¿Ã�Â¾Ã�Â²Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¸Ã�Â·Ã�Â²Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�ï¿½Ã�Â³Ã�Â°Ã‘â€šÃ�Â¸Ã�Â¾Ã�Â½Ã�Â°,
    // Ã‘â€šÃ�Â¾
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â´Ã�Â»Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // 10
    // Ã�Â¼Ã�Â¸Ã�Â½.)
    public static final int YOU_CANNOT_DELETE_CHARACTERS_ON_THIS_SERVER_RIGHT_NOW = 3091; // Ã�â€™
    // Ã�Â½Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã‘â€°Ã�ÂµÃ�Âµ
    // Ã�Â²Ã‘â‚¬Ã�ÂµÃ�Â¼Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã�Â¾Ã�Â¼
    // Ã‘ï¿½Ã�ÂµÃ‘â‚¬Ã�Â²Ã�ÂµÃ‘â‚¬Ã�Âµ
    // Ã‘Æ’Ã�Â´Ã�Â°Ã�Â»Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â¾.
    public static final int TRANSACTION_COMPLETED = 3092; // Ã�Â¡Ã�Â´Ã�ÂµÃ�Â»Ã�ÂºÃ�Â° Ã�Â·Ã�Â°Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�ÂµÃ�Â½Ã�Â°.
    public static final int YOU_ARE_PROTECTED_AGGRESSIVE_MONSTERS = 3108;
    public static final int YOU_ACQUIRED_S1_EXP_AND_S2_SP_AS_A_REWARD_YOU_RECEIVE_S3_MORE_EXP = 6011; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»Ã�Â¸
    // $s1
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°
    // Ã�Â¸
    // $s2
    // SP.
    // (Ã�â€™
    // Ã�Â½Ã�Â°Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â´Ã‘Æ’
    // Ã�Â²Ã‘â€¹
    // Ã�Â´Ã�Â¾Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�Â¸Ã‘â€šÃ�ÂµÃ�Â»Ã‘Å’Ã�Â½Ã�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // $s3
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°.)
    public static final int A_BLESSING_THAT_INCREASES_EXP_BY_1_2 = 6012; // Ã�Å¡Ã�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°
    // Ã‘Æ’Ã�Â²Ã�ÂµÃ�Â»Ã�Â¸Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // $s1
    // $s2
    public static final int IT_IS_NOT_A_BLESSING_PERIOD_WHEN_YOU_REACH_TODAY_S_TARGET_YOU_CAN_RECEIVE_S1 = 6013; // Ã�Â¡Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�ËœÃ�Â²Ã�ÂµÃ�Â½Ã‘â€š
    // Ã�Â½Ã�Âµ
    // Ã�Â¿Ã‘â‚¬Ã�Â¾Ã�Â²Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½.
    // Ã�â€™Ã�Â°Ã�Â¼
    // Ã�Â½Ã�ÂµÃ�Â¾Ã�Â±Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã�Â¼Ã�Â¾
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€¡Ã‘Å’
    // Ã‘ï¿½Ã�ÂµÃ�Â³Ã�Â¾Ã�Â´Ã�Â½Ã‘ï¿½Ã‘Ë†Ã�Â½Ã‘Å½Ã‘Å½
    // Ã‘â€ Ã�ÂµÃ�Â»Ã‘Å’,
    // Ã�Â¸
    // Ã‘â€šÃ�Â¾Ã�Â³Ã�Â´Ã�Â°
    // Ã�Â²Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã�Â½Ã�Â°Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â´Ã‘Æ’:
    // $s1.
    public static final int IT_IS_EVA_S_BLESSING_PERIOD_S1_WILL_BE_EFFECTIVE_UNTIL_S2 = 6014; // Ã�â€�Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â¾
    // Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�â€¢Ã�Â²Ã‘â€¹.
    // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â°Ã�ÂµÃ‘â€šÃ�Âµ
    // $s1
    // Ã�Â´Ã�Â¾
    // $s2.
    public static final int IT_IS_EVA_S_BLESSING_PERIOD_UNTIL_S1_JACK_SAGE_CAN_GIFT_YOU_WITH_S2 = 6015; // Ã�â€�Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�Â½Ã�Â¾
    // Ã�Â±Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�â€¢Ã�Â²Ã‘â€¹.
    // Ã�â€�Ã�Â¾
    // $s1
    // Ã�Â¡Ã‘â€šÃ�Â¸Ã�Â²
    // Ã�Â²Ã‘â€¹Ã�Â´Ã�Â°Ã‘ï¿½Ã‘â€š
    // $s2.
    public static final int PROGRESS__EVENT_STAGE_S1 = 6016; // Ã�â€™Ã‘â€¹Ã�Â¿Ã�Â¾Ã�Â»Ã�Â½Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // ($s1-Ã�Â¹ Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�ËœÃ�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°)
    public static final int EVA_S_BLESSING_STAGE_S1_HAS_BEGUN = 6017; // Ã�ï¿½Ã�Â°Ã‘â€¡Ã�Â¸Ã�Â½Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // $s1-Ã�Â¹
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�â€˜Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�â€¢Ã�Â²Ã‘â€¹.
    public static final int EVA_S_BLESSING_STAGE_S1_HAS_ENDED = 6018; // Ã�â€”Ã�Â°Ã�ÂºÃ�Â°Ã�Â½Ã‘â€¡Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // $s1-Ã�Â¹
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â¸Ã�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�â€˜Ã�Â»Ã�Â°Ã�Â³Ã�Â¾Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�â€¢Ã�Â²Ã‘â€¹.
    public static final int YOU_CANNOT_BUY_THE_ITEM_ON_THIS_DAY_OF_THE_WEEK = 6019; // Ã�â€™
    // Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘â€š
    // Ã�Â´Ã�ÂµÃ�Â½Ã‘Å’
    // Ã�Â½Ã�ÂµÃ�Â´Ã�ÂµÃ�Â»Ã�Â¸
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int YOU_CANNOT_BUY_THE_ITEM_AT_THIS_HOUR = 6020; // Ã�â€™
    // Ã�Â´Ã�Â°Ã�Â½Ã�Â½Ã‘â€¹Ã�Â¹
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´
    // Ã�Â¿Ã�Â¾Ã�ÂºÃ‘Æ’Ã�Â¿Ã�ÂºÃ�Â°
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€šÃ�Â°
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�Â½Ã�Â°.
    public static final int S1_REACHED_S2_CONSECUTIVE_WINS_IN_JACK_GAME = 6021; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $s1
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»
    // $s2
    // Ã‘â‚¬Ã�Â°Ã�Â·
    // Ã�Â²
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Âµ
    // Ã�â€�Ã�Â¶Ã�ÂµÃ�ÂºÃ�Â°.
    public static final int S1_RECEIVED_S4_S3_AS_REWARD_FOR_S2_CONSECUTIVE_WINS = 6022; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $s1
    // Ã�Â¿Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�Â¸Ã�Â»
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â´Ã�Â¼Ã�ÂµÃ‘â€š
    // $s3
    // ($s4
    // Ã‘Ë†Ã‘â€š.)
    // Ã�Â²
    // Ã�Â½Ã�Â°Ã�Â³Ã‘â‚¬Ã�Â°Ã�Â´Ã‘Æ’
    // Ã�Â·Ã�Â°
    // Ã‘â€šÃ�Â¾,
    // Ã‘â€¡Ã‘â€šÃ�Â¾
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´Ã�Â¸Ã�Â»
    // $s2
    // Ã‘â‚¬Ã�Â°Ã�Â·.
    public static final int WORLD__S1_CONSECUTIVE_WINS_S2_PPL = 6023; // Ã�Å“Ã�Â¸Ã‘â‚¬:
    // Ã�Å¸Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´ -
    // $s1
    // ($s2
    // Ã‘â€¡Ã�ÂµÃ�Â».)
    public static final int MY_RECORD__S1_CONSECUTIVE_WINS = 6024; // Ã�â€™Ã‘â€¹: Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´:
    // $s1
    public static final int WORLD__UNDER_4_CONSECUTIVE_WINS = 6025; // Ã�Å“Ã�Â¸Ã‘â‚¬:
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�Âµ 4
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´
    public static final int MY_RECORD__UNDER_4_CONSECUTIVE_WINS = 6026; // Ã�â€™Ã‘â€¹:
    // Ã�Â¼Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�Âµ
    // 4
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´
    public static final int IT_S_HALLOWEEN_EVENT_PERIOD = 6027; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã�Â¸Ã�Â¾Ã�Â´ Ã�ËœÃ�Â²Ã�ÂµÃ�Â½Ã‘â€šÃ�Â°
    // Ã�Â¥Ã�ÂµÃ�Â»Ã�Â»Ã�Â¾Ã‘Æ’Ã�Â¸Ã�Â½Ã�Â°.
    public static final int NO_RECORD_OVER_10_CONSECUTIVE_WINS = 6028; // Ã�ï¿½Ã�ÂµÃ‘â€š
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã�ÂµÃ�Â¹
    // Ã‘ï¿½Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // 10
    // Ã�Â¿Ã�Â¾Ã�Â±Ã�ÂµÃ�Â´.
    public static final int C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS = 3164; // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $c1 Ã�Â½Ã�Âµ
    // Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ‘â€š
    // Ã‘ï¿½Ã�Â¾Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘Ë†Ã�Â°Ã‘â€šÃ‘Å’
    // Ã�Â¿Ã�Â°Ã‘â‚¬Ã�Â½Ã‘â€¹Ã‘â€¦
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¹,
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã�Â½Ã�ÂµÃ�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ‘Æ’Ã�Â¿Ã�ÂµÃ�Â½.

    public static final int YOU_HAVE_EXCEEDED_THE_CORRECT_CALCULATION_RANGE_PLEASE_ENTER_AGAIN = 3093; // Ã�â€™Ã‘â€¹
    // Ã�Â¿Ã‘â‚¬Ã�ÂµÃ�Â²Ã‘â€¹Ã‘ï¿½Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¸Ã‘â€š
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘â€¹.
    // Ã�Å¸Ã�Â¾Ã�Â¶Ã�Â°Ã�Â»Ã‘Æ’Ã�Â¹Ã‘ï¿½Ã‘â€šÃ�Â°,
    // Ã�Â²Ã�Â²Ã�ÂµÃ�Â´Ã�Â¸Ã‘â€šÃ�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â½Ã‘Æ’
    // Ã�ÂµÃ‘â€°Ã�Âµ
    // Ã‘â‚¬Ã�Â°Ã�Â·.
    public static final int PLEASE_HELP_RAISE_REINDEER_FOR_SANTA_S_CHRISTMAS_DELIVERY = 6029; // Ã�Å¸Ã�Â¾Ã�Â·Ã�Â°Ã�Â±Ã�Â¾Ã‘â€šÃ‘Å’Ã‘â€šÃ�ÂµÃ‘ï¿½Ã‘Å’
    // Ã�Â¾
    // Ã�Â Ã‘Æ’Ã�Â´Ã�Â¾Ã�Â»Ã‘Å’Ã‘â€žÃ�Âµ
    // Ã�Â¸
    // Ã�Â²Ã‘â€¹Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â¸Ã‘â€šÃ�Âµ
    // Ã�ÂµÃ�Â³Ã�Â¾!
    // Ã�Å¾Ã�Â½
    // Ã�Â½Ã‘Æ’Ã�Â¶Ã�ÂµÃ�Â½
    // Ã�Â´Ã�Â»Ã‘ï¿½
    // Ã�Â´Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â²Ã�ÂºÃ�Â¸
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾Ã�Â´Ã�Â½Ã�Â¸Ã‘â€¦
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â¾Ã�Â².
    public static final int SANTA_HAS_STARTED_DELIVERING_THE_CHRISTMAS_GIFTS_TO_ADEN = 6030; // Ã�Â¡Ã�ÂµÃ�Â´Ã�Â¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â´Ã‘â€¹Ã�Â¹
    // Ã�â€�Ã�ÂµÃ�Â´
    // Ã�Å“Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â·
    // Ã�Â½Ã�Â°Ã‘â€¡Ã�Â¸Ã�Â½Ã�Â°Ã�ÂµÃ‘â€š
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â²Ã�Â¾Ã�Â·Ã�Â¸Ã‘â€šÃ‘Å’
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾Ã�Â´Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â¸
    // Ã�Â¿Ã�Â¾
    // Ã�Â¼Ã�Â¸Ã‘â‚¬Ã‘Æ’
    // Ã�ï¿½Ã�Â´Ã�ÂµÃ�Â½.
    public static final int SANTA_HAS_COMPLETED_THE_DELIVERIES_SEE_YOU_IN_1_HOUR = 6031; // Ã�Â¡Ã�ÂµÃ�Â´Ã�Â¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â´Ã‘â€¹Ã�Â¹
    // Ã�â€�Ã�ÂµÃ�Â´
    // Ã�Å“Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â·
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â²Ã�ÂµÃ�Â·
    // Ã�Â²Ã‘ï¿½Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â¸.
    // Ã�Â£Ã�Â²Ã�Â¸Ã�Â´Ã�ÂµÃ�Â¼Ã‘ï¿½Ã‘ï¿½
    // Ã‘â€¡Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â·
    // 1
    // Ã‘â€¡Ã�Â°Ã‘ï¿½.
    public static final int SANTA_IS_OUT_DELIVERING_THE_GIFTS_MERRY_CHRISTMAS = 6032; // Ã�Â¡Ã�ÂµÃ�Â´Ã�Â¾Ã�Â±Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â´Ã‘â€¹Ã�Â¹
    // Ã�â€�Ã�ÂµÃ�Â´
    // Ã�Å“Ã�Â¾Ã‘â‚¬Ã�Â¾Ã�Â·
    // Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â²Ã�Â¾Ã�Â·Ã�Â¸Ã‘â€š
    // Ã�Â½Ã�Â¾Ã�Â²Ã�Â¾Ã�Â³Ã�Â¾Ã�Â´Ã�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¿Ã�Â¾Ã�Â´Ã�Â°Ã‘â‚¬Ã�ÂºÃ�Â¸.
    // Ã�Â¡
    // Ã�ï¿½Ã�Â¾Ã�Â²Ã‘â€¹Ã�Â¼
    // Ã�Â³Ã�Â¾Ã�Â´Ã�Â¾Ã�Â¼!
    public static final int ONLY_THE_TOP_S1_APPEAR_IN_THE_RANKING_AND_ONLY_THE_TOP_S2_ARE_RECORDED_IN_MY_BEST_RANKING = 6033; // Ã�â€™
    // Ã‘â‚¬Ã�ÂµÃ�Â¹Ã‘â€šÃ�Â¸Ã�Â½Ã�Â³Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â¶Ã�Â°Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â´Ã�Â¾
    // $s1
    // Ã‘â€¡Ã�ÂµÃ�Â».,
    // Ã�Â°
    // Ã‘ï¿½Ã�Â°Ã�Â¼Ã‘â€¹Ã�Âµ
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�Â²Ã‘â€¹Ã�Âµ
    // $s2
    // Ã‘â€¡Ã�ÂµÃ�Â».
    // Ã�Â·Ã�Â°Ã�Â¿Ã�Â¸Ã‘ï¿½Ã‘â€¹Ã�Â²Ã�Â°Ã‘Å½Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Å“Ã�Â¾Ã�Â¹
    // Ã�Â²Ã‘â€¹Ã‘ï¿½Ã‘Ë†Ã�Â¸Ã�Â¹
    // Ã‘â‚¬Ã�ÂµÃ�Â¹Ã‘â€šÃ�Â¸Ã�Â½Ã�Â³.
    public static final int S1_HAVE_HAS_BEEN_INITIALIZED = 6034; // $s1
    // Ã�Â¾Ã�Â±Ã�Â½Ã‘Æ’Ã�Â»Ã�Â¸Ã�Â»Ã�Â¾Ã‘ï¿½Ã‘Å’.
    public static final int WHEN_THERE_ARE_MANY_PLAYERS_WITH_THE_SAME_SCORE_THEY_APPEAR_IN_THE_ORDER_IN_WHICH_THEY_WERE = 6035; // Ã�â€¢Ã‘ï¿½Ã�Â»Ã�Â¸
    // Ã�Â¾Ã�Â´Ã�Â¸Ã�Â½Ã�Â°Ã�ÂºÃ�Â¾Ã�Â²Ã�Â¾Ã�Âµ
    // Ã�ÂºÃ�Â¾Ã�Â»Ã�Â¸Ã‘â€¡Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â²Ã�Â¾
    // Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²
    // Ã�Â½Ã�Â°Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»Ã�Â¾
    // Ã�Â½Ã�ÂµÃ‘ï¿½Ã�ÂºÃ�Â¾Ã�Â»Ã‘Å’Ã�ÂºÃ�Â¾
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶Ã�ÂµÃ�Â¹,
    // Ã‘â€šÃ�Â¾
    // Ã�Â²Ã‘â€¹Ã‘Ë†Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¾Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â·Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã‘â€šÃ�Â¾Ã‘â€š,
    // Ã�ÂºÃ‘â€šÃ�Â¾
    // Ã�Â½Ã�Â°Ã�Â±Ã‘â‚¬Ã�Â°Ã�Â»
    // Ã�Â¸Ã‘â€¦
    // Ã‘â‚¬Ã�Â°Ã�Â½Ã‘Å’Ã‘Ë†Ã�Âµ.
    public static final int BELOW_S1_POINTS = 6036; // Ã�Å“Ã�ÂµÃ�Â½Ã‘Å’Ã‘Ë†Ã�Âµ $s1 Ã�Â¾Ã‘â€¡Ã�ÂºÃ�Â¾Ã�Â²

    public static final int YOU_CANCEL_FOR_COUPLE_ACTION = 3119; // Ã�â€™Ã‘â€¹
    // Ã�Â¾Ã‘â€šÃ�ÂºÃ�Â°Ã�Â·Ã�Â°Ã�Â»Ã�Â¸Ã‘ï¿½Ã‘Å’
    // Ã�Â¾Ã‘â€š Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â³Ã�Â¾
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã‘ï¿½.
    public static final int REQUEST_CANNOT_COMPLETED_BECAUSE_TARGET_DOES_NOT_MEET_LOCATION_REQUIREMENTS = 3120; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // Ã�Å“Ã�ÂµÃ‘ï¿½Ã‘â€šÃ�Â¾Ã‘â‚¬Ã�Â°Ã‘ï¿½Ã�Â¿Ã�Â¾Ã�Â»Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã‘â€ Ã�ÂµÃ�Â»Ã�Â¸
    // Ã�Â½Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â¾Ã‘â€šÃ�Â²Ã�ÂµÃ‘â€šÃ‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã‘Æ’Ã‘ï¿½Ã�Â»Ã�Â¾Ã�Â²Ã�Â¸Ã‘ï¿½Ã�Â¼.
    public static final int COUPLE_ACTION_WAS_CANCELED = 3121; // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã‘â€šÃ�Â¼Ã�ÂµÃ�Â½Ã�ÂµÃ�Â½Ã�Â¾.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IN_PRIVATE_STORE = 3123; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€š
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â»Ã�Â°Ã�Â²Ã�ÂºÃ�Â¾Ã�Â¹
    // Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â»Ã�Â¸Ã‘â€¡Ã�Â½Ã�Â¾Ã�Â¹
    // Ã�Â¼Ã�Â°Ã‘ï¿½Ã‘â€šÃ�ÂµÃ‘â‚¬Ã‘ï¿½Ã�ÂºÃ�Â¾Ã�Â¹.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_FISHING = 3124; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã�Â·Ã�Â°Ã�Â½Ã‘ï¿½Ã‘â€š
    // Ã�Â»Ã�Â¾Ã�Â²Ã�Â»Ã�ÂµÃ�Â¹
    // Ã‘â‚¬Ã‘â€¹Ã�Â±Ã‘â€¹.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_IN_COMBAT = 3125; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã�Â±Ã�Â¾Ã‘Å½.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IN_ANOTHER_COUPLE_ACTION = 3126; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã‘Æ’Ã�Â¶Ã�Âµ
    // Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�Â´Ã‘â‚¬Ã‘Æ’Ã�Â³Ã�Â¾Ã�Âµ
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_CURSED_WEAPON_EQUIPED = 3127; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã�Â½Ã�Â°Ã‘â€¦Ã�Â¾Ã�Â´Ã�Â¸Ã‘â€šÃ‘ï¿½Ã‘ï¿½
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¥Ã�Â°Ã�Â¾Ã‘ï¿½Ã�Â°.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_IN_OLYMPIAD = 3128; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Å¾Ã�Â»Ã�Â¸Ã�Â¼Ã�Â¿Ã�Â¸Ã�Â°Ã�Â´Ã�Âµ.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_IN_HIDEOUT_SIEGE = 3129; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â±Ã�Â¸Ã‘â€šÃ�Â²Ã�Âµ
    // Ã�Â·Ã�Â°
    // Ã‘â€¦Ã�Â¾Ã�Â»Ã�Â»
    // Ã�ÂºÃ�Â»Ã�Â°Ã�Â½Ã�Â°.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_IN_SIEGE = 3130; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã‘Æ’Ã‘â€¡Ã�Â°Ã‘ï¿½Ã‘â€šÃ�Â²Ã‘Æ’Ã�ÂµÃ‘â€š
    // Ã�Â²
    // Ã�Â¾Ã‘ï¿½Ã�Â°Ã�Â´Ã�Âµ
    // Ã�Â·Ã�Â°Ã�Â¼Ã�ÂºÃ�Â°.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_IN_VEHICLE_MOUNT_OTHER = 3131; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã‘ï¿½Ã�ÂµÃ�Â¹Ã‘â€¡Ã�Â°Ã‘ï¿½
    // Ã�Â½Ã�Â°
    // Ã�ÂºÃ�Â¾Ã‘â‚¬Ã�Â°Ã�Â±Ã�Â»Ã�Âµ,
    // Ã�Â²Ã�ÂµÃ‘â‚¬Ã‘â€¦Ã�Â¾Ã�Â¼
    // Ã�Â½Ã�Â°
    // Ã�Â¿Ã�Â¸Ã‘â€šÃ�Â¾Ã�Â¼Ã‘â€ Ã�Âµ
    // Ã�Â¸
    // Ã‘â€š.Ã�Â¿..
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_TELEPORTING = 3132; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã‘â€šÃ�ÂµÃ�Â»Ã�ÂµÃ�Â¿Ã�Â¾Ã‘â‚¬Ã‘â€šÃ�Â°Ã‘â€ Ã�Â¸Ã�Â¸.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_IN_TRANSFORM = 3133; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // $c1
    // Ã�Â²
    // Ã‘ï¿½Ã�Â¾Ã‘ï¿½Ã‘â€šÃ�Â¾Ã‘ï¿½Ã�Â½Ã�Â¸Ã�Â¸
    // Ã�Â¿Ã�ÂµÃ‘â‚¬Ã�ÂµÃ�Â²Ã�Â¾Ã�Â¿Ã�Â»Ã�Â¾Ã‘â€°Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½.
    public static final int COUPLE_ACTION_CANNOT_C1_TARGET_IS_DEAD = 3139; // Ã�â€”Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½
    // Ã�Â¾
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Â¼
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Â¸
    // Ã�Â½Ã�ÂµÃ�Â²Ã�Â¾Ã�Â·Ã�Â¼Ã�Â¾Ã�Â¶Ã�ÂµÃ�Â½.
    // Ã�Å¸Ã�ÂµÃ‘â‚¬Ã‘ï¿½Ã�Â¾Ã�Â½Ã�Â°Ã�Â¶
    // $c1
    // Ã�Â¼Ã�ÂµÃ‘â‚¬Ã‘â€šÃ�Â².
    public static final int YOU_ASK_FOR_COUPLE_ACTION_C1 = 3150; // Ã�â€™Ã‘â€¹ Ã�Â½Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â°Ã�Â²Ã�Â¸Ã�Â»Ã�Â¸
    // Ã�Â·Ã�Â°Ã�Â¿Ã‘â‚¬Ã�Â¾Ã‘ï¿½ Ã�Â½Ã�Â°
    // Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ
    // Ã�Â¸Ã�Â³Ã‘â‚¬Ã�Â¾Ã�ÂºÃ‘Æ’ $c1.
    public static final int C1_ACCEPTED_COUPLE_ACTION = 3151; // $c1 Ã‘ï¿½Ã�Â¾Ã�Â³Ã�Â»Ã�Â°Ã‘ï¿½Ã�Â¸Ã�Â»Ã‘ï¿½Ã‘ï¿½
    // Ã�Â½Ã�Â° Ã�Å¸Ã�Â°Ã‘â‚¬Ã�Â½Ã�Â¾Ã�Âµ
    // Ã�Â´Ã�ÂµÃ�Â¹Ã‘ï¿½Ã‘â€šÃ�Â²Ã�Â¸Ã�Âµ.
    public static final int REQUESTING_APPROVAL_CHANGE_PARTY_LOOT_S1 = 3135; // Requesting
    // approval
    // for
    // changing
    // party
    // loot
    // to
    // "$s1".
    public static final int PARTY_LOOT_CHANGE_CANCELLED = 3137; // Party loot
    // change was
    // cancelled.
    public static final int PARTY_LOOT_CHANGED_S1 = 3138; // Party loot was
    // changed to "$s1".
    public static final int YOU_OBTAINED_S1_RECOMMENDS = 3207; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾
    // Ã‘â‚¬Ã�ÂµÃ�ÂºÃ�Â¾Ã�Â¼Ã�ÂµÃ�Â½Ã�Â´Ã�Â°Ã‘â€ Ã�Â¸Ã�Â¹:
    // "$s1:.
    public static final int CURRENT_LOCATION__S1_S2_S3_INSIDE_SEED_OF_ANNIHILATION = 3170; // Ã�Â¢Ã�ÂµÃ�ÂºÃ‘Æ’Ã‘â€°Ã�Â°Ã‘ï¿½
    // Ã�Â»Ã�Â¾Ã�ÂºÃ�Â°Ã‘â€ Ã�Â¸Ã‘ï¿½:
    // $s1,
    // $s2,
    // $s3
    // (Ã�â€™Ã�Â½Ã‘Æ’Ã‘â€šÃ‘â‚¬Ã�Â¸
    // Ã�Â¡Ã�ÂµÃ�Â¼Ã�ÂµÃ�Â½Ã�Â¸
    // Ã�Â£Ã�Â½Ã�Â¸Ã‘â€¡Ã‘â€šÃ�Â¾Ã�Â¶Ã�ÂµÃ�Â½Ã�Â¸Ã‘ï¿½)
    public static final int YOU_HAVE_EARNED_S1_B_S2_EXP_AND_S3_B_S4_SP = 3259; // Ã�Å¸Ã�Â¾Ã�Â»Ã‘Æ’Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¾

    // Ã�Â·Ã�Â½Ã�Â°Ã‘â€¡Ã�ÂµÃ�Â½Ã�Â¸Ã�Âµ
    // Ã�Â¾Ã�Â¿Ã‘â€¹Ã‘â€šÃ�Â°
    // $s1
    // (Ã�â€˜Ã�Â¾Ã�Â½Ã‘Æ’Ã‘ï¿½:
    // $s2)
    // Ã�Â¸
    // SP
    // $s3
    // (Ã�â€˜Ã�Â¾Ã�Â½Ã‘Æ’Ã‘ï¿½:
    // $s4).
    
    public static final int YOU_CANNOT_MOVE_WHILE_IN_A_CHAOTIC_STATE = 3404;
    public static final int YOU_CANNOT_MOVE_WHILE_DEAD = 3392;
    public static final int YOU_CANNOT_MOVE_DURING_COMBAT = 3393;
    public static final int SUBCLASS_S1_HAS_BEEN_UPGRADED_TO_DUAL_CLASS_S2_CONGRATULATION = 3279;

    public SystemMessage(SystemMsg msg) {
        _messageId = msg.getId();
    }

    public SystemMessage(int messageId) {
        _messageId = messageId;
    }

    public SystemMessage(String msg) {
        this(S1);
        addString(msg);
    }

    public static SystemMessage obtainItemsByMail(ItemInstance item) {
        return new SystemMessage(SystemMessage.YOU_HAVE_ACQUIRED_S2_S1).addItemName(item.getItemId()).addNumber(
                                                                                                                       item.getCount());
    }

    public SystemMessage addString(String text) {
        args.add(new Arg(TYPE_TEXT, StringUtils.defaultString(text)));
        return this;
    }

    public SystemMessage addNumber(int number) {
        args.add(new Arg(TYPE_NUMBER, number));
        return this;
    }

    public SystemMessage addNumber(long number) {
        args.add(new Arg(TYPE_LONG, number));
        return this;
    }

    /**
     * Ã�Â£Ã‘ï¿½Ã‘â€šÃ�Â°Ã�Â½Ã�Â°Ã�Â²Ã�Â»Ã�Â¸Ã�Â²Ã�Â°Ã�ÂµÃ‘â€š Ã�Â¸Ã�Â¼Ã‘ï¿½ Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸ Ã‘ï¿½Ã‘â€šÃ�Â¾ playable Ã�Â¸Ã�Â»Ã�Â¸ id Ã�ÂµÃ‘ï¿½Ã�Â»Ã�Â¸ Ã‘ï¿½Ã‘â€šÃ�Â¾ npc
     */
    public SystemMessage addName(Creature cha) {
        if (cha == null)
            return addString(StringUtils.EMPTY);

        if (cha.isDoor())
            return addDoorName(((DoorInstance) cha).getDoorId());

        if (cha.getNpcId() <= 0)
            return addString(cha.getName());

        return addNpcName(cha.getNpcId());
    }

    public SystemMessage addDoorName(int id) {
        args.add(new Arg(TYPE_DOOR_NAME, new Integer(id)));
        return this;
    }

    public SystemMessage addNpcName(int id) {
        args.add(new Arg(TYPE_NPC_NAME, new Integer(1000000 + id)));
        return this;
    }

    public SystemMessage addItemName(int id) {
        args.add(new Arg(TYPE_ITEM_NAME, id));
        return this;
    }

    public SystemMessage addZoneName(Location loc) {
        args.add(new Arg(TYPE_ZONE_NAME, loc));
        return this;
    }

    public SystemMessage addSkillName(int id, int level) {
        args.add(new Arg(TYPE_SKILL_NAME, new int[]{id, level}));
        return this;
    }

    public SystemMessage addClassName(int id) {
        args.add(new Arg(TYPE_CLASS_NAME, id));
        return this;
    }

    /**
     * Elemental name - 0(Fire) ...
     *
     * @param type
     * @return
     */
    public SystemMessage addElemntal(int type) {
        args.add(new Arg(TYPE_ELEMENT_NAME, type));
        return this;
    }

    /**
     * ID from sysstring-e.dat
     *
     * @param type
     * @return
     */
    public SystemMessage addSystemString(int type) {
        args.add(new Arg(TYPE_SYSTEM_STRING, type));
        return this;
    }

    /**
     * Instance name from instantzonedata-e.dat
     *
     * @param type id of instance
     * @return
     */
    public SystemMessage addInstanceName(int type) {
        args.add(new Arg(TYPE_INSTANCE_NAME, type));
        return this;
    }

    /**
     * Castlename-e.dat<br> 0-9 Castle names<br> 21-64 CH names<br> 81-89 Territory names<br> 101-121 Fortress names<br>
     *
     * @param number
     * @return
     */
    public SystemMessage addFortId(int number) {
        args.add(new Arg(TYPE_CASTLE_NAME, number));
        return this;
    }

    public SystemMessage addDamage(Creature targetid, Creature attakerid, int damage) {
        args.add(new Arg(TYPE_DAMAGE, new int[]{targetid.getObjectId(), attakerid.getObjectId(), damage}));
        return this;
    }

    @Override
    protected final void writeImpl() {
        Player activeChar = getClient().getActiveChar();
        if (activeChar == null)
            return;

        writeC(0x62);

        writeD(_messageId);
        writeD(args.size());
        for (Arg e : args) {
            writeD(e.type);

            switch (e.type) {
                case TYPE_TEXT:
                case TYPE_PLAYER_NAME: {
                    writeS((String) e.obj);
                    break;
                }
                case TYPE_NUMBER:
                case TYPE_NPC_NAME:
                case TYPE_ITEM_NAME:
                case TYPE_CASTLE_NAME:
                case TYPE_ELEMENT_NAME:
                case TYPE_SYSTEM_STRING:
                case TYPE_INSTANCE_NAME:
                case TYPE_DOOR_NAME:
                case TYPE_CLASS_NAME: {
                    writeD(((Number) e.obj).intValue());
                    break;
                }
                case TYPE_SKILL_NAME: {
                    int[] skill = (int[]) e.obj;
                    writeD(skill[0]); // id
                    writeD(skill[1]); // level
                    break;
                }
                case TYPE_LONG: {
                    writeQ((Long) e.obj);
                    break;
                }
                case TYPE_ZONE_NAME: {
                    Location coord = (Location) e.obj;
                    writeD(coord.x);
                    writeD(coord.y);
                    writeD(coord.z);
                    break;
                }
                case TYPE_UNKNOWN_8: {
                    writeD(0x00); // ?
                    writeH(0x00); // ?
                    writeH(0x00); // ?
                    break;
                }
                case TYPE_DAMAGE: {
                    int[] attr = (int[]) e.obj;
                    writeD(attr[0]); // target object id
                    writeD(attr[1]); // attaker object id
                    writeD(attr[2]); // damage
                    break;
                }
            }
        }
    }

    private class Arg {
        public final int type;
        public final Object obj;

        private Arg(int _type, Object _obj) {
            type = _type;
            obj = _obj;
        }
    }
}
