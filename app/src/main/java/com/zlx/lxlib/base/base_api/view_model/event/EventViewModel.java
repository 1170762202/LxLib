package com.zlx.lxlib.base.base_api.view_model.event;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class EventViewModel extends ViewModel {
    private MutableLiveData<EventModel> liveData;

    public EventViewModel() {
        liveData = new MutableLiveData<>();
    }

    public void post(EventModel eventModel) {
        liveData.postValue(eventModel);
    }

    public LiveData<EventModel> get() {
        return liveData;
    }
}
