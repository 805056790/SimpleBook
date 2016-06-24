package graduation.hnust.simplebook.view.adatper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.model.Item;

/**
 * @Author : panxin109@gmail.com
 * @Date : 8:27 PM 5/3/16
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<ItemDto> itemList;

    public ItemAdapter(Context context, List<ItemDto> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            // Make up a new view
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview_item, null);
        } else {
            // Use convertView if it is available
            view = convertView;
        }

        ImageView imgType = (ImageView) view.findViewById(R.id.img_type);
        TextView txtTypeName = (TextView) view.findViewById(R.id.txt_type_name);
        TextView txtPercent = (TextView) view.findViewById(R.id.txt_percent);
        TextView txtAmount = (TextView) view.findViewById(R.id.txt_amount);

        imgType.setImageResource(itemList.get(position).getImageId());
        txtTypeName.setText(itemList.get(position).getTypeName());
        txtPercent.setText(itemList.get(position).getPercent() + "%");
        txtAmount.setText(itemList.get(position).getAmount()/100 + "");

        return view;
    }
}
