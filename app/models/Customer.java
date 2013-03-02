/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import play.db.jpa.Model;

/**
 *
 * @author lin
 */
@Entity
public class Customer extends Model{
    public String name;
    @OneToOne(fetch=FetchType.EAGER)
    public UserInfo info;
    public int creditPoint ;

		//public String toString(){
			//return name;
		//}
}
