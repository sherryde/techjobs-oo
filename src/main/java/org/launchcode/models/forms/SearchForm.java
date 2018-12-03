package org.launchcode.models.forms;

import org.launchcode.models.JobFieldType;

/**
 * Created by LaunchCode
 */
public class SearchForm {

    // The search options
    private JobFieldType[] fields = JobFieldType.values();

    // The selected search options
    private JobFieldType searchField = JobFieldType.ALL;

    // The search string
    private String keyword;

    public JobFieldType getSearchField() {
        return searchField;
    }

    public void setSearchField(JobFieldType searchField) {
        this.searchField = searchField;
    }

    public JobFieldType[] getFields() {
        return fields;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

/** Notes: https://education.launchcode.org/skills-back-end-java/assignments/techjobs-oo/
 * These fields represent the data associated with the search form. In other words,
 * each is necessary to display and process the form.
 * If you look within SearchController,
 * you'll see that we use model binding with a SearchForm object to process the form,
 * and we pass a SearchForm object into the view to display the form.

 We do this because there isn't a natural model object to use with the search form,
 but we'd like to have the benefits of model binding and validation.
 * */