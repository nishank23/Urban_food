package com.example.urban_food.Activites.SearchScreen;

import com.example.urban_food.model.Search;

import java.util.List;

public interface SearchActivityView {

    void onSuccessSearch(Search response);

    void onError(String msg);

    void ShowProgress();

    void dismissProgress();

}
