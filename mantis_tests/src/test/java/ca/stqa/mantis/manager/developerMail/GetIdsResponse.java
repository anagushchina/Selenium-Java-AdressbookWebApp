package ca.stqa.mantis.manager.developerMail;

import java.util.List;

public record GetIdsResponse(Boolean success, Object errors, List<String> result) {
}
