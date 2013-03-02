/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 *
 * @author lin
 */
@Entity
public class PrepaidCode extends Model{
    public String codeNo;
    public String usedEmail;
}
