package com.example.carmarket;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.carmarket.repository.CarRepository;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<CarItem> mData;

    public GridAdapter(List<CarItem> data) {
        this.mData = data;
    }
    @Override
    public int getCount() {
//        return CarRepository.getAllCars().size();
        return mData.size();
    }

    @Override
    public CarItem getItem(int i) {
//        List<CarItem> cars = CarRepository.getAllCars();
//        return cars.get(i);
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.grid_item, null);
        }

        CarItem item = getItem(i);
        ImageView ivItem = view.findViewById(R.id.iv_item);
        TextView tvTittle = view.findViewById(R.id.tv_tittle_text);

        ivItem.setImageResource(item.getThumbnailResId());
        tvTittle.setText(item.getFullTitle());

        return view;
    }
}
