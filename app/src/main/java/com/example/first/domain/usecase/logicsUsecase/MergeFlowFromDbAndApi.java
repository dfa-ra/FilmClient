package com.example.first.domain.usecase.logicsUsecase;

import com.example.first.domain.models.ShortFilmModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MergeFlowFromDbAndApi {

    public Flowable<List<ShortFilmModel>> execute(Flowable<List<ShortFilmModel>> flowFromDB, Single<List<ShortFilmModel>> flowFromApi){
        return Flowable.zip(
                        flowFromDB,
                        flowFromApi.toFlowable(),
                        (list1, list2) -> {
                            List<ShortFilmModel> result = new ArrayList<>(list1);
                            result.addAll(list2);
                            return result;
                        }
                )
                .distinctUntilChanged((list1, list2) -> {
                    return list1.containsAll(list2) || list2.containsAll(list1);
                });
    }
}
