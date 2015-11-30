package models

import java.io.File

    import org.joda.time.LocalDate
import play.api.data._
import play.api.data.Forms._

/**
 * Created by AdamT on 28/11/2015.
 */
case class TestForm(textInput: String,
                    optionalTextInput: Option[String],
                    numberInput: Int,
                    selectInput: String,
                    radioGroupInput: String,
                    dateInput: LocalDate,
                    passwordInput: String,
                    confirmPasswordInput: String,
                    checkboxInput: Boolean)

object TestForm {

  val form = Form(
    mapping(
      "textInput" -> nonEmptyText,
      "optionalTextInput" -> optional(text),
      "numberInput" -> number,
      "selectInput" -> text.verifying("Please select an option!", i => !i.equals("-- select --")),
      "radioGroupInput" -> text,
      "dateInput" -> jodaLocalDate.verifying("Date cant be in the future!", d => d.isBefore(LocalDate.now.plusDays(1))),
      "passwordInput" -> text(8, 30),
      "confirmPasswordInput" -> text(8, 30),
      "checkboxInput" -> boolean.verifying("Must click this checkbox", c => c.equals(true))
    )(TestForm.apply)(TestForm.unapply) verifying("Passwords must match!", fields => fields.passwordInput.equals(fields.confirmPasswordInput))
    //match {
     // case testForm => testForm.passwordInput.equals(testForm.confirmPasswordInput)
    //})
  )

}

