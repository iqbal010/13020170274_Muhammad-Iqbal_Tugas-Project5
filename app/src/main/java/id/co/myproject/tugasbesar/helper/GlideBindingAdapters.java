package id.co.myproject.tugasbesar.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.databinding.BindingAdapter;
import id.co.myproject.tugasbesar.R;

public class GlideBindingAdapters {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, String url){
        Context context = view.getContext();
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(url)
                .into(view);
    }

    @BindingAdapter("imageRes")
    public static void setImageResource(ImageView view, int res){
        Context context = view.getContext();
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(res)
                .into(view);
    }

    @BindingAdapter("imageBitmap")
    public static void setImageBitmap(ImageView imageView, String image){
        byte[] decodeString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        imageView.setImageBitmap(decodeByte);
    }
}
