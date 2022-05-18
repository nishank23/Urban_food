package com.example.urban_food.Activities.SearchScreen;

import com.example.urban_food.model.Search;

public interface SearchActivityView {

    void onSuccessSearch(Search response);

    void onError(String msg);

    void ShowProgress();

    void dismissProgress();

}
