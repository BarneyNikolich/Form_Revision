package controllers

import play.api.mvc._

trait Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

}

object Application extends Application
