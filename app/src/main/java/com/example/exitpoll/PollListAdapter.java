package com.example.exitpoll;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PollListAdapter extends ArrayAdapter<Item_Poll> {

    private Context mContext;
    private int mResource;
    private List<Item_Poll> mPollItemList;

    public PollListAdapter(@NonNull Context context,
                            int resource,
                            @NonNull List<Item_Poll> pollItemList) {
        super(context, resource, pollItemList);
        this.mContext = context;
        this.mResource = resource;
        this.mPollItemList = pollItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, parent, false);

        TextView scoreTextView = view.findViewById(R.id.score_text_view);
        ImageView imageView = view.findViewById(R.id.image_view);

        Item_Poll pollItem = mPollItemList.get(position);
        String score = pollItem.score;
        String filename = pollItem.image;

        scoreTextView.setText(score);

        AssetManager am = mContext.getAssets();
        try {
            InputStream is = am.open(filename);//เปิดไฟล์ใน asserts มาอ่าน
            Drawable drawable = Drawable.createFromStream(is," ");
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            File privateDir = mContext.getFilesDir();
            File logoFile = new File(privateDir, filename);

            Bitmap bitmap = BitmapFactory.decodeFile(logoFile.getAbsolutePath(), null);
            imageView.setImageBitmap(bitmap);

            e.printStackTrace();
        }

        return view;
    }
}
