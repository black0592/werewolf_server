package com.zinglabs.zwerewolf.util;

import com.zinglabs.zwerewolf.config.Config;
import com.zinglabs.zwerewolf.entity.role.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/7/29 12:15
 */
public class GameUtil {

    public static int getNumByModal(int modalId) {
        int number;
        switch (modalId) {
            case Config.MODEL_9:
                number = 9;
                break;
            case Config.MODEL_10:
                number = 10;
                break;
            case Config.MODEL_12_YNLB:
                number = 12;
                break;
            case Config.MODEL_12_YNLS:
                number = 12;
                break;
            default:
                number = 12;
                break;
        }
        return number;
    }

    public static List<Integer> getRolesByModalId(int modalId) {
        List<Integer> roles = new ArrayList<>();
        List<Integer> retRoles = new ArrayList<>();

        roles.addAll(Arrays.asList(Config.ROLE_CODE_OF_VILLAGER, Config.ROLE_CODE_OF_VILLAGER,
                Config.ROLE_CODE_OF_VILLAGER));  //3个村民

        roles.addAll(Arrays.asList(Config.ROLE_CODE_OF_WOLF, Config.ROLE_CODE_OF_WOLF,
                Config.ROLE_CODE_OF_WOLF));   //3个狼

        roles.add(Config.ROLE_CODE_OF_HUNTSMAN);
        roles.add(Config.ROLE_CODE_OF_PROPHET);
        roles.add(Config.ROLE_CODE_OF_WITCH);   //3个神

        switch (modalId) {

            case Config.MODEL_9:
                retRoles = roles;
                break;
            case Config.MODEL_10:
                roles.add(Config.ROLE_CODE_OF_VILLAGER);
                retRoles = roles;
                break;
            case Config.MODEL_12_YNLB:
                roles.add(Config.ROLE_CODE_OF_VILLAGER);
                roles.add(Config.ROLE_CODE_OF_GUARD);
                roles.add(Config.ROLE_CODE_OF_WOLF);
                retRoles = roles;
                break;
            case Config.MODEL_12_YNLS:
                roles.add(Config.ROLE_CODE_OF_VILLAGER);
                roles.add(Config.ROLE_CODE_OF_IDIOT);
                roles.add(Config.ROLE_CODE_OF_WOLF);
                retRoles = roles;
                break;
        }
        return retRoles;


    }

    public static Role getRole(int roleCode) {
        Role role = null;
        switch (roleCode) {
            case Config.ROLE_CODE_OF_VILLAGER:
                role = new Villager();
                break;
            case Config.ROLE_CODE_OF_GUARD:
                role = new Guard();
                break;

            case Config.ROLE_CODE_OF_HUNTSMAN:
                role = new Huntsman();
                break;

            case Config.ROLE_CODE_OF_IDIOT:
                role = new Idiot();
                break;

            case Config.ROLE_CODE_OF_PROPHET:
                role = new Prophet();
                break;

            case Config.ROLE_CODE_OF_WITCH:
                role = new Witch();
                break;

            case Config.ROLE_CODE_OF_WOLF:
                role = new Wolf();
                break;

        }
        return role;

    }

    public static Role getRoleFromRoleMap(Map<Integer, UserRole> players, int roleId) {
        Role role = null;
        for (UserRole ur : players.values()) {
            if (ur.getRoleId() == roleId) {
                role = ur.getRole();
            }
        }
        return role;
    }

    public static boolean verify(int roleId) {
        return roleId != Config.ROLE_CODE_OF_WOLF;

    }

    /**
     * 比较输赢
     * @param players 玩家礼盒
     * @return 0表示游戏未结束；1表示好人获胜；-1表示狼人获胜
     */
    public static int isGameOver(Map<Integer, UserRole> players) {
        int isOver = Config.GAME_STATUS_PROCESS;
        int vill_num =0;
        int wolf_num =0;
        int god_num = 0;
        for(UserRole ur:players.values()){
            int roleId = ur.getRoleId();
            if(roleId==Config.ROLE_CODE_OF_WOLF){
                wolf_num++;
            }else if(roleId==Config.ROLE_CODE_OF_VILLAGER){
                vill_num++;
            }else{
                god_num++;
            }
        }
        if(wolf_num==0){
            return Config.GAME_STATUS_OVER_GOOD;
        }else if(vill_num==0||god_num==0){
            return Config.GAME_STATUS_OVER_WOLF;
        }
        return isOver;
    }
}
