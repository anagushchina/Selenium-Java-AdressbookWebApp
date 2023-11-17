package ca.stqa.mantis.model;

public record IssueData(String summary, String description, Long project, Long category) {

    public IssueData() {
        this("", "", 0L, 1L);
    }

    public IssueData withSummary(String summary){
        return new IssueData(summary, description, project, category);
    }

    public IssueData withDescription(String description){
        return new IssueData(summary, description, project, category);
    }

    public IssueData withProject(Long project){
        return new IssueData(summary, description, project, category);
    }

    public IssueData withCategory(Long category){
        return new IssueData(summary, description, project, category);
    }




}


