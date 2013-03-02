/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import models.UserInfo;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import util.UserType;

/**
 *
 * @author lin
 */
@OnApplicationStart
public class BootStrap extends Job{

    @Override
    public void doJob() throws Exception {
        if(UserInfo.count() == 0){
            UserInfo user  = new UserInfo();
            user.email = "admin";
            user.password = "admin";
            user.userType = UserType.Admin;
            user.save();

        }
    }
}
