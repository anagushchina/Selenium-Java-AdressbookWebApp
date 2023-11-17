package ca.stqa.mantis.manager.developerMail;

import ca.stqa.mantis.model.DeveloperMailUser;

public record AddUserResponse(Boolean success, Object errors, DeveloperMailUser result) {
}
