package edu.pims.memegenerator;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MemeGenerator extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;

    TextView topTextView, bottomTextView;
    ImageView memeImageView;
    Button generateButton;
    LinearLayout memeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meme_generator);
        topTextView = findViewById(R.id.topTextView);
        bottomTextView = findViewById(R.id.bottomTextView);

        memeImageView = findViewById(R.id.memeImageView);
        memeLayout = findViewById(R.id.memeLayout);
        generateButton = findViewById(R.id.generateMemeBtn);


        String drawableName = getIntent().getStringExtra("drawableName");

        int resourceID = getResources().getIdentifier(drawableName, "drawable", getPackageName());

        memeImageView.setImageResource(resourceID);


        generateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Button to genereate meme and save in phone gallery
                AlertDialog.Builder builder=new AlertDialog.Builder(MemeGenerator.this);
                builder.setTitle("save to gallery");
                builder.setMessage("do you want to save meme to galary");
                builder.setCancelable(false);

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                         generateMeme();
                         finish();
                    }
                });

                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                         dialogInterface.dismiss();

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

               //  generateMeme();
            }


        });
    }



 void updateTopText(String text){
        topTextView.setText(text);
 }

    void updateBottomText(String text){
        bottomTextView.setText(text);
    }



    void generateMeme(){

        if (checkPermission()) {
            saveLayoutToGallery();
        } else {
            requestPermission();
        }



    }


    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveLayoutToGallery();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveLayoutToGallery() {
        // the parent layout containing your TextViews and ImageView
        Bitmap bitmap = getBitmapFromView(memeLayout);
        saveBitmapToGallery(bitmap, this);
    }

    public Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return bitmap;
    }


    public void saveBitmapToGallery(Bitmap bitmap, Context context) {
        String savedImagePath = null;

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/memeGenerator");

        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        File imageFile = new File(storageDir, imageFileName);
        savedImagePath = imageFile.getAbsolutePath();

        try {
            OutputStream fOut = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.close();

            MediaScannerConnection.scanFile(context, new String[]{imageFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

            Toast.makeText(context, "Image saved to gallery!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to save image!", Toast.LENGTH_SHORT).show();
        }
    }




}