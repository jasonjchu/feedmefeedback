package starterhacks.jwj.starterhacks;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class CouponDetails extends AppCompatActivity {

    private Bundle extras = getIntent().getExtras();
    private Coupon coupon = new Coupon();

    private ImageView imageView = findViewById(R.id.couponCode);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_details);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode("awesome",com.google.zxing.BarcodeFormat.QR_CODE,128,128);
            //CHANGE THE BITMATRIX INTO A BITMAP, CHANGE THE IMAGEVIEW OF couponCode


        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
