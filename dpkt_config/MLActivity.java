package com.noiztezk.pr13.ml;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.text.FirebaseVisionCloudText;
import com.google.firebase.ml.vision.cloud.text.FirebaseVisionCloudTextDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;
import com.noiztezk.pr13.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MLActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ml_layout);

        FirebaseVisionCloudDetectorOptions options =
                new FirebaseVisionCloudDetectorOptions.Builder()
                        .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                        .setMaxResults(15)
                        .build();

        findViewById(R.id.textView3).setOnClickListener(v -> {
            ImagePicker.create(MLActivity.this) // Activity or Fragment
                    .start();
        });


    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);


            FirebaseVisionImage image2 = null;
            try {
                image2 = FirebaseVisionImage.fromFilePath(MLActivity.this, Uri.fromFile(new File(image.getPath())));
            } catch (IOException e) {
                e.printStackTrace();
            }

            FirebaseVisionTextDetector detector = FirebaseVision.getInstance()
                    .getVisionTextDetector();

            Task<FirebaseVisionText> result = detector.detectInImage(image2)
                    .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                        @Override
                        public void onSuccess(FirebaseVisionText firebaseVisionText) {
                            for (FirebaseVisionText.Block block: firebaseVisionText.getBlocks()) {
                                Rect boundingBox = block.getBoundingBox();
                                Point[] cornerPoints = block.getCornerPoints();
                                String text = block.getText();

                                StringBuilder oneLine = new StringBuilder();
                                for (FirebaseVisionText.Line line: block.getLines()) {
                                    for (FirebaseVisionText.Element element: line.getElements()) {
                                        String text1 = element.getText();
                                        oneLine.append(text1+" ");
                                    }
                                    Log.d("TEST", oneLine.toString());
                                    oneLine = new StringBuilder();
                                }
                            }
                        }
                    });
//                    .addOnSuccessListener(firebaseVisionCloudText -> {
//                        // Task completed successfully
//                        // ...
//
//                        String recognizedText = firebaseVisionCloudText.getText();
//
//                        for (FirebaseVisionCloudText.Page page: firebaseVisionCloudText.getPages()) {
//                            List<FirebaseVisionCloudText.DetectedLanguage> languages =
//                                    page.getTextProperty().getDetectedLanguages();
//                            int height = page.getHeight();
//                            int width = page.getWidth();
//                            float confidence = page.getConfidence();
//
//                            for (FirebaseVisionCloudText.Block block: page.getBlocks()) {
//                                Rect boundingBox = block.getBoundingBox();
//                                List<FirebaseVisionCloudText.DetectedLanguage> blockLanguages =
//                                        block.getTextProperty().getDetectedLanguages();
//                                float blockConfidence = block.getConfidence();
//                                // And so on: Paragraph, Word, Symbol
//                            }
//                        }
//                    })
//                    .addOnFailureListener(e -> {
//                        // Task failed with an exception
//                        // ...
//                        Log.e("TEST", e != null ? "ckckck" : e.toString());
//                    });


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
