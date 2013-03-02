/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import models.UserInfo;
import util.UserType;

/**
 *
 * @author lin
 */
public class Security extends Secure.Security{

    static boolean authenticate(String email,String password){

        UserInfo userInfo = UserInfo.find("byEmailAndPassword", email, password).first();
        return userInfo!=null;
    }

    static boolean  check(String profile){
	

        UserInfo user = UserInfo.find("byEmail", connected()).first();
        if(user == null){
            return false;
        }
        if("Admin".equals(profile)){
            return user.userType == UserType.Admin;
        }
        if("Customer".equals(profile)){
            return user.userType == UserType.User;
        }

        if("Astrologer".equals(profile)){
            return user.userType == UserType.Astrologer;
        }
        return false;

    }
}
