package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;


@Config
public class ConeVision extends Mechanism{
    private OpenCvCamera webcam;
    public ImageDeterminationPipeline pipeline;

    @Override
    public void init(HardwareMap hwMap) {
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hwMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new ImageDeterminationPipeline();
        webcam.setPipeline(pipeline);

        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened(){
                webcam.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode){

            }
        });
    }

    public void stop(){
        webcam.stopStreaming();
        webcam.closeCameraDevice();
    }

    public static class ImageDeterminationPipeline extends OpenCvPipeline {
        public enum Position {
            RIGHT,
            CENTER,
            LEFT
        }

        static final Scalar BLUE = new Scalar(0, 0, 225);

        // TODO: Find right point for region
        static final Point REGION_TOPLEFT_CORNER = new Point(130, 130);
        static final int WIDTH = 50;
        static final int HEIGHT = 60;

        Point region_pointA = new Point(
                REGION_TOPLEFT_CORNER.x,
                REGION_TOPLEFT_CORNER.y);
        Point region_pointB = new Point(
                REGION_TOPLEFT_CORNER.x + WIDTH,
                REGION_TOPLEFT_CORNER.y + HEIGHT);

        Mat region_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg;

        private volatile Position position = Position.RIGHT;

        void inputToCb(Mat input){
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 2);
        }

        @Override
        public void init(Mat firstFrame){
            inputToCb(firstFrame);
            region_Cb = Cb.submat(new Rect(region_pointA, region_pointB));
        }

        @Override
        public Mat processFrame(Mat input){
            inputToCb(input);

            avg = (int) Core.mean(region_Cb).val[0];

            Imgproc.rectangle(
                    input,
                    region_pointA,
                    region_pointB,
                    BLUE,
                    2);

            // TODO: find out ranges of values depending on the image
            if (avg == 100){
                position = Position.RIGHT;
            } else if (avg == 200) {
                position = Position.CENTER;
            } else if (avg == 300) {
                position = Position.LEFT;
            }

            return input;
        }

        // return position once you know ranges
        public int getPosition(){
            return avg;
        }


    }


}

