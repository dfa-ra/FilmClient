package com.example.first.domain.usecase.logicsUsecase;

import android.util.Log;

import com.example.first.domain.models.ShortFilmModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MergeFlowFromDbAndApi {

    public Flowable<List<ShortFilmModel>> execute(Flowable<List<ShortFilmModel>> flowFromDB, Single<List<ShortFilmModel>> flowFromApi){
        return Flowable.zip(
                        flowFromApi.toFlowable(),
                        flowFromDB,
                        (list1, list2) -> {
                            Log.d("aa44", list1.toString());
                            Log.d("aa33", list2.toString());
                            List<ShortFilmModel> mylist1 = new ArrayList<>(list1);
                            List<ShortFilmModel> mylist2 = new ArrayList<>(list2);
                            List<ShortFilmModel> result = new ArrayList<>();
                            boolean flag = true;
                            for (ShortFilmModel resmodel: mylist1) {
                                flag = true;
                                for (ShortFilmModel model : mylist2) {
                                    if (resmodel.kinopoiskId == model.kinopoiskId) {
                                        flag = false;
                                        result.add(model);
                                        break;
                                    }
                                }
                                if (flag) result.add(resmodel);
                            }
                            return result;
                        }
                );
    }
}
