package controllers


import models.TestForm
import play.api.mvc.{Action, Controller}


/**
 * Created by AdamT on 28/11/2015.
 */
trait FormsController extends Controller {

    def view = Action {
      Ok(views.html.forms.forms(TestForm.form))
    }

  def submit = Action { implicit request =>
    TestForm.form.bindFromRequest.fold(
      errorForm => {
        BadRequest(views.html.forms.forms(errorForm))
      },
      formData => {
        val results = List(
          s"textInput: ${formData.textInput}",
          s"optionalTextInput: ${formData.optionalTextInput}",
          s"numberInput: ${formData.numberInput}",
          s"selectInput: ${formData.selectInput}",
          s"radioInput: ${formData.radioGroupInput}",
          s"dateInput: ${formData.dateInput}",
          s"passwordInput: ${formData.passwordInput}",
          s"confirmPasswordInput: ${formData.confirmPasswordInput}",
          s"checkboxInput: ${formData.checkboxInput}"
        )
        Redirect(routes.FormsController.formResults(results))
      }
    )
  }

  def formResults(filledForm: List[String]) = Action {
    Ok(views.html.forms.formResults(filledForm))
  }

}

object FormsController extends FormsController
