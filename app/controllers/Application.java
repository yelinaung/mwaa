package controllers;


import play.mvc.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
		
		public static void about() {
				render("Application/about.html");
		}
  
}