package graduation.hnust.simplebook.view.adatper;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import graduation.hnust.simplebook.R;
import graduation.hnust.simplebook.model.ConsumeType;
import graduation.hnust.simplebook.model.Item;

/**
 * 图片适配器, 用于展示消费类型列表(图标, 文字说明)
 *
 * @Author : panxin109@gmail.com
 * @Date : 2:58 PM 5/1/16
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<ConsumeType> typeList;

    public ImageAdapter(Context context, List<ConsumeType> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    /**
     * Returns the number of images to display
     *
     * @see android.widget.Adapter#getCount()
     */
    public int getCount() {
        return typeList.size();
    }

    /**
     * Returns the image at a specified position
     *
     * @see android.widget.Adapter#getItem(int)
     */
    public Object getItem(int position) {
        return typeList.get(position);
    }

    /**
     * Returns the id of an image at a specified position
     *
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Returns a view to display the image at a specified position
     *
     * @param position The position to display
     * @param convertView An existing view that we can reuse. May be null.
     * @param parent The parent view that will eventually hold the view we return.
     * @return A view to display the image at a specified position
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            // Make up a new view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.image_item, null);
        } else {
            // Use convertView if it is available
            view = convertView;
        }
        ImageView itemImage = (ImageView) view.findViewById(R.id.image_view_item);
        TextView itemName = (TextView) view.findViewById(R.id.txt_item_name);

        itemImage.setImageResource(typeList.get(position).getImageId());
        itemName.setText(typeList.get(position).getName());

        return view;
    }

}
