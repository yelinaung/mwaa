/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import play.db.jpa.Model;
import util.UserType;


/**
 *
 * @author lin
 */
@Entity
public class UserInfo extends Model{
    public String email;
    public String password;
    public String name;
    public String address;

    public UserType userType;
}
